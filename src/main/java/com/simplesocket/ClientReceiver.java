package com.simplesocket;

import java.io.InputStream;
import java.io.OutputStream;

public class ClientReceiver extends Thread {

	private InputStream is;
	private OutputStream os;
	
	public ClientReceiver(InputStream is, OutputStream os){
		this.is = is;
		this.os = os;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		int i;
		try{
			while((i = is.read())!=-1){
				System.out.print((char)i);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
}
