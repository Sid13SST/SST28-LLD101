package com.penlld.model;

public class Ink {
    private String color;
    private int level;

    public Ink(String color) {
        this.color = color;
        this.level = 100; // 100% initial level
    }

    public String getColor() {
        return color;
    }

    public boolean hasInk() {
        return level > 0;
    }

    public void useInk() {
        if (level > 0) {
            level -= 10;
        }
    }

    public void refill() {
        this.level = 100;
        System.out.println("Ink refilled with " + color + " color.");
    }
}
