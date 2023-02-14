package assignment2;

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
//		this.setSize(800, 500);
		this.setResizable(false);
		this.setLocation(200, 200);

		allRadioButton = new JRadioButton("All");
		keywordRadioButton = new JRadioButton("Keyword");
		priceRangeRadioButton = new JRadioButton("Price Range");

		ButtonGroup radioButtonGroup = new ButtonGroup();
		radioButtonGroup.add(priceRangeRadioButton);
		radioButtonGroup.add(keywordRadioButton);
		radioButtonGroup.add(allRadioButton);

		toPriceTextField = new JTextField(8);
		toPriceTextField.setText("to");
		fromPriceTextField = new JTextField(8);
		fromPriceTextField.setText("from");
		keywordTextField = new JTextField(8);
		keywordTextField.setText("keyword");
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

		productTable = new JTable();
		productTable.setEnabled(false);
		JScrollPane scrollPane = new JScrollPane(productTable);

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

		this.getContentPane().setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
		this.getContentPane().add(panel1);
		this.getContentPane().add(panel2);
		this.getContentPane().add(panel3);
		this.getContentPane().add(scrollPane);
		this.setVisible(true);
	}
}
