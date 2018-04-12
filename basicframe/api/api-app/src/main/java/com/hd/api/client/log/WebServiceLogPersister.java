/*
 * Copyright © 2014, Wangding (Chengdu) Tech Co.,Ltd. 
 * All right reserved.
 */
package com.hd.api.client.log;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hd.api.client.dao.WebServiceLogMapper;
import com.hd.sfw.log.trace.LogPersister;
import com.hd.sfw.log.trace.model.SysLogOper;
import com.hd.sfw.webservice.model.WebServiceLog;

/**
 * webservice日志持久化
 */
@Component
public class WebServiceLogPersister implements LogPersister {
	
	@Autowired
	private WebServiceLogMapper webServiceLogMapper;

	@Override
	public boolean doPersistence(SysLogOper log) {
		if(log instanceof WebServiceLog){
			webServiceLogMapper.insertWebServiceLog((WebServiceLog)log);
			return false;
		}
		return true;
	}

}
