package nl.itopia.modwillie.core.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

import javax.xml.bind.DatatypeConverter;

public class HashUtil {
	private static final String HASH_ALGORITHM = "sha-256";
	private static final String DEFAULT_ENCODING = "UTF-8";
	
	private static final int SIMPLE_HASH_LENGTH = 8;
	
	public static int getNewID() {
		Random random = new Random();
		return random.nextInt(Integer.MAX_VALUE);
	}
	
	public static String simpleHash(String msg) {
		String hash = hash(msg);
		return hash.substring(0, SIMPLE_HASH_LENGTH);
	}
	
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
}
