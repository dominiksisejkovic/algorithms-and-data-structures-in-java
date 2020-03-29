package dsisejkovic.structures.heap;

import dsisejkovic.structures.utils.IllegalOperationException;

import java.util.NoSuchElementException;

/**
 * Created by dsisejkovic on 13.7.2015..
 */
@SuppressWarnings("unchecked")
public class ArrayBasedHeap<T> {
    private ComparingType<T> comparingType;
    private int size;
    private T array[];
    private int capacity;

    public ArrayBasedHeap(int capacity, ComparingType<T> comparingType) {
        this.capacity = capacity;
        this.array = (T[]) new Object[capacity];
        this.comparingType = comparingType;
        // we dont use the index 0!
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    // in queue: enqueue
    public void insert(T element) {
        if (size() == capacity) {
            throw new IllegalOperationException("Can not add element to full heap.");
        }

        size++;
        array[size] = element;

        bubbleUp();
    }

    // in queue: dequeue
    public T remove() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        T element = array[1];
        array[1] = array[size()];
        array[size()] = null;
        size--;

        bubbleDown();

        return element;
    }


    private void bubbleUp() {
        int index = size();
        while (index > 1) {
            int parent = index / 2;
            if (!comparingType.compare(array[index], array[parent])) {
                break;
            }

            swap(index, parent);
            index = parent;
        }
    }

    private void bubbleDown() {
        int parent = 1;
        int child = parent * 2 + 1;

        while (child < size()) {
            // if child < size -1 is not true than this node has no children (it can not have only the right
            // child as a heap must be a complete binary tree!
            if (child < size - 1 && !comparingType.compare(array[child], array[child + 1])) {
                child++;
            }

            if (comparingType.compare(array[child], array[parent])) {
                swap(parent, child);
                parent = child;
                child = parent * 2 + 1;
            } else {
                break;
            }
        }
    }

    private void swap(int index1, int index2) {
        T tmp = array[index1];
        array[index1] = array[index2];
        array[index2] = tmp;
    }


    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        for (int i = 1; i <= size(); i++) {
            builder.append(array[i].toString()).append(" ");
        }

        builder.setLength(builder.length() - 1);
        return builder.toString();
    }

    public static void main(String[] args) {
        ArrayBasedHeap<Integer> heap = new ArrayBasedHeap<>(10, new MaxComparingType());

        int[] array = new int[]{2, 8, 6, 1, 10, 15, 3, 12, 11};

        for (int elem : array) {
            heap.insert(elem);
        }

        System.out.println(heap.remove());
        System.out.println(heap.remove());
        System.out.println(heap.remove());
        System.out.println(heap.remove());
        System.out.println(heap.remove());
        System.out.println(heap.remove());
        System.out.println(heap.remove());
        System.out.println(heap.remove());
        System.out.println(heap.remove());

        System.out.println("Heap size: " + heap.size());
    }
}
