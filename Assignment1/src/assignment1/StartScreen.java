package assignment1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class StartScreen extends JPanel {
	private AppScreenNavigator navigator;

	StartScreen(AppScreenNavigator navigator) {
		this.navigator = navigator;
		this.init();
	}

	private void init() {
		this.setBackground(Color.WHITE);
		this.setLayout(new BorderLayout(0, 0));
		this.addAppLogo();
		this.addButtons();
	}

	private void addAppLogo() {
		ImageIcon icon = new ImageIcon(AppConstants.APP_ICON_PATH);
		JLabel appLogo = new JLabel(icon, JLabel.CENTER);
		this.add(appLogo, BorderLayout.CENTER);
	}

	private void addButtons() {
		JPanel buttonPanel = new JPanel();
		buttonPanel.setPreferredSize(new Dimension(AppConstants.APP_FRAME_WIDTH, AppConstants.APP_FRAME_HEIGHT / 4));
		buttonPanel.setLayout(new GridLayout(3, 1, 0, 0));
		
		JButton btnStartPlaying = new JButton("Start Playing");
		btnStartPlaying.addActionListener(e -> navigator.openGameScreen());
		
		JButton btnAddNewText = new JButton("Add New Text");
		btnAddNewText.addActionListener(e -> navigator.openAddNewWordScreen());
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(e -> navigator.exitGame());
		
		buttonPanel.add(btnStartPlaying);
		buttonPanel.add(btnAddNewText);
		buttonPanel.add(btnExit);
		
		this.add(buttonPanel, BorderLayout.SOUTH);
	}
}
