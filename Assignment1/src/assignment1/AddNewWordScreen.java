package assignment1;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import javax.swing.*;

public class AddNewWordScreen extends JPanel {
	private AppScreenNavigator navigator;
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
	    try {
	    	// BufferedReader is to read the words in the document. The path is in the class AppConstants
	      BufferedReader reader = new BufferedReader(new FileReader(AppConstants.DATASOURCE_FILE_PATH));
	      String line; //variable to determine each line
	      //while loop to read all lines in the document. While is not null, keeps reading
	      while ((line = reader.readLine()) != null) {
	        listModel.addElement(line);
	      }
	      reader.close();
	      //catch a exception in case there is a problem reading the document. If there is, message will pop up in a JOptionPane
	    } catch (IOException e) {
	      JOptionPane.showMessageDialog(this, "Error reading file", "Error", JOptionPane.ERROR_MESSAGE);
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
		addButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent ae) 
			{
				String word = wordField.getText().trim();
				//enter a if condition to throw an error in case the user does not enters a word
				if(!word.isEmpty()) {
					//transform all words to upper case.
					word = word.toUpperCase();
					//add element word
					listModel.addElement(word);
					try {
						//create a buffered writer to include the word in the file
						BufferedWriter saveWord = new BufferedWriter(new FileWriter(AppConstants.DATASOURCE_FILE_PATH, true));
						saveWord.write(word + System.lineSeparator());
						//close it so save it
						saveWord.close();
						//throws an error in case it doesn't save it
					} catch(IOException e) {
						JOptionPane.showMessageDialog(addWord, "Word not saved in the file", "Error",JOptionPane.ERROR_MESSAGE);
					}
					//clears the field after adding the word
					wordField.setText("");
					//else is to throw a message for the user to enter the word
				} else {
					JOptionPane.showMessageDialog(addWord,  "Enter a word", "Error", JOptionPane.ERROR_MESSAGE);
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
