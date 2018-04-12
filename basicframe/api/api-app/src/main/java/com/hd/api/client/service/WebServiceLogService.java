/*
 * Copyright © 2014, Wangding (Chengdu) Tech Co.,Ltd. 
 * All right reserved.
 */
package com.hd.api.client.service;

import com.hd.api.client.entity.SysWebServiceLog;
import com.hd.sfw.core.model.Pagination;

/**
 * webservice日志
 */
public interface WebServiceLogService {

	 
	
	/**
	 * 统计满足条件的记录数
	 * @param condition
	 * @return
	 */
	public int getTotalCount(SysWebServiceLog condition);
	
	/**
	 * 根据id查询
	 * @param id
	 * @return
	 */
	public SysWebServiceLog findById(int id);
	
	public Pagination<SysWebServiceLog> getDataList(SysWebServiceLog webServiceLog);
}
