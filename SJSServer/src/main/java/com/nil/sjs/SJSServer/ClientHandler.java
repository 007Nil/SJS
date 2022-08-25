package com.nil.sjs.SJSServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import com.nil.sjs.SJSServer.Service.JsubService;


public class ClientHandler implements Runnable {
	private Socket client;
	private BufferedReader input;
	private PrintWriter output;
	private JsubService jsubService = new JsubService();

	public ClientHandler(Socket clientSocket) throws IOException {

		this.client = clientSocket;
		input = new BufferedReader(new InputStreamReader(client.getInputStream()));
		output = new PrintWriter(client.getOutputStream(),true);
	}

	@Override
	public void run() {
		try {
			String command = input.readLine();
			String binaryName = command.split(",")[0].split(":")[1];
			System.out.println(binaryName);
			if (binaryName.equals("jsub")) {
				output.println(jsubService.JobSubmitService(command));
			}
		} catch (Exception e) {

			System.err.println(e);
		} finally {
			try {
				client.close();
				input.close();
				output.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}
