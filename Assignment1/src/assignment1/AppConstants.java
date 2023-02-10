package assignment1;

/**
 * AppConstants is a final class that contains various constants that are used
 * throughout the Hangman game application.
 * 
 * @author Susant Shrestha
 */
public class AppConstants {

	/**
	 * The title of the game application.
	 */
	public static final String APP_TITLE = "HANGMAN";

	/**
	 * The path to the application icon image.
	 */
	public static final String APP_ICON_PATH = "assets/images/app_logo.png";

	/**
	 * The width of the game application frame.
	 */
	public static final int APP_FRAME_WIDTH = 500;

	/**
	 * The height of the game application frame.
	 */
	public static final int APP_FRAME_HEIGHT = 500;

	/**
	 * The path to the data source file that contains the words for the Hangman
	 * game.
	 */
	public static final String DATASOURCE_FILE_PATH = "assets/datasource/hangman.txt";

	/**
	 * The total number of lives available in the Hangman game.
	 */
	public static final int TOTAL_LIVES = 5;

	/**
	 * An array of paths to the images of the Hangman used in the game.
	 */
	public static final String[] HANGMAN_PICS = { "assets/images/hangman_pic_1.png", "assets/images/hangman_pic_2.png",
			"assets/images/hangman_pic_3.png", "assets/images/hangman_pic_4.png", "assets/images/hangman_pic_5.png",
			"assets/images/hangman_pic_6.png", };
}
