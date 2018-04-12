/*
 * Copyright © 2014, Wangding (Chengdu) Tech Co.,Ltd. 
 * All right reserved.
 */
package com.hd.sfw.core.support.jmx.mbean;



/**
 * 系统MBean实现
 * @version	0.0.1
 * @author	<a href="mailto:sunjian@iwangding.com">孙剑</a>
 * @date	2014-12-4 下午4:54:09
 */
public class SystemStatus implements SystemStatusMBean {

	@Override
	public void shutdown() {
		System.exit(0);
	}

	@Override
	public void gc() {
		System.gc();
	}

	@Override
	public long getMaxMemory() {
		return Runtime.getRuntime().maxMemory();
	}

	@Override
	public long getTotalMemory() {
		return Runtime.getRuntime().totalMemory();
	}

	@Override
	public long getFreeMemory() {
		return Runtime.getRuntime().freeMemory();
	}

	@Override
	public String echo(String str) {
		return str;
	}

}
