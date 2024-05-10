package edu.neumont;

import java.util.ArrayList;

public abstract class Player {
    private String name;
    private char symbol;


    public String getName() {
        return name;
    }

    public char getSymbol() {
        return symbol;
    }

    public Player(String name, char symbol) {
        this.name = name;
        this.symbol = symbol;
    }

    public abstract void takeTurn(Board gameBoard);
}