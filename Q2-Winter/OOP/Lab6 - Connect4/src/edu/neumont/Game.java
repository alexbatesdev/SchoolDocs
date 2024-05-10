package edu.neumont;

import java.util.ArrayList;
import java.util.Random;

public class Game {
    private Board gameBoard;
    private int winLen;
    private ArrayList<Player> players = new ArrayList<>();
    private int turn;


    public Game(int columns, int rows, int winLen) {
        gameBoard = new Board(columns, rows);
        this.winLen = winLen;
    }

    public void run() {
        boolean quit = false;
        quit_break:
        while (!quit) {
            /* show player menu */
            char[] symbols = {'x','o'};
            int random = new Random().nextInt(2);
            char player1S;
            char player2S;
            String prompt;
            if (random == 0) {
                player1S = 'x';
                player2S = 'o';
            } else {
                player1S = 'o';
                player2S = 'x';
            }

            View.mainMenu();
            int selection = Console.getInt("Selection: ", 1, 4);
            String name;
            switch (selection) {
                case 1://pvp
                    name = Console.getString("Enter player name: ");
                    players.add(new HumanPlayer(name, player1S));
                    name = Console.getString("Enter player name: ");
                    players.add(new HumanPlayer(name, player2S));
                    break;
                case 2://pve
                    name = Console.getString("Enter player name: ");
                    players.add(new HumanPlayer(name, player1S));
                    name = Console.getString("Enter AI name: ");
                    players.add(new AIPlayer(name, player2S));
                    break;
                case 3://eve
                    name = Console.getString("Enter AI name: ");
                    players.add(new AIPlayer(name, player1S));
                    name = Console.getString("Enter AI name: ");
                    players.add(new AIPlayer(name, player2S));
                    break;
                case 4://quit
                    quit = true;
                    break quit_break;
            }
            //reset game
            turn = 0;
            if (players.get(0).getSymbol() == 'o') {
                Console.println(players.get(0).getName() + " is yellow.", Console.YELLOW);
            } else {
                turn++;
                Console.println(players.get(1).getName() + " is yellow.", Console.YELLOW);
            }
            gameBoard.reset();
            //Start game
            Player winner = null;
            boolean running = true;
            while (running && !gameBoard.checkFull()) {
                Player player = players.get(turn);

                gameBoard.print();
                player.takeTurn(gameBoard);
                if (gameBoard.checkWin(player.getSymbol(), winLen)) {
                    running = false;
                    winner = player;
                }

                if (++turn == players.size()) turn = 0;
            }
            //display winner or tie
            gameBoard.print();
            if (winner != null) {
                Console.println(winner.getName() + " is the winner!", Console.GREEN);
            } else {
                Console.println("It was a tie :/", Console.WHITE);
            }

            String s = Console.getString("play again? (y/n)? ");
            if (s.equalsIgnoreCase("n")) quit = true;
        }
    }
}