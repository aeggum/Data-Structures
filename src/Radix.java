
public class Radix {
	private static final int R = 256;					//ASCII alphabet size
	private static final int CUTOFF = 15;				//cutoff to insertion sort

	public static void sort(String[] a) {
		String[] aux = new String[a.length];
		sort(a, 0, a.length - 1, 0, aux);
	}

	private static int charAt(String str, int d) {
		assert d >= 0 && d <= str.length();
		if (d == str.length()) return -1;
		return str.charAt(d);
	}


	private static void sort(String[] a, int low, int high, int d, String[] aux) {
		if (high <= low + CUTOFF) {
			insertion(a, low, high, d);
			return;
		}

		int[] count = new int[R + 2];
		for (int i = low; i <= high; i++) {
			int c = charAt(a[i], d);
			count[c + 2]++;
		}

		for (int r = 0; r < R + 1; r++) 
			count[r+1] += count[r];

		for (int i = low; i <= high; i++) {
			int c = charAt(a[i], d);
			aux[count[c+1]++] = a[i];
		}

		for (int i = low; i <= high; i++)
			a[i] = aux[i - low];

		for (int r = 0; r < R; r++) 
			sort(a, low + count[r], low + count[r+1] -1, d+1, aux);
	}



	private static void insertion(String[] a, int low, int high, int d) {
		for (int i = low; i <= high; i++) {
			for (int j = i; j > low && less(a[j], a[j-1], d); j--)
				exch(a, j, j-1);
		}
	}


	private static void exch(String[] a, int i, int j) {
		String temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}




	private static boolean less(String v, String w, int d) {
		assert(v.substring(0, d).equals(w.substring(0,d)));
		return v.substring(d).compareTo(w.substring(d)) < 0;
	}




	public static void main(String[] args) {
		String[] a = {"foobar", "foo", "too", "you", "aaa", "adam eggum", "adam", "eggum" };
		int N = a.length;
		sort(a);
		for (int i = 0; i < N; i++)
			System.out.println(a[i]);
	}

}
