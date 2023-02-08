package assignment1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * HangmanDataSource class is used to load and manage the word list for the
 * hangman game. It fetches the words from hangman.txt file and stores it in an
 * ArrayList. The class provides methods to access the word list and add new
 * words to the list.
 * 
 * @author Susant Shrestha
 */
public class HangmanDataSource {
	/** The ArrayList that stores the word list. */
	private ArrayList<String> wordList;

	public HangmanDataSource() {
		// Initialize the wordList
		this.wordList = new ArrayList<String>();
		// Fetch the word list from the data source file
		fetchWordList();
	}

	/**
	 * Fetches word list from the file at the location specified in
	 * AppConstants.DATASOURCE_FILE_PATH.
	 */
	private void fetchWordList() {
		// Create a File object for the data source file
		File file = new File(AppConstants.DATASOURCE_FILE_PATH);
		// Use a try-with-resources statement to create a Scanner object and read the
		// data source file
		try (Scanner scanner = new Scanner(file)) {
			// Read each line in the file and add it to the wordList ArrayList
			while (scanner.hasNextLine()) {
				wordList.add(scanner.nextLine().toUpperCase());
			}
		} catch (FileNotFoundException e) {
			// If the file is not found, print an error message
			System.out.printf("Cannot find a file in the give path: %s.\n", AppConstants.DATASOURCE_FILE_PATH);
		} catch (Exception e) {
			// If there is any other exception, print the exception message
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Gets the word list.
	 * 
	 * @return the word list.
	 */
	public ArrayList<String> getWordList() {
		return wordList;
	}

	/**
	 * Gets a random word from the word list.
	 * 
	 * @return a random word from the word list.
	 */
	public String getRandomWord() {
		// Create a Random object to generate random numbers
		Random random = new Random();
		// Generate a random index for the wordList ArrayList
		int randomIndex = random.nextInt(this.wordList.size());
		// Return the word at the randomly generated index
		return this.wordList.get(randomIndex);
	}

	/**
	 * Adds a new word to the word list.
	 * 
	 * @param - newWord the new word to add.
	 * @return true if the new word was added successfully, false otherwise.
	 */
	public void addNewWord(String newWord) throws Exception {
		// If the newWord is null or is an empty string, throw an exception
		if (newWord == null || newWord.trim().isEmpty()) {
			throw new Exception("Empty word cannot be added!!");
		}
		// If the newWord is null or is an empty string, throw an exception
		if (wordList.contains(newWord.toUpperCase())) {
			throw new Exception(newWord + " is already added!!");
		}
		// Create a File object for the data source file
		File file = new File(AppConstants.DATASOURCE_FILE_PATH);
		try (PrintWriter writer = new PrintWriter(file);) {
			// Add the new word to the word list
			wordList.add(newWord.trim().toUpperCase());
			// Write the updated word list to the file
			for (String word : wordList) {
				writer.println(word);
			}
		} catch (FileNotFoundException e) {
			// Catch and throw the exception if the file could not be found
			throw new Exception("Cannot find a file in the give path: " + AppConstants.DATASOURCE_FILE_PATH);
		} catch (Exception e) {
			// Catch and throw other exception
			System.out.println(e.getMessage());
			throw new Exception("Unexpected error occured while adding new word");
		}
	}
}
