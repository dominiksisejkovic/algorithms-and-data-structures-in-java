package dsisejkovic.structures.tree;

/**
 * Created by dsisejkovic on 11.7.2015..
 */
public class Node{
    private int value;
    private Node leftChild;
    private Node rightChild;


    public Node(int value) {
        this.value = value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public void setLeftChild(Node node) {
        this.leftChild = node;
    }

    public void setRightChild(Node node) {
        this.rightChild = node;
    }

    public Node getLeftChild() {
        return this.leftChild;
    }

    public Node getRightChild() {
        return this.rightChild;
    }


    @Override
    public String toString() {
        return String.valueOf(this.value);
    }

}