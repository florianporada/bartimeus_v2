package nl.itopia.modwillie.service.doorbell;

import java.io.IOException;
import java.net.URISyntaxException;

import com.ibm.json.java.JSONObject;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import nl.itopia.modwillie.core.util.JSONUtil;
import nl.itopia.modwillie.data.data.ChannelData;
import nl.itopia.modwillie.service.doorbell.data.DoorbellListener;

/**
 * The DoorbellClient holds a connection to the Server and will send a ChannelData object to the DoorbellListener
 * @author Robin de Jong
 */
public class DoorbellClient implements Emitter.Listener {
	private static final int DEFAULT_PORT = 9092;
	private static final String DEFAULT_HOST = "localhost";
	private static final String SOCKET_EVENT = "serial";
	
	private final Socket client;
	private DoorbellListener listener;
	
	public DoorbellClient() throws URISyntaxException {
		this(DEFAULT_HOST, DEFAULT_PORT);
	}
	
	public DoorbellClient(String host, int port) throws URISyntaxException {
		String url = "http://"+host+":"+port;
		
		System.out.println("[DoorbellClient] Connecting to: "+url);
		client = IO.socket(url);
		
		// We don't use the Emitter.Listener interface in this class for connecting.
		// It's mostly for debugging purposes.
		client.on(Socket.EVENT_CONNECT, new Emitter.Listener() {
			@Override
			public void call(Object... arg0) {
				System.out.println("[DoorbellClient] Connected to: "+url);
			}
		});
		
		client.on(SOCKET_EVENT, this);
	}

	public boolean isConnected() {
		return client.connected();
	}
	
	public void connect() {
		client.connect();
	}
	
	@Override
	public void call(Object... objects) {
		System.out.println("[DoorbellClient] Listener: "+listener);
		if(listener != null) {
			for(Object obj : objects) {
				// It's a org.json.JSONObject, while only work with com.ibm.json.java.JSONObject.
				// Get it as a String and use the IBM version instead. 
				String line = ((org.json.JSONObject) obj).toString();
				if(JSONUtil.isValid(line)) {
					JSONObject json;
					try {
						json = JSONObject.parse(line);
						ChannelData data = getChannelData(json);
						System.out.println("[DoorbellClient] Got data: "+data);
						listener.ring(data);
					} catch (IOException e) {
						System.err.println("Error while trying to convert the JSON. It was valid in the Util, but not when casting it later on.");
					}
				}
			}
		}
	}
	
	private ChannelData getChannelData(JSONObject obj) {
		ChannelData data = new ChannelData();
		
		Long id = (Long) obj.get("id");
		Long action = (Long) obj.get("action");
		Long value = (Long) obj.get("value");
		
		data.setId(id.intValue());
		data.setAction(action.intValue());
		data.setValue(value.intValue());
		
		return data;
	}
	
	public void stop() {
		client.close();
	}

	public void setListener(DoorbellListener listener) {
		this.listener = listener;
	}
}
