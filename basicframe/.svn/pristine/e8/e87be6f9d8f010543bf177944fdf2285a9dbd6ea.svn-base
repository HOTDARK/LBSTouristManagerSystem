/*
 * Copyright © 2014, Wangding (Chengdu) Tech Co.,Ltd. 
 * All right reserved.
 */
package com.hd.sfw.webservice.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

import org.apache.commons.lang.StringUtils;

import com.hd.sfw.webservice.model.WebServiceConfigure;

/**
 * 
 * @author zwr
 *
 */
public class SocketOrdinaryInit {

	private String host;
	private Integer port;
	private BufferedReader br=null;
	private InputStream is=null;
	private PrintWriter pw=null;
	private OutputStream os=null;
	private Socket socket =null;

	public SocketOrdinaryInit(WebServiceConfigure configure) {
		if(configure!=null && StringUtils.isNotEmpty(configure.getUrl())){
			this.host = getHost(configure.getUrl());
			this.port = getPost(configure.getUrl());
		}
	}

	/**
	 * 
	 * @param url
	 * @return
	 */
	private String getHost(String url) {
		if (!url.isEmpty()) {
			return url.split(":") != null && url.split(":")[1] != null
					&& url.split(":")[1].split("//") != null
					&& url.split(":")[1].split("//")[1] != null ? url
					.split(":")[1].split("//")[1] : null;
		} else {
			return null;
		}
	}

	/**
	 * 
	 * @param url
	 * @return
	 */
	private int getPost(String url) {
		if (!url.isEmpty()) {
			return url.split(":") != null && url.split(":")[2] != null ? Integer
					.parseInt(url.split(":")[2]) : 0;
		} else {
			return 0;
		}
	}

 
 

	/**
	 * 获取查询信息
	 */
	public String getMsg(String info)  {
		  //接收服务器的相应  
		String result=null;
		try {
			  socket =new Socket(host,port);  
			  os=socket.getOutputStream();  
			  pw=new PrintWriter(os);  
			  is=socket.getInputStream();  
			  br=new BufferedReader(new InputStreamReader(is,"GBK"));  
			  pw.println(info);  
			  pw.flush();  
			  socket.shutdownOutput();  
			  String reply=null;  
			  while(!((reply=br.readLine())==null)){
				 result=reply;
			  }
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
	    	 try {
				 br.close();  
				 is.close();  
				 pw.close();  
				 os.close();  
				 socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}  
		}
		 return result;
	}

	/**
	 * @return the host
	 */
	public String getHost() {
		return host;
	}

	/**
	 * @param host
	 *            the host to set
	 */
	public void setHost(String host) {
		this.host = host;
	}

	/**
	 * @return the port
	 */
	public Integer getPort() {
		return port;
	}

	/**
	 * @param port
	 *            the port to set
	 */
	public void setPort(Integer port) {
		this.port = port;
	}
 
 
}
