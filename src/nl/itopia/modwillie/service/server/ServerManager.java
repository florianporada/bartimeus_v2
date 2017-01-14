package nl.itopia.modwillie.service.server;

import java.io.IOException;

import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Async
@Component
public class ServerManager {
	private Server server = new Server(Server.DEFAULT_PORT);
	private boolean halt = false;
	
	@EventListener({ContextRefreshedEvent.class})
	public void start() throws IOException {
		System.out.println("[ServerManager] Halt: "+halt);
		if(halt) {
			return;
		}
		
		System.out.println("[ServerManager] Starting server");
		server.start();
	}
	
	public void send(String message) {
		server.onMessageFromSensor(message);
	}
	
	@EventListener({ContextClosedEvent.class})
	public void stop() throws IOException {
		System.out.println("[ServerManager] Stopping server");
		halt = true;
		server.stop();
	}
}
