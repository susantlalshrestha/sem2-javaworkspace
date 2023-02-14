package assignment2;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * The AddUpdateScreen class is the frame where the user can add, update or view
 * a product. The user can view the products using the First, Last, Previous and
 * Next Buttons The user can add using the add button and update using the
 * update button It extends JFrame.
 * 
 * @author Leon Czarlinski
 */

public class AddUpdateScreen extends JFrame {
	private ProductDataSource dataSource;
	// Create a panel to have label and text field for product ID
	private JPanel panelAddProductID;
	// Label for product ID
	private JLabel labelProductID;
	// Text field for product ID;
	private JTextField txtFieldProductID;

	// Create panel to have label and text field for Product name
	private JPanel panelAddProductName;
	// Label for product name
	private JLabel labelProductName;
	// text field for product name
	private JTextField txtFieldProductName;

	// Create a panel to display description, quantity and unit price
	private JPanel panelCenter;

	// Create a panel for product description
	private JPanel panelProductDesc;
	// create a label for product description
	private JLabel labelProductDesc;
	// create a text area for the product description
	private JTextArea textAreaDesc;

	// Create a panel for grid layout of quantity and unit price
	private JPanel panelQtdyPrice;
	// label for quantity
	private JLabel labelQtdy;
	// text field for quantity
	private JTextField txtFieldQtdy;
	// label for unit price
	private JLabel labelPrice;
	// text field for unit price
	private JTextField txtFieldPrice;

	// Create a panel for the add and update buttons
	private JPanel panelAddUpdate;
	// Button add
	private JButton addBtn;
	// Button update
	private JButton updateBtn;

	// Create a panel for the first, previous, next and last navigation
	private JPanel panelFirstLast;
	// button for first position
	private JButton firstBtn;
	// button for previous
	private JButton previousBtn;
	// button for next
	private JButton nextBtn;
	// button for last position
	private JButton lastBtn;

	// variable to determine the current position to navigate
	private int currentPosition = 0;
	private boolean reversed = false;

	public AddUpdateScreen(ProductDataSource dataSource) {
		this.dataSource = dataSource;
		this.initUI();
	}

	private void initUI() {
		// set title of the frame
		this.setTitle("Add / Update Product");
		// dispose only this frame on close
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		// set the size of the frame
		this.setSize(AppConstants.APP_FRAME_WIDTH, AppConstants.APP_FRAME_HEIGHT);
		// prevent frame from being resized
		this.setResizable(false);
		// set the location of the frame on the screen
		this.setLocation(150, 150);
		// make frame visible
		this.setVisible(true);
		// method to layout of product ID
		CreateProductID();
		// method to layout of product name
		CreateProductName();
		// method to layout of product description
		CreateDescription();
		// method to layout of product price and quantity
		CreatePriceQtdy();
		// method to layout of product description with price and quantity
		CreatePanelCenter();
		// method to layout the buttons to add and update
		CreateAddUpdate();
		// method to layout the buttons to first, last, next and previous
		CreateFirstLast();
		// method to layout all elements together
		CreateLayout();
	}

	// method to layout of product ID
	public void CreateProductID() {
		panelAddProductID = new JPanel();
		// setting the layout to FlowLayoyt
		panelAddProductID.setLayout(new FlowLayout());

		// adding the writing for the label
		labelProductID = new JLabel("ProductID");
		// setting the size for the dimension
		labelProductID.setPreferredSize(new Dimension(100, 20));
		// setting the size for the text field
		txtFieldProductID = new JTextField(20);

		// adding the label and text field to the panel
		panelAddProductID.add(labelProductID);
		panelAddProductID.add(txtFieldProductID);
	}

	// method to layout of product name
	public void CreateProductName() {
		panelAddProductName = new JPanel();
		// setting the layout to FlowLayoyt
		panelAddProductName.setLayout(new FlowLayout());

		// adding the writing for the label
		labelProductName = new JLabel("Name");
		// setting the size for the dimension
		labelProductName.setPreferredSize(new Dimension(100, 20));
		// setting the size for the text field
		txtFieldProductName = new JTextField(20);

		// adding the label and text field to the panel
		panelAddProductName.add(labelProductName);
		panelAddProductName.add(txtFieldProductName);
	}

	// method to layout of product description
	public void CreateDescription() {
		panelProductDesc = new JPanel();
		// setting the layout to BorderLayout
		panelProductDesc.setLayout(new BorderLayout());

		// adding the writing for the label
		labelProductDesc = new JLabel("Description");
		// setting the size for the dimension
		labelProductDesc.setPreferredSize(new Dimension(100, 20));
		// setting the position for the label to stay in the top of the panel
		labelProductDesc.setVerticalAlignment(JLabel.TOP);
		// setting the size for the text area
		textAreaDesc = new JTextArea(5, 10);

		// adding the label and text area to the panel
		panelProductDesc.add(labelProductDesc, BorderLayout.WEST);
		panelProductDesc.add(textAreaDesc, BorderLayout.CENTER);
	}

	// method to layout of product price and quantity
	public void CreatePriceQtdy() {
		panelQtdyPrice = new JPanel();
		// setting the layout to grid layout. 2 rows and 2 columns with a 40 px space
		// between them
		panelQtdyPrice.setLayout(new GridLayout(2, 2, 0, 40));
		// creating a border to have some space between elements
		panelQtdyPrice.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

		// adding the writing for the label
		labelQtdy = new JLabel("Quantity in hand");
		// setting the size for the dimension
		labelQtdy.setPreferredSize(new Dimension(25, 20));
		// setting the size for the text field
		txtFieldQtdy = new JTextField(10);

		// adding the writing for the label
		labelPrice = new JLabel("Unit Price");
		// setting the size for the dimension
		labelPrice.setPreferredSize(new Dimension(25, 20));
		// setting the size for the text field
		txtFieldPrice = new JTextField(10);

		// adding the elements to the layout
		panelQtdyPrice.add(labelQtdy);
		panelQtdyPrice.add(txtFieldQtdy);
		panelQtdyPrice.add(labelPrice);
		panelQtdyPrice.add(txtFieldPrice);

	}

	// method to layout of product description with price and quantity
	public void CreatePanelCenter() {
		panelCenter = new JPanel();
		// setting to a flowLayout
		panelCenter.setLayout(new FlowLayout());
		// adding the elements to the panel
		panelCenter.add(panelProductDesc);
		panelCenter.add(panelQtdyPrice);

	}

	// method to determine the size of the buttons.
	// since they have the same size, it would make it easier
	public void setButtonSize(JButton button) {
		Dimension size = button.getPreferredSize();
		size.width = 100; // determine the width
		size.height = 30; // determine the height
		button.setPreferredSize(size);
	}

	// method to layout the buttons to add and update
	public void CreateAddUpdate() {
		panelAddUpdate = new JPanel();
		// setting the layout to flow layout
		panelAddUpdate.setLayout(new FlowLayout());

		// adding the button Add
		addBtn = new JButton("Add");
		// adding the ActionListener as a anonymous class
		addBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				// create local variables to get the text from text field
				String id = txtFieldProductID.getText().trim(); // product ID
				String name = txtFieldProductName.getText().trim(); // product name
				String description = textAreaDesc.getText().trim(); // product description
				int quantity = Integer.parseInt(txtFieldQtdy.getText().trim()); // quantity
				double price = Double.parseDouble(txtFieldPrice.getText().trim()); // unit price
				// start try and catch to display correct messages to user
				try {
					// create a object of the class
					Product newProduct = new Product(id, name, description, quantity, price);
					// call the method to add new product
					int addedPosition = dataSource.addProduct(newProduct);
					// display a message of success
					int choice = JOptionPane.showOptionDialog(null,
							"Product added with success!! Do you add more products?", "Success",
							JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, 0);
					if (choice == 0) {
						// clear the text field to blank
						txtFieldProductID.setText("");
						txtFieldProductName.setText("");
						textAreaDesc.setText("");
						txtFieldQtdy.setText("");
						txtFieldPrice.setText("");
						// request focus for the txtFieldProductID
						txtFieldProductID.requestFocus();
					} else {
						// update the current position
						currentPosition = addedPosition;
						// set the reversed to false
						reversed = false;
					}
				} catch (Exception e) {
					// throw an error message in case something goes wrong.
					JOptionPane.showMessageDialog(panelAddUpdate, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		// adding the button Update
		updateBtn = new JButton("Update");
		// adding the ActionListener as a anonymous class
		updateBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				// create local variables to get the text from text field
				String id = txtFieldProductID.getText().trim(); // product ID
				String name = txtFieldProductName.getText().trim(); // product name
				String description = textAreaDesc.getText().trim(); // product description
				int quantity = Integer.parseInt(txtFieldQtdy.getText().trim()); // quantity
				double price = Double.parseDouble(txtFieldPrice.getText().trim()); // unit price
				// start try and catch to display correct messages to user
				try {
					// create a object of the class
					Product newProduct = new Product(id, name, description, quantity, price);
					// call the method to validate
					int updatedPosition = dataSource.updateProduct(newProduct);
					// display a message of success
					JOptionPane.showMessageDialog(panelAddUpdate, "Product updated with success", "Success",
							JOptionPane.INFORMATION_MESSAGE);
					// update the current position
					currentPosition = updatedPosition;
					// set the reversed to false
					reversed = false;
				} catch (Exception e) {
					// throw an error message in case something goes wrong.
					JOptionPane.showMessageDialog(panelAddUpdate, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		// add the two buttons to the panel
		panelAddUpdate.add(addBtn);
		panelAddUpdate.add(updateBtn);

		// set the size for the buttons
		setButtonSize(addBtn);
		setButtonSize(updateBtn);
	}

	// method to layout the buttons to first, last, next and previous
	public void CreateFirstLast() {
		panelFirstLast = new JPanel();
		// setting the layout to flow layout
		panelFirstLast.setLayout(new FlowLayout());

		// create the First button of the navigation
		firstBtn = new JButton("First");
		// add the actionListener as a anonymous class
		firstBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				try {
					Product product = dataSource.getProduct(0, false);
					// if product is not null, it will show the product info
					if (product != null) {
						// set the text in the text field
						txtFieldProductID.setText(product.getId());
						txtFieldProductName.setText(product.getName());
						textAreaDesc.setText(product.getDescription());
						txtFieldQtdy.setText(String.valueOf(product.getQuantity()));
						txtFieldPrice.setText(String.valueOf(product.getUnitPrice()));
						// determine the current position
						currentPosition = 0;
						// set the reversed to false
						reversed = false;
					} else {
						// display error message
						JOptionPane.showMessageDialog(panelFirstLast, "No product found at position " + currentPosition,
								"Error", JOptionPane.ERROR_MESSAGE);
					}
				} catch (Exception e) {
					// display error message from method
					JOptionPane.showMessageDialog(panelFirstLast, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		// create the Previous button
		previousBtn = new JButton("Previous");
		// add the Action Listener as a anonymous class
		previousBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				try {
					Product product = null;
					if (!reversed) {
						product = dataSource.getProduct(currentPosition - 1, reversed);
					} else {
						product = dataSource.getProduct(currentPosition + 1, reversed);
					}
					// if product is not null, it will show the product info
					if (product != null) {
						// set the text in the text field
						txtFieldProductID.setText(product.getId());
						txtFieldProductName.setText(product.getName());
						textAreaDesc.setText(product.getDescription());
						txtFieldQtdy.setText(String.valueOf(product.getQuantity()));
						txtFieldPrice.setText(String.valueOf(product.getUnitPrice()));
						// adjust the current position
						if (!reversed) {
							currentPosition = currentPosition - 1;
						} else {
							currentPosition = currentPosition + 1;
						}
					} else {
						// display error message
						JOptionPane.showMessageDialog(panelFirstLast, "No more products to display.", "Error",
								JOptionPane.INFORMATION_MESSAGE);
					}
					// display error message
				} catch (Exception e) {
					JOptionPane.showMessageDialog(panelFirstLast, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		// create the Next button
		nextBtn = new JButton("Next");
		// add the Action Listener as a anonymous class
		nextBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				try {
					Product product;
					if (!reversed) {
						product = dataSource.getProduct(currentPosition + 1, reversed);
					} else {
						product = dataSource.getProduct(currentPosition - 1, reversed);
					}
					// if product is not null, it will show the product info
					if (product != null) {
						txtFieldProductID.setText(product.getId());
						txtFieldProductName.setText(product.getName());
						textAreaDesc.setText(product.getDescription());
						txtFieldQtdy.setText(String.valueOf(product.getQuantity()));
						txtFieldPrice.setText(String.valueOf(product.getUnitPrice()));
						// adjust the current position
						if (!reversed) {
							currentPosition = currentPosition + 1;
						} else {
							currentPosition = currentPosition - 1;
						}
					} else {
						// display error message
						JOptionPane.showMessageDialog(panelFirstLast, "No more products to display.", "Error",
								JOptionPane.INFORMATION_MESSAGE);
					}
					// display error message
				} catch (Exception e) {
					JOptionPane.showMessageDialog(panelFirstLast, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		// create the Last button
		lastBtn = new JButton("Last");
		// add the Action Listener as a anonymous class
		lastBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				try {
					// determine the last position by calling the method from dataSource
//					int numProducts = dataSource.getNumProducts();
					Product product = dataSource.getProduct(0, true);
					if (product != null) {
						// if product is not null, it will show the product info
						txtFieldProductID.setText(product.getId());
						txtFieldProductName.setText(product.getName());
						textAreaDesc.setText(product.getDescription());
						txtFieldQtdy.setText(String.valueOf(product.getQuantity()));
						txtFieldPrice.setText(String.valueOf(product.getUnitPrice()));
						// adjust the position
						currentPosition = 0;
						// set the reversed to true
						reversed = true;
					} else {
						// display error message
						JOptionPane.showMessageDialog(panelFirstLast, "No more products available.", "Error",
								JOptionPane.ERROR_MESSAGE);
					}
					// display error message
				} catch (Exception e) {
					JOptionPane.showMessageDialog(panelFirstLast, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		// add buttons to the panel
		panelFirstLast.add(firstBtn);
		panelFirstLast.add(previousBtn);
		panelFirstLast.add(nextBtn);
		panelFirstLast.add(lastBtn);

		// set the size of the buttons calling the methods
		setButtonSize(firstBtn);
		setButtonSize(previousBtn);
		setButtonSize(nextBtn);
		setButtonSize(lastBtn);

	}

	// method to create the layout
	public void CreateLayout() {
		// set to be aligned to the left
		setLayout(new FlowLayout(FlowLayout.LEFT));
		// add elements
		add(panelAddProductID);
		add(panelAddProductName);
		add(panelCenter);
		add(panelAddUpdate);
		add(panelFirstLast);
	}
}
