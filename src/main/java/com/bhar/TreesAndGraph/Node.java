package com.bhar.TreesAndGraph;

/**
 * Created by amrk7 on 2/2/2016.
 */
public class Node<T> {
    public T data;
    public Node left;
    public Node right;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Node node = (Node) o;

        return data == node.data;
    }

   /* @Override
    public T hashCode() {
        T result = data;
        result = 31 * result + left.hashCode();
        result = 31 * result + right.hashCode();
        return result;
    }*/
}
