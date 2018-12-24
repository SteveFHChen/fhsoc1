package com.webmonitor;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class WebMonitor {
	/* 实验：模拟Web Server端对客户端发来的报文进行截取
	 * 
	 */
	public static void main(String[] args) {
		try {
			ServerSocket server = new ServerSocket(8080);
			Socket client = server.accept();
			InputStream is = client.getInputStream();
			OutputStream os = client.getOutputStream();
			//PrintWriter pw = new PrintWriter(client.getOutputStream());
			
			
			
			/*int i = is.read();
			while(i>0){
				i=is.read();
				System.out.print(i);
			}*/
			
			InputStreamReader isr = new InputStreamReader(is,"utf-8");
			BufferedReader br = new BufferedReader(isr);
			OutputStreamWriter osw = new OutputStreamWriter(os);
			
			System.out.println("采用readLine()读输入数据：");
			System.out.println("TCP/IP报文截取：");
			/*String line = br.readLine();
			while(line!=null){
				if(line!=null){
					System.out.println(line);
				}else{
					System.out.println("读入一个空行");
				}
				line = br.readLine();//有问题，在接收POST请求的BODY时，因为没有\r\n而导致卡在这
			}*/
			
			System.out.println("采用read()读输入数据：");
			System.out.println("TCP/IP报文截取：");
			int c1 = br.read();
			char c2 = (char) c1;
			//System.out.print(c1+",");//直接输出十进制编码
			System.out.print(c2);
			while(c1!=-1){
			//while(true){
				//System.out.print(c1+",");//直接输出十进制编码
				System.out.print(c2);
				c1 = br.read();
				c2 = (char) c1;
			}
			
//			System.out.println("利用read()和readLine()读取并解析请求数据：");
//			System.out.println("Http报文截取与解析：");
//			/*FhHttpRequest req = new FhHttpRequest();
//			String line = br.readLine();
//			System.out.println(line);
//			String[] li1 = line.split(" ");
//			req.setMethod(li1[0].trim());
//			req.setFile(li1[1].trim());
//			req.setProtocol(li1[2].trim());
//			
//			line = br.readLine();
//			System.out.println(line);
//			String[] li2 = line.split(":");
//			req.setIp(li2[1].trim());
//			req.setPort(li2[2].trim());
//			
//			line = br.readLine();
//			while(line!=null && !line.equals("")){
//				System.out.println(line);
//				String[] keyval = line.split(":");
//				if(keyval[0].trim().equals("Content-Length")){
//					req.setContentLength(new Integer(keyval[1].trim()));
//				}
//				line = br.readLine();
//			}
//			
//			for(int i=0; i<req.getContentLength(); i++){
//				char c3 = (char) br.read();
//				System.out.print(c3);
//			}
//			
//			System.out.println("回复请求浏览器，以便自断开连接：");
//			osw.write("HTTP/1.1 200 OK\r\n");
//			osw.write("Content-Length: 15\r\n");//长度写小了只会被读取指定长度的消息体，长度大了会被认为是错误的报文
//			osw.write("\r\n");
//			osw.write("helloWorld!你好");//可以发中文，一个汉字当2个英文字符
//			osw.flush();//断开客户端浏览器的连接。这是HTTP协议的要求。所以HTTP连接叫无状态连接。
//			osw.close();*/
//			
//			/* 问题：
//			 * 为什么通过Ajax Post方式发送数据，消息体要等页面刷新时才被服务器读取？
//			 */
//			client.close();
//			server.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
