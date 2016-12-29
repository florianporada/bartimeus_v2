package nl.itopia.modwillie.service.serial;

import java.io.IOException;
import java.io.InputStream;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.fazecast.jSerialComm.SerialPort;

@Async
@Component
public class SerialManager {
	private static final String SERIAL_PORT_NAME = "COM4";
	private static final byte NEW_LINE_BYTE = '\n';
	
	private SerialServer server;
	private SerialPort serialPort;
	
	private boolean halt;
	
	@EventListener({ContextRefreshedEvent.class})
	public void start() {
		halt = false;
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
		
		serialPort.openPort();
		serialPort.setComPortTimeouts(SerialPort.TIMEOUT_READ_SEMI_BLOCKING, 100, 0);
		InputStream input = serialPort.getInputStream();
		
		
		String line = "";
		
		while(!halt) {
			try {
				int bufferSize = input.available();
				if(bufferSize > 0) {
					byte[] buffer = new byte[bufferSize];
					String bufferString = new String(buffer);
					line += bufferString;
					
					if(contains(buffer, NEW_LINE_BYTE)) {
						validateLine(line);
						line = "";
					}
				}
			} catch (IOException e) {
				System.err.println(e.getMessage());
				halt = true;
			}
		}
	}
	
	private void validateLine(String line) {
		
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
	public void stop() {
		System.out.println("Stopping serialManager");
		halt = true;
		
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