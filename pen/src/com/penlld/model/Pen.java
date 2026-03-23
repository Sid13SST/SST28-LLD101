package com.penlld.model;

import com.penlld.strategy.open.OpeningMechanism;
import com.penlld.strategy.write.WritingBehavior;

public class Pen {
    private WritingBehavior writingBehavior;
    private OpeningMechanism openingMechanism;
    private Ink ink;
    private boolean isOpen;

    public Pen(WritingBehavior writingBehavior, OpeningMechanism openingMechanism, Ink ink) {
        this.writingBehavior = writingBehavior;
        this.openingMechanism = openingMechanism;
        this.ink = ink;
        this.isOpen = false;
    }

    public void start() {
        openingMechanism.open();
        this.isOpen = true;
    }

    public void write(String text) {
        if (!isOpen) {
            System.out.println("Cannot write -> Pen is closed. Please start() the pen first.");
            return;
        }
        if (!ink.hasInk()) {
            System.out.println("Cannot write -> Pen is out of ink. Please refill() the pen.");
            return;
        }
        writingBehavior.write(text, ink);
    }

    public void close() {
        openingMechanism.close();
        this.isOpen = false;
    }

    public void refill() {
        ink.refill();
    }
}
