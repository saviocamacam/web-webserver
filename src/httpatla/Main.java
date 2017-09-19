package httpatla;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Paths;

public class Main {

	public static void main(String[] args) throws IOException {
		
		int port = 8081; 
		String rootPath = Paths.get("").toAbsolutePath().toString();
        String subPath = "/dir/public_html";

        

        while (true) {
        	ServerSocket serverSocket = new ServerSocket(port); 
            Socket socket = serverSocket.accept();

            new Thread(new Worker(socket, rootPath + subPath)).run();
            serverSocket.close();
        }

	}

	static void log(String string) {
		System.out.println(string);
	}

}
