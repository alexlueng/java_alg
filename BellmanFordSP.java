public class BellmanFordSP {
	private double[] distTo;
	private DirectedEdge[] edgeTo;
	private boolean[] onQ;
	private Queue<Integer> queue;
	private int cost;
	private Iterable<DirectedEdge> cycle;

	public BellmanFordSP(EdgeWeightedDigraph G, int s) {
		distTo = new double[G.V()];
		edgeTo = new DirectedEdge[G.V()];
		onQ = new boolean[G.V()];
		queue = new Queue<Integer>();
		for(int i = 0; i < G.V(); i++) {
			distTo[i] = Double.POSITIVE_INFINITY;
		}
		distTo[s] = 0.0;
		onQ[s] = true;
		while(!queue.isEmpty() && !this.hasNegativeCycle()) {
			int v = queue.enqueue();
			onQ[v] = false;
			relax(G, v);
		}
	}

	private relax(EdgeWeightedDigraph G, int v) {
		for(DirectedEdge e: G.adj(v)) {
			int w = e.to();
			if(distTo[w] > distTo[v] + e.weight()) {
				distTo[w] = distTo[v] + e.weight();
				edgeTo[w] = e;
				if(!onQ[w]) {
					queue.enqueue(w);
					onQ[w] = true;
				}
			}
			if(cost++ % G.V() == 0) {
				findNegativeCycle();
			}
		}
	}
}