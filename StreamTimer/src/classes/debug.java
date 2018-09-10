package classes;

//Imports
//IO
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
//Util
import java.util.Scanner;

public class debug {
	//Constructors
	/**
	 * Makes the methods accessable without creating a debug instance
	 */
	private debug(){}
	
	//Methods
	//Private
	/**
	 * Creates the debug file
	 */
	private static void createFile() {
		File debugFile = new File("debug.txt");
		try {
			debugFile.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//Public
	/**
	 * Writes to the debug.txt file if debuging is turned on
	 * @param bug What is logged in the file
	 */
	public static void debug(String bug) {
		//Makes a file of debug.txt
		File debugFile = new File("debug.txt");
		//Checks if the file exists and if not makes it
		if(!debugFile.exists()) {
			createFile();
		} else {
			//Creates instance of a scanner to check if debug is turned on or not
			Scanner check = null;
			try {
				check = new Scanner(debugFile);
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			//If check has a new line and it is equal to true then write the bug to the file else do nothing
			if(check.hasNextLine() && debugOn()) {
				try {
					FileWriter debugWriter = new FileWriter(debugFile,true);
					debugWriter.write(bug + "\n");
					debugWriter.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * Checks if debug is active
	 */
	public static boolean debugOn() {
		//Makes a file of debug.txt
		File debugFile = new File("debug.txt");
		//Checks if the file exists and if not makes it
		if(!debugFile.exists()) {
			createFile();
		} else {
			//Creates instance of a scanner to check if debug is turned on or not
			Scanner check = null;
			try {
				check = new Scanner(debugFile);
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			return check.nextBoolean();
		}
		return false;
	}
	
	/**
	 * Toggles debug
	 */
	public static void debugToggle() {
		//Makes a file of debug.txt
		File debugFile = new File("debug.txt");
		//Checks if the file exists and if not makes it
		if(!debugFile.exists()) {
			createFile();
		} else {
			//Creates instance of a scanner to check if debug is turned on or not
			Scanner check = null;
			try {
				check = new Scanner(debugFile);
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			//If check has a new line and it is equal to true then write the bug to the file else do nothing
			if(check.hasNextLine() && !debugOn()) {
				try {
					FileWriter debugWriter = new FileWriter(debugFile);
					debugWriter.write("true" + "\n");
					debugWriter.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				try {
					FileWriter debugWriter = new FileWriter(debugFile);
					debugWriter.write("false" + "\n");
					debugWriter.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
