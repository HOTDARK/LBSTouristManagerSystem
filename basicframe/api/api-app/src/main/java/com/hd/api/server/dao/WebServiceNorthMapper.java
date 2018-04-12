package com.hd.api.server.dao;

import com.hd.api.server.entity.WebServiceNorth;


/**
 * 北向接口日志dao
 */
public interface WebServiceNorthMapper {
	
	/**
	 * 日志入库
	 * @param webServcieLogInfo
	 */
	public void addWebServiceLog(WebServiceNorth webServiceNorth);
	
}
