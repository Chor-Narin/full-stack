package com.chornarin.site.full_stack.ServiceImp.rateLimit;


import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.chornarin.site.full_stack.annotations.RateLimited;

@Aspect
@Component
@RequiredArgsConstructor
public class RateLimitingAspect {

    private final RateLimiterService rateLimiterService;
    private final HttpServletRequest request;

    @Around("@annotation(rateLimited)")
    public Object around(ProceedingJoinPoint joinPoint, RateLimited rateLimited) throws Throwable {
        String key = request.getRemoteAddr(); // or use userId for authenticated users

        boolean allowed = rateLimiterService.tryConsume(
                key,
                rateLimited.limit(),
                rateLimited.duration()
        );

        if (!allowed) {
            return ResponseEntity.status(429).body("Too Many Requests");
        }

        return joinPoint.proceed();
    }
}
