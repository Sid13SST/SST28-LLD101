package com.snakeandladder;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import com.snakeandladder.rules.Rule;

public class Game {
    private Board board;
    private Dice dice;
    private Queue<Player> players;
    private List<Player> rank;
    private Rule rule;

    public Game(Board board, Dice dice, List<Player> playerList, Rule rule) {
        this.board = board;
        this.dice = dice;
        this.players = new LinkedList<>(playerList);
        this.rank = new LinkedList<>();
        this.rule = rule;
    }

    public void play() {
        while (players.size() > 1) { 
            Player currentPlayer = rule.getPickPlayerStrategy().pickNextPlayer(players);
            
            System.out.print(currentPlayer.getName() + "'s turn. ");
            int rollValue = rule.getMakeMoveStrategy().makeMove(dice);
            
            if (rollValue == 0) {
                // Turn was skipped or total zero
                players.offer(currentPlayer);
                System.out.println(); // newline for formatting
                continue;
            } else {
                System.out.println("\nTotal roll: " + rollValue);
            }

            int currentPosition = currentPlayer.getPosition();
            int newPosition = currentPosition + rollValue;

            if (newPosition <= board.getTotalCells()) {
                System.out.println(currentPlayer.getName() + " moves from " + currentPosition + " to " + newPosition);
                
                newPosition = board.getNextPosition(newPosition);
                currentPlayer.setPosition(newPosition);

                if (rule.getWinStrategy().checkWin(currentPlayer, board)) {
                    System.out.println(">>> " + currentPlayer.getName() + " wins the game! <<<");
                    rank.add(currentPlayer);
                } else {
                    players.offer(currentPlayer);
                }
            } else {
                System.out.println(currentPlayer.getName() + " needs exactly " + (board.getTotalCells() - currentPosition) + " to win. Stays at " + currentPosition);
                players.offer(currentPlayer);
            }
            System.out.println();
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

    public static Game createGame(int n, int numPlayers, String difficultyLevel) {
        List<Player> playerList = new java.util.ArrayList<>();
        for (int i = 1; i <= numPlayers; i++) {
            playerList.add(new Player("Player " + i));
        }
        Board board = new Board(n);
        Dice dice = new Dice();
        Rule rule;
        if (difficultyLevel.equalsIgnoreCase("difficult") || difficultyLevel.equalsIgnoreCase("hard")) {
            rule = new com.snakeandladder.rules.DifficultRules();
        } else {
            rule = new com.snakeandladder.rules.EasyRules();
        }
        return new Game(board, dice, playerList, rule);
    }
}
