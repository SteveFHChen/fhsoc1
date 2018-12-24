package com.msocket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class SocketServerTCPm {
	
	/**
	 * java -cp ./fhsocket1-0.0.1-SNAPSHOT.jar com.msocket.SocketServerTCPm
	 * @param args
	 */
	public static void main(String[] args){
		ServerSocket server = null;
		try {
			server = new ServerSocket(8082);
			List<fhSocket> sockets = new ArrayList<fhSocket>();
			fhSocket fhs = null;
			
			ServerInput sin = new ServerInput(sockets);
			sin.start();
			
			while(true){
				Socket socket = server.accept();
				ServerRecive sr1=new ServerRecive(sockets);
				ServerSend ss1=new ServerSend();
				
				fhs = new fhSocket();
				fhs.setSocket(socket);
				fhs.setSr(sr1);
				fhs.setSs(ss1);
				sockets.add(fhs);
				
				sr1.getOis(socket);
				ss1.getOos(socket);
				
				sr1.start();
				ss1.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				server.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//如何在Server端断开socket连接，并释放所有的资源？
		//Server端如何捕获Client异常断开，是
	}
}
