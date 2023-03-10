package view;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class StaffInfoView extends JFrame {
	/**
	 * Private constructor to create an instance of the StaffInfoView class
	 */
	private StaffInfoView() {
		// call the initUI() method to create the user interface.
		this.initUI();
	}

	/**
	 * Private method to create the user interface for the application.
	 */
	private void initUI() {
		// add main panel in the frame
		this.addMainPanel();
		// set title of the frame
		this.setTitle("Staff Information");
		// set background color of the content pane
		this.getContentPane().setBackground(Color.WHITE);
		// exit out of application on close
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// set the size of the frame
		this.setSize(350, 400);
		// prevent frame from being resized
		this.setResizable(false);
		// place the window in center of screen
		this.setLocationRelativeTo(null);
	}

	/**
	 * Private method to add the main panel to the window. Adds the text fields and
	 * buttons to the panel and sets their properties.
	 */
	private void addMainPanel() {
		// Create main panel.
		JPanel panel = new JPanel();
		// Set the background of the panel to white.
		panel.setBackground(Color.WHITE);

		// Add the main panel to the frame.
		this.add(panel, BorderLayout.NORTH);
	}

	/**
	 * Public static method to open the StaffInfoView window and display the user
	 * interface.
	 */
	public static void open() {
		// Create a new instance of the StaffInfoView.
		StaffInfoView view = new StaffInfoView();
		// make frame visible
		view.setVisible(true);
	}
}
