package com.nil.sjs.SJSServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.http.WebSocket.Listener;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SJSServer {

	private static final int PORT = 10015;
//	private static ArrayList<ClientHandler> clients = new ArrayList<>();
	private static ExecutorService pool = Executors.newCachedThreadPool();
//	private static ErrorCode errorCode = new ErrorCode();

	public static void main(String[] args) throws IOException {

		ServerSocket listener = new ServerSocket(PORT);
		System.out.printf("Server has started. Running SJS Server on port %d \n", PORT);
		
		try {
			while(true) {
				Socket client = listener.accept();
				ClientHandler clientThread = new ClientHandler(client);				
				pool.execute(clientThread);
				
			}
		}catch (Exception e) {
			System.err.println("Server has encountered an error!!, Show must go on");
		}finally {
			listener.close();
		}

	}
}
