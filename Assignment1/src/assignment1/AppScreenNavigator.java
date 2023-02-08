package assignment1;

/**
 * AppScreenNavigator interface defines the contract for navigating between
 * different screens in the game application.
 * 
 * @author Susant Shrestha
 */
public interface AppScreenNavigator {
	/**
	 * Open start screen of the game.
	 */
	void openStartScreen();

	/**
	 * Open add new word screen for the game.
	 */
	void openAddNewWordScreen();

	/**
	 * Open game screen for the game.
	 */
	void openGameScreen();

	/**
	 * Exit from the game.
	 */
	void exitGame();
}
