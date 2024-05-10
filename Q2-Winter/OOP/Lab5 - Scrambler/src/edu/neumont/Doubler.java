package edu.neumont;

public class Doubler implements IStringEncryptable{
    @Override
    public String encrypt(String s) {
        return s + s;
    }

    @Override
    public String decrypt(String s) {
        return s.substring(s.length()/2);
    }
}