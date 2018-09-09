package gui;
//Imports
//awt
import java.awt.EventQueue;
//swing
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;
//awt
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.TimerTask;

//classes
import classes.StreamTimer;
import classes.StyleClass;
import classes.BotStarter;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;

public class TimerGUI extends Thread{
	
	//Instance Variables
	//JFrame
	private JFrame frame;
	//Lables
	private JLabel lblTimerText;
	//Booleans
	private static boolean debug = true;
	private JPanel panel;
	private JTable table;
	private JScrollPane scrollPane;
	//Colors
	private Color window_background;
	private Color timer_background;
	private Color timer_text;
	private Color button_background;
	private Color button_text;
	private Color table_background;
	private Color table_text;
	
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
		
		//Initializes the frame
		frame = new JFrame();
		frame.getContentPane().setForeground(Color.BLUE);
		frame.setResizable(false);
		frame.getContentPane().setBackground(window_background);
		frame.setBounds(100, 100, 490, 600);
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
		//table.setPreferredSize(new Dimension(200,table.getRowHeight()*table.getRowCount()));
		//table.setPreferredScrollableViewportSize(new Dimension(200,frame.getHeight()-70));
		scrollPane.setViewportView(table);
		//frame.getContentPane().add(new JScrollPane(table));
		
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
		
		
		//Timer
		StreamTimer time = new StreamTimer();
		time.start();
		
		BotStarter bot = new BotStarter();
		bot.start();
		
		int delay = 1; //milliseconds
		  ActionListener taskPerformer = new ActionListener() {
		      public void actionPerformed(ActionEvent evt) {
		    	  lblTimerText.setText(StreamTimer.getTime());
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
					e.printStackTrace();
				}
		    	  if(scan.hasNextLine()) {
		    		  String hold = scan.nextLine();
		    		  String[] split = hold.split(":");
		    		  model.addRow(new Object[]{split[0], split[1], split[2]});
		    	  }
		    	  if(scan != null)
		    		  scan.close();
		      }
		  };
		  new Timer(tableDelay, tableTaskPerformer).start();
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
}
