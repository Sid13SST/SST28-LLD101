package com.snakeandladder.strategies;

import com.snakeandladder.Player;
import java.util.Queue;

public interface IPickPlayerStrategy {
    Player pickNextPlayer(Queue<Player> players);
}
