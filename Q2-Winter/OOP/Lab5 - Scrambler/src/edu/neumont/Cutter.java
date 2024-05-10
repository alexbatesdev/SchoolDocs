package edu.neumont;

public class Cutter implements IStringEncryptable{
    @Override
    public String encrypt(String s) {
        String out = s.substring(s.length()/2) + s.substring(0,s.length()/2);
        return out;
    }

    @Override
    public String decrypt(String s) {
        String out = null;
        if (s.length() % 2 == 0) {
            out = s.substring(s.length()/2) + s.substring(0,s.length()/2);
        } else {
            out = s.substring((s.length()/2) + 1) + s.substring(0,(s.length()/2 + 1));
        }
        return out;
    }
}
