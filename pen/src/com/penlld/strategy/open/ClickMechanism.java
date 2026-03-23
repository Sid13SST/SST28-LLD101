package com.penlld.strategy.open;

public class ClickMechanism implements OpeningMechanism {
    private boolean isClickIn = false;

    @Override
    public void open() {
        if (!isClickIn) {
            System.out.println("Clicking the pen to open...");
            isClickIn = true;
        } else {
            System.out.println("Pen is already ready to write.");
        }
    }

    @Override
    public void close() {
        if (isClickIn) {
            System.out.println("Clicking the pen to close...");
            isClickIn = false;
        } else {
            System.out.println("Pen is already closed.");
        }
    }
}
