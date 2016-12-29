package nl.itopia.modwillie.service.serial;

import java.io.IOException;
import java.io.InputStream;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.fazecast.jSerialComm.SerialPort;
import com.ibm.json.java.JSONArray;
import com.ibm.json.java.JSONObject;

import nl.itopia.modwillie.core.util.JSONUtil;
import nl.itopia.modwillie.data.data.ChannelData;

@Async
@Component
public class SerialManager {
	private static final String SERIAL_PORT_NAME = "COM4";
	private static final byte NEW_LINE_BYTE = '\n';
	private static final int BUFFER_SIZE = 1024;
	
	private SerialServer server;
	private SerialPort serialPort;
	private InputStream input;
	
	private boolean halt;
	
	@EventListener({ContextRefreshedEvent.class})
	public void start() throws IOException {
		halt = false;
		server = new SerialServer();
		System.out.println("Starting serialManager");
		
		SerialPort[] ports = SerialPort.getCommPorts();
		for(SerialPort port : ports) {
			if(port.getSystemPortName().equals(SERIAL_PORT_NAME)) {
				System.out.println("Selecting port: "+port.getDescriptivePortName());
				serialPort = port;
			}
		}
		
		if(serialPort == null) {
			serialPort = ports[0];
		}
		serialPort.setBaudRate(9600);
		serialPort.openPort();
		serialPort.setComPortTimeouts(SerialPort.TIMEOUT_READ_BLOCKING, 0, 0);
		input = serialPort.getInputStream();
		// Retry to to get the input if none is available!
		
		
		String line = "";
		while(!halt) {
			try {
				int bufferSize = input.available();
				if(bufferSize > 0) {
					byte[] buffer = new byte[bufferSize];
					serialPort.readBytes(buffer, bufferSize);
					String bufferString = new String(buffer);
					line += bufferString;
					if(contains(buffer, NEW_LINE_BYTE)) {
						ChannelData data = validateLine(line);
						if(data != null) {
							server.send(data);
						}
						line = "";
					}
				}
			} catch (IOException e) {
				System.err.println(e.getMessage());
				halt = true;
			}
		}
	}
	
	
	private ChannelData validateLine(String line) throws IOException {
		if(JSONUtil.isValid(line)) {
			JSONObject json = JSONObject.parse(line);
			Long id = (Long) json.get("id");
			Long action = (Long) json.get("action");
			Long value = (Long) json.get("value");
			
			ChannelData channelData = new ChannelData();
			channelData.setId(id.intValue());
			channelData.setAction(action.intValue());
			channelData.setValue(value.intValue());
			
			return channelData;
		}
		
		return null;
	}
	
	private boolean contains(byte[] arr, byte obj) {
		for(byte a : arr) {
			if (a == obj) {
				return true;
			}
		}
		
		return false;
	}
	
	@EventListener({ContextClosedEvent.class})
	public void stop() throws IOException {
		System.out.println("Stopping serialManager");
		halt = true;

		if(input != null) {
			input.close();
		}
		
		if(serialPort != null) {
			if(!serialPort.closePort()) {
				System.err.println("Error while trying to close the port");
			}
		}

		
		if(server != null) {
			server.stop();
		}
	}
}