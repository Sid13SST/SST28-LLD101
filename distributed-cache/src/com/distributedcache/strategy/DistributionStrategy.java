package com.distributedcache.strategy;

import java.util.List;
import com.distributedcache.node.CacheNode;

public interface DistributionStrategy {
    CacheNode selectNode(String key, List<CacheNode> nodes);
}
