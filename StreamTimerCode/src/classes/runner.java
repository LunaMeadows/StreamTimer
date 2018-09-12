package classes;
//Imports
//IO
import java.io.File;
//GUI
import gui.SetupWindow;
import gui.TimerGUI;

public class runner {
	/**
	 * Main class to start the program
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		//Creates instance of settings.ini file
		File settingsFile = new File(System.getProperty("user.home") + "\\Documents\\DBSoftware\\StreamTimer\\settings.ini");
		//Checks if the current version is the most up to date version
		UpdateChecker.updateCheckStandalone();
		//Checks if settings.ini exists and if not goes through the setup process and if it does runs the timer
		if(!settingsFile.exists()) {
			SetupWindow setup = new SetupWindow();
			setup.activate();
			while(!settingsFile.exists()) {
			}
		}
		TimerGUI timer = new TimerGUI();
		timer.activate();
	}
}
