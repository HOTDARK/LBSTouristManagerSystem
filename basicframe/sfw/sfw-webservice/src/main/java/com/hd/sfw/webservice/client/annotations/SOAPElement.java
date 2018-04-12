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
 * 用于指定单一的多层嵌套，如：
 * <Query><b>abccd</b></Query>,此时SOAPElement(wrapper="b"),
 * 如果有多层<Query><a><b>abccd</b><a></Query>,SOAPElement(wrapper={"a","b"})
 * @version	0.0.1
 * @author	<a href="mailto:sunjian@iwangding.com">孙剑</a>
 * @date	2014-10-16 下午2:19:45
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SOAPElement {
	
	/**
	 * 可以有多层
	 * @return
	 */
	String[] wrapper() default {};
	
	String namespace() default "";
	
}
