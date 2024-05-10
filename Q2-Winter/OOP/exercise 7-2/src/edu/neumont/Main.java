package edu.neumont;

public class Main {

    public static void main(String[] args) {
        int i1 = 10; // [10]
        int i2 = 20; // [20]

        System.out.println("i1 = " + i1);
        zero(i1);
        System.out.println("(zero) i1 = " + i1);

        System.out.println("i1 = " + i1 + "i2= " + i2);
        swap(i1, i2);
        System.out.println("(swap) i1 = " + i1 + " i2= " + i2);

        IntRef iref1 = new IntRef(10);
        IntRef iref2 = new IntRef(20);
        IntRef iref3 = iref1;

        System.out.println(iref1);
        System.out.println(iref2);
        System.out.println(iref3);

        System.out.println("iref1= " + iref1.value);
        zero(iref1);
        System.out.println("(zero)iref1= " + iref1.value);
        System.out.println("(zero)iref3= " + iref3.value);

        System.out.println("iref1 = " + iref1.value + " iref2= " + iref2.value);
        swap(iref1, iref2);
        System.out.println("(swap) iref1 = " + iref1.value + " iref2= " + iref2.value);
    }

    static void zero(int i) {
        i = 0;
    }

    static void swap(int i1, int i2) {
        int temp = i1;
        i1 = i2;
        i2 = temp;
    }

    static void zero(IntRef i) {
        i.value = 0;
    }

    static void swap(IntRef ir1, IntRef ir2) {
        IntRef temp = new IntRef(ir1);
        ir1.value = ir2.value;
        ir2.value = temp.value;
    }
}
