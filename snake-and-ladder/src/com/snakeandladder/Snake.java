package com.snakeandladder;

public class Snake implements Jump {
    private int head;
    private int tail;

    public Snake(int head, int tail) {
        this.head = head;
        this.tail = tail;
    }

    public int getHead() { return head; }
    public int getTail() { return tail; }

    @Override
    public int getStartPosition() {
        return head;
    }

    @Override
    public int getEndPosition() {
        return tail;
    }

    @Override
    public String getJumpMessage() {
        return "Player bitten by snake at " + head + "! Going down to " + tail;
    }
}
