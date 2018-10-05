package gui;
//Imports
//awt
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//swing
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JTextField;

import classes.StyleClass;

import javax.swing.JScrollPane;
//io
import java.io.File;
import java.io.IOException;
//util
import java.util.Scanner;

public class DebugConsole {
	//Instance variables
	//JFrame
	private JFrame frame;
	//JTextField
	private JTextField txtSearch;
	//JScrollPane
	private JScrollPane scrollPane;
	//JButton
	private JButton btnRefresh;
	//JTextArea
	private JTextArea txtConsole;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DebugConsole window = new DebugConsole();
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
	public DebugConsole() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings("static-access")
	private void initialize() {
		//Initializeation
		//Style Class
		StyleClass styles = new StyleClass();
		styles.updateStyles();
		
		//JFrame
		frame = new JFrame();
		frame.getContentPane().setBackground(styles.getWindow_background());
		frame.setBounds(100, 100, 650, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		//JScrollPane
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 55, 614, 395);
		frame.getContentPane().add(scrollPane);
		//JTextArea
		txtConsole = new JTextArea();
		txtConsole.setBackground(styles.getTextbox_background());
		txtConsole.setForeground(styles.getTextbox_text());
		scrollPane.setViewportView(txtConsole);
		//JTextField
		txtSearch = new JTextField();
		txtSearch.setBackground(styles.getTextbox_background());
		txtSearch.setForeground(styles.getTextbox_text());
		txtSearch.setFont(new Font("Tahoma", Font.PLAIN, 24));
		txtSearch.setText("Test");
		txtSearch.setBounds(10, 11, 236, 33);
		frame.getContentPane().add(txtSearch);
		txtSearch.setColumns(10);
		//JButton
		btnRefresh = new JButton("Refresh");
		btnRefresh.setBackground(styles.getButton_background());
		btnRefresh.setBackground(styles.getButton_text());
		btnRefresh.setBounds(535, 11, 89, 23);
		frame.getContentPane().add(btnRefresh);
		
		//Gets debug file and reads it in
		File file = new File("debug.txt");
		  try {
			Scanner read = new Scanner(file);
			while(read.hasNextLine())
				txtConsole.setText(txtConsole.getText() + "\n" + read.nextLine());
			read.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		  //Refreshes the console with new info and search
		  btnRefresh.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					txtConsole.setText("");
					File file = new File("debug.txt");
					  try {
						Scanner read = new Scanner(file);
						while(read.hasNextLine()) {
							String hold = read.nextLine();
							if(hold.startsWith(txtSearch.getText()))
								txtConsole.setText(txtConsole.getText() + "\n" + hold);
						}
						read.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
	}
}
