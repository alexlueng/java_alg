import edu.princeton.cs.algs4.*;

public class BinarySearchST<Key extends Comparable<Key>, Value> {
	private Key[] keys;
	private Value[] vals;
	private int N;
	public BinarySearchST(int capacity) {
		keys = (Keys[]) new Comparable[capacity];
		vals = (Value[]) new Object[capacity];
	}

	public int size() {
		return N;
	}

	public Value get(Key Key) {
		if(isEmpty()) {
			return null;
		}
		int i = rank(key);
		if(i < N && keys[i].compareTo(key) == 0) {
			return vals[i];
		} else {
			return null;
		}
	}

	public int rank(Key key) {
		int low = 0;
		int high = N - 1;

		while(low <= high) {
			int mid = low + (high - low) / 2;
			int cmp = keys[mid].compareTo(key);
			if(cmp < 0) {
				high = mid - 1;
			} else if(cmp > 0) {
				low = mid + 1;
			} else {
				return mid;
			}
			return low;
		}

	}

	public void put(Key key, Value val) {
		// Search key, if succeed, update the value and return, if not then new one
		int i = rank(key);
		if(i < N && keys[i].compareTo(key) == 0) {
			vals[i] = val;
			return;
		}
		for(int j = N; j > i; j--) {
			keys[j] = keys[j-1];
			vals[j] = vals[j-1];
		}
		keys[i] = key;
		vals[i] = val;
		N++;
	}

	public void delete() {

	}

}