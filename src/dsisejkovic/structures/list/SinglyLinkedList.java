package dsisejkovic.structures.list;

import dsisejkovic.structures.utils.IllegalOperationException;

import java.util.ArrayList;

/**
 * Created by dsisejkovic on 8.7.2015..
 */
public class SinglyLinkedList<E> {
    private Node<E> head;
    private Node<E> tail;
    private int size;


    // O(1)
    public void addToHead(E element) {
        head = new Node<>(element, head);
        if (tail == null) {
            tail = head;
        }

        size++;
    }

    // O(1)
    public void addToTail(E element) {
        Node<E> node = new Node<>(element, null);

        if (tail != null) {
            tail.getNext().setNext(node);
            tail = node;
        } else {
            tail = head = node;
        }

        size++;
    }

    // O(1)
    public E deleteFromHead() {
        if (isEmpty()) {
            throw new IllegalOperationException("Deleting from empty list now allowed.");
        }

        E element = head.getElement();

        if (head == tail) {
            head = tail = null;
        } else {
            head = head.getNext();
        }

        size--;

        return element;
    }

    // O(n)
    // Average: O(n)
    public E deleteFromTail() {
        if (isEmpty()) {
            throw new IllegalOperationException("Deleting from empty list now allowed.");
        }

        E element = tail.getElement();

        if (head == tail) {
            head = tail = null;
        } else {
            // find the predecessor of the tail!
            Node<E> tmp;
            for (tmp = head; tmp.getNext() != tail; tmp = tmp.getNext()) ;
            tail = tmp;
            tail.setNext(null);
        }

        size--;
        return element;
    }

    // O(n)
    public void deleteNode(E element) {
        if (!isEmpty()) {
            if (head == tail && element == head.getElement()) {
                head = tail = null;
                size--;
            } else if (element == head.getElement()) {
                deleteFromHead();
            } else {
                Node<E> pred;
                Node<E> tmp;

                for (pred = head, tmp = head.getNext();
                     tmp != null && tmp.getElement() != element;
                     tmp = tmp.getNext(), pred = pred.getNext())
                    ;

                if (tmp != null) {
                    pred.setNext(tmp.getNext());
                    if (tmp == tail) {
                        tail = pred;
                    }

                    size--;
                }
            }
        }
    }

    // O(n)
    public boolean contains(E element) {
        Node<E> tmp;
        for (tmp = head; tmp != null && tmp.getElement() != element; tmp = tmp.getNext()) ;
        return tmp != null;
    }

    private boolean isEmpty() {
        return size == 0;
    }

    private static class Node<E> {
        private E element;
        private Node<E> next;

        public Node(E element, Node<E> next) {
            this.element = element;
            this.next = next;
        }

        public E getElement() {
            return element;
        }

        public Node<E> getNext() {
            return next;
        }

        public void setNext(Node<E> node) {
            this.next = node;
        }

    }
}