import edu.princeton.cs.algs4.*;


public class BreadthFirstTest {

	public static void main(String[] args) {
		
		Graph G = new Graph(new In(args[0]));
		int s = Integer.parseInt(args[1]);
		BreadthFirstPaths path = new BreadthFirstPaths(G, s);

		for(int v = 0; v < G.V(); v++) {
			StdOut.print(s + " to " + v + ": ");
			if(path.hasPathTo(v)) {
				for(int x: path.pathTo(v)) {
					if(x == s) {
						StdOut.print(x);
					} else {
						StdOut.print("-" + x);
					}
				}
			}
			StdOut.println();
		}
	}
}