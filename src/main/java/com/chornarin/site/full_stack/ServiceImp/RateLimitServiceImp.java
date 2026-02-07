package com.chornarin.site.full_stack.ServiceImp;

import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Refill;

@Service
public class RateLimitServiceImp {

    private final Map<String, Bucket> buckets = new ConcurrentHashMap<>();
    private static final int CAPACITY = 100; // requests per minute

    public boolean tryConsume(String clientId){
        Bucket bucket = buckets.computeIfAbsent(clientId, this::createBucket);
        return bucket.tryConsume(1);

    }

    private Bucket createBucket(String clientId){
        return Bucket.builder()
            .addLimit(Bandwidth.classic(CAPACITY,
                Refill.greedy(CAPACITY, Duration.ofMinutes(1))))
            .build();
    }


}
