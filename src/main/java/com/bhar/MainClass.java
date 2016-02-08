package com.bhar;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by amrk7 on 2/3/2016.
 */
public class MainClass {
    public static void main(String[] arg) throws IOException {
        Scanner scanner = new Scanner(System.in);
        BinaryTree root = BinaryTree.loadFromFile(new File("/Users/amruth.s/Documents/personal/Practice/src/main/java/com/bhar/input.data"));
        //System.out.print("Enter node to be found: " );
        //int data = scanner.nextInt();
        //System.out.println("Enter distance: " );
        //int distance = scanner.nextInt();
        //root.rotate();
        //root.printBFT();
        //root.displayNodesFrom(data,distance);
        System.out.print("Deepest path : " + root.getDeepestPath());
    }
}
