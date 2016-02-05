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
    
    public void displayNodesFrom(int data, int d) {
    	List<Node> result = getNodesFrom(data, d);
    	for (Node n : result) {
    		System.out.println(n.data);
    	}
    }

    private List getNodesFrom(int data, int d) {
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
            while (i < d && queue.hasData()) {
            	queue.markEndAsMarker();
            	Node current;
               do {
            	   current = queue.deque();
                   if (current.left != null)
                	   queue.enque(current.left);
                   if (current.right != null)
                	   queue.enque(current.right);
                } while (current != queue.getMarker() && queue.hasData());
                queue.markEndAsMarker();
                i++;
            }
        }
        return queue.getAllElementsAsList();
    }

    private List getOtherNodesFrom(Node node, int d) {
    	List<Node> path = getPathFromRoot(node);
        return new ArrayList();
    }

    private List getPathFromRoot(Node node) {
        Stack<Node> stack = new Stack<Node>();
        stack.push(rootNode);
        return getPathFromNode(rootNode, node, stack);
    }
    
    private List getPathFromNode(Node current, Node find, Stack<Node> stack) {
    	if (current.data != find.data) {
    		if (current.left != null) {
    			stack.push(current.left);
    			getPathFromNode(current.left, find, stack);
    		}
    		if (current.right != null) {
    			stack.push(current.right);
    			getPathFromNode(current.right, find, stack);
    		}
    		stack.pop();  		
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
