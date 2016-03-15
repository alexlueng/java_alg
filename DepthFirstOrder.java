import edu.princeton.cs.algs4.*;

public class DepthFirstOrder {
	
	private boolean[] marked;

	private int[] pre;
	private int[] post;

	private Queue<Integer> preorder;
	private Queue<Integer> postorder;
	private Stack<Integer> reversePostorder;

	private int preCounter;
	private int postCounter;

	public DepthFirstOrder(Digraph G) {

		pre = new int[G.V()];
		post = new int[G.V()];

		preorder = new Queue<Integer>();
		postorder = new Queue<Integer>();
		reversePostorder = new Stack<Integer>();

		marked = new boolean[G.V()];
		for(int v = 0; v < G.V(); v++) {
			if(!marked[v]) {
				dfs(G, v);
			}
		}
	}	

	public DepthFirstOrder(EdgeWeightedDigraph G) {
		pre = new int[G.V()];
		post = new int[G.V()];

		preorder = new Queue<Integer>();
		postorder = new Queue<Integer>();
		reversePostorder = new Stack<Integer>();

		marked = new boolean[G.V()];
		for(int v = 0; v < G.V(); v++) {
			if(!marked[v]) {
				dfs(G, v);
			}
		}
	}

	private void dfs(Digraph G, int v) {
		marked[v] = true;
		pre[v] = preCounter++;
		preorder.enqueue(v);
		//marked[v] = true;
		for(int w: G.adj(v)) {
			if(!marked[w]) {
				dfs(G, w);
			}
		}
		postorder.enqueue(v);
		post[v] = postCounter++;
		reversePostorder.push(v);
	}

	private void dfs(EdgeWeightedDigraph G, int v) {
		marked[v] = true;
		pre[v] = preCounter++;
		preorder.enqueue(v);
		//marked[v] = true;
		for(DirectedEdge e: G.adj(v)) {
			int w = e.to();
			if(!marked[w]) {
				dfs(G, w);
			}
		}
		postorder.enqueue(v);
		post[v] = postCounter++;
		reversePostorder.push(v);
	}

	public int pre(int v) {
		return pre[v];
	}

	public int post(int v) {
		return post[v];
	}



	public Iterable<Integer> pre() {
		return preorder;
	}

	public Iterable<Integer> post() {
		return postorder;
	}

	public Iterable<Integer> reversePost() {
		return reversePostorder;
	}

/*	public static void main(String[] args) {
		In in = new In(args[0]);
		Digraph G = new Digraph(in);

		DepthFirstOrder dfs = new DepthFirstOrder(G);

		StdOut.println("   v  pre post");
        StdOut.println("--------------");
        for (int v = 0; v < G.V(); v++) {
            StdOut.printf("%4d %4d %4d\n", v, dfs.pre(v), dfs.post(v));
        }

        StdOut.print("Preorder:  ");
        for (int v : dfs.pre()) {
            StdOut.print(v + " ");
        }
        StdOut.println();

        StdOut.print("Postorder: ");
        for (int v : dfs.post()) {
            StdOut.print(v + " ");
        }
        StdOut.println();

        StdOut.print("Reverse postorder: ");
        for (int v : dfs.reversePost()) {
            StdOut.print(v + " ");
        }
        StdOut.println();

	}*/

}