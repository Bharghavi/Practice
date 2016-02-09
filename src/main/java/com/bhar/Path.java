package com.bhar;

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

    public Path(List<T> data) {
        pathList = data;
    }


}
