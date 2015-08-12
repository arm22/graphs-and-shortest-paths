/** @author Geoff Gray, Austin Meyers
 * @UWNetID gegray, arm38
 * @studentID 1463717, 1228316
 * @email gegray@uw.edu, arm38@uw.edu
 * 
 */

import java.util.*;

/**
 * A representation of a graph. Assumes that we do not have negative cost edges
 * in the graph.
 */
public class MyGraph implements Graph {
	private Collection<Vertex> vertList;
	private Collection<Edge> edgeList;
	private Map<Vertex, Set<Edge>> graph;

	/**
	 * Creates a MyGraph object with the given collection of vertices and the
	 * given collection of edges.
	 * 
	 * @param v
	 *            a collection of the vertices in this graph
	 * @param e
	 *            a collection of the edges in this graph
	 */
	public MyGraph(Collection<Vertex> v, Collection<Edge> e) {
		if (v == null || v.size() == 0 || e == null || e.size() == 0) {
			throw new IllegalArgumentException();
		}
		edgeList = new HashSet<Edge>();
		graph = new HashMap<Vertex, Set<Edge>>();
		
		//Test if each edge passes input validation.
		//Otherwise throw an exception.
		for (Edge edge : e) {
			if (edge.getWeight() < 0 || 
				edge.getSource().equals(edge.getDestination()) || 
				!v.contains(edge.getSource()) || 
				!v.contains(edge.getDestination())) {
					throw new IllegalArgumentException();
			}

			//If an edge has the same dest 
			//and the same source throw an exception 
			//if their weights are not equal
			for (Edge comp : e) {
				if (comp.getSource().equals(edge.getSource()) &&
					comp.getWeight() != edge.getWeight() &&
					comp.getDestination().equals(edge.getDestination())) {
						throw new IllegalArgumentException();
				}
			}
		}
		// Adds edges to the map.
		for (Edge edge: e) {
			edgeList.add(edge);
			Vertex parent = edge.getSource();
			if (!graph.containsKey(parent)) {
				graph.put(parent, new HashSet<Edge>());
			} 			
			graph.get(parent).add(edge);
		}
		
		// Adds vertexes to the map
		vertList = new HashSet<Vertex>();
		for (Vertex vert: v) {
			vertList.add(vert);
			if (!graph.containsKey(vert)) {
				graph.put(vert, new HashSet<Edge>());
			}		
		}
	}

	/**
	 * Return the collection of vertices of this graph
	 * 
	 * @return the vertices as a collection (which is anything iterable)
	 */
	@Override
	public Collection<Vertex> vertices() {
		return vertList;
	}

	/**
	 * Return the collection of edges of this graph
	 * 
	 * @return the edges as a collection (which is anything iterable)
	 */
	@Override
	public Collection<Edge> edges() {
		return edgeList;
	}

	/**
	 * Return a collection of vertices adjacent to a given vertex v. i.e., the
	 * set of all vertices w where edges v -> w exist in the graph. Return an
	 * empty collection if there are no adjacent vertices.
	 * 
	 * @param v
	 *            one of the vertices in the graph
	 * @return an iterable collection of vertices adjacent to v in the graph
	 * @throws IllegalArgumentException
	 *             if v does not exist.
	 */
	@Override
	public Collection<Vertex> adjacentVertices(Vertex v) {
		if (!vertList.contains(v) || v == null) {
			throw new IllegalArgumentException();
		}
		Set<Edge> edges = graph.get(v);
		Collection<Vertex> adjacent = new HashSet<Vertex>();
		for (Edge edge : edges) {
			adjacent.add(edge.getDestination());
		}
		return adjacent;
	}

	/**
	 * Test whether vertex b is adjacent to vertex a (i.e. a -> b) in a directed
	 * graph. Assumes that we do not have negative cost edges in the graph.
	 * 
	 * @param a
	 *            one vertex
	 * @param b
	 *            another vertex
	 * @return cost of edge if there is a directed edge from a to b in the
	 *         graph, return -1 otherwise.
	 * @throws IllegalArgumentException
	 *             if a or b do not exist.
	 */
	@Override
	public int edgeCost(Vertex a, Vertex b) {
		if (a == null || !vertList.contains(a) || b == null || !vertList.contains(b)) {
			throw new IllegalArgumentException();
		}
		Set<Edge> edges = graph.get(a);
		for (Edge edge : edges) {
			if (edge.getDestination() == b) {
				return edge.getWeight();
			}
		}
		return -1;
	}

	/**
	 * Returns the shortest path from a to b in the graph, or null if there is
	 * no such path. Assumes all edge weights are nonnegative. Uses Dijkstra's
	 * algorithm.
	 * 
	 * @param a
	 *            the starting vertex
	 * @param b
	 *            the destination vertex
	 * @return a Path where the vertices indicate the path from a to b in order
	 *         and contains a (first) and b (last) and the cost is the cost of
	 *         the path. Returns null if b is not reachable from a.
	 * @throws IllegalArgumentException
	 *             if a or b does not exist.
	 */
	public Path shortestPath(Vertex a, Vertex b) {

		// YOUR CODE HERE (you might comment this out this method while doing
		// Part 1)

	}

}
