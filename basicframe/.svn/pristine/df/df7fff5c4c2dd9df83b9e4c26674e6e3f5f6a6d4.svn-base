package com.hd.sfw.webservice.client;


import java.util.Properties;

import javax.xml.namespace.QName;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.apache.cxf.transport.http.HTTPConduit;
import org.apache.cxf.transports.http.configuration.HTTPClientPolicy;

import com.hd.sfw.webservice.WebServiceClient;
import com.hd.sfw.webservice.model.WebServiceConfigure;

/**
 * cxf JaxWsDynamicClientFactory动态代理工厂 调用方式
 */
public class CXFClient implements WebServiceClient {
	
	/**
	 * 获取调用客户端
	 * @param configure 接口配置 
	 * @return
	 */
	private Client getClient(WebServiceConfigure configure) {
		// 解决包冲突
		Properties props = System.getProperties();
		props.setProperty("org.apache.cxf.stax.allowInsecureParser", "1");
		props.setProperty("UseSunHttpHandler", "true");
		JaxWsDynamicClientFactory clientFactory = JaxWsDynamicClientFactory.newInstance();
		Client client = clientFactory.createClient(configure.getUrl());
		HTTPConduit conduit = (HTTPConduit) client.getConduit();
		HTTPClientPolicy policy = new HTTPClientPolicy();
		policy.setConnectionTimeout(configure.getTimeout());
		policy.setReceiveTimeout(configure.getTimeout());
		conduit.setClient(policy);
		return client;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <T> T call(WebServiceConfigure configure,Object[] args,Class<T> responseType) throws Exception{
		Client client = getClient(configure);
		//返回参数类型
	    Class<?>[] classes = responseType==null?new Class[]{}:new Class[] {responseType};
		QName opAddEntry = new QName(configure.getTargetNamespace(), configure.getMethodName());
		Object request = args==null||args.length==0?null:args[0];
		return (T)client.invoke(opAddEntry, request, classes)[0];
	    
	}
	
}
