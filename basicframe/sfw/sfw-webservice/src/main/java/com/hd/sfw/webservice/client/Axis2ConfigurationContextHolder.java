/*
 * Copyright © 2015, Wangding (Chengdu) Tech Co.,Ltd. 
 * All right reserved.
 */
package com.hd.sfw.webservice.client;

import org.apache.axis2.AxisFault;
import org.apache.axis2.context.ConfigurationContext;
import org.apache.axis2.context.ConfigurationContextFactory;
import org.apache.axis2.transport.http.HTTPConstants;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;

/**
 * 持有axis2的ConfigurationContext
 * @version	0.0.1
 * @author	<a href="mailto:sunjian@iwangding.com">孙剑</a>
 * @date	2015-4-8 下午1:29:15
 */
public class Axis2ConfigurationContextHolder {
	public static ConfigurationContext context;
	static{
		try {
			context = ConfigurationContextFactory.createConfigurationContextFromFileSystem(null, null);
			
			HttpConnectionManagerParams params = new HttpConnectionManagerParams();
			
			//检测旧连接
			params.setStaleCheckingEnabled(true);
			//默认单个主机的连接数
			params.setDefaultMaxConnectionsPerHost(100);
			//连接池最大连接数
			params.setMaxTotalConnections(10000);
			
			//socket超时时间
			params.setSoTimeout(2*1000);
			//连接超时时间
			params.setConnectionTimeout(2*1000);
			
			MultiThreadedHttpConnectionManager manager = new MultiThreadedHttpConnectionManager();
			manager.setParams(params);
			
			context.setProperty(HTTPConstants.MULTITHREAD_HTTP_CONNECTION_MANAGER, manager);
			
		} catch (AxisFault e) {
			e.printStackTrace();
		}
	}
}
