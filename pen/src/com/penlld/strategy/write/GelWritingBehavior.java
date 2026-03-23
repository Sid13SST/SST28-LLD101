package com.penlld.strategy.write;

import com.penlld.model.Ink;

public class GelWritingBehavior implements WritingBehavior {
    @Override
    public void write(String text, Ink ink) {
        System.out.println("Writing smoothly like a gel pen (" + ink.getColor() + "): " + text);
        ink.useInk();
    }
}
