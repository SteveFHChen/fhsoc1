package com.simplesocket;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ServerReceiver extends Thread {

	private Socket socket;
	private String ip;
	private int port;
	private InputStream is;
	private OutputStream os;
	
	public ServerReceiver(Socket socket, InputStream is, OutputStream os){
		this.socket = socket;
		this.ip = this.socket.getInetAddress().getHostAddress();
		this.port = this.socket.getPort();
		this.is = is;
		this.os = os;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		int i;
		int[] buf = new int[1000];
		char[] linex= new char[1000];
		int ind_buf = 0;
		String line = null;
		String[] msg = null;
		try{
			while((i = is.read())!=-1){
				System.out.print((char)i);
				
				buf[ind_buf] = i;
				ind_buf++;
				
				//Read by line
				if(ind_buf>=2 && buf[ind_buf-2]=='\r' && buf[ind_buf-1]=='\n'){
					
					for(int k=0; k<ind_buf; k++){
						linex[k] = (char)buf[k];
					}
					line = String.valueOf(linex, 0, ind_buf);
					System.out.println("Get line: "+line);
					
					msg = line.split("#");//MSG#port#content
					if("*".equals(msg[1])){
						//Client send message to all other clients.
						for(ServerReceiver sr : Server.clients){
							if(sr.getPort()!=this.port){
								sr.getOs().write(line.getBytes());
							}
						}
					}else{
						int msgPort = Integer.parseInt(msg[1]);
						
						//Client send message to the specified client.
						for(ServerReceiver sr : Server.clients){
							if(sr.getPort()==msgPort){
								sr.getOs().write(line.getBytes());
							}
						}
					}
					
					ind_buf = 0;//reset
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public Socket getSocket() {
		return socket;
	}

	public String getIp() {
		return ip;
	}

	public int getPort() {
		return port;
	}

	public InputStream getIs() {
		return is;
	}

	public OutputStream getOs() {
		return os;
	}

}
