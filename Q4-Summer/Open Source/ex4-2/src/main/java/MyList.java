import java.util.ArrayList;

public interface MyList<T> {

    public void add(T element);

    ArrayList<T> get(int index);


    public void remove(int index);
    public void remove(T val);

    public int size();
}
