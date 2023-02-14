package assignment2;

/**
 * 
 * This class represents a product with its various attributes.
 * 
 * @author Susant Shrestha
 */
public class Product {

	private String id;
	private String name;
	private String description;
	private int quantity;
	private double unitPrice;

	/**
	 * 
	 * Constructor for the Product class that takes in all the attributes of the
	 * product.
	 * 
	 * @param id          the ID of the product
	 * @param name        the name of the product
	 * @param description the description of the product
	 * @param quantity    the quantity of the product
	 * @param unitPrice   the unit price of the product
	 */
	public Product(String id, String name, String description, int quantity, double unitPrice) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.quantity = quantity;
		this.unitPrice = unitPrice;
	}

	/**
	 * 
	 * Constructor for the Product class that takes in the ID, name, description,
	 * and quantity of the product. The unit price is set to 0 by default.
	 * 
	 * @param id          the ID of the product
	 * @param name        the name of the product
	 * @param description the description of the product
	 * @param quantity    the quantity of the product
	 */
	public Product(String id, String name, String description, int quantity) {
		this(id, name, description, quantity, 0);
	}

	/**
	 * 
	 * Constructor for the Product class that takes in the ID, name, and description
	 * of the product. The quantity and unit price are set to 0 by default.
	 * 
	 * @param id          the ID of the product
	 * @param name        the name of the product
	 * @param description the description of the product
	 */
	public Product(String id, String name, String description) {
		this(id, name, description, 0);
	}

	/**
	 * 
	 * Constructor for the Product class that takes in the ID and name of the
	 * product. The description, quantity, and unit price are set to empty string
	 * and 0 by default.
	 * 
	 * @param id   the ID of the product
	 * @param name the name of the product
	 */
	public Product(String id, String name) {
		this(id, name, "");
	}

	/**
	 * 
	 * Constructor for the Product class that takes in the ID of the product. The
	 * name, description, quantity, and unit price are set to empty string and 0 by
	 * default.
	 * 
	 * @param id the ID of the product
	 */
	public Product(String id) {
		this(id, "");
	}

	/**
	 * 
	 * Default constructor for the Product class. The ID, name, description,
	 * quantity, and unit price are set to empty string and 0 by default.
	 */
	public Product() {
		this("");
	}

	/**
	 * 
	 * Getter for the ID of the product.
	 * 
	 * @return the ID of the product
	 */
	public String getId() {
		return id;
	}

	/**
	 * 
	 * Setter for the ID of the product.
	 * 
	 * @param id the ID of the product
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 
	 * Getter for the name of the product. Trims any leading or trailing white
	 * spaces from the name.
	 * 
	 * @return the name of the product
	 */
	public String getName() {
		return name.trim();
	}

	/**
	 * 
	 * Setter for the name of the product.
	 * 
	 * @param name the name of the product
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 
	 * Getter for the description of the product. Trims any leading or trailing
	 * white spaces from the description.
	 * 
	 * @return the description of the product
	 */
	public String getDescription() {
		return description.trim();
	}

	/**
	 * 
	 * Setter for the description of the product.
	 * 
	 * @return name the description of the product
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * 
	 * Getter for the quantity of the product.
	 * 
	 * @return name the quantity of the product
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * 
	 * Setter for the quantity of the product.
	 * 
	 * @param name the quantity of the product
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	/**
	 * 
	 * Getter for the unit price of the product.
	 * 
	 * @return name the unit price of the product
	 */
	public double getUnitPrice() {
		return unitPrice;
	}

	/**
	 * 
	 * Setter for the unit price of the product.
	 * 
	 * @param name the unit price of the product
	 */
	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}

	/**
	 * 
	 * Checks whether this product is equal to another object based on its ID.
	 * 
	 * @param obj the other object to compare
	 * @return true if the objects are equal, false otherwise
	 */
	@Override
	public boolean equals(Object obj) {
		Product other = (Product) obj;
		return this.id.equals(other.id);
	}
}
