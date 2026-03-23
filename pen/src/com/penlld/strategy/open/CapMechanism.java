package com.penlld.strategy.open;

public class CapMechanism implements OpeningMechanism {
    private boolean isCapped = true;

    @Override
    public void open() {
        if (isCapped) {
            System.out.println("Uncapping the pen...");
            isCapped = false;
        } else {
            System.out.println("Pen is already uncapped.");
        }
    }

    @Override
    public void close() {
        if (!isCapped) {
            System.out.println("Putting cap back on the pen...");
            isCapped = true;
        } else {
            System.out.println("Pen is already capped.");
        }
    }
}
