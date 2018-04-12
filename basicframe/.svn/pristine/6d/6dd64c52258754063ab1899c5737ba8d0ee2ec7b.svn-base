/*
 * Copyright © 2014, Wangding (Chengdu) Tech Co.,Ltd. 
 * All right reserved.
 */
package com.hd.sfw.core.support.jmx.mbean;

/**
 * 系统状态MBean
 * @version	0.0.1
 * @author	<a href="mailto:sunjian@iwangding.com">孙剑</a>
 * @date	2014-12-4 下午4:47:48
 */
public interface SystemStatusMBean {
	
	/**
	 * 关闭应用
	 */
	public void shutdown();
	
	/**
	 * 调用gc
	 */
	public void gc();
	
	/**
	 * 返回最大内存大小(byte)
	 * @return
	 */
	public long getMaxMemory();

	/**
	 * 返回总内存大小(byte)
	 * @return
	 */
	public long getTotalMemory();

	/**
	 * 返回空闲内存大小 (byte)
	 * @return
	 */
	public long getFreeMemory();
	
	/**
	 * echo服务，返回输入
	 * @param str
	 * @return
	 */
	public String echo(String str);
	
}
