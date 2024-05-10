package edu.neumont;

import java.io.*;
import java.util.Scanner;

public class Main {
    //Create a console application.
    //Ask the user to input a string.
    //Modify the string by appending your signature.
    //Write that string out to a file.
    //Read that file back in and extract the string.
    //Display your modified string in the console.

    public static void main(String[] args) {

        File file = null;
        FileInputStream input = null;
        FileOutputStream output = null;
        file = new File("src/re-src/output.txt");

        try {
            input = new FileInputStream(file);
            output = new FileOutputStream(file);

            // print text file to console
            Scanner scanner = new Scanner(input);
            //IOStream.writeToConsole(input);
            // output text file to file
            //IOStream.writeToStream(input,output);

            String string = Console.getString("Please input a string: ");
            String sig = Console.getString("Please input your name: ");

            IOStream.writeToStream(string + " - " + sig, output);

            String str = IOStream.readToString(input);
            System.out.println(str);
        } catch (Exception e) {
            System.out.println("Oops it didn't work because -> " + e );
        } finally {
            try {
                if (input != null) input.close();
                if (output != null) output.close();
            } catch (Exception e) {
                System.out.println("Oops it didn't work because -> " + e );
            }
        }

    }
}
