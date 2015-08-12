/**
 * @author Geoff Gray, Austin Meyers
 * @UWNetID gegray, arm38
 * @studentID 1463717, 1228316
 * @email gegray@uw.edu, arm38@uw.edu
 * 
 * Testing for MyGraph.
 **/

import java.io.*;    // file handling
import java.util.*;  // multiple imports

public class TestGraph {

   public void main(String[] args) {
      String input = "edge.txt";      // base, do not modify
      String output = "test-edge.txt"; // output file
      
      
      
      MyGraph mg = readMyGraph(input, output);
      testMyVerts();
      testMyEdges(output);
      testMyPath();
   }
   
   public static MyGraph readMyGraph(String input, String output) {
      Scanner inputScan = new Scanner(new File(input));
      Scanner outputScan = new Scanner(new File(output));
      List<Vertex> verts = new ArrayList<Vertex>();
      List<Edge> edges = new ArrayList<Edge>();
      
      while (inputScan.hasNext()) {
         Vertex v = new Vertex(inputScan.next());
         verts.add(v);
      }
      while (inputScan.hasNext()) {
         Vertex from = new Vertex(inputScan.next());
         Vertex to = new Vertex(inputScan.next());
         int w = inputScan.nextInt();
         Edge e = new Edge(from, to, w);
         edges.add(e);
      }
      MyGraph result = new MyGraph(verts, edges);
      return result;
   }
   
   public static void testMyVerts() {
      String[] correctVertsArray = {"ATL", "ORD", "DEN", "IAH", "IAD", "MKC", "LAX", "JFK", 
                                    "SFO", "SEA", "IND"};
      Set[] testVertsSet = new HashSet<Vertex>();
      
      System.out.println("Testing Verts: ");
      System.out.println("Correct Verts Array is " + correctVertsArray.toString());
      
      for (String correctVert : correctVertsArray) {
         Vertex temp = new Vertex(correctVert);
         System.out.println("Adding Vert: " + temp);
         testVertsSet.add(temp);
         System.out.println("Test Verts Hash Set is now " + testVertsSet.toString());
      }
      
      System.out.println("Correct Verts Array FINAL : " + correctVertsArray.toString());
      System.out.println("Test Verts Hash Set FINAL : " + testVertsSet.toString());
      
      if (testVertsSet.equals(mg.vertices())) {
         System.out.println(".equals check PASSED. ");
      } else {
         System.out.println(".equals check FAILED. Test Verts Hash Set != MyGraph Vertices. ");
      }
      System.out.println();
   }
   
   public void testMyEdges(String output) {
      Scanner outputScan = new Scanner(new File(output));
      List<Edge> edges = new ArrayList<Edge>();
      
      System.out.println("Testing Edges: ");
      
      while (outputScan.hasNext()) {
         Vertex from = new Vertex(inputScan.next());
         Vertex to = new Vertex(inputScan.next());
         int w = inputScan.nextInt();
         Edge e = new Edge(from, to, w);
         System.out.println("Adding edge -- from: " + from + " to: " + to + " weight: " + w);
         edges.add(e);
         System.out.println("Edge list is now " + edges.toString());
      }
      
      if (edges.equals(mg.edges())) {
         System.out.println(".equals check PASSED. ");
      } else {
         System.out.println(".equals check FAILED. ");
      }
      System.out.println();
   }
   
   
   
}





/**
public static void testMyEdges() {
      String[] correctVertsArray = {"ATL", "ORD", "DEN", "IAH", "IAD", "MKC", "LAX", "JFK", 
                                    "SFO", "SEA", "IND"};
      String[] testVertsArray = {};
      
      System.out.println("Testing Verts: ");
      System.out.println("Correct Verts Array is " + correctVertsArray.toString());
      
      for (String correctVert : correctVertsArray) {
         Vertex temp = new Vertex(correctVert);
         System.out.println("Adding Vert: " + temp);
         testVertsArray.add(temp.toString());
         System.out.println("Test Verts Array is now " + testVertsArray.toString());
      }
      
      System.out.println("Correct Verts Array FINAL : " + correctVertsArray.toString());
      System.out.println("Test Verts Array FINAL    : " + testVertsArray.toString());
      
      if (correctVertsArray.equals(testVertsArray)) {
         System.out.println(".equals check PASSED. ");
      } else {
         System.out.println(".equals check FAILED. ");
      }
   }

**/



//34567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890