package gui;

//Imports
//Awt
import java.awt.EventQueue;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
//Swing
import javax.swing.JFrame;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.UIManager;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
//Classes
import classes.FileClass;
import classes.StyleClass;
import classes.TimeClass;
import classes.debug;
//Io
import java.io.IOException;
import java.util.ArrayList;

public class settingsPopup {
	//Instance Variables
	//JFrame
	private JFrame frame;
	//Buttons
	private JButton ResetButton;
	private JButton DoneButton;
	//JLabel
	private JLabel label;
	private JLabel label_1;
	private JLabel label_2;
	private JLabel label_3;
	private JLabel label_4;
	private JLabel label_5;
	private JLabel label_6;
	private JLabel label_7;
	private JLabel label_8;
	private JLabel label_9;
	//JComboBox
	@SuppressWarnings("rawtypes")
	private JComboBox StylesCB;
	//JTextField
	private JTextField HostTimeTF;
	private JTextField BitsTimeTF;
	private JTextField GiftedSubTimeTF;
	private JTextField ResubTimeTF;
	private JTextField SubTimeTF;
	private JTextField SubtractTimeFormatTF;
	private JTextField AddTimeFormatTF;
	private JTextField SubtractTimeCommandTF;
	private JTextField AddTimeCommandTF;
	//JRadioButton
	private JRadioButton rdbtnBitsTime;
	private JRadioButton rdbtnHostraidTime;
	private JRadioButton rdbtnGiftedSubTime;
	private JRadioButton rdbtnResubTime;
	private JRadioButton rdbtnSubTime;
	//String
	private String currentSelection;
	//Boolean
	private boolean open = false;
	//StyleClass
	private StyleClass styles;
	//ArrayList
	private ArrayList<JButton> buttons = new ArrayList<JButton>();
	private ArrayList<JLabel> labels = new ArrayList<JLabel>();
	private ArrayList<JTextField> textFields = new ArrayList<JTextField>();
	private ArrayList<JRadioButton> radioButtons = new ArrayList<JRadioButton>();
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					/*settingsPopup window = new settingsPopup();
					window.frame.setVisible(true);*/
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public settingsPopup() {
		//initialize();
	}
	
	/**
	 * Activates the window.
	 */
	public void activate() {
		open = true;
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings({ "static-access", "unchecked", "rawtypes" })
	private void initialize() {
		//Initializes styles
		styles = new StyleClass();
		currentSelection = styles.getStyle();
		
		//Initialize frame
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 396, 404);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setTitle("Settings");
		frame.getContentPane().setLayout(null);
		
		//Buttons
		//Initialize Reset Button
		ResetButton = new JButton("Reset Default");
		buttons.add(ResetButton);
		ResetButton.setBounds(199, 346, 110, 26);
		frame.getContentPane().add(ResetButton);
		//Initialize Done Button
		DoneButton = new JButton("Done");
		buttons.add(DoneButton);
		DoneButton.setBounds(321, 346, 63, 26);
		frame.getContentPane().add(DoneButton);
		
		//Labels
		//Initialize SetText Label
		label = new JLabel();
		labels.add(label);
		label.setText("Restart to take effect on this screen");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(UIManager.getFont("Label.font"));
		label.setBounds(20, 321, 359, 16);
		frame.getContentPane().add(label);
		
		label_1 = new JLabel("---------------------------------------UI SETTINGS---------------------------------------");
		labels.add(label_1);
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setBounds(0, 271, 397, 16);
		frame.getContentPane().add(label_1);
		
		label_2 = new JLabel();
		labels.add(label_2);
		label_2.setText("Put ammount of time to add in minutes");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setFont(UIManager.getFont("Label.font"));
		label_2.setBounds(20, 253, 359, 16);
		frame.getContentPane().add(label_2);
		
		label_3 = new JLabel("-------------------------------------TIME SETTINGS-------------------------------------");
		labels.add(label_3);
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setBounds(0, 136, 397, 16);
		frame.getContentPane().add(label_3);
		
		label_4 = new JLabel();
		labels.add(label_4);
		label_4.setText("$TT%-Time Type, \r\n$TA%-Time Ammount");
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		label_4.setFont(UIManager.getFont("Label.font"));
		label_4.setBounds(20, 118, 359, 16);
		frame.getContentPane().add(label_4);
		
		label_5 = new JLabel("Subtract Time Format:");
		labels.add(label_5);
		label_5.setHorizontalAlignment(SwingConstants.LEFT);
		label_5.setBounds(3, 101, 126, 16);
		frame.getContentPane().add(label_5);
		
		label_6 = new JLabel("Add Time Format:");
		labels.add(label_6);
		label_6.setHorizontalAlignment(SwingConstants.LEFT);
		label_6.setBounds(19, 75, 99, 16);
		frame.getContentPane().add(label_6);
		
		label_7 = new JLabel("Subtract Time Command:");
		labels.add(label_7);
		label_7.setBounds(0, 51, 151, 16);
		frame.getContentPane().add(label_7);
		
		label_8 = new JLabel("Add Time Command:");
		labels.add(label_8);
		label_8.setBounds(2, 25, 117, 16);
		frame.getContentPane().add(label_8);
		
		label_9 = new JLabel("-------------------------------------COMMAND INFO-------------------------------------");
		labels.add(label_9);
		label_9.setHorizontalAlignment(SwingConstants.CENTER);
		label_9.setBounds(0, 0, 397, 16);
		frame.getContentPane().add(label_9);
		
		//ComboBox
		//Initialize Styles ComboBox
		StylesCB = new JComboBox();
		StylesCB.setModel(new DefaultComboBoxModel(styles.getStyles()));
		StylesCB.setSelectedItem(styles.getStyle().split("=")[1].substring(0, styles.getStyle().split("=")[1].indexOf(".")));
		StylesCB.setBounds(48, 294, 337, 25);
		frame.getContentPane().add(StylesCB);
		
		//TextField
		HostTimeTF = new JTextField();
		textFields.add(HostTimeTF);
		HostTimeTF.setText("0");
		HostTimeTF.setColumns(10);
		HostTimeTF.setBounds(206, 232, 76, 20);
		frame.getContentPane().add(HostTimeTF);
		
		BitsTimeTF = new JTextField();
		textFields.add(BitsTimeTF);
		BitsTimeTF.setText("0");
		BitsTimeTF.setColumns(10);
		BitsTimeTF.setBounds(87, 232, 76, 20);
		frame.getContentPane().add(BitsTimeTF);
		
		GiftedSubTimeTF = new JTextField();
		textFields.add(GiftedSubTimeTF);
		GiftedSubTimeTF.setText("0");
		GiftedSubTimeTF.setColumns(10);
		GiftedSubTimeTF.setBounds(272, 184, 76, 20);
		frame.getContentPane().add(GiftedSubTimeTF);
		
		ResubTimeTF = new JTextField();
		textFields.add(ResubTimeTF);
		ResubTimeTF.setText("0");
		ResubTimeTF.setColumns(10);
		ResubTimeTF.setBounds(155, 184, 76, 20);
		frame.getContentPane().add(ResubTimeTF);
		
		SubTimeTF = new JTextField();
		textFields.add(SubTimeTF);
		SubTimeTF.setText("0");
		SubTimeTF.setColumns(10);
		SubTimeTF.setBounds(33, 184, 76, 20);
		frame.getContentPane().add(SubTimeTF);
		
		SubtractTimeFormatTF = new JTextField();
		textFields.add(SubtractTimeFormatTF);
		SubtractTimeFormatTF.setToolTipText("Default: $DF%-$CN%-$TN%");
		SubtractTimeFormatTF.setText("$TA% $TT%");
		SubtractTimeFormatTF.setColumns(25);
		SubtractTimeFormatTF.setBounds(134, 98, 251, 20);
		frame.getContentPane().add(SubtractTimeFormatTF);
		
		AddTimeFormatTF = new JTextField();
		textFields.add(AddTimeFormatTF);
		AddTimeFormatTF.setToolTipText("Default: $DF%-$CN%-$TN%");
		AddTimeFormatTF.setText("$TA% $TT%");
		AddTimeFormatTF.setColumns(25);
		AddTimeFormatTF.setBounds(120, 74, 265, 20);
		frame.getContentPane().add(AddTimeFormatTF);
		
		SubtractTimeCommandTF = new JTextField();
		textFields.add(SubtractTimeCommandTF);
		SubtractTimeCommandTF.setText("subtracttime");
		SubtractTimeCommandTF.setColumns(10);
		SubtractTimeCommandTF.setBounds(148, 49, 237, 20);
		frame.getContentPane().add(SubtractTimeCommandTF);
		
		AddTimeCommandTF = new JTextField();
		textFields.add(AddTimeCommandTF);
		AddTimeCommandTF.setText("addtime");
		AddTimeCommandTF.setColumns(10);
		AddTimeCommandTF.setBounds(120, 23, 265, 20);
		frame.getContentPane().add(AddTimeCommandTF);
		
		//RadioButton
		rdbtnBitsTime = new JRadioButton("Bits time");
		radioButtons.add(rdbtnBitsTime);
		rdbtnBitsTime.setBounds(87, 208, 75, 24);
		frame.getContentPane().add(rdbtnBitsTime);
		
		rdbtnHostraidTime = new JRadioButton("Host/Raid time");
		radioButtons.add(rdbtnHostraidTime);
		rdbtnHostraidTime.setBounds(191, 208, 107, 24);
		frame.getContentPane().add(rdbtnHostraidTime);
		
		rdbtnGiftedSubTime = new JRadioButton("Gifted Sub time");
		radioButtons.add(rdbtnGiftedSubTime);
		rdbtnGiftedSubTime.setBounds(257, 160, 114, 24);
		frame.getContentPane().add(rdbtnGiftedSubTime);
		
		rdbtnResubTime = new JRadioButton("Resub time");
		radioButtons.add(rdbtnResubTime);
		rdbtnResubTime.setBounds(145, 160, 89, 24);
		frame.getContentPane().add(rdbtnResubTime);
		
		rdbtnSubTime = new JRadioButton("Sub time");
		radioButtons.add(rdbtnSubTime);
		rdbtnSubTime.setBounds(33, 160, 76, 24);
		frame.getContentPane().add(rdbtnSubTime);
		
		updateStyle();
		//Listeners
		//Checks if user clicks Done button and checks if everything is correct and if so saves and closes the window
		DoneButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FileClass save = new FileClass();
				save.readInSettings();
				if(save.checkTimeFormat(SubtractTimeFormatTF.getText()).equals("clear") && save.checkTimeFormat(AddTimeFormatTF.getText()).equals("clear")) {
					save.saveSettings(save.getBotName(), save.getBotOAuth(), save.getTwitchChannel(), AddTimeCommandTF.getText(), SubtractTimeCommandTF.getText(), AddTimeFormatTF.getText(), SubtractTimeFormatTF.getText());
				}
				TimeClass timeSave = new TimeClass();
				if(rdbtnSubTime.isSelected())
					timeSave.setSubTime(Integer.parseInt(SubTimeTF.getText()));
				else
					timeSave.setSubTime(0);
				
				if(rdbtnResubTime.isSelected())
					timeSave.setResubTime(Integer.parseInt(ResubTimeTF.getText()));
				else
					timeSave.setResubTime(0);
				
				if(rdbtnGiftedSubTime.isSelected())
					timeSave.setGiftedsubTime(Integer.parseInt(GiftedSubTimeTF.getText()));
				else
					timeSave.setGiftedsubTime(0);
				
				if(rdbtnBitsTime.isSelected())
					timeSave.setBitsTime(Integer.parseInt(BitsTimeTF.getText()));
				else
					timeSave.setBitsTime(0);
				
				if(rdbtnHostraidTime.isSelected())
					timeSave.setRaidTime(Integer.parseInt(HostTimeTF.getText()));
				else
					timeSave.setRaidTime(0);
				timeSave.save();
				open = false;
				frame.dispose();
			}
		});
		
		//Checks if the user changes the style
		StylesCB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String hold = String.valueOf(StylesCB.getSelectedItem());
				if(!hold.equals(currentSelection)) {
					currentSelection = hold;
					try {
						styles.changeStyle(String.valueOf(StylesCB.getSelectedItem()) + ".style");
					} catch (IOException e1) {
						debug.debug("SettingsPopupStylesActionListener:" + "There was an error getting styles");
						debug.debug(e1.getStackTrace());
					}
					updateStyle();
				}
			}
		});
		
		//If exit button is pressed set open to false
		frame.addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		    	open = false;
		    	frame.dispose();
		    }
		});
	}
	
	/**
	 * Updates style
	 */
	@SuppressWarnings("static-access")
	private void updateStyle() {
		//Gets new style info
		styles.updateStyles();
		
		//Updates frame
		frame.getContentPane().setBackground(styles.getWindow_background());
		
		//Upates Styles comboBox
		StylesCB.setForeground(styles.getDropdown_text());
		StylesCB.setBackground(styles.getDropdown_background());
		
		//Updates buttons
		for(int i = 0; i < buttons.size(); i++) {
			buttons.get(i).setForeground(styles.getButton_text());
			buttons.get(i).setBackground(styles.getButton_background());
		}
		
		//Updates labels
		for(int i = 0; i < labels.size(); i++) {
			labels.get(i).setForeground(styles.getText());
		
		}
		
		//Updates text fields
		for(int i = 0; i < textFields.size(); i++) {
			textFields.get(i).setForeground(styles.getTextbox_text());
			textFields.get(i).setBackground(styles.getTextbox_background());
		}
		
		//Updates radio buttons
		for(int i = 0; i < radioButtons.size(); i++) {
			radioButtons.get(i).setForeground(styles.getText());
			radioButtons.get(i).setBackground(styles.getWindow_background());
		}
	}
	/**
	 * returns Open boolean
	 * @return
	 */
	public boolean getOpen() {
		return open;
	}
	
	/**
	 * Sets focus to this window
	 */
	public void setFocus() {
		frame.setVisible(true);
		frame.toFront();
		frame.requestFocus();
	}
}
