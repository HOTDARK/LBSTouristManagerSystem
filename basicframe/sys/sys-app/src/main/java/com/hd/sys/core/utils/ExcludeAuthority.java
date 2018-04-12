package com.hd.sys.core.utils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 用于权限控制 方法级别的注解 包含此注解的方法将不做权限验证<br>
 * 这个只能用于action层
 * @author sunjian
 * @version V1.0, 2013-5-7 上午09:21:14
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ExcludeAuthority {

}
