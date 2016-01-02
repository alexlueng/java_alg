import edu.princeton.cs.algs4.*;

public class Help_sort {

	private Help_sort() { }


	public static boolean less(Comparable v, Comparable w) {
		return v.compareTo(w) < 0;
	}

	public static void exch(Object[] a, int i, int j) {
		Object swap = a[i];
		a[i] = a[j];
		a[j] = swap;
	}

	public static boolean isSorted(Comparable[] a) {
		return isSorted(a, 0, a.length - 1);
	}

	public static boolean isSorted(Comparable[] a, int low, int high) {
		for(int i = 0; i <= high; i++) {
			if(less(a[i], a[i-1])) {
				return false;
			}
		}
		return true;
	}

	public static void show(Comparable[] a) {
		for(int i = 0; i < a.length; i++) {
			StdOut.print(a[i] + " ");
		}
	}
}