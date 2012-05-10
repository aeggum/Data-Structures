
///////////////////////////////////////////////////////////////////////////////
//                  
// Main Class File:  AddressBook.java
// File:             DuplicateException.java
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
/**
 * DuplicateException is an Exception that is thrown when the user attempts to add
 * a duplicate to the address book.
 * <p>Extends Exception
 */
public class DuplicateException extends Exception{
	public DuplicateException() {
		super();
	}
	public DuplicateException(String message) {
		super(message);
	}
} // closes DuplicateException class

