package edu.neumont;

public class Board {
    private char[][] board;
    private int colLen;
    private int rowLen;

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

    public char getCel(int col, int row) {
        return board[row][col];
    }
    public boolean checkWin(char symbol, int length) {
        //check horizontal
        for (int row = 0; row < rowLen; row++) {
            int count = 0;
            for (int col = 0; col < colLen; col++) {
                if(board[row][col] == symbol) {
                    count++;
                } else {
                    count = 0;
                }
                if (count == length) return true;
            }
        }
        //check vertical
        for (int col = 0; col < colLen; col++) {
            int count = 0;
            for (int row = 0; row < rowLen; row++) {
                if(board[row][col] == symbol) {
                    count++;
                } else {
                    count = 0;
                }
                if (count == length) return true;
            }
        }
        //check diagonal

        return false;
    }

    public boolean checkFull() {
        int count = 0;
        for (int row = 0; row < rowLen; row++) {
            for (int col = 0; col < colLen; col++) {
                if (board[row][col] != ' ') {
                    count++;
                }
            }
        }
        if (count == (rowLen * colLen)) {
            return true;
        } else {
            return false;
        }
    }

    public void reset() {
        for (int row = 0; row < rowLen; row++) {
            for (int col = 0; col < colLen; col++) {
                board[row][col] = ' ';
            }
        }
    }

    public void print() {
        for (int row = 0; row < rowLen; row++) {
            for (int col = 0; col < colLen; col++) {
                switch (board[row][col]) {
                    case ' ':
                        Console.print("    ", Console.WHITE_BACKGROUND);
                        break;
                    case 'x':
                        Console.print("    ", Console.RED_BACKGROUND);
                        break;
                    case 'o':
                        Console.print("    ", Console.BLUE_BACKGROUND);
                        break;
                }
            }
            System.out.println();
        }
        System.out.println();
    }
}