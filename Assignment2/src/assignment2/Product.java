package assignment2;

import java.util.Objects;

public class Product {

	private String id;
	private String name;
	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", description=" + description + ", quantity=" + quantity
				+ ", unitPrice=" + unitPrice + "]";
	}

	private String description;
	private int quantity;
	private double unitPrice;

	public Product(String id, String name, String description, int quantity, double unitPrice) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.quantity = quantity;
		this.unitPrice = unitPrice;
	}

	public Product(String id, String name, String description, int quantity) {
		this(id, name, description, quantity, 0);
	}

	public Product(String id, String name, String description) {
		this(id, name, description, 0);
	}

	public Product(String id, String name) {
		this(id, name, "");
	}

	public Product(String id) {
		this(id, "");
	}

	public Product() {
		this("");
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name.trim();
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description.trim();
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}

	@Override
	public boolean equals(Object obj) {
		Product other = (Product) obj;
		return this.id.equals(other.id);
	}
}
