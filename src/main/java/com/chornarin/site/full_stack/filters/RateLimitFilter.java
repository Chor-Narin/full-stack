package com.chornarin.site.full_stack.filters;

import java.io.IOException;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.chornarin.site.full_stack.ServiceImp.RateLimitServiceImp;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import tools.jackson.databind.ObjectMapper;


@Component
@RequiredArgsConstructor
@Data
public class RateLimitFilter extends OncePerRequestFilter {
    private final RateLimitServiceImp rateLimitFilter;
    private final ObjectMapper objectMapper;

    @Override
    protected void doFilterInternal(
        HttpServletRequest request, 
        HttpServletResponse response, 
        FilterChain filterChain)
            throws ServletException, IOException {
        String clientId = getClientIdentifier(request);
        if(!rateLimitFilter.tryConsume(clientId)){
            sendRateLimitResponse(response);
            return;
        }
        filterChain.doFilter(request, response);
    }

    private String getClientIdentifier(HttpServletRequest request){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth != null && auth.isAuthenticated() && !"anonymousUser".equals(auth.getPrincipal())){
            return "user:" + auth.getName(); // per-user limit
        }
        return "ip:" + getClientIp(request);
    }

    private String getClientIp(HttpServletRequest request){
        String xff = request.getHeader("X-Forwarded-For"); // behind proxy
        return (xff != null) ? xff.split(",")[0].trim() : request.getRemoteAddr();
    }

    private void sendRateLimitResponse(HttpServletResponse response) throws IOException{
        response.setStatus(429);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setIntHeader("Retry-After", 60);

        objectMapper.writeValue(response.getOutputStream(),Map.of(
            "status", 429,
            "error" , "Too Many Requests",
            "message" , "Rate Limit exceeded, Please try again later"
        ) 
    );
    }

}
