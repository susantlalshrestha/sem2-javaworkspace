package model;

import java.util.Objects;

public class Staff {
	private String id;
	private String lastName;
	private String firstName;
	private char mi;
	private String address;
	private String city;
	private String state;
	private String telephone;
	private String email;

	public Staff(String id, String lastName, String firstName, char mi, String address, String city, String state,
			String telephone, String email) {
		this.id = id;
		this.lastName = lastName;
		this.firstName = firstName;
		this.mi = mi;
		this.address = address;
		this.city = city;
		this.state = state;
		this.telephone = telephone;
		this.email = email;
	}

	public Staff(String id, String lastName, String firstName, char mi, String address, String city, String state,
			String telephone) {
		this(id, lastName, firstName, mi, address, city, state, telephone, "");
	}

	public Staff(String id, String lastName, String firstName, char mi, String address, String city, String state) {
		this(id, lastName, firstName, mi, address, city, state, "");
	}

	public Staff(String id, String lastName, String firstName, char mi, String address, String city) {
		this(id, lastName, firstName, mi, address, city, "");
	}

	public Staff(String id, String lastName, String firstName, char mi, String address) {
		this(id, lastName, firstName, mi, address, "");
	}

	public Staff(String id, String lastName, String firstName, char mi) {
		this(id, lastName, firstName, mi, "");
	}

	public Staff(String id, String lastName, String firstName) {
		this(id, lastName, firstName, 'M');
	}

	public Staff(String id, String lastName) {
		this(id, lastName, "");
	}

	public Staff(String id) {
		this(id, "");
	}

	public Staff() {
		this("");
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFristName() {
		return firstName;
	}

	public void setFristName(String fristName) {
		this.firstName = fristName;
	}

	public char getMi() {
		return mi;
	}

	public void setMi(char mi) {
		this.mi = mi;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		Staff other = (Staff) obj;
		return this.id.equals(other.id);
	}
}
