package com.chornarin.site.full_stack.ServiceImp.rateLimit;


import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Refill;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class RateLimiterService {

    private final Map<String, Bucket> buckets = new ConcurrentHashMap<>();

    public boolean tryConsume(String key, long limit, long durationSeconds) {
        Bucket bucket = buckets.computeIfAbsent(key, k -> createNewBucket(limit, durationSeconds));
        return bucket.tryConsume(1);
    }

    private Bucket createNewBucket(long limit, long durationSeconds) {
        Refill refill = Refill.intervally(limit, Duration.ofSeconds(durationSeconds));
        Bandwidth limitBandwidth = Bandwidth.classic(limit, refill);
        return Bucket.builder().addLimit(limitBandwidth).build();
    }
}
