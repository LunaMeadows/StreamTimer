package classes;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TimeClass {
	private int subTime = 0;
	public TimeClass() {
		readIn();
	}
	
	private void readIn() {
		File times = new File("times");
		Scanner scan = null;
		try {
			scan = new Scanner(times);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		subTime = scan.nextInt();
	}
	
	public int getSubTime() {
		return subTime;
	}
}
