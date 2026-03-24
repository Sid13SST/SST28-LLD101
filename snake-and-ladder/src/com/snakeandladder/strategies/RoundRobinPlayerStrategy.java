package com.snakeandladder.strategies;

import com.snakeandladder.Player;
import java.util.Queue;

public class RoundRobinPlayerStrategy implements IPickPlayerStrategy {
    @Override
    public Player pickNextPlayer(Queue<Player> players) {
        return players.poll();
    }
}
