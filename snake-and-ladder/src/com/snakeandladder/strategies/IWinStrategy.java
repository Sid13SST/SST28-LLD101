package com.snakeandladder.strategies;

import com.snakeandladder.Player;
import com.snakeandladder.Board;

public interface IWinStrategy {
    boolean checkWin(Player player, Board board);
}
