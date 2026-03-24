package com.snakeandladder;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("--- Snake and Ladder Game ---");
        System.out.print("Enter the size of the board (n for an nxn board): ");
        int n = scanner.nextInt();
        
        System.out.print("Enter the number of players (x): ");
        int numPlayers = scanner.nextInt();
        
        System.out.print("Enter difficulty level (easy/difficult): ");
        String difficulty = scanner.next();
        
        Game game = Game.createGame(n, numPlayers, difficulty);
        
        System.out.println("\nStarting the game...");
        game.play();
        
        scanner.close();
    }
}
