package com.snakeandladder;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Board {
    private int totalCells;
    private Map<Integer, Snake> snakes;
    private Map<Integer, Ladder> ladders;

    public Board(int n, String difficultyLevel) {
        this.totalCells = n * n;
        this.snakes = new HashMap<>();
        this.ladders = new HashMap<>();
        initializeBoard(n, difficultyLevel);
    }

    private void initializeBoard(int n, String difficultyLevel) {
        Random random = new Random();
        int count = 0;
        
        // Ensure "easy" and "hard" differ in jump metrics
        boolean isHard = "hard".equalsIgnoreCase(difficultyLevel);

        // Place n snakes
        while (count < n) {
            // head must be between 2 and totalCells - 1
            int head = random.nextInt(totalCells - 2) + 2; 
            int tail;
            
            if (isHard) {
                // Hard mode: snakes go way down (tail close to 1)
                tail = random.nextInt(head / 2) + 1;
            } else {
                // Easy mode: snakes don't go too far down
                tail = head - random.nextInt(head / 2 + 1) - 1;
                if (tail < 1) tail = 1;
            }
            
            if (!snakes.containsKey(head) && !ladders.containsKey(head) && !ladders.containsKey(tail)) {
                snakes.put(head, new Snake(head, tail));
                count++;
            }
        }

        // Place n ladders
        count = 0;
        int timeout = 0; // prevent infinite loops
        while (count < n && timeout < 2000) {
            timeout++;
            int start = random.nextInt(totalCells - 2) + 2; 
            int end;
            
            if (isHard) {
                // Hard mode: ladders are short
                end = start + random.nextInt((totalCells - start) / 2 + 1) + 1;
            } else {
                // Easy mode: ladders are long
                end = start + random.nextInt(totalCells - start) + 1;
            }
            if (end > totalCells) end = totalCells;
            
            // Check for valid position and no overlaps
            if (!ladders.containsKey(start) && !snakes.containsKey(start) && end != start) {
                boolean overlap = false;
                
                // Prevent cycles and overlaps with snakes
                for (Snake s : snakes.values()) {
                    if (s.getHead() == end || s.getTail() == start || s.getHead() == start) {
                        overlap = true;
                        break;
                    }
                }
                
                // Prevent cycles and overlaps with other ladders
                for (Ladder l : ladders.values()) {
                    if (l.getStart() == end || l.getEnd() == start) {
                         overlap = true;
                         break;
                    }
                }
                
                if (!overlap) {
                    ladders.put(start, new Ladder(start, end));
                    count++;
                }
            }
        }
        
        System.out.println("Board initialized with " + snakes.size() + " snakes and " + ladders.size() + " ladders.");
    }

    public int getTotalCells() { return totalCells; }
    
    public int getNextPosition(int position) {
        if (snakes.containsKey(position)) {
            System.out.println("Player bitten by snake at " + position + "! Going down to " + snakes.get(position).getTail());
            return snakes.get(position).getTail(); // Does not recursively check per cycle rules
        }
        if (ladders.containsKey(position)) {
            System.out.println("Player got ladder at " + position + "! Climbing up to " + ladders.get(position).getEnd());
            return ladders.get(position).getEnd(); // Does not recursively check per cycle rules
        }
        return position;
    }
}
