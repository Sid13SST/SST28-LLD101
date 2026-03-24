package com.snakeandladder;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Board {
    private int totalCells;
    private Map<Integer, Jump> jumps;

    public Board(int n) {
        this.totalCells = n * n;
        this.jumps = new HashMap<>();
        initializeBoard(n);
    }

    private void initializeBoard(int n) {
        Random random = new Random();
        int count = 0;
        
        // Place n snakes
        while (count < n) {
            int head = random.nextInt(totalCells - 2) + 2; 
            int tail = head - random.nextInt(head / 2 + 1) - 1;
            if (tail < 1) tail = 1;
            
            if (!jumps.containsKey(head) && !jumps.containsKey(tail)) {
                jumps.put(head, new Snake(head, tail));
                count++;
            }
        }

        // Place n ladders
        count = 0;
        int timeout = 0; // prevent infinite loops
        while (count < n && timeout < 2000) {
            timeout++;
            int start = random.nextInt(totalCells - 2) + 2; 
            int end = start + random.nextInt(totalCells - start) + 1;
            if (end > totalCells) end = totalCells;
            
            if (!jumps.containsKey(start) && end != start) {
                boolean overlap = false;
                
                // Prevent cycles and overlaps
                for (Jump j : jumps.values()) {
                    if (j.getEndPosition() == start || j.getStartPosition() == end || j.getStartPosition() == start) {
                        overlap = true;
                        break;
                    }
                }
                
                if (!overlap) {
                    jumps.put(start, new Ladder(start, end));
                    count++;
                }
            }
        }
        
        int snakeCount = 0;
        int ladderCount = 0;
        for (Jump j : jumps.values()) {
            if (j instanceof Snake) snakeCount++;
            else if (j instanceof Ladder) ladderCount++;
        }
        System.out.println("Board initialized with " + snakeCount + " snakes and " + ladderCount + " ladders.");
    }

    public int getTotalCells() { return totalCells; }
    
    public int getNextPosition(int position) {
        if (jumps.containsKey(position)) {
            Jump jump = jumps.get(position);
            System.out.println(jump.getJumpMessage());
            return jump.getEndPosition();
        }
        return position;
    }
}
