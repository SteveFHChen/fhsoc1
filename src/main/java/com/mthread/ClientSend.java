package com.mthread;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.Socket;

import com.fh.CInfo;

public class ClientSend extends Thread {
	private Socket socket;
	private BufferedReader sin=new BufferedReader(new InputStreamReader(System.in));//InputStreamReader流将键盘输入传到内存的一块缓冲区中
	private ObjectOutputStream oos = null;
	private CInfo obj1 = null;
	private String cmd = null;
	private String ss[];
	
	public ClientSend(Socket socket){
		try {
			this.socket = socket;
			this.oos = new ObjectOutputStream(this.socket.getOutputStream());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		/*	接收键盘输入的数据，并封装成对象从socket发送出去
		 * 
		 */
		try{
			obj1 = new CInfo();
			obj1.setCmdType("MSG");
			obj1.setSrcPort(String.valueOf(this.socket.getLocalPort()));
			obj1.setDisPort(String.valueOf(this.socket.getPort()));
			obj1.setInfo("我上线了");
			this.oos.writeObject(obj1);
			this.oos.flush();
			
			while(true){
				System.out.print("请输入命令：");
				cmd = sin.readLine();
				if(cmd.equals("bye")) break;
				ss = cmd.split("#");
				if(ss.length >= 3 ){
					obj1 = new CInfo();
					obj1.setCmdType(ss[0]);
					obj1.setSrcPort(String.valueOf(this.socket.getLocalPort()));
					obj1.setDisPort(ss[1]);
					obj1.setInfo(ss[2]);
					this.oos.writeObject(obj1);
					this.oos.flush();
				}else{
					System.out.println("指命格式错误，请重新输入！");
				}
			}
			
		}catch(Exception e){
			System.out.println(e.getMessage());
		}finally{
			//在Client端进行连接关闭，是这样写吗？注意：Server端是否也把对应的socket和流等资源关闭变释放。
			try {
				this.socket.shutdownOutput();
				this.socket.shutdownInput();
				
				this.oos.close();
				this.oos=null;
				
				this.socket.close();
				this.socket = null;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
