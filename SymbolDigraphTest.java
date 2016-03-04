import edu.princeton.cs.algs4.*;

public class SymbolDigraphTest {
	public static void main(String[] args) {
		String filename = args[0];
		String delimiter = args[1];
		SymbolDigraph sg = new SymbolDigraph(filename, delimiter);
		Digraph G = sg.G();
/*		while(!StdIn.isEmpty()) {
			String t = StdIn.readLine();
			for(int v: G.adj(sg.index(t))) {
				StdOut.println("	" + sg.name(v));
			}
		}*/
		StdOut.print(G);
	}
}