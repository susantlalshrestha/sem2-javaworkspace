package assignment1;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import javax.swing.*;

public class AddNewWordScreen extends JPanel {
	private AppScreenNavigator navigator;
	private HangmanDataSource datasource;
	private JPanel viewWords;
	private DefaultListModel<String> listModel;
	private JList<String> listOfWords;
	private JLabel wordLabel;
	private JPanel addWord;
	private JTextField wordField;
	private JButton addButton;
	private JButton backButton;

	
	public AddNewWordScreen(AppScreenNavigator navigator, HangmanDataSource datasource) {
		this.navigator = navigator;
		this.datasource = datasource;
		this.initUI();
	}

	private void initUI() {
		this.setBackground(Color.WHITE);
		this.setLayout(new BorderLayout(0, 0));
		// TODO: Create GUI list all the words in JList and take input from user in JTextField and when pressed add button it should this function
		// this.datasource.addNewWord("asdfhl");
		
		BackButton();
		
		ViewWords();
		
		AddWords();
		
		CreateLayout();
		
	}
	
	public void ViewWords() {
		viewWords = new JPanel();
		viewWords.setLayout(new BorderLayout());
		viewWords.setBackground(Color.WHITE);
		
	    listModel = new DefaultListModel<>();
	    try {
	      BufferedReader reader = new BufferedReader(new FileReader(AppConstants.DATASOURCE_FILE_PATH));
	      String line;
	      while ((line = reader.readLine()) != null) {
	        listModel.addElement(line);
	      }
	      reader.close();
	    } catch (IOException e) {
	      JOptionPane.showMessageDialog(this, "Error reading file", "Error", JOptionPane.ERROR_MESSAGE);
	    }
		
		listOfWords = new JList<>(listModel);
		listOfWords.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listOfWords.setVisibleRowCount(5);
		JScrollPane scrollPane = new JScrollPane(listOfWords);	
		scrollPane.setPreferredSize(new Dimension(250, 80));
		viewWords.add(scrollPane , BorderLayout.CENTER);
		
	}
	
	public void AddWords() {
		addWord = new JPanel();
		
		wordLabel = new JLabel("Type the word to add: ");
		
		wordField = new JTextField(20);
		
		addButton = new JButton("Add word");
		addButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent ae) 
			{
				String word = wordField.getText().trim();
				if(!word.isEmpty()) {
					word = word.toUpperCase();
					listModel.addElement(word);
					try {
						BufferedWriter saveWord = new BufferedWriter(new FileWriter(AppConstants.DATASOURCE_FILE_PATH, true));
						saveWord.write(word + System.lineSeparator());
						saveWord.close();
					} catch(IOException e) {
						JOptionPane.showMessageDialog(addWord, "Word not saved in the file", "Error",JOptionPane.ERROR_MESSAGE);
					}
					wordField.setText("");
				} else {
					JOptionPane.showMessageDialog(addWord,  "Enter a word", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		addWord.add(wordLabel);
		addWord.add(wordField);
		addWord.add(addButton);
		
	}
		
	public void BackButton() {
		backButton = new JButton("Go back to main menu");
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				navigator.openStartScreen();
			}
		});
	}
	
	public void CreateLayout() {
		add(backButton, BorderLayout.NORTH);
		add(viewWords, BorderLayout.CENTER);
		add(addWord, BorderLayout.SOUTH);
	}
	
}
