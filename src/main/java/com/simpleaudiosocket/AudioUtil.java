package com.simpleaudiosocket;

import javax.sound.sampled.AudioFormat;

public class AudioUtil {

	private static AudioFormat af = null;
	
	private AudioUtil(){}
	
	public static AudioFormat getAudioFormantInstance(){
		if(af==null){
			af = getAudioFormat();
		}
		return af;
	}
	
	public static AudioFormat getAudioFormat(){
		AudioFormat.Encoding encoding = AudioFormat.Encoding.PCM_SIGNED;
		float rate = 8000f;//sampleRate: 8000, 11025, 16000, 22050, 44100;
		int sampleSize = 16;//sampleSizeBits: 8, 16;
		String signedString = "signed";
		boolean bigEndian = true;//bigEndian: true, false
		int channels = 1;//channels: 1-single, 2-double
		return new AudioFormat(encoding, rate, sampleSize, channels,
				(sampleSize / 8) * channels, rate, bigEndian);
	}
}
