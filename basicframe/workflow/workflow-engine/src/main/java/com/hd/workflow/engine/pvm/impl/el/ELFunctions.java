/*
 * Copyright © 2015, Wangding (Chengdu) Tech Co.,Ltd. 
 * All right reserved.
 */
package com.hd.workflow.engine.pvm.impl.el;

import org.apache.commons.lang.StringUtils;

/**
 * 一些用于在el表达式中使用的函数和方法.
 * 此类中的静态方法将通过反射被自动加入到el context中，
 * 注意不能存在方法重载因为最终映射的名称是取的方法名如果存在方法重载将导致覆盖
 * @version	0.0.1
 * @author	<a href="mailto:sunjian@iwangding.com">孙剑</a>
 * @date	2015-4-20 下午3:10:16
 */
public class ELFunctions {

	/**
	 * 用于在el表达式中实现映射，el表达式中的用法如下：<br>
	 * ${iwd:mapping(mm,'1=>ok,2=>no')} <br>
	 * 如果mm=1，则返回ok;如果mm=null,则返回null;如果mm非null且不存在于map中，则返回mm自身<br> 
	 * @param target
	 * @param map
	 * @return
	 */
	public static Object mapping(Object target,String map){
		if(target!=null&&StringUtils.isNotEmpty(map)){
			String t = target.toString();
			String prefix = t+"=>";
			
			String[] arr = map.trim().split(",");
			for(String str : arr){
				if(str.startsWith(prefix)){
					return str.replace(prefix, "");
				}
			}
			
			return target;
		}else{
			return null;
		}
		
	}
	
}
