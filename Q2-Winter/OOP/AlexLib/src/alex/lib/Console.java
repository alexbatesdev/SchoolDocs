package alex.lib;

import java.text.SimpleDateFormat;
import java.util.Scanner;

public class Console {
    public static final String RESET = "\u001B[0m";
    public static final String BAR = "----------------------";
    public static final String BLACK = "\u001B[30m";
    public static final String RED = "\u001b[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";
    public static final String WHITE = "\u001B[37m";
    public static final String BLACK_BACKGROUND = "\u001B[40m";
    public static final String RED_BACKGROUND = "\u001b[41m";
    public static final String GREEN_BACKGROUND = "\u001B[42m";
    public static final String YELLOW_BACKGROUND = "\u001B[43m";
    public static final String BLUE_BACKGROUND = "\u001B[44m";
    public static final String PURPLE_BACKGROUND = "\u001B[45m";
    public static final String CYAN_BACKGROUND = "\u001B[46m";
    public static final String WHITE_BACKGROUND = "\u001B[47m";

    static Scanner scanner = new Scanner(System.in);

    static public String getString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    static public int getInt(String prompt) {
        int i = 0;
        boolean valid = false;
        while (!valid) {
            try {
                i = Integer.parseInt(getString(prompt));
                valid = true;
            } catch (Exception ex) {
                setColor(RED_BACKGROUND);
                System.out.println("That's not a number dumbass");
                setColor(RESET);
            }
        }
        return i;
    }

    static public int getInt(String prompt, int min, int max) {
        int i = 0;
        boolean valid = false;
        while (!valid) {
            try {
                i = Integer.parseInt(getString(prompt));
                valid = (i >= min && i <= max);
                if (!valid) {
                    setColor(RED_BACKGROUND);
                    System.out.println("Value is not between " + min + " and " + max + ". Please try again");
                    setColor(RESET);
                }
            } catch (Exception ex) {
                setColor(RED_BACKGROUND);
                System.out.println("That's not a number dumbass");
                setColor(RESET);
            }
        }
        return i;
    }

    static public float getFloat(String prompt) {
        float i = 0;
        boolean valid = false;
        while (!valid) {
            try {
                i = Float.parseFloat(getString(prompt));
                valid = true;
            } catch (Exception ex) {
                setColor(RED_BACKGROUND);
                System.out.println("That's not a number dumbass");
                System.out.println(ex);
                setColor(RESET);
            }
        }
        return i;
    }

    public static String getDateString(String prompt, SimpleDateFormat dateFormat) {
        String string = null;
        boolean valid = false;
        while (!valid) {
            string = getString(prompt);
            try {
                dateFormat.parse(string);
                valid = true;
            } catch (Exception e) {
                System.out.println(e);
            }
        }

        return string;
    }

    public static boolean getBoolean(String prompt, String trueString, String falseString) {
        while (true) {
            String userInput = getString(prompt);
            if (userInput.equalsIgnoreCase(trueString)) {
                return true;
            } else if (userInput.equalsIgnoreCase(falseString)) {
                return false;
            } else {
                System.out.println("Invalid input, please input " + trueString + " or " + falseString);
            }
        }
    }

    public static int getMenuSelection(String[] options, boolean withQuit) {
        int i = 1;
        if (withQuit) System.out.println("0) Quit");
        for (String option : options) {
            System.out.println(i + ") " + option);
            i++;
        }
        return getInt("Enter selection: ", (withQuit) ? 0 : 1, options.length);
    }

    public static void setColor(String color) {
        System.out.print(color);
    }

    public static void setColor(String color, String backgroundColor) {
        System.out.print(color);
        System.out.print(backgroundColor);
    }

    public static void print(String string, String color) {
        setColor(color);
        System.out.print(string);
        setColor(RESET);
    }

    public static void println(String string, String color) {
        setColor(color);
        System.out.println(string);
        setColor(RESET);
    }

    public static void print(String string, String color, String backgroundColor) {
        setColor(color);
        setColor(backgroundColor);
        System.out.print(string);
        setColor(RESET);
    }

    public static void println(String string, String color, String backgroundColor) {
        setColor(color);
        setColor(backgroundColor);
        System.out.println(string);
        setColor(RESET);
    }
}