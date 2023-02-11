package assignment2;

public class Application {

	public static void main(String[] args) {

		ProductDataSource datasource = new ProductDataSource();
		MainScreen.open(datasource);
	}
}
