package com.msocket;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.Socket;

import com.fh.CInfo;

public class ServerSend extends Thread {
	/* Server上的客户端socket向外发送数据
	 * 
	 */
	private BufferedReader sin=new BufferedReader(new InputStreamReader(System.in));
	private ObjectOutputStream Oos = null;
	private Socket socket = null;
	private CInfo obj1 = null;
	private String line = null;
	public void getOos(Socket s){
		try{
			this.Oos = new ObjectOutputStream(s.getOutputStream());
			this.socket = s;
		}catch(Exception e){
			e.getMessage();
		}
	}
	
	public ObjectOutputStream getOos2(){
		return this.Oos;
	}
	
	@Override
	public void run() {
		/*try{
			line = sin.readLine();
			while(!line.equals("bye")){
				obj1 = new CInfo();
				obj1.setInfo(line);
				this.Oos.writeObject(obj1);
				this.Oos.flush();
				line = sin.readLine();
			}
		}catch(Exception e){
			System.out.println(e.getMessage());
		}*/
	}
	
}
