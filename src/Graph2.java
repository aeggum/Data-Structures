import java.util.*;

public class Graph2 {
	private final int numNodes;
	private int numEdges;
	private ArrayList<ArrayList<Edge>> adjacentEdges;

	public Graph2(int numNodes) {
		this.numNodes = numNodes;
		this.numEdges = 0;
		adjacentEdges = new ArrayList<ArrayList<Edge>>();
		for (int i = 0; i < numNodes; i++) 
			adjacentEdges.add(new ArrayList<Edge>());
	}

	public Graph2(int numNodes, int numEdges) {
		this(numNodes);
		if (numEdges < 0) 
			throw new RuntimeException("Number of Edges must be nonnegative");

		for (int i = 0; i < numEdges; i++) {
			int v = (int) Math.random() * numNodes;
			int w = (int) Math.random() * numNodes;
			double weight = Math.round(100 * Math.random());
			Edge e = new Edge(v, w, (int) weight);
			addEdge(e);
		}
	}

	public int getNumNodes() {
		return this.numNodes;
	}

	public int numEdges() {
		return this.numEdges;
	}

	public void addEdge(Edge edge) {
		int node1 = edge.getNode();
		int node2 = edge.getOtherNode(node1);
		for (ArrayList<Edge> edgeList: adjacentEdges) {
			//Old code did nothing here
		}

		adjacentEdges.get(node1).add(edge);
		adjacentEdges.get(node2).add(edge);
		numEdges++;
	}


	public ArrayList<Edge> adjacentEdges(int node) {
		return adjacentEdges.get(node);
	}


	public Iterator<Edge> edges() {
		ArrayList<Edge> list = new ArrayList<Edge>();
		for (int i = 0; i < numNodes; i++) {
			for (Edge e: adjacentEdges(i)) {
				if (e.getOtherNode(i) > i) {
					list.add(e);
				}
			}
		}

		return list.iterator();
	}


	public String toString() {
		String NEWLINE = System.getProperty("line.separator");
		StringBuilder s = new StringBuilder();
		s.append(numNodes + " " + numEdges + NEWLINE);
		for (int v = 0; v < numNodes; v++) {
			s.append(v + ": ");
			for (Edge e : adjacentEdges.get(v)) {
				s.append(e + ", ");
			}
			s.append(NEWLINE);
		}
		return s.toString();
	}

}
