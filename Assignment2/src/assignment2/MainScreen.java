package assignment2;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * 
 * The MainScreen class represents the main GUI of the product management
 * system. It implements the AppScreenNavigator interface which defines methods
 * for navigating to different screens in the application.
 * 
 * @author Susant Shrestha
 */
public class MainScreen extends JFrame implements AppScreenNavigator {
	private ProductDataSource dataSource; // A data source to interact with the product database/file.
	private AddUpdateScreen addUpdateScreen; // The add/update screen for managing products.
	private FindDisplayScreen findDisplayScreen; // The find/display screen for searching and viewing products.

	/**
	 * Constructs a new MainScreen with the given data source.
	 * 
	 * @param dataSource The data source to interact with the product database.
	 */
	private MainScreen(ProductDataSource dataSource) {
		this.dataSource = dataSource;
		this.initUI();
	}

	/**
	 * Initializes the user interface of the main screen.
	 */
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

	/**
	 * Adds the menu bar to the main screen.
	 * 
	 * Creates a "File" menu with an "Exit" item that exits the application.
	 * 
	 * Creates a "Product" menu with "Add/Update" and "Find/Display" items that open
	 * their respective screens.
	 * 
	 * Adds the created menus to the menu bar and sets it to the frame.
	 */
	private void addMenuBar() {
		JMenuBar menuBar = new JMenuBar();

		// Create a "File" menu with an "Exit" item that exits the application.
		JMenu fileMenu = new JMenu("File");
		JMenuItem exitItem = new JMenuItem("Exit");
		exitItem.addActionListener(e -> this.exitApplication());
		fileMenu.add(exitItem);
		menuBar.add(fileMenu);

		// Create a "Product" menu with "Add/Update" and "Find/Display" items that open
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

	/**
	 * 
	 * Adds the main panel to the main screen with a label displaying the name of
	 * the application.
	 * 
	 * The panel uses a BorderLayout with no gap between components and has a white
	 * background.
	 */
	private void addMainPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout(0, 0)); // no gap between components
		panel.setBackground(Color.WHITE);
		JLabel label = new JLabel("Product Management System");
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setVerticalAlignment(JLabel.CENTER);
		panel.add(label);
		this.add(panel);
	}

	/**
	 * 
	 * Opens the add/update screen for the product management system.
	 * 
	 * If the screen has not been previously opened, it creates a new instance of
	 * AddUpdateScreen using the ProductDataSource provided during initialization of
	 * the MainScreen class.
	 * 
	 * If the screen is already visible, it brings the screen to the front of the
	 * user's display.
	 */
	@Override
	public void openAddUpdateScreen() {
		if (addUpdateScreen == null || !addUpdateScreen.isVisible()) {
			addUpdateScreen = new AddUpdateScreen(this.dataSource);
		} else {
			addUpdateScreen.requestFocus();
		}
	}

	/**
	 * 
	 * Opens the find/display screen for the product management system.
	 * 
	 * If the screen has not been previously opened, it creates a new instance of
	 * FindDisplayScreen using the ProductDataSource provided during initialization
	 * of the MainScreen class.
	 * 
	 * If the screen is already visible, it brings the screen to the front of the
	 * user's display.
	 */
	@Override
	public void openFindDisplayScreen() {
		if (findDisplayScreen == null || !findDisplayScreen.isVisible()) {
			findDisplayScreen = new FindDisplayScreen(this.dataSource);
		} else {
			findDisplayScreen.requestFocus();
		}
	}

	/**
	 * Exit from the game.
	 */
	@Override
	public void exitApplication() {
		// Show a confirmation dialog to ask the user if they really want to exit
		int choice = JOptionPane.showOptionDialog(null, "Are you sure you want to exit the application?", null,
				JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, 0);
		if (choice == 0) {
			System.exit(0);
		}
	}

	/**
	 * The open method creates a new instance of the MainScreen and opens it.
	 */
	public static void open(ProductDataSource dataSource) {
		// Create a new instance of the MainScreen.
		new MainScreen(dataSource);
	}
}
