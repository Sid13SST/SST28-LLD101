package com.snakeandladder.strategies;

import com.snakeandladder.Dice;

public class AllowConsecutive implements IMakeMoveStrategy {
    @Override
    public int makeMove(Dice dice) {
        int totalRollValue = 0;
        int currentRoll;
        do {
            currentRoll = dice.roll();
            totalRollValue += currentRoll;
            if (currentRoll == 6) {
                System.out.print("Rolled a 6! Rolling again... ");
            }
        } while (currentRoll == 6);
        return totalRollValue;
    }
}
