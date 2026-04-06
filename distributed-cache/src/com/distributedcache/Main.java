package com.distributedcache;

import com.distributedcache.db.Database;
import com.distributedcache.strategy.ModuloDistributionStrategy;

public class Main {
    public static void main(String[] args) {
        Database db = (key) -> {
            System.out.println("DB Access: Fetching value for key: " + key);
            return "value_from_db_for_" + key;
        };

        DistributedCache cache = new DistributedCache(3, 2, new ModuloDistributionStrategy(), db);

        System.out.println("--- Store Some Data ---");
        cache.put("user1", "Siddhant");
        cache.put("user2", "Ayush");
        cache.put("user3", "Shreyansh");
        cache.put("user4", "Abhishek");

        System.out.println("\n--- Get Data (Cache Hit) ---");
        System.out.println("Result: " + cache.get("user1"));
        System.out.println("Result: " + cache.get("user2"));

        System.out.println("\n--- Get Data (Cache Miss) ---");
        System.out.println("Result: " + cache.get("user5"));

        System.out.println("\n--- Fill a Node to Trigger Eviction ---");
        cache.put("user6", "Rishabh");
        cache.put("user9", "Manav");
        cache.put("user12", "Aarav");
        cache.put("user15", "Ansh");

        System.out.println("\n--- Verify LRU Eviction ---");
        System.out.println("Getting key 'user1' (which might have been evicted if it was on a full node)");
        System.out.println("Result: " + cache.get("user1"));
    }
}
