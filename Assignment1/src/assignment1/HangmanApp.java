package assignment1;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * The HangmanApp class is the main frame of the Hangman game application. It
 * extends JFrame and implements AppScreenNavigator interface.
 * 
 * @author Susant Shrestha
 */
public class HangmanApp extends JFrame implements AppScreenNavigator {
	// instance variable to store the content pane of the frame
	private JPanel contentPane;
	// instance variable to store the data source for the game
	private HangmanDataSource dataSource;

	/**
	 * The private constructor initializes the data source and the user interface.
	 */
	private HangmanApp() {
		// create an instance of HangmanDataSource
		this.dataSource = new HangmanDataSource();
		// initialize the UI of the frame
		this.initUI();
	}

	/**
	 * Method to initialize the UI of the frame.
	 */
	private void initUI() {
		// create new jpanel and assign it to contentPane
		this.contentPane = new JPanel();
		// set the layout of contentPane to BorderLayout
		this.contentPane.setLayout(new BorderLayout(0, 0));
		// set the contentPane as the content pane of this frame
		this.setContentPane(contentPane);
		// initially open start screen panel
		this.openStartScreen();
		// set title of the frame
		this.setTitle(AppConstants.APP_TITLE);
		// exit out of application on close
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// set the size of the frame
		this.setSize(AppConstants.APP_FRAME_WIDTH, AppConstants.APP_FRAME_HEIGHT);
		// prevent frame from being resized
		this.setResizable(false);
		// place the window in center of screen
		this.setLocationRelativeTo(null);
		// change icon of frame
		this.setIconImage(new ImageIcon(AppConstants.APP_ICON_PATH).getImage());
		// make frame visible
		this.setVisible(true);
	}

	/**
	 * Method to change the current panel of the contentPane.
	 * 
	 * @param panel - JPanel to be displayed in the contentPane.
	 */
	private void changePanel(JPanel panel) {
		// remove all components from the contentPane
		contentPane.removeAll();
		// add the specified panel to the contentPane
		contentPane.add(panel, BorderLayout.CENTER);
		// validate the changes made to the contentPane
		contentPane.validate();
		// repaint the contentPane to reflect the changes
		contentPane.repaint();
	}

	/**
	 * The openStartScreen method opens the start screen.
	 */
	@Override
	public void openStartScreen() {
		// Change the current panel to the start screen.
		changePanel(new StartScreen(this));
	}

	/**
	 * The openAddNewWordScreen method opens the add new word screen.
	 */
	@Override
	public void openAddNewWordScreen() {
		// Change the current panel to the add new word screen.
		changePanel(new AddNewWordScreen(this, dataSource));
	}

	/**
	 * The openGameScreen method opens the game screen.
	 */
	@Override
	public void openGameScreen() {
		// Change the current panel to the game screen.
		changePanel(new GameScreen(this, dataSource));
	}

	/**
	 * The exitGame method closes the game and exits the application.
	 */
	@Override
	public void exitGame() {
		// Show a confirmation dialog to ask the user if they really want to exit the
		// game.
		int choice = JOptionPane.showOptionDialog(null, "Are you sure you want to exit the game?", null,
				JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, 0);
		if (choice == 0) {
			System.exit(0);
		}
	}

	/**
	 * The open method creates a new instance of the HangmanApp and opens it.
	 */
	public static void open() {
		// Create a new instance of the HangmanApp.
		new HangmanApp();
	}
}
