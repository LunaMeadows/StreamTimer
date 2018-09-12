package classes;

//Imports
//IO
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
//Util
import java.util.Scanner;
//Swing
import javax.swing.JFileChooser;
import javax.swing.JFrame;

public class FileClass {
	//Instance Variables
	//String
	private String BotName;
	private String BotOAuth;
	private String TwitchChannel;
	private String AddTimeCommand;
	private String SubTimeCommand;
	private String AddTimeFormat;
	private String SubTimeFormat;

	// Constructors
	public FileClass() {}
	
	// Methods
	//Public
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
	
	/**
	 * Creates a popup menu allowing the user to select a file to load
	 * @param windowTitle Title for the popup window
	 * @return returns the file selected
	 */
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
	
	/**
	 * Creates a popup window allowing the user to save a file
	 * @param windowTitle Title for the popup window
	 * @return returns the saved file
	 */
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
	 * Checks to make sure that the given format is valid and
	 * dose not contain any bad characters
	 * 
	 * @param format
	 *            Passes the string the user gave for the format to check
	 * @return Returns a string with either the error or that everything is good and
	 *         clear to pass onto the next area
	 */
	public String checkTimeFormat(String format) {
		// Creates a copy of the format string to modify
		String copy = format;
		// Creates an array that will store any character that is not specified in the
		// .split method and runs until there is no more specified characters
		String[] arr = copy.split("[$%TA_ ]", 0);
		// for loop to check if there was any invalid characters and if so returns a
		// string saying that the format contains an invalid character
		for (int i = 0; i < arr.length; i++) {
			// checks if the string at i position is empty or not, and if not returns the
			// error of invalid charachter
			if (!arr[i].equals("")) {
				debug.debug("FileClassCheckTimeFormat:" + "You used " + arr[i] + " which is an invalid character, please only use the codes and _");
				return "You used " + arr[i] + " which is an invalid character, please only use the codes and _";
			}
		}
		// Strings to hold the needed format codes to check for in the string
		String timeAmmount = "$TA%";
		String timeType = "$TT%";
		// If the string contains the specified string then it will set the respective
		// string to null
		if (format.contains("$TA%")) {
			timeAmmount = null;
		}
		// If the string contains the specified string then it will set the respective
		// string to null
		if (format.contains("$TT%")) {
			timeType = null;
		}
		// If the format code strings are all null, then the format string is clear and
		// good to use
		if (timeAmmount == null && timeType == null) {
			return "clear";
			// If one or more is not null, then it returns a error code saying that you
			// forgot a code and lists what codes are needed.
		} else {
			debug.debug("FileClassCheckTimeFormat:" + "You forgot to include a code, here are the ones that are not present: " + timeAmmount + " " + timeType);
			return ("You forgot to include a code, here are the ones that are not present: " + timeAmmount + " " + timeType);
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
	public void saveSettings(String BotName, String BotOAuth, String TwitchChannel, String AddTimeCommand, String SubTimeCommand, String AddTimeFormat, String SubTimeFormat) {
		// Creates instance of File to create the settings.ini file
		File settingsFile = null;
		// Try loop to make sure that the file gets created if it is not there at the start
		try {
			// Creates instance of the FileWriter to write to the settings.ini file
			settingsFile = new File(System.getProperty("user.home") + "\\Documents\\DBSoftware\\StreamTimer\\settings.ini");
			FileWriter writer = new FileWriter(settingsFile);
			// Writes the needed info to the settings.ini file with a blank line at the end
			writer.write(BotName + "\n");
			writer.write(BotOAuth + "\n");
			writer.write(TwitchChannel + "\n");
			writer.write(AddTimeCommand + "\n");
			writer.write(SubTimeCommand + "\n");
			writer.write(AddTimeFormat + "\n");
			writer.write(SubTimeFormat + "\n");
			writer.close();
			// Catches if the file does not exist
		} catch (IOException e) {
			try {
				// Will try to create the file
				settingsFile = new File(System.getProperty("user.home") + "\\Documents\\DBSoftware\\StreamTimer");
				settingsFile.mkdir();
				settingsFile.createNewFile();
				// If file is created then will run the saveSettings method again
				saveSettings(BotName, BotOAuth, TwitchChannel, AddTimeCommand, SubTimeCommand, AddTimeFormat, SubTimeFormat);
			} catch (IOException e1) {
				// Catches if there is any errors for debugging
				debug.debug("FileClassSaveSettings:" + System.getProperty("user.home") + "\\Documents\\DBSoftware\\StreamTimer\\settings.ini");
				debug.debug(e1.getStackTrace());
			}
		}
		try {
			Cryption.encrypt("This is a test k", settingsFile);
		} catch (CryptoException e) {
			debug.debug("FileClassSaveSettings:" + "There was an error encrypting the file.");
			debug.debug(e.getStackTrace());
		}
	}
	
	/**
	 * Reads in settings from settings.ini
	 */
	public void readInSettings() {
		// Creates instance of File to create the settings.ini temp file
		File settingsFile = null;
		try {
			settingsFile = Cryption.decrypt("This is a test k", new File(System.getProperty("user.home") + "\\Documents\\DBSoftware\\StreamTimer\\settings.ini"));
		} catch (CryptoException | IOException e1) {
			debug.debug("FileClassSaveSettings:" + "There was an error decrypting the file.");
			debug.debug(e1.getStackTrace());
			return;
		}
		// Try loop to make sure that the file gets created if it is not there at the
		// start
		try {
			// Creates instance of the FileWriter to write to the settings.ini file
			FileReader read = new FileReader(settingsFile);
			Scanner reader = new Scanner(read);
			BotName = reader.nextLine();
			BotOAuth = reader.nextLine();
			TwitchChannel = reader.nextLine();
			AddTimeCommand = reader.nextLine();
			SubTimeCommand = reader.nextLine();
			AddTimeFormat = reader.nextLine();
			SubTimeFormat = reader.nextLine();
			reader.close();
			read.close();
			// Catches if the file does not exist
		} catch (IOException e) {
			debug.debug("FileClassReadInSettings:" + "Error creating new file");
			debug.debug(e.getStackTrace());
		}
		settingsFile.delete();
	}
		
	// Getters
	/**
	 * @return the botName
	 */
	public String getBotName() {
		return BotName;
	}

	/**
	 * @return the botOAuth
	 */
	public String getBotOAuth() {
		return BotOAuth;
	}

	/**
	 * @return the twitchChannel
	 */
	public String getTwitchChannel() {
		return TwitchChannel;
	}
	
	/**
	 * @return the addTimeFormat
	 */
	public String getAddTimeFormat() {
		return AddTimeFormat;
	}

	/**
	 * @return the addTimeCommand
	 */
	public String getAddTimeCommand() {
		return AddTimeCommand;
	}

	/**
	 * @return the subTimeCommand
	 */
	public String getSubTimeCommand() {
		return SubTimeCommand;
	}

	/**
	 * @return the subTimeFormat
	 */
	public String getSubTimeFormat() {
		return SubTimeFormat;
	}
}
