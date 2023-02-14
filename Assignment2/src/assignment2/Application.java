package assignment2;

/**
 * 
 * This class serves as the entry point of the application.
 * 
 * @author Susant Shrestha
 */
public class Application {

	public static void main(String[] args) {
		// Create a new instance of the ProductDataSource class
		ProductDataSource datasource = new ProductDataSource();
		// Open the main screen of the application and pass the data source as a
		// parameter
		MainScreen.open(datasource);
	}
}
