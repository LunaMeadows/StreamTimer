package classes;

//Imports
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

public class FileClass {
	// Instance Variables
	private String dateFormat;
	private String fileNameFormat;
	private File saveLocation;
	private File backupLocation;

	// Constructors
	public FileClass() {
	}

	// Getters
	/**
	 * @return the dateFormat
	 */
	public String getDateFormat() {
		return dateFormat;
	}

	/**
	 * @return the fileNameFormat
	 */
	public String getFileNameFormat() {
		return fileNameFormat;
	}

	/**
	 * @return the saveLocation
	 */
	public File getSaveLocation() {
		return saveLocation;
	}

	/**
	 * @return the backupLocation
	 */
	public File getBackupLocation() {
		return backupLocation;
	}

	// Setters
	/**
	 * @param dateFormat
	 *            the dateFormat to set
	 */
	public void setDateFormat(String dateFormat) {
		this.dateFormat = dateFormat;
	}

	/**
	 * @param fileNameFormat
	 *            the fileNameFormat to set
	 */
	public void setFileNameFormat(String fileNameFormat) {
		this.fileNameFormat = fileNameFormat;
	}

	/**
	 * @param saveLocation
	 *            the saveLocation to set
	 */
	public void setSaveLocation(File saveLocation) {
		this.saveLocation = saveLocation;
	}

	/**
	 * @param backupLocation
	 *            the backupLocation to set
	 */
	public void setBackupLocation(File backupLocation) {
		this.backupLocation = backupLocation;
	}

	// Methods
	/**
	 * Creates popup menu to allow user to select a Directory to save files to.
	 * 
	 * @param windowTitle
	 *            Needed for title of the popup window so user knows what they are
	 *            selecting
	 * @return Returns a JFileChooser variable so the programmer can manipulate the
	 *         Directory chosen by the user.
	 */
	public JFileChooser selectFolder(String windowTitle) {
		/*
		 * Fragment from http://www.rgagnon.com/javadetails/java-0370.html
		 */
		// Creates instance of JFileChooser
		JFileChooser chooser = new JFileChooser();
		// Sets current directory to where the .jar is located
		chooser.setCurrentDirectory(new java.io.File("."));
		// Sets the window title to what was passed
		chooser.setDialogTitle(windowTitle);
		// Sets the JFileChooser instance to only allow selection of Directories
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		// Shows the popup menu
		chooser.showDialog(null, "Select");
		// Returns the JFileChooser instance
		return chooser;
	}
	
	public JFileChooser selectFile(String windowTitle) {
		/*
		 * Fragment from http://www.rgagnon.com/javadetails/java-0370.html
		 */
		// Creates instance of JFileChooser
		JFileChooser chooser = new JFileChooser();
		// Sets current directory to where the .jar is located
		chooser.setCurrentDirectory(new java.io.File("."));
		// Sets the window title to what was passed
		chooser.setDialogTitle(windowTitle);
		// Sets the JFileChooser instance to only allow selection of Directories
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		// Shows the popup menu
		chooser.showDialog(null, "Select");
		// Returns the JFileChooser instance
		return chooser;
	}
	
	public JFileChooser saveFile(String windowTitle) {
		/*
		 * Fragment from http://www.rgagnon.com/javadetails/java-0370.html
		 */
		// Creates instance of JFileChooser and JFrame
		JFrame frame = new JFrame();
		JFileChooser chooser = new JFileChooser();
		// Sets current directory to where the .jar is located
		chooser.setCurrentDirectory(new java.io.File("."));
		// Sets the window title to what was passed
		chooser.setDialogTitle(windowTitle);
		// Sets the JFileChooser instance to only allow selection of Directories
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		// Shows the popup menu
		chooser.showSaveDialog(frame);
		// Returns the JFileChooser instance
		return chooser;
	}
	
	/**
	 * Checks to make sure that the given date format is in a correct format and
	 * dose not contain any bad characters
	 * 
	 * @param format
	 *            Passes the string the user gave for the format to check
	 * @return Returns a string with either the error or that everything is good and
	 *         clear to pass onto the next area
	 */
	public String checkDateFormat(String format) {
		// Creates a copy of the format string to modify
		String copy = format;
		// Creates an array that will store any character that is not specified in the
		// .split method and runs until there is no more specified characters
		String[] arr = copy.split("[$%YMD_]", 0);
		// for loop to check if there was any invalid characters and if so returns a
		// string saying that the format contains an invalid character
		for (int i = 0; i < arr.length; i++) {
			// checks if the string at i position is empty or not, and if not returns the
			// error of invalid charachter
			if (!arr[i].equals("")) {
				return "You used an invalid character, please only use the codes and _";
			}
		}
		// Strings to hold the needed format codes to check for in the string
		String year = "$YYYY%";
		String month = "$MM%";
		String date = "$DD%";
		// If the string contains the specified string then it will set the respective
		// string to null
		if (format.contains("$YYYY%")) {
			year = null;
		}
		// If the string contains the specified string then it will set the respective
		// string to null
		if (format.contains("$MM%")) {
			month = null;
		}
		// If the string contains the specified string then it will set the respective
		// string to null
		if (format.contains("$DD%")) {
			date = null;
		}
		// If the format code strings are all null, then the format string is clear and
		// good to use
		if (year == null && month == null && date == null) {
			return "clear";
			// If one or more is not null, then it returns a error code saying that you
			// forgot a code and lists what codes are needed.
		} else {
			return ("You forgot to include a code, here are the ones that are not present: " + year + " " + month + " "
					+ date);
		}
	}

	/**
	 * Checks to make sure that the given file name format is in a correct format
	 * and dose not contain any bad characters
	 * 
	 * @param format
	 *            Passes the string the user gave for the format to check
	 * @return Returns a string with either the error or that everything is good and
	 *         clear to pass onto the next area
	 */
	public String checkFileNameFormat(String format) {
		// Creates a copy of the format string to be checked and modified
		String copy = format;
		// Creates an array that will store any character that is not specified in the
		// .split method and runs until there is no more specified characters
		String[] arr = copy.split("[$%DFCNT-]", 0);
		// for loop to check if there was any invalid characters and if so returns a
		// string saying that the format contains an invalid character
		for (int i = 0; i < arr.length; i++) {
			// checks if the string at i position is empty or not, and if not returns the
			// error of invalid charachter
			if (!arr[i].equals("")) {
				return "You used an invalid character, please only use the codes and -";
			}
		}
		// Strings to hold the needed format codes to check for in the string
		String date = "$DF%";
		String className = "$CN%";
		String test = "$TN%";
		// If the string contains the specified string then it will set the respective
		// string to null
		if (format.contains("$DF%")) {
			date = null;
		}
		// If the string contains the specified string then it will set the respective
		// string to null
		if (format.contains("$CN%")) {
			className = null;
		}
		// If the string contains the specified string then it will set the respective
		// string to null
		if (format.contains("$TN%")) {
			test = null;
		}
		// If the format code strings are all null, then the format string is clear and
		// good to use
		if (date == null && className == null && test == null) {
			return "clear";
			// If one or more is not null, then it returns a error code saying that you
			// forgot a code and lists what codes are needed.
		} else {
			return ("You forgot to include a code, here are the ones that are not present: " + date + " " + className
					+ " " + test);
		}
	}

	/**
	 * Saves the settings for the program so that it can be accessed later on in the
	 * program
	 * 
	 * @param dateFormat
	 *            String with valid date format to save to file
	 * @param fileNameFormat
	 *            String with valid file name format to save to file
	 * @param saveLocation
	 *            String with valid save location to save to file
	 * @param backupLocation
	 *            String with valid backup location to save to file
	 */
	public void saveSettings(String dateFormat, String fileNameFormat, File saveLocation, File backupLocation) {
		// Creates instance of File to create the settings.ini file
		File settingsFile = new File(System.getProperty("user.home") + "\\Documents\\DBSoftware\\settings.ini");
		// Try loop to make sure that the file gets created if it is not there at the
		// start
		try {
			// Creates instance of the FileWriter to write to the settings.ini file
			FileWriter writer = new FileWriter(settingsFile);
			// Writes the date and file format as well as the save and backup location to
			// the settings.ini file with a blank line at the end
			writer.write(dateFormat + "\n");
			writer.write(fileNameFormat + "\n");
			writer.write(saveLocation.getAbsolutePath() + "\n");
			writer.write(backupLocation.getAbsolutePath() + "\n");
			writer.close();
			// Catches if the file does not exist
		} catch (IOException e) {
			try {
				// Will try to create the file
				settingsFile.createNewFile();
				// If file is created then will run the saveSettings method again
				saveSettings(dateFormat, fileNameFormat, saveLocation, backupLocation);
			} catch (IOException e1) {
				// Catches if there is any errors for debugging
				e1.printStackTrace();
			}
		}
	}

	public void readInSettings() {
		// Creates instance of File to create the settings.ini file
		File settingsFile = new File("settings.ini");
		// Try loop to make sure that the file gets created if it is not there at the
		// start
		try {
			// Creates instance of the FileWriter to write to the settings.ini file
			FileReader read = new FileReader(settingsFile);
			Scanner reader = new Scanner(read);
			dateFormat = reader.nextLine();
			fileNameFormat = reader.nextLine();
			File save = new File(reader.nextLine());
			saveLocation = save;
			File backup = new File(reader.nextLine());
			backupLocation = backup;
			reader.close();
			read.close();
			// Catches if the file does not exist
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	// Create read in method

}
