package com.ratelimiter.config;

/**
 * Configuration for a rate limiter instance.
 * Defines the maximum number of requests allowed within a specific time window.
 */
public class RateLimitConfig {
    private final int maxRequests;
    private final long windowSizeInMillis;

    public RateLimitConfig(int maxRequests, long windowSizeInMillis) {
        this.maxRequests = maxRequests;
        this.windowSizeInMillis = windowSizeInMillis;
    }

    public int getMaxRequests() {
        return maxRequests;
    }

    public long getWindowSizeInMillis() {
        return windowSizeInMillis;
    }

    @Override
    public String toString() {
        return String.format("RateLimitConfig{maxRequests=%d, windowSize=%d ms}", maxRequests, windowSizeInMillis);
    }
}
