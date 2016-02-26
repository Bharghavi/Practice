package com.bhar.TreesAndGraph;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by amrk7 on 2/9/2016.
 */
public class Path<T> {
    List<T> pathList;

     public Path(){
        pathList = new ArrayList<T>();
    }

    public Path(List<T> list) {
        pathList = list;
    }

    public Path(T data) {
        pathList =  new ArrayList<T>();
        pathList.add(data);
    }

    public void addData(T data) {
        pathList.add(data);
    }

    public void addAll(List<T> list) {
        pathList.addAll(list);
    }


}
