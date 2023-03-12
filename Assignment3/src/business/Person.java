package business;

import java.util.Objects;

/**
 * 
 * The Person class represents a person with an ID, first name, last name, phone
 * number, and age.
 * 
 * @author SUSANT_SHRESTHA_N01550307
 */
public class Person {
	/**
	 * Specifies the length of the record ID.
	 */
	public static final int ID_LENGTH = 4;
	/**
	 * Specifies the maximum length of the person's first name.
	 */
	public static final int FIRST_NAME_MAX_LENGTH = 20;
	/**
	 * Specifies the maximum length of the person's last name.
	 */
	public static final int LAST_NAME_MAX_LENGTH = 25;
	/**
	 * Specifies the maximum length of the person's phone.
	 */
	public static final int PHONE_MAX_LENGTH = 10;
	/**
	 * Specifies the total number of bytes required to store a single record of
	 * person in the file.
	 */
	public static final int RECORD_SIZE = ID_LENGTH + FIRST_NAME_MAX_LENGTH + LAST_NAME_MAX_LENGTH + PHONE_MAX_LENGTH
			+ Integer.BYTES;

	// Instance variables
	private String id;
	private String firstName;
	private String lastName;
	private String phone;
	private int age;

	/**
	 * Constructs a new Person object with the given ID, first name, last name,
	 * phone number, and age.
	 * 
	 * @param id        the ID of the person
	 * @param firstName the first name of the person
	 * @param lastName  the last name of the person
	 * @param phone     the phone number of the person
	 * @param age       the age of the person
	 */
	public Person(String id, String firstName, String lastName, String phone, int age) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phone = phone;
		this.age = age;
	}

	/**
	 * Constructs a new Person object with the given ID, first name, last name, and
	 * phone number.
	 * 
	 * @param id        the ID of the person
	 * @param firstName the first name of the person
	 * @param lastName  the last name of the person
	 * @param phone     the phone number of the person
	 */
	public Person(String id, String firstName, String lastName, String phone) {
		this(id, firstName, lastName, phone, 0);
	}

	/**
	 * Constructs a new Person object with the given ID, first name, and last name.
	 * 
	 * @param id        the ID of the person
	 * @param firstName the first name of the person
	 * @param lastName  the last name of the person
	 */
	public Person(String id, String firstName, String lastName) {
		this(id, firstName, lastName, "");
	}

	/**
	 * Constructs a new Person object with the given ID and first name.
	 * 
	 * @param id        the ID of the person
	 * @param firstName the first name of the person
	 */
	public Person(String id, String firstName) {
		this(id, firstName, "");
	}

	/**
	 * Constructs a new Person object with the given ID.
	 * 
	 * @param id the ID of the person
	 */
	public Person(String id) {
		this(id, "");
	}

	/**
	 * Constructs a new Person object with no ID, first name, last name, phone
	 * number, or age.
	 */
	public Person() {
		this("");
	}

	/**
	 * Retrieves the ID of the person, in uppercase format.
	 * 
	 * @return the ID of the person
	 */
	public String getId() {
		if (id != null) {
			return id.toUpperCase();
		}
		return id;
	}

	/**
	 * Sets the record id of the person.
	 * 
	 * @param id The record id of the person.
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Retrieves the first name of the person.
	 * 
	 * @return The first name of the person.
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Sets the first name of the person.
	 * 
	 * @param firstName The first name of the person.
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Retrieves the last name of the person.
	 * 
	 * @return The last name of the person.
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Sets the last name of the person.
	 * 
	 * @param lastName The last name of the person.
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Retrieves the phone number of the person.
	 * 
	 * @return The phone number of the person.
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * Sets the phone number of the person.
	 * 
	 * @param phone The phone number of the person.
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * Retrieves the age of the person.
	 * 
	 * @return The age of the person.
	 */
	public int getAge() {
		return age;
	}

	/**
	 * Sets the age of the person.
	 * 
	 * @param age The age of the person.
	 */
	public void setAge(int age) {
		this.age = age;
	}

	/**
	 * Computes the hashcode of the person object based on its record id.
	 * 
	 * @return The hashcode of the person object.
	 */
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	/**
	 * Compares the identity of two Person objects based on their record id
	 * 
	 * @param obj The object to compare to.
	 * @return true if the objects are equal, false otherwise.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		return Objects.equals(id, other.id);
	}
}
