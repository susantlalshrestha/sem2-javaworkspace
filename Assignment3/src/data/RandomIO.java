package data;

import java.io.IOException;
import java.io.RandomAccessFile;

import business.Person;
import utils.ByteStringHelper;

/**
 * 
 * The RandomIO class provides methods for reading and writing person records to
 * a random access file. It supports opening and closing the file, finding a
 * person record by ID, finding the index of a person record, reading a person
 * record from a specific position, reading the last person record in the file,
 * writing a person record to a specific position, and writing a person record
 * to the end of the file.
 * 
 * @author SUSANT_SHRESTHA_N01550307
 */
public class RandomIO {
	private RandomAccessFile file;

	/**
	 * Closes the random access file if it is not null.
	 * 
	 * @throws IOException if an I/O error occurs while closing the file.
	 */
	public void close() throws IOException {
		// Check if the file object is not null.
		if (this.file != null) {
			// Call the close() method of the file object to close the file.
			this.file.close();
		}
		// Set the file object to null.
		this.file = null;
	}

	/**
	 * Finds the index of the person record with the specified ID in the file.
	 * 
	 * @param personID the ID of the person record to find.
	 * @return the index of the person record with the specified ID, or -1 if not
	 *         found.
	 * @throws IOException if an I/O error occurs while reading from the file.
	 */
	public int findIndexOf(String personID) throws IOException {
		// Iterate through all the records in the file.
		for (int index = 0; index < this.size(); index++) {
			// Move the file pointer to the start of the current record.
			this.file.seek(index * Person.RECORD_SIZE);
			// Read the ID bytes from the current record.
			byte[] bytes = new byte[Person.ID_LENGTH];
			this.file.read(bytes);
			// Convert the ID bytes to a string.
			String id = ByteStringHelper.bytesToString(bytes);
			// Compare the ID to the given person ID.
			if (id.equalsIgnoreCase(personID)) {
				// If the IDs match, return the current index.
				return index;
			}
		}
		// If the person ID is not found in the file, return -1.
		return -1;
	}

	/**
	 * Finds the index of the specified person record in the file.
	 * 
	 * @param person the person record to find.
	 * @return the index of the specified person record, or -1 if not found.
	 * @throws IOException if an I/O error occurs while reading from the file.
	 */
	public int findIndexOf(Person person) throws IOException {
		// Call the findIndexOf method with the person's ID.
		return this.findIndexOf(person.getId());
	}

	/**
	 * Finds and returns the person record with the specified ID in the file.
	 * 
	 * @param personID the ID of the person record to find.
	 * @return the person record with the specified ID, or null if not found.
	 * @throws IOException if an I/O error occurs while reading from the file.
	 */
	public Person find(String personID) throws IOException {
		// Find the position of the Person with the given ID.
		int position = this.findIndexOf(personID);
		// If a position was found, read the Person from that position and return it.
		if (position != -1) {
			return this.read(position);
		}
		// If no position was found, return null to indicate that the Person was not
		// found.
		return null;
	}

	/**
	 * Opens the specified file in read-write mode.
	 * 
	 * @param filePath the path of the file to open.
	 * @throws IOException if an I/O error occurs while opening the file.
	 */
	public void open(String filePath) throws IOException {
		// Close the file if it is already open.
		this.close();
		// Create a new RandomAccessFile instance with the given file path in read-write
		// mode.
		this.file = new RandomAccessFile(filePath, "rw");
	}

	/**
	 * Reads and returns the person record at the specified position in the file.
	 * 
	 * @param position the position of the person record to read.
	 * @return the person record at the specified position.
	 * @throws IOException if an I/O error occurs while reading from the file.
	 */
	public Person read(int position) throws IOException {
		// Move the file pointer to the beginning of the specified record.
		this.file.seek(position * Person.RECORD_SIZE);
		// Read the ID bytes from the current record.
		byte[] bytes = new byte[Person.ID_LENGTH];
		this.file.read(bytes);
		// Convert the ID bytes to a string.
		String id = ByteStringHelper.bytesToString(bytes);
		// Read the first name bytes from the current record.
		bytes = new byte[Person.FIRST_NAME_MAX_LENGTH];
		this.file.read(bytes);
		// Convert the first name bytes to a string.
		String firstName = ByteStringHelper.bytesToString(bytes);
		// Read the last name bytes from the current record.
		bytes = new byte[Person.LAST_NAME_MAX_LENGTH];
		this.file.read(bytes);
		// Convert the last name bytes to a string.
		String lastName = ByteStringHelper.bytesToString(bytes);
		// Read the phone bytes from the current record.
		bytes = new byte[Person.PHONE_MAX_LENGTH];
		this.file.read(bytes);
		// Convert the phone bytes to a string.
		String phone = ByteStringHelper.bytesToString(bytes);
		// Read the integer age from the current record.
		int age = this.file.readInt();
		// Create and return a new Person object with the data read from the file.
		return new Person(id, firstName, lastName, phone, age);
	}

	/**
	 * Reads the last Person object in the file.
	 * 
	 * @return the last Person object in the file.
	 * @throws IOException if an I/O error occurs while reading the file.
	 */
	public Person read() throws IOException {
		// Call the read() method with the position set to the last record.
		return this.read(this.size() - 1);
	}

	/**
	 * Returns the number of Person objects in the file.
	 * 
	 * @return the number of Person objects in the file.
	 * @throws IOException if an I/O error occurs while reading the file.
	 */
	public int size() throws IOException {
		// Calculate the number of records by dividing the file length by the size of a
		// single record.
		return (int) (this.file.length() / Person.RECORD_SIZE);
	}

	/**
	 * Writes the given Person object to the specified position in the file.
	 * 
	 * @param person   the Person object to write.
	 * @param position the position in the file to write the Person object to.
	 * @throws IOException if an I/O error occurs while writing to the file.
	 */
	public void write(Person person, int position) throws IOException {
		// Move the file pointer to the position in the file where the Person object
		// should be written.
		this.file.seek(position * Person.RECORD_SIZE);
		// Write the Person's ID to the file as a byte array.
		this.file.write(ByteStringHelper.stringToBytes(person.getId(), Person.ID_LENGTH));
		// Write the Person's first name to the file as a byte array.
		this.file.write(ByteStringHelper.stringToBytes(person.getFirstName(), Person.FIRST_NAME_MAX_LENGTH));
		// Write the Person's last name to the file as a byte array.
		this.file.write(ByteStringHelper.stringToBytes(person.getLastName(), Person.LAST_NAME_MAX_LENGTH));
		// Write the Person's phone number to the file as a byte array.
		this.file.write(ByteStringHelper.stringToBytes(person.getPhone(), Person.PHONE_MAX_LENGTH));
		// Write the Person's age to the file as an int.
		this.file.writeInt(person.getAge());
	}

	/**
	 * Writes the given Person object to the last position in the file.
	 * 
	 * @param person the Person object to write.
	 * @throws IOException if an I/O error occurs while writing to the file.
	 */
	public void write(Person person) throws IOException {
		// Write the Person object to the end of the file by calling the overloaded
		// write method with the size of the file as the position.
		this.write(person, this.size());
	}
}
