
public class StringQuick {
	private static final int CUTOFF = 15;
	
	public static void sort(String[] a) {
		sort(a, 0, a.length - 1, 0);
		assert(isSorted(a));
	}
	
	private static int charAt(String s, int d) {
		assert(d >= 0 && d <= s.length());
		if (d == s.length()) return -1;
		return s.charAt(d);
	}
	
	private static void sort(String[] a, int low, int high, int d) {
		if (high <= low + CUTOFF) {
			insertion(a, low, high, d);
			return;
		}
		
		int lt = low, gt = high;
		int v = charAt(a[low], d);
		int i = low + 1;
		while (i <= gt) {
			int t = charAt(a[i], d);
			if (t < v) exchange(a, lt++, i++);
			else if (t > v) exchange(a, i, gt--);
			else i++;
		}
		
		sort(a, low, lt-1, d);
		if (v >= 0) sort(a, lt, gt, d+1);
		sort(a, gt+1, high, d);
	}
	
	private static void insertion(String[] a, int low, int high, int d) {
		for (int i = low; i <= high; i++) {
			for (int j = i; j > low && less(a[j], a[j-1], d); j--)
				exchange(a, j, j-1);
		}
	}
	
	private static void exchange(String[] a, int i, int j) {
		String temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}
	
	
	private static boolean less(String v, String w, int d) {
		assert(v.substring(0, d).equals(w.substring(0, d)));
		return v.substring(d).compareTo(w.substring(d)) < 0;
	}
	
	
	private static boolean isSorted(String[] a) {
		for (int i = 1; i < a.length; i++) 
			if (a[i].compareTo(a[i-1]) < 0) return false;
		return true;
	}
	
	public static void main(String[] args) {
		String[] a = {"foobar", "foo", "too", "you", "aaa", "adam eggum", "adam", "eggum" };
		sort(a);
		
		for (int i = 0; i < a.length; i++)
			System.out.println(a[i]);
	}
}
