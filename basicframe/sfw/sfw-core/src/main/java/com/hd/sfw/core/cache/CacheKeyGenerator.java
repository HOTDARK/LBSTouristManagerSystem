package com.hd.sfw.core.cache;


/**
 * 缓存key生成器
 * @author sunjian
 * @version V1.0, 2013-7-4 下午04:18:02
 */
public interface CacheKeyGenerator {
	
	/**
	 * 获取缓存key
	 * @param cacheDefine
	 * @param args
	 * @return
	 */
	public Object getKey(CacheableDefine cacheDefine,Object[] args);
}
