package dsisejkovic.structures.stack;

import dsisejkovic.structures.utils.IllegalOperationException;
import java.util.NoSuchElementException;

/**
 * Created by dsisejkovic on 9.7.2015..
 */

public class StaticStack<E> {
    private int size;
    private Object[] array;
    private int position;

    public StaticStack() {
        this(10);
    }

    public StaticStack(int size) {
        this.size = size;
        this.array = new Object[size];
    }

    public E pop() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        E element;
        element = (E) array[position];
        array[position] = null;
        position--;

        return element;
    }

    public E peek() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        return (E) array[position];
    }

    public void push(E element) {
        if (position == size - 1) {
            throw new IllegalOperationException("Stack is full.");
        }

        position++;
        array[position] = element;
    }

    private boolean isEmpty() {
        return position == size - 1;
    }

}