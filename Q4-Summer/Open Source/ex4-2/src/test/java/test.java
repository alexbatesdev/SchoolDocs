import org.junit.jupiter.api.Test;

public class test {
    // test the MyArrayList class

    @Test
    void testMyArrayList() {
        MyArrayList list = new MyArrayList();
        list.add(2);
        list.add("hello");
        list.add(true);
        list.add(false);
        list.add(new Object());
        list.add("world");

        list.remove(2);
        list.remove("world");

        list.get(0);
        list.get(2);

        System.out.println(list.size());
        System.out.println(list);
    }

    //Make individual tests for every function of the MyArrayList class
    @Test
    void testMyArrayListAdd() {
        MyArrayList list = new MyArrayList();
        list.add(2);
        list.add("hello");
        list.add(true);
        list.add(false);
        list.add(new Object());
        list.add("world");
        System.out.println(list);
    }

    @Test
    void testMyArrayListRemove() {
        MyArrayList list = new MyArrayList();
        list.add(2);
        list.add("hello");
        list.add(true);
        list.add(false);
        list.add(new Object());
        list.add("world");
        list.remove(2);
        list.remove("world");
        System.out.println(list);
    }

    @Test
    void testMyArrayListGet() {
        MyArrayList list = new MyArrayList();
        list.add(2);
        list.add("hello");
        list.add(true);
        list.add(false);
        list.add(new Object());
        list.add("world");
        System.out.println(list.get(0) + " " + list.get(2));
    }

    @Test
    void testMyArrayListSize() {
        MyArrayList list = new MyArrayList();
        list.add(2);
        list.add("hello");
        list.add(true);
        list.add(false);
        list.add(new Object());
        list.add("world");
        System.out.println(list.size());
    }

    @Test
    void testMyArrayListToString() {
        MyArrayList list = new MyArrayList();
        list.add(2);
        list.add("hello");
        list.add(true);
        list.add(false);
        list.add(new Object());
        list.add("world");
        System.out.println(list);
    }
}
