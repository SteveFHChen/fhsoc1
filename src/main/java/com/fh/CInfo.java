package com.fh;

import java.io.Serializable;

public class CInfo implements Serializable{
	private static final long serialVersionUID = -805973080767191348L;

	private String cmdType;
	private String srcPort;
	private String disPort;
	private String info;
	
	public String getCmdType() {
		return cmdType;
	}
	public void setCmdType(String cmdType) {
		this.cmdType = cmdType;
	}
	public String getSrcPort() {
		return srcPort;
	}
	public void setSrcPort(String srcPort) {
		this.srcPort = srcPort;
	}
	public String getDisPort() {
		return disPort;
	}
	public void setDisPort(String disPort) {
		this.disPort = disPort;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	@Override
	public String toString() {
		return "cmdType:"+this.cmdType+";disPort:"+this.disPort+";info:"+this.info;
	}
	
}