package classes;

public class BotStarter extends Thread{
	public static void main(String[] args) {
		TimerBot bot = new TimerBot();
		bot.connect();
		bot.joinChannel("#dragonslayer7516");
		System.out.println("TEST");
		bot.start();
	}
	
	public void run() {
		TimerBot bot = new TimerBot();
		bot.connect();
		bot.joinChannel("#dragonslayer7516");
		bot.start();
	}
}