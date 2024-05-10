public class Pi {
    public static char GetDigofPi(int digit) {
        String s;
        final Double Pi = Math.PI; //3.14159265358979323846;

        s = Pi.toString();
        return s.charAt(digit);

    }
}