package classes;
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
	//Boolean
	private static boolean debug = true;
	//Constructor
	public TimerBot () {
		this.setUsername("Dragonslayer7516");
		this.setOauth_Key("oauth:77b92nac7w69l4wlott8xv11x3uxzo");
		this.setClientID("wjy72kls6t4hrhf5vzt8tz35xsi2u6");
		TimeClass time = new TimeClass();
		subTime = time.getSubTime();
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
	public void onSub(User user, Channel channel, String message) {
		debug("Found IT");
		command("subscribertime", channel, user);
	}
	
	@Override
	public void onBits(User user, Channel channel, String message, String bits) {
	}
	
	@Override
	public void userJoins(User user, Channel channel) {
		
	}
	
	@Override
	public void onWhisper(User user, String message) {
		
	}
	
	//Private
	/**
	 * Easy debug method to output a string to console if debuging is turned on.
	 * 
	 * @param message is the message that is printed to console
	 */
	@SuppressWarnings("unused")
	private static void debug(String message) {
		if(debug) {
			System.out.println(message);}
	}
	
	private void command(String message, Channel channel, User user) {
		File update = new File("updates.txt");
		FileWriter write = null;
		try {
			write = new FileWriter(update, true);
		} catch (IOException e1) {
			e1.printStackTrace();
			return;
		}
		
		String[] split = message.split(" ");
		if(split[0].contains("addtime") && split.length == 3) {
			try {
				add(split[2], Integer.parseInt(split[1]), channel);
				write.write("Command" + ":" + user + ":" + Integer.parseInt(split[1]) + "\n");
			} catch (NumberFormatException e) {
				e.printStackTrace();
				return;
			} catch (IOException e) {
				e.printStackTrace();
				return;
			}
		} else if(split[0].contains("subtime")  && split.length == 3) {
			try {
				sub(split[2], Integer.parseInt(split[1]), channel);
				write.write("Command" + ":" + user + ":" + Integer.parseInt(split[1]) + "\n");
			} catch (NumberFormatException e) {
				e.printStackTrace();
				return;
			} catch (IOException e) {
				e.printStackTrace();
				return;
			}
		} else if(split[0].contains("subscribertime")  && split.length == 1) {
			try {
				add("minutes", subTime, channel);
				write.write("Sub" + ":" + user + ":" + subTime + "\n");
			} catch (NumberFormatException e) {
				e.printStackTrace();
				return;
			} catch (IOException e) {
				e.printStackTrace();
				return;
			}
		} else {
			return;
		}
		if(write != null) {
			try {
				write.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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
			// TODO Auto-generated catch block
			e.printStackTrace();
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}