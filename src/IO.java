import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;


public class IO {
	/**
	 * Loads the File given from the fileName and returns a 2-dimensional
	 * array of <tt>String</tt>s that represents all of the values from the
	 * file.  It does this by utilizing file input with Java's <tt>FileReader</tt>
	 * and <tt>BufferedReader</tt> objects.  If the fileName passed in is null,
	 * null will also be returned.
	 * @param fileName The name of the file you want the 2D Array representation for
	 * @return A 2D array of Strings that represents all of the values inside of the file
	 * that were separated by commas.  <tt>null</tt> returned if <b>fileName</b> is also null.
	 */
	public static String[][] loadFile(String fileName, int ARRSIZE) {
		if (fileName == null) return null;
		File file = new File(fileName);
		String[][] fileInput = new String[ARRSIZE][ARRSIZE];

		//Need to keep these in try-catch, as they risk throwing an IOException
		try {
			FileReader file_reader = new FileReader(file);
			BufferedReader buf_reader = new BufferedReader(file_reader);
			for (int line = 0; line < ARRSIZE; line++) {
				String inputLine = buf_reader.readLine();
				//System.out.println(inputLine);
				fileInput[line] = inputLine.split(",");
			}

			buf_reader.close();
			file_reader.close();
		} catch (IOException ioe) {
			System.err.println(ioe.getMessage());
		}

		return fileInput;
	}



	/**
	 * This method will create various files with values of the given type, returning
	 * the filename that it created. If null is passed in, null will be returned. The file
	 * that is created depends on the type that is passed in.  When an <tt>int</tt> is
	 * passed in, <b>integers.txt</b> will be created, and likewise <b>doubles.txt</b>
	 * or <b>strings.txt</b> for <tt>Double</tt>s and <tt>String</tt>s.
	 * @param type The type of input (ie Integer, Double, String)
	 * @return String the filename of the file that was created
	 */
	public static String writeFile(Object type, int ARRSIZE) {
		//Variables needed for file input and determining what file to create
		File file = null;
		boolean stringIn = false;
		boolean intIn = false;
		boolean doubleIn = false;

		if (type == null) return null;

		//Depending on what was passed in, create the file named what we want
		if (type instanceof String) {
			stringIn = true;
			file = new File("strings.txt");
		}
		else if (type instanceof Integer){
			intIn = true;
			file = new File("integers.txt");
		}
		else if (type instanceof Double) {
			doubleIn = true;
			file = new File("doubles.txt");
		}
		else {
			System.err.println("The input type needs to be int, double, or String");
			System.exit(0);
		}

		/*
		 * Create a FileWriter stream to a file and wrap a PrintWriter object around
		 * it to print primitive values to the file.
		 */
		FileWriter fileWriter = null;
		BufferedWriter buf_writer = null;
		PrintWriter printWriter = null;

		//Inside of a try-catch, as many of these operations can throw IOExceptions
		try {
			//Initialize the fileWriter, bufWriter, and printWriter objects
			fileWriter = new FileWriter(file, false);
			buf_writer = new BufferedWriter(fileWriter);
			printWriter = new PrintWriter(buf_writer, true);

			/*
			 * The following if-elseif-elseif creates the file using the type we
			 * want to use, based on what was passed in. It creates a file using
			 * Strings, Integers, or Doubles.
			 */
			if (stringIn) {
				//Currently unimplemented
				String[][] array = new String[ARRSIZE][ARRSIZE];
				assert array != null;	//placeholder for now

			}

			/*
			 * If we're creating integers.txt, create a 2D array and fill
			 * it with numbers from 0 to ARRSIZE^2.  Then put them into the
			 * file, with separation by commas.
			 */
			else if (intIn) {
				int[][] array = new int[ARRSIZE][ARRSIZE];

				//Fill up the 2D array
				for (int i = 0; i < array.length; i++) {
					for (int j = 0; j < array[0].length; j++) {
						array[i][j] = i*j;
					}
				}

				//Put the 2D array into the file
				for (int i = 0; i < array.length; i++) {
					for (int j = 0; j < array[0].length; j++) {
						printWriter.print(array[i][j]);
						if (j != array[0].length - 1)
							printWriter.print(",");
					}
					printWriter.println();
				}

				if (printWriter.checkError())
					System.err.println("An output error occurred!");

				printWriter.close();
				buf_writer.close();
				fileWriter.close();
			}

			/*
			 * If we're creating doubles.txt, create a 2D array and fill
			 * it with numbers from 0 to ARRSIZE^2*.75.  Then put them into the
			 * file, with separation by commas.
			 */
			else if (doubleIn) {
				double[][] array = new double[ARRSIZE][ARRSIZE];

				//Fill up the 2D array
				for (int i = 0; i < array.length; i++) {
					for (int j = 0; j < array[0].length; j++) {
						array[i][j] = i*j*.75;
					}
				}

				//Put the 2D array into doubles.txt, with separation by comma
				for (int i = 0; i < array.length; i++) {
					for (int j = 0; j < array[0].length; j++) {
						printWriter.print(array[i][j]);
						if (j != array[0].length - 1)
							printWriter.print(",");
					}
					printWriter.println();
				}

				if (printWriter.checkError())
					System.err.println("An output error occurred!");

				printWriter.close();
				buf_writer.close();
				fileWriter.close();
			}

		} catch (IOException ioe) {
			System.err.println("IO Exception");
			System.err.println(ioe.getMessage());
		}

		System.out.println(file.toString());
		return file.getName();
	}


	/**
	 * Converts a two-dimensional array of <tt>String</tt>s into an <tt>ArrayList</tt> of integers.
	 * It returns the <tt>ArrayList</tt> after it has been converted.
	 * @param strArray The 2D array of <tt>String</tt>s you want to convert
	 * @return An <tt>ArrayList</tt> of <tt>Integer</tt>s containing everything that was in the parameter.
	 */
	public static ArrayList<Integer> stringToIntList(String[][] strArray) {
		ArrayList<Integer> ints = new ArrayList<Integer>(strArray.length * strArray[0].length);

		for (int row = 0; row < strArray.length; row++) {
			for (int col = 0; col < strArray[0].length; col++) {
				int num = Integer.parseInt(strArray[row][col]);
				ints.add(num);
			}
		}
		return ints;
	}
	
	public static Integer[] stringToIntArray(String[][] strArray) {
		Integer[] ints = new Integer[strArray.length * strArray[0].length];
		//ArrayList<Integer> ints = new ArrayList<Integer>(strArray.length * strArray[0].length);

		int i = 0;
		for (int row = 0; row < strArray.length; row++) {
			for (int col = 0; col < strArray[0].length; col++) {
				int num = Integer.parseInt(strArray[row][col]);
				ints[i] = num;
				i++;
			}
		}
		return ints;
	}

	
	public static Double[] stringToDoubleArray(String[][] strArray) {
		Double[] doubs = new Double[strArray.length * strArray[0].length];
		//ArrayList<Integer> ints = new ArrayList<Integer>(strArray.length * strArray[0].length);

		int i = 0;
		for (int row = 0; row < strArray.length; row++) {
			for (int col = 0; col < strArray[0].length; col++) {
				double num = Double.parseDouble(strArray[row][col]);
				doubs[i] = num;
				i++;
			}
		}
		return doubs;
	}
	
	/**
	 * Converts a two-dimensional array of <tt>String</tt>s into an <tt>ArrayList</tt> of
	 * <tt>Integer</tt>s.  
	 * It returns the <tt>ArrayList</tt> after it has been converted.
	 * @param strArray The 2D array of <tt>String</tt>s (though actually doubles) you want to convert.
	 * @return An <tt>ArrayList</tt> of <tt>Double</tt>s containing everything that was in the parameter.
	 */
	public static ArrayList<Double> stringToDoubleList(String[][] strArray) {
		ArrayList<Double> doubles = new ArrayList<Double>(strArray.length * strArray[0].length);

		for (int row = 0; row < strArray.length; row++) {
			for (int col = 0; col < strArray[0].length; col++) {
				double num = Double.parseDouble(strArray[row][col]);
				doubles.add(num);
			}
		}
		return doubles;
	}



}
