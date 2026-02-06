package com.chornarin.site.full_stack.enums;

import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.chornarin.site.full_stack.ServiceImp.RateLimitServiceImp;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Refill;
import lombok.Data;

public enum RateLimitTier {
    

    ANONYMOUS(20),
    USER(10),
    PREMIUM(500),
    ADMIN(1000);

    private final int capacity;

    RateLimitTier(int capacity){
        this.capacity = capacity;
    }

    public int getCapacity() {
        return capacity;
    }
    
    private final Map<String , Bucket > buckets = new ConcurrentHashMap<>();

    

    public boolean tryConsume(String clientId, RateLimitTier tier){
        String key = clientId + ":" + tier.name();
        Bucket bucket = buckets.computeIfAbsent(key, k-> createBucket(tier));
        return bucket.tryConsume(1);
    }

    private Bucket createBucket(RateLimitTier tier){
        return Bucket.builder()
            .addLimit(Bandwidth.classic(tier.getCapacity(), Refill.greedy(tier.getCapacity(), Duration.ofMinutes(1))))
            .build();
    }

}
