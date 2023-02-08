package assignment1;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Scanner;

import javax.swing.*;

/**
 * The AddNewWordScreen class extends JPanel and represents the screen to add new word in
 * hangman game. It contains all the necessary UI components and functions to add new word.
 * 
 * @author 
 */
public class AddNewWordScreen extends JPanel {
	/** The AppScreenNavigator instance that is used to navigate to different screens. */
	private AppScreenNavigator navigator;
	/** The HangmanDataSource instance that is used to add new word to the file. */
	private HangmanDataSource datasource;
	//create the panel to show the words
	private JPanel viewWords;
	//create the defaultlist and the list to display the words in the document
	private DefaultListModel<String> listModel;
	private JList<String> listOfWords;

	//create the Panel to insert the Label, textfield and button
	private JPanel addWord;
	//create the label to display the info "Type the word to add:"
	private JLabel wordLabel;
	//create the textfield to insert the word
	private JTextField wordField;
	//create the button to add the word
	private JButton addButton;
	//create the button to go back to menu
	private JButton backButton;

	
	public AddNewWordScreen(AppScreenNavigator navigator, HangmanDataSource datasource) {
		this.navigator = navigator;
		this.datasource = datasource;
		this.initUI();
	}

	private void initUI() {
		//this.setBackground and this.setLayout is to set the background color and the layout to the panel
		this.setBackground(Color.WHITE);
		this.setLayout(new BorderLayout(0, 0));
		
		//Method with the back button for the user to go back to main menu
		BackButton();
		
		//method with the view words (list)
		ViewWords();
		
		//method with the add words (label, textfield and add button)
		AddWords();
		
		//method to create the layout: back button in north, viewwords in center and add words in south
		CreateLayout();
		
	}
	
	//method in details
	public void ViewWords() {
		//create a JPanel
		viewWords = new JPanel();
		//set the layout
		viewWords.setLayout(new BorderLayout());
		//set the background color
		viewWords.setBackground(Color.WHITE);
		
		//list model to read the words in the document
	    listModel = new DefaultListModel<>();
	    for(String word: datasource.getWordList()) {
	    	listModel.addElement(word);
	    }
		
	    //listofWords created to show the list
		listOfWords = new JList<>(listModel);
		listOfWords.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listOfWords.setVisibleRowCount(5);
		//Create a scroll pane no scroll the list, since the max height is defined. 
		JScrollPane scrollPane = new JScrollPane(listOfWords);	
		scrollPane.setPreferredSize(new Dimension(250, 80));
		//insert the scroll pane inside the viewwords pane
		viewWords.add(scrollPane , BorderLayout.CENTER);
		
	}
	
	//method to add words
	public void AddWords() {
		//create a panel (flow layout)
		addWord = new JPanel();
		//create a label
		wordLabel = new JLabel("Type the word to add: ");
		//create a text field lenght of 20
		wordField = new JTextField(20);
		//create a add buton
		addButton = new JButton("Add word");
		//create an anonymous class to determine the ActionListener
		addButton.addActionListener(new ActionListener() {
			  public void actionPerformed(ActionEvent ae) {
			    String word = wordField.getText().trim();
			    try {
			    	datasource.addNewWord(word);					
			    	listModel.addElement(word.toUpperCase());
				} catch (Exception e) {
			        JOptionPane.showMessageDialog(addWord, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			  }
			});
		
		//add Label, textfield and button inside the Panel
		addWord.add(wordLabel);
		addWord.add(wordField);
		addWord.add(addButton);
		
	}
	
	//method to create a back button	
	public void BackButton() {
		//create a back buton
		backButton = new JButton("Go back to main menu");
		//add an anonymous class
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				navigator.openStartScreen();
			}
		});
	}
	
	//method to create the layout: Add the back button in north, viewwords in center and addword in south
	public void CreateLayout() {
		add(backButton, BorderLayout.NORTH);
		add(viewWords, BorderLayout.CENTER);
		add(addWord, BorderLayout.SOUTH);
	}
	
}
