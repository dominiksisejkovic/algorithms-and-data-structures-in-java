package dsisejkovic.structures.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Created by dsisejkovic on 9.7.2015..
 */

public class DynamicStack<T> {
    private int position;
    private List<T> arrayList;

    public DynamicStack() {
        this(10);
    }

    public DynamicStack(int size) {
        this.arrayList = new ArrayList(size);
        this.position = -1;
    }

    public T pop() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        T element = arrayList.get(position);
        arrayList.set(position, null);
        position--;

        return element;
    }

    public T peek() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        T element = arrayList.get(position);
        arrayList.set(position, null);
        position--;

        return element;
    }

    public void push(T element) {
        arrayList.set(position, element);
        position++;
    }

    private boolean isEmpty() {
        return position == -1;
    }
}