package com.hd.api.client.log;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hd.api.client.dao.LogOperMapper;
import com.hd.sfw.log.trace.LogPersister;
import com.hd.sfw.log.trace.model.SysLogOper;

/**
 * 系统操作日志持久化
 */
@Component
public class LogOperPersister implements LogPersister {
	
	@Autowired
	private LogOperMapper logOperMapper;
	
	@Override
	public boolean doPersistence(SysLogOper log) {
		logOperMapper.insert(log);
		return true;
	}

}
