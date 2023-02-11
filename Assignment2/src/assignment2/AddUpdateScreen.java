package assignment2;

import javax.swing.JFrame;

public class AddUpdateScreen extends JFrame {
	private ProductDataSource dataSource;

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
	}
}
