package com.msocket;

import java.net.Socket;

public class fhSocket {
	private Socket socket;
	private ServerRecive sr;
	private ServerSend ss;
	public Socket getSocket() {
		return socket;
	}
	public void setSocket(Socket socket) {
		this.socket = socket;
	}
	public ServerRecive getSr() {
		return sr;
	}
	public void setSr(ServerRecive sr) {
		this.sr = sr;
	}
	public ServerSend getSs() {
		return ss;
	}
	public void setSs(ServerSend ss) {
		this.ss = ss;
	}
}
