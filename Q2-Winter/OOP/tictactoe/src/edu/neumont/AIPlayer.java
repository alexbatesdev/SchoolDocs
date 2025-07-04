package edu.neumont;

import java.util.Random;

public class AIPlayer extends Player{
    public AIPlayer(String name, char symbol) {
        super(name, symbol);
    }

    @Override
    public void takeTurn(Board gameBoard) {
        boolean open = false;
        while (!open) {
            int column = new Random().nextInt(gameBoard.getColLen());
            int row = new Random().nextInt(gameBoard.getRowLen());

            if (gameBoard.getCel(column, row) == ' ') {
                open = true;
                gameBoard.placeSymbol(column, row, getSymbol());
            }
        }
        System.out.println("AI " + getName() + " has taken a turn.");
    }


}