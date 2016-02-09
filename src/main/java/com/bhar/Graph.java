package com.bhar;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by amrk7 on 2/8/2016.
 */
public class Graph {

    private int vertices;
    private List<LLNode<Integer>> adjacencyList;

    public Graph(int v) {
        this.vertices = v;
        this.adjacencyList = new ArrayList<LLNode<Integer>>(vertices);
        for (int i =0; i <vertices ; i++)
            adjacencyList.add(i, new LLNode<Integer>());
    }

    public void addEdge(int v1, int v2) {
        if (adjacencyList.get(v1).data == null)
            adjacencyList.set(v1, new LLNode<Integer>(v2));
        else {
            LLNode<Integer> current = adjacencyList.get(v1);
            while (current.next != null)
                current = current.next;
            current.add(v2);
        }
    }

    public void printOrderOfExecution(List<Integer> list) {
       for (int each  : list)
           System.out.print(each + " ");
        System.out.println();
    }

    private boolean contains(LLNode<Integer> linkedList, int data) {
        LLNode<Integer> current = linkedList;
        while (current != null && current.data != null) {
            if (current.data == data)
                return true;
        }
        return false;
    }

    private List<Integer> topologicalSort() {
        List<Integer> result = new ArrayList<Integer>();
        boolean visited[] = new boolean[vertices];
        boolean canBeVisited[] = new boolean[vertices];

        for (int i = 0; i<vertices; i++)
            canBeVisited[i] = true;

        updateVisits(canBeVisited, visited);

        for (int i = 0; i<vertices; i++) {
           if (canBeVisited[i] && !visited[i])
               printOrderOfExecution(topologicalSort(i, visited, new Stack<Integer>()));
        }

        return result;
    }

    private void updateVisits(boolean[] canBeVisited, boolean[] visited) {
        for (int i = 0; i < vertices; i++) {
            for (int j = 0 ; j< vertices ; j++) {
                if (contains(adjacencyList.get(j), i) && !visited[i]) {
                    canBeVisited[i] = false;
                    break;
                }
            }
        }
    }

    private List<Path> topologicalSort(int index, boolean[] visited, Stack<Integer> stack) {
        List<Path> result = new ArrayList<Path>();

        LLNode<Integer>  currentNode = adjacencyList.get(index);
        while (currentNode != null && currentNode.data != null) {
            if (!visited[currentNode.data])
                result.add(new Path(topologicalSort(currentNode.data, visited, stack)));
            currentNode = currentNode.next;
        }

        visited[index] = true;
        stack.push(index);
        // stack.getAllElementsAsList();
        return result;
    }


}
