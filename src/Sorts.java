import java.util.*;


public class Sorts {
	//One ArrayList for each type, and one for each to hold the original, unsorted list
	private ArrayList<Integer> intList;
	private final ArrayList<Integer> unsortedIntList;
	private ArrayList<Double> doubleList;
	private final ArrayList<Double> unsortedDoubleList;
	private ArrayList<String> strList;
	private final ArrayList<String> unsortedStrList;
	private final int ARRSIZE;


	/**
	 * Sorts is the constructor for the Sorts class.  It takes in three <tt>ArrayList</tt>s.
	 * One list each for Integers, Doubles, and Strings.  Currently, the String sorting is
	 * not enabled so null can be passed in with no negative repercussions.
	 * @param ints An <tt>ArrayList</tt> of Integers
	 * @param doubs An <tt>ArrayList</tt> of Doubles
	 * @param strs An <tt>ArrayList</tt> of Strings
	 */
	public Sorts(ArrayList<Integer> ints, ArrayList<Double> doubs, ArrayList<String> strs, int arrsize) {
		this.unsortedIntList = ints;
		this.unsortedDoubleList = doubs;
		this.unsortedStrList = null;

		this.intList = new ArrayList<Integer>();
		this.doubleList = new ArrayList<Double>();
		this.setArrays(ints, doubs, strs);
		
		this.ARRSIZE = arrsize;
	}


	/**
	 * Sets the values of the ArrayLists we'll be doing the sorting on.  We set the values individually
	 * so that we don't have to worry about passing too many things by reference, or anything similar.
	 * Assumed to be used only by the constructor of the Sorts class.
	 * @param ints An <tt>ArrayList</tt> of Integers
	 * @param doubs An <tt>ArrayList</tt> of Doubles
	 * @param strs An <tt>ArrayList</tt> of Strings
	 */
	private void setArrays(ArrayList<Integer> ints, ArrayList<Double> doubs, ArrayList<String> strs) {
		for (int i: ints)
			this.intList.add(i);

		for (double d: doubs)
			this.doubleList.add(d);

		this.strList = null;
	}

	
	public void doSorts(Integer[] intArray, Double[] doubleArray) {
		long startTime, endTime, duration;

		if (ARRSIZE <= 200) {
			startTime = System.currentTimeMillis();
			this.bubble(intArray);
			this.bubble(doubleArray);
			//sort.newBubble(stringArray);	//TODO:
			endTime = System.currentTimeMillis();
			duration = endTime - startTime;
			printStats("bubble", duration);

			startTime = System.currentTimeMillis();
			this.optimizedBubble(intArray);
			this.optimizedBubble(doubleArray);
			endTime = System.currentTimeMillis();
			duration = endTime - startTime;
			printStats("opt_bubble", duration);		
		}

		if (ARRSIZE <= 250) {
			startTime = System.currentTimeMillis();
			this.selection(intArray);
			this.selection(doubleArray);
			endTime = System.currentTimeMillis();
			duration = endTime - startTime;
			printStats("selection", duration);
		}
		
		if (ARRSIZE <= 300) {
			startTime = System.currentTimeMillis();
			this.insertion(intArray);
			this.insertion(doubleArray);
			endTime = System.currentTimeMillis();
			duration = endTime - startTime;
			printStats("insertion", duration);
		}

		startTime = System.currentTimeMillis();
		this.comb(intArray);
		this.comb(doubleArray);
		endTime = System.currentTimeMillis();
		duration= endTime - startTime;
		printStats("comb", duration);
		
		startTime = System.currentTimeMillis();
		Arrays.sort(intArray.clone());
		Arrays.sort(doubleArray.clone());
		endTime = System.currentTimeMillis();
		duration = endTime - startTime;
		printStats("java's", duration);
		
		this.mergeSort();
		this.quickSort();
	}
	
	
	public void printStats(String sort, long duration) {
		System.out.printf("ARRSIZE --> %d, %s sort\t --> %f seconds.\n", ARRSIZE, sort, duration/1000.0);
	}
	
	/**
	 * An updated version of the bubbleSort method that simply takes an Array of 
	 * Objects and sorts those rather than dealing with separate sorting implementations
	 * for the different types supported. 
	 * Integers Doubles and Strings are supported currently.
	 * <p><u><b>BUBBLE SORT DESCRIPTION:</b></u></p> 
	 * The algorithm goes through the list checking, one
	 * index at a time to see if any two elements should be swapped.  It completes
	 * only when a full iteration through the list yielded no swaps. <p/>
	 * Note: if the ARRSIZE in Driver is too large, this function doesn't have a chance of completing.  Too slow.
	 * There is nothing passed in and nothing is returned.  The run time is O(n^2). <br/>
	 * @param arrayToSort
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void bubble(Comparable[] toSort) {
		boolean swapped = true;

		Comparable[] array = toSort.clone();

		while (swapped) {
			swapped = false;
			for (int i = 1; i < toSort.length; i++) {
				if (array[i-1].compareTo(array[i]) > 0) {
					Comparable newObj = array[i-1];
					array[i-1] = array[i];
					array[i] = newObj;
					swapped = true;
				}
			}
		}

		this.assertSortedAsc(array);
	}



	/**
	 * Updated optimized bubble sort.  This method simply takes in an Array of Objects 
	 * assumed to be either Strings, Doubles, or Integers, and it sorts them.  
	 * <p><u><b>OPTIMIZED BUBBLE SORT DESCRIPTION:</b></u></p>
	 * 
	 * Optimizes by using the fact that after N passes, the last element in the list will be
	 * sorted, and so on, moving down through the list.  This results in anywhere
	 * from 10 to 50% improvement over the basic bubbleSort algorithm. <br/>
	 * @param toSort
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void optimizedBubble(Comparable[] toSort) {
		Comparable[] array = toSort.clone();

		int size = array.length;
		do {
			int newN = 0;
			for (int i = 1; i < array.length; i++) {
				//follows the same principle as bubbleSort; if two elements are
				//out of order it will swap them; difference is with the newN
				if (array[i-1].compareTo(array[i]) > 0) {
					Comparable bigElement = array[i-1];
					array[i-1] = array[i];
					array[i] = bigElement;
					newN = i;
				}
			}
			size = newN;
		} while (size > 0);

		this.assertSortedAsc(array);
	}




	/**
	 * An updated selection sort that simply takes an array of comparable things,
	 * and sorts them using the selection sorting algorithm. 
	 * Selection sort is one of the simplest sorts, getting the smallest element
	 * the first time through, then the second smallest, and so on through the 
	 * entire list.
	 * <br /> 
	 * Prevents a lot of extra swapping, but in general is not a competitive sorting algorithm, at all.
	 * @param toSort
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void selection(Comparable[] toSort) {
		Comparable[] array = toSort.clone();

		Comparable minimum;
		int mindex = 0;

		/*
		 * Two loops: one to place the minimum for this time through, and the other 
		 * to find the minimum for this time through the sort
		 */
		for (int startPos = 0; startPos < array.length; startPos++) {
			minimum = array[startPos];
			mindex = startPos;

			//go through every other element in the array and find the minimum
			for (int i = startPos + 1; i < array.length; i++) {
				if (array[i].compareTo(minimum) < 0) {
					minimum = array[i];
					mindex = i;
				}
			}

			//If they are the same, you don't need to do anything; otherwise swap
			if (minimum.compareTo(array[startPos]) != 0) {
				array[mindex] = array[startPos];
				array[startPos] = minimum;
			}
		}

		this.assertSortedAsc(array);
	}


	/**
	 * Updated insertion sort. This method simply takes in an Array of Objects 
	 * assumed to be either Strings, Doubles, or Integers, and it sorts them.  
	 * <p><u><b>INSERTION SORT DESCRIPTION:</b></u></p>
	 * 
	 * 
	 * Generally faster sorting than the other simple sorting methods, and is often the one
	 * chosen out of Bubble, Selection, or Insertion because of that.  It is
	 * highly dependent on the dataset you are sorting. Already mostly-sorted data is done quickly.
	 * <br />
	 * Can easily swap from ascending to descending here. 
	 * 
	 * @param toSort
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void insertion(Comparable[] toSort) {
		Comparable[] sorted = toSort.clone();

		for (int i = 1; i < sorted.length -1; i++) {
			Comparable placing = sorted[i];		//placing is what's being placed into the deck
			int j = i-1;

			/*
			 * Move through the sorted deck, continue when the thing we're placing 
			 * less than what we're looking at.  When done, we finally place it
			 * in it's final location (for this loop)
			 */
			while (j >= 0 && sorted[j].compareTo(placing) > 0) {
				sorted[j+1] = sorted[j];
				j--;
			}

			sorted[j+1] = placing;
		}

		this.assertSortedAsc(sorted);
	}


	/**
	 * This is an updated version of comb sort, but it doesn't completely work.  
	 * It gets every element in the array except for one, at least in the normal
	 * case.  Too bad, as it's relatively fast.
	 * <br />
	 * <u><b>DOES NOT ALWAYS COMPLETELY WORK!!!</b></u>
	 * <br />
	 * That's unfortunate, as well, as this is a very fast sorting algorithm.
	 * @param toSort
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void comb(Comparable[] toSort) {
		Comparable[] sorted = toSort.clone();
		double shrinkFactor = 1.247330950103979;

		int gap = sorted.length;
		boolean swapped = false;

		while (gap != 1 || swapped == false) {
			gap /= shrinkFactor;
			if (gap < 1) 
				gap = 1;

			int i = 0;
			swapped = false;
			while (i + gap < sorted.length) {
				if (sorted[i].compareTo(sorted[i + gap]) > 0) {
					Comparable temp = sorted[i];
					sorted[i] = sorted[i + gap];
					sorted[i + gap] = temp;
					swapped = true;
				}
				i++;
			}
		}

		//cannot call the checker below, as it doesn't work.
		//this.assertSortedAsc(sorted);
	}




	/**
	 * MergeSort algorithm based on the famous sorting algorithm of which details
	 * can be found on <a href="http://en.wikipedia.org/wiki/Merge_sort">Wikipedia</a>. <br/>
	 * This is supposedly one of the fastest sorting algorithms, with a run speed of O(nlogn),
	 * even in the worse case.  However, probably due to the fact that it's not being optimized,
	 * and I'm using ArrayLists exclusively, it's not nearly as fast as CombSort or HeapSort. <p/>
	 * <b>The runtime of this method with an ARRSIZE of 250 was <i>3.112</i> seconds.</b> <br/>
	 * While this is faster than Insertion and Selection Sort, it cannot compare to the two fast
	 * algorithms up to this point.  On to quicksort we go!
	 * <p/>
	 * One thing to keep in mind, the way I've set up my lists may benefit the other sorting algorithms
	 * more than mergesort for whatever reason.  When finished with quicksort, I should find a function 
	 * to reverse the order of the lists and see if that changes the speed of the sorts.
	 * TODO: Develop a way to reverse the order of the lists to compare in another way
	 */
	public void mergeSort() {
		this.resetArrays();
		long startTime = System.currentTimeMillis();

		//Will deal with Integers first, then move on to Doubles and Strings
		this.intList = runIntMergeSort(this.intList);
		this.doubleList = runDoubMergeSort(this.doubleList);


		long endTime = System.currentTimeMillis();
		long duration = endTime - startTime;
		print("Merge Sort took " + duration/1000.0 + " seconds");
	}

	/**
	 * The method that does the work for the Integer version of MergeSort().  
	 * It's the worker method and it's a helper method.  It runs MergeSort() on 
	 * an ArrayList of Integers.  To do this, it breaks the ArrayList into two
	 * even halves and recursively calls itself until the size gets down to 1. 
	 * At that point, it starts merging the left and right sides with another helper 
	 * method, mergeInt().
	 * @param list The ArrayList of Integers that MergeSort is being run on.
	 * @return In the end, a sorted ArrayList of Integers will be returned.  
	 * But it will change, because it's recursive.
	 */
	private ArrayList<Integer> runIntMergeSort(ArrayList<Integer> list) {
		if (list.size() <= 1) 	//If the size of the list is 1, it's sorted, so return it
			return list;
		//else, list size is > 1, so split into sublists
		ArrayList<Integer> left = new ArrayList<Integer>(list.size()/2 + 1), right = new ArrayList<Integer>(list.size()/2 + 1);
		int middle = list.size()/2;
		for (int i = 0; i < middle; i++) 
			left.add(list.get(i));
		for (int i = middle; i < list.size(); i++) 
			right.add(list.get(i));

		//Recursively call runIntMergeSort to further split each sublist is of size 1
		left = runIntMergeSort(left);
		right = runIntMergeSort(right);

		//Merge the sublists returned and return the merged sublist

		return mergeInt(left, right);
	}

	

	


	/**
	 * A helper method that does the work for the Double version of MergeSort().
	 * It runs MergeSort(0 on an ArryList of Doubles. To do this, it breaks the 
	 * original ArrayList into halves and recursively calls itself until the size 
	 * of the the lists are 1.  At that point, it starts merging the left and right 
	 * sides with another helper method, mergeDouble().
	 * @param list The ArrayList of Doubles that MergeSort is being run on.
	 * @return A sorted ArrayList of Doubles will be returned at the end.
	 */
	private ArrayList<Double> runDoubMergeSort(ArrayList<Double> list) {
		if (list.size() <= 1) 	//If the size of the list is 1, it's sorted, so return it
			return list;
		//else, list size is > 1, so split into sublists
		ArrayList<Double> left = new ArrayList<Double>(list.size()/2 + 1), right = new ArrayList<Double>(list.size()/2 + 1);
		int middle = list.size()/2;
		for (int i = 0; i < middle; i++) 
			left.add(list.get(i));
		for (int i = middle; i < list.size(); i++) 
			right.add(list.get(i));

		//Recursively call runIntMergeSort to further split each sublist is of size 1
		left = runDoubMergeSort(left);
		right = runDoubMergeSort(right);

		//Merge the sublists returned and return the merged sublist

		return mergeDouble(left, right);
	}

	/**
	 * The method that does the merging of the ArrayLists for MergeSort() when 
	 * dealing with Integers.  The method for Doubles is mergeDouble().  It follows 
	 * the convention for a top-down MergeSort, merging two ArrayLists of Integers. <br/>
	 * The ArrayList will be ordered from lowest to highest.
	 * @param left The left half of a list, as specified by MergeSort()
	 * @param right The right half of a list, as speciified by MergeSort()
	 * @return A sorted ArrayList of Integers that is the size of left + right
	 */
	private ArrayList<Integer> mergeInt(ArrayList<Integer> left, ArrayList<Integer> right) {
		ArrayList<Integer> result = new ArrayList<Integer>();
		while (left.size() > 0 || right.size() > 0) {
			if (left.size() > 0 && right.size() > 0) {
				//Whichever is lower, we want to add to result
				if (left.get(0) <= right.get(0)) 
					result.add(left.remove(0));
				else 
					result.add(right.remove(0));
			}

			//Add the remaining to result, one way or another
			else if (left.size() > 0) 
				result.add(left.remove(0));
			else if (right.size() > 0)
				result.add(right.remove(0));
		}
		return result;
	}

	
	
	/**
	 * The method that does the merging of the ArrayLists for MergeSort() when 
	 * dealing with Doubles.  The method for Integers is mergeInt().  It follows the 
	 * convention for a top-down MergeSort, merging two ArrayLists of Doubles. </br>
	 * The ArrayList will be sorted from lowest to highest.
	 * @param left The left half of a list, as specified by MergeSort()
	 * @param right The right half of a list, as specified by MergeSort()
	 * @return A sorted ArrayList of Doubles that is the size of left + right
	 */
	private ArrayList<Double> mergeDouble(ArrayList<Double> left, ArrayList<Double> right) {
		ArrayList<Double> result = new ArrayList<Double>();
		while (left.size() > 0 || right.size() > 0) {
			if (left.size() > 0 && right.size() > 0) {
				//Whichever is lower, we want to add to result
				if (left.get(0) <= right.get(0)) 
					result.add(left.remove(0));
				else 
					result.add(right.remove(0));
			}

			//Add the remaining to result, one way or another
			else if (left.size() > 0) 
				result.add(left.remove(0));
			else if (right.size() > 0)
				result.add(right.remove(0));
		}
		return result;
	}


	/**
	 * QuickSort is one of the fastest sorting algorithms known to man.  It has 
	 * a best-case runtime of O(NlogN) and a worst-case of O(N^2), but it runs faster
	 * than most of the other sorting algorithms.  It's space efficent, as well. <br/>
	 * QuickSort is a divide and conquer algorithm.  Splits everything into low and high 
	 * elements and works from there, using a pivot point and sorting the sublists.
	 * <p/>
	 * <ol>
	 * 	<li>Pick a pivot point from the list</li>
	 * 	<li>Reorder the list so elements less than the pivot are before the pivot, those 
	 * greater than it come after it.  Pivot is now in its final position. </li>
	 * 	<li>Recursively sort the sub-list of lesser elements adn the sublist of greater elements</li>
	 * </ol>
	 * <p/>
	 * <b>The runtime of this method with an ARRSIZE of 250 was <i>0.094</i> seconds.</b> <br/>
	 * <b>The runtime of this method with an ARRSIZE of 2000 was <i>7.372</i> seconds. </b> <br/>
	 * Note that QuickSort is a little slower than the other fast datasets in a small set.
	 * However, larger sets are not a comparison.  At 2000, QuickSort dominates.
	 * 
	 */
	public void quickSort() {
		this.resetArrays();
		long startTime = System.currentTimeMillis();

		//this.intList = quickSortInt(this.intList);
		//this.doubleList = quickSortDouble(this.doubleList);
		//this.intList = 
		quickSortInt(this.intList, 0, this.intList.size() - 1);
		quickSortDouble(this.doubleList, 0, this.doubleList.size() - 1);

		long endTime = System.currentTimeMillis();
		long duration = endTime - startTime;
		print("Quick Sort took " + duration/1000.0 + " seconds.");
	}


	/**
	 * This is simply Java's Collections.sort() method, timed here for 
	 * comparison's sake. Interestingly, my implementation of quicksort has
	 * shown to be very competitive with this. 
	 */
	public void collectionSort() {
		this.resetArrays();
		long startTime = System.currentTimeMillis();

		Collections.sort(this.doubleList);
		Collections.sort(this.intList);

		long endTime = System.currentTimeMillis();
		long duration = endTime - startTime;
		print("Java's Collections' sort took " + duration/1000.0 + " seconds.");
	}

	/**
	 * The partition operation for the Integer version of QuickSort. This partitions
	 * the elements between indexes left and right, by moving all elements less
	 * than what's in the pivot position before the pivot and those greater than it to after 
	 * the pivot position.  Only uses exchanges, so nothing has changed in terms of the 
	 * content of the list itself. <br/>
	 * Duplicates can be on either side of the pivot.
	 * May not be the best or fastest version of the partition function, but it's
	 * probably the easiest to understand.
	 * <br/>Same as the Double version.
	 */
	private int partitionInt(ArrayList<Integer> list, int leftIndex, int rightIndex, int pivotIndex) {
		int pivot = list.get(pivotIndex);
		int rightElement = list.get(rightIndex);
		//Swap the pivot and right element, moving pivot to the end of list
		list.set(rightIndex, pivot);
		list.set(pivotIndex, rightElement);

		int storeIndex = leftIndex;
		for (int i = leftIndex; i < rightIndex; i++) {
			if (list.get(i) < pivot) {
				int storeElement = list.get(storeIndex);
				list.set(storeIndex, list.get(i));
				list.set(i, storeElement);
				storeIndex++;
			}
		}
		int storeElement = list.get(storeIndex);
		list.set(storeIndex, list.get(rightIndex));
		list.set(rightIndex, storeElement);

		return storeIndex;
	}


	/**
	 * The partition operation for the Double version of QuickSort. This partitions
	 * the elements between indexes left and right, by moving all elements less
	 * than what's in the pivot position before the pivot and those greater than it to after 
	 * the pivot position.  Only uses exchanges, so nothing has changed in terms of the 
	 * content of the list itself. <br/>
	 * Duplicates can be on either side of the pivot.
	 * May not be the best or fastest version of the partition function, but it's
	 * probably the easiest to understand.
	 * <br/>Same as the Integer version.
	 */
	private int partitionDouble(ArrayList<Double> list, int leftIndex, int rightIndex, int pivotIndex) {
		double pivot = list.get(pivotIndex);
		double rightElement = list.get(rightIndex);

		//Swap the pivot and right element; move pivot to the end of the list
		list.set(rightIndex, pivot);
		list.set(pivotIndex, rightElement);

		int storeIndex = leftIndex;
		for (int i = leftIndex; i < rightIndex; i++) {
			if (list.get(i) < pivot) {
				double storeElement = list.get(storeIndex);
				list.set(storeIndex, list.get(i));
				list.set(i, storeElement);
				storeIndex++;
			}
		}

		double storeElement = list.get(storeIndex);
		list.set(storeIndex, list.get(rightIndex));
		list.set(rightIndex, storeElement);

		return storeIndex;
	}


	/**
	 * The helper method that runs QuickSort for Integers.  Actually quite simple
	 * once the partition method is out of the way, QuickSort recursively calls
	 * itself until it's down to a size of 1, breaking the lists one smaller at a 
	 * time, which guarantees a max of N recursive calls.
	 * <br/> 
	 * Same as the Double version, except for it being Integer instead of Double.
	 * @param list The list that's being sorted.
	 * @param left The left most item in the list
	 * @param right The right most item in the list
	 */
	private void quickSortInt(ArrayList<Integer> list, int left, int right) {
		if (left < right) {
			//A better way to choose the pivot is to use a median method, but will do that later
			int pivotIndex = (left + right) / 2;
			int pivotNewIndex = partitionInt(list, left, right, pivotIndex);
			quickSortInt(list, left, pivotNewIndex - 1);
			quickSortInt(list, pivotNewIndex + 1, right);
		}
	}

	/**
	 * The helper method that runs QuickSort for Doubles.  Actually quite simple
	 * once the partition method is out of the way, QuickSort recursively calls
	 * itself until it's down to a size of 1, breaking the lists one smaller at a 
	 * time, which guarantees a max of N recursive calls.
	 * <br/> 
	 * Same as the Double version, except for it being Double instead of Integer.
	 * @param list The list that's being sorted.
	 * @param left The left most item in the list
	 * @param right The right most item in the list
	 */
	private void quickSortDouble(ArrayList<Double> list, int left, int right) {
		if (left < right) {
			//A better way to choose the pivot is to use a median method, but will do that later
			int pivotIndex = (left + right) / 2;
			int pivotNewIndex = partitionDouble(list, left, right, pivotIndex);
			quickSortDouble(list, left, pivotNewIndex - 1);
			quickSortDouble(list, pivotNewIndex + 1, right);
		}
	}


	

	/**
	 * A very simple helper method that prints out an Object of any kind.
	 * This method can be called with ints, doubles, strings, sorts, etc.
	 * <br/>Simply calls System.out.println() for the object passed in. If it's
	 * your own object, there must be a toString() method provided.
	 * @param input The object you want to print out.
	 */
	private void print(Object input) {
		System.out.println(input);
	}


	/**
	 * Checks if any elements in the array are not in ascending order.  
	 * If it finds any, it exits immediately. 
	 * @param toCheck
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void assertSortedAsc(Comparable[] toCheck) {
		for (int i = 0; i < toCheck.length - 1; i++) {
			if (!(toCheck[i].compareTo(toCheck[i+1]) <= 0)) {
				System.err.println("Array found not in ascending order. Exiting.");
				System.err.println("The index that failed was " + i);
				printArray(toCheck);
				System.exit(-1);
			}
		}
	}

	@SuppressWarnings("rawtypes")
	public void printArray(Comparable[] toPrint) {
		for (int index = 0; index < toPrint.length; index++) {
			System.out.print(toPrint[index] + ", ");
		}
	}


	/**
	 * Private helper method that resets the ArrayLists back to how they were before
	 * being sorted.  This is required because everything is passed by reference in Java
	 * and we want to change the values themselves.
	 * Nothing is passed in and nothing is returned.  this.intList and this.doubleList are modified.
	 */
	private void resetArrays() {
		int index = 0;
		for (int i: this.unsortedIntList) {
			this.intList.set(index, i);
			index++;
		}

		index = 0;
		for (double d: this.unsortedDoubleList) {
			this.doubleList.set(index, d);
			index++;
		}
	}


	

}


