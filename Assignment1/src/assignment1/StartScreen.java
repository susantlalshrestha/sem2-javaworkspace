package assignment1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * StartScreen is a JPanel that is responsible for the start screen of the
 * hangman game. It displays the app logo and provides three buttons for the
 * user: 1. Start Playing 2. Add New Text 3. Exit
 * 
 * @author Susant Shrestha
 */
public class StartScreen extends JPanel {
	/** The AppScreenNavigator instance that is used to navigate to different screens. */
	private AppScreenNavigator navigator;

	/**
	 * The constructor of the StartScreen class.
	 * 
	 * @param navigator - The AppScreenNavigator interface that is used to navigate
	 *                  to different screens in the hangman game.
	 */
	StartScreen(AppScreenNavigator navigator) {
		// Assign the passed navigator to the class variable.
		this.navigator = navigator;
		// Initialize the UI of the start screen.
		this.initUI();
	}

	/**
	 * The method responsible for initializing the UI of the start screen.
	 */
	private void initUI() {
		// Set the background color of the panel to white.
		this.setBackground(Color.WHITE);
		// Set the layout manager for the panel.
		this.setLayout(new BorderLayout(0, 0));
		// Add the app logo to the center of the panel.
		this.addAppLogo();
		// Add the buttons to the bottom of the panel.
		this.addButtons();
	}

	/**
	 * The method responsible for adding the app logo to the center of the panel.
	 */
	private void addAppLogo() {
		// Load the app icon using the path stored in the AppConstants class.
		ImageIcon icon = new ImageIcon(AppConstants.APP_ICON_PATH);
		// Create a JLabel to display the app logo.
		JLabel appLogo = new JLabel(icon, JLabel.CENTER);
		// Add the app logo to the center of the panel.
		this.add(appLogo, BorderLayout.CENTER);
	}

	/**
	 * The method responsible for adding the buttons to the bottom of the panel.
	 */
	private void addButtons() {
		// Create a JPanel to hold the buttons.
		JPanel buttonPanel = new JPanel();
		// Set the preferred size of the button panel.
		buttonPanel.setPreferredSize(new Dimension(AppConstants.APP_FRAME_WIDTH, AppConstants.APP_FRAME_HEIGHT / 4));
		// Set the layout manager for the button panel.
		buttonPanel.setLayout(new GridLayout(3, 1, 0, 0));

		// Create a JButton for starting the game.
		JButton btnStartPlaying = new JButton("Start Playing");
		// Add an action listener to the start playing button to open the game screen.
		btnStartPlaying.addActionListener(e -> navigator.openGameScreen());

		// Create the "Add New Text" button.
		JButton btnAddNewText = new JButton("Add New Text");
		// Add an action listener to the "Add New Text" button.
		btnAddNewText.addActionListener(e -> navigator.openAddNewWordScreen());

		// Create the "Exit" button.
		JButton btnExit = new JButton("Exit");
		// Add an action listener to the "Exit" button.
		btnExit.addActionListener(e -> navigator.exitGame());

		// Add the buttons to the button panel.
		buttonPanel.add(btnStartPlaying);
		buttonPanel.add(btnAddNewText);
		buttonPanel.add(btnExit);

		// Add the button panel to the StartScreen.
		this.add(buttonPanel, BorderLayout.SOUTH);
	}
}
