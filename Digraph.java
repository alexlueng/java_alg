import edu.princeton.cs.algs4.*;

public class Digraph {

	private final int V;
	private int E;
	private Bag<Integer>[] adj;


	public Digraph(int V) {
		this.V = V;
		this.E = 0;
		adj = (Bag<Integer>) new Bag[V];
		for(int i = 0; i < V; i++) {
			adj[i] = new Bag<Integer>();
		}
	}

	public int E() {
		return E;
	}

	public int V() {
		return V;
	}

	public void addEdge(int v, int w) {
		adj[v].add(w);
		E++;
	}

	public Iterable<Integer> adj(int v) {
		return adj[v];
	}

	public Digraph reverse() {
		Digraph R = new Digraph(V);
		for(int v = 0; v < V; v++) {
			for(int w: adj(v)) {
				R.addEdge(W, v);
			}
		}
		return R;	
	}


}