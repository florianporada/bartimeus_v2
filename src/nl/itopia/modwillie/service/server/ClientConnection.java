package nl.itopia.modwillie.service.server;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class represents a connection to a client.
 *
 * @author jimj316
 */
public class ClientConnection {

	private final Socket sock;
	private final PrintStream out;

	public ClientConnection(Socket sock) throws IOException {
		this.sock = sock;
		this.out = new PrintStream(sock.getOutputStream());
	}

	public boolean isWorking() {
		return sock.isConnected() && !sock.isClosed();
	}

	public void sendMessage(String message) {
		out.println(message);
	}

	void close() {
		try {
			sock.close();
		} catch (IOException ex) {
			Logger.getLogger(ClientConnection.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

}