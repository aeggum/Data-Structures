import java.util.ArrayList;

/**
 * This class will be utilized as the driver class for any or all objects or 
 * algorithms throughout the code.  It originally was built only for sorting, 
 * but I plan on adding implementations for various other things.
 * @author eggum
 *
 */
public class Driver {
	static int ARRSIZE = 300;
	public static void main(String[] args) {
		sorts();
		
		//TODO: Add implementations for different things

	}


	/**
	 * This method should be called when you want to run all of the sorting 
	 * algorithms that I've created.  There is bubble, optimizedBubble, 
	 * selection, insertion, merge, comb, heap, quick, and java's own sort, 
	 * all timed for comparison's sake.  More are coming, soon. 
	 */
	public static void sorts() {
		//load the file of integers, and fill up an ArrayList of ints
		String[][] arr = IO.loadFile(IO.writeFile(0, ARRSIZE), ARRSIZE);
		ArrayList<Integer> ints = IO.stringToIntList(arr);
		Integer[] intArray = IO.stringToIntArray(arr);


		//load the file of doubles, and fill up the ArrayList of doubles
		arr = IO.loadFile(IO.writeFile(0.0, ARRSIZE), ARRSIZE);
		ArrayList<Double> doubles = IO.stringToDoubleList(arr);
		Double[] doubleArray = IO.stringToDoubleArray(arr);

		//Create an instance of the object I will be using for sorting.
		Sorts sort = new Sorts(ints, doubles, null, ARRSIZE);
		sort.doSorts(intArray, doubleArray);
	}

	
	/**
	 * A very simple helper method that prints out a String 2D array.
	 * @param array The 2D array of Strings that you want to print
	 */
	public static void print2DArray(String[][] array) {
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[0].length; j++) {
				System.out.print(array[i][j]);
				if (j != array[0].length - 1)
					System.out.print(", ");
			}
			System.out.println();
		}
	}
}
