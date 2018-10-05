package classes;
public class BotStarter extends Thread{	
	//Instance Variables
	//TimerBot
	private TimerBot bot;
	
	//Methods
	//Public
	/**
	 * Starts a new thread for the bot
	 */
	public void run() {
		//Makes instance of FileClass to get channel name from settings.ini
		FileClass channelName = new FileClass();
		channelName.readInSettings();
		//Creates irc bot
		bot = new TimerBot();
		//Connects the bot to twich then to the channel and the starts the bot
		bot.connect();
		bot.joinChannel(channelName.getTwitchChannel());
		bot.start();
	}
}