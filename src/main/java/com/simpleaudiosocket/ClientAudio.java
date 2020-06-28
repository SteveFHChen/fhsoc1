package com.simpleaudiosocket;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.SourceDataLine;

public class ClientAudio {

	public static void recordAudio(SourceDataLine sdl, DataInputStream dis,
			AudioFormat af){
		byte[] buf = new byte[10000];
		int cnt = 0;
		try{
			sdl.open(af);
			sdl.start();
			while((cnt=dis.read(buf, 0, buf.length))>=1){
				if(cnt > 0){
					sdl.write(buf, 0, cnt);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String line = null;
		Socket socket = null;
		InputStream is = null;
		OutputStream os = null;
		try{
			String serverIP = "127.0.0.1";
			serverIP = "47.106.111.210";
			if(args.length>=1){
				serverIP=args[0];
			}
			
			int port=8084;
			if(args.length>=2){
				port=Integer.parseInt(args[1]);
			}
			
			String role = "sender";
			if(args.length>=3){
				role = args[2];
			}
			
			socket = new Socket(serverIP,port);//立即连接服务端，如果服务端连接不上就直接报错，退出程序
			if(socket!=null){
				System.out.println("I "+socket.getLocalPort()+" connect to "+socket.getPort()+" success!");
			}else{
				throw new Exception("Connect to "+serverIP+":"+port+" failed!");
			}
			
			BufferedReader sin=new BufferedReader(new InputStreamReader(System.in));
			is = socket.getInputStream();
			os = socket.getOutputStream();
			
			DataInputStream dis = new DataInputStream(is);
			DataOutputStream dos = new DataOutputStream(os);
			
			AudioFormat af = AudioUtil.getAudioFormantInstance();
			
			//if("receiver".equals(role)){
				ClientAudioReceiver cr = new ClientAudioReceiver(af, dis, dos);
				cr.start();
			//}
			
			//if("sender".equals(role)){
				ClientAudioSender cs = new ClientAudioSender(af, dis, dos);
				cs.start();
			//}
			
			System.out.println("Console input is started.");
			line=sin.readLine();
			while(!line.equals("bye")){
				if(line.equals("sender stop")){
					ClientAudioSender.stopflag = true;
				}else if(line.equals("sender start")){
					ClientAudioSender.stopflag = false;
				}else if(line.equals("receiver stop")){
					ClientAudioReceiver.stopflag = true;
				}else if(line.endsWith("receiver start")){
					ClientAudioReceiver.stopflag = false;
				}else{
					System.out.println("Unknow command: "+line);
				}
				line=sin.readLine();
			}
			
			ClientAudioSender.exitflag = true;
			ClientAudioReceiver.exitflag = true;
			
			Thread.sleep(3000);
			
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
