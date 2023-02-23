package assignment3;

import presentaion.PersonSimulator;

/**
 * 
 * The Application class is the entry point to the application.
 * 
 * @author SUSANT_SHRESTHA_N01550307
 */
public class Application {

	public static void main(String[] args) {
		// Open the person simulator passing the path of the data source file
		PersonSimulator.open("datasource/person.dat");
	}
}
