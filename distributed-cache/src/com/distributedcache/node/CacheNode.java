package com.distributedcache.node;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import com.distributedcache.policy.EvictionPolicy;

public class CacheNode {
    private final String nodeId;
    private final int capacity;
    private final Map<String, String> storage;
    private final EvictionPolicy<String> evictionPolicy;

    public CacheNode(String nodeId, int capacity, EvictionPolicy<String> evictionPolicy) {
        this.nodeId = nodeId;
        this.capacity = capacity;
        this.storage = new ConcurrentHashMap<>();
        this.evictionPolicy = evictionPolicy;
    }

    public synchronized String get(String key) {
        if (storage.containsKey(key)) {
            evictionPolicy.keyAccessed(key);
            return storage.get(key);
        }
        return null;
    }

    public synchronized void put(String key, String value) {
        if (storage.containsKey(key)) {
            storage.put(key, value);
            evictionPolicy.keyAccessed(key);
            return;
        }

        if (storage.size() >= capacity) {
            String evictedKey = evictionPolicy.evict();
            if (evictedKey != null) {
                storage.remove(evictedKey);
                System.out.println("Node " + nodeId + ": Evicted key: " + evictedKey);
            }
        }

        storage.put(key, value);
        evictionPolicy.keyPut(key);
    }

    public String getNodeId() {
        return nodeId;
    }
}
