package com.hd.sfw.core.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Field;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

/**
 * Http请求方法
 * @author somnuscy
 *
 */
public class HttpUtils {
	
	/**
	 * 默认超时时间30s
	 */
	private static final int DEFAULT_TIMEOUT = 30*1000;
	/**
	 * 编码方式
	 */
	private static final String CHARSET = "UTF-8";
	/**
	 * 提交方式[POST]
	 */
	private static final String REQ_POST = "POST";
	/**
	 * 提交方式[GET]
	 */
	private static final String REQ_GET = "GET";
	
	/**
     * 向指定URL发送GET方法的请求
     * @param url 发送请求的URL
     * @param param 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @param timeOut 超时时间
     * @return URL 所代表远程资源的响应结果
     */
    public static String sendGet(String url, String param, int timeOut) {
        BufferedReader in = null;
        try {
            String urlNameString = url + "?" + param;
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            HttpURLConnection conn = (HttpURLConnection) realUrl.openConnection();
            conn.setRequestMethod(REQ_GET);
            // 设置超时
 			conn.setConnectTimeout(timeOut==0?DEFAULT_TIMEOUT:timeOut);
 			conn.setReadTimeout(timeOut==0?DEFAULT_TIMEOUT:timeOut);
            // 建立实际的连接
            conn.connect();
            
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(conn.getInputStream(), CHARSET));
            String result = "";
            while ((result = in.readLine()) != null) {
				return result;
			}
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return null;
    }
    
	/**
     * 向指定 URL 发送POST方法的请求
     * @param url 发送请求的 URL
     * @param params 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @param timeOut 超时时间
     * @return 所代表远程资源的响应结果
     */
    public static String sendPost(String url, String params, int timeOut) {
    	OutputStreamWriter out = null;
        BufferedReader in = null;
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            HttpURLConnection conn = (HttpURLConnection)realUrl.openConnection();
            conn.setRequestMethod(REQ_POST);
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 设置超时
 			conn.setConnectTimeout(timeOut==0?DEFAULT_TIMEOUT:timeOut);
 			conn.setReadTimeout(timeOut==0?DEFAULT_TIMEOUT:timeOut);
            // 获取URLConnection对象对应的输出流
            out = new OutputStreamWriter(conn.getOutputStream(), CHARSET);
            // 发送请求参数
            out.write(params);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(conn.getInputStream(), CHARSET));
            String result = "";
            while ((result = in.readLine()) != null) {
				return result;
			}
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！"+e);
            e.printStackTrace();
        } finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
        return null;
    }  
    
    /**
     * 包装HTTP请求参数
     * @param objs 对象数组。支持(Object、Map)
     * @return name1=value1&name2=value2
     */
    public static String packParam(Object[] objs) {
		if (objs == null || objs.length == 0) {
			return "";
		}
		StringBuilder sb = new StringBuilder();
		for (Object obj : objs) {
			if (obj == null) {
				continue;
			}
			if (obj instanceof Map) {
				@SuppressWarnings("unchecked")
				Map<String, Object> map = (Map<String, Object>) obj;
				map.forEach((k, v) -> {
					sb.append(sb.length() > 0 ? "&" : "");
					sb.append(k);
					sb.append("=");
					sb.append(v);
				});
			} else {
				Field[] fields = obj.getClass().getDeclaredFields();
				for (Field field : fields) {
					if ("serialVersionUID".equals(field.getName())) {
						continue;
					}
					field.setAccessible(true);
					try {
						if (field.get(obj) != null) {
							sb.append(sb.length() > 0 ? "&" : "");
							sb.append(field.getName());
							sb.append("=");
							sb.append(field.get(obj));
						}
					} catch (IllegalArgumentException e) {
						// ignore
					} catch (IllegalAccessException e) {
						// ignore
					}
				}
			}
		}
		return sb.toString();
	}
    
    /**
	 * 判断url是否带有参数
	 * @param url
	 * @return true:带有参数用&，false:未带参数用?
	 */
	public static boolean isParamUrl(String url){
		if (StringUtils.isBlank(url)) {
			return false;
		}
		if (url.indexOf("?") == -1) {
			return false;
		} else {
			return true;
		}
	}
    
}