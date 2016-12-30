package nl.itopia.modwillie.service.doorbell;

import java.io.InputStream;
import java.net.URISyntaxException;

import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import nl.itopia.modwillie.data.data.ChannelData;
import nl.itopia.modwillie.service.doorbell.data.DoorbellListener;

@Async
@Component
public class DoorbellManager {
	private static final int RETRY_COUNT = 10;
	private static final int RETRY_DELAY = 3000;
	
	private DoorbellClient client;
	private boolean halt;
	
	private DoorbellListener respond;
	
	@EventListener({ContextRefreshedEvent.class})
	public void start() throws URISyntaxException {
		halt = false;
		client = getConnection();
		// Due to SpringMVC and the EvenListener annotation, we can't implement a interface.
		respond = new DoorbellListener() {
			@Override
			public void ring(ChannelData data) {
				processRing(data);
			}
		};
		client.setListener(respond);
		
	}
	
	private DoorbellClient getConnection() throws URISyntaxException {
		DoorbellClient client = new DoorbellClient();
		int tries = 0;
		
		do {
			client.connect();
			
			if(!client.isConnected()) {
				tries ++;
				System.out.println("[DoorbellManager] ["+tries+"/"+RETRY_COUNT+"] Didn't get a connection, retrying in "+RETRY_DELAY+" seconds.");
				try {
					Thread.sleep(RETRY_DELAY);
				} catch (InterruptedException e) {
					break;
				}
			}
			
		} while(!halt && !client.isConnected() && tries < RETRY_COUNT);
		
		
		return client;
	}
	
	@EventListener({ContextClosedEvent.class})
	public void stop() {
		System.out.println("[DoorbellManager] Stopping");
		client.stop();
	}

	public void processRing(ChannelData data) {
		System.out.println("[DoorbellManager] Ring: "+data);
	}
}
