package com.hd.sfw.log.trace;

import com.hd.sfw.log.trace.model.SysLogOper;

/**
 * 日志持久化器<br>
 * 在日志持久化时存在多个LogPersister的实现实例在一个list中，会依次访问这些实的doPersistence方法
 * @author sunjian
 * @version V1.0, 2013-4-8 下午04:19:54
 */
public interface LogPersister {
	
	/**
	 * 
	 * 本方法做最终的持久化操作，如果返回true则将继续调用后面的LogPersister,否则结束调用
	 * @param log
	 * @return
	 */
	public boolean doPersistence(SysLogOper log);
		
}
