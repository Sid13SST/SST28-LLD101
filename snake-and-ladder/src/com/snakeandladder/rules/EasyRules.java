package com.snakeandladder.rules;

import com.snakeandladder.strategies.*;

public class EasyRules implements Rule {
    @Override
    public IMakeMoveStrategy getMakeMoveStrategy() {
        return new AllowConsecutive();
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
