/*
 * Copyright © 2014, Wangding (Chengdu) Tech Co.,Ltd. 
 * All right reserved.
 */
package com.hd.sfw.webservice;

import com.hd.sfw.webservice.model.WebServiceConfigure;

/**
 * webservice 客户端接口
 * @version	0.0.1
 * @author	<a href="mailto:sunjian@iwangding.com">孙剑</a>
 * @date	2014-9-10 上午11:24:20
 */
public interface WebServiceClient{
	
	/**
	 * 只需要传入webservice的配置信息和入参即可
	 * @param configure webservice配置信息，此参数一定非空
	 * @param args 入参
	 * @param responseType 返回类型
	 * @return 
	 * @throws Exception
	 */
	public <T> T call(WebServiceConfigure configure,Object[] args,Class<T> responseType) throws Exception;
	
}
