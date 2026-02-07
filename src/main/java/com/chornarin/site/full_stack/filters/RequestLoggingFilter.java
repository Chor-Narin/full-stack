package com.chornarin.site.full_stack.filters;

import java.io.IOException;
import java.util.List;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j // Lombok annotation , creates logs 'fields' automatically
@Order(2)

public class RequestLoggingFilter extends OncePerRequestFilter {

    private static final List<String> SKIP_PATHS = List.of(
        "/autuator/health", "/favicon.io", "/api/auth/*"
    );

    @Override
    protected void doFilterInternal(HttpServletRequest request,
            HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // 1. Pre-processing (before controller)
        log.info("Incoming request: {} {}", request.getMethod(), request.getRequestURI());

        // 2. Continue chain
        filterChain.doFilter(request, response);

        // 3. Post-processing (after controller)
        log.info("Response status: {}", response.getStatus());
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String path = request.getRequestURI();
        log.info("this is the message responses from ", request.getSession());
        return SKIP_PATHS.stream().anyMatch(path::startsWith);
    }

}
