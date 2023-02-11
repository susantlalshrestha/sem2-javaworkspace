package assignment2;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MainScreen extends JFrame implements AppScreenNavigator {
	private ProductDataSource dataSource;
	private AddUpdateScreen addUpdateScreen;
	private FindDisplayScreen findDisplayScreen;

	private MainScreen(ProductDataSource dataSource) {
		this.dataSource = dataSource;
		this.initUI();
	}

	private void initUI() {
		// add menu bar in the frame
		this.addMenuBar();
		// add main panel in the frame
		this.addMainPanel();
		// set title of the frame
		this.setTitle("Product Main GUI");
		// exit out of application on close
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// set the size of the frame
		this.setSize(AppConstants.APP_FRAME_WIDTH, AppConstants.APP_FRAME_HEIGHT);
		// prevent frame from being resized
		this.setResizable(false);
		// set the location of the frame on the screen
		this.setLocation(100, 100);
		// make frame visible
		this.setVisible(true);
	}

	private void addMenuBar() {
		JMenuBar menuBar = new JMenuBar();

		JMenu fileMenu = new JMenu("File");
		JMenuItem exitItem = new JMenuItem("Exit");
		exitItem.addActionListener(e -> this.exitApplication());
		fileMenu.add(exitItem);
		menuBar.add(fileMenu);

		JMenu productMenu = new JMenu("Product");
		JMenuItem addUpdateItem = new JMenuItem("Add / Update");
		addUpdateItem.addActionListener(e -> this.openAddUpdateScreen());
		productMenu.add(addUpdateItem);
		JMenuItem findDisplayItem = new JMenuItem("Find / Display");
		findDisplayItem.addActionListener(e -> this.openFindDisplayScreen());
		productMenu.add(findDisplayItem);
		menuBar.add(productMenu);

		this.setJMenuBar(menuBar);
	}

	private void addMainPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout(0, 0));
		panel.setBackground(Color.WHITE);
		JLabel label = new JLabel("Product Management System");
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setVerticalAlignment(JLabel.CENTER);
		panel.add(label);
		this.add(panel);
	}

	@Override
	public void openAddUpdateScreen() {
		if (addUpdateScreen == null || !addUpdateScreen.isVisible()) {
			addUpdateScreen = new AddUpdateScreen(this.dataSource);
		} else {
			addUpdateScreen.requestFocus();
		}
	}

	@Override
	public void openFindDisplayScreen() {
		if (findDisplayScreen == null || !findDisplayScreen.isVisible()) {
			findDisplayScreen = new FindDisplayScreen(this.dataSource);
		} else {
			findDisplayScreen.requestFocus();
		}
	}

	@Override
	public void exitApplication() {
		// Show a confirmation dialog to ask the user if they really want to exit
		int choice = JOptionPane.showOptionDialog(null, "Are you sure you want to exit the application?", null,
				JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, 0);
		if (choice == 0) {
			System.exit(0);
		}

	}

	public static void open(ProductDataSource dataSource) {
		new MainScreen(dataSource);
	}
}
