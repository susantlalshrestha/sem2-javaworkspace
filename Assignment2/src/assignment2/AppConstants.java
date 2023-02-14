package assignment2;

/**
 * 
 * This class defines constants that are used throughout the application.
 */
public class AppConstants {
	/**
	 * Specifies the path to the product data source file.
	 */
	public static final String DATASOURCE_PATH = "assets/datasource/products.dat";
	/**
	 * Specifies the length of the product ID.
	 */
	public static final int PRODUCT_ID_LENGTH = 4;
	/**
	 * Specifies the maximum length of the product name.
	 */
	public static final int PRODUCT_NAME_MAX_LENGTH = 32;
	/**
	 * Specifies the maximum length of the product description.
	 */
	public static final int PRODUCT_DESC_MAX_LENGTH = 64;
	/**
	 * Specifies the total number of bytes required to store a single product in the
	 * data source file.
	 */
	public static final int PRODUCT_TOTAL_BYTES = AppConstants.PRODUCT_ID_LENGTH + AppConstants.PRODUCT_NAME_MAX_LENGTH
			+ AppConstants.PRODUCT_DESC_MAX_LENGTH + Integer.BYTES + Double.BYTES;
	/**
	 * The width of the game application frame.
	 */
	public static final int APP_FRAME_WIDTH = 500;

	/**
	 * The height of the game application frame.
	 */
	public static final int APP_FRAME_HEIGHT = 500;

}
