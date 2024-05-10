package edu.neumont;

public class Main {

    public static void main(String[] args) {
        byte age = 127; // [0,0,0,0,0,0,0,0]
        short date = 32767; //[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]
        int savings = 2_147_483_647; //[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]

        float f = 21.124154f;
        double d = 232.52362362362362;
        int i = 12356;
        byte b = 100;

        char c = 'A';

        System.out.println('A'+'l'+'e'+'x');
        System.out.println('K'+'a'+'i'+'s'+'h'+'a');

        d = f;
        f = (float)d;

        //i = b;
        b = (byte)i;
        System.out.println("b = " + b);
        System.out.println("i = " + i);
    }
}
