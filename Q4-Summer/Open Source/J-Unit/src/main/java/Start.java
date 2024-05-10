public class Start {
    public static void main(String[] args) {
        char cReturn;
        int i = 0;
        while (true) {
            i++;
            cReturn = Pi.GetDigofPi(i);
            System.out.println(cReturn);
        }
    }
}