package com.snakeandladder;

import java.util.Random;

public class Dice {
    private int numberOfDice;
    private Random random;

    public Dice(int numberOfDice) {
        this.numberOfDice = numberOfDice;
        this.random = new Random();
    }

    public int roll() {
        int result = 0;
        for (int i = 0; i < numberOfDice; i++) {
            result += random.nextInt(6) + 1; // 1 to 6
        }
        return result;
    }
}
