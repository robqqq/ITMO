package gui;

import java.awt.*;
import java.util.Random;

public class RandomColor {
    private static Random rand = new Random();

    public static Color getRandomColor(){
        float r = rand.nextFloat();
        float g = rand.nextFloat();
        float b = rand.nextFloat();
        return new Color(r, g, b);
    }
}
