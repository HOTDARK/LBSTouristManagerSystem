/*
 * Copyright © 2014, Wangding (Chengdu) Tech Co.,Ltd. 
 * All right reserved.
 */
package com.hd.sfw.core.utils;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.FrameworkServlet;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

/**
 * 针对spring mvc的一些工具类
 * @version	0.0.1
 * @author	<a href="mailto:sunjian@iwangding.com">孙剑</a>
 * @date	2014-8-21 上午11:38:42
 */
public class SpringMVCUtils {
	
	/**
	 * 获取所有映射的url
	 * @author	<a href="mailto:sunjian@iwangding.com">孙剑</a>
	 * @param request
	 * @return
	 */
	public static List<String> getAllRequestMapping(HttpServletRequest request){
		ServletContext sc = request.getSession().getServletContext();
        WebApplicationContext wac = WebApplicationContextUtils.getWebApplicationContext(sc,FrameworkServlet.class.getName() + ".CONTEXT.mvc");
        RequestMappingHandlerMapping mapping = wac.getBean(RequestMappingHandlerMapping.class);
        
        List<String> list = new ArrayList<String>();
        for(RequestMappingInfo info : mapping.getHandlerMethods().keySet()){
        	for(String url : info.getPatternsCondition().getPatterns()){
        		list.add(url);
        		break;
        	}
        }
        
        return list;
        
	}
	
}
