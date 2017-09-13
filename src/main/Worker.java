package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

public class Worker implements Runnable{

	private Socket socket;
	private Map<String, String> headerParams = new HashMap<String, String>();
	private String headerHttpMethod;
	private String headerPath;
	private String headerMessage;
	
	private String mimeType;
	
	private String localPath = "";
	private File folder = null;
	
	InputStream input;
	OutputStream output;

	public Worker(Socket socket, String localPath) {
		this.folder = new File(localPath);
		this.localPath = localPath;
		this.socket = socket;
	}

	@Override
	public void run() {
		Main.log("-> Atendendo requisicao...\n");
		try {
			prepareToRun();
			
			byte[] response = {};
			switch (this.headerHttpMethod) {
			case "GET": response = processGET(); break;

			default:
				break;
			}
			output.write(response);
			output.flush();
			this.finalize();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	

	}
	
	private byte[] processGET() {
		this.localPath = localPath.concat(this.headerPath);
		this.folder = new File(localPath);
		
		if(this.folder.exists() && this.folder.isFile()) {
			mimeType = URLConnection.guessContentTypeFromName(folder.getName());
			Main.log("Mimetype: " + mimeType);
			return reponse200();
			
		} else if(this.folder.exists() && folder.isDirectory()) {
			File folderFiles[] = getFiles("index", "html");
			Main.log("Arquivos: " + folderFiles.length);
			
			if(folderFiles != null)
				return reponse200();
		}
		return reponse404();
	}
	
	private File[] getFiles(String prefix, String extension) {
		
		File[] files = folder.listFiles((dir1, name) -> name.startsWith(prefix) && name.endsWith("." + extension));
		
		return files;
	}

	private byte[] reponse200() {
		Main.log("Source Found: " + headerPath);
		
		String responseHeader = "HTTP/1.1 200 Ok\n"
				+ "\n"
				+ "";
		return responseHeader.getBytes();
	}

	private byte[] reponse404() {
		Main.log("Source not Found: " + headerPath);
		
		String responseHeader = "HTTP/1.1 404 Not found\n"
                + "\n"
        		+ "<!DOCTYPE HTML PUBLIC \"-//IETF//DTD HTML 2.0//EN\"><html><head><title>"
                + "404 Not Found"
        		+ "</title></head><body><h1>Not Found</h1><p>The requested URL "
                + this.headerPath
                + " was not found on this server.</p><hr><address>"
                + "ValentinServer/0.1.1b on "
                + headerParams.get("Host")
                + "</address></body></html>";
        return responseHeader.getBytes();
	 }

	private void prepareToRun() {
		try {
			input = socket.getInputStream();
			output = socket.getOutputStream();

			BufferedReader br = new BufferedReader(new InputStreamReader(input));
			String s = br.readLine();
			
			// Decode HTTP-METHOD
			String ss[] = s.split(" ");
			this.headerHttpMethod = ss[0];
			this.headerPath = ss[1];
			if(ss.length > 2)
				this.headerMessage = ss[2];
			
			// Decode HTTP-HEADER PARAMS
			while(!(s=br.readLine()).isEmpty()){
				System.out.println(s);
				ss = s.split(":");
				headerParams.put(ss[0], ss[1].trim());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	protected void finalize() throws Throwable {
		socket.close();
		System.out.println("Worker encerrado!");
		super.finalize();
	}
}