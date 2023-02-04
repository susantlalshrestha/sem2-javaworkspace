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
	}
}
