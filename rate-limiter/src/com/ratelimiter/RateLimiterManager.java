package com.ratelimiter;

import com.ratelimiter.config.RateLimitConfig;
import com.ratelimiter.strategy.RateLimitingStrategy;
import java.util.HashMap;
import java.util.Map;

/**
 * RateLimiterManager acts as the central coordinator for rate limiting.
 * It manages configurations and strategy assignments for different keys.
 * This simplifies the interface for internal services to a single call.
 */
public class RateLimiterManager {
    private final Map<String, RateLimitConfig> configs = new HashMap<>();
    private final Map<String, RateLimitingStrategy> strategies = new HashMap<>();

    /**
     * Registers a new rate limit for a given key.
     */
    public void registerKey(String key, RateLimitConfig config, RateLimitingStrategy strategy) {
        configs.put(key, config);
        strategies.put(key, strategy);
    }

    /**
     * Updates the strategy for an existing key at runtime.
     * Demonstrates the Strategy Pattern's pluggable nature.
     */
    public void setStrategy(String key, RateLimitingStrategy strategy) {
        if (configs.containsKey(key)) {
            strategies.put(key, strategy);
        }
    }

    /**
     * Checks if a request is allowed for the given key.
     */
    public boolean isAllowed(String key) {
        RateLimitConfig config = configs.get(key);
        RateLimitingStrategy strategy = strategies.get(key);

        if (config == null || strategy == null) {
            return true;
        }

        return strategy.isAllowed(key, config);
    }
}
