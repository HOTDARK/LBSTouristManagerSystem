/*
 * Copyright © 2014, Wangding (Chengdu) Tech Co.,Ltd. 
 * All right reserved.
 */
package com.hd.sfw.webservice;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

/**
 * TODO 类描述
 * @version	0.0.1
 * @author	<a href="mailto:sunjian@iwangding.com">孙剑</a>
 * @date	2014-8-26 下午12:07:15
 */
public class Test2 {
	public static void main(String[] args) {
		JaxWsProxyFactoryBean factory=new JaxWsProxyFactoryBean();
        factory.setServiceClass(SysUserService.class);
          
        factory.setAddress("http://localhost:8080/bsa-center/ws/HelloWorld?wsdl");  
        SysUserService helloService=(SysUserService)factory.create();  
        System.out.println(ToStringBuilder.reflectionToString(helloService.findById(1)));  
	}
}
