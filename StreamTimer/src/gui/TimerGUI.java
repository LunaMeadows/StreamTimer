package gui;
//Imports
//awt
import java.awt.EventQueue;
//Swing
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
//Awt
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Color;
import java.awt.event.WindowFocusListener;
import java.awt.event.WindowEvent;
//IO
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
//classes
import classes.StreamTimer;
import classes.StyleClass;
import classes.TimeClass;
import classes.debug;
import classes.BotStarter;
import classes.FileClass;
//Util
import java.util.Scanner;
import java.util.ArrayList;

public class TimerGUI extends Thread{
	//Instance Variables
	//JFrame
	private JFrame frame;
	private JFrame editFrame;
	//Lables
	private JLabel lblTimerText;
	//JPanel
	private JPanel panel;
	//JTable
	private JTable table;
	//JScrollPane
	private JScrollPane scrollPane;
	//JButton
	private JButton Settings;
	private JButton btnTwitch;
	private JButton btnStart;
	private JButton btnRestart;
	private JButton btnEdit;
	private JButton btnClear;
	//String
	private String currentChannel;
	private String currentStyle;
	//Colors
	//StylesClass
	private StyleClass styles;
	//FileClass
	private FileClass stream;
	//Arrays
	private ArrayList<JLabel> labels;
	private ArrayList<JButton> buttons;
	//PopupWindows
	private settingsPopup settings = new settingsPopup();
	private twitchPopup twitch = new twitchPopup();
	//StreamTimer/BotStarter
	private StreamTimer time = null;
	private BotStarter bot = null;
	//Boolean
	private boolean editOpen = false;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TimerGUI window = new TimerGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws InterruptedException 
	 */
	public TimerGUI() throws InterruptedException {
		initialize();
	}
	
	/**
	 * Activates the window.
	 * @throws InterruptedException 
	 */
	public void activate() throws InterruptedException {
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws InterruptedException 
	 */
	@SuppressWarnings("static-access")
	private void initialize() throws InterruptedException {
		//Initializes colors from selected style
		styles = new StyleClass();
		currentStyle = styles.getStyle();
		
		//Sets currentChannel for update checking
		stream = new FileClass();
		stream.readInSettings();
		currentChannel = stream.getTwitchChannel();
		
		//Initializes the frame
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 490, 600);
		frame.setTitle("Stream Timer");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		//Initializes the panel
		panel = new JPanel();
		panel.setBounds(10, 10, 464, 101);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		//Labels
		//LabelsArray
		labels = new ArrayList<JLabel>();
		//TimerText
		lblTimerText = new JLabel("00:00:00");
		labels.add(lblTimerText);
		lblTimerText.setHorizontalAlignment(SwingConstants.CENTER);
		lblTimerText.setBounds(0, 0, 464, 101);
		panel.add(lblTimerText);
		lblTimerText.setFont(new Font("SansSerif", Font.PLAIN, 90));
		
		//Buttons
		//ButtonsArray
		buttons = new ArrayList<JButton>();
		//Settings Button
		Settings = new JButton("Settings");
		buttons.add(Settings);
		Settings.setFont(new Font("SansSerif", Font.PLAIN, 20));
		Settings.setBounds(10, 500, 150, 50);
		Settings.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		frame.getContentPane().add(Settings);
		//Twitch Button
		btnTwitch = new JButton("Twitch");
		buttons.add(btnTwitch);
		btnTwitch.setFont(new Font("SansSerif", Font.PLAIN, 20));
		btnTwitch.setBounds(324, 500, 150, 50);
		btnTwitch.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		frame.getContentPane().add(btnTwitch);
		//Start Button
		btnStart = new JButton("Start");
		btnStart.setFont(new Font("SansSerif", Font.PLAIN, 20));
		buttons.add(btnStart);
		btnStart.setBounds(10, 122, 130, 90);
		btnStart.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		frame.getContentPane().add(btnStart);
		//Restart Button
		btnRestart = new JButton("Restart");
		btnRestart.setFont(new Font("SansSerif", Font.PLAIN, 20));
		buttons.add(btnRestart);
		btnRestart.setBounds(178, 122, 130, 90);
		btnRestart.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		frame.getContentPane().add(btnRestart);
		//Edit Button
		btnEdit = new JButton("Edit");
		btnEdit.setFont(new Font("SansSerif", Font.PLAIN, 20));
		buttons.add(btnEdit);
		btnEdit.setBounds(344, 122, 130, 90);
		btnEdit.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		frame.getContentPane().add(btnEdit);
		//Clear Button
		btnClear = new JButton("Clear");
		btnClear.setFont(new Font("SansSerif", Font.PLAIN, 20));
		buttons.add(btnClear);
		btnClear.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnClear.setBounds(167, 500, 150, 50);
		frame.getContentPane().add(btnClear);
		
		//Table
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 222, 464, 250);
		table = new JTable(new DefaultTableModel(new Object[] {"Type", "User", "Time"}, 0));
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setRowSelectionAllowed(false);
		table.setRowHeight(20);
		table.setAutoscrolls(true);
		scrollPane.setViewportView(table);
		frame.getContentPane().add(scrollPane);
		
		//Starts bot and timer and updates styles
		startBot();
		startTimer();
		updateStyles();
		
		//Sets timer label to current time every millisecond
		int delay = 1; //milliseconds
		  ActionListener taskPerformer = new ActionListener() {
		      public void actionPerformed(ActionEvent evt) {
		    	  lblTimerText.setText(StreamTimer.getTime());
		      }
		  };
		  new Timer(delay, taskPerformer).start();
		  
		  //Adds updates to table
		  int tableDelay = 1000; //milliseconds
		  ActionListener tableTaskPerformer = new ActionListener() {
		      public void actionPerformed(ActionEvent evt) {
		    	  //Gets the current table
		    	  DefaultTableModel model = (DefaultTableModel) table.getModel();
		    	  model.setRowCount(0);
		    	  File update = new File("updates.txt");
		    	  Scanner scan = null;
		    	  try {
					scan = new Scanner(update);
				} catch (FileNotFoundException e) {
					debug.debug("TimerGUIUpdateChecker:" + "Could not find the file");
					debug.debug(e.getStackTrace());
				}
		    	  //While the update file has a new item add it to the table
		    	  while(scan.hasNextLine()) {
		    		  String hold = scan.nextLine();
		    		  String[] split = hold.split(":");
		    		  model.addRow(new Object[]{split[0], split[1], split[2]});
		    	  }
		    	  //If the scanner is not null close the scanner, else do nothing
		    	  if(scan != null)
		    		  scan.close();
		    	  //Reads in settings and checks if the twitch channel has changed
		    	  stream.readInSettings();
		    	  if(!currentChannel.equals(stream.getTwitchChannel())) {
		    		  currentChannel = stream.getTwitchChannel();
		    		  startBot();
		    	  }

		    	  //Checks if the style has changed and if so updates the style
		    	  styles.updateStyles();
		    	  if(!currentStyle.equals(styles.getStyle())) {
			    	  currentStyle = styles.getStyle();
		    		  updateStyles();
		    	  }
		      }
		  };
		  new Timer(tableDelay, tableTaskPerformer).start();
		  
		  //Restarts the timer and resets text on Start button to start
			btnRestart.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					startTimer();
					time.setRun(false);
					btnStart.setText("Start");
				}
			});
			
			//Creats a new window to edit time and sets new time if closed and pauses timer else if exit button clicked resumes timer
		  btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!editOpen && !twitch.getOpen() && !settings.getOpen()) {
					//Initialization
					editOpen = true;
					time.setRun(false);
					//Frame
					editFrame = new JFrame();
					editFrame.getContentPane().setBackground(styles.getWindow_background());
					editFrame.setResizable(false);
					editFrame.setBounds(100, 100, 356, 184);
					editFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					editFrame.setTitle("Edit Time");
					editFrame.getContentPane().setLayout(null);
					
					//TextFields
					JTextField textField = new JTextField();
					textField.setForeground(styles.getTextbox_text());
					textField.setBackground(styles.getTextbox_background());
					textField.setHorizontalAlignment(SwingConstants.RIGHT);
					textField.setText(time.getTime().split(":")[0]);
					textField.setFont(new Font("SansSerif", Font.PLAIN, 70));
					textField.setBounds(10, 11, 84, 95);
					editFrame.getContentPane().add(textField);
					textField.setColumns(10);
					
					JTextField MinutesTF = new JTextField();
					MinutesTF.setForeground(styles.getTextbox_text());
					MinutesTF.setBackground(styles.getTextbox_background());
					MinutesTF.setHorizontalAlignment(SwingConstants.RIGHT);
					MinutesTF.setText(time.getTime().split(":")[1]);
					MinutesTF.setFont(new Font("SansSerif", Font.PLAIN, 70));
					MinutesTF.setColumns(10);
					MinutesTF.setBounds(133, 11, 84, 95);
					editFrame.getContentPane().add(MinutesTF);
					
					JTextField SecondsTF = new JTextField();
					SecondsTF.setForeground(styles.getTextbox_text());
					SecondsTF.setBackground(styles.getTextbox_background());
					SecondsTF.setHorizontalAlignment(SwingConstants.RIGHT);
					SecondsTF.setText(time.getTime().split(":")[2]);
					SecondsTF.setFont(new Font("SansSerif", Font.PLAIN, 70));
					SecondsTF.setColumns(10);
					SecondsTF.setBounds(256, 11, 84, 95);
					editFrame.getContentPane().add(SecondsTF);
					
					//JLabel
					JLabel label = new JLabel(":");
					label.setForeground(styles.getText());
					label.setFont(new Font("SansSerif", Font.PLAIN, 70));
					label.setBounds(104, 14, 19, 89);
					editFrame.getContentPane().add(label);
					
					JLabel label_1 = new JLabel(":");
					label_1.setForeground(styles.getText());
					label_1.setFont(new Font("SansSerif", Font.PLAIN, 70));
					label_1.setBounds(227, 11, 19, 89);
					editFrame.getContentPane().add(label_1);
					
					//JButton
					JButton btnSet = new JButton("Set");
					btnSet.setBackground(styles.getButton_background());
					btnSet.setForeground(styles.getButton_text());
					btnSet.setBounds(10, 120, 330, 26);
					editFrame.getContentPane().add(btnSet);
					
					//Sets the new time for the timer
					btnSet.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							TimeClass timeEdit = new TimeClass();
							int[] startTime = {Integer.parseInt(textField.getText()), Integer.parseInt(MinutesTF.getText()), Integer.parseInt(SecondsTF.getText())};
							timeEdit.setStartTime(startTime);
							timeEdit.save();
							startTimer();
							btnStart.setText("Start");
							editOpen = false;
							editFrame.dispose();
						}
					});
					
					//If closed button is clicked starts the timer at current time
					editFrame.addWindowListener(new java.awt.event.WindowAdapter() {
					    @Override
					    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
					    	if(btnStart.getText().equals("Start"))
					    		time.setRun(false);
					    	else
					    		time.setRun(true);
					    	editOpen = false;
					    }
					});
					
					SecondsTF.addKeyListener(new KeyAdapter() {
						@Override
						public void keyReleased(KeyEvent arg0) {
							if(Integer.parseInt(SecondsTF.getText()) > 60) {
								SecondsTF.setText("60");
							}
						}
					});
	
					MinutesTF.addKeyListener(new KeyAdapter() {
						@Override
						public void keyReleased(KeyEvent arg0) {
							if(Integer.parseInt(MinutesTF.getText()) > 60) {
								MinutesTF.setText("60");
							}
						}
					});
					editFrame.setVisible(true);
				}
			}
		  });
		  
		  //Popus up twitch edit window allowing you to change current bot or current channel
		  btnTwitch.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(!editOpen && !settings.getOpen() && !twitch.getOpen())
						twitch.activate();
				}
			});
		  
		  //Starts the timer if it is paused/not started and pauses the timer if it is running and swaps text when needed
			btnStart.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(btnStart.getText().equals("Start")) {
						StreamTimer.setRun(true);
						btnStart.setText("Pause");
					} else if(btnStart.getText().equals("Pause")) {
						StreamTimer.setRun(false);
						btnStart.setText("Start");
					}
				}
			});
			
			//Popus up settings window to change settings
			Settings.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(!editOpen && !twitch.getOpen() && !settings.getOpen())
					settings.activate();
				}
			});
			
			//Clears the update table and updates txt file
			btnClear.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					File clear = new File("updates.txt");
					try {
						FileWriter clearWrite = new FileWriter(clear);
						clearWrite.close();
					} catch (IOException e1) {
						debug.debug("TimerGUIUpdateChecker:" + "There was an error clearing updates");
						debug.debug(e1.getStackTrace());
					}
				}
			});
			
			/**
			 * If twitch or settings window is open, set focus and play error sound
			 */
			frame.addWindowFocusListener(new WindowFocusListener() {
				public void windowGainedFocus(WindowEvent arg0) {
					if(twitch.getOpen()) {
						playSound();
						twitch.setFocus();
					}
					else if(settings.getOpen()) {
						playSound();
						settings.setFocus();
					} else if(editOpen) {
						playSound();
						editFrame.setVisible(true);
						editFrame.toFront();
						editFrame.requestFocus();
					}
				}
				public void windowLostFocus(WindowEvent arg0) {
				}
			});
	}
	
	//Private
		/**
		 * Restarts timer
		 */
		@SuppressWarnings("deprecation")
		private void startTimer(){
			if(time == null) {
				time = new StreamTimer();
				time.start();
			} else {
				time.stop();
				time = new StreamTimer();
				time.start();
			}
		}
		
		/**
		 * Restarts twich bot
		 */
		@SuppressWarnings("deprecation")
		private void startBot(){
			if(bot == null) {
				bot = new BotStarter();
				bot.start();
			} else {
				bot.stop();
				bot = new BotStarter();
				bot.start();
			}
		}
		
		@SuppressWarnings("static-access")
		private void updateStyles() {
			//Updates colors
			styles.updateStyles();
			
			//Updates frame
			frame.getContentPane().setBackground(styles.getWindow_background());
			
			//Updates panel
			panel.setBackground(styles.getTimer_background());
			
			//Updates labels
			for(int l = 0; l < labels.size(); l++)
				labels.get(l).setForeground(styles.getText());
			
			//Updates buttons
			for(int b = 0; b < buttons.size(); b++) {
				buttons.get(b).setBackground(styles.getButton_background());
				buttons.get(b).setForeground(styles.getButton_text());
			}
			
			//Updates table
			table.setForeground(styles.getTable_text());
			table.setBackground(styles.getTable_background());
		}
		
		/**
		 * Plays error sound
		 */
		private void playSound() {
			final Runnable runnable = (Runnable) Toolkit.getDefaultToolkit().getDesktopProperty("win.sound.exclamation");
				if (runnable != null) runnable.run();
		}

}
