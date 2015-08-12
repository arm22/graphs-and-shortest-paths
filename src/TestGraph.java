import java.util.*;
import java.io.*;

/**
 * @author Geoff Gray, Austin Meyers
 * @UWNetID gegray, arm38
 * @studentID 1463717, 1228316
 * @email gegray@uw.edu, arm38@uw.edu
 * 
 * Testing for MyGraph.
 **/

public class TestGraph {
   public static MyGraph mg;

   public static void main(String[] args) {
      if(args.length != 2) {
			System.err.println("USAGE: java Paths <vertex_file> <edge_file>");
			System.exit(1);
		}
   
      mg = readGraph(args[0], args[1]);
      
      testMyVerts();
      testMyEdges(args[1]);
      testMyPath();
   }
   
   
   public static MyGraph readGraph(String f1, String f2) {
		Scanner s = null;
		try {
			s = new Scanner(new File(f1));
		} catch(FileNotFoundException e1) {
			System.err.println("FILE NOT FOUND: "+f1);
			System.exit(2);
		}

		Collection<Vertex> v = new ArrayList<Vertex>();
		while(s.hasNext())
			v.add(new Vertex(s.next()));

		try {
			s = new Scanner(new File(f2));
		} catch(FileNotFoundException e1) {
			System.err.println("FILE NOT FOUND: "+f2);
			System.exit(2);
		}

		Collection<Edge> e = new ArrayList<Edge>();
		while(s.hasNext()) {
			try {
				Vertex a = new Vertex(s.next());
				Vertex b = new Vertex(s.next());
				int w = s.nextInt();
				e.add(new Edge(a,b,w));
			} catch (NoSuchElementException e2) {
				System.err.println("EDGE FILE FORMAT INCORRECT");
				System.exit(3);
			}
		}
		return new MyGraph(v,e);
	}
   
   
   public static void testMyVerts() {
      String[] correctVertsArray = {"ATL", "ORD", "DEN", "IAH", "IAD", "MKC", "LAX", "JFK", 
                                    "SFO", "SEA", "IND"};
      Set<Vertex> testVertsSet = new HashSet<Vertex>();
      
      System.out.println("Testing Verts: ");
      System.out.println("Correct Verts Array is " + correctVertsArray.toString());
      
      for (String correctVert : correctVertsArray) {
         Vertex temp = new Vertex(correctVert);
         System.out.println("Adding Vert: " + temp);
         testVertsSet.add(temp);
         System.out.println("Test Verts Hash Set is now " + testVertsSet.toString());
      }
      System.out.println("Test Verts Hash Set FINAL : " + testVertsSet.toString());
      
      if (testVertsSet.equals(mg.vertices())) {
         System.out.println(".equals check PASSED. ");
      } else {
         System.out.println(".equals check FAILED. Test Verts Hash Set != MyGraph Vertices. ");
      }
      System.out.println();
   }
   
   
   public static void testMyEdges(String f2) {
      Scanner s = null;
      try {
         s = new Scanner(new File(f2));
      } catch(FileNotFoundException e1) {
			System.err.println("FILE NOT FOUND: "+f2);
			System.exit(2);
		}
      
      System.out.println("Testing Edges: ");
      Collection<Edge> e = new ArrayList<Edge>();
      while(s.hasNext()) {
			try {
				Vertex a = new Vertex(s.next());
				Vertex b = new Vertex(s.next());
				int w = s.nextInt();
            System.out.println("Adding edge -- from: " + a + " to: " + b + " weight: " + w);
				e.add(new Edge(a,b,w));
            System.out.println("Edge list is now " + e.toString());
			} catch (NoSuchElementException e2) {
				System.err.println("EDGE FILE FORMAT INCORRECT");
				System.exit(3);
			}
		}
      
		if (e.equals(mg.edges())) {
			System.out.println(".equals check PASSED. ");
		} else {
			System.out.println(".equals check FAILED. ");
		}
      System.out.println();
   }
   
   
   public static void testMyPath() {
      boolean passed = true;
      Vertex sea, sfo, mkc, atl, lax, jfk;
      sea = new Vertex("SEA");
      sfo = new Vertex("SFO");
      mkc = new Vertex("MKC");
      atl = new Vertex("ATL");
      lax = new Vertex("LAX");
      jfk = new Vertex("JFK");
      
      System.out.println("Testing Shortest Path: ");
      
      /**
      Path seaSfo = mg.shortestPath(sea, sfo);
      System.out.println("Testing SEA > SFO: ");
      System.out.println("Expected cost: 195 ");
      System.out.println("Your cost    : " + seaSfo.cost);
      if (seaSfo.cost != 195) {
         passed = false;
      }
      
      Path sfoSea = mg.shortestPath(sfo, sea);
      System.out.println("Testing SFO > SEA: ");
      System.out.println("Expected cost: 193 ");
      System.out.println("Your cost    : " + sfoSea.cost);
      if (sfoSea.cost != 193) {
         passed = false;
      }
      
      Path seaMkc = mg.shortestPath(sea, mkc);
      System.out.println("Testing SEA > MKC: ");
      System.out.println("Expected cost: 391 ");
      System.out.println("Your cost    : " + seaMkc.cost);
      if (seaMkc.cost != 391) {
         passed = false;
      }
      
      Path laxJfk = mg.shortestPath(lax, jfk);
      System.out.println("Testing LAX > JFK: ");
      System.out.println("Expected cost: 692 ");
      System.out.println("Your cost    : " + laxJfk.cost);
      if (laxJfk.cost != 692) {
         passed = false;
      }
      **/
      Path jfkLax = mg.shortestPath(jfk, lax);
      System.out.println("Testing JFK > LAX: ");
      System.out.println("Expected cost: 678 ");
      System.out.println("Your cost    : " + jfkLax.cost);
      if (jfkLax.cost != 678) {
         passed = false;
      }
      

      if (passed) {
         System.out.println("All shortest path tests passed. ");
      } else {
         System.out.println("One or more shortest path tests failed. See log above. ");
      }
      System.out.println();
   }
   
}