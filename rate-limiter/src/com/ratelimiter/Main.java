package com.ratelimiter;

import com.ratelimiter.config.RateLimitConfig;
import com.ratelimiter.strategy.FixedWindowCounter;
import com.ratelimiter.strategy.RateLimitingStrategy;
import com.ratelimiter.strategy.SlidingWindowCounter;

/**
 * Demo class to showcase the Pluggable Rate Limiting System.
 * Demonstrates multiple algorithms, multiple keys, and runtime switching.
 */
public class Main {
    public static void main(String[] args) {
        RateLimiterManager manager = new RateLimiterManager();

        // 1. Setup Customer T1: 5 requests per 1 minute (Fixed Window)
        String customerT1 = "Customer-T1";
        RateLimitConfig t1Config = new RateLimitConfig(5, 60 * 1000);
        RateLimitingStrategy fixedWindow = new FixedWindowCounter();
        manager.registerKey(customerT1, t1Config, fixedWindow);

        System.out.println("--- Testing Customer T1 (Fixed Window, 5 req/min) ---");
        for (int i = 1; i <= 7; i++) {
            boolean allowed = manager.isAllowed(customerT1);
            System.out.println("Request " + i + ": " + (allowed ? "ALLOWED" : "DENIED (Limit Exceeded)"));
        }

        // 2. Setup Tenant P1: 1000 requests per 1 hour (Sliding Window)
        String tenantP1 = "Tenant-P1";
        RateLimitConfig p1Config = new RateLimitConfig(10, 3600 * 1000);
        RateLimitingStrategy slidingWindow = new SlidingWindowCounter();
        manager.registerKey(tenantP1, p1Config, slidingWindow);

        System.out.println("\n--- Testing Tenant P1 (Sliding Window, 10 req/hr) ---");
        for (int i = 1; i <= 5; i++) {
            boolean allowed = manager.isAllowed(tenantP1);
            System.out.println("Request " + i + ": " + (allowed ? "ALLOWED" : "DENIED"));
        }

        // 3. Runtime Switching: Switch Customer T1 to Sliding Window
        System.out.println("\n--- Switching Customer T1 to Sliding Window at Runtime ---");
        manager.setStrategy(customerT1, slidingWindow);
        boolean allowedAfterSwitch = manager.isAllowed(customerT1);
        System.out.println("Request after strategy switch: " + (allowedAfterSwitch ? "ALLOWED" : "DENIED"));

        // 4. Business Logic Integration simulation
        System.out.println("\n--- Business Logic Integration Simulation ---");
        simulateInternalServiceCall(manager, "Customer-T1", true); 
        simulateInternalServiceCall(manager, "Customer-T1", false); 
    }

    private static void simulateInternalServiceCall(RateLimiterManager manager, String key, boolean needsExternalResource) {
        System.out.println("Service processing request for " + key + "...");
        if (needsExternalResource) {
            System.out.print("  External resource needed. Consulting rate limiter... ");
            if (manager.isAllowed(key)) {
                System.out.println("ALLOWED. Proceeding with external call.");
            } else {
                System.out.println("REJECTED. Gracefully handling rate limit exceeded.");
            }
        } else {
            System.out.println("  No external resource needed. Business logic completed.");
        }
    }
}
