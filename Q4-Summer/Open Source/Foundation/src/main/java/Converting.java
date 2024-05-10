import java.util.Scanner;

public class Converting {

    public static void Execute() {

        // Implicit conversion, a long can hold any value an int can
        // for built-in numeric types, an implicit conversion can be made when the value can fit without truncation
        int intNum = 2342342;
        long lngBigNum = intNum;
        Object c = 'C';

        String strType = c.getClass().toString();
        String strImpConv = String.format("Object type: %s", strType);
        System.out.println(strImpConv);

        var vc = 'C';
        // var - the actual type is resolved by the compiler during compile time.
        //var provides the same type safety as a normal typed variable
        // Object - the base built-in type for all variables in java, type checked at run time.

        // Explicit conversions
        // If a conversion cannot be made without risk of losing info the compiler requires that you perform an explicit conversion, or a "cast"
        // A cast is a way of telling the compiler that you intend to make the conversion
        //and are aware data loss may occur, or might fail at run time

        double d = 1234.5;
        int i;
        // cast double to int
        i = (int) d;
        System.out.println(i);

        //int intWasString = "5";
        String strNum = "5";
        int intResult;
        intResult = Integer.parseInt(strNum);
        String strMsg = Integer.TYPE.toString();
        String strOutput = String.format("Parse: %d, %s", intResult, strMsg);

        //Keyboard input
        Scanner sc = new Scanner(System.in);
        Integer intTyped = sc.nextInt();
        System.out.printf("got %d from keyboard", intTyped);
    }
}
