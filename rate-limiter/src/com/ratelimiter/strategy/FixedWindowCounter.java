package com.ratelimiter.strategy;

import com.ratelimiter.config.RateLimitConfig;
import java.util.HashMap;
import java.util.Map;

/**
 * Fixed Window Counter Algorithm.
 * Divides time into fixed-size windows and resets the counter at the start of each window.
 */
public class FixedWindowCounter implements RateLimitingStrategy {
    private final Map<String, WindowData> clientData = new HashMap<>();

    @Override
    public boolean isAllowed(String key, RateLimitConfig config) {
        long currentTime = System.currentTimeMillis();
        long windowStart = (currentTime / config.getWindowSizeInMillis()) * config.getWindowSizeInMillis();

        clientData.putIfAbsent(key, new WindowData(windowStart, 0));
        WindowData data = clientData.get(key);

        if (data.windowStartTime != windowStart) {
            data.windowStartTime = windowStart;
            data.count = 0;
        }

        if (data.count < config.getMaxRequests()) {
            data.count++;
            return true;
        }

        return false; 
    }

    private static class WindowData {
        long windowStartTime;
        int count;

        WindowData(long windowStartTime, int count) {
            this.windowStartTime = windowStartTime;
            this.count = count;
        }
    }
}
