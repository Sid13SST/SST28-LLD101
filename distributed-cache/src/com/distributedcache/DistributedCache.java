package com.distributedcache;

import java.util.ArrayList;
import java.util.List;
import com.distributedcache.db.Database;
import com.distributedcache.node.CacheNode;
import com.distributedcache.policy.LRUEvictionPolicy;
import com.distributedcache.strategy.DistributionStrategy;

public class DistributedCache {
    private final List<CacheNode> nodes;
    private final DistributionStrategy strategy;
    private final Database db;

    public DistributedCache(int numberOfNodes, int nodeCapacity, DistributionStrategy strategy, Database db) {
        this.nodes = new ArrayList<>(numberOfNodes);
        for (int i = 0; i < numberOfNodes; i++) {
            this.nodes.add(new CacheNode("node-" + (i + 1), nodeCapacity, new LRUEvictionPolicy<>(nodeCapacity)));
        }
        this.strategy = strategy;
        this.db = db;
    }

    public String get(String key) {
        CacheNode targetNode = strategy.selectNode(key, nodes);
        String value = targetNode.get(key);

        if (value == null) {
            System.out.println("DistributedCache: Cache Miss for key: " + key + ". Fetching from Database...");
            value = db.fetch(key);
            if (value != null) {
                targetNode.put(key, value);
            }
        } else {
            System.out.println("DistributedCache: Cache Hit for key: " + key + " on " + targetNode.getNodeId());
        }

        return value;
    }

    public void put(String key, String value) {
        CacheNode targetNode = strategy.selectNode(key, nodes);
        System.out.println("DistributedCache: Storing key: " + key + " in " + targetNode.getNodeId());
        targetNode.put(key, value);
    }
}
