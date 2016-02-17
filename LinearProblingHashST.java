public class LinearProblingHashST<Key, Value> {
	
	private int N; // total of key-value
	private int M = 16; // size of table
	private Key[] keys;
	private Value[] vals; // use two arrays to store the key-value

	public LinearProblingHashST() {
		keys = (Key[]) new Object[M];
		vals = (Value[]) new Object[M];
	}

	private int hash(Key key) {
		return (key.hashCode() & 0x7fffffff) % M;
	}

	private void resize() {

	}

	public void put(Key key, Value val) {
		if(N >= M/2) {
			resize(M*2);
		}
		int i;
		for(i = hash(key); keys[i] != null; i = (i + 1) % M) {
			if(keys[i].equals(key)) {
				vals[i] = val;
				return;
			}
		}
		keys[i] = key;
		vals[i] = val;
		N++;
	}

	public Value get(Key key) {
		for(int i = hash(key); keys[i] != null; i = (i + 1) % M) {
			if(keys[i].equals(key)) {
				return vals[i];
			}
		}
		return null;
	}

	public void delete(Key key) {
		if(!contains(key)) {
			return;
		}
		int i = hash(key);
		while(!key.equals(keys[i])) {
			i = (i + 1) % M;
		}
		keys[i] = null;
		vals[i] = null;


		i = (i + 1) % M;
		while(keys[i] != null) {
			Key keyToRedo = keys[i];
			Value valToRedo = vals[i];
			keys[i] = null;
			vals[i] = null;
			N--;
			put(keyToRedo, valToRedo);
			i = (i + 1) % M;
		}
		N--;
		if(N > 0 && N == M/8) {
			resize(M/2);
		}
	}
}