package com.distributedcache.strategy;

import java.util.List;
import com.distributedcache.node.CacheNode;

public class ModuloDistributionStrategy implements DistributionStrategy {
    @Override
    public CacheNode selectNode(String key, List<CacheNode> nodes) {
        if (nodes == null || nodes.isEmpty()) return null;
        int nodeIndex = Math.abs(key.hashCode()) % nodes.size();
        return nodes.get(nodeIndex);
    }
}
