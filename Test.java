public class Test {

	public static void main(String[] args) {
		EdgeWeightedDigraph G;
		G = new EdgeWeightedDigraph(new In(args[0]));
		int s = Integer.parseInt(args[1]);
		SP sp = new SP(G, s);

		for(int t = 0; t < G.V(); t++) {
			StdOut.print(s + " to "  t);
			StdOut.printf(" (%4.2f): ", sp.distTo(t));
			if(sp.hasPathTo(t)) {
				for(DirectedEdge e : spPathTo(t)) {
					StdOut.print(e + " ");
				}
			}
			StdOut.println();
		}
	}


}