package edu.neumont;

public class Main {

    public static void main(String[] args) {
        bones();
    }

    static void bones() {
        int i = 5;
        System.out.println("bones");
        muscle();
    }

    static void muscle() {
        int i = 8;
        System.out.println("muscle");
        skin();
    }

    static void skin() {
        System.out.println("skin");
    }
}
