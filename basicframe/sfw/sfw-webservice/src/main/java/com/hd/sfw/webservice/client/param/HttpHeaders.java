/*
 * Copyright © 2014, Wangding (Chengdu) Tech Co.,Ltd. 
 * All right reserved.
 */
package com.hd.sfw.webservice.client.param;

import java.util.HashMap;

/**
 * HTTP头
 * @version	0.0.1
 * @author	<a href="mailto:sunjian@iwangding.com">孙剑</a>
 * @date	2014-10-23 下午3:04:05
 */
public class HttpHeaders extends HashMap<String, Object>{
	private static final long serialVersionUID = 1L;

	/**
	 * 添加头
	 * @param name
	 * @param value
	 */
	public void addHeader(String name,Object value){
		this.put(name, value);
	}

	
}
