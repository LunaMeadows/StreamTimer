package gui;

import java.awt.Desktop;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;

import classes.FileClass;
import classes.StyleClass;
import classes.debug;

import java.awt.event.ActionListener;
import java.net.URI;
import java.awt.event.ActionEvent;

public class twitchPopup {

	private JFrame frame;
	private JTextField botNameTF;
	private JTextField OAuthKeyTF;
	private JTextField TwichChannelTF;
	private boolean open = false;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					/*twitchPopup window = new twitchPopup();
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
	public twitchPopup() {
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
	@SuppressWarnings("static-access")
	private void initialize() {
		StyleClass style = new StyleClass();
		FileClass info = new FileClass();
		info.readInSettings();
		
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 281, 162);
		frame.getContentPane().setBackground(style.getWindow_background());
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setTitle("Twitch Settings");
		frame.getContentPane().setLayout(null);
		
		JLabel lblTwitchBotName = new JLabel("Twitch Bot Name:");
		lblTwitchBotName.setBounds(10, 12, 99, 16);
		lblTwitchBotName.setForeground(style.getText());
		frame.getContentPane().add(lblTwitchBotName);
		
		JLabel lblTwitchOauthKey = new JLabel("Twitch OAuth Key:");
		lblTwitchOauthKey.setBounds(10, 42, 103, 16);
		lblTwitchOauthKey.setForeground(style.getText());
		frame.getContentPane().add(lblTwitchOauthKey);
		
		JLabel lblTwitchChannel = new JLabel("Twitch Channel:");
		lblTwitchChannel.setBounds(10, 72, 90, 16);
		lblTwitchChannel.setForeground(style.getText());
		frame.getContentPane().add(lblTwitchChannel);
		
		JButton btnGetOauthKey = new JButton("Get OAuth Key");
		btnGetOauthKey.setBounds(10, 100, 115, 26);
		btnGetOauthKey.setBackground(style.getButton_background());
		btnGetOauthKey.setForeground(style.getButton_text());
		frame.getContentPane().add(btnGetOauthKey);
		
		botNameTF = new JTextField();
		botNameTF.setBounds(112, 10, 152, 20);
		botNameTF.setBackground(style.getTextbox_background());
		botNameTF.setForeground(style.getTextbox_text());
		botNameTF.setText(info.getBotName());
		frame.getContentPane().add(botNameTF);
		botNameTF.setColumns(10);
		
		OAuthKeyTF = new JTextField();
		OAuthKeyTF.setToolTipText("Leave blank if you are using the same bot");
		OAuthKeyTF.setColumns(10);
		OAuthKeyTF.setBounds(114, 40, 150, 20);
		OAuthKeyTF.setBackground(style.getTextbox_background());
		OAuthKeyTF.setForeground(style.getTextbox_text());
		frame.getContentPane().add(OAuthKeyTF);
		
		TwichChannelTF = new JTextField();
		TwichChannelTF.setColumns(10);
		TwichChannelTF.setBounds(101, 70, 163, 20);
		TwichChannelTF.setBackground(style.getTextbox_background());
		TwichChannelTF.setForeground(style.getTextbox_text());
		TwichChannelTF.setText(info.getTwitchChannel());
		frame.getContentPane().add(TwichChannelTF);
		
		JButton btnSave = new JButton("Save");
		btnSave.setBounds(165, 100, 98, 26);
		btnSave.setBackground(style.getButton_background());
		btnSave.setForeground(style.getButton_text());
		frame.getContentPane().add(btnSave);
		
		//Listeners
		//Gets OAuth key
		btnGetOauthKey.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					  Desktop desktop = java.awt.Desktop.getDesktop();
					  URI oURL = new URI("https://twitchapps.com/tmi/");
					  desktop.browse(oURL);
					} catch (Exception e) {
						debug.debug("TwitchPopupGetOAuthActionLister:" + "There was an error getting window");
						debug.debug(e.getStackTrace());
					}
			}
		});
		//Save Button
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				open = false;
				if(OAuthKeyTF.getText() == null || OAuthKeyTF.getText().equals(""))
					info.saveSettings(botNameTF.getText(), info.getBotOAuth(), TwichChannelTF.getText(), info.getAddTimeCommand(), info.getSubTimeCommand(), info.getAddTimeFormat(), info.getSubTimeFormat());
				else
					info.saveSettings(botNameTF.getText(), OAuthKeyTF.getText(), TwichChannelTF.getText(), info.getAddTimeCommand(), info.getSubTimeCommand(), info.getAddTimeFormat(), info.getSubTimeFormat());
				frame.dispose();
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
	
	public boolean getOpen() {
		return open;
	}
	
	public void setFocus() {
		frame.setVisible(true);
		frame.toFront();
		frame.requestFocus();
	}
}
