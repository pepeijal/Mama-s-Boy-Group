/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package navigation;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;



/**
 *
 * @author HP
 */
public class Navigation {
    public static void main(String[] args) {
        
        String[] str1, str2;
        Scanner input = new Scanner(System.in);
        Graph<String, Integer> graph1 = new Graph<>();
      
        int num=0;
        
        try{
            
            Scanner read = new Scanner(new FileInputStream ("0.txt")); // read text file
            while(read.hasNextLine()) { 
                //if there is Integer, read the line as string and take the integer from string
                String Line2 = read.nextLine();
                num = Integer.valueOf(Line2);
                
                // if the line is not equals to QUERY, read the next line
                while(read.hasNextLine()){
                String line = read.nextLine();
                if(!Line2.equalsIgnoreCase("QUERIES")) {
                    for(int i=0; i<num; i++) {
                       
                String Line = read.nextLine();
                    

                    if(!read.hasNextLine()) { // if there is no line anymore, stop the loop
                        break;
                    }

                        str1 = Line.split(" => "); // to split the string and assign it as String array
                        str2 = Line.split(" -> ");

                    if(str1.length>1) {     
                        graph1.addVertex(str1[0]); // add the substring at index 0 as the vertex
                        graph1.addVertex(str1[1]);
                        graph1.addEdge(str1[0], str1[1],1); // add the edges between substring index 0 and index 1
                        graph1.addEdge(str1[1], str1[0],1); // add the edges between substring index 1 and index 1 (bidirectional)
                }
                
                    if(str2.length>1) { // to get the path between source and destination
                        System.out.println(str2[0] + " -> " + str2[1]);
                        graph1.DFS(graph1, str2[0], str2[1]); // using Depth First Search to find the path between the vertices
                        System.out.println("\n");
                }
              }
                }      
           }
        }
            read.close();       
            
        }catch(FileNotFoundException e){ // throw exception if did not find file
            System.out.println("File Error");
        }
    }
}

class Vertex<T extends Comparable<T>, N extends Comparable<N>> { // vertex class
    T vertexInfo; // the info of the vertex
    Vertex<T,N> nextVertex; 
    Edge<T,N> firstEdge; // first edge

    public Vertex() { // constructor
        vertexInfo = null;
        nextVertex = null;
        firstEdge = null;
    }
    
    public Vertex(T vInfo, Vertex<T,N> next) {
        vertexInfo = vInfo; //vertex value with T type
        nextVertex = next; // implement using linked list, call next vertex
        firstEdge = null; // 2d array,
    }
}

class Edge<T extends Comparable<T>, N extends Comparable<N>> { // edge class
    Vertex<T,N> toVertex; // the destination
    N weight; // value for the edge
    Edge<T,N> nextEdge; // like linked list
    

    public Edge() {
        toVertex = null;
        weight = null;
        nextEdge = null;
    }

    public Edge(Vertex<T, N> destination, N w, Edge<T, N> a) {
        toVertex = destination;
        weight = w;
        nextEdge = a;
    }
}

class Graph<T extends Comparable<T>, N extends Comparable<N>> { // graph class 
    Vertex<T,N> head; // the first vertex
    int size;
    boolean visited;
    
    public Graph() { // constructor
        head = null;
        size = 0;
    }
   
   public void clear() {   // to clear the list
      head=null;
   }

   public int getSize()   { // to get the size
      return this.size;
   }

   public boolean hasVertex(T v)	{ // method to check is the list has the vertex
      if (head==null)
         return false;
      Vertex<T,N> temp = head;
      while (temp!=null)	{
         if ( temp.vertexInfo.compareTo( v ) == 0 )
            return true;
         temp=temp.nextVertex;
      }
      return false;
   }

   public boolean addVertex(T v)	{ // method to add vertex to the graph
      if (hasVertex(v)==false)	{
         Vertex<T,N> temp=head;
         Vertex<T,N> newVertex = new Vertex<>(v, null);
         if (head==null)   
            head=newVertex;
         else {
            Vertex<T,N> previous=head;;
            while (temp!=null)  {
               previous=temp;
               temp=temp.nextVertex;
            }
            previous.nextVertex=newVertex;
         }
         size++;
         return true;
      }
      else
         return false;
   }
 
   public ArrayList<T> getNeighbours (T v)  { // getting the vertex's neighbours
      if (!hasVertex(v))
         return null;
      ArrayList<T> list = new ArrayList<T>();
      Vertex<T,N> temp = head;
      while (temp!=null)	{
         if ( temp.vertexInfo.compareTo( v ) == 0 )   {
            // Reached vertex, look for destination now
            Edge<T,N> currentEdge = temp.firstEdge;
            while (currentEdge != null) {
               list.add(currentEdge.toVertex.vertexInfo);
               currentEdge=currentEdge.nextEdge;
            }
         }
         temp=temp.nextVertex;
      }
      return list;   
   }
   
   public void printEdges()   { // displaying all of the connected vertices
      Vertex<T,N> temp=head;
      while (temp!=null) {
         System.out.print("# " + temp.vertexInfo + " : " );
         Edge<T,N> currentEdge = temp.firstEdge;
         while (currentEdge != null) {
            System.out.print("[" + temp.vertexInfo + "," + currentEdge.toVertex.vertexInfo +"] " );
            currentEdge=currentEdge.nextEdge;
         }
         System.out.println();
         temp=temp.nextVertex;
      }  
   }
   
    public boolean addEdge(T source, T destination, N w)   {
      if (head==null)
         return false;
      if (!hasVertex(source) || !hasVertex(destination)) 
         return false;
      Vertex<T,N> sourceVertex = head;
      while (sourceVertex!=null)	{
         if ( sourceVertex.vertexInfo.compareTo( source ) == 0 )   {
            // Reached source vertex, look for destination now
            Vertex<T,N> destinationVertex = head;
            while (destinationVertex!=null)	{
               if ( destinationVertex.vertexInfo.compareTo( destination ) == 0 )   {
                  // Reached destination vertex, add edge here
                  Edge<T,N> currentEdge = sourceVertex.firstEdge;
                  Edge<T,N> newEdge = new Edge<>(destinationVertex, w, currentEdge);
                  sourceVertex.firstEdge=newEdge;
                  return true;
               }
               destinationVertex=destinationVertex.nextVertex;
            }
         }
         sourceVertex=sourceVertex.nextVertex;
      }
      return false;
   }
    
    public int getIndex(T v) {
      Vertex<T,N> temp = head;
      int pos=0;
      while (temp!=null)	{
         if ( temp.vertexInfo.compareTo( v ) == 0 )
            return pos;
         temp=temp.nextVertex;
         pos+=1;
      }
      return -1;
   }
    
    public int dfsAddition(Graph graph, String source, String destination, boolean visited[]){
        visited[graph.getIndex(source)] = true;
        
        System.out.print(source + " -> "); // print format
        for(int i =0;i < graph.getNeighbours(source).size();i++){ 
            String str = (String) graph.getNeighbours(source).get(i); // getting the adjacent vertices 
            
            if(str.equals(destination)){ // finding the vertex that same with destination that user wants
                System.out.print(destination);
                return 1;
            }

            if(!visited[graph.getIndex(str)]){ // if there is the vertex that is not visited, transverse again to find the remaining vertex
                if(dfsAddition(graph, str, destination, visited) == 1){ // recursion to repeat 
                    return 1;
                }else continue;
            }
        }
        return 0;
    }
    
    public int DFS(Graph graph, String source, String destination){
        boolean visited[] = new boolean[graph.getSize()]; // to indicate is the vertex is visited or not
        if(dfsAddition(graph, source, destination, visited) == 1) { // calling the other method of DFS to do the operation of searching
            return 1;
        }
        return 0;
    }
}
