
///////////////////////////////////////////////////////////////////////////////
//                  
// Main Class File:  AddressBook.java
// File:             BSTSortedList.java
// Semester:         Fall 2010
//
// Author:           Adam Eggum aeggum@wisc.edu
// CS Login:         eggum
// Lecturer's Name:  Rebecca Hasti
//
// Pair Partner:     Justin Smith
// CS Login:         justins
// Lecturer's Name:  Rebecca Hasti
//
//////////////////////////// 80 columns wide //////////////////////////////////
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

/**
 * BSTSortedList extends the Comparable interface and implements the methods
 * found in SortedListADT. The class includes a constructor as well as the insert,
 * delete, lookup, and findInRange methods.
 *
 * @authors Adam Eggum and Justin Smith
 *
 * @param <K>
 */
public class BSTSortedList<K extends Comparable<K>>  {
	private BSTnode<K> root;  // the root node
	private int numItems;  // the number of items in the sorted list
	List<K> list = new ArrayList<K>(); // the list of items

	// TO DO:
	// Add a no-argument constructor
	// Add your code to implement the Sorted List ADT operations using a binary
	// search tree.
	// You may use any code given in the on-line reading on BSTs.

	/**
	 * A no argument constructor for the BSTSortedList object
	 */
	public BSTSortedList() {
		this.root = null;
		this.numItems = 0;
	}


	/**
	 * Inserts the given key into the BSTSortedList. If the key is already in the
	 * BSTSortedList, a DuplicateException will be thrown.
	 */
	public void insert(K key) throws DuplicateException {
		if (lookup(key) != null)
			throw new DuplicateException();
		
		//TODO: ...
		if(key instanceof Object){
			numItems ++;
			this.root = insert(this.root, key);
		}
	}

	/**
	 * Auxiliary insert method that is called in the the public insert method.
	 * This method will also throw a DuplicateException.  It recursively finds
	 * where the key needs to go in the BSTnode, and then inserts it and returns
	 * the node.
	 * @param n The root of the BSTnode
	 * @param key What you want to be entered into the BSTnode
	 * @return A BSTnode object
	 * @throws DuplicateException if the key is already in the BSTnode; no
	 * duplicates are allowed
	 */
	private BSTnode<K> insert(BSTnode<K> n, K key) throws DuplicateException{
		if (n == null) {
			return new BSTnode<K>(key, null, null);
		}

		if (n.getKey().equals(key)) {
			throw new DuplicateException();
		}

		if (key.compareTo(n.getKey()) < 0) {
			// add k to the left subtree
			n.setLeft(insert(n.getLeft(), key) );
			return n;
		}

		else {
			// add k to the right subtree
			n.setRight(insert(n.getRight(), key) );
			return n;
		}
	}

	/**
	 * Deletes the given key from the BSTSortedList. If the key is in the list,
	 * the key is removed and true is returned. Otherwise, the list stays unchanged
	 * and false is returned.
	 * @return True if the key was in the BSTSortedList and was removed deleted,
	 * false otherwise
	 */
	public boolean delete(K key) {
		if (key == null) return false;
		if (this.root == null) return false;
		if (this.root.getLeft() == null && this.root.getRight() == null
				&& key.equals(this.root.getKey())) {
			this.root = null;
			return true;
		}
		
		else if (delete(this.root, key) == null)
			return false;
		else {
			this.root = delete(this.root, key);
			return true;
		}
	}

	/**
	 * Auxiliary delete method that does most of the work, and goes recursively
	 * through the BSTnode until it either finds the key or determines that the
	 * key is not in the  BSTnode.
	 * @param n The root of the BSTnode
	 * @param key The key that you want deleted
	 * @return A BSTnode object
	 */
	private BSTnode<K> delete(BSTnode<K> n, K key) {
		if (n == null) {
			return null;
		}

		if (key.equals(n.getKey())) {
			// n is the node to be removed
			if (n.getLeft() == null && n.getRight() == null) {
				return null;
			}
			if (n.getLeft() == null) {
				return n.getRight();
			}
			if (n.getRight() == null) {
				return n.getLeft();
			}

			// if we get here, then n has 2 children
			K smallVal = smallest(n.getRight());
			n.setKey(smallVal);
			n.setRight(delete(n.getRight(), smallVal) );
			return n;
		}

		else if (key.compareTo(n.getKey()) < 0) {
			n.setLeft(delete(n.getLeft(), key) );
			return n;
		}
		else {
			n.setRight(delete(n.getRight(), key) );
			return n;
		}
	}

	/**
	 * Searches for the given list in the sorted list and returns it.
	 * If it's not in the list, null is returned.
	 */
	public K lookup(K key) {
		if (key == null) return null;
		return lookup(key, this.root);
	}

	/**
	 * Auxiliary lookup method that does the work for the lookup method. Searches
	 * the list until it finds the key.  If it finds the key, then the key is returned.
	 * @param key the key to search for
	 * @param n the root of the BSTnode
	 * @return the key if it is in the BSTnode; null otherwise
	 */
	private K lookup(K key, BSTnode<K> n){
		if (n == null) return null;
		if (key.equals(n.getKey())) return key;
		if (key.compareTo(n.getKey()) < 0){
			n = n.getLeft();
			return lookup(key, n);
		}
		else
			n = n.getRight();
		return lookup(key, n);
	}

	/**
	 * Returns the size of the BSTSortedList
	 */
	public int size() {
		return this.numItems;
	}

	/**
	 * Returns true if the BSTSortedList is empty
	 * @return True if BSTSortedList is empty, false if it is not.
	 */
	public boolean isEmpty() {
		return (size() == 0);
	}

	/**
	 * Returns an iterator for the BSTSortedList.
	 */
	public Iterator<K> iterator() {
		return new BSTSortedListIterator<K>(root);

	}

	/**
	 * Returns a list of all the items in the BSTSortedList that are in the range
	 * between the first parameter and the second parameter.
	 * @return A list of keys in the given range
	 * @param
	 */
	public List<K> findInRange(K begin, K end) {
		if (end.compareTo(begin) < 0) return null;

		boolean lessThanEnd = true;
		Iterator<K> iter = this.iterator();
		List<K> list = new ArrayList<K>();
		K item;

		while (iter.hasNext() && lessThanEnd){
			item = iter.next();
			if (item.compareTo(end) > 0)
				lessThanEnd = false;
			else
				if (!(item.compareTo(begin) < 0))
					list.add(item);
		}
		return list;
	}



	/**
	 * A helper method that finds the smallest node in a given BSTnode.
	 * @param n a BSTnode
	 * @return The smallest key in the BSTnode
	 */
	private K smallest(BSTnode<K> n) {
		// precondition: n is not null
		// postcondition: return the smallest value in the subtree rooted at n
		if (n.getLeft() == null) {
			return n.getKey();
		} else 
			return smallest(n.getLeft());
	}

} // ends BSTSortedList class
