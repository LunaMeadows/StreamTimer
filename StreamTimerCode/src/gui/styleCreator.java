package gui;

//Imports
//awt
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.Color;
//swing
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import classes.FileClass;
import classes.StyleClass;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JSlider;
import javax.swing.JRadioButton;
import java.awt.SystemColor;

public class styleCreator {
	//Instance Variables
	//JFrame
	private JFrame frame;
	//JTable
	private JTable TableBackground;
	//JTextField
	private JTextField TextboxBackground;
	private JTextField WindowBackgroundR;
	private JTextField WindowBackgroundG;
	private JTextField WindowBackgroundB;
	private JTextField TimerBackgroundR;
	private JTextField TimerBackgroundG;
	private JTextField TimerBackgroundB;
	private JTextField TimerTextR;
	private JTextField TimerTextG;
	private JTextField TimerTextB;
	private JTextField ButtonBackgroundR;
	private JTextField ButtonBackgroundG;
	private JTextField ButtonBackgroundB;
	private JTextField ButtonTextR;
	private JTextField ButtonTextG;
	private JTextField ButtonTextB;
	private JTextField TableBackgroundR;
	private JTextField TableBackgroundG;
	private JTextField TableBackgroundB;
	private JTextField TableTextR;
	private JTextField TableTextG;
	private JTextField TableTextB;
	private JTextField TextboxBackgroundR;
	private JTextField TextboxBackgroundG;
	private JTextField TextboxBackgroundB;
	private JTextField TextboxTextR;
	private JTextField TextboxTextG;
	private JTextField TextboxTextB;
	private JTextField TextR;
	private JTextField TextG;
	private JTextField TextB;
	private JTextField DropdownBackgroundR;
	private JTextField DropdownBackgroundG;
	private JTextField DropdownBackgroundB;
	private JTextField DropdownTextR;
	private JTextField DropdownTextG;
	private JTextField DropdownTextB;
	//JLabel
	private JLabel lblGSliderValue;
	private JLabel lblBSliderValue;
	//JButton
	private JButton btnLoad;
	private JPanel panel;
	private JPanel panel_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					styleCreator window = new styleCreator();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public styleCreator() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 616, 578);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel TimerBackground = new JPanel();
		TimerBackground.setBounds(330, 11, 266, 112);
		frame.getContentPane().add(TimerBackground);
		TimerBackground.setLayout(null);
		
		JLabel TimerText = new JLabel("New label");
		TimerText.setHorizontalAlignment(SwingConstants.CENTER);
		TimerText.setFont(new Font("SansSerif", Font.PLAIN, 40));
		TimerText.setBounds(0, 0, 266, 112);
		TimerBackground.add(TimerText);
		
		JButton Button = new JButton("New button");
		Button.setFont(new Font("SansSerif", Font.PLAIN, 20));
		Button.setBounds(446, 134, 150, 52);
		frame.getContentPane().add(Button);
		
		JScrollPane tablePanel = new JScrollPane();
		tablePanel.setBounds(330, 199, 266, 151);
		frame.getContentPane().add(tablePanel);
		
		TableBackground = new JTable();
		TableBackground.setForeground(SystemColor.infoText);
		TableBackground.setModel(new DefaultTableModel(
			new Object[][] {
				{"TEST", "TEST", "TEST"},
				{"TEST", "TEST", null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
			},
			new String[] {
				"New column", "New column", "New column"
			}
		));
		tablePanel.setViewportView(TableBackground);
		
		TextboxBackground = new JTextField();
		TextboxBackground.setBounds(330, 134, 109, 20);
		frame.getContentPane().add(TextboxBackground);
		TextboxBackground.setColumns(10);
		
		JLabel Text = new JLabel("TEST TEXT");
		Text.setBounds(330, 157, 106, 14);
		frame.getContentPane().add(Text);
		
		JComboBox DropdownBoxBackground = new JComboBox();
		DropdownBoxBackground.setModel(new DefaultComboBoxModel(new String[] {"THIS IS A TEST", "THIS IS A TEST", "THIS IS A TEST", "THIS IS A TEST"}));
		DropdownBoxBackground.setBounds(330, 360, 266, 20);
		frame.getContentPane().add(DropdownBoxBackground);
		
		JButton btnSave = new JButton("Save");
		btnSave.setBounds(527, 504, 73, 34);
		frame.getContentPane().add(btnSave);
		
		panel_1 = new JPanel();
		panel_1.setBounds(6, 32, 314, 362);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JRadioButton lblWindowBackground = new JRadioButton("Window Background:");
		lblWindowBackground.setBounds(0, 24, 146, 24);
		panel_1.add(lblWindowBackground);
		
		JRadioButton lblTimerBackground = new JRadioButton("Timer background:");
		lblTimerBackground.setBounds(0, 48, 132, 24);
		panel_1.add(lblTimerBackground);
		
		JRadioButton lblTimerText = new JRadioButton("Timer Text:");
		lblTimerText.setBounds(0, 73, 89, 24);
		panel_1.add(lblTimerText);
		
		JRadioButton lblButtonBackground = new JRadioButton("Button Background:");
		lblButtonBackground.setBounds(0, 98, 137, 24);
		panel_1.add(lblButtonBackground);
		
		JRadioButton lblButtonText = new JRadioButton("Button Text:");
		lblButtonText.setBounds(0, 124, 93, 24);
		panel_1.add(lblButtonText);
		
		JRadioButton lblTableBackground = new JRadioButton("Table Background:");
		lblTableBackground.setBounds(0, 150, 131, 24);
		panel_1.add(lblTableBackground);
		
		JRadioButton lblTableText = new JRadioButton("Table Text:");
		lblTableText.setBounds(0, 173, 87, 24);
		panel_1.add(lblTableText);
		
		JRadioButton lblTextboxBackground = new JRadioButton("Textbox Background:");
		lblTextboxBackground.setBounds(0, 197, 146, 24);
		panel_1.add(lblTextboxBackground);
		
		JRadioButton lblTextboxText = new JRadioButton("Textbox Text:");
		lblTextboxText.setBounds(0, 221, 102, 24);
		panel_1.add(lblTextboxText);
		
		JRadioButton lblText = new JRadioButton("Text:");
		lblText.setBounds(0, 246, 53, 24);
		panel_1.add(lblText);
		
		JRadioButton lblDropdownBackground = new JRadioButton("Dropdown Background:");
		lblDropdownBackground.setBounds(0, 271, 158, 24);
		panel_1.add(lblDropdownBackground);
		
		JRadioButton lblDropdownText = new JRadioButton("Dropdown Text:");
		lblDropdownText.setBounds(0, 297, 114, 24);
		panel_1.add(lblDropdownText);
		
		WindowBackgroundR = new JTextField();
		WindowBackgroundR.setBounds(164, 25, 42, 20);
		panel_1.add(WindowBackgroundR);
		WindowBackgroundR.setColumns(10);
		
		WindowBackgroundG = new JTextField();
		WindowBackgroundG.setBounds(216, 25, 42, 20);
		panel_1.add(WindowBackgroundG);
		WindowBackgroundG.setColumns(10);
		
		WindowBackgroundB = new JTextField();
		WindowBackgroundB.setBounds(268, 25, 42, 20);
		panel_1.add(WindowBackgroundB);
		WindowBackgroundB.setColumns(10);
		
		TimerBackgroundR = new JTextField();
		TimerBackgroundR.setBounds(164, 50, 42, 20);
		panel_1.add(TimerBackgroundR);
		TimerBackgroundR.setColumns(10);
		
		TimerBackgroundG = new JTextField();
		TimerBackgroundG.setBounds(216, 50, 42, 20);
		panel_1.add(TimerBackgroundG);
		TimerBackgroundG.setColumns(10);
		
		TimerBackgroundB = new JTextField();
		TimerBackgroundB.setBounds(268, 50, 42, 20);
		panel_1.add(TimerBackgroundB);
		TimerBackgroundB.setColumns(10);
		
		TimerTextR = new JTextField();
		TimerTextR.setBounds(164, 75, 42, 20);
		panel_1.add(TimerTextR);
		TimerTextR.setColumns(10);
		
		TimerTextG = new JTextField();
		TimerTextG.setBounds(216, 75, 42, 20);
		panel_1.add(TimerTextG);
		TimerTextG.setColumns(10);
		
		TimerTextB = new JTextField();
		TimerTextB.setBounds(268, 75, 42, 20);
		panel_1.add(TimerTextB);
		TimerTextB.setColumns(10);
		
		ButtonBackgroundR = new JTextField();
		ButtonBackgroundR.setBounds(164, 100, 42, 20);
		panel_1.add(ButtonBackgroundR);
		ButtonBackgroundR.setColumns(10);
		
		ButtonBackgroundG = new JTextField();
		ButtonBackgroundG.setBounds(216, 100, 42, 20);
		panel_1.add(ButtonBackgroundG);
		ButtonBackgroundG.setColumns(10);
		
		ButtonBackgroundB = new JTextField();
		ButtonBackgroundB.setBounds(268, 100, 42, 20);
		panel_1.add(ButtonBackgroundB);
		ButtonBackgroundB.setColumns(10);
		
		ButtonTextR = new JTextField();
		ButtonTextR.setBounds(164, 125, 42, 20);
		panel_1.add(ButtonTextR);
		ButtonTextR.setColumns(10);
		
		ButtonTextG = new JTextField();
		ButtonTextG.setBounds(216, 125, 42, 20);
		panel_1.add(ButtonTextG);
		ButtonTextG.setColumns(10);
		
		ButtonTextB = new JTextField();
		ButtonTextB.setBounds(268, 125, 42, 20);
		panel_1.add(ButtonTextB);
		ButtonTextB.setColumns(10);
		
		TableBackgroundR = new JTextField();
		TableBackgroundR.setBounds(164, 151, 42, 20);
		panel_1.add(TableBackgroundR);
		TableBackgroundR.setColumns(10);
		
		TableBackgroundG = new JTextField();
		TableBackgroundG.setBounds(216, 151, 42, 20);
		panel_1.add(TableBackgroundG);
		TableBackgroundG.setColumns(10);
		
		TableBackgroundB = new JTextField();
		TableBackgroundB.setBounds(268, 151, 42, 20);
		panel_1.add(TableBackgroundB);
		TableBackgroundB.setColumns(10);
		
		TableTextR = new JTextField();
		TableTextR.setBounds(164, 174, 42, 20);
		panel_1.add(TableTextR);
		TableTextR.setColumns(10);
		
		TableTextG = new JTextField();
		TableTextG.setBounds(216, 174, 42, 20);
		panel_1.add(TableTextG);
		TableTextG.setColumns(10);
		
		TableTextB = new JTextField();
		TableTextB.setBounds(268, 174, 42, 20);
		panel_1.add(TableTextB);
		TableTextB.setColumns(10);
		
		TextboxBackgroundR = new JTextField();
		TextboxBackgroundR.setBounds(164, 199, 42, 20);
		panel_1.add(TextboxBackgroundR);
		TextboxBackgroundR.setColumns(10);
		
		TextboxBackgroundG = new JTextField();
		TextboxBackgroundG.setBounds(216, 199, 42, 20);
		panel_1.add(TextboxBackgroundG);
		TextboxBackgroundG.setColumns(10);
		
		TextboxBackgroundB = new JTextField();
		TextboxBackgroundB.setBounds(268, 199, 42, 20);
		panel_1.add(TextboxBackgroundB);
		TextboxBackgroundB.setColumns(10);
		
		TextboxTextR = new JTextField();
		TextboxTextR.setBounds(164, 223, 42, 20);
		panel_1.add(TextboxTextR);
		TextboxTextR.setColumns(10);
		
		TextboxTextG = new JTextField();
		TextboxTextG.setBounds(216, 223, 42, 20);
		panel_1.add(TextboxTextG);
		TextboxTextG.setColumns(10);
		
		TextboxTextB = new JTextField();
		TextboxTextB.setBounds(268, 223, 42, 20);
		panel_1.add(TextboxTextB);
		TextboxTextB.setColumns(10);
		
		TextR = new JTextField();
		TextR.setBounds(164, 248, 42, 20);
		panel_1.add(TextR);
		TextR.setColumns(10);
		
		TextG = new JTextField();
		TextG.setBounds(216, 248, 42, 20);
		panel_1.add(TextG);
		TextG.setColumns(10);
		
		TextB = new JTextField();
		TextB.setBounds(268, 248, 42, 20);
		panel_1.add(TextB);
		TextB.setColumns(10);
		
		DropdownBackgroundR = new JTextField();
		DropdownBackgroundR.setBounds(164, 273, 42, 20);
		panel_1.add(DropdownBackgroundR);
		DropdownBackgroundR.setColumns(10);
		
		DropdownBackgroundG = new JTextField();
		DropdownBackgroundG.setBounds(216, 273, 42, 20);
		panel_1.add(DropdownBackgroundG);
		DropdownBackgroundG.setColumns(10);
		
		DropdownBackgroundB = new JTextField();
		DropdownBackgroundB.setBounds(268, 273, 42, 20);
		panel_1.add(DropdownBackgroundB);
		DropdownBackgroundB.setColumns(10);
		
		DropdownTextR = new JTextField();
		DropdownTextR.setBounds(164, 298, 42, 20);
		panel_1.add(DropdownTextR);
		DropdownTextR.setColumns(10);
		
		DropdownTextG = new JTextField();
		DropdownTextG.setBounds(216, 298, 42, 20);
		panel_1.add(DropdownTextG);
		DropdownTextG.setColumns(10);
		
		DropdownTextB = new JTextField();
		DropdownTextB.setBounds(268, 298, 42, 20);
		panel_1.add(DropdownTextB);
		DropdownTextB.setColumns(10);
		
		JLabel lblRValue = new JLabel("R value");
		lblRValue.setBounds(164, 0, 46, 14);
		panel_1.add(lblRValue);
		
		JLabel lblGValue = new JLabel("G value");
		lblGValue.setBounds(216, 0, 46, 14);
		panel_1.add(lblGValue);
		
		JLabel lblBValue = new JLabel("B value");
		lblBValue.setBounds(268, 0, 46, 14);
		panel_1.add(lblBValue);
		
		panel = new JPanel();
		panel.setBounds(6, 392, 427, 146);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JSlider RSlider = new JSlider();
		RSlider.setBounds(73, 12, 190, 26);
		panel.add(RSlider);
		RSlider.setValue(0);
		RSlider.setMaximum(255);
		
		JSlider GSlider = new JSlider();
		GSlider.setBounds(73, 46, 190, 26);
		panel.add(GSlider);
		GSlider.setValue(0);
		GSlider.setMaximum(255);
		
		JSlider BSlider = new JSlider();
		BSlider.setBounds(73, 84, 190, 26);
		panel.add(BSlider);
		BSlider.setValue(0);
		BSlider.setMaximum(255);
		
		JPanel RGBColorPlane = new JPanel();
		RGBColorPlane.setBounds(276, 12, 135, 98);
		panel.add(RGBColorPlane);
		
		JLabel lblRSliderValue = new JLabel("R: 0");
		lblRSliderValue.setBounds(0, 15, 60, 22);
		panel.add(lblRSliderValue);
		lblRSliderValue.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		lblGSliderValue = new JLabel("G: 0");
		lblGSliderValue.setBounds(0, 49, 61, 22);
		panel.add(lblGSliderValue);
		lblGSliderValue.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		lblBSliderValue = new JLabel("B: 0");
		lblBSliderValue.setBounds(1, 85, 60, 22);
		panel.add(lblBSliderValue);
		lblBSliderValue.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		JButton btnSet = new JButton("Set");
		btnSet.setBounds(191, 112, 73, 34);
		panel.add(btnSet);
		
		JButton btnGet = new JButton("Get");
		btnGet.setBounds(73, 112, 73, 34);
		panel.add(btnGet);
		
		//Gets the selected fields from the selected button
		btnGet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(lblWindowBackground.isSelected()) {
					RSlider.setValue(Integer.parseInt(WindowBackgroundR.getText()));
					GSlider.setValue(Integer.parseInt(WindowBackgroundG.getText()));
					BSlider.setValue(Integer.parseInt(WindowBackgroundB.getText()));
				} else if(lblTimerBackground.isSelected()) {
					RSlider.setValue(Integer.parseInt(TimerBackgroundR.getText()));
					GSlider.setValue(Integer.parseInt(TimerBackgroundG.getText()));
					BSlider.setValue(Integer.parseInt(TimerBackgroundB.getText()));
				} else if(lblTimerText.isSelected()) {
					RSlider.setValue(Integer.parseInt(TimerTextR.getText()));
					GSlider.setValue(Integer.parseInt(TimerTextG.getText()));
					BSlider.setValue(Integer.parseInt(TimerTextB.getText()));
				} else if(lblButtonBackground.isSelected()) {
					RSlider.setValue(Integer.parseInt(ButtonBackgroundR.getText()));
					GSlider.setValue(Integer.parseInt(ButtonBackgroundG.getText()));
					BSlider.setValue(Integer.parseInt(ButtonBackgroundB.getText()));
				} else if(lblButtonText.isSelected()) {
					RSlider.setValue(Integer.parseInt(ButtonTextR.getText()));
					GSlider.setValue(Integer.parseInt(ButtonTextG.getText()));
					BSlider.setValue(Integer.parseInt(ButtonTextB.getText()));
				} else if(lblTableBackground.isSelected()) {
					RSlider.setValue(Integer.parseInt(TableBackgroundR.getText()));
					GSlider.setValue(Integer.parseInt(TableBackgroundG.getText()));
					BSlider.setValue(Integer.parseInt(TableBackgroundB.getText()));
				} else if(lblTableText.isSelected()) {
					RSlider.setValue(Integer.parseInt(TableTextR.getText()));
					GSlider.setValue(Integer.parseInt(TableTextG.getText()));
					BSlider.setValue(Integer.parseInt(TableTextB.getText()));
				} else if(lblTextboxBackground.isSelected()) {
					RSlider.setValue(Integer.parseInt(TextboxBackgroundR.getText()));
					GSlider.setValue(Integer.parseInt(TextboxBackgroundG.getText()));
					BSlider.setValue(Integer.parseInt(TextboxBackgroundB.getText()));
				} else if(lblTextboxText.isSelected()) {
					RSlider.setValue(Integer.parseInt(TextboxTextR.getText()));
					GSlider.setValue(Integer.parseInt(TextboxTextG.getText()));
					BSlider.setValue(Integer.parseInt(TextboxTextB.getText()));
				} else if(lblText.isSelected()) {
					RSlider.setValue(Integer.parseInt(TextR.getText()));
					GSlider.setValue(Integer.parseInt(TextG.getText()));
					BSlider.setValue(Integer.parseInt(TextB.getText()));
				} else if(lblDropdownBackground.isSelected()) {
					RSlider.setValue(Integer.parseInt(DropdownBackgroundR.getText()));
					GSlider.setValue(Integer.parseInt(DropdownBackgroundG.getText()));
					BSlider.setValue(Integer.parseInt(DropdownBackgroundB.getText()));
				} else if(lblDropdownText.isSelected()) {
					RSlider.setValue(Integer.parseInt(DropdownTextR.getText()));
					GSlider.setValue(Integer.parseInt(DropdownTextG.getText()));
					BSlider.setValue(Integer.parseInt(DropdownTextB.getText()));
				}
			}
		});
		
		//Listeners
		//Sets the selected fields rgb values
		btnSet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//WindowBackground
				if(lblWindowBackground.isSelected()) {
					WindowBackgroundR.setText(String.valueOf(RSlider.getValue()));
					WindowBackgroundG.setText(String.valueOf(GSlider.getValue()));
					WindowBackgroundB.setText(String.valueOf(BSlider.getValue()));
					frame.getContentPane().setBackground(new Color(Integer.parseInt(WindowBackgroundR.getText()), Integer.parseInt(WindowBackgroundG.getText()), Integer.parseInt(WindowBackgroundB.getText())));
				}
				//TimerBackground
				if(lblTimerBackground.isSelected()) {
					TimerBackgroundR.setText(String.valueOf(RSlider.getValue()));
					TimerBackgroundG.setText(String.valueOf(GSlider.getValue()));
					TimerBackgroundB.setText(String.valueOf(BSlider.getValue()));
					TimerBackground.setBackground(new Color(Integer.parseInt(TimerBackgroundR.getText()), Integer.parseInt(TimerBackgroundG.getText()), Integer.parseInt(TimerBackgroundB.getText())));
				}
				//TimerText
				if(lblTimerText.isSelected()) {
					TimerTextR.setText(String.valueOf(RSlider.getValue()));
					TimerTextG.setText(String.valueOf(GSlider.getValue()));
					TimerTextB.setText(String.valueOf(BSlider.getValue()));
					TimerText.setForeground(new Color(Integer.parseInt(TimerTextR.getText()), Integer.parseInt(TimerTextG.getText()), Integer.parseInt(TimerTextB.getText())));
				}
				//ButtonBackground
				if(lblButtonBackground.isSelected()) {
					ButtonBackgroundR.setText(String.valueOf(RSlider.getValue()));
					ButtonBackgroundG.setText(String.valueOf(GSlider.getValue()));
					ButtonBackgroundB.setText(String.valueOf(BSlider.getValue()));
					Button.setBackground(new Color(Integer.parseInt(ButtonBackgroundR.getText()), Integer.parseInt(ButtonBackgroundG.getText()), Integer.parseInt(ButtonBackgroundB.getText())));
				}
				//ButtonText
				if(lblButtonText.isSelected()) {
					ButtonTextR.setText(String.valueOf(RSlider.getValue()));
					ButtonTextG.setText(String.valueOf(GSlider.getValue()));
					ButtonTextB.setText(String.valueOf(BSlider.getValue()));
					Button.setForeground(new Color(Integer.parseInt(ButtonTextR.getText()), Integer.parseInt(ButtonTextG.getText()), Integer.parseInt(ButtonTextB.getText())));
				}
				//TableBackground
				if(lblTableBackground.isSelected()) {
					TableBackgroundR.setText(String.valueOf(RSlider.getValue()));
					TableBackgroundG.setText(String.valueOf(GSlider.getValue()));
					TableBackgroundB.setText(String.valueOf(BSlider.getValue()));
					TableBackground.setBackground(new Color(Integer.parseInt(TableBackgroundR.getText()), Integer.parseInt(TableBackgroundG.getText()), Integer.parseInt(TableBackgroundB.getText())));
				}
				//TableText
				if(lblTableText.isSelected()) {
					TableTextR.setText(String.valueOf(RSlider.getValue()));
					TableTextG.setText(String.valueOf(GSlider.getValue()));
					TableTextB.setText(String.valueOf(BSlider.getValue()));
					TableBackground.setForeground(new Color(Integer.parseInt(TableTextR.getText()), Integer.parseInt(TableTextG.getText()), Integer.parseInt(TableTextB.getText())));
				}
				//TextboxBackground
				if(lblTextboxBackground.isSelected()) {
					TextboxBackgroundR.setText(String.valueOf(RSlider.getValue()));
					TextboxBackgroundG.setText(String.valueOf(GSlider.getValue()));
					TextboxBackgroundB.setText(String.valueOf(BSlider.getValue()));
					TextboxBackground.setBackground(new Color(Integer.parseInt(TextboxBackgroundR.getText()), Integer.parseInt(TextboxBackgroundG.getText()), Integer.parseInt(TextboxBackgroundB.getText())));
				}
				//TextboxText
				if(lblTextboxText.isSelected()) {
					TextboxTextR.setText(String.valueOf(RSlider.getValue()));
					TextboxTextG.setText(String.valueOf(GSlider.getValue()));
					TextboxTextB.setText(String.valueOf(BSlider.getValue()));
					TextboxBackground.setForeground(new Color(Integer.parseInt(TextboxTextR.getText()), Integer.parseInt(TextboxTextG.getText()), Integer.parseInt(TextboxTextB.getText())));
				}
				//Text
				if(lblText.isSelected()) {
					TextR.setText(String.valueOf(RSlider.getValue()));
					TextG.setText(String.valueOf(GSlider.getValue()));
					TextB.setText(String.valueOf(BSlider.getValue()));
					Text.setForeground(new Color(Integer.parseInt(TextR.getText()), Integer.parseInt(TextG.getText()), Integer.parseInt(TextB.getText())));
				}
				//DropdownBackground
				if(lblDropdownBackground.isSelected()) {
					DropdownBackgroundR.setText(String.valueOf(RSlider.getValue()));
					DropdownBackgroundG.setText(String.valueOf(GSlider.getValue()));
					DropdownBackgroundB.setText(String.valueOf(BSlider.getValue()));
					DropdownBoxBackground.setBackground(new Color(Integer.parseInt(DropdownBackgroundR.getText()), Integer.parseInt(DropdownBackgroundG.getText()), Integer.parseInt(DropdownBackgroundB.getText())));
				}
				//DropdownText
				if(lblDropdownText.isSelected()) {
					DropdownTextR.setText(String.valueOf(RSlider.getValue()));
					DropdownTextG.setText(String.valueOf(GSlider.getValue()));
					DropdownTextB.setText(String.valueOf(BSlider.getValue()));
					DropdownBoxBackground.setForeground(new Color(Integer.parseInt(DropdownTextR.getText()), Integer.parseInt(DropdownTextG.getText()), Integer.parseInt(DropdownTextB.getText())));
				}
			}
		});
		
		btnLoad = new JButton("Load");
		btnLoad.setBounds(527, 458, 73, 34);
		frame.getContentPane().add(btnLoad);
		
		//Loads a style
		btnLoad.addActionListener(new ActionListener() {
			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent e) {
				FileClass file = new FileClass();
				StyleClass style = new StyleClass(file.selectFile("Load Style").getSelectedFile());
				//WindowBackground
				WindowBackgroundR.setText(String.valueOf(style.getWindow_background().getRed()));
				WindowBackgroundG.setText(String.valueOf(style.getWindow_background().getGreen()));
				WindowBackgroundB.setText(String.valueOf(style.getWindow_background().getBlue()));
				frame.getContentPane().setBackground(new Color(Integer.parseInt(WindowBackgroundR.getText()), Integer.parseInt(WindowBackgroundG.getText()), Integer.parseInt(WindowBackgroundB.getText())));
				
				//TimerBackground
				TimerBackgroundR.setText(String.valueOf(style.getTimer_background().getRed()));
				TimerBackgroundG.setText(String.valueOf(style.getTimer_background().getGreen()));
				TimerBackgroundB.setText(String.valueOf(style.getTimer_background().getBlue()));
				TimerBackground.setBackground(new Color(Integer.parseInt(TimerBackgroundR.getText()), Integer.parseInt(TimerBackgroundG.getText()), Integer.parseInt(TimerBackgroundB.getText())));
				
				//TimerText
				TimerTextR.setText(String.valueOf(style.getTimer_text().getRed()));
				TimerTextG.setText(String.valueOf(style.getTimer_text().getGreen()));
				TimerTextB.setText(String.valueOf(style.getTimer_text().getBlue()));
				TimerText.setForeground(new Color(Integer.parseInt(TimerTextR.getText()), Integer.parseInt(TimerTextG.getText()), Integer.parseInt(TimerTextB.getText())));
				
				//ButtonBackground
				ButtonBackgroundR.setText(String.valueOf(style.getButton_background().getRed()));
				ButtonBackgroundG.setText(String.valueOf(style.getButton_background().getGreen()));
				ButtonBackgroundB.setText(String.valueOf(style.getButton_background().getBlue()));
				Button.setBackground(new Color(Integer.parseInt(ButtonBackgroundR.getText()), Integer.parseInt(ButtonBackgroundG.getText()), Integer.parseInt(ButtonBackgroundB.getText())));
				
				//ButtonText
				ButtonTextR.setText(String.valueOf(style.getButton_text().getRed()));
				ButtonTextG.setText(String.valueOf(style.getButton_text().getGreen()));
				ButtonTextB.setText(String.valueOf(style.getButton_text().getBlue()));
				Button.setForeground(new Color(Integer.parseInt(ButtonTextR.getText()), Integer.parseInt(ButtonTextG.getText()), Integer.parseInt(ButtonTextB.getText())));
				
				//TableBackground
				TableBackgroundR.setText(String.valueOf(style.getTable_background().getRed()));
				TableBackgroundG.setText(String.valueOf(style.getTable_background().getGreen()));
				TableBackgroundB.setText(String.valueOf(style.getTable_background().getBlue()));
				TableBackground.setBackground(new Color(Integer.parseInt(TableBackgroundR.getText()), Integer.parseInt(TableBackgroundG.getText()), Integer.parseInt(TableBackgroundB.getText())));
				
				//TableText
				TableTextR.setText(String.valueOf(style.getTable_text().getRed()));
				TableTextG.setText(String.valueOf(style.getTable_text().getGreen()));
				TableTextB.setText(String.valueOf(style.getTable_text().getBlue()));
				TableBackground.setForeground(new Color(Integer.parseInt(TableTextR.getText()), Integer.parseInt(TableTextG.getText()), Integer.parseInt(TableTextB.getText())));
				
				//TextboxBackground
				TextboxBackgroundR.setText(String.valueOf(style.getTextbox_background().getRed()));
				TextboxBackgroundG.setText(String.valueOf(style.getTextbox_background().getGreen()));
				TextboxBackgroundB.setText(String.valueOf(style.getTextbox_background().getBlue()));
				TextboxBackground.setBackground(new Color(Integer.parseInt(TextboxBackgroundR.getText()), Integer.parseInt(TextboxBackgroundG.getText()), Integer.parseInt(TextboxBackgroundB.getText())));
				
				//TextboxText
				TextboxTextR.setText(String.valueOf(style.getTextbox_text().getRed()));
				TextboxTextG.setText(String.valueOf(style.getTextbox_text().getGreen()));
				TextboxTextB.setText(String.valueOf(style.getTextbox_text().getBlue()));
				TextboxBackground.setForeground(new Color(Integer.parseInt(TextboxTextR.getText()), Integer.parseInt(TextboxTextG.getText()), Integer.parseInt(TextboxTextB.getText())));
				
				//Text
				TextR.setText(String.valueOf(style.getText().getRed()));
				TextG.setText(String.valueOf(style.getText().getGreen()));
				TextB.setText(String.valueOf(style.getText().getBlue()));
				Text.setForeground(new Color(Integer.parseInt(TextR.getText()), Integer.parseInt(TextG.getText()), Integer.parseInt(TextB.getText())));
				
				//DropdownBackground
				DropdownBackgroundR.setText(String.valueOf(style.getDropdown_background().getRed()));
				DropdownBackgroundG.setText(String.valueOf(style.getDropdown_background().getGreen()));
				DropdownBackgroundB.setText(String.valueOf(style.getDropdown_background().getBlue()));
				DropdownBoxBackground.setBackground(new Color(Integer.parseInt(DropdownBackgroundR.getText()), Integer.parseInt(DropdownBackgroundG.getText()), Integer.parseInt(DropdownBackgroundB.getText())));
				
				//DropdownText
				DropdownTextR.setText(String.valueOf(style.getDropdown_text().getRed()));
				DropdownTextG.setText(String.valueOf(style.getDropdown_text().getGreen()));
				DropdownTextB.setText(String.valueOf(style.getDropdown_text().getBlue()));
				DropdownBoxBackground.setForeground(new Color(Integer.parseInt(DropdownTextR.getText()), Integer.parseInt(DropdownTextG.getText()), Integer.parseInt(DropdownTextB.getText())));
				
			}
		});
		
		//Saves style
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FileClass save = new FileClass();
				File saveFile = save.saveFile("Save File").getSelectedFile();
				FileWriter write = null;
				try {
					write = new FileWriter(saveFile);
					if(write != null) {
						write.write("window_background=" + String.valueOf(frame.getContentPane().getBackground().getRGB()) + "\n");
						write.write("timer_background=" + String.valueOf(TimerBackground.getBackground().getRGB()) + "\n");
						write.write("timer_text=" + String.valueOf(TimerText.getForeground().getRGB()) + "\n");
						write.write("button_background=" + String.valueOf(Button.getBackground().getRGB()) + "\n");
						write.write("button_text=" + String.valueOf(Button.getForeground().getRGB()) + "\n");
						write.write("table_background=" + String.valueOf(TableBackground.getBackground().getRGB()) + "\n");
						write.write("table_text=" + String.valueOf(TableBackground.getForeground().getRGB()) + "\n");
						write.write("textbox_background=" + String.valueOf(TextboxBackground.getBackground().getRGB()) + "\n");
						write.write("textbox_text=" + String.valueOf(TextboxBackground.getForeground().getRGB()) + "\n");
						write.write("text=" + String.valueOf(Text.getForeground().getRGB()) + "\n");
						write.write("dropdown_background=" + String.valueOf(DropdownBoxBackground.getBackground().getRGB()) + "\n");
						write.write("dropdown_text=" + String.valueOf(DropdownBoxBackground.getForeground().getRGB()));
					}
					write.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		//Refreshes color plane
		int delay = 1; //milliseconds
		  ActionListener taskPerformer = new ActionListener() {
		      public void actionPerformed(ActionEvent evt) {
		    	  RGBColorPlane.setBackground(new Color(RSlider.getValue(), GSlider.getValue(), BSlider.getValue()));
		    	  lblRSliderValue.setText("R: " + RSlider.getValue());
		    	  lblGSliderValue.setText("G: " + GSlider.getValue());
		    	  lblBSliderValue.setText("B: " + BSlider.getValue());
		      }
		  };
		  new Timer(delay, taskPerformer).start();
	}
}
