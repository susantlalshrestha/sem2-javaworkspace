package assignment1;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class HangmanApp extends JFrame implements AppScreenNavigator {
	private JPanel contentPane;
	private HangmanDataSource dataSource;

	private HangmanApp() {
		this.dataSource = new HangmanDataSource();
		this.initUI();
	}

	private void initUI() {
		// create new jpanel and assign it to contentPane
		this.contentPane = new JPanel();
		// set the layout of contentPane to BorderLayout
		this.contentPane.setLayout(new BorderLayout(0, 0));
		// set the contentPane as the content pane of this frame
		this.setContentPane(contentPane);
		// initially open start screen panel
		this.openStartScreen();
		// set title of the frame
		this.setTitle(AppConstants.APP_TITLE);
		// exit out of application on close
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// set the size of the frame
		this.setSize(AppConstants.APP_FRAME_WIDTH, AppConstants.APP_FRAME_HEIGHT);
		// prevent frame from being resized
		this.setResizable(false);
		// place the window in center of screen
		this.setLocationRelativeTo(null);
		// change icon of frame
		this.setIconImage(new ImageIcon(AppConstants.APP_ICON_PATH).getImage());
		// make frame visible
		this.setVisible(true);
	}

	private void changePanel(JPanel panel) {
		contentPane.removeAll();
		contentPane.add(panel, BorderLayout.CENTER);
		contentPane.validate();
		contentPane.repaint();
	}

	@Override
	public void openStartScreen() {
		changePanel(new StartScreen(this));
	}

	@Override
	public void openAddNewWordScreen() {
		changePanel(new AddNewWordScreen(this, dataSource));
	}

	@Override
	public void openGameScreen() {
		changePanel(new GameScreen(this, dataSource));
	}

	@Override
	public void exitGame() {
		int choice = JOptionPane.showOptionDialog(null, "Are you sure you want to exit the game?", null,
				JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, 0);
		if (choice == 0) {
			System.exit(0);
		}
	}

	public static void open() {
		new HangmanApp();
	}
}
