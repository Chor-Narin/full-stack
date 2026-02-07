package com.chornarin.site.full_stack.config.auth;

import java.util.Optional;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.chornarin.site.full_stack.models.User;

@Component
public class SecurityUtils {

    public Optional<User> getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !auth.isAuthenticated() || auth.getPrincipal().equals("anonymousUser")) {
            return Optional.empty();
        }
        return Optional.of((User) auth.getPrincipal());
    }

    public User requireCurrentUser() {
        return getCurrentUser()
                .orElseThrow(() -> new RuntimeException("Not authenticated"));
    }

    public boolean isCurrentUser(Long userId) {
        return getCurrentUser()
                .map(u -> u.getId().equals(userId))
                .orElse(false);
    }

    public boolean hasRole(String role) {
        return SecurityContextHolder.getContext().getAuthentication()
                .getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_" + role));
    }
}
