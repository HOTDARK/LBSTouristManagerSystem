package com.hd.tsa.service.common.impl;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.hd.tsa.service.common.ThreadsService;
import com.hd.sfw.core.utils.PropertiesUtils;

/**
 * 线程池处理
 * @author somnuscy
 *
 */
@Service
public class ThreadsServiceImpl implements ThreadsService {
	
	private final static Logger log = LoggerFactory.getLogger(ThreadsServiceImpl.class);
	
	private ThreadPoolExecutor poolExecutor = null;
	
	public ThreadsServiceImpl(){
		if (poolExecutor == null) {
			String maxThreads = PropertiesUtils.getProperty(this.getClass(), "common", "maxThreads");
			String keepAliveTime = PropertiesUtils.getProperty(this.getClass(), "common", "keepAliveTime");
			log.info("初始化公共线程池：最大线程大小={}，空闲线程回收时间={}", maxThreads, keepAliveTime);
			poolExecutor = new ThreadPoolExecutor(Integer.parseInt(maxThreads), Integer.parseInt(maxThreads), Long.parseLong(keepAliveTime), TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());
		}
	}

}
