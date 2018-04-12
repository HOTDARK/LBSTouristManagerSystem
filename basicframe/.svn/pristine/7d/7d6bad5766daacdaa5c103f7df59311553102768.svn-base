/*
 * Copyright © 2014, Wangding (Chengdu) Tech Co.,Ltd. 
 * All right reserved.
 */
package com.hd.api.client.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hd.api.client.dao.WebServiceLogMapper;
import com.hd.api.client.entity.SysWebServiceLog;
import com.hd.api.client.service.WebServiceLogService;
import com.hd.sfw.core.model.Pagination;

/**
 * webservice日志实现
 * @version	0.0.1
 * @author	<a href="mailto:sunjian@iwangding.com">孙剑</a>
 * @date	2014-11-12 上午10:28:20
 */
@Service
public class WebServiceLogServiceImpl implements WebServiceLogService{
	
	@Autowired
	private WebServiceLogMapper webServiceLogMapper;
	
	@Override
	public Pagination<SysWebServiceLog> getDataList(SysWebServiceLog webServiceLog) {
		if (webServiceLog==null) {
			throw new IllegalArgumentException("conditon不能为空");
		}
		Integer count = webServiceLogMapper.findNumByCondition(webServiceLog);
		List<SysWebServiceLog> rowList = new ArrayList<SysWebServiceLog>();
		if(count>0){
			rowList = webServiceLogMapper.findByPage(webServiceLog, webServiceLog.getiDisplayStart(), webServiceLog.getiDisplayLength());
		}
		Pagination<SysWebServiceLog> page = new Pagination<SysWebServiceLog>(count, rowList);
		return page;
	}



	@Override
	public int getTotalCount(SysWebServiceLog condition) {
		return webServiceLogMapper.findNumByCondition(condition);
	}

	@Override
	public SysWebServiceLog findById(int id) {
		return webServiceLogMapper.findByPK((long) id);
	}

}
