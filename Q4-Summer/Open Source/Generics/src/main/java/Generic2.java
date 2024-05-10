public class Generic2 <T, U> {
    //Electric boogaloo
    T obj1;
    U obj2;

    public Generic2(T obj1, U obj2) {
        this.obj1 = obj1;
        this.obj2 = obj2;
    }

    public void print() {
        System.out.println(obj1 + " " + obj2);
    }

    public T getObj1() {
        return obj1;
    }

    public U getObj2() {
        return obj2;
    }

    public void setObj1(T obj1) {
        this.obj1 = obj1;
    }

    public void setObj2(U obj2) {
        this.obj2 = obj2;
    }
}
