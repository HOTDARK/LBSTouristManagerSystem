/*
 * Copyright © 2014, Wangding (Chengdu) Tech Co.,Ltd. 
 * All right reserved.
 */
package com.hd.sfw.webservice.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringReader;
import java.lang.reflect.Field;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.log4j.Logger;

import com.hd.sfw.core.utils.IOUtils;
import com.hd.sfw.core.utils.json.JsonUtils;
import com.hd.sfw.webservice.WebServiceClient;
import com.hd.sfw.webservice.client.annotations.HttpParam;
import com.hd.sfw.webservice.model.WebServiceConfigure;
import com.hd.sfw.webservice.model.enums.AuthType;

/**
 * http，xml方式返回的webservice接口
 * @version	0.0.1
 * @author	<a href="mailto:likl@iwangding.com">李坤林</a>
 * @date	2014-9-12 上午10:11:26
 */
public class HttpClient implements WebServiceClient {
	private static final Logger logger = Logger.getLogger(HttpClient.class);
	@Override
	public <T> T call(WebServiceConfigure configure,Object[] args,Class<T> responseType) throws Exception{
		if(configure.getAuth() != null && configure.getAuth().equals(AuthType.POST)){
			PrintWriter out = null;
			BufferedReader in = null;
			String result = "";
			try {
				URL realUrl = new URL(configure.getUrl());
				// 打开和URL之间的连接
				URLConnection conn = realUrl.openConnection();
				// 设置通用的请求属性
				conn.setRequestProperty("accept", "*/*");
				conn.setRequestProperty("connection", "Keep-Alive");
				conn.setRequestProperty("user-agent",
						"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
				// 发送POST请求必须设置如下两行
				conn.setDoOutput(true);
				conn.setDoInput(true);
				// 获取URLConnection对象对应的输出流
				out = new PrintWriter(conn.getOutputStream());
				
				//根据<判断是否是想xml
				String param;
				if(args[0].toString().indexOf("<")>-1){
					param = args[0].toString();
				}else{
					param=getParam(args);
				}
				// 发送请求参数
				out.print(param);
				// flush输出流的缓冲
				out.flush();
				// 定义BufferedReader输入流来读取URL的响应
				in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
				String line;
				while ((line = in.readLine()) != null) {
					result += line;
				}
			} catch (Exception e) {
				System.out.println("发送 POST 请求出现异常！" + e);
				e.printStackTrace();
			}
			// 使用finally块来关闭输出流、输入流
			finally {
				try {
					if (out != null) {
						out.close();
					}
					if (in != null) {
						in.close();
					}
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
			return getObjectOfXml(URLDecoder.decode(result, "UTF-8"), responseType);
		}else{
			String url = configure.getUrl() + getParam(args);
			
			URL urlObj = new URL(url);  
	        HttpURLConnection conn = (HttpURLConnection)urlObj.openConnection();
	        
	        //30秒连接超时
	        conn.setConnectTimeout(30000);
	        //读超时
	        conn.setReadTimeout(configure.getTimeout().intValue());
	        
	        InputStream inputStream = conn.getInputStream();
	        byte[] getData = IOUtils.toByteArray(inputStream);
	        IOUtils.closeQuietly(inputStream);
	        
			return getObjectOfXml(new String(getData, "UTF-8"), responseType);
		}
		
	}
	
	/**
	 * @author	<a href="mailto:likl@iwangding.com">李坤林</a>
	 * @param obj
	 */
	public String getParam(Object[] objs) {
		if (objs == null){
			return "";
		}
			
		StringBuilder paramStr = new StringBuilder();
		
		for(Object obj : objs){
			Field[] fields = obj.getClass().getDeclaredFields();
			for (Field field : fields) {
				field.setAccessible(true);
				paramStr.append(paramStr.length()>0?"&":"?");
				
				HttpParam param = field.getAnnotation(HttpParam.class);
				paramStr.append(param==null?field.getName():param.value());
				
				try {
					paramStr.append("=" + field.get(obj));
				} catch (IllegalArgumentException e) {
					//ignore
				} catch (IllegalAccessException e) {
					//ignore
				}
			}
		}
		
		return paramStr.toString();
	}
	
	/**
	 * 将xml转换成对象
	 * @param xml
	 * @param obj
	 * @return
	 * @throws JAXBException 
	 */
	@SuppressWarnings("unchecked")
	private <T> T getObjectOfXml(String xml, Class<T> responseType) throws JAXBException{
		try {
			logger.debug("返回解析xml:"+xml);
			JAXBContext ctx = JAXBContext.newInstance(responseType);
			Unmarshaller um = ctx.createUnmarshaller();
			return (T)um.unmarshal(new StringReader(xml));
		} catch (Exception e) {
			//e.printStackTrace();
			//如果解析出错，说明传过来的值也可能是Json格式的数据
			return getObjectOfJson(xml,responseType);
		}
	}
	
	/**
	 * 将JSON转换成对象
	 * @param xml
	 * @param obj
	 * @return
	 * @throws JAXBException 
	 */
	private <T> T getObjectOfJson(String json, Class<T> responseType) throws JAXBException{
		return JsonUtils.json2Object(json, responseType, null);
	}
}
