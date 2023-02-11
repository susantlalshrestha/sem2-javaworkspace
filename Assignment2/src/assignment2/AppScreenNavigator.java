package assignment2;

/**
 * AppScreenNavigator interface defines the contract for navigating between
 * different screens in the game application.
 * 
 * @author Susant Shrestha
 */
public interface AppScreenNavigator {
	/**
	 * Open add new word screen for the game.
	 */
	void openAddUpdateScreen();

	/**
	 * Open game screen for the game.
	 */
	void openFindDisplayScreen();

	/**
	 * Exit from the game.
	 */
	void exitApplication();
}
