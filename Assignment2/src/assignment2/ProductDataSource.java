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

public class ProductDataSource {

	public ArrayList<Product> getAllProducts() throws Exception {
		return getAllProducts(new File(AppConstants.DATASOURCE_PATH));
	}
	
	public ArrayList<Product> getAllProducts(File file) throws Exception {
		ArrayList<Product> products = new ArrayList<Product>();
		long totalBytes = file.length();
		try (DataInputStream in = new DataInputStream(new FileInputStream(file))) {
			for (int bytes = 0; bytes < totalBytes; bytes += AppConstants.PRODUCT_TOTAL_BYTES) {
				String id = bytesToString(in.readNBytes(AppConstants.PRODUCT_ID_LENGTH));
				String name = bytesToString(in.readNBytes(AppConstants.PRODUCT_NAME_MAX_LENGTH));
				String description = bytesToString(in.readNBytes(AppConstants.PRODUCT_DESC_MAX_LENGTH));
				System.out.println(id + " " + name + " " + description);
				int quantity = in.readInt();
				double price = in.readDouble();
				products.add(new Product(id, name, description, quantity, price));
			}
		} catch (FileNotFoundException e) {
			throw new FileNotFoundException("File not found in the path: " + AppConstants.DATASOURCE_PATH);
		} catch (IOException e) {
			e.printStackTrace();
			throw new IOException("Couldn't read the product from the file. Make sure the input is correct!!", e);
		} catch (Exception e) {
			throw new Exception("Some unexpected error occured while adding the product!!", e);
		}
		return products;
	}

	public Product getProduct(int position, boolean reverse) throws Exception {
		File file = new File(AppConstants.DATASOURCE_PATH);
		long totalBytes = file.length();
		long skipBytes = 0;
		if (reverse) {
			skipBytes = totalBytes - (AppConstants.PRODUCT_TOTAL_BYTES * (position + 1));
		} else {
			skipBytes = (AppConstants.PRODUCT_TOTAL_BYTES * position);
		}
		if (totalBytes == 0 || skipBytes < 0 || skipBytes > totalBytes - AppConstants.PRODUCT_TOTAL_BYTES) {
			return null;
		}
		try (DataInputStream in = new DataInputStream(new FileInputStream(file))) {
			in.skip(skipBytes);
			String id = bytesToString(in.readNBytes(AppConstants.PRODUCT_ID_LENGTH));
			String name = bytesToString(in.readNBytes(AppConstants.PRODUCT_NAME_MAX_LENGTH));
			String description = bytesToString(in.readNBytes(AppConstants.PRODUCT_DESC_MAX_LENGTH));
			int quantity = in.readInt();
			double price = in.readDouble();
			return new Product(id, name, description, quantity, price);
		} catch (FileNotFoundException e) {
			throw new FileNotFoundException("File not found in the path: " + AppConstants.DATASOURCE_PATH);
		} catch (IOException e) {
			throw new IOException("Couldn't read the product from the file. Make sure the input is correct!!", e);
		} catch (Exception e) {
			throw new Exception("Some unexpected error occured while adding the product!!", e);
		}
	}

	public Product getProduct(int position) throws Exception {
		return getProduct(position, false);
	}

	public void addProduct(Product newProduct) throws Exception {
		this.validateProduct(newProduct);
		File file = new File(AppConstants.DATASOURCE_PATH);
		ArrayList<Product> products = getAllProducts(file);
		if (products.contains(newProduct)) {
			throw new Exception("Product with id " + newProduct.getId() + " is already added");
		}
		try (DataOutputStream out = new DataOutputStream(new FileOutputStream(file));) {
			for (Product product : products) {
				out.write(stringToBytes(product.getId(), AppConstants.PRODUCT_ID_LENGTH));
				out.write(stringToBytes(product.getName(), AppConstants.PRODUCT_NAME_MAX_LENGTH));
				out.write(stringToBytes(product.getDescription(), AppConstants.PRODUCT_DESC_MAX_LENGTH));
				out.writeInt(product.getQuantity());
				out.writeDouble(product.getUnitPrice());
			}
			out.write(stringToBytes(newProduct.getId(), AppConstants.PRODUCT_ID_LENGTH));
			out.write(stringToBytes(newProduct.getName(), AppConstants.PRODUCT_NAME_MAX_LENGTH));
			out.write(stringToBytes(newProduct.getDescription(), AppConstants.PRODUCT_DESC_MAX_LENGTH));
			out.writeInt(newProduct.getQuantity());
			out.writeDouble(newProduct.getUnitPrice());
		} catch (FileNotFoundException e) {
			throw new FileNotFoundException("File not found in the path: " + AppConstants.DATASOURCE_PATH);
		} catch (IOException e) {
			throw new IOException("Couldn't write the product to the file. Make sure the input is correct!!", e);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Some unexpected error occured while adding the product!!", e);
		}
	}
	
	public int getNumProducts() throws Exception {
	    int position = 0;
	    while (getProduct(position, false) != null) {
	        position++;
	    }
	    return position;
	}

	public void updateProduct(Product newProduct) throws Exception {
		this.validateProduct(newProduct);
		File file = new File(AppConstants.DATASOURCE_PATH);
		ArrayList<Product> products = getAllProducts(file);
		if (!products.contains(newProduct)) {
			throw new Exception("Product with id " + newProduct.getId() + " is doesn't exist.");
		}
		try (DataOutputStream out = new DataOutputStream(new FileOutputStream(file));) {
			for (Product product : products) {
				if (product.equals(newProduct)) {
					out.write(stringToBytes(newProduct.getId(), AppConstants.PRODUCT_ID_LENGTH));
					out.write(stringToBytes(newProduct.getName(), AppConstants.PRODUCT_NAME_MAX_LENGTH));
					out.write(stringToBytes(newProduct.getDescription(), AppConstants.PRODUCT_DESC_MAX_LENGTH));
					out.writeInt(newProduct.getQuantity());
					out.writeDouble(newProduct.getUnitPrice());
					continue;
				}
				out.write(stringToBytes(product.getId(), AppConstants.PRODUCT_ID_LENGTH));
				out.write(stringToBytes(product.getName(), AppConstants.PRODUCT_NAME_MAX_LENGTH));
				out.write(stringToBytes(product.getDescription(), AppConstants.PRODUCT_DESC_MAX_LENGTH));
				out.writeInt(product.getQuantity());
				out.writeDouble(product.getUnitPrice());
			}
		} catch (FileNotFoundException e) {
			throw new FileNotFoundException("File not found in the path: " + AppConstants.DATASOURCE_PATH);
		} catch (IOException e) {
			throw new IOException("Couldn't write the product to the file. Make sure the input is correct!!", e);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Some unexpected error occured while adding the product!!", e);
		}
	}

	public ArrayList<Product> findProductsByKeyword(String keyword) throws Exception {
		ArrayList<Product> products = new ArrayList<Product>();
		try (DataInputStream in = new DataInputStream(new FileInputStream(AppConstants.DATASOURCE_PATH))) {
			int totalBytes = in.available();
			for (int bytes = 0; bytes <= totalBytes; bytes += AppConstants.PRODUCT_TOTAL_BYTES) {
				String id = bytesToString(in.readNBytes(AppConstants.PRODUCT_ID_LENGTH));
				String name = bytesToString(in.readNBytes(AppConstants.PRODUCT_NAME_MAX_LENGTH));
				if (name.contains(keyword)) {
					String description = bytesToString(in.readNBytes(AppConstants.PRODUCT_DESC_MAX_LENGTH));
					int quantity = in.readInt();
					double price = in.readDouble();
					products.add(new Product(id, name, description, quantity, price));
				}
			}
		} catch (FileNotFoundException e) {
			throw new FileNotFoundException("File not found in the path: " + AppConstants.DATASOURCE_PATH);
		} catch (IOException e) {
			throw new IOException("Couldn't read the product from the file. Make sure the input is correct!!", e);
		} catch (Exception e) {
			throw new Exception("Some unexpected error occured while adding the product!!", e);
		}
		return products;
	}

	public ArrayList<Product> findProductsByPriceRange(double minPrice, double maxPrice) throws Exception {
		ArrayList<Product> products = new ArrayList<Product>();
		try (DataInputStream in = new DataInputStream(new FileInputStream(AppConstants.DATASOURCE_PATH))) {
			int totalBytes = in.available();
			for (int bytes = 0; bytes <= totalBytes; bytes += AppConstants.PRODUCT_TOTAL_BYTES) {
				double price = in.readDouble();
				if (price >= minPrice && price <= maxPrice) {
					String id = bytesToString(in.readNBytes(AppConstants.PRODUCT_ID_LENGTH));
					String name = bytesToString(in.readNBytes(AppConstants.PRODUCT_NAME_MAX_LENGTH));
					String description = bytesToString(in.readNBytes(AppConstants.PRODUCT_DESC_MAX_LENGTH));
					int quantity = in.readInt();
					products.add(new Product(id, name, description, quantity, price));
				}
			}
		} catch (FileNotFoundException e) {
			throw new FileNotFoundException("File not found in the path: " + AppConstants.DATASOURCE_PATH);
		} catch (IOException e) {
			throw new IOException("Couldn't read the product from the file. Make sure the input is correct!!", e);
		} catch (Exception e) {
			throw new Exception("Some unexpected error occured while adding the product!!", e);
		}
		return products;
	}

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

	private byte[] stringToBytes(String str, int size) {
		byte[] bytes = str.getBytes();
		return Arrays.copyOf(bytes, size);
	}

	private String bytesToString(byte[] bytes) {
		return new String(bytes, StandardCharsets.UTF_8);
	}
}
