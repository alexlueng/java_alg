import edu.princeton.cs.algs4.*;


/*public class NFA {


	private char[] re; // match
	private Digraph G; // epsilon transfer
	private int M;

	public NFA(String regexp) {
		Stack<Integer> ops = new  Stack<Integer>();
		re = regexp.toCharArray();
		M = re.length;
		G = new Digraph(M+1);

		for(int i = 0; i < M; i++) {
			int lp = i;
			if(re[i] == '(' || re[i] == '|') {
				ops.push(i);
			} else if (re[i] == ')') {
				int or = ops.pop();
				if(re[or] == '|') {
					lp = ops.pop();
					G.addEdge(lp, or+1);
					G.addEdge(or, i);
				} else {
					lp = or;
				}
			}
			if(i < M-1 && re[i+1] == '*') {
				G.addEdge(lp, i+1);
				G.addEdge(i+1, lp);
			}
			if(re[i] == '(' || re[i] == '*' || re[i] == ')') {
				G.addEdge(i, i+1);
			}
		}
	}

	public boolean recognizes(String txt) {
		Bag<Integer> pc = new Bag<Integer>();
		DirectedDFS dfs = new DirectedDFS(G, 0);
		for(int v = 0; v < G.V(); v++) {
			if(dfs.marked(v)) {
				pc.add(v);
			}
		}

		for(int i = 0; i < txt.length(); i++) {
			Bag<Integer> match = new Bag<Integer>();
			for(int v : pc) {
				if (v < M) {
					if(re[v] == txt.charAt(i) || re[v] == '.') {
						match.add(v+1);
					}
				}
			}
			pc = new Bag<Integer>();
			dfs = new DirectedDFS(G, match);
			for(int v = 0; v < G.V(); v++) {
				if(dfs.marked(v)) {
					pc.add(v);
				}
			}
		}
		for(int v : pc) {
			if (v == M) {
				return true;
			}
		}
		return false;
	}

	public static void main(String[] args) {
		String regexp = "(." + args[0] + ".)";
		NFA nfa = new NFA(regexp);
		while(StdIn.hasNextLine()) {
			String txt = StdIn.readLine();
			if(nfa.recognizes(txt)) {
				StdOut.println(txt);
			}
		}
	}

}*/
public class NFA { 

    private Digraph G;         // digraph of epsilon transitions
    private String regexp;     // regular expression
    private int M;             // number of characters in regular expression

    /**
     * Initializes the NFA from the specified regular expression.
     *
     * @param  regexp the regular expression
     */
    public NFA(String regexp) {
        this.regexp = regexp;
        M = regexp.length();
        Stack<Integer> ops = new Stack<Integer>(); 
        G = new Digraph(M+1); 
        for (int i = 0; i < M; i++) { 
            int lp = i; 
            if (regexp.charAt(i) == '(' || regexp.charAt(i) == '|') 
                ops.push(i); 
            else if (regexp.charAt(i) == ')') {
                int or = ops.pop(); 

                // 2-way or operator
                if (regexp.charAt(or) == '|') { 
                    lp = ops.pop();
                    G.addEdge(lp, or+1);
                    G.addEdge(or, i);
                }
                else if (regexp.charAt(or) == '(')
                    lp = or;
                else assert false;
            } 

            // closure operator (uses 1-character lookahead)
            if (i < M-1 && regexp.charAt(i+1) == '*') { 
                G.addEdge(lp, i+1); 
                G.addEdge(i+1, lp); 
            } 
            if (regexp.charAt(i) == '(' || regexp.charAt(i) == '*' || regexp.charAt(i) == ')') 
                G.addEdge(i, i+1);
        }
        if (ops.size() != 0)
            throw new IllegalArgumentException("Invalid regular expression");
    } 

    /**
     * Returns true if the text is matched by the regular expression.
     * 
     * @param  txt the text
     * @return <tt>true</tt> if the text is matched by the regular expression,
     *         <tt>false</tt> otherwise
     */
    public boolean recognizes(String txt) {
        DirectedDFS dfs = new DirectedDFS(G, 0);
        Bag<Integer> pc = new Bag<Integer>();
        for (int v = 0; v < G.V(); v++)
            if (dfs.marked(v)) pc.add(v);

        // Compute possible NFA states for txt[i+1]
        for (int i = 0; i < txt.length(); i++) {
            if (txt.charAt(i) == '*' || txt.charAt(i) == '|' || txt.charAt(i) == '(' || txt.charAt(i) == ')')
                throw new IllegalArgumentException("text contains the metacharacter '" + txt.charAt(i) + "'");

            Bag<Integer> match = new Bag<Integer>();
            for (int v : pc) {
                if (v == M) continue;
                if ((regexp.charAt(v) == txt.charAt(i)) || regexp.charAt(v) == '.')
                    match.add(v+1); 
            }
            dfs = new DirectedDFS(G, match); 
            pc = new Bag<Integer>();
            for (int v = 0; v < G.V(); v++)
                if (dfs.marked(v)) pc.add(v);

            // optimization if no states reachable
            if (pc.size() == 0) return false;
        }

        // check for accept state
        for (int v : pc)
            if (v == M) return true;
        return false;
    }

    /**
     * Unit tests the <tt>NFA</tt> data type.
     */
    public static void main(String[] args) {
        String regexp = "(" + args[0] + ")";
        String txt = args[1];
        NFA nfa = new NFA(regexp);
        StdOut.println(nfa.recognizes(txt));
    }

} 









