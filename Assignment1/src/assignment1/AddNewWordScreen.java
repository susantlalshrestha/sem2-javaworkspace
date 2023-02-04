package assignment1;

import java.awt.Color;

import javax.swing.JPanel;

public class AddNewWordScreen extends JPanel {
	private AppScreenNavigator navigator;
	private HangmanDataSource datasource;

	public AddNewWordScreen(AppScreenNavigator navigator, HangmanDataSource datasource) {
		this.navigator = navigator;
		this.datasource = datasource;
		this.initUI();
	}

	private void initUI() {
		this.setBackground(Color.WHITE);
		// TODO: Create GUI list all the words in JList and take input from user in JTextField and when pressed add button it should this function
		// this.datasource.addNewWord("asdfhl");
	}
}
