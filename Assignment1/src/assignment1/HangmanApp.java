package assignment1;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class HangmanApp extends JFrame {
	private static final String APP_TITLE = "HANGMAN";
	private static final String APP_ICON_PATH = "assets/images/app_logo.png";

	public HangmanApp() {
		this.init();
	}

	private void init() {
		// Set title of the frame
		this.setTitle(APP_TITLE);
		// Exit out of application on close
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Set the size of the frame
		this.setSize(420, 420);
		// Prevent frame from being resized
		this.setResizable(false);
		// Place the window in center of screen
		this.setLocationRelativeTo(null);
		// Change icon of frame
		this.setIconImage(new ImageIcon(APP_ICON_PATH).getImage() );
		// Make frame visible
		this.setVisible(true);
	}
}
