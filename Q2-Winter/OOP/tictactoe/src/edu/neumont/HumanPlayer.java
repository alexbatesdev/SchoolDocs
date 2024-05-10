package edu.neumont;

public class HumanPlayer extends Player{
    public HumanPlayer(String name, char symbol) {
        super(name, symbol);
    }

    @Override
    public void takeTurn(Board gameBoard) {
        boolean open = false;
        while (!open) {
            int column = Console.getInt(getName() + " Enter column: ", 1, gameBoard.getColLen()) - 1;
            int row = Console.getInt(getName() + " Enter row: ", 1, gameBoard.getRowLen()) - 1;

            if (gameBoard.getCel(column, row) == ' ') {
                open = true;
                gameBoard.placeSymbol(column, row, getSymbol());
            } else {
                Console.println("Invalid location, try again", Console.RED);
                gameBoard.print();
            }
        }
    }


}