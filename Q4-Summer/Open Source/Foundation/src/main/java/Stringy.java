public class Stringy {

    public static void DoIt() {
        String str1 = "abc";
        //same as
        char aryChar[] = {'a', 'b', 'c'};
        String str2 = new String(aryChar);

        String strOutput = String.format("Formatted output: %.1f, %d, %s", 4.06, 12, "Donut");
        System.out.println(strOutput);

        //compare strings
        String s1 = "HELLO";
        String s2 = "HELLO";
        String s3 = new String("HELLO");

        System.out.println(s1 == s2); //true
        System.out.println(s1 == s3); //false
        System.out.println(s1.equals(s2)); //true
        System.out.println(s1.equals(s3)); //true

        //s1 + s2 + s2;
        StringBuilder sb = new StringBuilder("Obi-Wan says: ");
        sb.append("Hello ");
        sb.append("There");
        System.out.println(sb);

        //String.toUpperCase("abc");
        //String.strip()  //trims white space
        //String.substring(9,14);
//        for (String strLine: aryLines) {
//            strLine = "";
//        }
    }
}
