/*
 * Copyright © 2014, Wangding (Chengdu) Tech Co.,Ltd. 
 * All right reserved.
 */
package com.hd.sys.core.log;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hd.sfw.log.trace.LogPersister;
import com.hd.sfw.log.trace.model.SysLogOper;
import com.hd.sys.dao.SysLogOperMapper;

/**
 * 系统操作日志持久化
 */
@Component
public class SysLogOperPersister implements LogPersister {
	
	@Autowired
	private SysLogOperMapper sysLogOperMapper;
	
	@Override
	public boolean doPersistence(SysLogOper log) {
		sysLogOperMapper.insert(log);
		return true;
	}

}
