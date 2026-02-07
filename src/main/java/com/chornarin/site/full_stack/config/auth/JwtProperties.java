package com.chornarin.site.full_stack.config.auth;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;


@Data
@Configuration
@NoArgsConstructor
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {
    
    @NotBlank
    @Value("${JWT_SECRET}")
    private String secret;

    @Min(60000)
    @Value("${EXPIRATION}")
    private long expiration = 86400000;

    @Min(60000)
    @Value("${REFRESH_EXPIRATION}")
    private long refreshExpiration = 604800000 ;

}
