package com.hd.tsa.action;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hd.sfw.core.utils.CollectionUtils;
import com.hd.sfw.core.utils.PropertiesUtils;
import com.hd.sys.action.base.ActionResult;

@Controller
@RequestMapping("/Base64ToImg")
public class Base64ToImg {
	
	private static Logger logger = Logger.getLogger(Base64ToImg.class);
	
	private static	String url = PropertiesUtils.getProperty(Base64ToImg.class, "propftp", "ftpUrl");
	private static	int port= Integer.valueOf(PropertiesUtils.getProperty(Base64ToImg.class, "propftp", "ftpPort"));
	private static String username = PropertiesUtils.getProperty(Base64ToImg.class, "propftp", "ftpUserName");
	private static String password = PropertiesUtils.getProperty(Base64ToImg.class, "propftp", "ftpPassword");

	/**
	 * base64字符串转化成图片(单张图片)
	 * 对字节数组字符串进行Base64解码并生成图片
	 * @param imgStr
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/GenerateImage")
	public ActionResult GenerateImage(String imgStr, String model) { 
		ActionResult result = new ActionResult();
	    try { 
	    	//Base64解码 
	    	if (imgStr != null && imgStr!="") {
				imgStr = imgStr.replaceAll("~", "+");
				byte[] b = Base64.getDecoder().decode(imgStr);
				for(int i=0;i<b.length;++i) { 
					if(b[i]<0) {//调整异常数据 
						b[i]+=256; 
					} 
				} 
				//生成jpeg图片 
				String imgFilePath = String.valueOf(new Date().getTime())+".jpg";//新生成的图片
				InputStream input = new ByteArrayInputStream(b);
				String path = PropertiesUtils.getProperty(Base64ToImg.class, "propftp", "ftpRoot")+model;
				// 上传 连接ftp服务器 
				FTPClient ftp = new FTPClient();  
				int reply;  
				ftp.setDefaultTimeout(150000);
				ftp.connect(url, port);// 连接FTP服务器  
				// 如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器  
				ftp.login(username, password);// 登录  
				reply = ftp.getReplyCode();  
				if (!FTPReply.isPositiveCompletion(reply)) {  
					ftp.disconnect();  
					throw new RuntimeException("ftp连接失败！"); 
				}
				ftp.setFileType(FTPClient.BINARY_FILE_TYPE);    			
				ftp.makeDirectory(path); //创建目录
				ftp.changeWorkingDirectory(path);//切换目录
				ftp.setBufferSize(1024);//设置缓冲区 如果未设置该参数，将会一个字节一个字节的读取数据
				ftp.setControlEncoding("UTF-8");
				ftp.enterLocalPassiveMode(); //遍历目录
				ftp.storeFile(imgFilePath,input);  
				String filepath = path+"/"+imgFilePath;
				result.setSuccess(true);
				result.put("filepath", filepath);
			}
	    } catch (Exception e)  { 
	    	logger.error("上传失败"+e.getMessage(), e);
	    	e.printStackTrace();
	    	result.setMessage("上传失败");
	    } 
	    return result;
	} 
	
	
	/**
	 * base64字符串转化成图片(单张图片)
	 * 对字节数组字符串进行Base64解码并生成图片
	 * @param imgStr
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/GenerateImages")
	public ActionResult GenerateImages(@RequestBody Map<String, String> map) { 
		ActionResult result = new ActionResult();
		String model = map.get("model");
		if (CollectionUtils.isNotEmpty(map)) {
			Map<String, String> newMap = new HashMap<>();
			for (String key : map.keySet()) {
				if (!key.equals("model")) {
					try { 
						//Base64解码 
						String imgstr = map.get(key);
						imgstr = imgstr.replaceAll("~", "+");
						logger.info("imgstr-"+imgstr);
						byte[] b = Base64.getDecoder().decode(imgstr);
						for(int i=0;i<b.length;++i) { 
							if(b[i]<0) {//调整异常数据 
								b[i]+=256; 
							} 
						} 
						//生成jpeg图片 
						String imgFilePath = String.valueOf(new Date().getTime())+".jpg";//新生成的图片
						InputStream input = new ByteArrayInputStream(b);
						String path = PropertiesUtils.getProperty(Base64ToImg.class, "propftp", "ftpRoot")+model;
						// 上传 连接ftp服务器 
						FTPClient ftp = new FTPClient();  
						int reply;  
						ftp.setDefaultTimeout(150000);
						ftp.connect(url, port);// 连接FTP服务器  
						// 如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器  
						ftp.login(username, password);// 登录  
						reply = ftp.getReplyCode();  
						if (!FTPReply.isPositiveCompletion(reply)) {
							ftp.disconnect();  
							throw new RuntimeException("ftp连接失败！"); 
						}
						ftp.setFileType(FTPClient.BINARY_FILE_TYPE);    			
						ftp.makeDirectory(path); //创建目录
						ftp.changeWorkingDirectory(path);//切换目录
						ftp.setBufferSize(1024);//设置缓冲区 如果未设置该参数，将会一个字节一个字节的读取数据
						ftp.setControlEncoding("UTF-8");
						ftp.enterLocalPassiveMode(); //遍历目录
						ftp.storeFile(imgFilePath,input);  
						String filepath = path+"/"+imgFilePath;
						newMap.put(key, filepath);
						result.setSuccess(true);
					} catch (Exception e) { 
						newMap.put(key, "");
						logger.error("上传失败"+e.getMessage(), e);
						e.printStackTrace();
					}
				}
			}
			result.put("paths", newMap);
		}
		return result;
	} 
}
