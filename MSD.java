public class MSD {
	private static int R = 256;
	private static final int M = 15; // switch to insert sort
	private static String[] aux;

	private static int charAt(String s, int d) {
		if(d < s.length()) {
			return s.charAt(d);
		} else {
			return -1;
		}
	}
	public static void sort(String[] a) {
		int N = a.length;
		aux = new String[N];
		sort(a, 0, N-1, 0);
	}
	private static void sort(String[] a, int low, int high, int d) {
		// use the dth as the key to sort a[low] - a[high]
		if(high <= low + M) {
			Insertion.sort(a, low, high, d);
			return;
		}
		int[] count = new int[R+2]; // frequency

		for (int i = low; i <= high; i++) {
			count[charAt(a[i], d) + 2]++;
		}

		for (int r = 0; r < R+1; r++) { // turn the frequency into index
			count[r+1] += count[r];
		}

		for (int i = low; i <= high; i++) { // data classify
			aux[count[charAt(a[i], d) + 1]++] = a[i];
		}

		for (int i = low; i <= hi; i++) { // write back
			a[i] = aux[i - low];
		}
		// sort recursively using every character
		for (int r = 0; r < R; r++) {
			sort(a, low + count[r], low + count[r+1] - 1, d+1);
		}

	}
}