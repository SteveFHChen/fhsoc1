package com.simplesocket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

public class Client {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String line = null;
		Socket socket = null;
		InputStream is = null;
		OutputStream os = null;
		try{
			String serverIP = "127.0.0.1";
			//serverIP = "47.106.111.210";
			if(args.length>=1){
				serverIP=args[0];
			}
			
			int port=8083;
			if(args.length>=2){
				port=Integer.parseInt(args[1]);
			}
			socket = new Socket(serverIP,port);//立即连接服务端，如果服务端连接不上就直接报错，退出程序
			System.out.println("I "+socket.getLocalPort()+" connect to "+socket.getPort()+" success!");
			
			BufferedReader sin=new BufferedReader(new InputStreamReader(System.in));
			is = socket.getInputStream();
			os = socket.getOutputStream();
			
			ClientReceiver cr = new ClientReceiver(is, os);
			cr.start();
			
			line=sin.readLine();
			while(!line.equals("bye")){
				os.write(line.getBytes());
				os.write("\r\n".getBytes());
				os.flush();
				line=sin.readLine();
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				os.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				is.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				socket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
