package com.fh;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Person /*extends UnicastRemoteObject*/{
	private static final long serialVersionUID = 7538984519610769946L;
	/*protected Person() throws RemoteException {
		super();
	}*/
	private String name;
	private String msg;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
}
