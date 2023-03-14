package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import controller.StaffController;

public class StaffInfoView extends JFrame {
	private StaffController controller;
	private JPanel pnl_staffInfo;
	private JLabel lbl_staffID;
	private JTextField txt_staffID;
	private JLabel lbl_lName;
	private JTextField txt_lName;
	private JLabel lbl_fName;
	private JTextField txt_fName;
	private JLabel lbl_mi;
	private JTextField txt_mi;
	private JLabel lbl_address;
	private JTextField txt_address;
	private JLabel lbl_city;
	private JTextField txt_city;
	private JLabel lbl_state;
	private JTextField txt_state;
	private JLabel lbl_phone;
	private JTextField txt_phone;
	
	private JPanel pnl_buttons;
	private JButton btn_view;
	private JButton btn_insert;
	private JButton btn_update;
	private JButton btn_clear;
	
	private JLabel lbl_message;
	

	/**
	 * Private constructor to create an instance of the StaffInfoView class
	 */
	private StaffInfoView(StaffController controller) {
		this.controller = controller;
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
		
		CreateStaffInformation();
		
		CreateButtons();
		
		CreateLayout();
	}

	/**
	 * Public static method to open the StaffInfoView window and display the user
	 * interface.
	 */
	public static void open() {
		// Create a new instance of the StaffInfoView.
		StaffInfoView view = new StaffInfoView(new StaffController());
		// make frame visible
		view.setVisible(true);
	}
	
	public void CreateStaffInformation() {
		JPanel pnl_staffInfo = new JPanel();
	    pnl_staffInfo.setLayout(new GridLayout(8, 2, 10, 10));
	    pnl_staffInfo.setBorder(new TitledBorder(new EtchedBorder(), "Staff Information"));

	    JLabel lbl_staffID = new JLabel("Staff ID:");
	    JTextField txt_staffID = new JTextField(20);

	    JLabel lbl_lName = new JLabel("Last Name:");
	    JTextField txt_lName = new JTextField(20);

	    JLabel lbl_fName = new JLabel("First Name:");
	    JTextField txt_fName = new JTextField(20);

	    JLabel lbl_mi = new JLabel("M.I.:");
	    JTextField txt_mi = new JTextField(5);

	    JLabel lbl_address = new JLabel("Address:");
	    JTextField txt_address = new JTextField(30);

	    JLabel lbl_city = new JLabel("City:");
	    JTextField txt_city = new JTextField(15);

	    JLabel lbl_state = new JLabel("State:");
	    JTextField txt_state = new JTextField(2);

	    JLabel lbl_phone = new JLabel("Phone:");
	    JTextField txt_phone = new JTextField(12);

	    pnl_staffInfo.add(lbl_staffID);
	    pnl_staffInfo.add(txt_staffID);
	    pnl_staffInfo.add(lbl_lName);
	    pnl_staffInfo.add(txt_lName);
	    pnl_staffInfo.add(lbl_fName);
	    pnl_staffInfo.add(txt_fName);
	    pnl_staffInfo.add(lbl_mi);
	    pnl_staffInfo.add(txt_mi);
	    pnl_staffInfo.add(lbl_address);
	    pnl_staffInfo.add(txt_address);
	    pnl_staffInfo.add(lbl_city);
	    pnl_staffInfo.add(txt_city);
	    pnl_staffInfo.add(lbl_state);
	    pnl_staffInfo.add(txt_state);
	    pnl_staffInfo.add(lbl_phone);
	    pnl_staffInfo.add(txt_phone);
	    
	    this.pnl_staffInfo = pnl_staffInfo;
		
	}
	
	public void CreateButtons() {
		JPanel pnl_buttons = new JPanel();
	    pnl_buttons.setLayout(new GridLayout(1, 4, 10, 10));
		JButton btn_view = new JButton("View");
		JButton btn_insert = new JButton ("Insert");
		JButton btn_update = new JButton ("Update");
		JButton btn_clear = new JButton("Clear");
		
	    pnl_buttons.add(btn_view);
	    pnl_buttons.add(btn_insert);
	    pnl_buttons.add(btn_update);
	    pnl_buttons.add(btn_clear);
		
	}
	
	public void CreateLayout() {		
		add(pnl_staffInfo, BorderLayout.NORTH);
		add(pnl_buttons, BorderLayout.CENTER);
		add(lbl_message, BorderLayout.SOUTH);
	}
}
