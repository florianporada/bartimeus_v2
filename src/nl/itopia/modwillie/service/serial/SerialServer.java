package nl.itopia.modwillie.service.serial;

import java.io.IOException;
import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOServer;
import nl.itopia.modwillie.data.data.ChannelData;


public class SerialServer {
	public static final int DEFAULT_PORT = 9092;
	public static final String DEFAULT_HOST = "localhost";
	
	private final SocketIOServer server;
	
	public SerialServer() throws IOException {
		this(DEFAULT_HOST, DEFAULT_PORT);
	}
	
	public SerialServer(String host, int port) throws IOException {
		Configuration config = new Configuration();
		config.setHostname(host);
		config.setPort(port);
		
		server = new SocketIOServer(config);
		server.start();
	}
	
	public void send(ChannelData data) {
		System.out.println("Sending: "+data);
		server.getBroadcastOperations().sendEvent("serial", data);
	}
	
	public void stop() {
		server.stop();
	}
}

