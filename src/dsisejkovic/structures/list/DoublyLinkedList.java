package dsisejkovic.structures.list;

import java.util.NoSuchElementException;

/**
 * Created by dsisejkovic on 8.7.2015..
 */
public class DoublyLinkedList<E> {
    private Node<E> head;
    private Node<E> tail;
    private int size;


    // O(1)
    public void addToHead(E element) {
        if (isEmpty()) {
            head = tail = new Node<>(element, null, null);
        } else {
            Node<E> node = new Node<>(element, head, null);
            head.setPrevious(node);
            head = node;
        }

        size++;
    }

    // O(1)
    public void addToTail(E element) {
        if (isEmpty()) {
            tail = head = new Node<>(element, null, null);
        } else {
            Node<E> node = new Node<>(element, null, tail);
            tail.setNext(node);
            tail = node;
        }

        size++;
    }

    // O(1)
    public E deleteFromTail() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        E element = tail.getElement();
        Node<E> prev = tail.getPrevious();

        tail.setElement(null);
        tail.setPrevious(null);
        tail = prev;

        if (prev == null) {
            head = null;
        } else {
            prev.setNext(null);
        }

        size--;

        return element;
    }

    // O(1)
    public E deleteFromHead() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        E element = head.getElement();
        Node<E> next = head.getNext();

        head.setElement(null);
        head.setNext(null);
        head = next;

        if (next == null) {
            tail = null;
        } else {
            next.setPrevious(null);
        }

        size--;

        return element;
    }

    private boolean isEmpty() {
        return size == 0;
    }

    public int getSize() {
        return size;
    }

    private static class Node<E> {
        private E element;
        private Node<E> next;
        private Node<E> previous;

        public Node(E element, Node<E> next, Node<E> previous) {
            this.element = element;
            this.next = next;
            this.previous = previous;
        }

        public E getElement() {
            return element;
        }

        public void setElement(E element) {
            this.element = element;
        }

        public Node<E> getNext() {
            return next;
        }

        public Node<E> getPrevious() {
            return previous;
        }

        public void setNext(Node<E> node) {
            this.next = node;
        }

        public void setPrevious(Node<E> node) {
            this.previous = node;
        }
    }
}