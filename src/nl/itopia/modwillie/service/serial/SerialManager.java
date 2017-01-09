package nl.itopia.modwillie.service.serial;

import java.io.IOException;
import java.io.InputStream;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import com.fazecast.jSerialComm.SerialPort;
import com.ibm.json.java.JSONObject;

import nl.itopia.modwillie.core.helper.CouldNotStartException;
import nl.itopia.modwillie.core.util.JSONUtil;
import nl.itopia.modwillie.data.data.ChannelData;

/**
 * The SerialManager will open a serial port and send the data to the SerialServer. RETRY_COUNT is the count the application is allowed to retry to make a connection, RETRY_DELAY is the delay between the retries.
 * While waiting for a new retry, the thread is blocked. The manager will run in asynchronous.
 * @author Robin de Jong
 */
@Async
@Component
public class SerialManager {
//	private static final String SERIAL_PORT_NAME = "COM4";
	private static final String SERIAL_PORT_NAME = "cu.wchusbserial1420";
	private static final byte NEW_LINE_BYTE = '\n';
	private static final int RETRY_COUNT = 10;
	private static final int RETRY_DELAY = 3000;
	
	private SerialServer server;
	private SerialPort serialPort;
	private InputStream input;
	
	private boolean halt;
	
	@EventListener({ContextRefreshedEvent.class})
	public void start() throws IOException, CouldNotStartException {
		halt = false;
		server = new SerialServer();
		System.out.println("Starting serialManager");
		
		SerialPort[] ports = SerialPort.getCommPorts();
		for(SerialPort port : ports) {
			System.out.println("Available ports: "+port.getSystemPortName()+", "+port.getDescriptivePortName());
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
		input = getInputStream(serialPort);
		
		System.out.println("[SerialManager] Got input: "+input);
		if(input == null) {
			throw new CouldNotStartException();
		}
		
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
	
	/**
	 * Get the input stream of a serial port. If it isn't available, retry it RETRY_COUNT times.  
	 * @param port SerialPort
	 * @return A input stream of the Serial port
	 */
	private InputStream getInputStream(SerialPort port) {
		InputStream in = null;
		int tries = 0;
		
		do {
			in = port.getInputStream();
			
			if(in == null) {
				tries ++;
				System.out.println("[SerialManager] ["+tries+"/"+RETRY_COUNT+"] Didn't get a InputStream, retrying in "+RETRY_DELAY+" seconds.");
				try {
					Thread.sleep(RETRY_DELAY);
				} catch (InterruptedException e) {
					break;
				}
			}
			
		} while(!halt && in == null && tries < RETRY_COUNT);
		
		
		return in;
	}
	
	/**
	 * Validate whether a line is a valid ChannelData object.
	 * @param line A string containing that could contain the JSON ChannelData
	 * @return A ChannelData object, or a null if the line wasn't valid
	 * @throws IOException
	 */
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
			System.out.println("[SerialManager] Got channelData: "+channelData);
			
			return channelData;
		}
		
		return null;
	}
	
	/**
	 * Check if a given byte is in a byte array
	 * @param arr
	 * @param obj
	 * @return If it the array contains the byte
	 */
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