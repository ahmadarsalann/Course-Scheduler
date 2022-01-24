package com.company;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int size = 0;//initialize the size at zero
        Graph<String> graph;//declare the graph
        ArrayList<String> manipulate = new ArrayList<>();//ArrayList to add stuff in the graph
        ArrayList<String> courses = new ArrayList<>();//Get the courses with no prereq
        ArrayList<Integer> numofreq = new ArrayList<>();//get the number of prereq
        try {
            FileReader file = new FileReader(args[0]);//read the file once
            FileReader file2 = new FileReader(args[0]);//read the file twice
            Scanner scan = new Scanner(file);//to scan the file once
            Scanner scan2 = new Scanner(file2);//to scan the file twice
            int useless = Integer.parseInt(scan2.nextLine());//get the size of the file+++
            size = Integer.parseInt(scan.nextLine());//convert the string size to an int
            graph = new Graph<>(size);//initalize the graph with a size
            StringBuffer bro = new StringBuffer();//Buffer to get the courses before the prereq number
            StringBuffer bro2 = new StringBuffer();//Buffer to get prereq numbers
            int a = 0;//initailize a
            while(scan.hasNext()){//while the scanner is see the data
                String split = scan.nextLine();//put the whole line in string split
                String[] newdata = split.split(" ");//split the data by every space and put it in the array
                bro.append(newdata[0] + " ");//get the first element of the array and append to bro
                bro2.append(newdata[1] + " ");//get the second element of the array and append to bro2
                a++;//increment a
            }//end of while
            String see = bro.toString();//convert bro buffer to string
            String see2 = bro2.toString();//convert bro2 buffer to string
            String[] newdata2 = see.split(" ");//split the see string from space and put it in an array
            String[] newdata4 = see2.split(" ");//split the see2 string from space and put it in an array
            for(int i = 0; i < a; i++){//for loop to set value and add stuff to graph
                graph.setValue(i, newdata2[i]);//set a value and add the data to graph
                courses.add(newdata2[i]);//add the data from array to arraylist
                courses.add(newdata4[i]);//add the data from another array to arraylist
                numofreq.add(Integer.parseInt(newdata4[i]));//add the number of prereq to a different arraylist
            }//end of for
            while(scan2.hasNext()){//another iteration to go through the file
                String split = scan2.nextLine();//String to store the next line
                String[] newdata3 = split.split(" ");//split the string by spaces and add it to the array
                for(int i = 0; i < newdata3.length; i++){//loop to go over an array
                    manipulate.add(newdata3[i]);//add the data to arraylist
                }//end of for
                for(int i = 2; i < manipulate.size(); i++){//another loop
                    graph.insertEdge(manipulate.get(i), manipulate.get(0));//insert all the edges in the graph
                }//end of for
                for(int i = 0; i < manipulate.size(); i++){//loop
                    manipulate.remove(i);//remove all the elements from the arraylist and prepare for next iteration
                    i--;//decrement i
                }//end of for
            }//end of while
            int first = 1;//initalize first to 1
            StringBuffer emptynodes = new StringBuffer();//buffer to get the nodes which dont have edges
            for(int i = 0; i < courses.size(); i++){//get a loop
                if(first == courses.size()){//if first equals to size break out
                    break;//breakout
                }//end of if
                if(courses.get(first).equals("0")){//if we see a 0 in arraylist
                    emptynodes.append(courses.get(i) + " ");//element before that store it in buffer
                }//end of if
                first++;//increment first
            }//end of for
            String emptynodes2 = emptynodes.toString();//convert buffer to string
            String[] nodes = emptynodes2.split(" ");//split the string by space and store it in an array
            ArrayList<String> ordering = new ArrayList<>();//arraylist for topological ordering
            ArrayList<String> activeset = new ArrayList<>();//arraylist to get a set
            for(int i = 0; i < nodes.length; i++){//loop to go through the active set
                activeset.add(nodes[i]);//add the nodes with no edges to activeset
            }//end of for
            int c = 0;//initialize c
            while(c < activeset.size()){//while c is less than activeset size
               int j = graph.lookup(activeset.get(c));//j looks up for a value of the edge in c
               ordering.add(graph.lookup(j));//adds the element to ordering
               for(int i = 0; i < graph.getSize(); i++){//loop for topological sorting
                   if(graph.isEdge(j, i)){//if two nodes have an edge
                       graph.removeEdge(j, i);//remove it
                       numofreq.set(i, numofreq.get(i) - 1);//decrement the prereq by 1
                       if(numofreq.get(i).equals(0)){//if prereq is zero
                           activeset.add(graph.lookup(i));//add it to active set since it has no nodes
                       }//end of if
                   }//end of if
                   else{
                       continue;//else next iteration
                   }//end of else
               }//end of for
               c++;//increment c for next iteration
            }
            if(ordering.size() != useless){//if ordering is not equals to the size of graph
                System.out.println("No topological ordering");//print this
            }//end of if
            else {
                System.out.println("There is an order!!!!");//else there is an order
                for (int i = 0; i < ordering.size(); i++) {//for loop to print the order
                    System.out.println(ordering.get(i));//prints the order
                }//end of for
            }//end of else

        }//end of try
        catch(FileNotFoundException e){//handle filenotfoundexception
            System.out.println("File not found");//print file not found if exception catch
        }//end of catch

    }//end of main
}//end of Primary Main
