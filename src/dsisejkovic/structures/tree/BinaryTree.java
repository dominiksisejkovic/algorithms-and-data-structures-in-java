package dsisejkovic.structures.tree;

import java.util.*;

/**
 * Created by dsisejkovic on 11.7.2015..
 */
public class BinaryTree {
    public void add(Node node, int value) {
        if (value < node.getValue()) {
            if (node.getLeftChild() == null) {
                node.setLeftChild(new Node(value));
            } else {
                add(node.getLeftChild(), value);
            }
        } else {
            if (node.getRightChild() == null) {
                node.setRightChild(new Node(value));
            } else {
                add(node.getRightChild(), value);
            }
        }
    }

    public boolean search(Node node, int value) {
        if (node == null) {
            return false;
        }

        if (node.getValue() == value) {
            return true;
        }

        if (value < node.getValue()) {
            return search(node.getLeftChild(), value);
        } else {
            return search(node.getRightChild(), value);
        }
    }

    public Node deleteByCopyingRec(Node node, int value) {
        if (node == null) {
            return null;
        } else if (value < node.getValue()) {
            node.setLeftChild(deleteByCopyingRec(node.getLeftChild(), value));
            return node;
        } else if (value > node.getValue()) {
            node.setRightChild(deleteByCopyingRec(node.getRightChild(), value));
            return node;
        } else {
            // found the node!

            if (node.getLeftChild() == null) {
                return node.getRightChild();
            } else if (node.getRightChild() == null) {
                return node.getLeftChild();
            } else {
                // node has both children: delete by copying

                // 1. find predecessor
                Node predecessor = node.getLeftChild();
                while (predecessor.getRightChild() != null) {
                    predecessor = predecessor.getRightChild();
                }

                // 2. copy data from predecessor to node (to be "deleted")
                node.setValue(predecessor.getValue());

                // 3. bypass the predecessor (skip over it)
                return predecessor.getLeftChild(); // predecessor doesn't have right child (for sure!).
            }
        }
    }

    private Node deleteByMerging(Node node, int value) {
        if (node == null) {
            return null;
        } else if (value < node.getValue()) {
            node.setLeftChild(deleteByMerging(node.getLeftChild(), value));
            return node;
        } else if (value > node.getValue()) {
            node.setRightChild(deleteByMerging(node.getRightChild(), value));
            return node;
        } else {
            //found the node!
            if (node.getLeftChild() == null) {
                return node.getRightChild();
            } else if (node.getRightChild() == null) {
                return node.getLeftChild();
            } else {
                // has both children: delete by merging

                //1. find predecessor
                Node predecessor = node.getLeftChild(); // go left
                while (predecessor.getRightChild() != null) {
                    predecessor = predecessor.getRightChild(); // go right as mush as you can
                }

                // 2. redirect pointer of predecessor to right child of node to be deleted 
                predecessor.setRightChild(node.getRightChild());

                // redirect parent's left pointer to left child of node to be deleted
                return node.getLeftChild();
            }
        }
    }

    // uses queue
    public void bfs(Node node) {
        Queue<Node> q = new LinkedList<>();
        q.add(node);

        while (!q.isEmpty()) {
            Node currentNode = q.remove();
            System.out.println(currentNode.getValue());

            if (currentNode.getLeftChild() != null) {
                q.add(currentNode.getLeftChild());
            }

            if (currentNode.getRightChild() != null) {
                q.add(currentNode.getRightChild());
            }
        }

    }

    // uses stack 
    public void dfs(Node node) {
        Stack<Node> s = new Stack<>();
        s.push(node);

        while (!s.isEmpty()) {
            Node currentNode = s.pop();

            System.out.println(currentNode.getValue());

            if (currentNode.getRightChild() != null) {
                s.push(currentNode.getRightChild());
            }

            if (currentNode.getLeftChild() != null) {
                s.push(currentNode.getLeftChild());
            }
        }
    }

    public void printInorder(Node node) {
        if (node != null) {
            printInorder(node.getLeftChild());
            System.out.println(node.getValue() + " ");
            printInorder(node.getRightChild());
        }
    }

    public void printPreorder(Node node) {
        if (node != null) {
            System.out.println(node.getValue() + " ");
            printPreorder(node.getLeftChild());
            printPreorder(node.getRightChild());
        }
    }

    public void printPostorder(Node node) {
        if (node != null) {
            printPostorder(node.getLeftChild());
            printPostorder(node.getRightChild());
            System.out.println(node.getValue() + " ");
        }
    }

    void iterativePreorder(Node node) {
        if (node == null) {
            throw new IllegalArgumentException();
        }

        Stack<Node> stack = new Stack<>();
        stack.push(node);

        while (!stack.isEmpty()) {
            Node tmp = stack.pop();
            System.out.println(tmp.getValue());


            if (tmp.getRightChild() != null) {
                stack.push(tmp.getRightChild());
            }

            if (tmp.getLeftChild() != null) {
                stack.push(tmp.getLeftChild());
            }
        }
    }

    void iterativeInorder(Node node) {
        if (node == null) {
            throw new IllegalArgumentException();
        }

        Stack<Node> stack = new Stack<>();

        while (node != null) {
            stack.push(node);
            node = node.getLeftChild();
        }

        while (!stack.isEmpty()) {
            Node tmpNode = stack.pop();
            System.out.println(tmpNode.getValue());

            if (tmpNode.getRightChild() != null) {
                tmpNode = tmpNode.getRightChild();

                while (tmpNode != null) {
                    stack.push(tmpNode);
                    tmpNode = tmpNode.getLeftChild();
                }
            }
        }
    }

    // using two stacks
    public void iterativePostorder1(Node node) {
        if (node == null) {
            throw new IllegalArgumentException();
        }

        Stack<Node> s1 = new Stack<>(); // used as tmp stack
        Stack<Node> s2 = new Stack<>(); // will contain the right order of nodes to traverse

        s1.push(node);

        while (!s1.isEmpty()) {
            node = s1.pop();
            s2.push(node);

            if (node.getLeftChild() != null) {
                s1.push(node.getLeftChild());
            }
            if (node.getRightChild() != null) {
                s1.push(node.getRightChild());
            }
        }

        while (!s2.isEmpty()) {
            node = s2.pop();
            System.out.println(node.getValue());
        }
    }

    // using one stack
    // The idea is to move down to leftmost node using left pointer. 
    // While moving down, push root and root’s right child to stack.
    // Once we reach leftmost node, print it if it doesn’t have a right child. 
    // If it has a right child, then change root so that the right child is processed before.
    public void iterativePostorder2(Node node) {
        // TODO
    }


    public static void main(String[] args) {
        int[] inputArray = new int[]{10, 25, 2, 12, 20, 31, 29};
        Node root = new Node(13);
        BinaryTree tree = new BinaryTree();

        // add values to tree
        for (int value : inputArray) {
            tree.add(root, value);
        }

        System.out.println("Inorder: ");
        tree.printInorder(root); // sorted!

        System.out.println("Iterative inorder: ");
        tree.iterativeInorder(root);

        System.out.println("Preorder: ");
        tree.printPreorder(root);

        System.out.println("Iterative preorder: ");
        tree.iterativePreorder(root);

        System.out.println("Postorder: ");
        tree.printPostorder(root);
        System.out.println(tree.search(root, 10));
        System.out.println(tree.search(root, 0));

        System.out.println("Iterative postorder1: ");
        tree.iterativePostorder1(root);
        System.out.println("Iterative postorder2: ");
        tree.iterativePostorder2(root);

        System.out.println("BFS:");
        tree.bfs(root);

        System.out.println("DFS:");
        tree.dfs(root);

        System.out.println("Delete by merging recursive: ");
        tree.deleteByMerging(root, 12);
        tree.deleteByMerging(root, 25);
        tree.printInorder(root);

        System.out.println("Delete by copying recursive: ");
        tree.deleteByCopyingRec(root, 2);
        tree.deleteByCopyingRec(root, 31);
        tree.printInorder(root);
        System.out.println(root);

    }

}
