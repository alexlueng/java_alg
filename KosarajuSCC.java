import edu.princeton.cs.algs4.*;


public class KosarajuSCC {

	private boolean[] marked;
	private int[] id;
	private int count;

	public KosarajuSCC(Digraph G) {
		marked = new boolean[G.V()];
		id = new int[G.V()];
		DepthFirstOrder order = new DepthFirstOrder(G.reverse());
		for(int s: order.reversePost()) {
			if(!marked[s]) {
				dfs(G, s);
				count++;
			}
		}
	}

	private void dfs(Digraph G, int v) {
		marked[v] = true;
		id[v] = count;
		for(int w: G.adj(v)) {
			if(!marked[w]) {
				dfs(G, w);
			}
		}
	}

	public boolean stronglyConnected(int v, int w) {
		return id[v] == id[w];
 	}

 	public int id(int v) {
 		return id[v];
 	}

 	public int count() {
 		return count;
 	}


 	public static void main(String[] args) {
 		In in = new In(args[0]);
 		Digraph G = new Digraph(in);
 		KosarajuSCC scc = new KosarajuSCC(G);

 		int M = scc.count;
 		StdOut.println(M + " components");

 		Queue<Integer>[] components = (Queue<Integer>[]) new Queue[M];
 		for(int i = 0; i < M; i++) {
 			components[i] = new Queue<Integer>();
 		}

 		for(int v = 0; v < G.V(); v++) {
 			components[scc.id(v)].enqueue(v);
 		}

 		for(int i = 0; i < M; i++) {
 			for(int v: components[i]) {
 				StdOut.print(v + " ");
 			}
 			StdOut.println();
 		}
 	}

}