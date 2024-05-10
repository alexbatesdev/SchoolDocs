import java.util.Arrays;

public class ArrayStuff {

    public static void DoIt() {
        int[] aryNums = {1, 5, 9};
        //SumIt(aryNums);
        //SumIt();
        SumIt(1, 5, 9);
    }

    public static void CumParrot() {
        int[] a1 = new int[] {1,2,3};
        int[] a2 = new int[] {1,2,3};

        //if (a1 == a2) { // Won't work, different memory locations
        //if (a1.equals(a2)) { // Won't work, not comparing contents
        //if (Arrays.deepEquals(a1, a2)) { // Does work, if they are objects, can be different sizes I think?
        if (Arrays.equals(a1, a2)) { // Does work, they have to be the same size
            System.out.println("They match");
        } else {
            System.out.println("They don't match");
        }

    }

    //public static void SumIt(int[] aryNumbers) {
    public static void SumIt(int ...aryNumbers) {
        int inTotal = 0;
        for (int i = 0; i < aryNumbers.length; i++) {
            inTotal += aryNumbers[i];
        }
        System.out.println(inTotal);
    }

}
