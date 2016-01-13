import edu.princeton.cs.algs4.*;
import java.util.NoSuchElementException;

/*
* Usage: Base binary search api implements
* Author: Alex Lueng
* Last Modify: 2016-01-12
*/

public class BinarySearchST<Key extends Comparable<Key>, Value> {
	private static final int INIT_CAPACITY = 2;
	private Key[] keys;
	private Value[] vals;
	private int N = 0;

	public BinarySearchST() {
        this(INIT_CAPACITY);
    }

	public BinarySearchST(int capacity) {
		keys = (Key[]) new Comparable[capacity];
		vals = (Value[]) new Object[capacity];
	}

	public void resize(int capacity) {
		assert capacity >= N;
		Key[] tempk = (Key[]) new Comparable[capacity];
		Value[] tempv = (Value[]) new Object[capacity];

		for(int i = 0; i < N; i++) {
			tempk[i] = keys[i];
			tempv[i] = vals[i];
		}
		vals = tempv;
		keys = tempk;
	}

	public int size() {
		return N;
	}

	public boolean isEmpty() {
		return size() == 0;
	}

	/*
	* Does this symbol table contains the given key?
	* @param key the key
	* @return true if this symbol table contains the key and false otherewise
	* @throws NullPointerException if key is null
	*/

	public boolean contains(Key key) {
		if(key == null) {
			throw new NullPointerException("argument to contains() is null");
		}
		return get(key) != null;
	}

	public Value get(Key key) {
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

	public void put(Key key, Value val) {
		// Search key, if succeed, update the value and return, if not then new one

		if(key == null) {
			throw new NullPointerException("first argument to put() is null");
		}

		if(val == null) {
			delete(key);
			return;
		}

		int i = rank(key);
		if(i < N && keys[i].compareTo(key) == 0) {
			vals[i] = val;
			return;
		}

		if(N == keys.length) {
			resize(keys.length * 2);
		}

		for(int j = N; j > i; j--) {
			keys[j] = keys[j-1];
			vals[j] = vals[j-1];
		}
		keys[i] = key;
		vals[i] = val;
		N++;

		assert check();
	}

	public int rank(Key key) {

		if(key == null) {
			throw new NullPointerException("key to rank() is null");
		}

		int low = 0;
		int high = N - 1;

		while(low <= high) {
			int mid = low + (high - low) / 2;
			int cmp = keys[mid].compareTo(key);
			if(cmp < 0) {
				low = mid + 1;
			} else if(cmp > 0) {
				high = mid - 1;
			} else {
				return mid;
			}
		}
		return low;
	}



	public void delete(Key key) {
		if(key == null) {
			throw new NullPointerException("key to delete() is null");
		}
		int i = rank(key);
		// key is not in the table
		if(i == N && keys[i].compareTo(key) != 0) {
			return;
		}
		for(int j = i; j < N-1; j++) {
			keys[j] = keys[j+1];
			vals[j] = vals[j+1];
		}
		N--;
		keys[N] = null;
		vals[N] = null;
		if(N > 0 && N == keys.length/4) {
			resize(keys.length / 2);
		}
		assert check();
	}

	public void delMin() {
		if(isEmpty()) {
			throw new NoSuchElementException("symbol table underflow error");
		}
		delete(min());
	}

	public void delMax() {
		if(isEmpty()) {
			throw new NoSuchElementException("symbol table underflow error");
		}
	}

	public Key min() {
		return keys[0];
	}

	public Key max() {
		return keys[N-1];
	}

	public Key select(int k) {
		if(k < 0 || k >= N) {
			return null;
		}
		return keys[k];
	}

	public Key ceiling(Key key) {
		if(key == null) {
			throw new NullPointerException("argument to ceiling() is null");
		}
		int i = rank(key);
		if(i == N) {
			return null;
		}
		return keys[i];
	}

	public Key floor(Key key) {
		if(key == null) {
			throw new NullPointerException("argument to floor() is null");
		}
		int i = rank(key);
		if(i < N && key.compareTo(keys[i]) == 0) {
			return keys[i];
		}
		if(i == 0) {
			return null;
		} else {
			return keys[i-1];
		}
	}

	public Iterable<Key> keys() {
		//StdOut.print(min() +" " +max());
		return keys(min(), max());
	}


	public Iterable<Key> keys(Key low, Key high) {

		if(low == null) {
			throw new NullPointerException("first argument to size() is null");
		}
		if(high == null) {
			throw new NullPointerException("second argument to size() is null");
		}

		NodeQueue<Key> q = new NodeQueue<Key>();

		if(low == null) {
			throw new NullPointerException("first argument to size() is null");
		}
		if(high == null) {
			throw new NullPointerException("second argument to size() is null");
		}

		if(low.compareTo(high) > 0) {
			return q;
		}

		for(int i = rank(low); i < rank(high); i++) {
			q.enqueue(keys[i]);
		}
		if(contains(high)) {
			q.enqueue(keys[rank(high)]);
		}

		return q;
	}

	private boolean check() {
		return isSorted() && rankCheck();
	}

	private boolean isSorted() {
		for(int i = 1; i < size(); i++) {
			if(keys[i].compareTo(keys[i-1]) < 0) {
				return false;
			}
		}
		return true;
	}

	private boolean rankCheck() {
		for(int i = 0; i < size(); i++) {
			if(i != rank(select(i))) {
				return false;
			}
		}
		for(int i = 0; i < size(); i++) {
			if(keys[i].compareTo(select(rank(keys[i]))) != 0) {
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) { 
        BinarySearchST<String, Integer> st = new BinarySearchST<String, Integer>();
        for (int i = 0; !StdIn.isEmpty(); i++) {
            String key = StdIn.readString();
            st.put(key, i);
/*            StdOut.println(st.min());
            StdOut.println(st.max());*/
        }
       for (String s : st.keys())
           StdOut.println(s + " " + st.get(s));
/*        int N = st.size();
        for(int i = 0 ; i < N; i++) {
        	StdOut.println(st.min());
        	st.delMin();
        }*/

    }

}
