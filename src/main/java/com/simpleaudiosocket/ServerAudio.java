package com.simpleaudiosocket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import com.simplesocket.ServerConsoleInput;

public class ServerAudio {

	public static List<ServerAudioReceiver> clients = new ArrayList<ServerAudioReceiver>();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int port=8084;
		if(args.length>=1){
			port=Integer.parseInt(args[0]);
		}
		
		
		ServerSocket server = null;
		Socket socket = null;
		ServerAudioReceiver sr = null;
		try{
			server=new ServerSocket(port);
			System.out.println("Server started, port is: "+port);
			
			ServerConsoleInput serverConsoleInput = new ServerConsoleInput();
			serverConsoleInput.start();
			
			while(true){
				System.out.println("Waiting client connect...");
				socket = server.accept();
				System.out.println("Client "+socket.getInetAddress().getHostAddress()+":"+socket.getPort()+" connected.");
				
				
				OutputStream os2 = socket.getOutputStream();
				InputStream is2 = socket.getInputStream();
				
				sr = new ServerAudioReceiver(socket, is2, os2);
				sr.start();
				clients.add(sr);
				System.out.println("Client is added into clients pool success!");
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				server.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
