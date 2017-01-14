package nl.itopia.modwillie.service.server;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class impliments a message server.
 *
 * @author jimj316
 */
public class Server {

	private static final String PIPE_NAME = "/tmp/bartimeusfifo";
	public static final int DEFAULT_PORT = 2774;

	private ServerSocket sock;
	private final Set<ClientConnection> clients = new HashSet<>();
	private final int port;

	/**
	 * Constructs a new Server instance, that will listen on the given port.
	 * @param port the port number to listen on.
	 */
	public Server(int port) {
		this.port = port;
	}

	/**
	 * Starts this server instance; it will bind to it's port and will open a pipe for commands.
	 * @throws IOException
	 */
	public void start() throws IOException {
//		openFifo();
		openSock();
	}

	/**
	 *
	 * @throws IOException
	 */
	protected void openSock() throws IOException {
		this.sock = new ServerSocket(port);
		System.out.println("Now listening on port " + port);
		new Thread("TCP broadcast thread") {
			@Override
			public void run() {
				try {
					while (true) {
						try {
							Socket accepted = sock.accept(); // listen for a connection, and accept it
							System.out.println("Accepted connection from " + accepted.getRemoteSocketAddress().toString());
							ClientConnection cli = new ClientConnection(accepted); // create a new client connection wrapper
							clients.add(cli); // add it to the list of clients
						} catch (IOException ex) {

							// do nothing useful for now
//							ex.printStackTrace();
						}
					}
				} finally {
					try {
						System.out.println("Closing socket...");
						if(sock != null && !sock.isClosed()) {
							sock.close();
						}
					} catch (IOException ex) {
						System.err.println("WARNING: failed to close IO socket!");
						Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
					}
				}
			}
		}.start();
	}

	protected void openFifo() throws RuntimeException, IOException {
		System.out.println("Connecting named pipe " + PIPE_NAME);
		File fifoFile = new File (PIPE_NAME);
		if (!fifoFile.exists())
		{
			System.out.println("Pipe doesn't exist, opening.");
			Process exec = Runtime.getRuntime().exec("mkfifo " + PIPE_NAME);
			int exit = -1;
			do {
				try {
					exec.waitFor();
					exit = exec.exitValue();
				} catch (InterruptedException ex) {
					Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
				}
			} while (exit == -1);

			if (exit == 0) {
				System.out.println("Opened pipe succesfully!");
			} else {
				System.err.println("Failed to open pipe: mkfifo returned error code " + exit);
				throw new RuntimeException("Could not open FIFO: mkfifo error " + exit);
			}
		}
		else if (!fifoFile.canRead())
		{
			System.err.println("Pipe exists, but I have no permissions to read it!");
			throw new RuntimeException("Could not open FIFO: permission denied.");
		}
		else
		{
			System.out.println("Connected to pipe.");
		}

		new Thread("FIFO watcher thread") {
			@Override
			public void run() {
				RandomAccessFile pipe = null;
				try {
					pipe = new RandomAccessFile( PIPE_NAME, "r");
					boolean run = true;
					while (run)
					{
						// Read response from pipe
						String read = pipe.readLine();
						if (read != null)
							onMessageFromSensor(read);
//						else
//							run = false;
					}
					System.err.println("FIFO EOF reached!");

				} catch (FileNotFoundException ex) {
					System.err.println("Could not locate FIFO.");
					Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
				} catch (IOException ex) {
					System.err.println("An IO exception occurred connecting to the FIFO. ");
					Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
				} finally {
					if (pipe != null)
						try
						{
							System.out.println("Closing pipe...");
							pipe.close();
						} catch (IOException ex) {
							System.err.println("WARNING: FIFO was not closed!");
					}
				}

			}
		}.start();
	}

	public void stop() {
		try {
			sock.close();
			for (ClientConnection cli : clients) {
				cli.close();
			}
			sock = null;
		} catch (IOException ex) {
			Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public void onMessageFromSensor(String message) {
		System.out.println("Sending " + message + " to all clients.");
		Set<ClientConnection> deadClients = new HashSet<>();
		for (ClientConnection cli : clients) {
			if (cli.isWorking()) {

				cli.sendMessage(message);
			} else {
				deadClients.add(cli);
			}
		}
		clients.removeAll(deadClients);
		if (!deadClients.isEmpty()) {
			System.out.println("Clients " + deadClients + " disconnected.");
		}
	}

	public static int i = 0;

	public static void main(String[] args) throws IOException {

		int port = -1;
		for (String arg : args) {
			if (arg.startsWith("-p")) {
				port = Integer.parseInt(arg.substring(2));
			}
		}
		if (port == -1) {
			port = DEFAULT_PORT;
			System.out.println("No port specified (with -p<PORT> flag); defaulting to port "+DEFAULT_PORT+".");
		}

		Server server = new Server(port);
		server.start();

		Runtime.getRuntime().addShutdownHook(new Thread("Shutdown process")
		{
			@Override
			public void run() {
				stop();
			}
		});
//		Scanner sc = new Scanner(System.in);

//		System.out.println("Press ENTER to send a message.");
//		while (true) {
//			sc.nextLine();
//		Timer timer = new Timer();
//		timer.schedule(new TimerTask() {
//			@Override
//			public void run() {
		// Simulate a person ringing the doorbell
		//Syntax:
		// MESSAGE_TYPE:SOURCE_NAME:ARGUMENTS:ARGUMENTS...
//			server.onMessageFromSensor("SENSOREVENT:DOORBELL1:PERSON_AT_DOOR:" + i);
//			i++;
//		}
//		}, 1000, 1000);
	}
//	}
}