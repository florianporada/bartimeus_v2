package nl.itopia.modwillie.service.serial;

import java.io.IOException;
import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOServer;
import nl.itopia.modwillie.data.data.ChannelData;

/**
 * The SerialServer will open a SocketServer and broadcast the given ChannelData's.
 * @author Robin de Jong
 */
public class SerialServer {
	public static final int DEFAULT_PORT = 9092;
	public static final String DEFAULT_HOST = "localhost";
	private static final String SOCKET_EVENT = "serial";
	
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
	
	/**
	 * Send the given ChannelData to all connected clients.
	 * @param data
	 */
	public void send(ChannelData data) {
		System.out.println("[SerialServer] Sending to "+SOCKET_EVENT+" with data: "+data);
		server.getBroadcastOperations().sendEvent(SOCKET_EVENT, data);
	}

	/**
	 * Stop the SocektIOServer
	 */
	public void stop() {
		server.stop();
	}
}

