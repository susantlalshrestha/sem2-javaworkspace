package assignment1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class HangmanDataSource {
	private ArrayList<String> wordList;

	public HangmanDataSource() {
		this.wordList = new ArrayList<String>();
		fetchWordList();
	}

	private void fetchWordList() {
		File file = new File(AppConstants.DATASOURCE_FILE_PATH);
		try (Scanner scanner = new Scanner(file)) {
			while (scanner.hasNextLine()) {
				wordList.add(scanner.nextLine().toUpperCase());
			}
		} catch (FileNotFoundException e) {
			System.out.printf("Cannot find a file in the give path: %s.\n", AppConstants.DATASOURCE_FILE_PATH);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public ArrayList<String> getWordList() {
		return wordList;
	}

	public String getRandomWord() {
		Random random = new Random();
		int randomIndex = random.nextInt(this.wordList.size());
		return this.wordList.get(randomIndex);
	}

	public boolean addNewWord(String newWord) {
		if (newWord == null || newWord.trim().equals("")) {
			return false;
		}
		File file = new File(AppConstants.DATASOURCE_FILE_PATH);
		try (PrintWriter writer = new PrintWriter(file);) {
			wordList.add(newWord.trim().toUpperCase());
			for (String word : wordList) {
				writer.println(word);
			}
			return true;
		} catch (FileNotFoundException e) {
			System.out.printf("Cannot find a file in the give path: %s.\n", AppConstants.DATASOURCE_FILE_PATH);
			return false;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	public boolean removeWord(String word) {
		if (word == null || word.trim().equals("") || !wordList.contains(word)) {
			return false;
		}
		File file = new File(AppConstants.DATASOURCE_FILE_PATH);
		try (PrintWriter writer = new PrintWriter(file);) {
			wordList.remove(wordList.indexOf(word.trim().toUpperCase()));
			for (String w : wordList) {
				writer.println(w);
			}
			return true;
		} catch (FileNotFoundException e) {
			System.out.printf("Cannot find a file in the give path: %s.\n", AppConstants.DATASOURCE_FILE_PATH);
			return false;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}
}
