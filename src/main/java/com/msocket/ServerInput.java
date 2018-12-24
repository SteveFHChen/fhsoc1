package com.msocket;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

import com.fh.CInfo;

public class ServerInput extends Thread {
	/* Server端接收键盘输入
	 * 
	 */
	private BufferedReader sin=new BufferedReader(new InputStreamReader(System.in));
	private String line = null;
	private String ss[] = null;
	private CInfo cmd = null;
	
	private List<fhSocket> sockets = null;
	
	public ServerInput(List<fhSocket> sockets){
		this.sockets = sockets;
	}

	@Override
	public void run() {
		try{
		cmd = new CInfo();
		cmd.setCmdType("AA");
		while(!cmd.equals("END")){
			System.out.print("请输入命令：");
			line = sin.readLine();
			/* 解析命令，并做出相应的操作
			 * 命令格式：type#port#message
			 */
			ss = line.split("#");
			if( ss.length >=3 ){
				cmd = new CInfo();
				cmd.setCmdType(ss[0].trim());
				cmd.setDisPort(ss[1].trim());
				cmd.setSrcPort("8082");
				cmd.setInfo(ss[2].trim());
				//System.out.println("命令对象："+cmd);
				
				if(cmd.getCmdType().equals("MSG")){
					String disPorts[] = cmd.getDisPort().split(",");
					for(String dpt : disPorts){//支持Server与Client单聊和群发
						for(fhSocket skt : sockets){
							if(dpt.equals(String.valueOf(skt.getSocket().getPort()))){
								skt.getSs().getOos2().writeObject(cmd);
								skt.getSs().getOos2().flush();
								break;
							}
						}
					}
				}
			}else{
				System.out.println("指令格式错误，请重新输入！");
			}
		}
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
}
