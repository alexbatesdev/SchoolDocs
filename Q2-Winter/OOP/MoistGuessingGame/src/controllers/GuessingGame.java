package controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

public class GuessingGame {
    private BufferedReader bread = new BufferedReader(new InputStreamReader(System.in));
    public void play(){
        System.out.print("Select difficulty: Easy, Medium, Hard: ");
        try {
            //Gets difficulty
            String difficultyChoice = bread.readLine();
            int maxValue;
            if (difficultyChoice.equalsIgnoreCase("easy")) {
                maxValue = 10;
            } else if (difficultyChoice.equalsIgnoreCase("medium")) {
                maxValue = 50;
            } else if (difficultyChoice.equalsIgnoreCase("hard")) {
                maxValue = 100;
            } else {
                System.out.println("Oops! That's not a difficulty! It's ok, I'll just set it to Very Hard for you! :D");
                maxValue = 1000;
            }
            System.out.println("Guess a number between 1 and " + maxValue + ": ");
            //Sets random number
            int answer;
            Random rng = new Random();
            answer = rng.nextInt(maxValue) + 1;
            //Handles the users inputs/guesses
            String thing;
            int guess;
            boolean win = false;
            for (int i = 0; i < 5; i++) {
                if(win == false) {
                    try {
                        thing = bread.readLine();
                        guess = Integer.parseInt(thing);
                        if (guess > answer) {
                            System.out.println("Too high. Try again.\n");
                        } else if (guess < answer) {
                            System.out.println("Too low. Try again.\n");
                        } else if (guess == answer) {
                            System.out.println("You win!");
                            win = true;
                        }
                    } catch(Exception ioe){
                        System.out.println("oops.");
                    }
                }
            }
            if(win == false){
                System.out.println("You lose. The answer is " + answer);
            }
        } catch(Exception ioe){
            System.out.println(ioe.getMessage());
        }
    }
}