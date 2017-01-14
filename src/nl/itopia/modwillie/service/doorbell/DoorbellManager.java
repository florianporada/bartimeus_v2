package nl.itopia.modwillie.service.doorbell;

import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import nl.itopia.modwillie.core.service.NotificationService;
import nl.itopia.modwillie.core.service.SensorService;
import nl.itopia.modwillie.core.service.UserService;
import nl.itopia.modwillie.data.data.ChannelAction;
import nl.itopia.modwillie.data.data.ChannelData;
import nl.itopia.modwillie.data.model.Pattern;
import nl.itopia.modwillie.data.model.Sensor;
import nl.itopia.modwillie.data.model.User;
import nl.itopia.modwillie.service.doorbell.data.DoorbellListener;
import nl.itopia.modwillie.service.server.Server;
import nl.itopia.modwillie.service.server.ServerManager;

/**
 * The DoorbellManager will actually process the incomming data. RETRY_COUNT is the count the application is allowed to retry to make a connection, RETRY_DELAY is the delay between the retries.
 * While waiting for a new retry, the thread is blocked. The manager will run in asynchronous.
 * @author Robin de Jong
 */
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
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ServerManager serverManager;
	
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
	
	/**
	 * Tries to get a connection to the SocketIOServer. RETRY_COUNT tries will be made, before returning null
	 * @return A connected DoorbellClient
	 * @throws URISyntaxException
	 */
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

	/**
	 * Process the given ChannelData. It will look at the action of the channel and call the correct method for it
	 * @param data ChannelData
	 */
	public void processRing(ChannelData data) {
		Sensor sensor = sensorService.getSensorForId(data.getId());
		ChannelAction action = ChannelAction.get(data.getAction());
		
		System.out.println("Got something: "+data);
		
		if(action == ChannelAction.RING) {
			sendNotification(data.getValue(), sensor);
		} else if(action == ChannelAction.INVALID) {
			registerUser(data.getValue(), sensor);
		} else {
			System.err.println("[DoorbellManager] Error for: "+data);
		}
	}
	
	/**
	 * Send a 'TEST' command to the watch with the given pattern, this should "test" the vibration pattern so the user can decide if he likes it.
	 * @param pattern
	 */
	public void sendTestNotification(Pattern pattern) {
		// TODO: Send a notification to the watch which should try to use this pattern
		System.out.println("[DoorbellManager] Send TEST, with pattern: "+pattern);
		
//		NotificationService.test(pattern);
		String message = NotificationService.construct(pattern.getServerId(), "Test", "Testing notification");
		serverManager.send(message);
	}
	
	/**
	 * Send a 'RING' command to the watch with the patterns the user likes.
	 * @param sensor
	 */
	public void sendNotification(int id, Sensor sensor) {
		System.out.println("[DoorbellManager.sendNotification] Get the pattern that is used");
		// The patterns are on FetchType.EAGER, so if we access it from the sensor, we won't actually get the patterns
		long userId = sensor.getUser().getId();
		User user = userService.getUser(userId);
		
		//		NotificationService.ring(user.getIncommingPattern());
		Pattern pattern = user.getIncommingPattern();
		
		String description = "A person is at the door";
		if(id < 0) {
			description = "A unknown person is at the door";
		}
		
		String message = NotificationService.construct(pattern.getServerId(), "Ring", description);
		serverManager.send(message);
	}
	
	/**
	 * Register the given ID in the database
	 * @param id The ID from the FingerPrint sensor
	 * @param sensor
	 */
	public void registerUser(int id, Sensor sensor) {
		// TODO: Register the user
	}
}
