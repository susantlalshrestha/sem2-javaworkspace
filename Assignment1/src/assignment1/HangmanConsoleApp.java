package assignment1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class HangmanConsoleApp {
	private String WORDS_FILE_PATH = "assets/hangman.txt";
	private ArrayList<String> allWords = new ArrayList<String>();
	Scanner kbd = new Scanner(System.in);

	public HangmanConsoleApp() {
		getAllWordsFromFile();
	}

	public void start() {
		displayStartScreen();
		int playersChoice = kbd.nextInt();
		while (playersChoice != 3) {
			if (playersChoice == 1) {
				startPlaying();
				displayStartScreen();
			} else if (playersChoice == 2) {
				addNewWords();
				displayStartScreen();
			} else {
				System.out.println(" - Invalid choice!! Please make a valid choice");
			}
			playersChoice = kbd.nextInt();
		}
		System.out.println(" - Thank you!! Hope you had a good time!! :)");
		kbd.close();
	}

	private void startPlaying() {
		String randomWord = selectRandomWord();
		String guessedWord = "*".repeat(randomWord.length());
		int lives = 5;
		System.out.println(randomWord);
		do {
			System.out.printf("(Lives left %d) Enter a letter in word %s:", lives, guessedWord);
			char guessLetter = kbd.next().toUpperCase().charAt(0);
			if (!guessedWord.contains(String.valueOf(guessLetter))
					&& randomWord.contains(String.valueOf(guessLetter))) {
				char[] guessedChars = guessedWord.toCharArray();
				for (int i = 0; i < randomWord.length(); i++) {
					if (randomWord.charAt(i) == guessLetter) {
						guessedChars[i] = guessLetter;
					}
				}
				guessedWord = new String(guessedChars);
			} else {
				lives--;
			}
		} while (lives > 0 && !guessedWord.equalsIgnoreCase(randomWord));

		if (lives == 0) {
			System.out.printf("You lose! The word was %s.\n", randomWord);
		} else {
			System.out.printf("You win! The word was %s.\n", randomWord);
		}
	}

	private void addNewWords() {
		String choice;
		do {
			System.out.println("Enter a new word: ");
			addNewWordToFile(kbd.next());
			System.out.println("Word added!!");
			System.out.println("Do you want to add more words??");
			System.out.println("Enter 'Y' to add, any other key to go back!!");
			choice = kbd.next();
		} while (choice.equalsIgnoreCase("Y"));
	}

	private void getAllWordsFromFile() {
		File file = new File(WORDS_FILE_PATH);
		try (Scanner scanner = new Scanner(file)) {
			while (scanner.hasNextLine()) {
				allWords.add(scanner.nextLine().toUpperCase());
			}
		} catch (FileNotFoundException e) {
			System.out.printf("Cannot find a file in the give path: %s.\n", WORDS_FILE_PATH);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private void addNewWordToFile(String newWord) {
		File file = new File(WORDS_FILE_PATH);
		try (PrintWriter writer = new PrintWriter(file);) {
			allWords.add(newWord.toUpperCase());
			for (String word : allWords) {
				writer.println(word);
			}
		} catch (FileNotFoundException e) {
			System.out.printf("Cannot find a file in the give path: %s.\n", WORDS_FILE_PATH);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private String selectRandomWord() {
		Random random = new Random();
		int randomIndex = random.nextInt(allWords.size());
		return allWords.get(randomIndex);
	}

	private void displayStartScreen() {
		System.out.println("-".repeat(80));
		System.out.printf("\n%44s\n\n", "!!HANGMAN!!");
		System.out.printf("%48s\n\n", "Select you choice:");
		System.out.printf("%46s\n", "1: Start Playing");
		System.out.printf("%46s\n", "2: Add New Words");
		System.out.printf("%42s\n\n", "3: Exit Game");
		System.out.println("-".repeat(80));
	}
}
