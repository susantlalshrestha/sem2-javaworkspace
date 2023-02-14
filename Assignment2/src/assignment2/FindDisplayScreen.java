package assignment2;

//Import necessary libraries and packages
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

//Create the FindDisplayScreen class which extends JFrame class
public class FindDisplayScreen extends JFrame {
	
	// Initialize necessary instance variables
	private ProductDataSource dataSource;
	private JRadioButton allRadioButton;
	private JRadioButton keywordRadioButton;
	private JRadioButton priceRangeRadioButton;
	private JTextField keywordTextField;
	private JTextField fromPriceTextField;
	private JTextField toPriceTextField;
	private JButton searchButton;
	private JTable productTable;
	
	// Create a constructor for the FindDisplayScreen class
	public FindDisplayScreen(ProductDataSource dataSource) {
		// Set the ProductDataSource instance variable equal to the provided dataSource parameter
		this.dataSource = dataSource;
		// Call the initUI method to initialize the user interface
		this.initUI();
	}
	// Create the initUI method which initializes the user interface
	private void initUI() {
		// Set the title of the window
		this.setTitle("Find/Display Products");
		// Set what happens when the close button is clicked
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		// Set the size of the window
		this.setSize(AppConstants.APP_FRAME_WIDTH, AppConstants.APP_FRAME_HEIGHT);
		// Set the window to not be resizable
		this.setResizable(false);
		// Set the location of the window
		this.setLocation(200, 200);
		// Create radio buttons for selecting what type of search to perform
		allRadioButton = new JRadioButton("All");
		keywordRadioButton = new JRadioButton("Keyword");
		priceRangeRadioButton = new JRadioButton("Price Range");
		// Create a button group for the radio buttons
		ButtonGroup radioButtonGroup = new ButtonGroup();
		radioButtonGroup.add(priceRangeRadioButton);
		radioButtonGroup.add(keywordRadioButton);
		radioButtonGroup.add(allRadioButton);
		// Create text fields for inputting the search parameters
		toPriceTextField = new JTextField(8);
		toPriceTextField.setText("to");
		fromPriceTextField = new JTextField(8);
		fromPriceTextField.setText("from");
		keywordTextField = new JTextField(8);
		keywordTextField.setText("keyword");
		// Create a button for initiating the search
		searchButton = new JButton("Find/Display");
		// Add an action listener to the search button
		searchButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				List<Product> products = null;
				try {
					// Check which radio button is selected and perform the appropriate search
					if (allRadioButton.isSelected()) {
						products = dataSource.getAllProducts();
					} else if (keywordRadioButton.isSelected()) {
						String keyword = keywordTextField.getText();
						products = dataSource.findProductsByKeyword(keyword);
					} else if (priceRangeRadioButton.isSelected()) {
						double fromPrice = Double.parseDouble(fromPriceTextField.getText());
						double toPrice = Double.parseDouble(toPriceTextField.getText());
						products = dataSource.findProductsByPriceRange(fromPrice, toPrice);
					}
					// If products were found and not null, create a table to display them
					if (products != null && products.size() > 0) {
						// Create a 2-dimensional object array to store product data
						Object[][] data = new Object[products.size()][3];
						// Loop through each product in the list and add its data to the object array
						for (int i = 0; i < products.size(); i++) {
							data[i][0] = products.get(i).getId();
							data[i][1] = products.get(i).getName();
							data[i][2] = products.get(i).getUnitPrice();
						}
						// Create an array to store the column names for the table
						String[] columnNames = { "Product ID", "Product Name", "Price" };
						// Create a table model using the data and column names
						TableModel tableModel = new DefaultTableModel(data, columnNames);
						// Set the table model for the product table
						productTable.setModel(tableModel);
					} else {
						// Display a message if no products were found
						JOptionPane.showMessageDialog(FindDisplayScreen.this, "No products found", "Message",
								JOptionPane.INFORMATION_MESSAGE);
					}
					// Catch any exceptions that occurred during the product search and display an error message
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(FindDisplayScreen.this,
							"Error in searching product: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		// Create a new JTable for displaying the product search results
		productTable = new JTable();
		// Disable editing of the table
		productTable.setEnabled(false);
		// Create a scroll pane to hold the product table
		JScrollPane scrollPane = new JScrollPane(productTable);
		// Create three panels for the user input fields
		JPanel panel1 = new JPanel();
		panel1.setLayout(new FlowLayout(FlowLayout.LEFT));
		panel1.add(priceRangeRadioButton);
		panel1.add(fromPriceTextField);
		panel1.add(toPriceTextField);
		panel1.add(searchButton);

		JPanel panel2 = new JPanel();
		panel2.setLayout(new FlowLayout(FlowLayout.LEFT));
		panel2.add(keywordRadioButton);
		panel2.add(keywordTextField);

		JPanel panel3 = new JPanel();
		panel3.setLayout(new FlowLayout(FlowLayout.LEFT));
		panel3.add(allRadioButton);
		// Set the layout of the main content pane to a vertical BoxLayout
		this.getContentPane().setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
		this.getContentPane().add(panel1);
		this.getContentPane().add(panel2);
		this.getContentPane().add(panel3);
		this.getContentPane().add(scrollPane);
		// Make the GUI visible to the user
		this.setVisible(true);
	}
}
