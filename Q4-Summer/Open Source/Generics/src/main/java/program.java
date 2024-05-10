import java.lang.reflect.GenericArrayType;
import java.lang.reflect.GenericDeclaration;
import java.util.ArrayList;

public class program {
    public static void main(String[] args) {

//        testPrintArray();
//    InternalGenerics();
//    TestGeneric1Class();
    TestGeneric2Class();
    }

    public static void TestGeneric1Class() {
        Generic1<String> g1 = new Generic1<String>();
        g1.set("Hello");
        System.out.println(g1.get());

        Generic1<Integer> g2 = new Generic1<Integer>();
        g2.set(100);
        System.out.println(g2.get());
    }

    public static void TestGeneric2Class() {
        Generic2<String, Integer> g2 = new Generic2<String, Integer>("Hello", 100);
        g2.print();
    }

    public static void printArrayint(Integer[] aryInt) {
        for (Integer element: aryInt) {
            System.out.printf("%s", element);
        }
        System.out.println("");
    }

    public static void InternalGenerics() {
        ArrayList ary = new ArrayList();
        ary.add(1);
        ary.add("String");
        ary.add(true);

        for (Object obj: ary) {
            System.out.println(obj);
        }
    }

    // Uses generics instead
    public static <E> void printArray(E[] ary) {
        for (E element: ary) {
            System.out.printf("%s", element);
        }
        System.out.println("");
    }

    public static void testPrintArray() {
        Integer[] intArray = {1, 2, 3, 4, 5};
        Double[] doubleArray = {1.1, 2.2, 3.3, 4.4, 5.5};
        Character[] charArray = {'a', 'b', 'c', 'd', 'e'};

        //printArrayint(intArray);
        printArray(intArray);
        printArray(doubleArray);
        printArray(charArray);
    }
}
