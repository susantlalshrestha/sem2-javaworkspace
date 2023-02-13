package assignment2;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class AddUpdateScreen extends JFrame {
	private ProductDataSource dataSource;
	private JPanel panelAddProductID;
	private JLabel labelProductID;
	private JTextField txtFieldProductID;
	
	private JPanel panelAddProductName;
	private JLabel labelProductName;
	private JTextField txtFieldProductName;
	
	private JPanel panelCenter;
	
	private JPanel panelProductDesc;
	private JLabel labelProductDesc;
	private JTextArea textAreaDesc;
	
	private JPanel panelQtdyPrice;
	
	private JLabel labelQtdy;
	private JTextField txtFieldQtdy;
	private JLabel labelPrice;
	private JTextField txtFieldPrice;
	
	private JPanel panelAddUpdate;
	private JButton addBtn;
	private JButton updateBtn;
	
	private JPanel panelFirstLast;
	private JButton firstBtn;
	private JButton previousBtn;
	private JButton nextBtn;
	private JButton lastBtn;

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
		
		CreateProductID();
		
		CreateProductName();
		
		CreateDescription();
		
		CreatePriceQtdy();
		
		CreatePanelCenter();
		
		CreateAddUpdate();
		
		CreateFirstLast();
		
		CreateLayout();
	}
	
	public void CreateProductID() {
		panelAddProductID = new JPanel();
		panelAddProductID.setLayout(new FlowLayout());
		
		labelProductID = new JLabel("ProductID");
		labelProductID.setPreferredSize(new Dimension(100, 20));
		
		txtFieldProductID = new JTextField(20);
		
		panelAddProductID.add(labelProductID);
		panelAddProductID.add(txtFieldProductID);
	}
	
	public void CreateProductName() {
		panelAddProductName = new JPanel();
		panelAddProductName.setLayout(new FlowLayout());
		
		labelProductName = new JLabel("Name");
		labelProductName.setPreferredSize(new Dimension(100, 20));
		txtFieldProductName = new JTextField(20);
		
		panelAddProductName.add(labelProductName);
		panelAddProductName.add(txtFieldProductName);
	}
	
	public void CreateDescription() {
		panelProductDesc = new JPanel();
		panelProductDesc.setLayout(new BorderLayout());

		
		labelProductDesc = new JLabel("Description");
		labelProductDesc.setPreferredSize(new Dimension(100, 20));
		labelProductDesc.setVerticalAlignment(JLabel.TOP);
		
		textAreaDesc = new JTextArea(5,10);
		
		panelProductDesc.add(labelProductDesc, BorderLayout.WEST);
		panelProductDesc.add(textAreaDesc, BorderLayout.CENTER);
	}
	
	public void CreatePriceQtdy() {
		panelQtdyPrice = new JPanel();
		panelQtdyPrice.setLayout(new GridLayout(2,2,0,40));
		panelQtdyPrice.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));

		labelQtdy = new JLabel ("Quantity in hand");
		labelQtdy.setPreferredSize(new Dimension(25, 20));
		txtFieldQtdy = new JTextField (10);
				
		labelPrice = new JLabel ("Unit Price");	
		labelPrice.setPreferredSize(new Dimension(25, 20));
		txtFieldPrice = new JTextField (10);
		
		panelQtdyPrice.add(labelQtdy);
		panelQtdyPrice.add(txtFieldQtdy);
		panelQtdyPrice.add(labelPrice);
		panelQtdyPrice.add(txtFieldPrice);
		
	}
	
	public void CreatePanelCenter() {
		panelCenter = new JPanel();
		panelCenter.setLayout(new FlowLayout());
		panelCenter.add(panelProductDesc);
		panelCenter.add(panelQtdyPrice);
		
	}
	
	public void setButtonSize(JButton button) {
	    Dimension size = button.getPreferredSize();
	    size.width = 100;
	    size.height = 30;
	    button.setPreferredSize(size);
	}
	
	public void CreateAddUpdate() {
		panelAddUpdate = new JPanel();
		panelAddUpdate.setLayout(new FlowLayout());
		
		addBtn = new JButton ("Add");
		updateBtn = new JButton ("Update");
		
		panelAddUpdate.add(addBtn);
		panelAddUpdate.add(updateBtn);
		
	    setButtonSize(addBtn);
	    setButtonSize(updateBtn);
	}
	
	public void CreateFirstLast() {
		panelFirstLast = new JPanel();
		panelFirstLast.setLayout(new FlowLayout());

		
		firstBtn = new JButton("First");
		previousBtn = new JButton("Previous");
		nextBtn = new JButton("Next");
		lastBtn = new JButton("Last");
		
		panelFirstLast.add(firstBtn);
		panelFirstLast.add(previousBtn);
		panelFirstLast.add(nextBtn);
		panelFirstLast.add(lastBtn);
		
	    setButtonSize(firstBtn);
	    setButtonSize(previousBtn);
	    setButtonSize(nextBtn);
	    setButtonSize(lastBtn);
		
	}

	public void CreateLayout() {
		setLayout(new FlowLayout(FlowLayout.LEFT));
		add(panelAddProductID);
		add(panelAddProductName);
		add(panelCenter);
		add(panelAddUpdate);
		add(panelFirstLast);
	}
}

