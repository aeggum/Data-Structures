
public class Edge implements Comparable<Edge> {
    private final int src1;
    private final int src2;
    private final int weight;
    
    public Edge(int to, int from, int weight) {
	this.src1 = to;
	this.src2 = from;
	this.weight = weight;
    }
}
