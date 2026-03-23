package com.penlld;

import com.penlld.model.Ink;
import com.penlld.model.Pen;
import com.penlld.strategy.open.CapMechanism;
import com.penlld.strategy.open.ClickMechanism;
import com.penlld.strategy.write.BallWritingBehavior;
import com.penlld.strategy.write.FountainWritingBehavior;
import com.penlld.strategy.write.GelWritingBehavior;

public class Main {
    public static void main(String[] args) {
        System.out.println("--- Pen LLD Demonstration ---");

        // 1. Create a Blue Ball Pen with Click Mechanism
        System.out.println("\n[1] Using a Blue Ball Pen (Click-based):");
        Pen blueBallPen = new Pen(
            new BallWritingBehavior(),
            new ClickMechanism(),
            new Ink("Blue")
        );
        blueBallPen.start();
        blueBallPen.write("Hello, World!");
        blueBallPen.close();

        // 2. Create a Black Fountain Pen with Cap Mechanism
        System.out.println("\n[2] Using a Black Fountain Pen (Cap-based):");
        Pen blackFountainPen = new Pen(
            new FountainWritingBehavior(),
            new CapMechanism(),
            new Ink("Black")
        );
        blackFountainPen.start();
        blackFountainPen.write("Writing a beautiful letter.");
        blackFountainPen.write("Signature.");
        blackFountainPen.close();

        // 3. Create a Red Gel Pen with Cap Mechanism
        System.out.println("\n[3] Using a Red Gel Pen (Cap-based):");
        Pen redGelPen = new Pen(
            new GelWritingBehavior(),
            new CapMechanism(),
            new Ink("Red")
        );
        redGelPen.start();
        
        // Simulating running out of ink
        System.out.println("Writing long essays...");
        for (int i = 0; i < 10; i++) {
            redGelPen.write("Writing paragraph " + (i + 1));
        }
        
        // This won't write
        redGelPen.write("Trying to write more...");
        
        // Refill ink
        System.out.println("\nRefilling the pen...");
        redGelPen.refill();
        redGelPen.write("Writing after refill!");
        redGelPen.close();
    }
}
