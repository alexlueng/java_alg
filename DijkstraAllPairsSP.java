import edu.princeton.cs.algs4.*;

public class DijkstraAllPairsSP {
	private DijkstraSP[] all;
	public DijkstraAllPairsSP(EdgeWeightedDigraph G) {
		all = new DijkstraSP[G.V()];
		for(int v = 0; v < G.V(); v++) {
			all[v] = DijkstraSP(G, v);
		}
	}
	public Iterable<Edge> path(int s, int t) {
		return all[s].pathTo(t);
	}
	public double dist(int s, int t) {
		return all[s].distTo(t);
	} 
}