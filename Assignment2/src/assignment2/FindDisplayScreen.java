package assignment2;

import javax.swing.JFrame;
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
    private JTextArea productTextArea;

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
        searchPanel.setLayout(new BoxLayout(searchPanel, BoxLayout.X_AXIS));

        keywordTextField = new JTextField(7);
        keywordTextField.setText("keyword");
        fromPriceTextField = new JTextField(7);
        fromPriceTextField.setText("from");
        toPriceTextField = new JTextField(7);
        toPriceTextField.setText("to");
        searchButton = new JButton("Find/Display");

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Product> products = null;
                try {
                    if (allRadioButton.isSelected()) {
                        products = dataSource.getAllProducts();
                    } else if (
keywordRadioButton.isSelected()) {
String keyword = keywordTextField.getText();
products = dataSource.findProductsByKeyword(keyword);
} else if (priceRangeRadioButton.isSelected()) {
double fromPrice = Double.parseDouble(fromPriceTextField.
getText());
double toPrice = Double.parseDouble(toPriceTextField.getText());
products = dataSource.findProductsByPriceRange(fromPrice, toPrice);
}
if (products != null && products.size() > 0) {
StringBuilder sb = new StringBuilder();
for (Product product : products) {
sb.append(product.toString());
sb.append("\n");
}
productTextArea.setText(sb.toString());
} else {
productTextArea.setText("No products found");
}
} catch (Exception ex) {
JOptionPane.showMessageDialog(FindDisplayScreen.this,
"Error in searching products: " + ex.getMessage(),
"Error", JOptionPane.ERROR_MESSAGE);
}
}
});

    searchPanel.add(keywordTextField);
    searchPanel.add(fromPriceTextField);
    searchPanel.add(toPriceTextField);
    searchPanel.add(searchButton);

    JPanel productPanel = new JPanel();
    productTextArea = new JTextArea(10, 50);
    productTextArea.setEditable(false);
    JScrollPane scrollPane = new JScrollPane(productTextArea);
    productPanel.add(scrollPane);

    Container container = this.getContentPane();
    container.setLayout(new FlowLayout());
    container.add(radioButtonPanel);
    container.add(searchPanel);
    container.add(productPanel);
    this.setVisible(true);
}
}