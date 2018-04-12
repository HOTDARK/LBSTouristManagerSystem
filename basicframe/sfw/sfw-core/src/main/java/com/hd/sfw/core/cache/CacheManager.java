package com.hd.sfw.core.cache;

import java.lang.reflect.Method;
import java.util.List;

/**
 * 全局缓存配置管理
 * @author sunjian
 * @version V1.0, 2013-7-5 下午03:07:29
 */
public interface CacheManager {
	
	/**
	 * 根据method获取CacheableDefine
	 * @param method
	 * @return
	 */
	public CacheableDefine getCacheableDefine(Method method);
	
	/**
	 * 添加
	 * @param cacheableDefine
	 */
	public void putCacheableDefine(CacheableDefine cacheableDefine);
	
	/**
	 * 获取定义集合
	 * @return
	 */
	public List<CacheableDefine> getCacheableDefines();
	
	/**
	 * 通过ID获取特定的缓存配置
	 * @return
	 */
	public CacheableDefine getCacheableDefineByID(String id);
	
	/**
	 * 缓存是否打开
	 * @return
	 */
	public boolean isOpen();
}
