package com.cavariux.twitchirc.Core;
//Imports
//io
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
//net
import java.net.Socket;
//util
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
//twitchirc
import com.cavariux.twitchirc.Chat.Channel;
import com.cavariux.twitchirc.Chat.User;

import classes.debug;

/**
 * The main object to start making your bot
 * @author Leonardo Mariscal Edited by Derrick Bush
 * @version 1.0-Beta
 */
public class TwitchBot {
	//Instance Variables
	//String
	@SuppressWarnings("unused")
	private String whispers_ip = "";
	private String user;
	private String oauth_key;
	private String version = "v1.0-Beta";
	private String commandTrigger = "!";
	private String clientID = "";
	private String testLine = null; //Used for dev testing, never called in application
	//Int
	@SuppressWarnings("unused")
	private int whispers_port = 443;
	//Boolean
	private boolean wen = true;
	private boolean stopped = true;
	//Buffered
	private BufferedWriter writer;
	private BufferedReader reader;
	//ArrayList
	private ArrayList<String> channels = new ArrayList<String>();
	//Logger
	private static final Logger LOGGER = Logger.getLogger(TwitchBot.class.getName());
	
	//Methods
	//Private
	/**
	 * Splits the message if it is PRIVMSG tag
	 * @param message is the line read in from twitch irc
	 * @return
	 */
	private static ArrayList<String> privmsgSplit(String message) {
		String[] split = message.split(";");
		ArrayList<String> info = new ArrayList<String>();
		for(int i = 0; i < split.length; i++) {
			if(split[i].contains("display-name=")) {
				info.add(split[i].substring(split[i].indexOf("=")+1));
			} else if (split[i].contains("bits=")) {
				info.add(split[i].substring(split[i].indexOf("=")+1));
			} else if (split[i].contains("PRIVMSG")) {
				String hold = split[i].substring(split[i].indexOf("#"));
				if(hold.contains(":")) {
	    			info.add(hold.substring(hold.indexOf("#"), (hold.indexOf(":")-1)));
	    			info.add(hold.substring(hold.indexOf(":")+1));
	    		} else {
	    			info.add(hold.substring(hold.indexOf("#")));
	    			info.add("No message");
	    		}
			}
		}
		return info;
	}
	
	/**
	 * Splits the message if it is NOTICE tag
	 * @param message is the line read in from twitch irc
	 * @return
	 */
	private static ArrayList<String> noticeSplit(String message) {
		String[] split = message.split(":");
		ArrayList<String> info = new ArrayList<String>();
		info.add(split[0].substring(split[0].indexOf("=")+1).trim());
		info.add(split[1].substring(split[1].indexOf("#")).trim());
		info.add(split[2].trim());
		return info;
	}
	
	/**
	 * Splits the message if it is SUB tag
	 * @param message is the line read in from twitch irc
	 * @return
	 */
	private static ArrayList<String> subSplit(String message) {
		String[] split = message.split(";");
		ArrayList<String> info = new ArrayList<String>();
		for(int i = 0; i < split.length; i++) {
			if(split[i].contains("display-name=")) {
				info.add(split[i].substring((split[i].indexOf("="))+1));
			} else if(split[i].contains("USERNOTICE")) {
				info.add(split[i].substring(split[i].indexOf("#")));
				info.add("No message");
			}
		}		
		return info;
	}
	
	/**
	 * Splits the message if it is RESUB- tag
	 * @param message is the line read in from twitch irc
	 * @return
	 */
	private static ArrayList<String> resubSplit(String message) {
		String[] split = message.split(";");
		ArrayList<String> info = new ArrayList<String>();
		for(int i = 0; i < split.length; i++) {
			if(split[i].contains("display-name=")) {
    			info.add(split[i].substring((split[i].indexOf("="))+1));
    		} else if(split[i].contains("USERNOTICE")) {
    			String hold = split[i].substring(split[i].indexOf("#"));
    			if(hold.contains(":")) {
    			info.add(hold.substring(hold.indexOf("#"), (hold.indexOf(":")-1)));
    			info.add(hold.substring(hold.indexOf(":")+1));
    			} else {
    				info.add(hold.substring(hold.indexOf("#")));
    				info.add("No message");
    			}
    		}
		}
		return info;
	}
	
	/**
	 * Splits the message if it is GIFTSUB tag
	 * @param message is the line read in from twitch irc
	 * @return
	 */
	private static ArrayList<String> giftsubSplit(String message) {
		String[] split = message.split(";");
		ArrayList<String> info = new ArrayList<String>();
		for(int i = 0; i < split.length; i++) {
			if(split[i].contains("display-name=")) {
				info.add(split[i].substring((split[i].indexOf("="))+1));
			} else if(split[i].contains("USERNOTICE")) {
				info.add(split[i].substring(split[i].indexOf("#")));
				info.add("No message");
			}
		}
		return info;
	}
	
	/**
	 * Splits the message if it is RAID tag
	 * @param message is the line read in from twitch irc
	 * @return
	 */
	private static ArrayList<String> raidSplit(String message) {
		String[] split = message.split(";");
		ArrayList<String> info = new ArrayList<String>();
		for(int i = 0; i < split.length; i++) {
			if(split[i].contains("display-name=")) {
				info.add(split[i].substring((split[i].indexOf("="))+1));
			} else if(split[i].contains("USERNOTICE")) {
				info.add(split[i].substring(split[i].indexOf("#")));
				info.add("No message");
			}
		}
		return info;
	}
	
	//Public
	public TwitchBot(){}
	
	/**
	 * Here you can connect without having to connect to a chat group
	 */
	public void connect() {
		wen = false;
		connect("irc.twitch.tv", 6667);
	}
	
	/**
	 * The connect method alouds you to connect to the IRC servers on twitch
	 * @param ip The ip of the Chat group
	 * @param port The port of the Chat group
	 */
	public void connect(String ip, int port)
	{
		//If the bot is already running, stop method
		if (isRunning()) return;
		//Checks if both user and oauthkey are not null
		try{
			if (user == null || user == "")
			{
				LOGGER.log(Level.SEVERE, "Please select a valid Username");
				System.exit(1);
				return;
			}
			if (oauth_key == null || oauth_key == "")
			{
				LOGGER.log(Level.SEVERE,"Please select a valid Oauth_Key");
				System.exit(2);
				return;
			}
			//Creats socket connection to the twitchIRC server
			@SuppressWarnings("resource")
			Socket socket = new Socket(ip, port);
			//Gives twitch info on what you bot is and needs
			this.writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			//Reads in chat
			this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			//Arguments of what is given from the server
			this.writer.write("PASS " + oauth_key + "\r\n");
			this.writer.write("NICK " + user + "\r\n");
			this.writer.write("USER " + this.getVersion() + " \r\n");
			this.writer.write("CAP REQ :twitch.tv/commands \r\n");
			this.writer.write("CAP REQ :twitch.tv/membership \r\n");
			this.writer.write("CAP REQ :twitch.tv/tags \r\n");
			this.writer.flush();
			String line = "";
			//Runs while the reader is reciving connection information.
			while ((line = this.reader.readLine()) != null)
			{
				 if (line.indexOf("004") >= 0) {
		                LOGGER.log(Level.INFO,"Connected >> " + user + " ~ irc.twitch.tv");
		                break;
		            }else {
		                LOGGER.log(Level.INFO,line);
		            }
			}
		} catch (IOException e)
		{
			debug.debug("TwitchBotConnect:" + "There was an error connecting to twitch");
			debug.debug(e.getStackTrace());
		}
	}
	
	/**
     * Message had to be appended with .toString()
	 * @param message the command you will sent
	 */
	public void sendRawMessage(Object message)
	{
		try {
			this.writer.write(message + " \r\n");
			this.writer.flush();
		} catch (IOException e) {
			debug.debug("TwitchBotSendRawMessage:" + "There was an error sending raw message");
			debug.debug(e.getStackTrace());
		}
		LOGGER.log(Level.INFO, message.toString());
	}
	
	/**
	 * Send a message to a channel on Twitch (Don't need to be on that channel)
	 * @param message The message that will be sent
	 * @param channel The channel where the message will be sent
	 */
	public void sendMessage(Object message, Channel channel)
	{
		try {
			this.writer.write("PRIVMSG " + channel + " :" + message.toString() + "\r\n");
			this.writer.flush();
		} catch (IOException e) {
			debug.debug("TwitchBotSendMessage:" + "There was an error sending message");
			debug.debug(e.getStackTrace());
		}
		LOGGER.log(Level.INFO,"> MSG " + channel + " : " + message.toString());
	}
	
	/**
	 * The method to join an IRC channel on Twitch
	 * @param channel The channel name
	 * @return You can get the channel you just created
	 */
	public final Channel joinChannel (String channel)
	{
		Channel cnl = Channel.getChannel(channel.toLowerCase(), this);
		sendRawMessage("JOIN " + cnl.toString().toLowerCase() + "\r\n");
		this.channels.add(cnl.toString());
		LOGGER.log(Level.INFO,"> JOIN " + cnl);
		return cnl;
	}
	
	/**
	 * Leaves the channel you want
	 * @param channel The channel you want to leave
	 */
	@SuppressWarnings("unlikely-arg-type")
	public final void partChannel (String channel)
	{
		Channel cnl = Channel.getChannel(channel.toLowerCase(), this);
		this.sendRawMessage("PART " + cnl);
		this.channels.remove(cnl);
		LOGGER.log(Level.INFO,"> PART " + channel);
	}
	
	/**
	 * Simply stops the bot.
	 */
	public void stop() {
		this.stopped = true;
		this.sendRawMessage("Stopping"); //so that the bot has one message to process to actually stop
	}
	
	/**
	 * Stops the bot and sends a message to all channels the bot has joined
	 * @param message Message to send to all channels
	 */
	public void stopWithMessage(String message) {
		this.stopped = true;
		for (String cnl : channels) {
			this.sendMessage(message, Channel.getChannel(cnl, this));
		}
	}
	
	public boolean isRunning() {
		return !stopped;
	}
	
	/**
	 * This will let you whisper to the specified user
	 * @param user The user to send the message
	 * @param message The messsage to send
	 */
	public void whisper(User user, String message)
	{
		if (!channels.isEmpty()) {
			this.sendMessage("/w " + user + " " + message, Channel.getChannel(channels.get(0), this));
			
		} else if (!wen) {
			LOGGER.log(Level.INFO,"You have to be either connected to at least one channel or join another Server to be able to whisper!");
		} else {
			sendRawMessage("PRIVMSG #jtv :/w " + user + " " + message);
		}
	}
	
	
	//Protected
	/**
	 * This method is called when a sub is recived in the Twich Chat
	 * @param user
	 * @param channel
	 * @param message
	 */
	protected void onBits(User user, Channel channel, String message, String bits)
	{
		
	}
	
	/**
	 * This method is called when a sub is recived in the Twich Chat
	 * @param user
	 * @param channel
	 * @param message
	 */
	protected void onSub(User user, Channel channel, String message, String type)
	{
		
	}
	
	/**
	 * This method is called when a message is sent on the Twitch Chat.
	 * @param user The user is sent, if you put it on a String it will give you the user's nick
	 * @param channel The channel where the message was sent
	 * @param message The message
	 */
	protected void onMessage(User user, Channel channel, String message)
	{
		
	}
	
	/**
	 * This method is called when a command is sent on the Twitch Chat.
	 * @param user The user is sent, if you put it on a String it will give you the user's nick
	 * @param channel The channel where the command was sent
	 * @param command The command
	 */
	protected void onCommand(User user, Channel channel, String command)
	{
		
	}
	
	/**
	 * This method is called when someone hosts the Tiwtch Streamer
	 * @param hoster
	 * @param hosted
	 */
	protected void onHost(User hoster, Channel hosted)
	{
	
	}
	
	/**
	 * A user joins the channel
	 * @param user The user that has join
	 * @param channel The channel he joined
	 */
	protected void userJoins(User user, Channel channel)
	{
		
	}
	
	/**
	 * A user leaves/parts the channel
	 * @param user The user that has left
	 * @param channel The channel he left
	 */
	protected void userParts(User user, Channel channel)
	{
		
	}
	
	/**
	 * When overrided this method lets you check for whispers.
	 * @param user The user that sent it
	 * @param message The message he sent
	 */
	protected void onWhisper(User user, String message)
	{
	}
	
	//Getter
	public final List<Channel> getChannels(){
		return Channel.getChannels();
	}
	
	/**
	 * Method to return the clientid.
	 * @return The clientid
	 */
	public final String getClientID() {
		if (this.clientID != null)
			return this.clientID;
		else {
			LOGGER.log(Level.INFO,"You need to give a clientID to use the TwitchAPI");
			return "";
		}
	}
	
	/**
     * Returns the logger for this library.
     * @return Logger
     */
    public static Logger getLOGGER() {
        return LOGGER;
    }
    
    /**
	 * No need to use this dev things
	 * @return a BufferedWrtier
	 */
	public final BufferedWriter getWriter ()
	{
		return this.writer;
	}
	
	/**
	 * Get the version of the TwitchIRC lib
	 * @return the version of the TwitchIRC lib
	 */
	public final String getVersion()
	{
		return "TwitchIRC "+version;
	}
	
	//Setter
	/**
	 * Set the username that the connect method will use
	 * @param username Needs your <a href="http://www.twitch.tv">Twitch</a> Username to connect
	 */
	public final void setUsername(String username) {
		this.user = username;
	}
	
	/**
	 * Sets the required ClientID for the use of the api.
	 * @param clientID
	 */
	public final void setClientID(String clientID) {
		this.clientID = clientID;
	}
	
	/**
	  * Set the "password" that the <a href="connect">connect</a> method will use.
	  * @param oauth_key To get this key go to the <a href="http://twitchapps.com/tmi/">TwitchApps</a> and get it on the TMI section
	  */
	public final void setOauth_Key (String oauth_key)
	{
		this.oauth_key = oauth_key;
	}
	
	/**
     * Experimetal, see if we can change the nick of the bot.
     * @param newNick
     */
    public void setNick(String newNick){
        try {
            this.writer.write("NICK " + newNick + "\r\n");
        }catch (IOException ioe){
        	debug.debug("TwitchBotConnect:" + "There was an error setting nickname");
			debug.debug(ioe.getStackTrace());
        }
    }
	
    /**
	 * Sets the whispers ip (000.000.000.000:0000)
	 * @param ip The whole ip
	 */
    protected final void setWhispersIp(String ip)
	{
		if (!ip.contains(":")) {
			LOGGER.log(Level.INFO,"Invaid ip!");
			return;
		}
		String[] args = ip.split(":");
		whispers_ip = args[0];
		whispers_port = Integer.parseInt(args[1]);
	}
	
	/**
	 * Sets the whispers ip
	 * @param ip The ip to connect
	 * @param port The port to connect with
	 */
	protected final void setWhispersIp(String ip, int port)
	{
		whispers_ip = ip;
		whispers_port = port;
	}
	
	/**
	 * Sets the string that will mark a message as command if it begins with it
	 * @param trigger string to mark a message as command
	 */
	public void setCommandTrigger(String trigger) {
		this.commandTrigger = trigger;
	}
    
	//Start command
	/**
	 * Starts the full mechanism of the bot, this is the last method to be called
	 */
	public final void start()
	{
		if (isRunning()) return;
		String line = "";
		stopped = false;
		try {
			while ((line = this.reader.readLine( )) != null && !stopped) {
				if(testLine != null)
					line = testLine;
				//Makes sure that the bot is still connected to chat
			    if (line.toLowerCase( ).startsWith("ping")) {
			    	LOGGER.log(Level.INFO,"> PING");
			        LOGGER.log(Level.INFO,"< PONG " + line.substring(5));
			        this.writer.write("PONG " + line.substring(5) + "\r\n");
			        this.writer.flush();
			        //Runs if the line contains PRIVMSG for someone sending a message in chat or bits
			    } else if (line.contains("PRIVMSG")) {
			    	//Sends the line to privmsgSplit to split it into an arraylist with only useful information
			    	ArrayList<String> info = privmsgSplit(line);
			    	if(info.size() == 3) /*Runs if no bits were sent*/{
			    		//Creates variables for passing through
			    		User user = User.getUser(info.get(0));
			    		Channel channel = Channel.getChannel(info.get(1), this);
			    		String message = info.get(2);
			    		LOGGER.log(Level.INFO, "> " + channel + " | " + user + " >> " + message);
			    		//Checks if the message is a command or not and passes it to the wright things
			    		if(message.startsWith(commandTrigger)) {
			    			onCommand(user, channel, message);
			    		} else {
			    			onMessage(user, channel, message);
			    		}
			    	} else if (info.size() == 4) /*Runs if bits were sent*/{
			    		//Creates variables for passing through
			    		String bits = info.get(0);
			    		User user = User.getUser(info.get(1));
			    		Channel channel = Channel.getChannel(info.get(2), this);
			    		String message = info.get(3);
			    		LOGGER.log(Level.INFO, "> " + channel + " | " + user + " >> " + message + " " + bits);
			    		//Sends to only bits as there cant be bits and a command
			    		onBits(user, channel, message, bits);
			    	}
			    	//Runs if the bot joins a channel
			    } else if (line.contains(" JOIN ")) {
			    	String[] p = line.split(" ");
			    	String[] pd = line.split("!");
			    	if (p[1].equals("JOIN"))
			    		userJoins(User.getUser(pd[0].substring(1)), Channel.getChannel(p[2], this));
			    	//Runs if the bot leavs the channel
				} else if (line.contains(" PART ")) {
			    	String[] p = line.split(" ");
			    	String[] pd = line.split("!");
			    	if (p[1].equals("PART"))
			    		userParts(User.getUser(pd[0].substring(1)), Channel.getChannel(p[2], this));
			    	//Runs if the bot recives a whisper
				} else if (line.contains(" WHISPER ")) {
					String[] parts = line.split(":");
					final User wsp_user = User.getUser(parts[1].split("!")[0]);
					String message = parts[2];
					onWhisper(wsp_user, message);
					//Runs if the channel acctivates or deactivates host mode, slow mode, or sub mode
				} else if (line.contains(":tmi.twitch.tv NOTICE")) {
					ArrayList<String> info = noticeSplit(line);
					if (info.get(0).equals("slow_on")) {
						LOGGER.log(Level.INFO, "> Slow mode was turned on by " + info.get(1) + " for " + info.get(2).substring(info.get(2).indexOf("every")+6));
					} else if (info.get(0).equals("slow_off")) {
						LOGGER.log(Level.INFO, "> Slow mode was turned off by " + info.get(1));
					} else if (info.get(0).equals("subs_on")) {
						LOGGER.log(Level.INFO, "> Sub mode was turned on by " + info.get(1));
					} else if (info.get(0).equals("subs_off")) {
						LOGGER.log(Level.INFO, "> Sub mode was turned off by " + info.get(1));
					} else if (info.get(0).equals("host_on")) {
						LOGGER.log(Level.INFO, "> Host mode was turned on by " + info.get(1) + ". " + info.get(2));
					} else if (info.get(0).equals("host_off")) {
						LOGGER.log(Level.INFO, "> Host mode was turned off by " + info.get(1));
					}
			    	//Runs if a user in the channel gets mod or gets mod revoked
			    } else if (line.startsWith(":jtv MODE ")) {
			    	String[] p = line.split(" ");
			    	if (p[3].equals("+o")) {
			    		LOGGER.log(Level.INFO,"> +o " + p[4]);
			    	} else {
			    		LOGGER.log(Level.INFO,"> -o " + p[4]);
			    	}
			    	//Reconnects if the bot gets disconnected
			    } else if (line.toLowerCase().contains("disconnected")) {
				    LOGGER.log(Level.INFO, line);
				    this.connect();
				    //Runs if a person gifts a sub
			    } else if (line.toLowerCase().contains("msg-id=subgift")) {
			    	System.out.println(line);
			    	ArrayList<String> info = giftsubSplit(line);
			    	onSub(User.getUser(info.get(0)), Channel.getChannel(info.get(2), this), info.get(3), "subgift");
			    	//Runs if a person resubs
			    } else if (line.toLowerCase().contains("msg-id=resub")) {
			    	ArrayList<String> info = resubSplit(line);
			    	onSub(User.getUser(info.get(0)), Channel.getChannel(info.get(1), this), info.get(2), "resub");
			    	//Runs if a person subs
			    } else if (line.toLowerCase().contains("msg-id=sub")) {
			    	ArrayList<String> info = subSplit(line);
			    	onSub(User.getUser(info.get(0)), Channel.getChannel(info.get(1), this), info.get(2), "sub");
			    	//Runs if a person raids the channel
			    } else if (line.toLowerCase().contains("msg-id=raid")) {
			    	ArrayList<String> info = raidSplit(line);
			    	onSub(User.getUser(info.get(0)), Channel.getChannel(info.get(1), this), info.get(2), "raid");
			    } else {
			        LOGGER.log(Level.INFO,"> " + line);
			    }
			    if(testLine != null)
					testLine = null;
			}
		} catch (IOException e) {
			debug.debug("TwitchBotStart:" + "There was an error while the bot was running, " + line);
			debug.debug(e.getStackTrace());
		}
	}
}
