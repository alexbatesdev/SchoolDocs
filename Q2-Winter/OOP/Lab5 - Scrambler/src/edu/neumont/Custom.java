package edu.neumont;

import java.util.Random;

public class Custom implements IStringEncryptable{
    Random rng = new Random();
    int seed;
    public Custom() {
        seed = rng.nextInt(1000);
    }

    @Override
    public String encrypt(String s) {
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            chars[i] = (char)(chars[i] + seed);
        }
        return String.valueOf(chars);
    }

    @Override
    public String decrypt(String s) {
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            chars[i] = (char)(chars[i] - seed);
        }
        return String.valueOf(chars);
    }
}
