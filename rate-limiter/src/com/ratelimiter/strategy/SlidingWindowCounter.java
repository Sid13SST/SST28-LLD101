package com.ratelimiter.strategy;

import com.ratelimiter.config.RateLimitConfig;
import java.util.HashMap;
import java.util.Map;

/**
 * Sliding Window Weighted Counter Algorithm.
 * Instead of fixed boundaries, it uses a weighted count between current and previous windows.
 */
public class SlidingWindowCounter implements RateLimitingStrategy {
    private final Map<String, SlidingWindowData> clientData = new HashMap<>();

    @Override
    public boolean isAllowed(String key, RateLimitConfig config) {
        long currentTime = System.currentTimeMillis();
        long currentWindowStart = (currentTime / config.getWindowSizeInMillis()) * config.getWindowSizeInMillis();

        SlidingWindowData data = clientData.computeIfAbsent(key, k -> new SlidingWindowData(currentWindowStart));

        if (data.currentWindowStart != currentWindowStart) {
            if (currentWindowStart - data.currentWindowStart == config.getWindowSizeInMillis()) {
                data.previousWindowCount = data.currentWindowCount;
            } else {
                data.previousWindowCount = 0;
            }
            data.currentWindowCount = 0;
            data.currentWindowStart = currentWindowStart;
        }

        // Weighted calculation: currentCount + (previousCount * fraction of previous window overlapping)
        double weight = 1.0 - ((double) (currentTime - currentWindowStart) / config.getWindowSizeInMillis());
        double estimatedCount = data.currentWindowCount + (data.previousWindowCount * weight);

        if (estimatedCount < config.getMaxRequests()) {
            data.currentWindowCount++;
            return true;
        }

        return false; 
    }

    private static class SlidingWindowData {
        long currentWindowStart;
        int currentWindowCount;
        int previousWindowCount;

        SlidingWindowData(long currentWindowStart) {
            this.currentWindowStart = currentWindowStart;
            this.currentWindowCount = 0;
            this.previousWindowCount = 0;
        }
    }
}
