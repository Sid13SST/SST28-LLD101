package com.snakeandladder;

public class Ladder implements Jump {
    private int start;
    private int end;

    public Ladder(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public int getStart() { return start; }
    public int getEnd() { return end; }

    @Override
    public int getStartPosition() {
        return start;
    }

    @Override
    public int getEndPosition() {
        return end;
    }

    @Override
    public String getJumpMessage() {
        return "Player got ladder at " + start + "! Climbing up to " + end;
    }
}
