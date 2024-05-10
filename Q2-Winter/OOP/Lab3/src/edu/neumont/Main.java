package edu.neumont;

public class Main {

    public static void main(String[] args) {
        String name = Console.getString("Enter college name: ");
        College neumont = new College(name);
        neumont.run();


    }
}
