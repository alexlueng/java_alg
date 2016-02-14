import java.util.NoSuchElementException;

public class RedBlackBST<Key extends Comparable<Key>, Value> {

	private static final boolean RED = true;
	private static final boolean BLACK = false;

	private Node root;


	private class Node {
		private Key key;
		private Value val;
		private Node left, right;
		private boolean color;
		private int N;

		public Node(Key key, Value val, boolean color, int N) {
			this.key = key;
			this.val = val;
			this.color = color;
			this.N = N;
		}
	}

	public RedBlackBST() {

	}

	// Node helper methods

	private boolean isRed(Node x) {
		
		if(x == null) {
			return false;
		}
		return x.color == RED;

	}

	private int size(Node x) {
		if(x == null) {
			return 0;
		}
		return x.N;
	}

	public int size() {
		return size(root);
	}

	public boolean isEmpty() {
		return root == null;
	}



}