package com.simpleaudiosocket;

import java.io.DataInputStream;
import java.io.DataOutputStream;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

public class ClientAudioReceiver extends Thread {

	private AudioFormat af = null;
	private SourceDataLine sdl = null;
	
	private DataInputStream dis;
	private DataOutputStream dos;

	public static boolean stopflag = false;
	public static boolean exitflag = false;
	
	public ClientAudioReceiver(AudioFormat af, DataInputStream dis, DataOutputStream dos) throws LineUnavailableException{
		this.af = af;
		DataLine.Info dataLineInfo = new DataLine.Info(SourceDataLine.class, af);
		sdl = (SourceDataLine) AudioSystem.getLine(dataLineInfo);
		
		this.dis = dis;
		this.dos = dos;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		byte[] buf = new byte[10000];
		int cnt = 0;
		try{
			sdl.open(af);
			System.out.println("Client receiver open() success.");
			sdl.start();
			System.out.println("Client receiver start() success.");
			
			while((cnt=dis.read(buf, 0, buf.length))>=1){
				if(stopflag != true && cnt > 0){
					sdl.write(buf, 0, cnt);//write the audio data into device to play;
				}
				if(exitflag==true) break;
			}
			
			System.out.println("Client receiver exit success.");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
}
