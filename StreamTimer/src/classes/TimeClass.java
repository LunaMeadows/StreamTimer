package classes;

import java.io.BufferedWriter;
//Imports
//io
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
//util
import java.util.Scanner;

public class TimeClass {
	//Instance Variables
	//Ints
	private static int[] startTime;
	private static int subTime;
	private static int resubTime;
	private static int giftedsubTime;
	private static int bitsTime;
	private static int raidTime;
	
	//Constructor
	/**
	 * Constructor for TimeClass
	 */
	public TimeClass() {
		readIn();
	}
	
	//Methods
	//Private
	/**
	 * Reads in values and sets the corisponding varialbes
	 */
	private static void readIn() {
		//Looks for times.txt created by setup
		File times = new File("times.txt");
		Scanner scan = null;
		try {
			scan = new Scanner(times);
		} catch (FileNotFoundException e) {
			debug.debug("TimeClassReadIn:" + "There was an error creating the scanner");
			debug.debug(e.getStackTrace());
		}
		//While the file has lines read through and set variables
		while(scan.hasNext()) {
			String hold = scan.nextLine();
			if(hold.startsWith("startTime=")) {
				String[] time = hold.substring(hold.indexOf("=")+1).split(":");
				int[] timeArray = {Integer.parseInt(time[0]), Integer.parseInt(time[1]), Integer.parseInt(time[2])};
				startTime = timeArray;
			} else if(hold.startsWith("subTime=")) {
				subTime = Integer.parseInt(hold.substring(hold.indexOf("=")+1));
			} else if(hold.startsWith("resubTime=")) {
				resubTime = Integer.parseInt(hold.substring(hold.indexOf("=")+1));
			} else if(hold.startsWith("giftedsubTime=")) {
				giftedsubTime = Integer.parseInt(hold.substring(hold.indexOf("=")+1));
			} else if(hold.startsWith("bitsTime=")) {
				bitsTime = Integer.parseInt(hold.substring(hold.indexOf("=")+1));
			} else if(hold.startsWith("raidTime=")) {
				raidTime = Integer.parseInt(hold.substring(hold.indexOf("=")+1));
			}
		}
	}
	
	//Public
	/**
	 * Saves times to the file
	 */
	public static void save() {
		File times = new File("times.txt");
		FileWriter write = null;
		BufferedWriter bWrite = null;
		try {
			write = new FileWriter(times);
			bWrite = new BufferedWriter(write);
			bWrite.write("startTime=" + startTime[0] + ":" + startTime[1] + ":" + startTime[2] + "\n");
			bWrite.write("subTime=" + subTime + "\n");
			bWrite.write("resubTime=" + resubTime + "\n");
			bWrite.write("giftedsubTime=" + giftedsubTime + "\n");
			bWrite.write("bitsTime=" + bitsTime + "\n");
			bWrite.write("raidTime=" + raidTime + "\n");
			bWrite.close();
			write.close();
		} catch (IOException e) {
			debug.debug("TimeClassSave:" + "There was an error creating the writer");
			debug.debug(e.getStackTrace());
		}
	}
	
	//Getters
	/**
	 * @return the startTime
	 */
	public int[] getStartTime() {
		return startTime;
	}

	/**
	 * @return the subTime
	 */
	public int getSubTime() {
		return subTime;
	}

	/**
	 * @return the resubTime
	 */
	public int getResubTime() {
		return resubTime;
	}

	/**
	 * @return the giftedsubTime
	 */
	public int getGiftedsubTime() {
		return giftedsubTime;
	}

	/**
	 * @return the bitsTime
	 */
	public int getBitsTime() {
		return bitsTime;
	}

	/**
	 * @return the raidTime
	 */
	public int getRaidTime() {
		return raidTime;
	}
	
	//Setters
	/**
	 * @param startTime the startTime to set
	 */
	public static void setStartTime(int[] startTime) {
		TimeClass.startTime = startTime;
	}

	/**
	 * @param subTime the subTime to set
	 */
	public static void setSubTime(int subTime) {
		TimeClass.subTime = subTime;
	}

	/**
	 * @param resubTime the resubTime to set
	 */
	public static void setResubTime(int resubTime) {
		TimeClass.resubTime = resubTime;
	}

	/**
	 * @param giftedsubTime the giftedsubTime to set
	 */
	public static void setGiftedsubTime(int giftedsubTime) {
		TimeClass.giftedsubTime = giftedsubTime;
	}

	/**
	 * @param bitsTime the bitsTime to set
	 */
	public static void setBitsTime(int bitsTime) {
		TimeClass.bitsTime = bitsTime;
	}

	/**
	 * @param raidTime the raidTime to set
	 */
	public static void setRaidTime(int raidTime) {
		TimeClass.raidTime = raidTime;
	}
}
