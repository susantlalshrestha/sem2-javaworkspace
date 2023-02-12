package assignment2;

import javax.swing.JFrame;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class FindDisplayScreen extends JFrame {
	private ProductDataSource dataSource;
	private JRadioButton allRadioButton;
	private JRadioButton keywordRadioButton;
	private JRadioButton priceRangeRadioButton;
	private JTextField keywordTextField;
	private JTextField fromPriceTextField;
	private JTextField toPriceTextField;
	private JButton searchButton;
	private JTable productTable;
	private JTextArea productTextArea;

	public FindDisplayScreen(ProductDataSource dataSource) {
		this.dataSource = dataSource;
		this.initUI();
	}

	private void initUI() {
		this.setTitle("Find / Display Product");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(AppConstants.APP_FRAME_WIDTH, AppConstants.APP_FRAME_HEIGHT);
		this.setResizable(false);
		this.setLocation(200, 200);

		JPanel radioButtonPanel = new JPanel();
		radioButtonPanel.setLayout(new BoxLayout(radioButtonPanel, BoxLayout.X_AXIS));

		allRadioButton = new JRadioButton("All");
		keywordRadioButton = new JRadioButton("Keyword");
		priceRangeRadioButton = new JRadioButton("Price Range");

		ButtonGroup radioButtonGroup = new ButtonGroup();
		radioButtonGroup.add(allRadioButton);
		radioButtonGroup.add(keywordRadioButton);
		radioButtonGroup.add(priceRangeRadioButton);

		radioButtonPanel.add(allRadioButton);
		radioButtonPanel.add(keywordRadioButton);
		radioButtonPanel.add(priceRangeRadioButton);

		JPanel searchPanel = new JPanel();
		searchPanel.setLayout(new BoxLayout(searchPanel, BoxLayout.X_AXIS));

		keywordTextField = new JTextField();
		fromPriceTextField = new JTextField();
		toPriceTextField = new JTextField();
		searchButton = new JButton("Search");

		searchButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				List<Product> products = null;
				try {
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
						displayProducts(products);
						} catch (Exception ex) {
						JOptionPane.showMessageDialog(FindDisplayScreen.this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
						}
						}
						});
		searchPanel.add(keywordTextField);
		searchPanel.add(fromPriceTextField);
		searchPanel.add(toPriceTextField);
		searchPanel.add(searchButton);

		JPanel displayPanel = new JPanel();
		displayPanel.setLayout(new BoxLayout(displayPanel, BoxLayout.X_AXIS));

		productTextArea = new JTextArea();
		productTextArea.setEditable(false);

		JScrollPane scrollPane = new JScrollPane(productTextArea);

		displayPanel.add(scrollPane);

		Container contentPane = this.getContentPane();
		contentPane.add(radioButtonPanel, BorderLayout.NORTH);
		contentPane.add(searchPanel, BorderLayout.CENTER);
		contentPane.add(displayPanel, BorderLayout.SOUTH);
		
		this.setVisible(true);
	}

	private void displayProducts(List<Product> products) {
		productTextArea.setText("");
		for (Product product : products) {
			productTextArea.append(product.toString() + "\n");
		}
	}
}