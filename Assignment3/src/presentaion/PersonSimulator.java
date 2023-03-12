package presentaion;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import business.Person;
import data.RandomIO;

/**
 * 
 * The PersonSimulator class represents a GUI application to manage a list of
 * persons stored in a random-access file. It extends the JFrame class to create
 * a window for the user interface. The class uses the Person class to represent
 * a person object and the RandomIO class to read and write data to the file.
 * The class expects a file path to the person data file as a parameter to the
 * open() method, which is used to create an instance of the PersonSimulator
 * class and display the window.
 * 
 * @author SUSANT_SHRESTHA_N01550307
 */
public class PersonSimulator extends JFrame {
	// Instance variables
	String filePath;

	JTextField txtRecordID;
	JTextField txtFirstName;
	JTextField txtLastName;
	JTextField txtPhone;
	JTextField txtAge;

	/**
	 * 
	 * Private constructor to create an instance of the PersonSimulator class with
	 * the specified file path.
	 * 
	 * @param filePath the path to the person data file
	 */
	private PersonSimulator(String filePath) {
		// Initializes the filePath instance variable
		this.filePath = filePath;
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
		this.setTitle("Person Simulator");
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
		// Set layout of the panel to GridBagLayout.
		panel.setLayout(new GridBagLayout());

		// Create the instance of GridBagConstraints.
		GridBagConstraints gbc = new GridBagConstraints();
		// Set the GridBagConstraints to fill horizontally.
		gbc.fill = GridBagConstraints.HORIZONTAL;
		// Set the GridBagConstraints insets.
		gbc.insets = new Insets(10, 20, 10, 20);

		// Add a label for the "Record ID" field in the top row.
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 0;
		panel.add(new JLabel("Record ID"), gbc);

		// Create a text field for the "Record ID" field and add it to the top row.
		txtRecordID = new JTextField();
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.weightx = 2;
		panel.add(txtRecordID, gbc);

		// Add a label for the "First Name" field in the second row.
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.weightx = 0;
		panel.add(new JLabel("First Name"), gbc);

		// Create a text field for the "First Name" field and add it to the second row.
		txtFirstName = new JTextField();
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.weightx = 2;
		panel.add(txtFirstName, gbc);

		// Add a label for the "Last Name" field in the third row.
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.weightx = 0;
		panel.add(new JLabel("Last Name"), gbc);

		// Create a text field for the "Last Name" field and add it to the third row.
		txtLastName = new JTextField();
		gbc.gridx = 1;
		gbc.gridy = 2;
		gbc.weightx = 2;
		panel.add(txtLastName, gbc);

		// Add a label for the "Phone" field in the fourth row.
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.weightx = 0;
		panel.add(new JLabel("Phone"), gbc);

		// Create a text field for the "Phone" field and add it to the fourth row.
		txtPhone = new JTextField();
		gbc.gridx = 1;
		gbc.gridy = 3;
		gbc.weightx = 2;
		panel.add(txtPhone, gbc);

		// Add a label for the "Age" field in the fifth row.
		gbc.gridx = 0;
		gbc.gridy = 4;
		gbc.weightx = 0;
		panel.add(new JLabel("Age"), gbc);

		// Create a text field for the "Age" field and add it to the fifth row.
		txtAge = new JTextField();
		gbc.gridx = 1;
		gbc.gridy = 4;
		gbc.weightx = 2;
		panel.add(txtAge, gbc);

		// Create a panel for the "ADD" and "FIND" buttons.
		JPanel panelBtn = new JPanel();
		// Set the background of the panelBtn to white.
		panelBtn.setBackground(Color.WHITE);
		// Set layout of the panelBtn to GridLayout with 1 row and 2 columns.
		panelBtn.setLayout(new GridLayout(1, 2));

		// Create an "ADD" button and add an action listener to it.
		JButton btnAdd = new JButton("ADD");
		btnAdd.addActionListener(e -> this.addPerson());
		panelBtn.add(btnAdd);

		// Create a "FIND" button and add an action listener to it.
		JButton btnFind = new JButton("FIND");
		btnFind.addActionListener(e -> this.findPerson());
		panelBtn.add(btnFind);

		// Add the "ADD" and "FIND" buttons to the main panel.
		gbc.gridx = 0;
		gbc.gridy = 5;
		gbc.weightx = 0;
		gbc.gridwidth = 2;
		panel.add(panelBtn, gbc);

		// Add the main panel to the frame.
		this.add(panel, BorderLayout.NORTH);
	}

	/**
	 * Private method to add a new person to the data file. Reads the data from the
	 * text fields, validates it, creates a Person object, and writes it to the
	 * file. Displays a success message if the operation is successful or an error
	 * message if there is a problem.
	 */
	private void addPerson() {
		try {
			// Get the record ID from the input field.
			String id = this.txtRecordID.getText();
			// Check if the ID is blank.
			if (id.isBlank()) {
				// Show an error message and focus on the ID input field.
				this.showErrorMessage("Record ID cannot be empty!!");
				this.txtRecordID.requestFocus();
				return;
			}
			// Check if the ID length is not equal to the predefined length.
			if (id.length() != Person.ID_LENGTH) {
				// Show an error message and focus on the ID input field.
				this.showErrorMessage("Record ID must contain excatly " + Person.ID_LENGTH + " characters!!");
				this.txtRecordID.requestFocus();
				return;
			}

			// Get the first name from the input field.
			String firstName = this.txtFirstName.getText();
			// Check if the first name is blank.
			if (firstName.isBlank()) {
				// Show an error message and focus on the first name input field.
				this.showErrorMessage("First name cannot be empty!!");
				this.txtFirstName.requestFocus();
				return;
			}
			// Check if the first name length exceeds the predefined max length.
			if (firstName.length() > Person.FIRST_NAME_MAX_LENGTH) {
				// Show an error message and focus on the first name input field.
				this.showErrorMessage(
						"First name must contain at most " + Person.FIRST_NAME_MAX_LENGTH + " characters!!");
				this.txtFirstName.requestFocus();
				return;
			}

			// Get the last name from the input field.
			String lastName = this.txtLastName.getText();
			// Check if the last name is not blank and its length exceeds the max length.
			if (!lastName.isBlank() && lastName.length() > Person.LAST_NAME_MAX_LENGTH) {
				// Show an error message and focus on the last name input field.
				this.showErrorMessage(
						"Last name must contain at most " + Person.LAST_NAME_MAX_LENGTH + " characters!!");
				this.txtLastName.requestFocus();
				return;
			}

			// Get the phone number from the input field.
			String phone = this.txtPhone.getText();
			// Check if the phone number is not blank and its length exceeds the max length.
			if (!phone.isBlank() && phone.length() > Person.PHONE_MAX_LENGTH) {
				// Show an error message and focus on the phone number input field.
				this.showErrorMessage("Last name must contain at most " + Person.PHONE_MAX_LENGTH + " characters!!");
				this.txtPhone.requestFocus();
				return;
			}

			// Get the age as a string from the input field and convert it to an integer.
			String ageStr = this.txtAge.getText();
			int age = 0;
			// Check if the age is not blank.
			if (!ageStr.isBlank()) {
				// Parse the age string value to integer
				age = Integer.parseInt(ageStr);
			}

			// Create a new instance of the RandomIO class.
			RandomIO io = new RandomIO();
			// Open the file with the given file path.
			io.open(this.filePath);
			// check if a Person record with the same ID already exists in the file
			if (io.findIndexOf(id) != -1) {
				// Show an error message and focus on the ID input field.
				this.showErrorMessage("Person record with same ID already exist!!");
				this.txtRecordID.requestFocus();
			} else {
				// Write the new person to the file.
				io.write(new Person(id, firstName, lastName, phone, age));
				// Show a success message.
				this.showSuccessMessage("Person added Successfully!!");
				// Clear the input fields.
				this.clear();
			}
			// Close the file.
			io.close();
		} catch (NumberFormatException e) {
			// Show a error message and focus on the age input field
			this.showErrorMessage("Age can contain only numbers");
			this.txtAge.requestFocus();
		} catch (IOException e) {
			// Print the stack trace if an IOException occurs.
			e.printStackTrace();
		}
	}

	/**
	 * Private method to find a person in the data file by record ID. Reads the ID
	 * from the text field, searches the file for the person, and displays the
	 * person's data in the text fields if found. Otherwise, clears the text fields
	 * and displays an error message.
	 */
	private void findPerson() {
		try {
			// Get the record ID from the input field.
			String id = this.txtRecordID.getText();
			// Check if the ID is blank.
			if (id.isBlank()) {
				// Show an error message and focus on the ID input field.
				this.showErrorMessage("Record ID cannot be empty!!");
				this.txtRecordID.requestFocus();
				return;
			}
			// Check if the ID length is not equal to the predefined length.
			if (id.length() != Person.ID_LENGTH) {
				// Show an error message and focus on the ID input field.
				this.showErrorMessage("Record ID must contain excatly " + Person.ID_LENGTH + " characters!!");
				this.txtRecordID.requestFocus();
				return;
			}

			// Create a new instance of the RandomIO class.
			RandomIO io = new RandomIO();
			// Open the file with the given file path.
			io.open(this.filePath);
			// Find the person with the given ID in the file.
			Person person = io.find(id);
			// If the person is found, populate the GUI fields with the person's
			// information.
			if (person != null) {
				txtFirstName.setText(person.getFirstName());
				txtLastName.setText(person.getLastName());
				txtPhone.setText(person.getPhone());
				txtAge.setText(String.valueOf(person.getAge()));
			} else {
				// If the person is not found, clear the GUI fields, show an error message, and
				// focus on the ID input field.
				this.clear();
				this.txtRecordID.setText(id);
				this.txtRecordID.requestFocus();
				this.showErrorMessage("Person with record ID " + id + " not found!!");
			}
			// Close the file.
			io.close();
		} catch (IOException e) {
			// Print the stack trace if an IOException occurs.
			e.printStackTrace();
		}
	}

	/**
	 * Private method to clear the text fields of the form.
	 */
	private void clear() {
		txtRecordID.setText("");
		txtFirstName.setText("");
		txtLastName.setText("");
		txtPhone.setText("");
		txtAge.setText("");
	}

	/**
	 * Private method to show a success message in a dialog box.
	 * 
	 * @param message the message to display
	 */
	private void showSuccessMessage(String message) {
		JOptionPane.showMessageDialog(this, message, null, JOptionPane.PLAIN_MESSAGE);
	}

	/**
	 * Private method to show an error message in a dialog box.
	 * 
	 * @param message the message to display
	 */
	private void showErrorMessage(String message) {
		JOptionPane.showMessageDialog(this, message, null, JOptionPane.ERROR_MESSAGE);
	}

	/**
	 * Public static method to open the PersonSimulator window and display the user
	 * interface. Expects a file path to the person data file as a parameter.
	 * 
	 * @param filePath the path to the person data file
	 */
	public static void open(String filePath) {
		// Create a new instance of the PersonSimulator passing the filePath.
		PersonSimulator screen = new PersonSimulator(filePath);
		// make frame visible
		screen.setVisible(true);
	}
}
