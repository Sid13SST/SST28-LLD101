package com.snakeandladder;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Game {
    private Board board;
    private Dice dice;
    private Queue<Player> players;
    private List<Player> rank;

    public Game(Board board, Dice dice, List<Player> playerList) {
        this.board = board;
        this.dice = dice;
        this.players = new LinkedList<>(playerList);
        this.rank = new LinkedList<>();
    }

    public void play() {
        // Game continues until at least 2 players are still playing to win.
        // It means play() stops when only 1 player is left (they are the loser).
        while (players.size() > 1) { 
            Player currentPlayer = players.poll();
            int rollValue = dice.roll();
            int currentPosition = currentPlayer.getPosition();
            int newPosition = currentPosition + rollValue;

            if (newPosition <= board.getTotalCells()) {
                System.out.println(currentPlayer.getName() + " rolled a " + rollValue + " and moves from " + currentPosition + " to " + newPosition);
                
                // Check if snake or ladder applies
                newPosition = board.getNextPosition(newPosition);
                currentPlayer.setPosition(newPosition);

                if (newPosition == board.getTotalCells()) {
                    System.out.println(">>> " + currentPlayer.getName() + " wins the game! <<<");
                    rank.add(currentPlayer);
                } else {
                    players.offer(currentPlayer);
                }
            } else {
                System.out.println(currentPlayer.getName() + " rolled a " + rollValue + " but needs exactly " + (board.getTotalCells() - currentPosition) + " to win. Stays at " + currentPosition);
                players.offer(currentPlayer); // Player stays at same position and goes to back of queue
            }
        }
        
        if (players.size() == 1) {
            Player lastPlayer = players.poll();
            System.out.println(lastPlayer.getName() + " loses the game.");
            rank.add(lastPlayer);
        }
        
        System.out.println("----- Final Rankings -----");
        for (int i = 0; i < rank.size(); i++) {
            System.out.println((i + 1) + ". " + rank.get(i).getName());
        }
    }
}
