package com.hd.sfw.core.utils;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

public class IpUtils {
	
	/**
	 * 根据http请求，获取请求访问IP
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public static String getIpAddr(HttpServletRequest request) {
		if (request == null) {
			return null;
		}
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		if (StringUtils.isNotBlank(ip) && ip.contains(",")) {
			// 多个路由时，取第一个非unknown的ip
			final String[] ips = ip.split(",");
			for (final String uip : ips) {
				if (!"unknown".equalsIgnoreCase(uip)) {
					return uip;
				}
			}
			return ip.split(",")[0];
		} else {
			return ip;
		}
	}
	
	/**
	 * 根据http请求，获取请求访问端口
	 * @param request
	 * @return
	 */
	public static int getPort(HttpServletRequest request){
		String port = request.getHeader("X-Real-Port");
		if (port == null || port.isEmpty()) {
			port = String.valueOf(request.getRemotePort());
		}
		if (port != null ) {
			return Integer.parseInt(port);
		}
		return 0;
	}
}
