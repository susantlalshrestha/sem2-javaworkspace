package assignment1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * The GameScreen class extends JPanel and represents the game screen of a
 * hangman game. It contains all the necessary UI components and functions to
 * play the game.
 */
public class GameScreen extends JPanel {
	// The AppScreenNavigator instance that is used to navigate to different
	// screens.
	private AppScreenNavigator navigator;
	// The HangmanDataSource instance that is used to get a random word
	private HangmanDataSource datasource;
	// The word to be guessed in the current game.
	private String randomWord;
	// The word that is being guessed by the player, represented by asterisks and
	// correctly guessed letters.
	private String resultWord;
	// The letters that have already been guessed in the current game.
	private String guessedLetters;
	// The number of lives remaining for the player in the current game.
	private int livesLeft;
	// A flag to keep track of whether the game is in progress or has been stopped.
	public boolean isGameStopped;

	// A JLabel to display the hangman picture.
	private JLabel hangmanPic;
	// A JLabel to display the word being guessed by the player.
	private JLabel labelGuess;
	// A JLabel to display error messages.
	private JLabel labelError;
	// A JTextField to allow the player to enter their guess.
	private JTextField textFieldGuess;
	// A JButton to submit the player's guess.
	private JButton buttonGuess;

	/**
	 * Constructor for the GameScreen class. It takes two parameters:
	 * 
	 * @param navigator  - The AppScreenNavigator to navigate between different
	 *                   screens in the app.
	 * @param datasource - The HangmanDataSource to provide the data for the hangman
	 *                   game.
	 */
	public GameScreen(AppScreenNavigator navigator, HangmanDataSource datasource) {
		this.navigator = navigator;
		this.datasource = datasource;
		this.initUI();
		this.startPlaying();
	}

	/**
	 * The initUI method is used to initialize and set up the UI components.
	 */
	private void initUI() {
		// Set the background color to white.
		this.setBackground(Color.WHITE);
		// Set the layout to BorderLayout.
		this.setLayout(new BorderLayout(0, 0));

		// Create the hangman picture label and set its properties.
		this.hangmanPic = new JLabel();
		this.hangmanPic.setHorizontalTextPosition(JLabel.CENTER);
		this.hangmanPic.setVerticalTextPosition(JLabel.BOTTOM);
		this.hangmanPic.setHorizontalAlignment(JLabel.CENTER);
		this.hangmanPic.setVerticalAlignment(JLabel.CENTER);
		this.hangmanPic.setIconTextGap(20);
		this.add(hangmanPic, BorderLayout.CENTER);

		// Create the guess panel, set its properties and layout.
		JPanel guessPanel = new JPanel();
		guessPanel.setPreferredSize(new Dimension(AppConstants.APP_FRAME_WIDTH, AppConstants.APP_FRAME_HEIGHT / 2));
		guessPanel.setBackground(Color.WHITE);
		guessPanel.setLayout(new GridLayout(3, 1));

		// Create the label for the guess and set its properties.
		this.labelGuess = new JLabel();
		this.labelGuess.setHorizontalAlignment(JLabel.CENTER);
		this.labelGuess.setVerticalAlignment(JLabel.CENTER);
		guessPanel.add(labelGuess);

		// Create the input panel, set its background color and layout.
		JPanel inputPanel = new JPanel();
		inputPanel.setBackground(Color.WHITE);
		this.textFieldGuess = new JTextField();
		this.textFieldGuess.setPreferredSize(new Dimension(100, 50));
		this.textFieldGuess.setHorizontalAlignment(JTextField.CENTER);
		inputPanel.add(textFieldGuess);

		// Initialize the button for guessing a letter.
		this.buttonGuess = new JButton("Guess");
		this.buttonGuess.setPreferredSize(new Dimension(80, 50));
		this.buttonGuess.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String guess = textFieldGuess.getText().trim();
				if (guess != null && !guess.equals("")) {
					// Clear the text and check if the entered letter is valid.
					clearText();
					if (guess.length() > 1) {
						// Show error message if more than one letter is entered.
						showErrorMessage("Please enter only one letter!!");
					} else {
						// Match the letter with the word to be guessed.
						matchLetter(guess.toUpperCase().charAt(0));
					}
				}
			}
		});
		inputPanel.add(buttonGuess);
		guessPanel.add(inputPanel);

		// Initialize the label for displaying error messages.
		this.labelError = new JLabel("Hello");
		this.labelError.setHorizontalAlignment(JLabel.CENTER);
		this.labelError.setVerticalAlignment(JLabel.CENTER);
		this.labelError.setForeground(Color.RED);
		guessPanel.add(labelError);

		// Add the guess panel to the bottom of the screen.
		this.add(guessPanel, BorderLayout.SOUTH);
	}

	/**
	 * The startPlaying method is used to start a new game. It sets the randomWord,
	 * resultWord, guessedLetters, and livesLeft variables to their initial values.
	 * It also updates the hangman picture and the word being guessed by the player.
	 */
	private void startPlaying() {
		// Set the random word for the game
		this.randomWord = this.datasource.getRandomWord();
		// Set the result word as asterisks with the same length as the random word
		this.resultWord = "*".repeat(this.randomWord.length());
		// Set the guessed letters to an empty string
		this.guessedLetters = "";
		// Set the number of lives left to the total number of lives
		this.livesLeft = AppConstants.TOTAL_LIVES;
		// Set the isGameStopped flag to false
		this.isGameStopped = false;
		// Show the cheat word in the console
		System.out.println("Cheat: " + this.randomWord);
		// Update the UI.
		this.updateHangmanPic();
		this.updateGuessText();
		this.clearText();
	}

	/**
	 * The matchLetter method is called to match the input letter with the word to
	 * be guessed. If the letter has already been guessed, it shows an error
	 * message. If the letter is not present in the word to be guessed, the number
	 * of lives left is decreased. If the letter is present, the corresponding
	 * letter in the current state of the word to be guessed is updated.
	 * 
	 * @param letter - the input letter to be matched with the word to be guessed
	 */
	private void matchLetter(char letter) {
		if (this.guessedLetters.contains(String.valueOf(letter))) {
			this.showErrorMessage("'" + String.valueOf(letter).toUpperCase() + "' has already been guessed.");
			return;
		}
		this.guessedLetters += String.valueOf(letter);
		if (!this.randomWord.contains(String.valueOf(letter))) {
			decreaseLives();
			this.checkResult();
			return;
		}
		char[] guessedChars = this.resultWord.toCharArray();
		for (int i = 0; i < this.randomWord.length(); i++) {
			if (this.randomWord.charAt(i) == letter) {
				guessedChars[i] = letter;
			}
		}
		this.resultWord = new String(guessedChars);
		this.updateGuessText();
		this.checkResult();
	}

	/**
	 * The decreaseLives method decreases the number of lives left by 1 and updates
	 * the hangman picture.
	 */
	private void decreaseLives() {
		this.livesLeft -= 1;
		this.updateHangmanPic();
	}

	/**
	 * updateGuessText method updates the text of the labelGuess with the value of
	 * resultWord
	 */
	private void updateGuessText() {
		// Set the text of labelGuess to the value of resultWord
		this.labelGuess.setText(resultWord);
	}

	/**
	 * updateHangmanPic method updates the hangman picture based on the livesLeft
	 */
	private void updateHangmanPic() {
		// Set the text of hangmanPic
		this.hangmanPic.setText("(" + this.livesLeft + " guesses remaining)");
		// Set the icon of hangmanPic to the image in AppConstants.HANGMAN_PICS at the
		// index of livesLeft
		this.hangmanPic.setIcon(new ImageIcon(AppConstants.HANGMAN_PICS[this.livesLeft]));
	}

	/**
	 * showErrorMessage method displays the error message passed as parameter in the
	 * labelError
	 * 
	 * @param message - The error message to be displayed
	 */
	private void showErrorMessage(String message) {
		// Set the text of labelError to the value of message
		this.labelError.setText(message);
	}

	/**
	 * clearText method clears the text from textFieldGuess and labelError and sets
	 * the focus to textFieldGuess
	 */
	private void clearText() {
		// Set the text of textFieldGuess to an empty string
		this.textFieldGuess.setText("");
		// Set the text of labelError to an empty string
		this.labelError.setText("");
		// Request focus for textFieldGuess
		this.textFieldGuess.requestFocus();
	}

	/**
	 * checkResult method checks the result of the game and displays a dialog box to
	 * ask the user if they want to play again or go back to the start screen
	 */
	private void checkResult() {
		// Set isGameStopped to true
		this.isGameStopped = true;
		// Check if the resultWord is equal to the randomWord
		if (this.resultWord.equalsIgnoreCase(randomWord)) {
			// display a dialog box asking the user if they want to play again or go back to
			// the start screen
			int choice = JOptionPane.showOptionDialog(null, "Do you want to play again?", "You Won!!",
					JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, 0);
			// if the player chooses to play again
			if (choice == 0) {
				// start playing the game again
				this.startPlaying();
			} else { // if the player chooses to quit
				// open the start screen
				navigator.openStartScreen();
			}
			return;
		}
		// Check if the lives left is 0
		if (this.livesLeft == 0) {
			// Show a dialog box to ask the player if they want to try again
			int choice = JOptionPane.showOptionDialog(null, "Do you want to try again?", "You Lost!!",
					JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, 0);
			// if the player chooses to try again
			if (choice == 0) {
				// start playing the game again
				this.startPlaying();
			} else { // if the player chooses to quit
				// open the start screen
				navigator.openStartScreen();
			}
			return;
		}
		// set the game state to not stopped
		this.isGameStopped = false;
	}
}
