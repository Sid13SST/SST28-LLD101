package com.snakeandladder;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("--- Snake and Ladder Game ---");
        System.out.print("Enter the size of the board (n for an nxn board): ");
        int n = scanner.nextInt();
        
        System.out.print("Enter the number of players (x): ");
        int numPlayers = scanner.nextInt();
        
        System.out.print("Enter difficulty level (easy/hard): ");
        String difficulty = scanner.next();
        
        List<Player> players = new ArrayList<>();
        for (int i = 1; i <= numPlayers; i++) {
            players.add(new Player("Player " + i));
        }
        
        Board board = new Board(n, difficulty);
        Dice dice = new Dice(1); // 1 six-sided dice
        Game game = new Game(board, dice, players);
        
        System.out.println("\nStarting the game...");
        game.play();
        
        scanner.close();
    }
}
