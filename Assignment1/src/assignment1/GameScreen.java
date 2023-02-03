package assignment1;

import java.awt.Color;

import javax.swing.JPanel;

public class GameScreen extends JPanel {
	private AppScreenNavigator navigator;

	public GameScreen(AppScreenNavigator navigator) {
		this.navigator = navigator;
		this.init();
	}

	private void init() {
		this.setBackground(Color.green);
	}
}
