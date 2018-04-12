/*
 * Copyright © 2014, Wangding (Chengdu) Tech Co.,Ltd. 
 * All right reserved.
 */
package com.hd.sys.entity.vo;

import com.hd.sfw.core.cache.CacheableDefine;
import com.hd.sfw.core.model.Pagination;

/**
 * CacheableDefine vo对象
 * @version	0.0.1
 * @author	<a href="mailto:sunjian@iwangding.com">孙剑</a>
 * @date	2014-9-24 上午10:00:00
 */
public class CacheableDefineVO extends Pagination<CacheableDefineVO> {
	
	private String id;
	
	//缓存名称
	private String name;
	
	// key前缀
	private String prefix;
	
	//方法
	private String method;
	
	//有效时间 秒
	private int timeToLive;
	
	//空闲时长 秒
	private int timeToIdle;
	
	//缓存状态
	private int state;
	
	public CacheableDefineVO() {
	}
	
	public CacheableDefineVO(CacheableDefine define) {
		if(define!=null){
			this.id = define.getId();
			this.name = define.getName();
			this.prefix = define.getPrefix();
			this.timeToLive = define.getTimeToLive();
			this.timeToIdle = define.getTimeToIdle();
			this.state = define.getState();
			this.method = define.getMethod().getDeclaringClass().getName()+"."+define.getMethod().getName();
		}
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the method
	 */
	public String getMethod() {
		return method;
	}

	/**
	 * @param method the method to set
	 */
	public void setMethod(String method) {
		this.method = method;
	}

	/**
	 * @return the timeToLive
	 */
	public int getTimeToLive() {
		return timeToLive;
	}

	/**
	 * @param timeToLive the timeToLive to set
	 */
	public void setTimeToLive(int timeToLive) {
		this.timeToLive = timeToLive;
	}

	/**
	 * @return the timeToIdle
	 */
	public int getTimeToIdle() {
		return timeToIdle;
	}

	/**
	 * @param timeToIdle the timeToIdle to set
	 */
	public void setTimeToIdle(int timeToIdle) {
		this.timeToIdle = timeToIdle;
	}

	/**
	 * @return the state
	 */
	public int getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(int state) {
		this.state = state;
	}
	
	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	@Override
	public String toString() {
		return "CacheableDefineVO [id=" + id + ", name=" + name + ", prefix=" + prefix + ", method=" + method
				+ ", timeToLive=" + timeToLive + ", timeToIdle=" + timeToIdle + ", state=" + state + "]";
	}

}
