package assignment2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * This class represents a data source for products. It contains methods to
 * retrieve, add, update, and search for products in a file-based data source.
 * 
 * @author Susant Shrestha
 */
public class ProductDataSource {

	/**
	 * 
	 * Returns all products stored in the default data source file.
	 * 
	 * @return an ArrayList of Product objects representing all the products stored
	 *         in the data source
	 * @throws Exception if an error occurs while reading the data source file
	 */
	public ArrayList<Product> getAllProducts() throws Exception {
		// Gets all products from the default data source file
		return getAllProducts(new File(AppConstants.DATASOURCE_PATH));
	}

	/**
	 * 
	 * Returns all products stored in a specified data source file.
	 * 
	 * @param file - the File object representing the data source file
	 * @return an ArrayList of Product objects representing all the products stored
	 *         in the data source
	 * @throws Exception if an error occurs while reading the data source file
	 */
	public ArrayList<Product> getAllProducts(File file) throws Exception {
		// Retrieves the total size of the data source file
		long totalBytes = file.length();
		try (DataInputStream in = new DataInputStream(new FileInputStream(file))) {
			// Initializes a list of Product objects
			ArrayList<Product> products = new ArrayList<Product>();
			// Iterates through the data source file one product at a time
			for (int bytes = 0; bytes < totalBytes; bytes += AppConstants.PRODUCT_TOTAL_BYTES) {
				// Reads the product ID, name, and description from the data source file
				String id = bytesToString(in.readNBytes(AppConstants.PRODUCT_ID_LENGTH));
				String name = bytesToString(in.readNBytes(AppConstants.PRODUCT_NAME_MAX_LENGTH));
				String description = bytesToString(in.readNBytes(AppConstants.PRODUCT_DESC_MAX_LENGTH));

				// Reads the product quantity and unit price from the data source file
				int quantity = in.readInt();
				double price = in.readDouble();

				// Creates a new Product object with the retrieved data and adds it to the list
				products.add(new Product(id, name, description, quantity, price));
			}
			// Returns the list of Product objects
			return products;
		} catch (FileNotFoundException e) {
			// Throws a FileNotFoundException if the specified data source file is not found
			throw new FileNotFoundException("File not found in the path: " + AppConstants.DATASOURCE_PATH);
		} catch (IOException e) {
			// Throws an IOException if there is an error reading the data source file
			throw new IOException("Couldn't read the product from the file. Make sure the input is correct!!", e);
		} catch (Exception e) {
			// Throws a generic Exception if there is any other unexpected error
			throw new Exception("Some unexpected error occured while adding the product!!", e);
		}
	}

	/**
	 * 
	 * Returns a product at a specified position in the data source.
	 * 
	 * @param position the 0-based position of the product in the data source
	 * @param reverse  a boolean flag indicating whether the product should be
	 *                 retrieved in reverse order
	 * @return the Product object representing the product at the specified position
	 * @throws Exception if an error occurs while reading the data source file or if
	 *                   the position is invalid
	 */
	public Product getProduct(int position, boolean reverse) throws Exception {
		// Get the data source file
		File file = new File(AppConstants.DATASOURCE_PATH);
		// Get the total size of the data source file in bytes
		long totalBytes = file.length();
		// Initialize the number of bytes to skip in the file to 0
		long skipBytes = 0;
		// Determine the number of bytes to skip based on the position and reverse flags
		if (reverse) {
			skipBytes = totalBytes - (AppConstants.PRODUCT_TOTAL_BYTES * (position + 1));
		} else {
			skipBytes = (AppConstants.PRODUCT_TOTAL_BYTES * position);
		}
		// Check if the skip bytes are within the bounds of the file and return null if
		// not
		if (totalBytes == 0 || skipBytes < 0 || skipBytes > totalBytes - AppConstants.PRODUCT_TOTAL_BYTES) {
			return null;
		}
		try (DataInputStream in = new DataInputStream(new FileInputStream(file))) {
			// Skip the specified number of bytes in the file
			in.skip(skipBytes);
			// Reads the product ID, name, and description from the data source file
			String id = bytesToString(in.readNBytes(AppConstants.PRODUCT_ID_LENGTH));
			String name = bytesToString(in.readNBytes(AppConstants.PRODUCT_NAME_MAX_LENGTH));
			String description = bytesToString(in.readNBytes(AppConstants.PRODUCT_DESC_MAX_LENGTH));
			// Reads the product quantity and unit price from the data source file
			int quantity = in.readInt();
			double price = in.readDouble();
			// Return a new Product object with the read data
			return new Product(id, name, description, quantity, price);
		} catch (FileNotFoundException e) {
			// Throws a FileNotFoundException if the specified data source file is not found
			throw new FileNotFoundException("File not found in the path: " + AppConstants.DATASOURCE_PATH);
		} catch (IOException e) {
			// Throws an IOException if there is an error reading the data source file
			throw new IOException("Couldn't read the product from the file. Make sure the input is correct!!", e);
		} catch (Exception e) {
			// Throws a generic Exception if there is any other unexpected error
			throw new Exception("Some unexpected error occured while adding the product!!", e);
		}
	}

	/**
	 * 
	 * Adds a new product to the data source.
	 * 
	 * @param newProduct the Product object representing the new product to be added
	 * @return the 0-based position of the newly added product in the data source
	 * @throws Exception if an error occurs while writing to the data source file or
	 *                   if the product is invalid
	 */
	public int addProduct(Product newProduct) throws Exception {
		// Validate the new product before adding it to the data source
		this.validateProduct(newProduct);
		// Get the data source file
		File file = new File(AppConstants.DATASOURCE_PATH);
		// Get all the products from the data source
		ArrayList<Product> products = getAllProducts(file);
		// Check if the new product already exists in the data source
		if (products.contains(newProduct)) {
			throw new Exception("Product with id " + newProduct.getId() + " is already added");
		}
		try (DataOutputStream out = new DataOutputStream(new FileOutputStream(file));) {
			// Write all the existing products to the data source
			for (Product product : products) {
				out.write(stringToBytes(product.getId(), AppConstants.PRODUCT_ID_LENGTH));
				out.write(stringToBytes(product.getName(), AppConstants.PRODUCT_NAME_MAX_LENGTH));
				out.write(stringToBytes(product.getDescription(), AppConstants.PRODUCT_DESC_MAX_LENGTH));
				out.writeInt(product.getQuantity());
				out.writeDouble(product.getUnitPrice());
			}
			// Write the new product to the data source
			out.write(stringToBytes(newProduct.getId(), AppConstants.PRODUCT_ID_LENGTH));
			out.write(stringToBytes(newProduct.getName(), AppConstants.PRODUCT_NAME_MAX_LENGTH));
			out.write(stringToBytes(newProduct.getDescription(), AppConstants.PRODUCT_DESC_MAX_LENGTH));
			out.writeInt(newProduct.getQuantity());
			out.writeDouble(newProduct.getUnitPrice());
			// Return the position of the newly added product in the data source
			return products.size();
		} catch (FileNotFoundException e) {
			// Throws a FileNotFoundException if the specified data source file is not found
			throw new FileNotFoundException("File not found in the path: " + AppConstants.DATASOURCE_PATH);
		} catch (IOException e) {
			// Throws an IOException if there is an error reading the data source file
			throw new IOException("Couldn't write the product to the file. Make sure the input is correct!!", e);
		} catch (Exception e) {
			// Throws a generic Exception if there is any other unexpected error
			throw new Exception("Some unexpected error occured while adding the product!!", e);
		}
	}

	/**
	 * 
	 * Updates an existing product in the data source.
	 * 
	 * @param newProduct the Product object representing the updated product
	 * @return the 0-based position of the updated product in the data source
	 * @throws Exception if an error occurs while writing to the data source file or
	 *                   if the product is invalid
	 */
	public int updateProduct(Product newProduct) throws Exception {
		// Validate the new product before adding it to the data source
		this.validateProduct(newProduct);
		// Get the data source file
		File file = new File(AppConstants.DATASOURCE_PATH);
		// Gets all products from the data source
		ArrayList<Product> products = getAllProducts(file);
		// Initializes the updated position to -1
		int updatedPosition = -1;
		// Throws an exception if the product to be updated does not exist
		if (!products.contains(newProduct)) {
			throw new Exception("Product with id " + newProduct.getId() + " is doesn't exist.");
		}
		// Writes the updated product details to the data source
		try (DataOutputStream out = new DataOutputStream(new FileOutputStream(file));) {
			for (int i = 0; i < products.size(); i++) {
				Product product = products.get(i);
				// Writes the updated product details
				if (product.equals(newProduct)) {
					out.write(stringToBytes(newProduct.getId(), AppConstants.PRODUCT_ID_LENGTH));
					out.write(stringToBytes(newProduct.getName(), AppConstants.PRODUCT_NAME_MAX_LENGTH));
					out.write(stringToBytes(newProduct.getDescription(), AppConstants.PRODUCT_DESC_MAX_LENGTH));
					out.writeInt(newProduct.getQuantity());
					out.writeDouble(newProduct.getUnitPrice());
					// Updates the position of the updated product in the list
					updatedPosition = i;
					continue;
				}
				// Writes the details of the products other than the updated product
				out.write(stringToBytes(product.getId(), AppConstants.PRODUCT_ID_LENGTH));
				out.write(stringToBytes(product.getName(), AppConstants.PRODUCT_NAME_MAX_LENGTH));
				out.write(stringToBytes(product.getDescription(), AppConstants.PRODUCT_DESC_MAX_LENGTH));
				out.writeInt(product.getQuantity());
				out.writeDouble(product.getUnitPrice());
			}
			// Returns the position of the updated product in the list
			return updatedPosition;
		} catch (FileNotFoundException e) {
			// Throws a FileNotFoundException if the specified data source file is not found
			throw new FileNotFoundException("File not found in the path: " + AppConstants.DATASOURCE_PATH);
		} catch (IOException e) {
			// Throws an IOException if there is an error reading the data source file
			throw new IOException("Couldn't write the product to the file. Make sure the input is correct!!", e);
		} catch (Exception e) {
			// Throws a generic Exception if there is any other unexpected error
			throw new Exception("Some unexpected error occured while adding the product!!", e);
		}
	}

	/**
	 * 
	 * Searches for products in the data source by a keyword in the product name or
	 * description.
	 * 
	 * @param keyword the keyword to search for
	 * @return an ArrayList of Product objects representing the products matching
	 *         the search criteria
	 * @throws Exception if an error occurs while reading the data source file
	 */
	public ArrayList<Product> findProductsByKeyword(String keyword) throws Exception {
		try (DataInputStream in = new DataInputStream(new FileInputStream(AppConstants.DATASOURCE_PATH))) {
			ArrayList<Product> products = new ArrayList<Product>();
			// Get the total number of bytes in the data source file.
			int totalBytes = in.available();
			// Read through the file one product at a time, checking if the name contains
			// the keyword.
			for (int bytes = 0; bytes <= totalBytes; bytes += AppConstants.PRODUCT_TOTAL_BYTES) {
				// Read the product ID and convert to a String.
				String id = bytesToString(in.readNBytes(AppConstants.PRODUCT_ID_LENGTH));
				// Read the product name and convert to a String.
				String name = bytesToString(in.readNBytes(AppConstants.PRODUCT_NAME_MAX_LENGTH));
				// If the name contains the keyword, read the rest of the product information
				// and add to the ArrayList.
				if (name.contains(keyword)) {
					String description = bytesToString(in.readNBytes(AppConstants.PRODUCT_DESC_MAX_LENGTH));
					int quantity = in.readInt();
					double price = in.readDouble();
					products.add(new Product(id, name, description, quantity, price));
				}
			}
			return products;
		} catch (FileNotFoundException e) {
			// Throws a FileNotFoundException if the specified data source file is not found
			throw new FileNotFoundException("File not found in the path: " + AppConstants.DATASOURCE_PATH);
		} catch (IOException e) {
			// Throws an IOException if there is an error reading the data source file
			throw new IOException("Couldn't read the product from the file. Make sure the input is correct!!", e);
		} catch (Exception e) {
			// Throws a generic Exception if there is any other unexpected error
			throw new Exception("Some unexpected error occured while adding the product!!", e);
		}
	}

	/**
	 * 
	 * Searches for products in the data source within a specified price range.
	 * 
	 * @param minPrice the minimum price of the products to search for
	 * @param maxPrice the maximum price of the products to search for
	 * @return an ArrayList of Product objects representing the products matching
	 *         the search criteria
	 * @throws Exception if an error occurs while reading the data source file
	 */
	public ArrayList<Product> findProductsByPriceRange(double minPrice, double maxPrice) throws Exception {
		try (DataInputStream in = new DataInputStream(new FileInputStream(AppConstants.DATASOURCE_PATH))) {
			ArrayList<Product> products = new ArrayList<Product>();
			// Get the total number of bytes in the data source file.
			int totalBytes = in.available();
			// Read through the file one product at a time, checking if the name contains
			// the keyword.
			for (int bytes = 0; bytes <= totalBytes; bytes += AppConstants.PRODUCT_TOTAL_BYTES) {
				// Read the price value of the product from the file
				double price = in.readDouble();
				// Check if the product price is within the specified range
				if (price >= minPrice && price <= maxPrice) {
					// Read the remaining product details from the file and create a new Product
					// object
					String id = bytesToString(in.readNBytes(AppConstants.PRODUCT_ID_LENGTH));
					String name = bytesToString(in.readNBytes(AppConstants.PRODUCT_NAME_MAX_LENGTH));
					String description = bytesToString(in.readNBytes(AppConstants.PRODUCT_DESC_MAX_LENGTH));
					int quantity = in.readInt();
					products.add(new Product(id, name, description, quantity, price));
				}
			}
			return products;
		} catch (FileNotFoundException e) {
			// Throws a FileNotFoundException if the specified data source file is not found
			throw new FileNotFoundException("File not found in the path: " + AppConstants.DATASOURCE_PATH);
		} catch (IOException e) {
			// Throws an IOException if there is an error reading the data source file
			throw new IOException("Couldn't read the product from the file. Make sure the input is correct!!", e);
		} catch (Exception e) {
			// Throws a generic Exception if there is any other unexpected error
			throw new Exception("Some unexpected error occured while adding the product!!", e);
		}
	}

	/**
	 * 
	 * Validates a product to ensure it meets certain criteria.
	 * 
	 * @param product the Product object to be validated
	 * @throws Exception if the product fails any of the validation checks
	 */
	public void validateProduct(Product product) throws Exception {
		if (product.getId() == null || product.getId().isBlank()) {
			throw new Exception("Product id cannot be empty!!");
		}
		if (product.getId().length() != AppConstants.PRODUCT_ID_LENGTH) {
			throw new Exception("Product id length must be " + AppConstants.PRODUCT_ID_LENGTH);
		}
		if (product.getName() == null || product.getName().isBlank()) {
			throw new Exception("Product name cannot be empty!!");
		}
		if (product.getName().length() > AppConstants.PRODUCT_NAME_MAX_LENGTH) {
			throw new Exception("Product id length must be at most " + AppConstants.PRODUCT_NAME_MAX_LENGTH);
		}
		if (product.getQuantity() <= 0) {
			throw new Exception("Product quantity must be above 0!!");
		}
		if (product.getUnitPrice() <= 0) {
			throw new Exception("Product unit price must be above 0!!");
		}
	}

	/**
	 * 
	 * Converts a given string to a byte array of specified size.
	 * 
	 * @param str  the string to be converted
	 * @param size the size of the resulting byte array
	 * @return the byte array representing the string
	 */
	private byte[] stringToBytes(String str, int size) {
		byte[] bytes = str.getBytes();
		return Arrays.copyOf(bytes, size);
	}

	/**
	 * 
	 * Converts a given byte array to its corresponding string.
	 * 
	 * @param bytes the byte array to be converted
	 * @return the string representation of the byte array
	 */
	private String bytesToString(byte[] bytes) {
		return new String(bytes, StandardCharsets.UTF_8);
	}
}
