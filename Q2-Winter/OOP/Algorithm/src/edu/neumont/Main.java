package edu.neumont;


import java.util.Random;

public class Main {

    public static void main(String[] args) {
        System.out.println(Algorithms.sqr(8));
        System.out.println(Algorithms.cube(4));
        System.out.println(Algorithms.min(3,6));
        System.out.println(Algorithms.max(3,6));
        System.out.println(Algorithms.abs(-12));

        //arrays
        int[] numbers = new int[100];

        for (int i = 0; i < Algorithms.size(numbers); i++) {
            numbers[i] = new Random().nextInt(1, 11);

            System.out.print(numbers[i] + " ");
        }
        Algorithms.print(numbers);


        int[] tally = new int[10];
        for (var number : numbers) {
            tally[number-1]++;
            System.out.print(String.format("%2d - ", number));
            Algorithms.print(tally);

        }

        System.out.println();
        System.out.println("contains: " + Algorithms.contains(numbers, 67));
        System.out.println("Size: " + Algorithms.size(numbers));
        System.out.println("Index of 67: " + Algorithms.indexOf(numbers, 67));
        System.out.println("Sum: " + Algorithms.sum(numbers));
        System.out.println("Average: " + String.format("%.2f",Algorithms.avg(numbers)));

        System.out.println("Max: " + Algorithms.max(numbers));
        System.out.println("Min: " + Algorithms.min(numbers));
        System.out.println("Mode: " + (Algorithms.indexOf(tally,Algorithms.max(tally)) + 1));

        int[] ints = {34, 54, 1, 10, 78, 34};
        Algorithms.print(ints);
        Algorithms.reverse(ints);
        Algorithms.print(ints);

        System.out.println("count: " + Algorithms.count(ints, 34));

        int i1 = 10;
        int i2 = 15;
        int temp;
        System.out.println("i1: " + i1);
        System.out.println("i2: " + i2);
        temp = i2;
        i2 = i1;
        i1 = temp;
        System.out.println("i1: " + i1);
        System.out.println("i2: " + i2);

    }
}
