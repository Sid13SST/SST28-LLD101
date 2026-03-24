package com.snakeandladder.strategies;

import com.snakeandladder.Player;
import com.snakeandladder.Board;

public class StandardWinStrategy implements IWinStrategy {
    @Override
    public boolean checkWin(Player player, Board board) {
        return player.getPosition() == board.getTotalCells();
    }
}
