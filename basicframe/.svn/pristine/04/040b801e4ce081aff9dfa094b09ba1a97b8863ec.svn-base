package com.hd.sfw.webservice.client;


import javax.xml.namespace.QName;

import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMFactory;
import org.apache.axis2.AxisFault;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.rpc.client.RPCServiceClient;

import com.hd.sfw.webservice.WebServiceClient;
import com.hd.sfw.webservice.model.WebServiceConfigure;
import com.hd.sfw.webservice.model.enums.AuthType;

/**
 * axis2 RPC调用方式
 * @version	0.0.1
 * @author	<a href="mailto:sunjian@iwangding.com">孙剑</a>
 * @date	2014-9-10 下午3:14:32
 */
public class Axis2Client implements WebServiceClient {
	
	/**
	 * 获取RPCServiceClient
	 * @param configure
	 * @return
	 * @throws AxisFault
	 */
	private RPCServiceClient getClient(WebServiceConfigure configure) throws AxisFault{
		RPCServiceClient serviceClient = new RPCServiceClient(Axis2ConfigurationContextHolder.context,null);

	    //判断是否添加授权头
	    if(configure.getAuth()==AuthType.SOAP_HEADER){
	    	serviceClient.addHeader(getAuthenticationHeader(configure));	
	    }
	    
	    Options options = serviceClient.getOptions();
	    options.setCallTransportCleanup(true);
	    
	    //设置soapaction
	    options.setAction(configure.getSoapAction());
	    //调用的webservice地址
	    options.setTo(new EndpointReference(configure.getUrl()));
	    //设置超时时间
	    options.setTimeOutInMilliSeconds(configure.getTimeout());
	    
	    return serviceClient;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <T> T call(WebServiceConfigure configure,Object[] args,Class<T> responseType) throws Exception{
		
		RPCServiceClient serviceClient = getClient(configure);
		
		//返回参数类型
	    Class<?>[] classes = responseType==null?new Class[]{}:new Class[] {responseType};
	    QName opAddEntry = new QName(configure.getTargetNamespace(), configure.getMethodName());
	    
	    return (T)serviceClient.invokeBlocking(opAddEntry, args, classes)[0];
	    
	}
	
	/**
	 * 构造授权头
	 * @param configure
	 * @return
	 */
	private OMElement getAuthenticationHeader(WebServiceConfigure configure){
	    OMFactory factory = OMAbstractFactory.getOMFactory();
//	    OMNamespace namespace = factory.createOMNamespace(configure.getTargetNamespace(), "nsl");
	    OMElement header = factory.createOMElement("AuthenticationToken", null);
	    OMElement user = factory.createOMElement("Username", null);
	    OMElement pass = factory.createOMElement("Password", null);
	      
	    user.setText("admin");
	    pass.setText("admin");
	      
	    header.addChild(user);
	    header.addChild(pass);
	    
	    return header;
	}
	
}
