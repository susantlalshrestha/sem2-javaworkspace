package assignment1;

import java.awt.Color;

import javax.swing.JPanel;

public class GameScreen extends JPanel {
	private AppScreenNavigator navigator;
	private HangmanDataSource datasource;
	private String randomWord;
	private String guessedWord;
	private int livesLeft;
	public boolean isGameStopped;

	public GameScreen(AppScreenNavigator navigator, HangmanDataSource datasource) {
		this.navigator = navigator;
		this.datasource = datasource;
		this.randomWord = datasource.getRandomWord();
		this.guessedWord = "_".repeat(randomWord.length());
		this.livesLeft = AppConstants.TOTAL_LIVES;
		this.isGameStopped = false;
		this.initUI();
	}

	private void initUI() {
		this.setBackground(Color.green);
	}

	private void matchLetter(char letter) {
		if (guessedWord.contains(String.valueOf(letter))) {
			// TODO: show error message for already guessed letter
			return;
		}
		if (!randomWord.contains(String.valueOf(letter))) {
			this.livesLeft -= 1;
			// TODO: Refresh the lives left in the ui
			return;
		}
		char[] guessedChars = guessedWord.toCharArray();
		for (int i = 0; i < randomWord.length(); i++) {
			if (randomWord.charAt(i) == letter) {
				guessedChars[i] = letter;
			}
		}
		guessedWord = new String(guessedChars);

	}
}
