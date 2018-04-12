/*
 * Copyright © 2014, Wangding (Chengdu) Tech Co.,Ltd. 
 * All right reserved.
 */
package com.hd.tsa.message;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

/**
 * 解决BlazeDS在Tomcat7.27以上版本Streaming不工作的问题
 * @version	0.0.1
 */
public class MessageBrokerHack implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		
		chain.doFilter(request, new HttpServletResponseWrapper(
	            (HttpServletResponse) response) {
	        public void setHeader(String name, String value) {
	            if (!("Connection".equalsIgnoreCase(name) && "Close"
	                    .equalsIgnoreCase(value))) {
	                super.setHeader(name, value);
	            }
	        }
	    });
		
	}

	@Override
	public void destroy() {
		
	}

}
