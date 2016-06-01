import edu.princeton.cs.algs4.*;

public class Huffman {

	// alphabet size of extended ASCII
	private static final int R = 256;

	private Huffman() {}

	private static class Node implements Comparable<Node> {


		private char ch;
		private int freq;
		private final Node left, right;

		Node(char ch, int freq, Node left, Node right) {
			this.ch = ch;
			this.freq = freq;
			this.left = left;
			this.right = right;
		}

		public boolean isLeaf() {
			assert ((left == null) && (right == null)) || ((left != null) && (right != null)); 
			return left == null && right == null;
		}

		public int compareTo(Node that) {
			return this.freq - that.freq;
		}


	}

	private static String[] buildCode(Node root) {
		String[] st = new String[R];
		buildCode(st, root, "");
		return st;
	}

	private static void buildCode(String[] st, Node x, String s) {
		if(x.isLeaf()) {
			st[x.ch] = s;
			return;
		}
		buildCode(st, x.left, s + '0');
		buildCode(st, x.right, s + '1');
	}



	public static Node buildTrie(int[] freq) {
		MinPQ<Node> pq = new MinPQ<Node>();
		for(char c = 0; c < R; c++) {
			if(freq[c] > 0) {
				pq.insert(new Node(c, freq[c], null, null));
			}
		}
		while(pq.size() > 1) {
			Node x = pq.delMin();
			Node y = pq.delMin();
			Node parent = new Node('\0', x.freq + y.freq, x, y);
			pq.insert(parent);
		}
		return pq.delMin();
	}

	private static void writeTrie(Node x) {
		if(x.isLeaf()) {
			BinaryStdOut.write(true);
			BinaryStdOut.write(x.ch);
			return;
		}
		BinaryStdOut.write(false);
		writeTrie(x.left);
		writeTrie(x.right);
	}

	private static Node readTrie() {
		if(BinaryStdIn.readBoolean()) {
			return new Node(BinaryStdIn.readChar(), 0, null, null);
		}
		return new Node('\0', 0, readTrie(), readTrie());
	}


	public static void compress() {

		// read input
		String s = BinaryStdIn.readString();
		char[] input = s.toCharArray();

		// tabulate frequence counts
		int[] freq = new int[R];
		for(int i = 0; i < input.length; i++) {
			freq[input[i]]++;
		}

		// build the huffman tree
		Node root = buildTrie(freq);

		// build the compile table recursively
		String[] st = new String[R];
		buildCode(st, root, "");

		// print the trie for decoder
		writeTrie(root);

		// print number of bytes in original uncompressed message
		BinaryStdOut.write(input.length);

		// use huffman to deal the input
		for(int i = 0; i < input.length; i++) {
			String code = st[input[i]];
			for(int j = 0; j < code.length(); j++) {
				if(code.charAt(j) == '1') {
					BinaryStdOut.write(true);
				} else if(code.charAt(j) == '0') {
					BinaryStdOut.write(false);
				} else throw new IllegalStateException("Illegal state.");
			}
		}
		BinaryStdOut.close();
	}	

	public static void expand() {
		Node root = readTrie();
		int N = BinaryStdIn.readInt();
		for(int i = 0; i < N; i++) {
			Node x = root;
			while(!x.isLeaf()) {
				if(BinaryStdIn.readBoolean()) {
					x = x.left;
				} else {
					x = x.right;
				}
			}
			BinaryStdOut.write(x.ch);
		}
		BinaryStdOut.close();
	}

	public static void main(String[] args) {
		if(args[0].equals("-")) {
			compress();
		} else if (args[0].equals("+")) {
			expand();
		} else throw new IllegalStateException("Illegal command line input");
	}


}











