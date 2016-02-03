package com.bhar;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by amrk7 on 2/2/2016.
 */
public class BinaryTree {
    private Node rootNode;

    public BinaryTree(int root) {
        rootNode = new Node();
        rootNode.data = root;
    }

    public Node getRoot() {
        return rootNode;
    }

    public Node insert(Node node, boolean isLeft, int data){
        Node newNode = new Node();
        newNode.data = data;
        if (isLeft)
            node.left = newNode;
        else
            node.right = newNode;
        return newNode;
    }

    public void rotate() {
        rotate(rootNode);
    }

    private void rotate(Node node) {
        if (node != null) {
            Node temp = node.left;
            node.left = node.right;
            node.right = temp;
            rotate(node.left);
            rotate(node.right);
        }
//        return node;
    }

    public void printBFT() {
        Queue<Node> queue = new Queue<Node>(rootNode);
        while (queue.hasData()) {
            System.out.print(queue.peekFirst().data);
            Node current = queue.deque();
            if (current.left != null)
                queue.enque(current.left);
            if (current.right != null)
                queue.enque(current.right);
        }
    }

    public List getNodesFrom(int data, int d) {
        Node find = findNode(data);
        if (find == null)
            return new ArrayList();
        List<Node> result = new ArrayList<Node>();
        result.addAll(getChildNodesFrom(find, d));
        result.addAll(getOtherNodesFrom(find, d));
        return result;
    }

    private List getChildNodesFrom(Node node, int d) {
        Queue<Node> queue = new Queue<Node>(node);
        int i = 0;
        if (d > 0) {
            Node current = queue.deque();
            while (i < d ) {
                while (current != queue.getTail()) {
                    queue.enque(current.left);
                    queue.enque(current.right);
                    current = queue.deque();
                }
                queue.setTail();
                i++;
            }
        }
        return queue.getAllElementsAsList();
    }

    private List getOtherNodesFrom(Node node, int d) {
        return new ArrayList();
    }

    private List getPathFromRoot(Node node) {
        Stack<Node> stack = new Stack<Node>();
        stack.push(rootNode);
        Node top = rootNode;
        while (top != node) {
            while (node.left != null)
                stack.push(node.left);
            if (node.right != null)
                stack.push(node.right);
            else
                stack.pop();
            top = stack.getTop();
        }
        return stack.getAllElementsAsList();
    }

    private Node findNode(int data){
        Queue<Node> queue = new Queue<Node>(rootNode);
        while (queue.hasData()) {
            Node current = queue.deque();
            if (current.data == data)
                return current;
            queue.enque(current.left);
            queue.enque(current.right);
        }
        return null;
    }

}
