package classes;
import java.io.BufferedWriter;
//Imports
//io
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
//twitchirc
import com.cavariux.twitchirc.Chat.Channel;
import com.cavariux.twitchirc.Chat.User;
import com.cavariux.twitchirc.Core.TwitchBot;

public class TimerBot extends TwitchBot{
	//InstanceV Variables
	//Ints
	private int subTime;
	private int resubTime;
	private int	giftedsubTime;
	private int	bitsTime;
	private int	raidTime;
	//Strings
	private String addCommandFormat;
	private String subCommandFormat;
	private String addTimeCommand;
	private String subTimeCommand;
	
	//Constructor
	public TimerBot () {
		updateInfo();
	}
	//Methods
	//Overrides
	@Override
	public void onMessage(User user, Channel channel, String message) {
		
	}
	
	@Override
	public void onCommand(User user, Channel channel, String message){
		if(user.isMod(channel) || user.toString().toLowerCase().equals(channel.toString().substring(1))) {
			command(message, channel, user);
		}
	}
	
	@Override
	public void onSub(User user, Channel channel, String message, String type) {
		command(type, channel, user);
	}
	
	@Override
	public void onBits(User user, Channel channel, String message, String bits) {
		command("bits", channel, user);
	}
	
	@Override
	public void userJoins(User user, Channel channel) {
		
	}
	
	@Override
	public void onWhisper(User user, String message) {
		
	}
	
	//Private
	/**
	 * Updates info for the bot
	 */
	private void updateInfo() {
		FileClass info = new FileClass();
		info.readInSettings();
		this.setUsername(info.getBotName());
		this.setOauth_Key(info.getBotOAuth());
		this.setClientID("wjy72kls6t4hrhf5vzt8tz35xsi2u6");
		addTimeCommand = info.getAddTimeCommand();
		subTimeCommand = info.getSubTimeCommand();
		addCommandFormat = info.getAddTimeFormat();
		subCommandFormat = info.getSubTimeFormat();
		TimeClass time = new TimeClass();
		subTime = time.getSubTime();
		resubTime = time.getResubTime();
		giftedsubTime = time.getGiftedsubTime();
		bitsTime = time.getBitsTime();
		raidTime = time.getRaidTime();
	}
	
	/**
	 * Reads in a message and checks what to do with it
	 * @param message The message or command or type to execute
	 * @param channel The channel that is currently connected to
	 * @param user The user that sent the message or command or event
	 */
	@SuppressWarnings("resource")
	private void command(String message, Channel channel, User user) {
		updateInfo();
		File update = new File("updates.txt");
		FileWriter write = null;
		try {
			write = new FileWriter(update, true);
		} catch (IOException e1) {
			debug.debug("TimerBotCommand:" + "There was an error creating the FileWriter");
			debug.debug(e1.getStackTrace());
			return;
		}
		BufferedWriter writeBuff = new BufferedWriter(write);
		String[] split = message.split(" ");
		//Runs if the command is equal to the addTimeCommand
		if(split[0].contains(addTimeCommand) && split.length == 3) {
			try {
				String[] format = addCommandFormat.split(" ");
				if(format[0].equals("$TA%") && Integer.parseInt(split[1])>0) {
					add(split[2], Integer.parseInt(split[1]), channel);
					writeBuff.write("Add Command" + ":" + user + ":" + Integer.parseInt(split[1]));
					writeBuff.newLine();
				} else if(format[1].equals("$TA%") && Integer.parseInt(split[2])>0) {
					add(split[1], Integer.parseInt(split[2]), channel);
					writeBuff.write("Add Command" + ":" + user + ":" + Integer.parseInt(split[2]));
					writeBuff.newLine();
				}
			} catch (NumberFormatException e) {
				debug.debug("TimerBotCommand:" + "There was not a number where there should of been, " + message);
				debug.debug(e.getStackTrace());
				return;
			} catch (IOException e) {
				debug.debug("TimerBotCommand:" + "There was an error creating the BufferedWriter");
				debug.debug(e.getStackTrace());
				return;
			}
			//Runs if the command is equal to the subTimeCommand
		} else if(split[0].contains(subTimeCommand)  && split.length == 3) {
			try {
				String[] format = subCommandFormat.split(" ");
				if(format[0].equals("$TA%") && Integer.parseInt(split[1])>0) {
					sub(split[2], Integer.parseInt(split[1]), channel);
					writeBuff.write("Subtract Command" + ":" + user + ":" + Integer.parseInt(split[1]));
					writeBuff.newLine();
				} else if(format[1].equals("$TA%") && Integer.parseInt(split[2])>0) {
					sub(split[1], Integer.parseInt(split[2]), channel);
					writeBuff.write("Subtract Command" + ":" + user + ":" + Integer.parseInt(split[2]));
					writeBuff.newLine();
				}
				
			} catch (NumberFormatException e) {
				debug.debug("TimerBotCommand:" + "There was not a number where there should of been, " + message);
				debug.debug(e.getStackTrace());
				return;
			} catch (IOException e) {
				debug.debug("TimerBotCommand:" + "There was an error creating the BufferedWriter");
				debug.debug(e.getStackTrace());
				return;
			}
			//Runs if the command is toggleDebug
		} else if(split[0].contains("toggleDebug")  && split.length == 1) {
			debug.debugToggle();
			//Runs if the command is checkDebug
		} else if(split[0].contains("checkDebug")  && split.length == 1) {
			this.sendMessage("Debug is currently set to " + debug.debugOn(), channel);
			//Runs if the type is a sub
		} else if(split[0].contains("sub")  && split.length == 1) {
			try {
				add("minutes", subTime, channel);
				writeBuff.write("Sub" + ":" + user + ":" + subTime);
				writeBuff.newLine();
			} catch (NumberFormatException e) {
				debug.debug("TimerBotCommandSub:" + "There was not a number where there should of been, " + message);
				debug.debug(e.getStackTrace());
				return;
			} catch (IOException e) {
				debug.debug("TimerBotCommandSub:" + "There was an error creating the BufferedWriter");
				debug.debug(e.getStackTrace());
				return;
			}
			//Runs if the type is a resub
		} else if(split[0].contains("resub")  && split.length == 1) {
			try {
				add("minutes", resubTime, channel);
				writeBuff.write("resub" + ":" + user + ":" + resubTime);
				writeBuff.newLine();
			} catch (NumberFormatException e) {
				debug.debug("TimerBotCommandResub:" + "There was not a number where there should of been, " + message);
				debug.debug(e.getStackTrace());
				return;
			} catch (IOException e) {
				debug.debug("TimerBotCommandResub:" + "There was an error creating the BufferedWriter");
				debug.debug(e.getStackTrace());
				return;
			}
			//Runs if the type is a subgift
		} else if(split[0].contains("subgift")  && split.length == 1) {
			try {
				add("minutes", giftedsubTime, channel);
				writeBuff.write("subgift" + ":" + user + ":" + giftedsubTime);
				writeBuff.newLine();
			} catch (NumberFormatException e) {
				debug.debug("TimerBotCommandSubGift:" + "There was not a number where there should of been, " + message);
				debug.debug(e.getStackTrace());
				return;
			} catch (IOException e) {
				debug.debug("TimerBotCommandSubGift:" + "There was an error creating the BufferedWriter");
				debug.debug(e.getStackTrace());
				return;
			}
			//Runs if the type is a raid
		} else if(split[0].contains("raid")  && split.length == 1) {
			try {
				add("minutes", raidTime, channel);
				writeBuff.write("raid" + ":" + user + ":" + raidTime);
				writeBuff.newLine();
			} catch (NumberFormatException e) {
				debug.debug("TimerBotCommand:Raid" + "There was not a number where there should of been, " + message);
				debug.debug(e.getStackTrace());
				return;
			} catch (IOException e) {
				debug.debug("TimerBotCommandRaid:" + "There was an error creating the BufferedWriter");
				debug.debug(e.getStackTrace());
				return;
			}
			//Runs if the type is bits
		} else if(split[0].contains("bits")  && split.length == 1) {
			try {
				add("minutes", bitsTime, channel);
				writeBuff.write("bits" + ":" + user + ":" + bitsTime);
				writeBuff.newLine();
			} catch (NumberFormatException e) {
				debug.debug("TimerBotCommandBits:" + "There was not a number where there should of been, " + message);
				debug.debug(e.getStackTrace().toString());
				return;
			} catch (IOException e) {
				debug.debug("TimerBotCommandBits:" + "There was an error creating the BufferedWriter");
				debug.debug(e.getStackTrace());
				return;
			}
		} else {
			return;
		}
		if(write != null) {
			try {
				writeBuff.close();
				write.close();
			} catch (IOException e) {
				debug.debug("TimerBotCommandClose:" + "There was an error closing Writers");
				debug.debug(e.getStackTrace());
			}
		}
	}
	
	/**
	 * Sends the ammount of time to add from the timer to timer.txt in proper format
	 * @param type type of time you want to add(seconds minutes hours)
	 * @param ammount How much time you want to add
	 */
	private void add(String type, int ammount, Channel channel) {
		File file = new File("timer.txt");
		FileWriter write;
		try {
			write = new FileWriter(file, true);
			write.append("add " + type + " " + ammount + "\n");
			write.close();
			//sendMessage("Adding time now", channel);
		} catch (IOException e) {
			debug.debug("TimerBotCommandClose:" + "There was an error creating writers");
			debug.debug(e.getStackTrace());
		}
	}
	
	/**
	 * Sends the ammount of time to subtract from the timer to timer.txt in proper format
	 * @param type type of time you want to remove(seconds minutes hours)
	 * @param ammount How much time you want to remove
	 */
	private void sub(String type, int ammount, Channel channel) {
		File file = new File("timer.txt");
		FileWriter write;
		try {
			write = new FileWriter(file, true);
			write.append("sub " + type + " " + ammount + "\n");
			write.close();
			//sendMessage("sub time now", channel);
		} catch (IOException e) {
			debug.debug("TimerBotCommandClose:" + "There was an error creating writers");
			debug.debug(e.getStackTrace());
		}
	}
}