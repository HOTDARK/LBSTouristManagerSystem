/*
 * Copyright © 2014, Wangding (Chengdu) Tech Co.,Ltd. 
 * All right reserved.
 */
package com.hd.sfw.core.utils;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * 用于响应的工具类
 * 
 * @version	0.0.1
 * @author	<a href="mailto:sunjian@iwangding.com">孙剑</a>
 * @date	2014-8-21 上午10:54:32
 */
public class ResponseUtils {
	private final static Logger logger = Logger.getLogger(ResponseUtils.class);
	
	/**
	 * JSON 格式响应
	 * @author	<a href="mailto:sunjian@iwangding.com">孙剑</a>
	 * @param response
	 * @param res
	 */
	public static void responseJson(HttpServletResponse response,String res) {
		try {
			response.setContentType("application/json;charset=UTF-8");
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			out.write(res);
			out.flush();
		}catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		
	}
	
	/**
	 * javascript格式响应
	 * @author	<a href="mailto:sunjian@iwangding.com">孙剑</a>
	 * @param response
	 * @param res
	 */
	public static void responseScript(HttpServletResponse response,String res) {
		try {
			response.setContentType("text/javascript;charset=UTF-8");
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			out.write(res);
			out.flush();
		}catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		
	}
	
	/**
	 * html格式响应
	 * @author	<a href="mailto:sunjian@iwangding.com">孙剑</a>
	 * @param response
	 * @param res
	 */
	public static void responseHtml(HttpServletResponse response,String res) {
		try {
			response.setContentType("text/html;charset=utf-8");
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			out.write(res);
			out.flush();
		}catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		
	}

}
