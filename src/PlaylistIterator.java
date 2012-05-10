///////////////////////////////////////////////////////////////////////////////
//                   
// Main Class File:  PlaylistManager.java
// File:             PlaylistIterator.java
// Semester:         Fall 2010
//
// Author:           Adam Eggum, aeggum@wisc.edu
// CS Login:         eggum
// Lecturer's Name:  Rebecca Hasti
//
// Pair Partner:     Justin Smith
// CS Login:         justins
// Lecturer's Name:  Rebecca Hasti
//
//////////////////////////// 80 columns wide //////////////////////////////////

import java.util.*;
public class PlaylistIterator<E> implements Iterator<E>{
	private DblListnode<E> playlist;
	private DblListnode<E> holder;
	private boolean first;
	
	
	/**
	 * The constructor for the PlaylistIterator.  It takes in a DblListnode and
	 * sets instance DblListnodes to point at the DblListnode.  Also,
	 * two flags that are necessary are set to true.
	 * @param A DblListnode<E> that needs to be iterated through.
	 */
	PlaylistIterator(DblListnode<E> currSong) {
		playlist = currSong;
		holder = currSong;
		first = true;
		
		
	}
	/**
	 * Checks to see if the iterator is pointing at a DblListnode
	 * @return True if there is a DblListnode, false otherwise
	 */
	public boolean hasNext() {
		if (first  && playlist == holder) {
			first = false;
			return true;
		}
		return playlist != holder;
	}
	
	/**
	 * A private method; This was necessary with our implementation because
	 * without it the next() method would call hasNext() and return false 
	 * the first time around.
	 * @return True if there is a DblListnode, false otherwise
	 */
	private boolean secondHasNext() {
		if (playlist == holder) {
			return true;
		}
		return playlist != holder;
	}
	/**
	 * Checks to make sure the iterator is pointing at a DblListnode,
	 * then saves that node and moves the iterator over.
	 * @return The DblListnode the iterator was pointing at when next was 
	 * called.
	 */
	public E next() {
		if (!secondHasNext())
			throw new NoSuchElementException();
		DblListnode<E> tmp = playlist;
		playlist = playlist.getNext();
		return tmp.getData();
	}
	/**
	 * This operation is not supported.
	 * @throws UnsupportedOperationException if called
	 */
	public void remove() {
		throw new UnsupportedOperationException();
	}
} // ends PlaylistIterator class
