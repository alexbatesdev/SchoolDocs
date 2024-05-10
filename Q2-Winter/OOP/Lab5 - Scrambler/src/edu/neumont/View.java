package edu.neumont;

import java.util.ArrayList;

public class View {
    public void displayMenu() {
        System.out.println(Console.BAR);
        Console.setColor(Console.BLUE);
        System.out.println("1)enter string");
        System.out.println("2)view string");
        Console.setColor(Console.PURPLE);
        System.out.println("3)add encryption");
        System.out.println("4)display encryptions");
        System.out.println("5)clear encryptions");
        Console.setColor(Console.GREEN);
        System.out.println("6)encrypt string");
        System.out.println("7)decrypt string");
        Console.setColor(Console.RED);
        System.out.println("8)exit");
        Console.setColor(Console.RESET);
    }

    public void displayEncryptorsMenu() {
        Console.setColor(Console.RED);
        System.out.println("1) doubler");
        Console.setColor(Console.PURPLE);
        System.out.println("2) cutter");
        Console.setColor(Console.BLUE);
        System.out.println("3) vowel replacer");
        Console.setColor(Console.GREEN);
        System.out.println("4) custom");
        Console.setColor(Console.RESET);
    }

    public void displayEncryptors(ArrayList<IStringEncryptable> encryptors) {
        for (var encryptor: encryptors) {
            System.out.println(encryptor.getClass().getSimpleName());
        }
    }
}
