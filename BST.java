import edu.princeton.cs.algs4.*;

public class BST<Key extends Comparable<Key>, Value> {

	private Node root;

	private class Node {
		private Key key;
		private Value val;
		private Node left, right;
		private int N; // the amount of the nodes

		public Node(Key key, Value val, int N) {
			this.key = key;
			this.val = val;
			this.N = N;
		}
	}
	public int size() {
		return size(root);
	}
	private int size(Node x) {
		if(x == null) {
			return 0;
		} else {
			return x.N;
		}
	}

	public Value get(Key key) {
		return get(root, key);
	}

	private Value get(Node x, Key key) {
		if(x == null) {
			return null;
		}
		int cmp = key.compareTo(x.key);
		if(cmp > 0) {
			return get(x.right, key);
		} else if(cmp < 0) {
			return get(x.left, key);
		} else {
			return x.val;
		}
	}


	public void put(Key key, Value val) {
		root = put(root, key, val);
	}

	private Node put(Node x, Key key, Value val) {
		if(x == null) {
			return new Node(key, val, 1);
		}
		int cmp = key.compareTo(x.key);
		if(cmp < 0) {
			x.left = put(x.left, key, val);
		} else if(cmp > 0) {
			x.right = put(x.right, key, val);
		} else {
			x.val = val;
		}
		x.N = size(x.left) + size(x.right) + 1;
		return x;
	}

	public Key min() {
		return min(root).key;
	}
// 一直向左走，直到遇到空值
	private Node min(Node x) {
		if(x.left == null) {
			return x;
		}
		return min(x.left);
	}
// 一直向右走，直到遇到空值
	public Key max() {
		return max(root).key;
	}

	private Node max(Node x) {
		if(x.right == null) {
			return x;
		}
		return max(x.right);
	}

	public Key floor(Key key) {
		Node x = floor(root, key);
		if(x == null) {
			return null;
		}
		return x.key;
	}

	private Node floor(Node x, Key key) {
		if(x == null) {
			return null;
		}
		int cmp = key.compareTo(x.key);
		if(cmp == 0) {
			return x;
		}
		if(cmp < 0) {
			return floor(x.left, key);
		}
		Node t = floor(x.right, key);
		if(t != null) {
			return t;
		} else {
			return x;
		}
	}

	public Key ceiling(Key key) {
		Node x = ceiling(root, key);
		if(x == null) {
			return null;
		}
		return x.key;
	}

	private Node ceiling(Node x, Key key) {
		if(x == null) {
			return null;
		}
		int cmp = key.compareTo(x.key);
		if(cmp == 0) {
			return x;
		}
		if(cmp < 0) {
			Node t = ceiling(x.left, key);
			if(t != null) {
				return t;
			} else {
				return x;
			}
		}
		return ceiling(x.right, key);
	}


	public Key select(int k) {
		return select(root, k).key;
	}

	private Node select(Node x, int k) {
		if(x == null) {
			return null;
		}
		int t = size(x.left);
		if(t > k) {
			return select(x.left, k);
		} else if(t < k) {
			return select(x.right, k-t-1);
		} else {
			return x;
		}
	}

	public int rank(Key key) {
		return rank(root, key);
	}

	private int rank(Node x, Key key) {
		if(x == null) {
			return 0;
		}
		int cmp = key.compareTo(x.key);
		if(cmp < 0) {
			return rank(x.left, key);
		} else if(cmp > 0) {
			return 1 + size(x.left) + rank(x.right, key);
		} else {
			return size(x.left);
		}
	}

	public void deleteMin() {
		root = deleteMin(root);
	}
// 找到最小值，然后将它的右节点（它必然会有右节点）作为它的父节点的左子树，这样可以保持树的有序性
// 若有需要，还要更新节点数
	private Node deleteMin(Node x) {
		if(x.left == null) {
			return x.right;
		}
		x.left = deleteMin(x.left);
		x.N = size(x.left) + size(x.right) + 1;
		return x;
	}

	public void delete(Key key) {
		root = delete(root, key);
	}
// 对于删除任意节点：
// 1. 将指向即将被删除的结点的链接保存为t
// 2. 将x指向它的后继节点min(x.right)
// 3. 将x的右链接指向deleteMin(x.right)
// 4. 将x的左链接设为t.left
	private Node delete(Node x, Key key) {
		if(x == null) {
			return null;
		}
		int cmp = key.compareTo(x.key);
		if(cmp < 0) {
			x.left = delete(x.left, key);
		} else if(cmp > 0) {
			x.right = delete(x.right, key);
		} else {
			if(x.right == null) {
				return x.left;
			}
			if(x.left == null) {
				return x.right;
			}
			Node t = x;
			x = min(t.right);
			x.right = deleteMin(t.right);
			x.left = t.left;
		}
		x.N = size(x.left) + size(x.right) + 1;
		return x;
	}

	public Iterable<Key> keys() {
		return keys(min(), max());
	}

	public Iterable<Key> keys(Key low, Key high) {
		Queue<Key> queue = new Queue<Key>();
		keys(root, queue, low, high);
		return queue;
	}

	private void keys(Node x, Queue<Key> queue, Key low, Key high) {
		if( x == null) {
			return;
		}
		int cmplow = low.compareTo(x.key);
		int cmphigh = high.compareTo(x.key);
		if(cmplow < 0) {
			keys(x.left, queue, low, high);
		}
		if(cmplow <= 0 && cmphigh >= 0) {
			queue.enqueue(x.key);
		}
		if(cmphigh > 0) {
			keys(x.right, queue, low, high);
		}
	}

	public int height() {
		return height(root);
	}
// 计算树的最大深度
	private int height(Node x) {
		if(x == null) {
			return -1;
		}
		return 1 + Math.max(height(x.left), height(x.right));
	}

}








