/*
 * Copyright © 2014, Wangding (Chengdu) Tech Co.,Ltd. 
 * All right reserved.
 */
package com.hd.sfw.core.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

/**
 * http cookie 操作工具类
 * @version	0.0.1
 * @author	<a href="mailto:sunjian@iwangding.com">孙剑</a>
 * @date	2014-9-19 下午3:42:52
 */
public class CookieUtils {
	
	/**
	 * 删除cookie
	 * @param response
	 * @param cookieName
	 */
	public static void remove(HttpServletResponse response,String cookieName){
		if(response!=null&&StringUtils.isNotEmpty(cookieName)){
			Cookie cookie = new Cookie(cookieName, null);
			cookie.setMaxAge(0);
			cookie.setPath("/");
			response.addCookie(cookie);
		}
	}
	
	/**
	 * 获取指定的cookie
	 * @param request
	 * @param cookieName
	 * @return
	 */
	public static Cookie get(HttpServletRequest request,String cookieName){
		if(request!=null&&StringUtils.isNotEmpty(cookieName)){
			Cookie[] cookies = request.getCookies();
			if(cookies==null){
				return null;
			}
			
			for(Cookie cookie : cookies){
				if(cookieName.equals(cookie.getName())){
					return cookie;
				}
			}
		}
		
		return null;
	}
}
