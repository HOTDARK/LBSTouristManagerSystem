/*
 * Copyright © 2014, Wangding (Chengdu) Tech Co.,Ltd. 
 * All right reserved.
 */
package com.hd.sfw.core.support.http;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hd.sfw.core.utils.IOUtils;

/**
 * 用于支持读取打包后的资源文件，一般封装的工具包会使用此类来实现某些功能
 * @version	0.0.1
 * @author	<a href="mailto:sunjian@iwangding.com">孙剑</a>
 * @date	2014-9-1 上午11:38:30
 */
public abstract class ResourceSerlvet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public final String resourcePath;
	
	public ResourceSerlvet(String resourcePath) {
		this.resourcePath = resourcePath;
	}
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
        String contextPath = request.getContextPath();
        String servletPath = request.getServletPath();
        String requestURI = request.getRequestURI();
        
        if (contextPath == null) { // root context
            contextPath = "";
        }
        
        String uri = contextPath + servletPath;
        String path = requestURI.substring(contextPath.length() + servletPath.length());
        
        responseResourceFile(path, uri, response);
	}
	
    protected String getFilePath(String fileName) {
        return resourcePath + fileName;
    }
	
	protected void responseResourceFile(String fileName, String uri,
			HttpServletResponse response) throws ServletException, IOException {
		String filePath = getFilePath(fileName);
		String ext = IOUtils.getFileExt(fileName);
		
		if ("css".equalsIgnoreCase(ext)) {
			response.setContentType("text/css;charset=utf-8");
		} else if ("js".equalsIgnoreCase(ext)) {
			response.setContentType("text/javascript;charset=utf-8");
		}else{
			String mime = MimeTypes.getMimeType(ext);
			if(mime!=null){
				response.setContentType(mime);
			}
		}
		
		byte[] data = IOUtils.readFromResource(filePath);
		response.getOutputStream().write(data);
	}
	
	
}
