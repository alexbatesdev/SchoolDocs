package edu.neumont;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        String userString = "Neumont College";
        userString = userString + userString;
        String s1 = "Hello";
        String s2 = "World";
        String s3 = s1 + " " + s2;
        s3 = s1.concat(" ".concat(s3));

        System.out.println(s3);
        System.out.println(s3.charAt(0));
        for (int i = 0; i < s3.length(); i++) {
            //System.out.print(s3.charAt(i) + "-");
            //System.out.print((char)(s3.charAt(i) + 1));
        }
        char[] chars = s3.toCharArray();
        chars[3] = 'X';
        System.out.println(chars);

        String s4 = Arrays.toString(chars);
        System.out.println("s4 = " + s4);
        //System.out.println(userString);

        int i = 215236;
        String s5 = String.format("|%010d|", i);
        System.out.println("s5 = " + s5);

        double v = 234.32151;
        String s6 = String.format("float: %10.3f %d %s", v, i, s1);
        System.out.println(s6);

        String s7 = "Cucumber";
        System.out.println("s7 = " + s7);
        System.out.println(s7.substring(2,5));

        String s8 = "Double";
        s8 = s8 + s8;
        System.out.println("s8 = " + s8);
        int l = s8.length();
        s8 = s8.substring(l/2);
        System.out.println("s8 = " + s8);
    }
}
