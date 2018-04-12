/*
 * Copyright © 2014, Wangding (Chengdu) Tech Co.,Ltd. 
 * All right reserved.
 */
package com.hd.sfw.webservice.client.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * soap协议的参数注解
 * @version	0.0.1
 * @author	<a href="mailto:sunjian@iwangding.com">孙剑</a>
 * @date	2014-10-15 下午2:19:43
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SOAPParam {
	
	/**
	 * 对应到wsdl中的参数名 
	 * @return
	 */
	String name() default "";
	
	/**
	 * 参数排序，顺序排列，越小越靠前
	 * @return
	 */
	int order() default Integer.MAX_VALUE;
	
	/**
	 * 在soap请求的xml中，是否使用父节点的namespace
	 * @return
	 */
	boolean include() default false;
	
}
