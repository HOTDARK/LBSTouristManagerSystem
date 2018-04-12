package com.hd.sfw.core.cache;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 用于标记方法是否清除缓存
 * @author somnuscy
 *
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface CacheEvict {
	
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
	
}
