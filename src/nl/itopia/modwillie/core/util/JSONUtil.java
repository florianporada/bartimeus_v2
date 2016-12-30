package nl.itopia.modwillie.core.util;

import java.io.IOException;
import com.ibm.json.java.JSONArray;
import com.ibm.json.java.JSONObject;

public class JSONUtil {
	/**
	 * Checks if the provided JSON is a valid JSON object
	 * @param data
	 * @return A boolean if the data is valid JSON
	 */
	public static boolean isValid(String data) {
		try {
			JSONObject.parse(data);
		} catch (IOException e) {
			try {
				JSONArray.parse(data);
				// Use an exception, because it's possible that the returned data is a number.
			} catch (Exception e1) {
				return false;
			}
		}
		
		return true;
	}
}