package edu.neumont;

import java.util.Random;

public class RNG {
    static Random random = new Random();

    public static int getInt(int min, int max) {
        return random.nextInt(min, max);
    }

    public static double getDouble(double min, double max) {
        return random.nextDouble(min, max);
    }
}
