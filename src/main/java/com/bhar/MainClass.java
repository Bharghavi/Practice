package com.bhar;

import java.util.Scanner;

/**
 * Created by amrk7 on 2/3/2016.
 */
public class MainClass {
    public static void main(String[] arg) {
        System.out.println("Enter root");
        Scanner scanner = new Scanner(System.in);
        int rootValue = scanner.nextInt();
        BinaryTree root = new BinaryTree(rootValue);
        Queue<Node> queue = new Queue<Node>(root.getRoot());

        while (queue.hasData()) {
            Node current = queue.deque();
            System.out.println("Enter left of " + current.data);
            int left = scanner.nextInt();
            if (left != -1) {
                queue.enque(root.insert(current, true, left));
            }
            System.out.println("Enter right of " + current.data);
            int right = scanner.nextInt();
            if (right != -1)
                queue.enque(root.insert(current, false, right));
        }
        //root.rotate();
        //root.printBFT();
        root.displayNodesFrom(7,2);
    }
}
