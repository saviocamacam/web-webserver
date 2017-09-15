package httpatla;

import java.io.IOException;
import java.net.ServerSocket;

public class ServerManager {
	
	private ServerSocket serverSocket = null;
	private int port;
	
	public ServerManager(int port) {
		this.port = port;
	}
	
	public void runServer() {
		try {
			this.serverSocket = new ServerSocket(port);
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
