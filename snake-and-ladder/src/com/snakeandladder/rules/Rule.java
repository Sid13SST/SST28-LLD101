package com.snakeandladder.rules;

import com.snakeandladder.strategies.IMakeMoveStrategy;
import com.snakeandladder.strategies.IPickPlayerStrategy;
import com.snakeandladder.strategies.IWinStrategy;

public interface Rule {
    IMakeMoveStrategy getMakeMoveStrategy();
    IWinStrategy getWinStrategy();
    IPickPlayerStrategy getPickPlayerStrategy();
}
