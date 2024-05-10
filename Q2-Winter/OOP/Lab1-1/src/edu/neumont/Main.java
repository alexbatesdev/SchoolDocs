package edu.neumont;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    private static int MYAGE = 19;

    public static void main(String[] args) {
        boolean running = true;
        ArrayList<Person> people = new ArrayList<Person>();


        while (running) {
            Person person = new Person();

            //Get person info
            String name = "";
            while (name.isBlank()) {
                name = getString("What is your name? ");
            }
            person.setName(name);
            person.setPrefix(getString("Give me a prefix to use (Sir, mam, duke, Mr. Ms., Etc.) "));
            person.setSuffix(getString("Gimme a suffix that I should use, something real classy like \"The Third\" would be spiffy. "));

            //display person info
            System.out.println(person.getCompleteName());
            //Get Age
            person.setAge(getInt("Ok, I know you aren't supposed to ask this to someone you just met, but how old are you? "));

            //Add person to people
            people.add(person);

            //Print out people
            for (Person p : people) {
                System.out.print(p);
                System.out.println(ageCompare(p.getAge()));
            }

            String response = getString("Do you want to add another person?");
            if (response.equalsIgnoreCase("no")) {
                System.out.println("Ok");
                running = false;
            }
        }
    }

    static String getString(String prompt) {
        System.out.println(prompt);
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

    public static String ageCompare(int age) {
        if (age > MYAGE) return " (older)";
        else if (age < MYAGE) return " (younger)";
        else return " (Same age!)";

    }
}
