/*
 * Copyright © 2014, Wangding (Chengdu) Tech Co.,Ltd. 
 * All right reserved.
 */
package com.hd.sfw.core.utils;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;

/**
 * 获取JVM 进程id
 * @version	0.0.1
 * @author	<a href="mailto:sunjian@iwangding.com">孙剑</a>
 * @date	2014-8-18 下午3:49:51
 */
public class PidUtils {

	private static int pid = -1;
	
	private PidUtils() {
	}

	public static int getJvmPid() {
		if(pid < 0){
			try {
				RuntimeMXBean runtime = ManagementFactory.getRuntimeMXBean();
				String name = runtime.getName();
 				pid = Integer.parseInt(name.substring(0, name.indexOf(64)));
			} catch (Throwable e) {
				pid = 0;
			}
		}
			
		return pid;
	}

	public static void main(String[] args) {
		System.out.println(getJvmPid());
	}
}
