package com.hd.sfw.log.trace;

import java.util.UUID;

/**
 * 日志id
 * 
 * @author sunjian  
 * @version 1.0 2013-2-21
 */
public class LogId {
	
	//id
	private String id;
	
	//父id
	private String pid;
	
	//用户名
	private String username;
	
	private String from;
	
	private int ord = 0;
	
	public LogId() {
		//实例化时就生成日志id
		id = UUID.randomUUID().toString();
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the from
	 */
	public String getFrom() {
		return from;
	}

	/**
	 * @param from the from to set
	 */
	public void setFrom(String from) {
		this.from = from;
	}

	/**
	 * 返回此log的顺序，此方法没调用一次会加1<br>
	 * 另外由于一个LogId实例只会在一个线程中使用所以此方法也不需要进行同步处理
	 * @return
	 */
	public int getOrd(){
		return ord++;
	}
	
	@Override
	public String toString() {
		return "id:"+id+",pid:"+pid;
	}
	
}
