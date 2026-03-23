package com.penlld.strategy.write;

import com.penlld.model.Ink;

public class FountainWritingBehavior implements WritingBehavior {
    @Override
    public void write(String text, Ink ink) {
        System.out.println("Writing elegantly like a fountain pen (" + ink.getColor() + "): " + text);
        ink.useInk();
        ink.useInk(); // Fountain pens might use more ink
    }
}
