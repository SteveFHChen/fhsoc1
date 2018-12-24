package com.mthread;

import java.io.ObjectInputStream;
import java.net.Socket;

import com.fh.CInfo;

public class ClientRecive extends Thread {
	private Socket socket = null;
	private ObjectInputStream ois = null;
	private CInfo obj1 = null;
	
	public ClientRecive(Socket socket){
		try{
			this.socket = socket;
			this.ois = new ObjectInputStream(this.socket.getInputStream());
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
	
	@Override
	public void run() {
		/* 收到从socket传入过来的对象，并分析数据，作出相应的动作
		 * 
		 */
		try{
			while(true){
				obj1 = (CInfo)this.ois.readObject();
				//System.out.println(obj1);
				System.out.println("["+this.socket.getPort()+"]"+obj1.getSrcPort()+"说："+obj1.getInfo());
			}
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
}
