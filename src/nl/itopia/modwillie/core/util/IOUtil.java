package nl.itopia.modwillie.core.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class IOUtil {
	public static final String DEFAULT_ENCODING = "UTF-8";

	public static String callHttpsPost(String postUrl, String body, Header ... headers) throws IOException {
		URL url = new URL(postUrl);
		HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
		connection.setRequestMethod("POST");
		connection.setRequestProperty("Content-Length", "100");
		
		for(Header header : headers) {
			connection.setRequestProperty(
				header.getKey(), 
				header.getValue()
			);
		}
		
		connection.setDoOutput(true);
		connection.setDoInput(true);
		
		OutputStream out = connection.getOutputStream();
		out.write(body.getBytes("UTF-8"));
		out.close();
		
		
		InputStream input = connection.getInputStream();
		String output = inputStreamToString(input);
		
		return output;
	}
	
	public static String inputStreamToString(InputStream input) throws IOException {
		return inputStreamToString(input, DEFAULT_ENCODING);
	}
	
	public static String inputStreamToString(InputStream input, String encoding) throws IOException {
		InputStreamReader reader = new InputStreamReader(input, encoding);
		final int CHARS_PER_PAGE = 5000; //counting spaces
		final char[] buffer = new char[CHARS_PER_PAGE];
		StringBuilder output = new StringBuilder(CHARS_PER_PAGE);
		for(int read = reader.read(buffer, 0, buffer.length); read != -1; read = reader.read(buffer, 0, buffer.length)) {
		    output.append(buffer, 0, read);
		}
		
		return output.toString();
	}
	
	public static class Header {
		private String key;
		private String value;
		
		public Header() {
			
		}

		public Header(String key, String value) {
			super();
			this.key = key;
			this.value = value;
		}

		public String getKey() {
			return key;
		}

		public void setKey(String key) {
			this.key = key;
		}

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}

		@Override
		public String toString() {
			return "Header [key=" + key + ", value=" + value + "]";
		}
	}
}
