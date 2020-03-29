package dsisejkovic.structures.queue;

import dsisejkovic.structures.utils.IllegalOperationException;

/**
 * Created by dsisejkovic on 10.7.2015..
 */
public class CircularQueue {
    private int[] array;
    private int size;
    private int front;
    private int rear;

    public CircularQueue(int size) {
        this.size = size;
        this.array = new int[size];
    }

    public void enqueue(int element) {
        if (isFull()) {
            throw new IllegalOperationException("Can not enqueue to full queue.");
        }

        array[rear] = element;
        rear = (rear + 1) % size;
    }

    public int dequeue() {
        if (isEmpty()) {
            throw new IllegalOperationException("Can not dequeue from empty queue.");
        }

        int element = array[front];
        front = (front + 1) % size;

        return element;
    }

    public boolean isFull() {
        return (rear + 1) % size == front;
    }

    public boolean isEmpty() {
        return rear == front;
    }


    public static void main(String[] args) {
        CircularQueue queue = new CircularQueue(5);
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);

        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());

    }

}