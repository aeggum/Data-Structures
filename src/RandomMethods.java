import java.util.*;
public class RandomMethods {
	public static void main(String[] args) {
		String word = "Aaron Rodgers is the best!";
		print(reverseWords(word));
		print(reverse(word));
	}
	
	
	/**
	 * Returns true if an array has a duplicate, false otherwise.
	 * Does not count duplicates or anything, just true or false.
	 * @param array 
	 * @return true if the array contains duplicates, false otherwise.
	 */
	public static boolean duplicate(String[] array) {
		for (int i = 0; i < array.length; i++) {
			String str = array[i];
			for (int j = i + 1; j < array.length; j++) {
				if (array[j].equals(str)) 
					return true;
			}
		}
		
		return false;
	}
	
	/**
	 * Reverses each of the words in a String, separated by whitespace.
	 * So (abc def becomes cba fed).  Returns null if passed null, string is 
	 * empty or the first character is empty. 
	 * @param str
	 * @return String passed in, but with words inside of them reversed
	 */
	public static String reverseWords(String str) {
		if (str == null) return null;
		if (str.equals("") || str.charAt(0) == ' ') return null;
		
		String word = "";
		while (str.contains(" ")) {
			int index = str.indexOf(" ");
			for (int i = index - 1; i >= 0; i--) 
				word += str.charAt(i);
			
			word += str.charAt(index);
			str = str.substring(index + 1);
		}
		
		for (int i = str.length() - 1; i >= 0; i--) 
			word += str.charAt(i);
		
		return word;
	}
	
	
	/**
	 * Reverse the string passed in. 
	 * @param str String you want reversed
	 * @return The string passed in, but reversed
	 */
	public static String reverse(String str) {
		String word = ""; 
		for (int i = str.length() - 1; i >= 0; i--) 
			word += str.charAt(i);
		
		return word;
	}
	
	/**
	 * Calls System.out.println() on whatever is passed in. 
	 * @param toPrint
	 */
	public static void print(Object toPrint) {
		System.out.println(toPrint.toString());
	}
}






























*/
