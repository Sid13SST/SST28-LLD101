package com.penlld.strategy.write;

import com.penlld.model.Ink;

public interface WritingBehavior {
    void write(String text, Ink ink);
}
