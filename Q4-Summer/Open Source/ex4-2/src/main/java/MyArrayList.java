import java.util.ArrayList;
public class MyArrayList<T> implements MyList<T> {
    private ArrayList<T> list = new ArrayList<T>();

    @Override
    public void add(T element) {
        list.add(element);
    }

    @Override
    public ArrayList<T> get(int index) {
        return this.list;
    }

    @Override
    public void remove(int index) {
        list.remove(index);
    }

    @Override
    public void remove(T val) {
        list.remove(val);
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public String toString() {
        return "MyArrayList{" +
                "list=" + list +
                '}';
    }
}
