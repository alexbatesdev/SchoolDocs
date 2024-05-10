import java.util.ArrayList;

public class main {
    public static void main(String[] args) {

        // aBlockofCode = () -> System.out.println("Do It");



        // a lambda function
//        Runnable aBlockofCode = () -> {
//            System.out.println("Do It");
//        };

        ArrayList<Integer> aryNums = new ArrayList<>();
        aryNums.add(1);
        aryNums.add(2);
        aryNums.add(3);
        aryNums.forEach((num) -> System.out.println(num));

        // set a lambda express return variable rqual to a function
        // function type is an interface
        iSayHi speak = (String name) -> {
            return "Hello " + name;
        };
        System.out.println(speak.sayHi("John"));

        // send a lambda expression dunction into variables, send to another function
        iSayHi qMark = (s) -> s + "?";
        iSayHi xMark = (s) -> s + "!";
        printIt("Hello", qMark);
        printIt("Hello", xMark);

    }

    public static void printIt(String str, iSayHi talk) {
        System.out.println(talk.sayHi(str));
    }
}
