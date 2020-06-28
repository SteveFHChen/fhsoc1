package com.simplesocket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {

	public static List<ServerReceiver> clients = new ArrayList<ServerReceiver>();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int port=8083;
		if(args.length>=1){
			port=Integer.parseInt(args[0]);
		}
		
		
		ServerSocket server = null;
		Socket socket = null;
		ServerReceiver sr = null;
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
				
				sr = new ServerReceiver(socket, is2, os2);
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
