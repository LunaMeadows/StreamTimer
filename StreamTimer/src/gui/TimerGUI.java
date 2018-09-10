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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Color;
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

public class TimerGUI extends Thread{
	//Instance Variables
	//JFrame
	private JFrame frame;
	//Lables
	private JLabel lblTimerText;
	//JPanel
	private JPanel panel;
	//JTable
	private JTable table;
	//JScrollPane
	private JScrollPane scrollPane;
	//Booleans
	private boolean newTime = false;
	//String
	private String currentChannel;
	//Colors
	private Color window_background;
	private Color timer_background;
	private Color timer_text;
	private Color button_background;
	private Color button_text;
	private Color table_background;
	private Color table_text;
	//StreamTimer/BotStarter
	StreamTimer time = null;
	BotStarter bot = null;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					/*TimerGUI window = new TimerGUI();
					window.frame.setVisible(true);*/
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
		//initialize();
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
		StyleClass styles = new StyleClass();
		window_background = styles.getWindow_background();
		timer_background = styles.getTimer_background();
		timer_text = styles.getTimer_text();
		button_background = styles.getButton_background();
		button_text = styles.getButton_text();
		table_background = styles.getTable_background();
		table_text = styles.getTable_text();
		FileClass stream = new FileClass();
		stream.readInSettings();
		currentChannel = stream.getTwitchChannel();
		
		//Initializes the frame
		frame = new JFrame();
		frame.getContentPane().setForeground(Color.BLUE);
		frame.setResizable(false);
		frame.getContentPane().setBackground(window_background);
		frame.setBounds(100, 100, 490, 600);
		frame.setTitle("Stream Timer");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		//Initializes the panel
		panel = new JPanel();
		panel.setBackground(timer_background);
		panel.setBounds(10, 10, 464, 101);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		//Initializes componets
		
		//Labels
		//TimerText
		lblTimerText = new JLabel("00:00:00");
		lblTimerText.setBackground(Color.BLUE);
		lblTimerText.setForeground(timer_text);
		lblTimerText.setHorizontalAlignment(SwingConstants.CENTER);
		lblTimerText.setBounds(0, 0, 464, 101);
		panel.add(lblTimerText);
		lblTimerText.setFont(new Font("SansSerif", Font.PLAIN, 90));
		
		JButton Settings = new JButton("Settings");
		Settings.setFont(new Font("SansSerif", Font.PLAIN, 20));
		Settings.setForeground(button_text);
		Settings.setBackground(button_background);
		Settings.setBounds(10, 500, 150, 50);
		Settings.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		frame.getContentPane().add(Settings);
		
		JButton btnTwitch = new JButton("Twitch");
		btnTwitch.setFont(new Font("SansSerif", Font.PLAIN, 20));
		btnTwitch.setForeground(button_text);
		btnTwitch.setBackground(button_background);
		btnTwitch.setBounds(324, 500, 150, 50);
		btnTwitch.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		frame.getContentPane().add(btnTwitch);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 222, 464, 250);
		
		table = new JTable(new DefaultTableModel(new Object[] {"Type", "User", "Time"}, 0));
		table.setForeground(table_text);
		table.setBackground(table_background);
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setRowSelectionAllowed(false);
		table.setRowHeight(20);
		table.setAutoscrolls(true);
		scrollPane.setViewportView(table);
		
		frame.getContentPane().add(scrollPane);
		
		JButton btnStart = new JButton("Start");
		btnStart.setFont(new Font("SansSerif", Font.PLAIN, 20));
		btnStart.setForeground(button_text);
		btnStart.setBackground(button_background);
		btnStart.setBounds(10, 122, 130, 90);
		btnStart.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		frame.getContentPane().add(btnStart);
		
		JButton btnRestart = new JButton("Restart");
		btnRestart.setFont(new Font("SansSerif", Font.PLAIN, 20));
		btnRestart.setForeground(button_text);
		btnRestart.setBackground(button_background);
		btnRestart.setBounds(178, 122, 130, 90);
		btnRestart.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		frame.getContentPane().add(btnRestart);
		
		JButton btnEdit = new JButton("Edit");
		btnEdit.setFont(new Font("SansSerif", Font.PLAIN, 20));
		btnEdit.setForeground(button_text);
		btnEdit.setBackground(button_background);
		btnEdit.setBounds(344, 122, 130, 90);
		btnEdit.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		frame.getContentPane().add(btnEdit);
		
		JButton btnClear = new JButton("Clear");
		btnClear.setFont(new Font("SansSerif", Font.PLAIN, 20));
		btnClear.setForeground(button_text);
		btnClear.setBackground(button_background);
		btnClear.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnClear.setBounds(167, 500, 150, 50);
		frame.getContentPane().add(btnClear);
		
		startBot();
		startTimer();
		
		int delay = 1; //milliseconds
		  ActionListener taskPerformer = new ActionListener() {
		      public void actionPerformed(ActionEvent evt) {
		    	  lblTimerText.setText(StreamTimer.getTime());
		    	  if(newTime) {
		    		  System.out.println("help:");
		    	  }
		      }
		  };
		  new Timer(delay, taskPerformer).start();
		  
		  int tableDelay = 1000; //milliseconds
		  ActionListener tableTaskPerformer = new ActionListener() {
		      public void actionPerformed(ActionEvent evt) {
		    	  DefaultTableModel model = (DefaultTableModel) table.getModel();
		    	  model.setRowCount(0);
		    	  File update = new File("updates.txt");
		    	  Scanner scan = null;
		    	  try {
					scan = new Scanner(update);
				} catch (FileNotFoundException e) {
					debug.debug("TimerGUIUpdateChecker:" + "Could not find the file");
					debug.debug(e.getStackTrace().toString());
				}
		    	  while(scan.hasNextLine()) {
		    		  String hold = scan.nextLine();
		    		  String[] split = hold.split(":");
		    		  model.addRow(new Object[]{split[0], split[1], split[2]});
		    	  }
		    	  if(scan != null)
		    		  scan.close();
		    	  stream.readInSettings();
		    	  if(!currentChannel.equals(stream.getTwitchChannel())) {
		    		  currentChannel = stream.getTwitchChannel();
		    		  startBot();
		    	  }
		    	  
		      }
		  };
		  new Timer(tableDelay, tableTaskPerformer).start();
		  

			btnRestart.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					startTimer();
					btnStart.setText("Start");
				}
			});
		
		  btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFrame editFrame = new JFrame();
				editFrame.setResizable(false);
				editFrame.setBounds(100, 100, 356, 184);
				editFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				editFrame.setTitle("Edit Time");
				editFrame.getContentPane().setLayout(null);
				
				JTextField textField = new JTextField();
				textField.setHorizontalAlignment(SwingConstants.RIGHT);
				textField.setText("0");
				textField.setFont(new Font("SansSerif", Font.PLAIN, 70));
				textField.setBounds(10, 11, 84, 95);
				editFrame.getContentPane().add(textField);
				textField.setColumns(10);
				
				JTextField MinutesTF = new JTextField();
				MinutesTF.setHorizontalAlignment(SwingConstants.RIGHT);
				MinutesTF.setText("0");
				MinutesTF.setFont(new Font("SansSerif", Font.PLAIN, 70));
				MinutesTF.setColumns(10);
				MinutesTF.setBounds(133, 11, 84, 95);
				editFrame.getContentPane().add(MinutesTF);
				
				JTextField SecondsTF = new JTextField();
				SecondsTF.setHorizontalAlignment(SwingConstants.RIGHT);
				SecondsTF.setText("0");
				SecondsTF.setFont(new Font("SansSerif", Font.PLAIN, 70));
				SecondsTF.setColumns(10);
				SecondsTF.setBounds(256, 11, 84, 95);
				editFrame.getContentPane().add(SecondsTF);
				
				JLabel label = new JLabel(":");
				label.setFont(new Font("SansSerif", Font.PLAIN, 70));
				label.setBounds(104, 14, 19, 89);
				editFrame.getContentPane().add(label);
				
				JLabel label_1 = new JLabel(":");
				label_1.setFont(new Font("SansSerif", Font.PLAIN, 70));
				label_1.setBounds(227, 11, 19, 89);
				editFrame.getContentPane().add(label_1);
				
				JButton btnSet = new JButton("Set");
				btnSet.addActionListener(new ActionListener() {
					@SuppressWarnings("static-access")
					public void actionPerformed(ActionEvent e) {
						TimeClass timeEdit = new TimeClass();
						int[] startTime = {Integer.parseInt(textField.getText()), Integer.parseInt(MinutesTF.getText()), Integer.parseInt(SecondsTF.getText())};
						timeEdit.setStartTime(startTime);
						timeEdit.save();
						startTimer();
						editFrame.dispose();
					}
				});
				btnSet.setBounds(10, 120, 330, 26);
				editFrame.getContentPane().add(btnSet);
				
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
		  });
		  
		  btnTwitch.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					twitchPopup twitch = new twitchPopup();
					twitch.activate();
					
				}
			});

		  
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
			

			Settings.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					settingsPopup settings = new settingsPopup();
					settings.activate();
				}
			});
			

			btnClear.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					File clear = new File("updates.txt");
					try {
						FileWriter clearWrite = new FileWriter(clear);
						clearWrite.close();
					} catch (IOException e1) {
						debug.debug("TimerGUIUpdateChecker:" + "There was an error clearing updates");
						debug.debug(e1.getStackTrace().toString());
					}
				}
			});
	}
	
	//Private
		
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
}
