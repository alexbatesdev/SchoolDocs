package edu.neumont;

public class Main {

    public static void main(String[] args) {
        int[][] array = new int[9][9];

        System.out.println("Sudoki-doki literature club :)");
        array[0][0] = 5;
        array[0][1] = 3;
        array[0][4] = 7;
        array[1][0] = 6;
        array[1][3] = 1;
        array[1][4] = 9;
        array[1][5] = 5;
        array[2][1] = 9;
        array[2][2] = 8;
        array[2][7] = 6;
        array[3][0] = 8;
        array[3][4] = 6;
        array[3][8] = 3;
        array[4][0] = 4;
        array[4][3] = 8;
        array[4][5] = 3;
        array[4][8] = 1;
        array[5][0] = 7;
        array[5][4] = 2;
        array[5][8] = 6;
        array[6][1] = 6;
        array[6][6] = 2;
        array[6][7] = 8;
        array[7][3] = 4;
        array[7][4] = 1;
        array[7][5] = 9;
        array[7][8] = 5;
        array[8][4] = 8;
        array[8][7] = 7;
        array[8][8] = 9;

        print(array);
    }

    static void print(int[][] array) {
        //Goes through each row
        for (int r = 0; r < array.length; r++) {
            //goes through each column of current row
            for (int c = 0; c < array[r].length; c++) {
                System.out.print(array[r][c] + " ");
            }
            System.out.println();
        }
    }
}
