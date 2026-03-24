package com.snakeandladder.strategies;

import com.snakeandladder.Dice;

public class SkipOn3Consecutives implements IMakeMoveStrategy {
    @Override
    public int makeMove(Dice dice) {
        int consecutiveSixes = 0;
        int totalRollValue = 0;
        int currentRoll;

        do {
            currentRoll = dice.roll();
            if (currentRoll == 6) {
                consecutiveSixes++;
                if (consecutiveSixes == 3) {
                    System.out.println("Rolled three consecutive 6s! Turn skipped.");
                    return 0; // Skip turn entirely
                }
                totalRollValue += currentRoll;
                System.out.print("Rolled a 6! Rolling again... ");
            } else {
                totalRollValue += currentRoll;
            }
        } while (currentRoll == 6);
        
        return totalRollValue;
    }
}
