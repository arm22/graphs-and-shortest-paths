/** @author Geoff Gray, Austin Meyers
 * @UWNetID gegray, arm38
 * @studentID 1463717, 1228316
 * @email gegray@uw.edu, arm38@uw.edu
 */

import java.util.*;

/**
 * A representation of a graph. Assumes that we do not have negative cost edges
 * in the graph.
 */
public class MyGraph implements Graph {
	private Map<Vertex, ArrayList<Edge>> graph;
	private List<Edge> edges;

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
		graph = new HashMap<Vertex, ArrayList<Edge>>();
		edges = new ArrayList<Edge>();
		
		for(Vertex vert: v) {
			if (!graph.containsKey(vert)) {
				graph.put(vert, new ArrayList<Edge>());
			}
		}
		add(e);
	}
	/**
	 * Adds edges into the global list of edges
	 * 
	 * @param e
	 *            a collection of the edges in this graph
	 */
	private void add(Collection<Edge> e) {
		for (Edge edge: e) {
			
			if (!graph.containsKey(edge.getSource()) || !graph.containsKey(edge.getDestination())) {
				throw new IllegalArgumentException();
			}
			
			if (edge.getWeight() < 0) {
				throw new IllegalArgumentException();
			}
			
			boolean found = false;
			for (Edge validate : graph.get(edge.getSource())) {
				
				if (edge.getDestination().equals(validate.getDestination()) && edge.getWeight() != validate.getWeight()) {
					throw new IllegalArgumentException();
				}
				
				if (edge.getDestination().equals(validate.getDestination()) && edge.getWeight() == validate.getWeight()) {
					found = !found;
				}
			}
			if (!found) {
				edges.add(edge);
				graph.get(edge.getSource()).add(edge);
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
		return graph.keySet();
	}

	/**
	 * Return the collection of edges of this graph
	 * 
	 * @return the edges as a collection (which is anything iterable)
	 */
	@Override
	public Collection<Edge> edges() {
		return edges;
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
		if (!graph.containsKey(v)) {
			throw new IllegalArgumentException();
		}
		
		List<Vertex> nodes = new ArrayList<Vertex>();
		for (Edge edge : graph.get(v)) {
			nodes.add(edge.getDestination());
		}
		return nodes;
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
		if (a == null || b == null) {
			throw new IllegalArgumentException();
		}
		for (Edge edge : graph.get(a)) {
			if (edge.getDestination().equals(b))
				return edge.getWeight();
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
		if (!graph.containsKey(a) || !graph.containsKey(b))
			throw new IllegalArgumentException();
		List<Vertex> vertexes = new ArrayList<Vertex>();
		if (a.equals(b)) {
			vertexes.add(a);
			return new Path(vertexes, 0);
		}
		for (Vertex vert: graph.keySet()) {
			vert.dist = Integer.MAX_VALUE;
			vert.visited = false;
		}
		PriorityQueue<Vertex> queue = new PriorityQueue<Vertex>();
		a.dist = 0;
		queue.add(a);
		vertexes = dijkstra(queue, b, graph.keySet());
		return new Path(vertexes, b.dist);
	}
	
	/**
	 * Uses Dijkstra's algorithm to find the shortest path
	 * between 2 nodes. Returns a list of the vertexes with their paths
	 * 
	 * @param queue
	 *            the priority queue
	 * @param node
	 *            the vertex
	 * @param keys
	 * 			  the set of vertexes' keys
	 * @return a list of vertexes with their respective paths to the desired vertex
	 */
	private List<Vertex> dijkstra(PriorityQueue<Vertex> queue, Vertex node, Set<Vertex> keys) {
		while (!queue.isEmpty()) {
			Vertex curr = queue.poll();
			curr.visited = true;

			for (Edge edge : graph.get(curr)) {
				Vertex next = null;
				for (Vertex vert: keys) {
					if (vert.equals(edge.getDestination())) {
						next = vert;
						break;
					}
				}
				if (!next.visited) {
					int node1 = curr.dist + edge.getWeight();
					int node2 = next.dist;
					if (node1 < node2) {
						queue.remove(next);
						next.dist = node1;
						next.path = curr;
						queue.add(next);
						if (next.equals(node)) {
							node.path = next.path;
							node.dist = next.dist;
						}
					}
				}
			}
		}
		
		List<Vertex> pathList = new ArrayList<Vertex>();
		for (Vertex vert = node; vert != null; vert = vert.path)
			pathList.add(vert);
		Collections.reverse(pathList);
		return pathList;
	}
}
