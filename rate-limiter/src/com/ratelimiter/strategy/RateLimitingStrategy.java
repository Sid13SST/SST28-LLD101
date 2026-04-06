package com.ratelimiter.strategy;

import com.ratelimiter.config.RateLimitConfig;

/**
 * Interface for rate-limiting algorithms.
 * Allows plugging in different strategies without changing the business logic.
 */
public interface RateLimitingStrategy {
    boolean isAllowed(String key, RateLimitConfig config);
}
