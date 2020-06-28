package com.simplesocket;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ServerConsoleInput extends Thread {

	private BufferedReader sin;
	
	public ServerConsoleInput(){
		this.sin=new BufferedReader(new InputStreamReader(System.in));
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("Server console input is started.");
		try{
			String line=sin.readLine();
			while(!line.equals("bye")){
				for(ServerReceiver client : Server.clients){
					client.getOs().write(line.getBytes());
					client.getOs().flush();
				}
				line=sin.readLine();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
}
