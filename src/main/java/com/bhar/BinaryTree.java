package com.bhar;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

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

    private List<Node> getNodesFrom(int data, int d) {
        Node find = findNode(data);
        if (find == null)
            return new ArrayList<Node>();
        List<Node> result = new ArrayList<Node>();
        result.addAll(getChildNodes(find, d));
        result.addAll(getOtherNodesFrom(find, d));
        return result;
    }

    private List<Node> getChildNodes(Node node, int d) {
        Queue<Node> queue = new Queue<Node>(node);
        queue.markEnd();
        int i = 0;
        while (i < d && queue.hasData()) {
            Node current;
           do {
               current = queue.deque();
               if (current.left != null)
                   queue.enque(current.left);
               if (current.right != null)
                   queue.enque(current.right);
            } while (!current.equals(queue.getMarker()) && queue.hasData());
            queue.markEnd();
            i++;
        }
        return queue.getAllElementsAsList();
    }

    private List<Node> getOtherNodesFrom(final Node node, final int d) {
        final List<Node> result = new ArrayList<Node>();
        final List<Node> path = getPathFromRoot(node);
        int i=1;
        for(Node parentNode : path.subList(1, d>path.size()? path.size() : d)) {
            Node disconnectedSubTree;
            if(node.equals(parentNode.left)) {
                disconnectedSubTree = parentNode.left;
                parentNode.left = null;
                result.addAll(
                        getChildNodes(
                                parentNode,
                                d-i
                        )
                );
                parentNode.left = disconnectedSubTree;
            } else if (node.equals(parentNode.right)) {
                disconnectedSubTree = parentNode.right;
                parentNode.right = null;
                result.addAll(
                        getChildNodes(
                                parentNode,
                                d-i
                        )
                );
                parentNode.right = disconnectedSubTree;
            }
            i--;

        }
        return result;
    }

    private List<Node> getPathFromRoot(Node node) {
        final Stack<Node> stack = new Stack<Node>();
        stack.push(rootNode);
        if (getPathFromNode(rootNode, node, stack))
            return stack.getAllElementsAsList();
        return new ArrayList<Node>();
    }
    
    private boolean getPathFromNode(Node current, Node find, Stack<Node> stack) {
    	if (current.data != find.data) {
    		if (current.left != null) {
    			stack.push(current.left);
    			if (getPathFromNode(current.left, find, stack))
                    return true;
    		}
    		if (current.right != null) {
    			stack.push(current.right);
    			if (getPathFromNode(current.right, find, stack))
                    return true;
    		}
    		stack.pop();  		
    	}else {
            return true;
        }
    	return false;
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

    public static BinaryTree loadFromFile(File file) throws IOException {
        Map<Integer, List<Integer>> rawMap = new HashMap<Integer, List<Integer>>();
        BufferedReader br = new BufferedReader(new FileReader(file));
        for(String line; (line = br.readLine()) != null; ) {
            String[] relationShip = line.split(":");
            final List<Integer> children = new ArrayList<Integer>();
            for(String child : relationShip[1].split(",")) {
                children.add(Integer.parseInt(child));
            }
            rawMap.put(Integer.parseInt(relationShip[0]), children);
        }
        return loadFromMap(rawMap);
    }

    public static BinaryTree loadFromMap(Map<Integer, List<Integer>> rawMap) {
        final Iterator<Map.Entry<Integer, List<Integer>>> iterator = rawMap.entrySet().iterator();
        final Map<Integer, Node> nodeReferences = new HashMap<Integer, Node>();
        final Map.Entry<Integer, List<Integer>> firstRelation = iterator.next();
        final BinaryTree tree = new BinaryTree(firstRelation.getKey());
        final Node node = tree.getRoot();
        nodeReferences.put(
                firstRelation.getValue().get(0),
                tree.insert(node, true, firstRelation.getValue().get(0))
        );
        if(firstRelation.getValue().size() > 1) {
            nodeReferences.put(
                    firstRelation.getValue().get(1),
                    tree.insert(node, false, firstRelation.getValue().get(1))
            );
        }
        while (iterator.hasNext()) {
            Map.Entry<Integer, List<Integer>> relationShip = iterator.next();
            final Integer parent = relationShip.getKey();
            final List<Integer> children = relationShip.getValue();
            Node targetNode = nodeReferences.get(parent);
            nodeReferences.put(
                    children.get(0),
                    tree.insert(targetNode, true, children.get(0))
            );
            if(children.size() > 1) {
                nodeReferences.put(
                        children.get(1),
                        tree.insert(targetNode, false, children.get(1))
                );
            }
        }
        return tree;
    }

    public int getMaximumDepth() {
       return getMaximumFrom(rootNode);
    }

    private int getMaximumFrom(Node node) {
        int leftDepth = 0 , rightDepth = 0;
        if (node == null || isLeaf(node))
            return 0;
        leftDepth = getMaximumFrom(node.left);
        rightDepth = getMaximumFrom(node.right);

        if (leftDepth > rightDepth) {
            return leftDepth + 1;
        }
        else
            return rightDepth + 1;
    }

    private boolean isLeaf (Node node) {
        return node.left == null && node.right == null;
    }

    public void printDeepestPath() {
        printList(getDeepestPathFrom(rootNode));
    }

    private void printList(List<Node> list) {
        for (Node each : list)
            System.out.print(each.data + " ");
    }

    private List<Node> getDeepestPathFrom(Node node){
        List<Node> result = new ArrayList<Node>();
        if (node == null)
            return result;
        List<Node> leftPath = getDeepestPathFrom(node.left);
        List<Node> rightPath = getDeepestPathFrom(node.right);
        if (leftPath.size() > rightPath.size()) {
            result.add(node);
            result.addAll(leftPath);
        } else {
            result.add(node);
            result.addAll(rightPath);
        }
        return result;
    }

    public void printLongestDistance() {
        List<Node> deepestBranch = getDeepestPathFrom(rootNode);
        int longestDistance = 0;
        for (Node node : deepestBranch) {
            int nodeLength = getLongestDistanceFrom(node).size() - 1;
            if (nodeLength> longestDistance)
                longestDistance = nodeLength;
        }
        System.out.println("Longest distance: " + longestDistance);
        //printList(getLongestDistanceFrom(rootNode));
    }

    private List<Node> getLongestDistanceFrom(Node node) {
        List<Node> result = new ArrayList<Node>();
        if (node == null)
            return result;

        List<Node> leftPath = getDeepestPathFrom(node.left);
        List<Node> rightPath = getDeepestPathFrom(node.right);

        result.addAll(leftPath);
        result.add(node);
        result.addAll(rightPath);
        return result;
    }


}
