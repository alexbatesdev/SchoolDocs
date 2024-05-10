package edu.neumont;

import java.util.ArrayList;

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
        while (!quit) {
            /* show player menu */
            //1. pvp
            //Name players, number of players?
            //2. pve
            //name player
            //3. eve
            //just watch
            //4. Quit
            /* end of player menu*/
            //add players
            //String name = Console.getString("enter player name: ");
            players.add(new AIPlayer("Alex", 'x'));
            //name = Console.getString("enter player name: ");
            players.add(new AIPlayer("Leo", 'o'));
            //reset game
            turn = 0;
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