package com.chornarin.site.full_stack.ServiceImp;

import java.time.Instant;
import java.util.List;
import java.util.Map;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.chornarin.site.full_stack.Exceptions.ExistEmailException;
import com.chornarin.site.full_stack.ServiceImp.jwt.AuthService;
import com.chornarin.site.full_stack.ServiceImp.jwt.JwtServiceImp;
import com.chornarin.site.full_stack.config.auth.JwtProperties;
import com.chornarin.site.full_stack.dto.requests.LoginRequest;
import com.chornarin.site.full_stack.dto.requests.RegisterRequest;
import com.chornarin.site.full_stack.dto.responses.AuthResponse;
import com.chornarin.site.full_stack.dto.responses.UserDto;
import com.chornarin.site.full_stack.models.User;
import com.chornarin.site.full_stack.repository.UserRepository;
import com.chornarin.site.full_stack.services.UserService;

import jakarta.annotation.security.PermitAll;

import org.springframework.security.authentication.LockedException;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Service
@Data
@RequiredArgsConstructor
public class UserServiceImp implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final AuthService authService;
    private final JwtServiceImp jwtServiceImp;
    private final JwtProperties jwtProperties;
    private static final int MAX_FAILED_ATTEMPTS = 5;
    private static final int LOCK_DURATION_MINUTES = 900;
    private static final long LOCK_DURATION_SECONDS = 0;

    @Override
    public Boolean findByEmail(String email) {
        throw new UnsupportedOperationException("Unimplemented method 'findByEmail'");
    }

    // @PreAuthorize("hasRole('ADMIN')")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @PermitAll
    public List<User> getUser() {
        return userRepository.findAll();
    }

    // Sign Up
    @Override
    public void register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.email())) {
            throw new ExistEmailException(request.email());
        }
        User user = new User();
        user.setUsername(request.username());
        user.setEmail(request.email());
        user.setRole(request.role());
        user.setPassword(passwordEncoder.encode(request.password()));
        userRepository.save(user);
    }

    // Sign In
    @Override
    public AuthResponse login(LoginRequest request) {

        try {
            User user = userRepository.findByEmail(request.email())
                    .orElseThrow(() -> new UsernameNotFoundException("User not found"));

            // Handle already locked account & auto-unlock
            if (Boolean.TRUE.equals(user.getAccountLocked()) && user.getLockTime() != null) {
                Instant unlockTime = user.getLockTime().plusSeconds(LOCK_DURATION_SECONDS); // better name
                if (Instant.now().isAfter(unlockTime)) {
                    user.setAccountLocked(false);
                    user.setLockTime(null);
                    user.setFailedLoginAttempts(0);
                    userRepository.save(user);
                } else {
                    throw new org.springframework.security.authentication.LockedException(
                            "Account is locked. Try again after " + unlockTime + " or contact support.");
                }
            }

            // This line can throw LockedException, BadCredentialsException, etc.
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            user.getEmail(), request.password()));

            authService.recordSuccessfulLogin(user.getEmail());

            return generateAuthResponse(user);

        } catch (LockedException e) {
            throw new org.springframework.security.authentication.LockedException(e.getMessage(), e);

        } catch (BadCredentialsException e) {
            authService.recordFailedLogin(request.email());

            // Re-fetch user (in case it was locked during this attempt)
            User user = userRepository.findByEmail(request.email())
                    .orElseThrow(() -> new IllegalStateException("User disappeared"));

            if (Boolean.TRUE.equals(user.getAccountLocked())) {
                throw new org.springframework.security.authentication.LockedException(
                        "Account locked due to too many failed attempts.");
            }

            throw new BadCredentialsException("Invalid email or password", e);
        }
    }

    private AuthResponse generateAuthResponse(User user) {
        Map<String, Object> claims = Map.of("role", user.getRole().name());
        String accessToken = jwtServiceImp.generateToken(claims, user); // user is UserDetails
        String refreshToken = jwtServiceImp.generateRefreshToken(user);

        return new AuthResponse(
                accessToken,
                refreshToken,
                user.getUsername(), // ← fixed (or user.getEmail() if preferred)
                jwtProperties.getExpiration(),
                new UserDto(user.getId(), user.getEmail(), user.getUsername(), user.getRole().name()));
    }
}
