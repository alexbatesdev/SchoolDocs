package edu.neumont;

public class Board {
    private char[][] board;
    private int colLen;
    private int rowLen;
    public static final char OPEN_SYMBOL = ' ';

    public int getColLen() {
        return colLen;
    }

    public int getRowLen() {
        return rowLen;
    }

    public Board(int colLen, int rowLen) {
        this.colLen = colLen;
        this.rowLen = rowLen;

        board = new char[rowLen][colLen];
    }

    public void placeSymbol(int col, int row, char symbol) {
        board[row ][col] = symbol;
    }

    public int getOpenRow(int col) {
        int openRow = -1;

        for (int row = 0; row < rowLen; row++) {
            if (board[row][col] == OPEN_SYMBOL) {
                openRow = row;
            } else {
                break;
            }
        }

        return openRow;
    }

    public char getCel(int col, int row) {
        return board[row][col];
    }
    public boolean checkWin(char symbol, int length) {
        for (int row = 0; row < rowLen; row++) {
            for (int col = 0; col < colLen; col++) {
                //horizontal
                if (checkDirection(symbol, col, row, 1, 0, 4)) return true;
                //vertical
                if (checkDirection(symbol, col, row, 0, 1, 4)) return true;
                //Diag Down Right
                if (checkDirection(symbol, col, row, 1, 1, 4)) return true;
                //Diag Down Left
                if (checkDirection(symbol, col, row, -1, 1, 4)) return true;
            }
        }
        return false;
    }

    public boolean checkFull() {
        int count = 0;
        for (int row = 0; row < rowLen; row++) {
            for (int col = 0; col < colLen; col++) {
                if (board[row][col] == OPEN_SYMBOL) {
                    count++;
                }
            }
        }
        if (count == 0) {
            return true;
        } else {
            return false;
        }
    }

    private boolean checkDirection(char symbol, int colStart, int rowStart, int colDirection, int rowDirection, int length) {
        int count = 0;
        for (int row = rowStart, col = colStart; row >= 0 && row < rowLen && col >= 0 && col < colLen; row += rowDirection, col += colDirection) {
            if (board[row][col] == symbol) count++;
            else count = 0;

            if (count == length) return true;
        }
        return false;
    }

    public void reset() {
        for (int row = 0; row < rowLen; row++) {
            for (int col = 0; col < colLen; col++) {
                board[row][col] = OPEN_SYMBOL;
            }
        }
    }

    public void print() {
        for (int row = 0; row < rowLen; row++) {
            for (int col = 0; col < colLen; col++) {
                switch (board[row][col]) {
                    case OPEN_SYMBOL:
                        Console.print("  ", Console.WHITE_BACKGROUND);
                        break;
                    case 'x':
                        Console.print("  ", Console.RED_BACKGROUND);
                        break;
                    case 'o':
                        Console.print("  ", Console.YELLOW_BACKGROUND);
                        break;
                }
            }
            System.out.println();
        }
        System.out.println();
    }
}