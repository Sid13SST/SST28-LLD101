package com.distributedcache.policy;

import java.util.LinkedHashMap;
import java.util.Map;

public class LRUEvictionPolicy<K> implements EvictionPolicy<K> {
    private final Map<K, Boolean> accessOrder;
    private final int capacity;

    public LRUEvictionPolicy(int capacity) {
        this.capacity = capacity;
        // true for access-order, false for insertion-order
        this.accessOrder = new LinkedHashMap<>(capacity, 0.75f, true);
    }

    @Override
    public void keyAccessed(K key) {
        accessOrder.get(key); // Triggers LRU update in LinkedHashMap
    }

    @Override
    public void keyPut(K key) {
        accessOrder.put(key, true);
    }

    @Override
    public K evict() {
        if (accessOrder.isEmpty()) return null;
        K eldestKey = accessOrder.keySet().iterator().next();
        accessOrder.remove(eldestKey);
        return eldestKey;
    }
}
