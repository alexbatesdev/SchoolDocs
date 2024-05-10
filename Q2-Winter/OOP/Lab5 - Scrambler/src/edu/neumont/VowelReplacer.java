package edu.neumont;

public class VowelReplacer implements IStringEncryptable{
    private static final char[] vowels = {'a', 'e', 'i', 'o', 'u'};
    @Override
    public String encrypt(String s) {
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (isVowel(chars[i])) {
                if(Character.isUpperCase(chars[i])) {
                    chars[i] = Character.toUpperCase(getNextVowel(chars[i]));
                } else {
                    chars[i] = getNextVowel(chars[i]);
                }
            }
        }
        return String.valueOf(chars);
    }

    @Override
    public String decrypt(String s) {
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (isVowel(chars[i])) {
                chars[i] = getPrevVowel(chars[i]);
            }
        }
        return String.valueOf(chars);
    }

    private boolean isVowel(char c) {
        for (int i = 0; i < vowels.length; i++) {
            if (Character.toLowerCase(c) == vowels[i]) return true;
        }
        return false;
    }

    private int getVowelIndex(char c) {
        for (int i = 0; i < vowels.length; i++) {
            if (Character.toLowerCase(c) == vowels[i]) return i;
        }
        return -1;
    }

    char getNextVowel(char vowel) {
        int index = getVowelIndex(vowel);
        index += 1;
        if (index == vowels.length) index = 0;
        return vowels[index];
    }

    char getPrevVowel(char vowel) {
        int index = getVowelIndex(vowel);
        index -= 1;
        if (index < 0) index = vowels.length - 1;
        return vowels[index];
    }
}