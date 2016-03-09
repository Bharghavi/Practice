package com.bhar;

import com.bhar.CacheLibrary.*;
import com.bhar.LoggerLibrary.FileLogger;
import com.bhar.LoggerLibrary.LogClient;
import com.bhar.LoggerLibrary.Logger;
import com.bhar.MultiThreading.MultiThreadLauncher;
import com.bhar.ProducerConsumer.Launcher;

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

       /* List<String> whiteList = new ArrayList<String>();
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

        cacheClient.printCache();*/

       /* Logger<String> fileLogger = new FileLogger<String>("log.txt", 1);
        LogClient<String> logClient = new LogClient<String>(fileLogger);

        logClient.logError("E.line 1");
        logClient.logError("E.line 2");
        logClient.logError("E.line 3");
        logClient.logError("E.line 4");

        logClient.logWarning("W.line 1");
        logClient.logWarning("W.line 2");
        logClient.logWarning("W.line 3");
        logClient.logWarning("W.line 4");*/

        /*MultiThreadLauncher multiThreadLauncher = new MultiThreadLauncher("input.txt", "output.txt", 2);
        multiThreadLauncher.launch();*/

        Launcher launcher = new Launcher("input.txt", "output.txt");
        launcher.launch();
    }
}
