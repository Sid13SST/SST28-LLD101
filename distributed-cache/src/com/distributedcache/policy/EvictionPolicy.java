package com.distributedcache.policy;

public interface EvictionPolicy<K> {
    void keyAccessed(K key);
    void keyPut(K key);
    K evict();
}
