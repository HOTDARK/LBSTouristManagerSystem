package com.hd.sfw.core.cache;

import java.lang.reflect.Method;

/**
 * 方法缓存配置定义
 * @author sunjian
 * @version V1.0, 2013-7-5 上午11:38:29
 */
public class CacheableDefine {
	public final static int STATE_OPEN = 1;
	
	public final static int STATE_CLOSE = 0;
	
	//方法的唯一标示，由方法全路径加参数类型组成如com.test.Test.print(java.lang.String)
	private String id;
	
	//缓存名称
	private String name;
	
	//方法
	private Method method;
	
	//Key前缀
	private String prefix;
	
	//有效时间 秒
	private int timeToLive;
	
	//空闲时长 秒
	private int timeToIdle;
	
	//缓存状态
	private int state;
	
	//key生成策略
	private CacheKeyGenerator cacheKeyGenerator;
	
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
	public Method getMethod() {
		return method;
	}

	/**
	 * @param method the method to set
	 */
	public void setMethod(Method method) {
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
	 * @return the cacheKeyGenerator
	 */
	public CacheKeyGenerator getCacheKeyGenerator() {
		return cacheKeyGenerator;
	}

	/**
	 * @param cacheKeyGenerator the cacheKeyGenerator to set
	 */
	public void setCacheKeyGenerator(CacheKeyGenerator cacheKeyGenerator) {
		this.cacheKeyGenerator = cacheKeyGenerator;
	}
	
	/**
	 * @return the prefix
	 */
	public String getPrefix() {
		return prefix;
	}

	/**
	 * @param prefix the prefix to set
	 */
	public void setPrefix(String prefix) {
		this.prefix = prefix;
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

	public Object getCacheKey(Object[] args){
		return cacheKeyGenerator.getKey(this, args);
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
	
}
