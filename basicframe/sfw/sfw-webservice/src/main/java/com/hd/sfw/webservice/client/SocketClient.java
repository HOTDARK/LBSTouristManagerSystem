/*
 * Copyright © 2014, Wangding (Chengdu) Tech Co.,Ltd. 
 * All right reserved.
 */
package com.hd.sfw.webservice.client;

import java.io.IOException;

import org.apache.log4j.Logger;

import com.hd.sfw.webservice.WebServiceClient;
import com.hd.sfw.webservice.model.WebServiceConfigure;
import com.hd.sfw.webservice.utils.SocketChannelInit;
import com.hd.sfw.webservice.utils.SocketOrdinaryInit;

/**
 * socket方式返回的接口报文
 * 
 * @version 0.0.1
 * @author <a href="mailto:likl@iwangding.com">李坤林</a>
 * @date 2014-11-17 下午3:29:07
 */
public class SocketClient implements WebServiceClient {
	
	Logger logger=Logger.getLogger(SocketClient.class);

 

	@SuppressWarnings("unchecked")
	@Override
	public <T> T call(WebServiceConfigure configure, Object[] args,
			Class<T> responseType) throws Exception {
		if (configure.getSocketType()!=null) {
			if (configure.getSocketType().equals(WebServiceConfigure.socketOrdinary)) {
				SocketOrdinaryInit socketInit = new SocketOrdinaryInit(configure);
				String	msg = socketInit.getMsg(args[0].toString());
				return (T) msg;
			}else{
				SocketChannelInit socketInit = new SocketChannelInit(configure);
				String msg = null;
				try {
					msg = socketInit.getMsg(args[0].toString());
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					try {
						socketInit.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				return (T) msg;
			}
		}else{
			return null;
		}
		
	}

 

}


