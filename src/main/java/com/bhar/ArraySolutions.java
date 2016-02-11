package com.bhar;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bharghav on 2/11/2016.
 */
public class ArraySolutions {

    private List<Integer> array;

    public ArraySolutions(List<Integer> array) {
        this.array = new ArrayList<Integer>();
        this.array.addAll(array);
    }

    public void printLargestSubsequence() {
        List<Integer> result = findLargestIncreasingSequence();
        for (int each : result)
            System.out.print(each + " ");
    }

    public List<Integer> findLargestIncreasingSequence() {
        return findLargestIncreasingSequence(0);
    }

    public List<Integer> findLargestIncreasingSequence(int begin) {
        List<Integer> result = new ArrayList<Integer>();
        int i = begin;
        result.add(array.get(begin));
        while (i < array.size()-1) {
            if (array.get(i) < array.get(i+1)) {
                result.add(array.get(i + 1));
                i++;
            } else
                break;
        }

        if (i < array.size()-1) {
            List<Integer> largestSubset = findLargestIncreasingSequence(i+1);
            if (result.size() > largestSubset.size())
                return result;
            else
                return largestSubset;
        }

        return result;
    }
}
