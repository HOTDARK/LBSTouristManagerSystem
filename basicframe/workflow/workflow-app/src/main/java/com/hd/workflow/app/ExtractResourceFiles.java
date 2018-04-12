/*
 * Copyright © 2015, Wangding (Chengdu) Tech Co.,Ltd. 
 * All right reserved.
 */
package com.hd.workflow.app;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import com.hd.sfw.core.utils.IOUtils;

/**
 * 提取资源文件到webroot
 * @version	0.0.1
 * @author	<a href="mailto:sunjian@iwangding.com">孙剑</a>
 * @date	2015-5-4 上午10:36:47
 */
@Component
public class ExtractResourceFiles implements InitializingBean {
	
	private final static Logger log = LoggerFactory.getLogger(ExtractResourceFiles.class);
	
	/**
	 * 获取webroot的路径
	 * @return
	 */
	private File getBaseDir(){
		URL url = Thread.currentThread().getContextClassLoader().getResource("/");
		return new File(url.getFile()).getParentFile().getParentFile();
	}
	
	/**
	 * 判断是否是web app
	 * @return
	 */
	private boolean isWebApp(){
		URL url = Thread.currentThread().getContextClassLoader().getResource("/");
		if(url==null){
			return false;
		}
		
		return url.toString().indexOf("WEB-INF")!=-1;
	}
	
	/**
	 * 资源文件所在jar文件路径
	 * @return
	 */
	private String getResourceJar(){
		URL url = getClass().getResource("/files/modeler.jsp");
		
		if("jar".equals(url.getProtocol())){
			String str = url.getFile().split("\\!")[0];
			return str.replace("file:/", "");
		}else{
			return null;
		}
		
	}
	
	/**
	 * 提取资源文件
	 * @throws Exception
	 */
	private void extract() throws Exception{
		String jarPath = getResourceJar();
		if(jarPath==null){
			log.info("资源jar包为找到");
			return;
		}
		
		ZipFile zip = new ZipFile(jarPath);
		String baseDir = getBaseDir().getPath();
		
		Enumeration<? extends ZipEntry> enumeration = zip.entries();
		while(enumeration.hasMoreElements()){
			ZipEntry entry = enumeration.nextElement();
			if(entry.getName().startsWith("files/")&&!entry.isDirectory()){
				InputStream inputStream = zip.getInputStream(entry);
				if(inputStream!=null){
					copy(inputStream, baseDir+"/"+entry.getName().substring(6));
				}
			}
		}
		
	}
	
	private void copy(InputStream inputStream,String path){
		File file = new File(path);
		File dir = file.getParentFile();
		if(!dir.exists()){
			dir.mkdirs();
		}
		
		try {
			FileUtils.copyInputStreamToFile(inputStream, file);
		} catch (IOException e) {
			e.printStackTrace();
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
		} finally{
			IOUtils.closeQuietly(inputStream);
		}
		
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		
		if(!isWebApp()){
			return;
		}
		
		File baseDir = getBaseDir();
		
		//如果在根目录下存在modeler.jsp文件则不进行资源文件的提取
		if(new File(baseDir+"/modeler.jsp").isFile()){
			log.info("modeler.jsp已存在不进行资源文件提取");
			return;
		}
		
		String jarPath = getResourceJar();
		if(jarPath==null){
			log.info("资源jar包为找到");
		}
		
		log.info("提取资源文件.");
		extract();
	}

}
