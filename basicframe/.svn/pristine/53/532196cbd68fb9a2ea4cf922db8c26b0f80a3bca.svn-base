package com.hd.sfw.core.cache;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 用于标记方法是否加入缓存管理
 * @author somnuscy
 *
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Cacheable {
	
	/**
	 * 缓存方法的名称
	 * @return
	 */
	public String value();
	
	/**
	 * 缓存key前缀,为空时默认用value值作为前缀
	 * @return
	 */
	public String prefix() default "";
	
	/**
	 * 缓存key生成策略
	 * @return
	 */
	public Class<? extends CacheKeyGenerator> cacheKeyGenerator() default DefaultCacheKeyGenerator.class;
	
	/**
	 * 存活时间 单位为秒<br>
	 * 即对象最新失效的最大时间
	 * @return
	 */
	public int timeToLive() default 1800;
	
	/**
	 * 空闲时间 单位为秒<br>
	 * 即距离上一次访问该对象后空闲的时间，如果超过则失效.受timeToLive约束
	 * @return
	 */
	public int timeToIdle() default 0;
	
	/**
	 * 状态
	 * CacheableDefine.STATE_OPEN,
	 * CacheableDefine.STATE_CLOSE;
	 * @return
	 */
	public int state() default CacheableDefine.STATE_OPEN; 
}
