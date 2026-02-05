package com.chornarin.site.full_stack.config.auth;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Configuration
@NoArgsConstructor
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {
    
    @NotBlank
    private String secret = "f60882e019fc5871ddb5d12e3cf89af4811ce230991b492160c436549c4a8f3e7e261a39689c2f4d7e921ef9152a00deee8293df9a3e7ddfa3d32ed52cbd4d14";

    @Min(60000)
    private long expiration = 86400000;

    @Min(60000)
    private long refreshExpiration = 604800000 ;

}
