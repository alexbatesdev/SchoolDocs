package edu.neumont;

public class Main {
    String board[][] = new String[6][7];
    String color = "red";
    boolean gameRunning = true;
    boolean placed;

    public static void main(String[] args) {
        // write your code here
    }
    public void fall(int column, String color) {
        //Pieces fall and stop on top of lowest piece
        if (board[0][column] == null) {
            boolean falling = true;
            for (int i = 1; i < board.length; i++) {
                //System.out.println(board[i][column] + i + column);
                if ((board[i][column] == "red" || board[i][column] == "blue") && falling) {
                    board[i - 1][column] = color;
                    falling = false;
                    placed = true;
                    System.out.println("Placed at " + column + (i-1));
                } else if (i == board.length - 1 && falling) {
                    board[i][column] = color;
                    falling = false;
                    placed = true;
                    System.out.println("Placed at " + column + i);
                }
            }
        }
    }
    public void check(String color) {
        int chain = 0;
        top_break:
        for (int y = 0; y < board.length; y++) { //Goes through each row
            for (int x = 0; x < board[y].length; x++) { //goes through each column of current row (Goes from top left ro bottom right)
                //System.out.println(board[y][x]);
                if (board[y][x] == color) { //if finds a token matching the selected color
                    System.out.println("hooked at " + x + y);
                    //y is collumn, x is row
                    //Check surrounding grid slots
                    chain = 1;
                    System.out.println("HookChain up!" + chain); //chain is how many matching tokens are found nearby in a straight line
                    if (x < 4) {
                        if (board[y][x + 1] == color) {//checks immediately to the right of hooked token
                            chain++;
                            System.out.println("SecondChain up!" + chain);
                            for (int l = 2; l < 4; l++) { //Checks the 2 slots after
                                if (board[y][x + l] == color) {
                                    chain++;
                                    System.out.println("ThirdFourthChain up!" + chain);
                                    if (chain == 4) {
                                        System.out.println("WIN");
                                        gameRunning = false;
                                        break top_break;
                                    }
                                }
                            }
                        }
                    }
                    chain = 1;
                    if (y < 3) { //checks straight down
                        if (board[y+1][x] == color) {
                            chain++;
                            for (int l = 2; l < 4; l++) { //Checks the 2 slots after
                                if (board[y+l][x] == color) {
                                    chain++;
                                    System.out.println("ThirdFourthChain up!" + chain);
                                    if (chain == 4) {
                                        System.out.println("WIN");
                                        gameRunning = false;
                                        break top_break;
                                    }
                                }
                            }
                        }
                    }
                    chain = 1;
                    if (x < 4 && y < 3) {
                        if (board[y+1][x+1] == color) {
                            chain++;
                            for (int l = 2; l < 4; l++) { //Checks the 2 slots after
                                if (board[y+l][x+l] == color) {
                                    chain++;
                                    System.out.println("ThirdFourthChain up!" + chain);
                                    if (chain == 4) {
                                        System.out.println("WIN");
                                        gameRunning = false;
                                        break top_break;
                                    }
                                }
                            }
                        }
                    }
                    chain = 1;
                    if (x > 2 && y < 3) {
                        if (board[y+1][x-1] == color) {
                            chain++;
                            for (int l = 2; l < 4; l++) { //Checks the 2 slots after
                                if (board[y+l][x-l] == color) {
                                    chain++;
                                    System.out.println("ThirdFourthChain up!" + chain);
                                    if (chain == 4) {
                                        System.out.println("WIN");
                                        gameRunning = false;
                                        break top_break;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
