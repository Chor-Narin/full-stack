package com.chornarin.site.full_stack.ServiceImp;

import java.util.Date;
import java.util.Map;
import java.util.function.Function;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.WeakKeyException;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.chornarin.site.full_stack.config.auth.JwtProperties;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.crypto.SecretKey;

@Service
@Data
@RequiredArgsConstructor
public class JwtServiceImp {

    private final JwtProperties jwtProperties;

    private SecretKey getSigningKey() {
        String secret = jwtProperties.getSecret();
        if (secret == null) {
            throw new IllegalStateException("JWT secret is null");
        }
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        if (keyBytes.length < 32) {
            throw new WeakKeyException("Secret key too short (<256 bits)");
        }
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
        return Jwts.builder()
            .claims(extraClaims)
            .subject(userDetails.getUsername())
            .issuedAt(new Date())
            .expiration(new Date(System.currentTimeMillis() + jwtProperties.getExpiration()))
            .signWith(getSigningKey(), SignatureAlgorithm.HS256)  // or HS512 for stronger
            .compact();
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    private <T> T extractClaim(String token, Function<Claims, T> resolver) {
        return resolver.apply(extractAllClaims(token));
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser().verifyWith(getSigningKey()).build()
            .parseSignedClaims(token).getPayload();
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        return extractUsername(token).equals(userDetails.getUsername())
            && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractClaim(token, Claims::getExpiration).before(new Date());
    }

    public String generateRefreshToken(UserDetails user) {
        return Jwts.builder()
            .subject(user.getUsername())
            .issuedAt(new Date())
            .expiration(new Date(System.currentTimeMillis() + jwtProperties.getRefreshExpiration()))
            .signWith(getSigningKey(), SignatureAlgorithm.HS256)
            .compact();
    }
}