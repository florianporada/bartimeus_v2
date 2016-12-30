package nl.itopia.modwillie.service.doorbell;

import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import nl.itopia.modwillie.core.service.SensorService;
import nl.itopia.modwillie.data.data.ChannelAction;
import nl.itopia.modwillie.data.data.ChannelData;
import nl.itopia.modwillie.data.model.Pattern;
import nl.itopia.modwillie.data.model.Sensor;
import nl.itopia.modwillie.service.doorbell.data.DoorbellListener;

@Async
@Component
public class DoorbellManager {
	private static final int RETRY_COUNT = 10;
	private static final int RETRY_DELAY = 3000;
	
	private DoorbellClient client;
	private boolean halt;
	
	private DoorbellListener respond;
	
	@Autowired
	private SensorService sensorService;
	
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

	@EventListener({ContextClosedEvent.class})
	public void stop() {
		System.out.println("[DoorbellManager] Stopping");
		client.stop();
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


	public void processRing(ChannelData data) {
		Sensor sensor = sensorService.getSensorForId(data.getId());
		ChannelAction action = ChannelAction.get(data.getAction());
		
		if(action == ChannelAction.RING) {
			sendNotification(sensor);
		} else if(action == ChannelAction.INVALID) {
			registerUser(sensor);
		} else {
			System.err.println("[DoorbellManager] Error for: "+data);
		}
	}
	
	public void sendTestNotification(Pattern pattern) {
		// TODO: Send a notification to the watch which should try to use this pattern
//		System.out.println("[DoorbellManager] Send TEST, with pattern: "+pattern.getPattern());
		System.out.println("[DoorbellManager] Send TEST, with pattern: "+pattern);
	}
	
	public void sendNotification(Sensor sensor) {
//		System.out.println("[DoorbellManager.sendNotification] ");
		System.out.println("[DoorbellManager.sendNotification] Get the pattern that is used");
		
	}
	
	public void registerUser(Sensor sensor) {
		// TODO: Register the user
	}
}
