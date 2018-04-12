package com.hd.sfw.log.trace;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

import org.apache.log4j.Logger;

import com.hd.sfw.log.trace.model.SysLogOper;

/**
 * 
 * 负责执行日志持久化调用和缓冲日志
 * 
 * @author sunjian
 * @version V1.0, 2013-4-9 上午09:52:26
 */
public class LogPersisterExecutor {
	
	private final static Logger log = Logger.getLogger(LogPersisterExecutor.class);
	
	private List<LogPersister> persisters = new ArrayList<LogPersister>();
	
	private LinkedBlockingQueue<SysLogOper> queue = new LinkedBlockingQueue<SysLogOper>();
	
	//日志持久化线程数
	private int threads = 1;
	
	//线程停止信号
	private volatile boolean signal = false;
	
	/**
	 * 启动持久化线程
	 */
	public void start(){
		log.info("启动日志持久化器.");
		if(!signal){
			signal = true;
			for(int i=0;i<threads;i++){
				new LogPersisterThread("LOG-PERSISTER-THREAD-"+i).start();
			}
		}
	}
	
	/**
	 * 停止持久化
	 */
	public void stop(){
		log.info("停止日志持久化器.");
		signal = false;
		
		//这个操作可以认为是一个标记，使得之前的日志都被处理完毕
		queue.offer(new LoggingStopsignal());
	}
	
	
	public int getThreads() {
		return threads;
	}

	/**
	 * 设置线程数 必须在start之前设置才有效
	 * @param threads
	 */
	public void setThreads(int threads) {
		if(threads<0){
			throw new IllegalArgumentException("线程数必须大于0");
		}
		this.threads = threads;
	}

	/**
	 * 添加日志
	 * @param log
	 */
	public void add(SysLogOper log){
		if(signal){
			queue.offer(log);
		}
	}
	
	
	public List<LogPersister> getPersisters() {
		return persisters;
	}

	public void setPersisters(List<LogPersister> persisters) {
		this.persisters = persisters;
	}


	private class LogPersisterThread extends Thread{
		
		public LogPersisterThread(String name) {
			super(name);
		}
		
		@Override
		public void run() {
			while(signal){
				SysLogOper log = null;
				try {
					log = queue.take();
				} catch (InterruptedException e) {
				}
				
				if(log instanceof LoggingStopsignal){
					//再次放入队列，这样可以关闭所有的日志持久化线程
					queue.offer(log);
					return;
				}
				
				for(LogPersister p : persisters ){
					try{
						//依次调用，如果返回false则中断
						if(!p.doPersistence(log)){
							break;
						}
					}catch (Exception e) {
						//log4j记录日志 方便查找错误原因
						LogPersisterExecutor.log.error("日志持久化失败.", e);
					}
					
				}
				
			}
		}
	}
	
	/**
	 * 日志记录停止标记
	 * @author sunjian
	 * @version V1.0, 2013-11-28 下午01:50:08
	 */
	private static class LoggingStopsignal extends SysLogOper{
		
	}
}
