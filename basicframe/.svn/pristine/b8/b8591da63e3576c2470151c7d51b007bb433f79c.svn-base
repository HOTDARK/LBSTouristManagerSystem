package com.hd.sfw.core.cache;

import java.util.List;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.AnnotationUtils;

import com.hd.sfw.core.utils.CollectionUtils;

import net.sf.ehcache.Cache;

/**
 * 
 * 针对Ehcache实现的拦截，此拦截器需要把cacheManager、cache对象注入
 * @author somnuscy
 *
 */
public class CacheEvictMethodInterceptor implements MethodInterceptor,InitializingBean,Ordered {
	
	private CacheManager cacheManager;
	
	private Cache cache;
	
	@Override
	public Object invoke(MethodInvocation methodInvocation) throws Throwable {
		List<?> keys = cache.getKeys();
		if (CollectionUtils.isNotEmpty(keys)) {
			CacheEvict cacheEvict = AnnotationUtils.findAnnotation(methodInvocation.getMethod(), CacheEvict.class);
			StringBuffer sb = new StringBuffer(cacheEvict.value());
			sb.append("_");
			sb.append(StringUtils.isBlank(cacheEvict.prefix()) ? cacheEvict.value() : cacheEvict.prefix());
			for (Object key : keys) {
				if (key.toString().contains(sb.toString())) {
					cache.remove(key);
				}
			}
		}
		return methodInvocation.proceed();
	}


	/**
	 * @param cacheManager the cacheManager to set
	 */
	public void setCacheManager(CacheManager cacheManager) {
		this.cacheManager = cacheManager;
	}

	/**
	 * @param cache the cache to set
	 */
	public void setCache(Cache cache) {
		this.cache = cache;
	}


	@Override
	public void afterPropertiesSet() throws Exception {
		if(cache==null){
			throw new NullPointerException("cache属性不能为null");
		}
		
		if(cacheManager==null){
			throw new NullPointerException("cacheManage属性不能为null");
		}
	}

	@Override
	public int getOrder() {
		return Integer.MAX_VALUE;
	}
	
}
