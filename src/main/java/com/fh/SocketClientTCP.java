package com.fh;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;


public class SocketClientTCP{
	public static void main(String args[]){
		try{
			Socket socket=new Socket("127.0.0.1",8081);//立即连接服务端，如果服务端连接不上就直接报错，退出程序
			BufferedReader sin=new BufferedReader(new InputStreamReader(System.in));//InputStreamReader流将键盘输入传到内存的一块缓冲区中
			//PrintWriter os=new PrintWriter(socket.getOutputStream());
			//BufferedReader is=new BufferedReader(new InputStreamReader(socket.getInputStream()));//InputStreamReader流将socket输入传到内存的一块缓冲区中
			
			OutputStream os1 = socket.getOutputStream();
			ObjectOutputStream os = new ObjectOutputStream(os1);
			InputStream is2 = socket.getInputStream();
			ObjectInputStream is = new ObjectInputStream(is2);//此处会等待，当Server端执行了ObjectOutputStream os = new ObjectOutputStream(socket.getOutputStream());后才继续向下执行
				//因为建立一个流需要是Server和Client共同完成的
				//即先由一端通过OutputStream os1 = socket.getOutputStream()拿到OutputStream，并通过new xxxOutputStream(os1)创建了输入流后，另一端才能执行new ObjectInputStream(is2);；
				//即：只有一端选创建输出流，另一端才能创建相应的输入流。
			CInfo obj1 = new CInfo();
			obj1.setInfo("我上线了!");
			os.writeObject(obj1);
			os.flush();
			
			CInfo obj2 = null;
			
			String readline;
			readline=sin.readLine();//等待接收从键盘传过来的东西，程序会阻塞在这，直到收到回车键或对象
			
			while(!readline.equals("bye")){
				obj1 = new CInfo();//必须重新new
				obj1.setInfo(readline);
				os.writeObject(obj1);
				os.flush();
				
				//System.out.println("客户端："+readline);
				obj2 = (CInfo)is.readObject();//等待接收从socket传过来的东西，程序会阻塞在这，直到收到回车键或对象
				System.out.println("服务端："+obj2.getInfo());
				System.out.print("我(客户端)说：");
				readline=sin.readLine();//等待接收从键盘传过来的东西，程序会阻塞在这，直到收到回车键
			}
			
			/*
			 * server都会在obj2 = (CInfo)is.readObject();处等待接收到对象后再向后执行
			 * 当client从socket输入一个object时，如果object没有重新new，则即便是修改了属性，可在server端接收到的依然是修改前的object
			 * 因此，每次发信息时，都必须重新创建Object
			 * 这条规则无论是是client端还是server端都是一样的。
			 */
			
			/*
			 * 由于ServerSocket.accept()、socket.read()、键盘输入这3处地方都对程序有阻塞，导致聊天记录先后顺序不正常，
			 * 为此，解决的办法就是给每个阻塞创建一个单独的线程，从而不会互相阻塞。
			 */
			
			os.close();
			is.close();
			socket.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}