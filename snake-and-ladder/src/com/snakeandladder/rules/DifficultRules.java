package com.snakeandladder.rules;

import com.snakeandladder.strategies.*;

public class DifficultRules implements Rule {
    @Override
    public IMakeMoveStrategy getMakeMoveStrategy() {
        return new SkipOn3Consecutives();
    }

    @Override
    public IWinStrategy getWinStrategy() {
        return new StandardWinStrategy();
    }

    @Override
    public IPickPlayerStrategy getPickPlayerStrategy() {
        return new RoundRobinPlayerStrategy();
    }
}
