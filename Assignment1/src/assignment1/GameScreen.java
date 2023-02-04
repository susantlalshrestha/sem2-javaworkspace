package assignment1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GameScreen extends JPanel {
	private AppScreenNavigator navigator;
	private HangmanDataSource datasource;
	private String randomWord;
	private String resultWord;
	private String guessedLetters;
	private int livesLeft;
	public boolean isGameStopped;

	private JLabel hangmanPic;
	private JLabel labelGuess;
	private JLabel labelError;
	private JTextField textFieldGuess;
	private JButton buttonGuess;

	public GameScreen(AppScreenNavigator navigator, HangmanDataSource datasource) {
		this.navigator = navigator;
		this.datasource = datasource;
		this.initUI();
		this.startPlaying();
	}

	private void initUI() {
		this.setBackground(Color.WHITE);
		this.setLayout(new BorderLayout(0, 0));
		this.hangmanPic = new JLabel();
		this.hangmanPic.setHorizontalTextPosition(JLabel.CENTER);
		this.hangmanPic.setVerticalTextPosition(JLabel.BOTTOM);
		this.hangmanPic.setHorizontalAlignment(JLabel.CENTER);
		this.hangmanPic.setVerticalAlignment(JLabel.CENTER);
		this.hangmanPic.setIconTextGap(20);
		this.add(hangmanPic, BorderLayout.CENTER);

		JPanel guessPanel = new JPanel();
		guessPanel.setPreferredSize(new Dimension(AppConstants.APP_FRAME_WIDTH, AppConstants.APP_FRAME_HEIGHT / 2));
		guessPanel.setBackground(Color.WHITE);
		guessPanel.setLayout(new GridLayout(3, 1));

		this.labelGuess = new JLabel();
		this.labelGuess.setHorizontalAlignment(JLabel.CENTER);
		this.labelGuess.setVerticalAlignment(JLabel.CENTER);
		guessPanel.add(labelGuess);

		JPanel inputPanel = new JPanel();
		inputPanel.setBackground(Color.WHITE);
		this.textFieldGuess = new JTextField();
		this.textFieldGuess.setPreferredSize(new Dimension(100, 50));
		this.textFieldGuess.setHorizontalAlignment(JTextField.CENTER);
		inputPanel.add(textFieldGuess);

		this.buttonGuess = new JButton("Guess");
		this.buttonGuess.setPreferredSize(new Dimension(80, 50));
		this.buttonGuess.addActionListener(e -> {
			String guess = this.textFieldGuess.getText().trim();
			if (guess != null && !guess.equals("")) {
				this.clearText();
				if (guess.length() > 1) {
					this.showErrorMessage("Please enter only one letter!!");
				} else {
					this.matchLetter(guess.toUpperCase().charAt(0));
				}
			}
		});
		inputPanel.add(buttonGuess);
		guessPanel.add(inputPanel);

		this.labelError = new JLabel("Hello");
		this.labelError.setHorizontalAlignment(JLabel.CENTER);
		this.labelError.setVerticalAlignment(JLabel.CENTER);
		this.labelError.setForeground(Color.RED);
		guessPanel.add(labelError);

		this.add(guessPanel, BorderLayout.SOUTH);
	}

	private void startPlaying() {
		this.randomWord = datasource.getRandomWord();
		this.resultWord = "*".repeat(this.randomWord.length());
		this.guessedLetters = "";
		this.livesLeft = AppConstants.TOTAL_LIVES;
		this.isGameStopped = false;
		System.out.println("Cheat: " + this.randomWord);
		this.updateHangmanPic();
		this.updateGuessText();
		this.clearText();
	}

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

	private void decreaseLives() {
		this.livesLeft -= 1;
		this.updateHangmanPic();
	}

	private void updateGuessText() {
		this.labelGuess.setText(resultWord);
	}

	private void updateHangmanPic() {
		this.hangmanPic.setText("(" + this.livesLeft + " guesses remaining)");
		this.hangmanPic.setIcon(new ImageIcon(AppConstants.HANGMAN_PICS[this.livesLeft]));
	}

	private void showErrorMessage(String message) {
		this.labelError.setText(message);
	}

	private void clearText() {
		this.textFieldGuess.setText("");
		this.labelError.setText("");
		this.textFieldGuess.requestFocus();
	}

	private void checkResult() {
		this.isGameStopped = true;
		if (this.resultWord.equalsIgnoreCase(randomWord)) {
			int choice = JOptionPane.showOptionDialog(null, "Do you want to play again?", "You Won!!",
					JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, 0);
			if (choice == 0) {
				this.startPlaying();
			} else {
				navigator.openStartScreen();
			}
			return;
		}
		if (this.livesLeft == 0) {
			int choice = JOptionPane.showOptionDialog(null, "Do you want to try again?", "You Lost!!",
					JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, 0);
			if (choice == 0) {
				this.startPlaying();
			} else {
				navigator.openStartScreen();
			}
			return;
		}
		this.isGameStopped = false;
	}
}
