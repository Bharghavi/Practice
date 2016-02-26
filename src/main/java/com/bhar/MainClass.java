package com.bhar;

import com.bhar.CacheLibrary.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by amrk7 on 2/3/2016.
 */
public class MainClass {
    public static void main(String[] arg) throws IOException {
        //Scanner scanner = new Scanner(System.in);
        //BinaryTree root = BinaryTree.loadFromFile(new File("/Users/amruthkesav/bharghavi/Practice/src/main/java/com/bhar/input.data"));
        //BinaryTree root = BinaryTree.loadFromFile(new File("D:\\new\\Practice\\src\\main\\java\\com\\bhar\\input.data"));
        //System.out.print("Enter node to be found: " );
        //int data = scanner.nextInt();
        //System.out.println("Enter distance: " );
        //int distance = scanner.nextInt();
        //root.rotate();
        //root.printBFT();
        //root.displayNodesFrom(data,distance);
      /*  System.out.println("Maximum depth : " + root.getMaximumDepth());
        System.out.print("Deepest path: ");
        root.printDeepestPath();
        root.printLongestDistance();*/
        //root.printTreeTraversals();
        //Node constructedRoot = BinaryTree.constructTree(root.postOrderTraversal(), root.inOrderTraversal());
        //root.printBorderNodes();
        //BinaryTree.printTree(root.balanceBinaryTree());

       /*  Graph graph = new Graph(6);
        graph.addEdge(0,1);
        graph.addEdge(0,2);
        graph.addEdge(1,3);
        graph.addEdge(3,5);
        graph.addEdge(1,4);
       graph.addEdge(3,4);
        graph.addEdge(2,4);
        graph.addEdge(1,2);
        graph.addEdge(1,3);
        graph.addEdge(0,1);
        graph.printOrdersOfExecution();*/

        List<String> whiteList = new ArrayList<String>();
        whiteList.add("K1");
        whiteList.add("K2");

        Keystore<String, String> hashStore = new HashKeystore<String, String>(4);
        Eviction<String> fifoEviction = new FIFOEviction<String>(whiteList);

        CacheClient<String, String> cacheClient = new CacheClient<>(hashStore, fifoEviction);
        cacheClient.put("K1", "V1", 2);
        cacheClient.put("K2", "V2", 2);
        cacheClient.put("K3", "V3", 2);
        cacheClient.put("K4", "V4", 2);
        cacheClient.put("K5", "V5", 2);

        cacheClient.printCache();


    }
}
