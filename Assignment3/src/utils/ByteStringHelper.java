package utils;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/**
 * This class provides helper methods for converting between strings and byte
 * arrays.
 * 
 * @author SUSANT_SHRESTHA_N01550307
 */
public class ByteStringHelper {

	/**
	 * 
	 * Converts a given string to a byte array of specified size.
	 * 
	 * @param str  the string to be converted
	 * @param size the size of the resulting byte array
	 * @return the byte array representing the string
	 */
	public static byte[] stringToBytes(String str, int size) {
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
	public static String bytesToString(byte[] bytes) {
		// The byte array is assumed to be in UTF-8 format.
		// The resulting string is trimmed of leading and trailing whitespace.
		return new String(bytes, StandardCharsets.UTF_8).trim();
	}
}
