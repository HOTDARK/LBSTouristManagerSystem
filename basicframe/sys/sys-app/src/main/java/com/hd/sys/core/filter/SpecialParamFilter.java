package com.hd.sys.core.filter;

import java.io.IOException;
import java.util.Enumeration;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 过滤特殊入参
 * @author somnuscy
 *
 */
public class SpecialParamFilter implements Filter {
	
	@Override
	public void destroy() {

	}
	
	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest)servletRequest;
		HttpServletResponse response = (HttpServletResponse)servletResponse;
		//获得所有请求参数名 
		@SuppressWarnings("unchecked")
		Enumeration<String> params = request.getParameterNames();
        StringBuffer paramStr = new StringBuffer("");
        while (params.hasMoreElements()) {
            //得到参数名
            String name = params.nextElement().toString();
            //得到参数对应值 
            String[] value = request.getParameterValues(name);
            for (int i = 0; i < value.length; i++) {
            	paramStr.append(value[i]);
            }
        }
        /**
         * 有特殊字符跳转到错误页面
         */
        if (!existValidate(paramStr.toString())) {
        	response.sendError(402);
			return;
        } else {
            chain.doFilter(request, response);
        }
	}
	
	/**
	 * 是否存在特殊字符
	 * @param paramStr
	 * @return
	 */
	private boolean existValidate(String paramStr){
		String regEx = "^([^<>\"'%;)(&+]*)$";
		return Pattern.matches(regEx, paramStr);
	}
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}
	
}
