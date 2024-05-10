package edu.neumont;

import java.util.Scanner;

public class Console {
    static Scanner scanner = new Scanner(System.in);

    static String getString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    static int getInt(String prompt) {
        int i = 0;
        boolean valid = false;
        while (!valid) {
            try {
                i = Integer.parseInt(getString(prompt));
                valid = true;
            } catch (Exception ex) {
                System.out.println("That's not a number dumbass");
            }
        }
        return i;
    }

    static int getInt(String prompt, int min, int max) {
        int i = 0;
        boolean valid = false;
        while (!valid) {
            try {
                i = Integer.parseInt(getString(prompt));
                valid = (i >= min && i <= max);
                if (!valid) {
                    System.out.println("Value is not between " + min + " and " + max + ". Please try again");
                }
            } catch (Exception ex) {
                System.out.println("That's not a number dumbass");
            }
        }
        return i;
    }

    static float getFloat(String prompt) {
        float i = 0;
        boolean valid = false;
        while (!valid) {
            try {
                i = Float.parseFloat(getString(prompt));
                valid = true;
            } catch (Exception ex) {
                System.out.println("That's not a number dumbass");
                System.out.println(ex);
            }
        }
        return i;
    }
}
