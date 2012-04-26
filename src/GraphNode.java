import java.util.*;
public class GraphNode<T> implements Comparable<T> {
	private T data;
	private List<GraphNode<T>> succ = new ArrayList<GraphNode<T>>();
	private boolean visited;
	
	/**
	 * Constructs a new GraphNode using the data passed in. 
	 * @param data information to represent with the GraphNode
	 */
	public GraphNode (T data) {
		this.data = data;
		this.visited = false;
	}
	
	public void setVisited(boolean visit) { 
		this.visited = visit;
	}
	
	
	public boolean isVisited() {
		return this.visited;
	}
	
	
	public T getData() {
		return this.data;
	}
	
	
	public List<GraphNode<T>> getSuccessors() {
		return this.succ;
	}
	
	
	
	public void addSuccessor(GraphNode<T> node) {
		succ.add(node);
	}
	
	
	public int compareTo(T o) {
		if (o instanceof String) {
			String name = o.toString();
			return this.getData().toString().compareTo(name);
		}
		else if (o instanceof GraphNode<?>) {
			String name = o.toString();
			return this.getData().toString().compareTo(name);
		}
		else 
			throw new UnsupportedOperationException();
	}
	
}
