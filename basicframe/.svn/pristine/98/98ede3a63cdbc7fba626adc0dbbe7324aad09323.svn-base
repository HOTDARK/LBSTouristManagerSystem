/*
 * Copyright © 2014, Wangding (Chengdu) Tech Co.,Ltd. 
 * All right reserved.
 */
package com.hd.sfw.core.support.jmx;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.management.JMException;

import org.springframework.core.io.Resource;
import org.springframework.jmx.support.ConnectorServerFactoryBean;

/**
 * 针对授权进行特殊处理的ConnectorServerFactoryBean
 * @version	0.0.1
 * @author	<a href="mailto:sunjian@iwangding.com">孙剑</a>
 * @date	2014-12-5 下午3:01:45
 */
public class AuthConnectorServerFactoryBean extends ConnectorServerFactoryBean{
	
	//jmx授权的access文件路径
	private Resource accessFile;
	
	//jmx授权的密码文件路径
	private Resource passwdFile;

	/**
	 * 设置access文件
	 * @param accessFile
	 */
	public void setAccessFile(Resource accessFile) {
		this.accessFile = accessFile;
	}

	/**
	 * 设置密码文件
	 * @param passwdFile
	 */
	public void setPasswdFile(Resource passwdFile) {
		this.passwdFile = passwdFile;
	}
	
	@Override
	public void afterPropertiesSet() throws JMException, IOException {
		Map<String, String> map = new HashMap<String, String>();
		if(accessFile!=null){
			map.put("jmx.remote.x.access.file", accessFile.getFile().getAbsolutePath());	
		}
		if(passwdFile!=null){
			map.put("jmx.remote.x.password.file", passwdFile.getFile().getAbsolutePath());	
		}
		
		this.setEnvironmentMap(map);
		
		super.afterPropertiesSet();
	}
}
