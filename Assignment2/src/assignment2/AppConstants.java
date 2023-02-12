package assignment2;

public class AppConstants {
	public static final String DATASOURCE_PATH = "assets/datasource/products.dat";
	public static final int PRODUCT_ID_LENGTH = 4;
	public static final int PRODUCT_NAME_MAX_LENGTH = 32;
	public static final int PRODUCT_DESC_MAX_LENGTH = 64;
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
