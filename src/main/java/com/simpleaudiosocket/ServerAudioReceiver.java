package com.simpleaudiosocket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ServerAudioReceiver extends Thread {

	private Socket socket;
	private String ip;
	private int port;
	
	private DataInputStream dis;
	private DataOutputStream dos;
	
	public ServerAudioReceiver(Socket socket, InputStream is, OutputStream os){
		this.socket = socket;
		this.ip = this.socket.getInetAddress().getHostAddress();
		this.port = this.socket.getPort();
		
		this.dis = new DataInputStream(is);
		this.dos = new DataOutputStream(os);
		
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		byte[] buf = new byte[10000];
		int cnt;
		
		try{
			while((cnt=dis.read(buf, 0, buf.length))>=1){
				//when received, then send to others, except himself
				System.out.println("Server receive "+this.port+" said things - "+cnt);
				for(ServerAudioReceiver sr : ServerAudio.clients){
					if(sr.getPort()!=this.port){
						sr.getDos().write(buf, 0, cnt);
						System.out.println("Server resend to "+sr.getPort()+" said things - "+cnt);
					}
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

	public DataInputStream getDis() {
		return dis;
	}

	public DataOutputStream getDos() {
		return dos;
	}

}
