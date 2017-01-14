package nl.itopia.modwillie.core.util;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class IPUtil {
	public static String createAddress(String host, int port) {
		return host + ":" + port;
	}
	
	public static String getLocalAddress() {
		try {
			InetAddress local = InetAddress.getLocalHost();
			return local.getHostAddress();
		} catch (UnknownHostException e) {
		}
		return null;
	}
}
