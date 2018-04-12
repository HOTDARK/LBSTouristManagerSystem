/*
 * Copyright © 2014, Wangding (Chengdu) Tech Co.,Ltd. 
 * All right reserved.
 */
package com.hd.sfw.webservice;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;

/**
 * TODO 类描述
 * 
 * @version 0.0.1
 * @author <a href="mailto:sunjian@iwangding.com">孙剑</a>
 * @date 2014-8-26 上午11:24:59
 */
public class Test {
	public static void main(String[] args) throws Exception {
		JaxWsDynamicClientFactory factory = JaxWsDynamicClientFactory.newInstance();
		Client client = factory.createClient("http://localhost:8080/bsa-center/ws/HelloWorld?wsdl");
		
		String operation = "getUser";
		
//		Endpoint endpoint = client.getEndpoint();  
//		QName opName = new QName(endpoint.getService().getName().getNamespaceURI(), operation);  
//		BindingInfo bindingInfo = endpoint.getEndpointInfo().getBinding();  
//		if (bindingInfo.getOperation(opName) == null) {  
//		    for (BindingOperationInfo operationInfo : bindingInfo.getOperations()) {  
//		        if (operation.equals(operationInfo.getName().getLocalPart())) {  
//		            opName = operationInfo.getName();  
//		            break;  
//		        }  
//		    }  
//		}  
		  
		Object[] res = client.invoke(operation, 1);  
		
		System.out.println(ToStringBuilder.reflectionToString(res[0]));
	}
}
