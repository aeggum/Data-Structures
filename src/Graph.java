/**
 * Graph class that uses the GraphNode class.  Originally made for a project in CS367.
 * 
 */
import java.util.*;
public class Graph {
	private TreeMap<String, GraphNode<String>> nodes;

	/**
	 * Constructs a new Graph object
	 */
	public Graph() {
		nodes = new TreeMap<String, GraphNode<String>>();
	}

	/**
	 * Adds the given label to the graph as a node. If the given label is already
	 * in the graph as a node, then nothing will happen
	 * @param label - label of newly added node
	 * @throws IllegalArgumentException if label is null
	 */
	public void addNode(String label) {
		if (label == null) throw new IllegalArgumentException();
		GraphNode<String> node = new GraphNode<String>(label);
		if (!nodes.containsKey(label))
			this.nodes.put(label, node);
	}

	/**
	 * Return true iff a node with the given label is in the graph
	 * @param label - label of node to check
	 * @return true if and only if there is a node with the given label in the graph
	 * @throws IllegalArgumentException if label is null
	 */
	public boolean hasNode(String label) {
		if (label == null) throw new IllegalArgumentException();
		if (nodes.get(label) != null)
			return true;
		return false;
	}

	/**
	 * Remove the node with the given label and all edges connected
	 * to it from the graph.
	 * @param label - label of node to remove
	 * @throws IllegalArgumentException if label is null or there is no node with this label in the graph
	 */
	public void removeNode(String label) {
		if (label == null) throw new IllegalArgumentException();
		if (nodes.get(label) == null)
			throw new IllegalArgumentException();
		nodes.remove(label);

		//below, need to remove label from any other nodes list of successors
		NavigableSet<String> keys = this.nodes.navigableKeySet();
		for (String key: keys) {
			if (nodes.get(key).getSuccessors() != null) {
				for (GraphNode<String> succ: nodes.get(key).getSuccessors()) {
					if (succ.getData().equals(label))
						nodes.get(key).getSuccessors().remove(succ);
				} //close inner for
			} 
		} //close outer for
	} //close removeNode


	/**
	 * Add to the graph the specified directed edge from the node with the label label1
	 * to the node with the label label2. If the edge is already in the graph, just return.
	 * @param label1 - label of source node of the edge
	 * @param label2 - label of target node of the edge
	 * @throws IllegalArgumentException if either label is null or if there are no nodes in the graph with the given labels
	 */
	public void addEdge(String label1, String label2) {
		if (label1 == null || label2 == null) throw new IllegalArgumentException();
		if (nodes.containsKey(label1) && nodes.containsKey(label2))
			nodes.get(label1).addSuccessor(nodes.get(label2));
		else 
			throw new IllegalArgumentException();
	}


	/**
	 * Return true iff the graph contains an edge from the node with the label
	 * label1 to the node with the label label2.
	 * @param label1 - label of source node of the edge
	 * @param label2 - label of target node of the edge
	 * @return true if and only if there is an edge from the node labeled label1 to the node labeled label2
	 * @throws IllegalArgumentException if either label is null or if there are no nodes in the graph with the given labels
	 */
	public boolean hasEdge(String label1, String label2) {
		if (label1 == null || label2 == null) throw new IllegalArgumentException();
		if (nodes.containsKey(label1) && nodes.containsKey(label2))
			return nodes.get(label1).getSuccessors().contains((nodes.get(label2)));
		else 
			throw new IllegalArgumentException();
	}

	private void unVisitNodes() {
		NavigableSet<String> keys = this.nodes.navigableKeySet();
		for (String key: keys )
			nodes.get(key).setVisited(false);
	}

	/**
	 * Return a list of node labels in the order they are visited using a depth-first
	 * search starting at the node with the given label. Whenever a node has multiple successors,
	 * the successors are visited in alphabetical order.
	 * @param label - label of the start node
	 * @return list of the node labels in DFS order
	 * @throws IllegalArgumentException if label is null or there is no node with this label in the graph
	 */
	public List<String> dfs(String label) {
		if (label == null || nodes.get(label) == null)
			throw new IllegalArgumentException();
		unVisitNodes();
		List<String> labels = new ArrayList<String>();
		//calls auxiliary dfs
		return dfs(nodes.get(label), labels);
	}

	/**
	 * Helper method that does the work for the public dfs method above. It is
	 * passed a GraphNode and a list of the labels as they are visited, and it 
	 * recursively goes through and adds labels to the list in alphabetical order, 
	 * but only if they haven't been visited yet. Alphabetized using a TreeSet object. 
	 * @param node A GraphNode containing String objects
	 * @param labels The list of labels that are reached while doing dfs (alphabetized)
	 * @return A List of String objects that contains all labels that are hit after a dfs
	 */
	private List<String> dfs(GraphNode<String> node, List<String> labels) {
		if (node.isVisited())
			return labels;

		node.setVisited(true);
		labels.add(node.getData());

		//adding into set assures alphabetical ordering
		TreeSet<String> set = new TreeSet<String>();
		for (GraphNode<String> n : node.getSuccessors()) {
			set.add(n.getData());
		}
		for (String m : set ) {
			if (m != null) {
				if (!nodes.get(m).isVisited()) {
					dfs(nodes.get(m), labels);
				}
			}
		}
		return labels;
	} //close dfs 


	/**
	 * Return a list of node labels in the order they are visited using a breadth-first search starting
	 * at the node with the given label. Whenever a node has multiple successors, the successors are
	 * visited in alphabetical order.
	 * @param label - label of the start node
	 * @return list of the node labels in BFS order
	 * @throws IllegalArgumentException if label is null or there is no node with this label in the graph
	 */
	public List<String> bfs(String label) {
		if (label == null || nodes.get(label) == null)
			throw new IllegalArgumentException();
		unVisitNodes();
		//calls auxiliary bfs
		return bfs(nodes.get(label));
	}


	/**
	 * Helper bfs method that does the work for the public bfs. Here, a variety of 
	 * objects are used to ensure alphabetical ordering. An ArrayList is used as a 
	 * queue, and in the end a List of String labels (containing every node that is
	 * hit after doing a bfs) is returned.
	 * @param node A GraphNode with String labels
	 * @return A List of String labels
	 */
	private List<String> bfs(GraphNode<String> node) {
		List<GraphNode<String>> queue = new ArrayList<GraphNode<String>>();
		List<String> nodes = new ArrayList<String>();
		List<GraphNode<String>> succ;
		TreeSet<String> set = new TreeSet<String>();
		queue.add(node);
		node.setVisited(true);

		while (!queue.isEmpty()) {
			GraphNode<String> curr = queue.remove(0);
			succ = curr.getSuccessors();
			nodes.add(curr.getData());

			for (GraphNode<String> n : succ) {
				set.add(n.getData());
			}
			for (String successor : set) {

				if (!this.nodes.get(successor).isVisited()) {
					queue.add(this.nodes.get(successor));
					this.nodes.get(successor).setVisited(true);
				}
			}
		}
		return nodes;
	} //close bfs

	/**
	 * Return the number of nodes in the graph.
	 * @return number of nodes in the graph
	 */
	public int size() {
		return this.nodes.size();
	}

	/**
	 * Return the number of edges in the graph.
	 * @return number of edges in the graph
	 */
	public int numEdges() {
		int edges = 0;
		NavigableSet<String> keys = this.nodes.navigableKeySet();

		for (String key: keys )
			edges += nodes.get(key).getSuccessors().size();

				return edges;
	}

	/**
	 * Return true iff the graph has size 0 (i.e., no nodes or edges).
	 * @return true if and only if the graph is empty
	 */
	public boolean isEmpty() {
		return (size() == 0);
	}

	/**
	 * Return an iterator over the node labels in the graph.
	 * The labels are returned in sorted (alphabetical) order.
	 * @return iterator over the node labels in the graph (in alphabetical order).
	 */
	public Iterator<String> iterator() {
		//actually very simple, just use the navigableKeySet iterator.
		Iterator<String> iter = nodes.navigableKeySet().iterator();
		return iter;
	}





}

