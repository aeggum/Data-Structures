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

    
}
