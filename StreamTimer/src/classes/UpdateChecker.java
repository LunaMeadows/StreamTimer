package classes;
/*
 * @author Derrick Bush
 * 
 * Idea from https://www.hanselman.com/blog/MakingYourApplicationAutomaticallyUpdateItself.aspx
 * Code fragment from https://www.mkyong.com/java/how-to-send-http-request-getpost-in-java/
 * Code fragment from https://www.codeproject.com/Questions/398241/how-to-open-url-in-java - TorstenH
 * 
 */
//Imports
import java.io.*;
import java.net.*;
import java.awt.Desktop;
import javax.swing.JOptionPane;

public class UpdateChecker {
	//Instance Variables
	private final static String USER_AGENT = "Mozilla/5.0";
	private final static String VERSION = "Version 1.2";
	
	//Methods
	public static void updateCheckStandalone() throws Exception {
		String url = "https://raw.githubusercontent.com/derrickbush1999/StreamTimer/master/README.md";
		
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		
		con.setRequestMethod("GET");

		con.setRequestProperty("User-Agent", USER_AGENT);

		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		
		if (response.toString().indexOf(VERSION) == -1) {
			int confirmation = JOptionPane.showConfirmDialog(null, "The current version of the software is out of date. Please go to https://github.com/derrickbush1999/TestScaler to get the new version.");
			if(confirmation == JOptionPane.YES_OPTION) {
				try {
					  Desktop desktop = java.awt.Desktop.getDesktop();
					  URI oURL = new URI("https://github.com/derrickbush1999/StreamTimer");
					  desktop.browse(oURL);
					} catch (Exception e) {
						debug.debug("UpdateCheckerStandalone:" + "There was an error getting the users desktop");
						debug.debug(e.getStackTrace());
					}
			}
		}
	}
	
	public static int updateCheckReturn() throws Exception {
		String url = "https://raw.githubusercontent.com/derrickbush1999/StreamTimer/master/README.md";
		
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		
		con.setRequestMethod("GET");
		
		con.setRequestProperty("User-Agent", USER_AGENT);

		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		
		return response.toString().indexOf(VERSION);
	}
}
