// package com.chornarin.site.full_stack.config.rateLimiting;

// import java.time.Duration;
// import java.util.Map;
// import java.util.concurrent.ConcurrentHashMap;

// import org.springframework.context.annotation.Configuration;

// import io.github.bucket4j.Bucket;

// @Configuration
// public class RateLimiterConfig {
//     private final Map<String, Bucket> buckets = new ConcurrentHashMap<>();



//     private RateLimiterConfig getConfigForEndpoint(String endpoint){
//         if(endpoint.startsWith("/api/auth/login")){
//             return new RateLimiterConfig(5, Duration.ofMinutes(1)); // Brute force protection
//         }
//         if(endpoint.startsWith("/api/auth/register")){
//             return new RateLimiterConfig(3, Duration.ofMinutes(1)); // spam protection
//         }
//         return new RateLimiterConfig(100, Duration.ofSeconds(100));
//     }


//     public boolean tryConsume(String clientId, String endpoint){
//         String key = clientId + ":" + endpoint;
//         RateLimiterConfig config = getConfigForEndpoint(endpoint);
//         Bucket bucket = buckets.computeIfAbsent(key, k->createBucket(config));
//         return bucket.tryConsume(1);
//     }

// }
