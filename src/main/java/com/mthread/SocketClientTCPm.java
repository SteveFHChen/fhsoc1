package com.mthread;

import java.net.Socket;

public class SocketClientTCPm {

	/**
	 * java -cp ./fhsocket1-0.0.1-SNAPSHOT.jar com.mthread.SocketClientTCPm 47.106.111.210
	 * @param args
	 */
	public static void main(String[] args) {
		try{
			String serverIP = "127.0.0.1";
			//serverIP = "47.106.111.210";
			if(args.length>=1){
				serverIP=args[0];
			}
			Socket socket=new Socket(serverIP,8082);//立即连接服务端，如果服务端连接不上就直接报错，退出程序
			System.out.println("我"+socket.getLocalPort()+"已成功连接上服务器"+socket.getPort());
			
			ClientSend cs1 = new ClientSend(socket);//注意这两行的顺序，ClientSend()必须放在ClientRecive()之前，这是因为管收阻塞导致的。
			ClientRecive cr1 = new ClientRecive(socket);
			
			cr1.start();
			cs1.start();
			
			while(true){
				Thread.sleep(1000);
			}
			
		}catch(Exception e){
			System.out.println(e.getMessage());
		}finally{
			System.out.println("主线程执行finally块。");
			//发现main()数据从上向下执行完毕，查子线程还在执行。那，主线程是否已经结速退出，子线程是否无需主线程存在而单独运行？
			//如何在client端断开socket连接，并释放所有的资源？
		}
	}

}
