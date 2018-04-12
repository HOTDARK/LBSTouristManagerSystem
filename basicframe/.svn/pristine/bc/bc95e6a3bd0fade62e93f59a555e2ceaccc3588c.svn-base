/*
 * Copyright © 2014, Wangding (Chengdu) Tech Co.,Ltd. 
 * All right reserved.
 */
package com.hd.sys.core.log;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hd.sfw.log.trace.LogId;
import com.hd.sfw.log.trace.LogIdApplier;
import com.hd.sys.core.online.OnlineSession;
import com.hd.sys.core.online.OnlineUserManager;

/**
 * 默认填充，为logid加入用户名和请求来源
 * @version	0.0.1
 * @author	<a href="mailto:sunjian@iwangding.com">孙剑</a>
 * @date	2014-9-18 上午11:56:54
 */
@Component
public class DefaultLogIdApplier implements LogIdApplier{
	
	@Autowired
	public OnlineUserManager onlineUserManager;

	@Override
	public void apply(LogId logId) {
		OnlineSession session = onlineUserManager.getCurrentOnlineSession();
		if(session!=null&&logId!=null){
			logId.setFrom(session.getFrom());
			logId.setUsername(session.getOnlineSysUser().getUser().getUserAccount());
		}
	}

}
