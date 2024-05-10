package prog;

import controllers.GuessingGame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) {
        GuessingGame game = new GuessingGame();
        game.play();
    }
}
