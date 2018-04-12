package com.hd.api.server.variable;

import java.util.Properties;

/**
 * webservice服务端管理器
 * @author somnuscy
 *
 */
public class CXFServer {
	
	/**
	 * 初始化[服务器端]环境变量，解决某些系统环境以下变量不存在时会出现：Cannot create a secure XMLInputFactory
	 */
	public void load(){
		// 解决包冲突
		Properties props = System.getProperties();
		props.setProperty("org.apache.cxf.stax.allowInsecureParser", "1");
		props.setProperty("UseSunHttpHandler", "true");
	}
}
