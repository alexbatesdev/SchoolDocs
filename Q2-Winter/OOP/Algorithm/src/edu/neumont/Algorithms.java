package edu.neumont;

import com.sun.source.tree.LiteralTree;

public class Algorithms {
    public static int sqr(int a) {
        return a * a;
    }

    public static int cube(int a) {
        return sqr(a) * a;
    }

    public static int min(int a, int b) {
        return (a <= b) ? a : b;
    }

    public static int max(int a, int b) {
        return (a >= b) ? a : b;
    }

    public static int abs(int a) {
        return (a < 0) ? -a : a;
    }

    public static boolean contains(int[] array, int value) {
        for (var element: array) {
            if (element == value) return true;
        }
        return false;
    }

    public static int size(int[] array) {
        int i = 0;
        for (var item: array) i++;
        return i;
    }

    public static int indexOf(int[] array, int value) {
        for (int i = 0; i < size(array); i++) {
            if (array[i] == value) return i;
        }
        return -1;
    }

    public static int sum(int[] array) {
        int sum = 0;
        for (var num: array) {
            sum += num;
        }
        return sum;
    }
    public static double avg(int[] array) {
        return (double)sum(array)/size(array);
    }

    public static int max(int[] array) {
        int max = array[0];
        for (var num : array) {
            if (num > max) max = num;
        }
        return max;
    }

    public static int min(int[] array) {
        int min = array[0];
        for (var num : array) {
            if (num < min) min = num;
        }
        return min;
    }

    public static void print(int[] array) {
        for (int i : array) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    public static void reverse(int[] array) {
        int temp;
        for (int i = 0; i < (size(array)/2); i++) {
            temp = array[i];
            array[i] = array[(size(array) - i) - 1];
            array[(size(array) - i) - 1] = temp;
        }
    }

    public static int count(int[] array, int value) {
        int count = 0;
        for (int i : array) {
            if (i == value) count++;
        }
        return count;
    }
}
