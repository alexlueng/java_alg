import edu.princeton.cs.algs4.*;
import java.util.NoSuchElementException;
import java.util.InputMismatchException;


public class Digraph {

	private final int V;
	private int E;
	private Bag<Integer>[] adj;
	private int[] indegree;


	public Digraph(int V) {
		if(V < 0) {
			throw new IllegalArgumentException("Number of vertices in a Digraph must be nonnegative");
		}
		this.V = V;
		this.E = 0;
		indegree = new int[V];
		adj = (Bag<Integer>[]) new Bag[V];
		for(int i = 0; i < V; i++) {
			adj[i] = new Bag<Integer>();
		}
	}

	public Digraph(In in) {
		try {
			this.V = in.readInt();
			if(V < 0) {
				throw new IllegalArgumentException("Number of vertices in a Digraph must be nonnegative");
			}
			indegree = new int[V];
			adj = (Bag<Integer>[]) new Bag[V];
			for(int i = 0; i < V; i++) {
				adj[i] = new Bag<Integer>();
			}
			int E = in.readInt();
			if(E < 0) {
				throw new IllegalArgumentException("Number of edge in a Digraph must be nonnegative");
			}
			for(int i = 0; i < E; i++) {
				int v = in.readInt();
				int w = in.readInt();
				addEdge(v, w);
			}
		} catch(NoSuchElementException e) {
			throw new InputMismatchException("Invalid input format in Digraph constructor");
		}
	}

	public Digraph(Digraph G) {
		this(G.V());
		this.E = G.E();
		for(int v = 0; v < G.V(); v++) {
			this.indegree[v] = G.indegree(v);
		}
		for(int v = 0; v < G.V(); v++) {
			Stack<Integer> reverse = new Stack<Integer>();
			for(int w: G.adj[v]) {
				reverse.push(w);
			}
			for(int w: G.adj(v)) {
				adj[v].add(w);
			}
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


	private void validateVertex(int v) {
		if(v < 0 || v >= V) {
			throw new IndexOutOfBoundsException("vertex " + v + " is not between 0 and " + (V-1));
		}
	}

	public int indegree(int v) {
		validateVertex(v);
		return indegree[v];
	}

	public Digraph reverse() {
		Digraph R = new Digraph(V);
		for(int v = 0; v < V; v++) {
			for(int w: adj(v)) {
				R.addEdge(w, v);
			}
		}
		return R;	
	}

	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append(V + " vertices, " + E + " edges " + '\n');
		for(int v = 0; v < V; v++) {
			s.append(String.format("%d: ", v));
			for(int w: adj[v]) {
				s.append(String.format("%d ", w));
			}
			s.append('\n');
		}
		return s.toString();
	}

}