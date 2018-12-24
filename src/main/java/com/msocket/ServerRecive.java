package com.msocket;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

import com.fh.CInfo;

public class ServerRecive extends Thread {
	/* Server上的客户端socket接收Client过来的数据
	 * 
	 */
	private ObjectInputStream ois = null;
	private Socket socket = null;
	private CInfo obj = null;
	
	private List<fhSocket> sockets = null;
	
	public ServerRecive(List<fhSocket> sockets){
		this.sockets = sockets;
	}
	
	public void getOis(Socket s){
		try{
			this.ois = new ObjectInputStream(s.getInputStream());
			this.socket = s;
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
	
	public ObjectInputStream getOis2(){
		return this.ois;
	}
	
	@Override
	public void run() {
		/* 接收client通过socket向server传输的数据
		 * 并解析数据，作出相应的处理
		 * 数据转发：对应client的recive自己去查找别的socket进行转发。
		 */
		try{
			while(true){
				obj = (CInfo)this.ois.readObject();//线程阻塞在此
				//System.out.println("客户端"+this.socket.getPort()+"说："+obj.getInfo());
				if(obj.getDisPort().equals("8081")){
					//发给server的数据，不用转发
					System.out.println("客户端"+this.socket.getPort()+"说："+obj.getInfo());
				}else{
					//不发给server的数据，进行转发(在此可以实现客户端之间互相单聊，或群聊)
					//其实，为了程序的通用性，可以做成群聊，这样当disPort中只有一个时就是单聊
					System.out.println("客户端"+obj.getSrcPort()+"向"+obj.getDisPort()+"说："+obj.getInfo());
					String disPorts[] = obj.getDisPort().split(",");//支持Client和Client单聊和群发
					for(String dpt : disPorts){
						for(fhSocket skt : this.sockets){
							if(dpt.equals(String.valueOf(skt.getSocket().getPort()))){
								ObjectOutputStream oos = skt.getSs().getOos2();
								oos.writeObject(obj);
								oos.flush();
								break;
							}
						}
					}
				}
			}
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
}
