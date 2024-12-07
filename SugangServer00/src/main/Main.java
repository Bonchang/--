package main;

import java.io.IOException;
import java.net.ServerSocket;

public class Main {

	public static void main(String[] args) {
		try {
			ServerSocket serverSocket = new ServerSocket(1234);
			LoginServer loginServer = new LoginServer();
			loginServer.run(serverSocket);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
