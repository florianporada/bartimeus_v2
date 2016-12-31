package nl.itopia.modwillie.core.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

import javax.xml.bind.DatatypeConverter;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * A util class that helps with the generation of hashes
 * @author Robin de Jong
 */
public class HashUtil {
	private static final String HASH_ALGORITHM = "sha-256";
	private static final String DEFAULT_ENCODING = "UTF-8";
	private static final int MAX_INT = 32767; // https://www.arduino.cc/en/Reference/Int
	
	private static final int SIMPLE_HASH_LENGTH = 8;
	
	private static final BCryptPasswordEncoder ENCODER = new BCryptPasswordEncoder();
	
	/**
	 * Get a new ID. This should mostly be used for generating new sensors. The bounds is between 0 and 32,767.
	 * Which is the max int for Arduino (https://www.arduino.cc/en/Reference/Int).
	 * @return
	 */
	public static int getNewID() {
		Random random = new Random();
		return random.nextInt(MAX_INT);
	}
	
	/**
	 * Generate a hash that is more easy to type. The simple hash is a normal hash, with a length of SIMPLE_HASH_LENGTH
	 * @param msg
	 * @return
	 */
	public static String simpleHash(String msg) {
		String hash = hash(msg);
		return hash.substring(0, SIMPLE_HASH_LENGTH);
	}
	
	/**
	 * Generate a HASH_ALGORITHM hash with the given message.
	 * @param msg String of the message to be hashed
	 * @return A hashed message
	 */
	public static String hash(String msg) {
		String out = "";
		try {
			MessageDigest digest = MessageDigest.getInstance(HASH_ALGORITHM);
			byte[] bytes = msg.getBytes(DEFAULT_ENCODING);
			byte[] digested = digest.digest(bytes);
			
			out = DatatypeConverter.printHexBinary(digested);
		} catch (NoSuchAlgorithmException e) {
			System.err.println("NoSuchAlgorithmException: "+HASH_ALGORITHM);
			return null;
		} catch (UnsupportedEncodingException e) {
			System.err.println("UnsupportedEncodingException: "+DEFAULT_ENCODING);
			return null;
		}
		return out;
	}
	

	/**
	 * Hash the given password with BCryptPasswordEncoder
	 * @param password
	 * @return
	 */
	public static String hashPassword(String password) {
		return ENCODER.encode(password);
	}
}
