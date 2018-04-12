package com.hd.tsa.service.common.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.hd.tsa.action.upload.UploadFileAction;
import com.hd.tsa.service.common.UploadFileService;
import com.hd.sfw.core.utils.CollectionUtils;
import com.hd.sfw.core.utils.PropertiesUtils;

@Service
public class UploadFileServiceImpl implements UploadFileService{
	private static	String url = PropertiesUtils.getProperty(UploadFileServiceImpl.class, "propftp", "ftpUrl");
	private static	int port= Integer.valueOf(PropertiesUtils.getProperty(UploadFileServiceImpl.class, "propftp", "ftpPort"));
	private static String username = PropertiesUtils.getProperty(UploadFileServiceImpl.class, "propftp", "ftpUserName");
	private static String password = PropertiesUtils.getProperty(UploadFileServiceImpl.class, "propftp", "ftpPassword");
	private static String strPath = PropertiesUtils.getProperty(UploadFileAction.class, "propftp", "ftpRoot");
	
	@Override
	public OutputStream downFile(String filepath,OutputStream os) throws Exception {
		String remotePath = "";
		String ftpfileName = "";
		if(filepath!=null&&filepath.length()>0){
		    ftpfileName= filepath.substring(filepath.lastIndexOf("/")+1);
		    remotePath = filepath.substring(0, filepath.lastIndexOf("/"));
		}
		if(remotePath.length()>0&&ftpfileName.length()>0){
			  FTPClient ftp = new FTPClient();
			  int reply;
			  ftp.setControlEncoding("UTF-8");
			  ftp.connect(url, port);
			  // 如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器
			  ftp.login(username, password);// 登录			  
			  reply = ftp.getReplyCode();
			  if (!FTPReply.isPositiveCompletion(reply)) {
				 ftp.disconnect();
			  }
			  ftp.setFileType(FTPClient.BINARY_FILE_TYPE);  
			  ftp.enterLocalPassiveMode();
			  ftp.changeWorkingDirectory(remotePath);// 转移到FTP服务器目录
			  FTPFile[] fs = ftp.listFiles();
			  for (FTPFile ff : fs) {
				if (ff.getName().equals(ftpfileName)) {			
					ftp.retrieveFile(ff.getName(), os);
				}
			  }
			  ftp.logout();
          }
		return os;
	}
	
	@Override
	public List<Map<String, String>> uploadFiles(CommonsMultipartFile[] files , String model) throws Exception {
		List<Map<String, String>> list = new ArrayList<>();
		String path = strPath + model;
		String filepath = "";
		File tempDirPath = new File(path);
		if (!tempDirPath.exists()) {
			tempDirPath.mkdirs();
		}
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
		for(int i=0;i<files.length;i++){
			Map<String,String> map = new HashMap<>();
			CommonsMultipartFile file = files[i];
			String originalFileName = file.getOriginalFilename();
			int index = file.getOriginalFilename().lastIndexOf('.');
			String fileExtension = originalFileName.substring(index + 1, originalFileName.length());
			String fileName = new Date().getTime() + "." + fileExtension;
			Long fileSize = file.getSize();
			ftp.storeFile(fileName,file.getInputStream());
		    filepath = path +"/" + fileName;
			map.put("filePath", filepath);
			map.put("oldFileName", originalFileName);
			map.put("fileName",fileName);
			map.put("fileSize",fileSize+"");
			map.put("fileExtension",fileExtension);
			list.add(map);
		}
		return list;
	}
	
	@Override
	public Map<String, Object> uploadWxFiles(Map<String, File> files, String model) throws Exception {
		Map<String, Object> resMap = new HashMap<>();
		String path = strPath + model;
		File tempDirPath = new File(path);
		if (!tempDirPath.exists()) {
			tempDirPath.mkdirs();
		}
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
        if (CollectionUtils.isNotEmpty(files)) {
			files.forEach((k, v)->{
				try {
	        		Map<String,String> map = new HashMap<>();
	        		File file = files.get(k);
	        		String originalFileName = file.getName();
	        		int index = file.getName().lastIndexOf('.');
	        		String fileExtension = originalFileName.substring(index + 1, originalFileName.length());
	        		String fileName = new Date().getTime() + "." + fileExtension;
	        		Long fileSize = file.length();
	        		InputStream input = new FileInputStream(file);
	        		ftp.storeFile(fileName,input);
	        		map.put("filePath", path+"/"+fileName);
	        		map.put("oldFileName", fileName);
	        		map.put("fileName",fileName);
	        		map.put("fileSize",fileSize+"");
	        		map.put("fileExtension",fileExtension);
	        		resMap.put(k, map);
	        		input.close();
				} catch (Exception e) {
					resMap.put(k, null);
					e.printStackTrace();
				}
			});
        }
		return resMap;
	}
	
	@Override
	public Map<String, String> uploadWxImgs(Map<String, File> fileMap, String model) throws Exception {
		Map<String, String> map = new HashMap<>();
		String path = strPath + model;
		String filepath = "";
		File tempDirPath = new File(path);
		if (!tempDirPath.exists()) {
			tempDirPath.mkdirs();
		}
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
        for (String key : fileMap.keySet()) {
        	try {
        		File file = fileMap.get(key);//files.get(i);
        		String originalFileName = file.getName();
        		int index = file.getName().lastIndexOf('.');
        		String fileExtension = originalFileName.substring(index + 1, originalFileName.length());
        		String fileName = new Date().getTime() + "." + fileExtension;
        		InputStream input = new FileInputStream(file);
        		ftp.storeFile(fileName,input);
        		filepath = path +"/" + fileName;
        		map.put(key, filepath);
        		input.close();
			} catch (Exception e) {
				map.put(key, "");
				e.printStackTrace();
			}
		}
		return map;
	}
}