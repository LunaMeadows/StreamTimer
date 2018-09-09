package classes;
//Imports
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class StreamTimer extends Thread{
	//Instance Variables
	//Ints
	private static int hours = 0;
	private static int minutes = 2;
	private static int seconds = 5;
	//Strings
	private static String hh = null;
	private static String mm = null;
	private static String ss = null;
	private static String time = null;
	//Booleans
	private static boolean debug = false;
	
	//Methods
	//Public
	/**
	 * Creates a thread and runs inline with rest of method/class
	 * Use .start() instead of .run()
	 */
	public void run() {
		//while hours, minutes, or seconds is greater then 0 run the timer, else stop
		while(true) {
			command();
			timeIncerment();
			formatCheck();
			//de incermetn seconds if time does not equal 0
			if(seconds == 0) {
				debug("Time is up");
			} else {
				seconds--;
			}
			debug(time);
			//Trys to sleep for 1 second
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Returns the current time left on the timer formated with leading 0's
	 * 
	 * @return
	 */
	public static String getTime() {
		//if time does not equal null(Not setup) return time, else return as if the timer has hit 0
		if(time != null) {
			return time;
		}
		return "00:00:00";
	}
	
	//Private
	/**
	 * Easy debug method to output a string to console if debuging is turned on.
	 * 
	 * @param message is the message that is printed to console
	 */
	private static void debug(String message) {
		if(debug) {
			System.out.println(message);}
	}
	
	/**
	 * Incerments the time left down every second
	 */
	private static void timeIncerment() {
		//If seconds is equal to 0 then subtract 1 from minutes and set seconds to 59
		if(seconds == 0 && minutes > 0) {
			minutes--;
			seconds = 59;
		}
		//If minutes is equal to 0 then subtact 1 from hours and set minutes to 59
		if(minutes == 0 && hours > 0) {
			hours--;
			minutes = 59;
		}
	}
	
	/**
	 * Checkes to make sure that time is not larger than logicaly posible and makes sure that the returned time string is formated with leading 0's if needed
	 */
	private static void formatCheck() {
		//If seconds is greater then 59, it will add minutes and sub seconds till seconds is less then 59
		while(seconds > 59) {
			minutes++;
			seconds= seconds-59;
		}
		//If minutes are greater then 59, it will add hours and sub minutes till minutes is less then 59
		while(minutes > 59) {
			hours++;
			minutes= minutes-59;
		}
		//If seconds is less then 0, it will subtract minutes and add seconds till seconds is greater then 59
		while(seconds < 0) {
			minutes--;
			seconds = seconds+59;
		}
		//If minutes are less then 59, it will subtract hours and add minutes till seconds is greater then 59
		while(minutes < 0) {
			hours--;
			minutes= minutes+59;
		}
		if(hours < 0) {
			hours = 0;
			minutes = 0;
			seconds = 0;
		}
		//Checks if time is less then 10 and if so will add a 0 to the front of it
		//Hours
		if(hours < 10)
			hh = "0" + hours;
		else
			hh = String.valueOf(hours);
		//Minutes
		if(minutes < 10)
			mm = "0" + minutes;
		else
			mm = String.valueOf(minutes);
		//Seconds
		if(seconds < 10)
			ss = "0" + seconds;
		else
			ss = String.valueOf(seconds);
		//Sets the time string to the formated text
		time = hh + ":" + mm + ":" + ss;
	}
	
	/**
	 * Uses getCommand then sends it to interpret and runs while there is a command still in timer.txt
	 */
	private static void command() {
		//Creates file that points to timer.txt
		File timer = new File("timer.txt");
		Scanner input = null;
		//Trys to create a scanner with the file but will return if failed
		try {
			input = new Scanner(timer);
		} catch (FileNotFoundException e1) {
			return;
		}
		//While the scanner has a new line in timer.txt it will go through and execute all commands from top to bottom
		while(input.hasNextLine()) {
			String command;
			try {
				 command = getCommand();
			} catch (IOException e) {
				input.close();
				return;
			}
			if(command != null)
				interpret(command);
			else {
				input.close();
				break;
			}
		}
		input.close();
	}
	
	/**
	 * Reads in the first line of timer.txt and then moves all following commands up and removes the first line from the file so its not ran twice
	 * @return returns the first line in timer.txt
	 * @throws IOException thows error if timer.txt does not exist and returns null
	 */
	private static String getCommand() throws IOException  {
		File timer = new File("timer.txt");
		Scanner input = new Scanner(timer);
		String hold;
		try {
			hold = input.nextLine();
		} catch (NoSuchElementException ex) {
			input.close();
			return null;
		}
		FileWriter fileStream = new FileWriter(timer);
		BufferedWriter out = new BufferedWriter(fileStream);
		//while the scanner has a next line it will go through and remove the first line, move rest of the lines up and then return the first line
		while(input.hasNextLine()) {
			String next = input.nextLine();
			if(next.equals("\n"))
				out.newLine();
			else
				out.write(next);
			out.newLine();
		}
		out.close();
		input.close();
		return hold;
	}
	
	/**
	 * Figures out what to do with the command and then executes it
	 * @param command
	 */
	private static void interpret(String command) {
		Scanner type = new Scanner(command);
		String hold = (type.next()).toLowerCase();
		//Checks to see what command is requested and executes it if it is valid
		if(hold.equals("add")) {
			addTime(command);
		} else if(hold.equals("sub")){
			subtactTime(command);
		}
		type.close();
	}
	
	/**
	 * Adds time to the timer based on what the command said
	 * @param command
	 */
	private static void addTime(String command) {
		//Splits the string to find out what time type is added
		String[] split = command.split(" ");
		debug(command);
		try {
			Integer.parseInt(split[2]);
		} catch (NumberFormatException ex) {
			return;
		}
		if(split[1].equals("seconds")) {
			seconds = (seconds+(Integer.parseInt(split[2])));//Looks at the last part of the string which if valid stores the time and adds it
		} else if(split[1].equals("minutes")) {
			minutes = (minutes+(Integer.parseInt(split[2])));
		} else if(split[1].equals("hours")) {
			hours = (hours+(Integer.parseInt(split[2])));
		}
	}
	
	/**
	 * Subtracts time to the timer based on what the command said
	 * @param command
	 */
	private static void subtactTime(String command) {
		//Splits the string to find out what time type is subtracted
		String[] split = command.split(" ");
		try {
			Integer.parseInt(split[2]);
		} catch (NumberFormatException ex) {
			return;
		}
		if(split[1].equals("seconds")) {
			seconds = (seconds-(Integer.parseInt(split[2])));//Looks at the last part of the string which if valid stores the time and subtracts it
		} else if(split[1].equals("minutes")) {
			minutes = (minutes-(Integer.parseInt(split[2])));
		} else if(split[1].equals("hours")) {
			hours = (hours-(Integer.parseInt(split[2])));
		}
	}
	
	//Main/Testing
	public static void main(String args[]) throws InterruptedException, IOException {
		//while hours, minutes, or seconds is greater then 0 run the timer, else stop
		while(true) {
			command();
			timeIncerment();
			formatCheck();
			//de incermetn seconds if time does not equal 0
			if(seconds == 0) {
				debug("Time is up");
			} else {
				seconds--;
			}
			debug(time);
			//Trys to sleep for 1 second
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
