import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        boolean isRunning = true;
        while (isRunning) {
            int input = getInt("1) Add\n2) Subtract\n3) Multiply\n4) Divide\n5) Quit");
            switch (input) {
                case 1:
                    System.out.println("Sum: " + add(getDbl("First Addend: "), getDbl("Second Addend: ")));
                    break;
                case 2:
                    System.out.println("Difference: " + sub(getDbl("Minuend: "), getDbl("Subtrahend: ")));
                    break;
                case 3:
                    System.out.println("Product:" + mult(getDbl("First Factor: "), getDbl("Second Factor: ")));
                    break;
                case 4:
                    System.out.println("Quotient: " + div(getDbl("Dividend: "), getDbl("Divisor: ")));
                    break;
                case 5:
                    isRunning = false;
                    break;
                default:
                    System.out.println("PEBKAC Error");
                    break;
            }
        }
    }

    static Scanner scanner = new Scanner(System.in);

    static int getInt(String prompt) {
        System.out.println(prompt);
        int i = 0;
        boolean valid = true;
        do {
            try {
                i = Integer.parseInt(scanner.nextLine());
            } catch (Exception ex) {
                System.out.println("That's not a number dumbass");
                valid = false;
            }
        } while (!valid);
        return i;
    }

    static double getDbl(String prompt) {
        System.out.println(prompt);
        double i = 0;
        boolean valid = true;
        do {
            try {
                i = Double.parseDouble(scanner.nextLine());
            } catch (Exception ex) {
                System.out.println("That's not a number dumbass");
                valid = false;
            }
        } while (!valid);
        return i;
    }

    public static double add(double a, double b) {
        return a + b;
    }

    public static double sub(double a, double b) {
        return a - b;
    }

    public static double mult(double a, double b) {
        return a * b;
    }

    public static double div(double a, double b) {
        return a / b;
    }
}
