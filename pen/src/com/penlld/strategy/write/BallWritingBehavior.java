package com.penlld.strategy.write;

import com.penlld.model.Ink;

public class BallWritingBehavior implements WritingBehavior {
    @Override
    public void write(String text, Ink ink) {
        System.out.println("Writing like a ball pen (" + ink.getColor() + "): " + text);
        ink.useInk();
    }
}
