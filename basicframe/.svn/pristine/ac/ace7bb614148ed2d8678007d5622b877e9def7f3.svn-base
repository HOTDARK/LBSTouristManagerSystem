package com.hd.sys.core.log;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.hd.sys.core.consts.FunLogConst;

/**
 * 
 * 用于标记方法记录功能日志
 * @author somnuscy
 *
 */
@Target({ElementType.METHOD})   
@Retention(RetentionPolicy.RUNTIME)   
@Documented
public @interface LogOpt {
	
	/**
	 * 日志等级
	 * @return
	 */
	public int level() default FunLogConst.LEVEL_DEFAULT;
	
	/**
	 * 日志描述
	 * @return
	 */
	public String desc() default FunLogConst.DESC_DEFAULT;
	
	/**
	 * 上级日志描述
	 * @return
	 */
	public String parentDesc() default FunLogConst.DESC_DEFAULT;
}

