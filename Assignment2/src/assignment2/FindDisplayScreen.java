package assignment2;

import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
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

	public FindDisplayScreen(ProductDataSource dataSource) {
		this.dataSource = dataSource;
		this.initUI();
	}

	private void initUI() {
		this.setTitle("Find/Display Products");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(AppConstants.APP_FRAME_WIDTH, AppConstants.APP_FRAME_HEIGHT);
		this.setSize(800, 500);
		this.setResizable(false);
		this.setLocation(200, 200);

		JPanel radioButtonPanel = new JPanel();
		radioButtonPanel.setLayout(new BoxLayout(radioButtonPanel, BoxLayout.Y_AXIS));

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
		searchPanel.setLayout(new GridLayout(1, 4, 10, 0));

		toPriceTextField = new JTextField(3);
		toPriceTextField.setText("to");
		toPriceTextField.setFont(toPriceTextField.getFont().deriveFont(10f));
		fromPriceTextField = new JTextField(3);
		fromPriceTextField.setText("from");
		fromPriceTextField.setFont(fromPriceTextField.getFont().deriveFont(10f));
		keywordTextField = new JTextField(3);
		keywordTextField.setText("keyword");
		keywordTextField.setFont(keywordTextField.getFont().deriveFont(10f));
		searchButton = new JButton("Find/Display");

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
					if (products != null && products.size() > 0) {
						Object[][] data = new Object[products.size()][3];
						for (int i = 0; i < products.size(); i++) {
							data[i][0] = products.get(i).getId();
							data[i][1] = products.get(i).getName();
							data[i][2] = products.get(i).getUnitPrice();
						}
						String[] columnNames = { "Product ID", "Product Name", "Price" };
						TableModel tableModel = new DefaultTableModel(data, columnNames);
						productTable.setModel(tableModel);
					} else {
						JOptionPane.showMessageDialog(FindDisplayScreen.this, "No products found", "Message",
								JOptionPane.INFORMATION_MESSAGE);
					}
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(FindDisplayScreen.this,
							"Error in searching product: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		searchPanel.add(fromPriceTextField);
		searchPanel.add(toPriceTextField);
		searchPanel.add(keywordTextField);
		searchPanel.add(searchButton);

		productTable = new JTable();
		JScrollPane scrollPane = new JScrollPane(productTable);

		this.getContentPane().setLayout(new BorderLayout());
		this.getContentPane().add(radioButtonPanel, BorderLayout.WEST);
		this.getContentPane().add(searchPanel, BorderLayout.NORTH);
		this.getContentPane().add(scrollPane, BorderLayout.CENTER);
		this.setVisible(true);
	}
}
