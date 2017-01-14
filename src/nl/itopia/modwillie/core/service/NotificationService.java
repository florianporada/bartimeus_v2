package nl.itopia.modwillie.core.service;

import java.io.IOException;

import nl.itopia.modwillie.core.util.IOUtil;
import nl.itopia.modwillie.data.model.Pattern;

public class NotificationService {
	public static final String NOTIFICATION_URL = "https://gcm-http.googleapis.com/gcm/send";
	private static final IOUtil.Header[] HEADERS;
	
	private static final String PATTERN_TAG = "<pattern>";
	private static final String TITLE_TAG = "<title>";
	private static final String MESSAGE_TAG = "<message>";
	private static final String MESSAGE_TEMPLATE = PATTERN_TAG+"|"+TITLE_TAG+"|"+MESSAGE_TAG+"\n";
	
	static {
		HEADERS = new IOUtil.Header[]{
			new IOUtil.Header("Content-Type", "application/json"),
			new IOUtil.Header("Authorization", "key=AIzaSyAg0AzhYIyiUTSjhl6zE0UtkKVEGvTUcq8")
		};
	}
	
	
	public static void ring(Pattern pattern) {
		int id = pattern.getServerId();
		String body = "{\"to\":\"/topics/test\",\"notification\":{\"body\":\"Someone is at the door\",\"title\":\"Ring\", \"pattern\":"+id+"}}";
		send(body);
	}
	
	public static void test(Pattern pattern) {
		int id = pattern.getServerId();
		String body = "{\"to\":\"/topics/test\",\"notification\":{\"body\":\"Testing the pattern\",\"title\":\"Test\", \"pattern\":"+id+"}}";
		send(body);
	}
	
	private static void send(String body) {
		try {
			String out = IOUtil.callHttpsPost(NOTIFICATION_URL, body, HEADERS);
			System.out.println("Sended notification, with body: "+body+", result: "+out);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String construct(int pattern, String title, String msg) {
		String newMsg = MESSAGE_TEMPLATE;
		newMsg = newMsg.replace(PATTERN_TAG, ""+pattern);
		newMsg = newMsg.replaceAll(TITLE_TAG, title);
		newMsg = newMsg.replaceAll(MESSAGE_TAG, msg);
		
		return newMsg;
	}
}
