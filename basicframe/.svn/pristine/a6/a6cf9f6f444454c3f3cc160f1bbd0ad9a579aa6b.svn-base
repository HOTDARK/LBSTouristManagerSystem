/*
 * Copyright © 2014, Wangding (Chengdu) Tech Co.,Ltd. 
 * All right reserved.
 */
package com.hd.sfw.webservice.utils;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

import com.hd.sfw.webservice.model.WebServiceConfigure;

/**
 * 建立Socket连接,获取查询数据
 * 
 * @version 0.0.1
 * @author <a href="mailto:likl@iwangding.com">李坤林</a>
 * @date 2014-11-24 下午12:09:34
 */
public class SocketChannelInit {

	private String host;
	private Integer port;
	private Set<SelectionKey> selectionKeys = null;
	private int BLOCK = 2048;
	private ByteBuffer sendBuffer = ByteBuffer.allocate(BLOCK);
	private ByteBuffer receiveBuffer = ByteBuffer.allocate(BLOCK);
	private InetSocketAddress SERVER_ADDRESS;

	private SocketChannel clientChannel = null;
	private Selector selector = null;
	private String receiveText;
	private int count = 0;
	private long timeout;

	public SocketChannelInit(WebServiceConfigure configure) {
		if(configure!=null && StringUtils.isNotEmpty(configure.getUrl())){
			timeout = configure.getTimeout();
			this.host = getHost(configure.getUrl());
			this.port = getPost(configure.getUrl());
		}
		init();
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
	 * 初始化
	 */
	public void init() {
		try {
			clientChannel = SocketChannel.open();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			clientChannel.configureBlocking(false);
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			selector = Selector.open();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			clientChannel.register(selector, SelectionKey.OP_CONNECT);
		} catch (ClosedChannelException e) {
			e.printStackTrace();
		}
		try {
			SERVER_ADDRESS = new InetSocketAddress(host, port);
			clientChannel.connect(SERVER_ADDRESS);
			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	/**
	 * 关闭连接
	 * @throws IOException
	 */
	public void close() throws IOException{
		if(selector.isOpen()){
			selector.close();
		}
		if(clientChannel.isOpen()){
			clientChannel.close();
		}
	}

	/**
	 * 获取查询信息
	 */
	public String getMsg(String msg) throws IOException {
		SocketChannel socketChannel;
		boolean isExit = true;
		if (!selector.isOpen()) {
			init();
		}
		//延迟设置
		long timeTemp = System.currentTimeMillis();
		while (isExit) {
			int n = selector.select(3000);
			if(timeout<(System.currentTimeMillis()-timeTemp)){
				break;
			}
			if(n == 0){
				continue;
			}
			selectionKeys = selector.selectedKeys();
			for (SelectionKey selectionKey : selectionKeys) {
				if (selectionKey.isConnectable()) {
					socketChannel = (SocketChannel) selectionKey.channel(); //
					if (socketChannel.isConnectionPending()) {
						socketChannel.finishConnect();
						sendBuffer.clear();
						sendBuffer.put(msg.getBytes());
						sendBuffer.flip();
						socketChannel.write(sendBuffer);
					}
					socketChannel.register(selector, SelectionKey.OP_READ);
				} else if (selectionKey.isReadable()) {
					socketChannel = (SocketChannel) selectionKey.channel();
					receiveBuffer.clear();
					count = socketChannel.read(receiveBuffer);
					if (count > 0) {
						receiveText = new String(receiveBuffer.array(), 0, count, "GBK");
						if (!receiveText.isEmpty()) {
							isExit = false;
							break;
						}
						receiveBuffer.flip();
						socketChannel.close();
					}
				} else if (selectionKey.isWritable()) {
					sendBuffer.clear();
					socketChannel = (SocketChannel) selectionKey.channel();
					sendBuffer.put(msg.getBytes());
					sendBuffer.flip();
					socketChannel.write(sendBuffer);
					socketChannel.register(selector, SelectionKey.OP_READ);
				}
			}
			selectionKeys.clear();
		}
		return receiveText;
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

	/**
	 * @return the clientChannel
	 */
	public SocketChannel getClientChannel() {
		return clientChannel;
	}

	/**
	 * @param clientChannel
	 *            the clientChannel to set
	 */
	public void setClientChannel(SocketChannel clientChannel) {
		this.clientChannel = clientChannel;
	}

	/**
	 * @return the receiveText
	 */
	public String getReceiveText() {
		return receiveText;
	}

	/**
	 * @param receiveText
	 *            the receiveText to set
	 */
	public void setReceiveText(String receiveText) {
		this.receiveText = receiveText;
	}
}
