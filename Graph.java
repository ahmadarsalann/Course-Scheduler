package com.company;

// Graph.java

import java.util.HashMap;
import java.util.HashSet;

class Graph<Type> {
    // the matrix stores the edge information
    private boolean[][] matrix;

    // this stores the values being stored by this graph
    private Type[] values;

    // the size of the graph
    private int size;

    // set the graph as empty
    public Graph(int size) {
        matrix = new boolean[size][size];
        this.size = size;

        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                matrix[i][j] = false;
            }
        }

        // make space for the values (and ignore the cast warning)
        @SuppressWarnings("unchecked")
        Type[] values = (Type[]) new Object[size];
        this.values = values;
    }

    // lookup a node number by value
    public int lookup(Type value) {
        for (int i = 0; i < size; i++) {
            if (values[i] != null && values[i].equals(value)) {
                return i;
            }
        }
        return -1;
    }

    public Type lookup(int value){
        return values[value];
    }


    // insert an edge by index
    public void insertEdge(int from, int to) {
        matrix[from][to] = true;
    }

    // insert an edge by value
    public void insertEdge(Type from, Type to) {
        int fromIndex = lookup(from);
        int toIndex = lookup(to);
        insertEdge(fromIndex, toIndex);
    }

    // remove an edge
    public void removeEdge(int from, int to) {
        matrix[from][to] = false;
    }

    // return whether these are connected
    public boolean isEdge(int from, int to) {
        return matrix[from][to];
    }

    public boolean isEdge(int to){
        for(int i = 0; i < matrix.length; i++){
            if(matrix[to][i]){
                return true;
            }//end of if
        }//end of for
        return false;
    }

    // add a node's data to the graph
    public void setValue(int node, Type value) {
        values[node] = value;
    }

    // return the size of the graph
    public int getSize() {
        return size;
    }

    // get the value of a node
    public Type getValue(int index) {
        return values[index];
    }

    public void printvalues(){
        boolean good = true;
        HashMap<Integer, Integer> check = new HashMap<>();
        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix.length; j++){
                if(matrix[i][j] == good) {
                    check.put(i, j);
                }
            }

        }
        System.out.println(check);
    }
}
