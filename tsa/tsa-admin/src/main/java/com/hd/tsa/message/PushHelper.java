/*
 * Copyright © 2014, Wangding (Chengdu) Tech Co.,Ltd. 
 * All right reserved.
 */
package com.hd.tsa.message;

import java.util.UUID;

import flex.messaging.MessageBroker;
import flex.messaging.messages.AsyncMessage;

/**
 * 推送消息
 * @version	0.0.1
 */
public class PushHelper {
	
	private static MessageBroker msgBroker;
	
	/**
	 * 发送数据到flex message
	 * @param clientId 客户端id
	 * @param key
	 * @param value
	 */
	public static void push(String clientId,String key,Object value){
		//构建Message
		AsyncMessage msg = new AsyncMessage();
		msg.setDestination("comet");
		msg.setMessageId(UUID.randomUUID().toString());
		msg.setTimestamp(System.currentTimeMillis());
		//填充数据
		msg.setBody(new Atom(key, value));
		
		//通过id区分
		msg.setHeader("DSSubtopic", clientId);
		
		if(msgBroker==null){
			msgBroker = MessageBroker.getMessageBroker("_messageBroker");
		}
		msgBroker.routeMessageToService(msg, null);
	}
	
	public static class Atom{
		private String key;
		
		private Object value;
		
		public Atom() {
		}
		
		public Atom(String key, Object value) {
			super();
			this.key = key;
			this.value = value;
		}

		public String getKey() {
			return key;
		}

		public void setKey(String key) {
			this.key = key;
		}

		public Object getValue() {
			return value;
		}

		public void setValue(Object value) {
			this.value = value;
		}
	}
	
}
