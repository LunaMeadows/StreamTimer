package gui;

//Imports
//Awt
import java.awt.EventQueue;
import java.awt.Color;
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

public class settingsPopup {
	//Instance Variables
	//JFrame
	private JFrame frame;
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
	//String
	private String currentSelection;
	//Color
	private Color WindowBackground;
	private Color ButtonBackground;
	private Color ButtonText;
	private Color TextboxBackground;
	private Color TextboxText;
	private Color Text;
	private Color DropdownBackground;
	private Color DropdownText;

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
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings({ "static-access", "unchecked", "rawtypes" })
	private void initialize() {
		//Initializes styles
		StyleClass styles = new StyleClass();
		currentSelection = styles.getStyle();
		WindowBackground = styles.getWindow_background();
		ButtonBackground = styles.getButton_background();
		ButtonText = styles.getButton_text();
		TextboxBackground = styles.getTextbox_background();
		TextboxText = styles.getTextbox_text();
		Text = styles.getText();
		DropdownBackground = styles.getDropdown_background();
		DropdownText = styles.getDropdown_text();
		//Initialize frame
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 396, 404);
		frame.getContentPane().setBackground(WindowBackground);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setTitle("Settings");
		frame.getContentPane().setLayout(null);
		
		//Buttons
		//Initialize Reset Button
		JButton ResetButton = new JButton("Reset Default");
		ResetButton.setForeground(ButtonText);
		ResetButton.setBackground(ButtonBackground);
		ResetButton.setBounds(199, 346, 110, 26);
		frame.getContentPane().add(ResetButton);
		//Initialize Done Button
		JButton DoneButton = new JButton("Done");
		DoneButton.setForeground(ButtonText);
		DoneButton.setBackground(ButtonBackground);
		DoneButton.setBounds(321, 346, 63, 26);
		frame.getContentPane().add(DoneButton);
		
		//Labels
		//Initialize SetText Label
		JLabel label = new JLabel();
		label.setText("Restart to take effect on this screen");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(Text);
		label.setFont(UIManager.getFont("Label.font"));
		label.setBounds(20, 321, 359, 16);
		frame.getContentPane().add(label);
		
		JLabel label_1 = new JLabel("---------------------------------------UI SETTINGS---------------------------------------");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setForeground(Text);
		label_1.setBounds(0, 271, 397, 16);
		frame.getContentPane().add(label_1);
		
		JLabel label_2 = new JLabel();
		label_2.setText("Put ammount of time to add in minutes");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setForeground(Text);
		label_2.setFont(UIManager.getFont("Label.font"));
		label_2.setBounds(20, 253, 359, 16);
		frame.getContentPane().add(label_2);
		
		JLabel label_3 = new JLabel("-------------------------------------TIME SETTINGS-------------------------------------");
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setForeground(Text);
		label_3.setBounds(0, 136, 397, 16);
		frame.getContentPane().add(label_3);
		
		JLabel label_4 = new JLabel();
		label_4.setText("$TT%-Time Type, \r\n$TA%-Time Ammount");
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		label_4.setForeground(Text);
		label_4.setFont(UIManager.getFont("Label.font"));
		label_4.setBounds(20, 118, 359, 16);
		frame.getContentPane().add(label_4);
		
		JLabel label_5 = new JLabel("Subtract Time Format:");
		label_5.setHorizontalAlignment(SwingConstants.LEFT);
		label_5.setForeground(Text);
		label_5.setBounds(3, 101, 126, 16);
		frame.getContentPane().add(label_5);
		
		JLabel label_6 = new JLabel("Add Time Format:");
		label_6.setHorizontalAlignment(SwingConstants.LEFT);
		label_6.setForeground(Text);
		label_6.setBounds(19, 75, 99, 16);
		frame.getContentPane().add(label_6);
		
		JLabel label_7 = new JLabel("Subtract Time Command:");
		label_7.setForeground(Text);
		label_7.setBounds(0, 51, 151, 16);
		frame.getContentPane().add(label_7);
		
		JLabel label_8 = new JLabel("Add Time Command:");
		label_8.setForeground(Text);
		label_8.setBounds(2, 25, 117, 16);
		frame.getContentPane().add(label_8);
		
		JLabel label_9 = new JLabel("-------------------------------------COMMAND INFO-------------------------------------");
		label_9.setHorizontalAlignment(SwingConstants.CENTER);
		label_9.setForeground(Text);
		label_9.setBounds(0, 0, 397, 16);
		frame.getContentPane().add(label_9);
		
		//ComboBox
		//Initialize Styles ComboBox
		JComboBox StylesCB = new JComboBox();
		StylesCB.setModel(new DefaultComboBoxModel(getStyles()));
		StylesCB.setForeground(DropdownText);
		StylesCB.setBackground(DropdownBackground);
		StylesCB.setBounds(48, 294, 337, 25);
		frame.getContentPane().add(StylesCB);
		
		//TextField
		HostTimeTF = new JTextField();
		HostTimeTF.setText("0");
		HostTimeTF.setForeground(TextboxText);
		HostTimeTF.setColumns(10);
		HostTimeTF.setBackground(TextboxBackground);
		HostTimeTF.setBounds(206, 232, 76, 20);
		frame.getContentPane().add(HostTimeTF);
		
		BitsTimeTF = new JTextField();
		BitsTimeTF.setText("0");
		BitsTimeTF.setForeground(TextboxText);
		BitsTimeTF.setColumns(10);
		BitsTimeTF.setBackground(TextboxBackground);
		BitsTimeTF.setBounds(87, 232, 76, 20);
		frame.getContentPane().add(BitsTimeTF);
		
		GiftedSubTimeTF = new JTextField();
		GiftedSubTimeTF.setText("0");
		GiftedSubTimeTF.setForeground(TextboxText);
		GiftedSubTimeTF.setColumns(10);
		GiftedSubTimeTF.setBackground(TextboxBackground);
		GiftedSubTimeTF.setBounds(272, 184, 76, 20);
		frame.getContentPane().add(GiftedSubTimeTF);
		
		ResubTimeTF = new JTextField();
		ResubTimeTF.setText("0");
		ResubTimeTF.setForeground(TextboxText);
		ResubTimeTF.setColumns(10);
		ResubTimeTF.setBackground(TextboxBackground);
		ResubTimeTF.setBounds(155, 184, 76, 20);
		frame.getContentPane().add(ResubTimeTF);
		
		SubTimeTF = new JTextField();
		SubTimeTF.setText("0");
		SubTimeTF.setForeground(TextboxText);
		SubTimeTF.setColumns(10);
		SubTimeTF.setBackground(TextboxBackground);
		SubTimeTF.setBounds(33, 184, 76, 20);
		frame.getContentPane().add(SubTimeTF);
		
		SubtractTimeFormatTF = new JTextField();
		SubtractTimeFormatTF.setToolTipText("Default: $DF%-$CN%-$TN%");
		SubtractTimeFormatTF.setText("$TA% $TT%");
		SubtractTimeFormatTF.setForeground(TextboxText);
		SubtractTimeFormatTF.setColumns(25);
		SubtractTimeFormatTF.setBackground(TextboxBackground);
		SubtractTimeFormatTF.setBounds(134, 98, 251, 20);
		frame.getContentPane().add(SubtractTimeFormatTF);
		
		AddTimeFormatTF = new JTextField();
		AddTimeFormatTF.setToolTipText("Default: $DF%-$CN%-$TN%");
		AddTimeFormatTF.setText("$TA% $TT%");
		AddTimeFormatTF.setForeground(TextboxText);
		AddTimeFormatTF.setColumns(25);
		AddTimeFormatTF.setBackground(TextboxBackground);
		AddTimeFormatTF.setBounds(120, 74, 265, 20);
		frame.getContentPane().add(AddTimeFormatTF);
		
		SubtractTimeCommandTF = new JTextField();
		SubtractTimeCommandTF.setText("subtracttime");
		SubtractTimeCommandTF.setForeground(TextboxText);
		SubtractTimeCommandTF.setColumns(10);
		SubtractTimeCommandTF.setBackground(TextboxBackground);
		SubtractTimeCommandTF.setBounds(148, 49, 237, 20);
		frame.getContentPane().add(SubtractTimeCommandTF);
		
		AddTimeCommandTF = new JTextField();
		AddTimeCommandTF.setText("addtime");
		AddTimeCommandTF.setForeground(TextboxText);
		AddTimeCommandTF.setColumns(10);
		AddTimeCommandTF.setBackground(TextboxBackground);
		AddTimeCommandTF.setBounds(120, 23, 265, 20);
		frame.getContentPane().add(AddTimeCommandTF);
		
		//RadioButton
		JRadioButton rdbtnBitsTime = new JRadioButton("Bits time");
		rdbtnBitsTime.setForeground(Text);
		rdbtnBitsTime.setBackground(WindowBackground);
		rdbtnBitsTime.setBounds(87, 208, 75, 24);
		frame.getContentPane().add(rdbtnBitsTime);
		
		JRadioButton rdbtnHostraidTime = new JRadioButton("Host/Raid time");
		rdbtnHostraidTime.setForeground(Text);
		rdbtnHostraidTime.setBackground(WindowBackground);
		rdbtnHostraidTime.setBounds(191, 208, 107, 24);
		frame.getContentPane().add(rdbtnHostraidTime);
		
		JRadioButton rdbtnGiftedSubTime = new JRadioButton("Gifted Sub time");
		rdbtnGiftedSubTime.setForeground(Text);
		rdbtnGiftedSubTime.setBackground(WindowBackground);
		rdbtnGiftedSubTime.setBounds(257, 160, 114, 24);
		frame.getContentPane().add(rdbtnGiftedSubTime);
		
		JRadioButton rdbtnResubTime = new JRadioButton("Resub time");
		rdbtnResubTime.setForeground(Text);
		rdbtnResubTime.setBackground(WindowBackground);
		rdbtnResubTime.setBounds(145, 160, 89, 24);
		frame.getContentPane().add(rdbtnResubTime);
		
		JRadioButton rdbtnSubTime = new JRadioButton("Sub time");
		rdbtnSubTime.setForeground(Text);
		rdbtnSubTime.setBackground(WindowBackground);
		rdbtnSubTime.setBounds(33, 160, 76, 24);
		frame.getContentPane().add(rdbtnSubTime);
		
		//Listeners
		//Checks if user clicks Done button and checks if everything is correct and if so saves and closes the window
		DoneButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FileClass save = new FileClass();
				save.readInSettings();
				if(save.checkTimeFormat(SubtractTimeFormatTF.getText()).equals("clear") && save.checkTimeFormat(AddTimeFormatTF.getText()).equals("clear")) {
					save.saveSettings(save.getBotName(), save.getBotOAuth(), save.getTwitchChannel(), AddTimeCommandTF.getText(), SubtractTimeCommandTF.getText(), AddTimeFormatTF.getText(), SubtractTimeFormatTF.getText());
				}
				TimeClass timeSave = new TimeClass("saveSettings");
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
						styles.changeStyle("style_" + String.valueOf(StylesCB.getSelectedItem()));
					} catch (IOException e1) {
						debug.debug("SettignsPopupStylesAcctionListener:" + "There was an error getting the styles");
						debug.debug(e1.getStackTrace());
					}
					WindowBackground = styles.getWindow_background();
					ButtonBackground = styles.getButton_background();
					ButtonText = styles.getButton_text();
					TextboxBackground = styles.getTextbox_background();
					TextboxText = styles.getTextbox_text();
					Text = styles.getText();
					DropdownBackground = styles.getDropdown_background();
					DropdownText = styles.getDropdown_text();
				}
			}
		});
	}
	
	@SuppressWarnings("static-access")
	private String[] getStyles() {
		StyleClass styles = new StyleClass();
		return styles.getStyles();
	}
}
