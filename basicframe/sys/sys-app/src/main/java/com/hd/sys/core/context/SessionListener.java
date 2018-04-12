package com.hd.sys.core.context;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.commons.lang.StringUtils;

/**
 * 
 * @author sunjian
 * @version V1.0, 2013-5-7 下午12:27:10
 */
public class SessionListener implements HttpSessionListener{
	
	
	
	/**
	 * Session创建
	 */
	public void sessionCreated(HttpSessionEvent arg0) {
		//arg0.getSession().setMaxInactiveInterval(FrameworkContext.SYS_SESSION_EXPIRED_TIME);
	}

	/**
	 * Session消毁处理
	 */
	public void sessionDestroyed(HttpSessionEvent arg0) {
		HttpSession session = arg0.getSession();
		if(null != session){
			String sid = (String) session.getAttribute(FrameworkContext.SYS_USER_FLAG);
			if(StringUtils.isNotEmpty(sid)){
				FrameworkContext.getFrameworkContext().logout(sid);
			}
			
		}
	}
}
