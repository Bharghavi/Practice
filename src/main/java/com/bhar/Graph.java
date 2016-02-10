package com.bhar;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by amrk7 on 2/8/2016.
 */
public class Graph {

    private int vertices;
    private boolean[][] adjacencyMatrix;

    public Graph(int v) {
        this.vertices = v;
        this.adjacencyMatrix = new boolean[v][v];
    }

    public void addEdge(int v1, int v2) {
        adjacencyMatrix[v1][v2] = true;
    }


    public void printOrdersOfExecution() {
        List<Path<Integer>> result = allTopologicalSorts(getVisitableVertices(adjacencyMatrix, new ArrayList<Integer>()), new ArrayList<Integer>());

        for (Path<Integer> eachPath : result) {
            for (int i=eachPath.pathList.size()-1; i>=0; i--) {
                System.out.print(eachPath.pathList.get(i) + " ");
            }
            System.out.println();
        }
    }

    private List<Integer> getVisitableVertices(boolean[][] adjacencyMatrix, List<Integer> visited) {
        List<Integer> result = new ArrayList<Integer>();
        boolean canVisit = true;
        for (int i =0 ; i< vertices; i++) {
            canVisit = true;
            for (int j = 0; j<vertices;j++) {
                if (adjacencyMatrix[j][i]) {
                    canVisit = false;
                    break;
                }
            }
            if (canVisit && !visited.contains(i))
                result.add(i);
        }
        return result;
    }

    private List<Path<Integer>> allTopologicalSorts(List<Integer> toBeVisited, List<Integer> visitedAlready) {
        List<Path<Integer>> result = new ArrayList<Path<Integer>>();

        for (int visit : toBeVisited) {
            List<Integer> temp = new ArrayList<Integer>();
            temp.addAll(visitedAlready);
            temp.add(visit);
            List<Path<Integer>> currentPath = allTopologicalSorts(getAvailableVerticesAfterVisiting(temp), temp);
            if (currentPath.isEmpty())
                currentPath.add(new Path<Integer>(visit));
            else {
             for (Path eachPath : currentPath)
                eachPath.addData(visit);
            }
            result.addAll(currentPath);
        }

        return result;
    }

    private List<Integer> getAvailableVerticesAfterVisiting(List<Integer> visited) {
        boolean[][] tempAdjMat = new boolean[vertices][vertices];
        for (int i =0; i<vertices; i++)
            for (int j=0; j<vertices; j++)
                tempAdjMat[i][j] = adjacencyMatrix[i][j];

        for (int visit : visited) {
            for (int i =0;i<vertices;i++)
                tempAdjMat[visit][i] = false;
        }

        return getVisitableVertices(tempAdjMat, visited);
    }

}
