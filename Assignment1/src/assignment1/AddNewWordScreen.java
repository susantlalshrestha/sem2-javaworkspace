package assignment1;

import java.awt.Color;

import javax.swing.JPanel;

public class AddNewWordScreen extends JPanel {
	private AppScreenNavigator navigator;

	public AddNewWordScreen(AppScreenNavigator navigator) {
		this.navigator = navigator;
		this.init();
	}

	private void init() {
		this.setBackground(Color.red);
	}
}
