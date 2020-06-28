package com.simpleaudiosocket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.TargetDataLine;

public class ClientAudioSender extends Thread {
	private AudioFormat af = null;
	private TargetDataLine tdl;
	
	private DataInputStream dis;
	private DataOutputStream dos;
	
	public static boolean stopflag = false;
	public static boolean exitflag = false;
	
	public ClientAudioSender(AudioFormat af, DataInputStream dis, DataOutputStream dos) throws LineUnavailableException{
		this.af = af;
		DataLine.Info dataLineInfo = new DataLine.Info(TargetDataLine.class, af);
		this.tdl = (TargetDataLine) AudioSystem.getLine(dataLineInfo);
		
		this.dis = dis;
		this.dos = dos;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try{
			tdl.open(af);
			System.out.println("Client sender open() success.");
			tdl.start();
			System.out.println("Client sender start() success.");
			
			byte[] buf = new byte[10000];
			int cnt = 0;
			while((cnt = tdl.read(buf, 0, buf.length))>=1 ){
				if(stopflag != true && cnt > 0){
					dos.write(buf, 0, cnt);
					System.out.println("Client said things - "+cnt);
				}
				if(exitflag==true) break;
			}
			
			System.out.println("Client sender exit success.");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
}
