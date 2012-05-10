
public class Edge implements Comparable<Edge> {
    private final int src1;
    private final int src2;
    private final int weight;
    
    public Edge(int to, int from, int weight) {
	this.src1 = to;
	this.src2 = from;
	this.weight = weight;
    }

    public int getWeight() {
	return this.weight;
    }

    public int getNode() {
	return src1;
    }
    
    public int getOtherNode(int node) {
	if (node == src1) 
	    return src2;
	else if (node == src2)
	    return src1;
	else
	    return src2;
    }

    public int compareTo(Edge that) {
	if (this.getWeight() < that.getWeight()) 
	    return -1;
	else if (this.getWeight() > that.getWeight()) 
	    return 1;
	else
	    return 0;
    }


    public String toString() {
	return "" + src1 + ", " + src2 + ", " + weight;
    }

    
}
