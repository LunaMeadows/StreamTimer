package classes;
//Imports
//awt
import java.awt.Color;
//io
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
//util
import java.util.Scanner;

public class StyleClass {
	//Instance Varibales
	//Color
	private static Color window_background;
	private static Color timer_background;
	private static Color timer_text;
	private static Color button_background;
	private static Color button_text;
	private static Color table_background;
	private static Color table_text;
	private static Color textbox_background;
	private static Color textbox_text;
	private static Color text;
	private static Color dropdown_background;
	private static Color dropdown_text;
	//Strings
	private static String style;
	
	//Constructors
	/**
	 * Default constructor will load in active style
	 */
	public StyleClass() {
		readIn(null);
	}
	
	/**
	 * Will load in selected style
	 * @param file Style that you want to load
	 */
	public StyleClass(File file) {
		readIn(file);
	}
		
	//Methods
	private static void readIn(File file) {
		File active;
		if(file == null)
			active = new File("Styles\\active");
		else
			active = file;
		Scanner activeRead = null;
		try {
			activeRead = new Scanner(active);
		} catch (FileNotFoundException e) {
			debug.debug("StyleClassReadIn:" + "The file was not found, " + file);
			debug.debug(e.getStackTrace());
		}
		while(activeRead != null && activeRead.hasNext()) {
			String hold = activeRead.nextLine();
			if (hold.startsWith("style=")) {
				style = hold.substring(hold.indexOf("_")+1);
			} else if (hold.startsWith("window_background=")) {
				window_background = new Color(Integer.parseInt(hold.substring(hold.indexOf("=")+1)));
			} else if (hold.startsWith("timer_background=")) {
				timer_background = new Color(Integer.parseInt(hold.substring(hold.indexOf("=")+1)));
			} else if (hold.startsWith("timer_text=")) {
				timer_text = new Color(Integer.parseInt(hold.substring(hold.indexOf("=")+1)));
			} else if (hold.startsWith("button_background=")) {
				button_background = new Color(Integer.parseInt(hold.substring(hold.indexOf("=")+1)));
			} else if (hold.startsWith("button_text=")) {
				button_text = new Color(Integer.parseInt(hold.substring(hold.indexOf("=")+1)));
			} else if (hold.startsWith("table_background=")) {
				table_background = new Color(Integer.parseInt(hold.substring(hold.indexOf("=")+1)));
			} else if (hold.startsWith("table_text=")) {
				table_text = new Color(Integer.parseInt(hold.substring(hold.indexOf("=")+1)));
			} else if (hold.startsWith("textbox_background=")) {
				textbox_background = new Color(Integer.parseInt(hold.substring(hold.indexOf("=")+1)));
			} else if (hold.startsWith("textbox_text=")) {
				textbox_text = new Color(Integer.parseInt(hold.substring(hold.indexOf("=")+1)));
			} else if (hold.startsWith("text=")) {
				text = new Color(Integer.parseInt(hold.substring(hold.indexOf("=")+1)));
			} else if (hold.startsWith("dropdown_background=")) {
				dropdown_background = new Color(Integer.parseInt(hold.substring(hold.indexOf("=")+1)));
			} else if (hold.startsWith("dropdown_text=")) {
				dropdown_text = new Color(Integer.parseInt(hold.substring(hold.indexOf("=")+1)));
			}
		}
		if(activeRead != null)
			activeRead.close();
	}
	
	//Public
	public static String[] getStyles() {
		File folder = new File("Styles");
		File[] files = folder.listFiles();
		ArrayList<String> stylesArray = new ArrayList<String>();
		for(int i = 0; i < files.length; i++) {
			String file = files[i].toString();
			file = file.substring(file.indexOf("\\")+1);
			if(file.startsWith("style_"))
				stylesArray.add(file.substring(file.indexOf("_")+1));
		}
		String[] styles = new String[stylesArray.size()];
		styles = stylesArray.toArray(styles);
		return styles;
	}
	
	public static void changeStyle(String file) throws IOException {
		File change = new File("Styles\\" + file);
		File active = new File("Styles\\active");
		FileWriter write = new FileWriter(active);
		Scanner reader = new Scanner(change);
		write.write("style=" + file + "\n");
		while(reader.hasNextLine())
			write.write(reader.nextLine() + "\n");
		readIn(null);
		write.close();
		reader.close();
	}
	//Getters
	/**
	 * @return the style
	 */
	public static String getStyle() {
		return style;
	}
	
	/**
	 * @return the window_background
	 */
	public static Color getWindow_background() {
		return window_background;
	}

	/**
	 * @return the timer_background
	 */
	public static Color getTimer_background() {
		return timer_background;
	}

	/**
	 * @return the timer_text
	 */
	public static Color getTimer_text() {
		return timer_text;
	}

	/**
	 * @return the button_background
	 */
	public static Color getButton_background() {
		return button_background;
	}

	/**
	 * @return the button_text
	 */
	public static Color getButton_text() {
		return button_text;
	}

	/**
	 * @return the table_background
	 */
	public static Color getTable_background() {
		return table_background;
	}

	/**
	 * @return the table_text
	 */
	public static Color getTable_text() {
		return table_text;
	}
	
	/**
	 * @return the textbox_background
	 */
	public static Color getTextbox_background() {
		return textbox_background;
	}

	/**
	 * @return the textbox_text
	 */
	public static Color getTextbox_text() {
		return textbox_text;
	}

	/**
	 * @return the text
	 */
	public static Color getText() {
		return text;
	}

	/**
	 * @return the dropdown_background
	 */
	public static Color getDropdown_background() {
		return dropdown_background;
	}

	/**
	 * @return the dropdown_text
	 */
	public static Color getDropdown_text() {
		return dropdown_text;
	}
}
