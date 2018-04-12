package com.hd.api.server.service;

import com.hd.api.server.entity.WebServiceNorth;

/**
 * 北向接口日志服务层
 * @version	0.0.1
 * @date	2014-12-4 上午11:16:02
 */
public interface WebServiceNorthService {

	/**
	 * 添加日志
	 * @param webServcieLogInfo
	 */
	public void addWebServiceLog(WebServiceNorth webServcieLogInfo);
	
}
