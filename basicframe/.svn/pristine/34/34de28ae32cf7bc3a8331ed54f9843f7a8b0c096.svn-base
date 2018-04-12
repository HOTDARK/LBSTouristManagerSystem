/*
 * Copyright © 2014, Wangding (Chengdu) Tech Co.,Ltd. 
 * All right reserved.
 */
package com.hd.sfw.webservice;

import java.util.Map;

import com.hd.sfw.webservice.exception.IwdWebServiceException;
import com.hd.sfw.webservice.model.WebServiceConfigure;

/**
 * webservice管理
 * @version	0.0.1
 * @author	<a href="mailto:sunjian@iwangding.com">孙剑</a>
 * @date	2014-9-10 下午2:42:51
 */
public interface WebServiceManager {
	
	/**
	 * 调用指定的webservice
	 * @param wid webservice标识
	 * @param args 入参
	 * @param responseType 返回类型
	 * @return
	 * @throws IwdWebServiceException
	 */
	public <T> T call(Map<String, Object> parmas,String wid,Object[] args,Class<T> responseType) throws IwdWebServiceException;
	
	/**
	 * 加载webservice配置信息
	 * @author	<a href="mailto:sunjian@iwangding.com">孙剑</a>
	 */
	public void load();
	
	/**
	 * 获取webservice配置信息
	 * @param wid
	 * @return
	 */
	public WebServiceConfigure getConfigure(String wid);
	
}
