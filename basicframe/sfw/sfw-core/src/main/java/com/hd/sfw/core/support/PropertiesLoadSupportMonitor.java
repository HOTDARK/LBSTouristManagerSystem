package com.hd.sfw.core.support;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.hd.sfw.core.utils.PropertiesUtils;

/**
 * 在spring加载时扫描PropertiesLoadSupport的实现类以实现自动加载properties文件
 * @author sunjian
 * @version V1.0, 2013-12-4 下午04:26:19
 */
public class PropertiesLoadSupportMonitor implements BeanPostProcessor,InitializingBean{
	
	private final static Logger log = Logger.getLogger(PropertiesLoadSupportMonitor.class);
	
	private List<PropertiesFile> items = new ArrayList<PropertiesFile>();
	
	//是否启用全局的自动加载
	private boolean reloadable = false;
	
	//间隔时间秒数
	private long interval = 60;

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName)
			throws BeansException {
		
		if(bean instanceof PropertiesLoadSupport){
			PropertiesLoadSupport pls = (PropertiesLoadSupport)bean;
			File file = load(pls);
			items.add(new PropertiesFile(file.lastModified(), pls, file));
		}
		
		return bean;
	}

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName)
			throws BeansException {
		return bean;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		log.info("开始处理properties文件自动加载");
		if(reloadable){
			Thread thread = new ReloadThread("PROPERTIES_FILE_RELOADER");
			thread.setDaemon(true);
			thread.start();
		}
	}
	
	/**
	 * 加载properties
	 * @param pls
	 * @return 返回properties文件的File对象
	 */
	private File load(PropertiesLoadSupport pls){
		Resource rs = new ClassPathResource(pls.getPropertiesFilePath());
		if(rs.isReadable()){
			log.info("读取properties文件:"+pls.getPropertiesFilePath());
			InputStream in = null;
			try {
				in = rs.getInputStream();
				pls.loadProperties(PropertiesUtils.getMapByInputStream(in));
				return rs.getFile();
			} catch (IOException e) {
				throw new RuntimeException("读取解析properties文件失败:"+pls.getPropertiesFilePath(),e);
			}finally{
				if(in!=null){
					try {
						in.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			
		}else{
			throw new RuntimeException("文件不可读："+pls.getPropertiesFilePath());
		}
	}
	
	/**
	 * 获取检测文件
	 * @return the interval
	 */
	public long getInterval() {
		return interval;
	}

	/**
	 * 设置检测文件是否改变的间隔时间
	 * @param interval 间隔秒数
	 */
	public void setInterval(long interval) {
		this.interval = interval;
	}

	/**
	 * 是否启用自动重载
	 * @return the reloadable
	 */
	public boolean isReloadable() {
		return reloadable;
	}

	/**
	 * 设置是否启用自动重载
	 * @param reloadable
	 */
	public void setReloadable(boolean reloadable) {
		this.reloadable = reloadable;
	}

	private class ReloadThread extends Thread{
		
		public ReloadThread(String name) {
			super(name);
		}
		
		@Override
		public void run() {
			while(true){
				//每隔
				try {
					Thread.sleep(interval*1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				//循环
				for(PropertiesFile pf:items){
					if(pf.pls.reloadablePropertiesFile()){
						long lm = pf.file.lastModified();
						if(lm!=pf.lastModified){
							log.info("发现更改进行reload");
							load(pf.pls);
							pf.lastModified = lm;
						}
					}
				}
			}
			
		}
	}

	private static class PropertiesFile{
		private long lastModified;
		private PropertiesLoadSupport pls;
		private File file;
		
		/**
		 * @param lastModified
		 * @param pls
		 * @param file
		 */
		public PropertiesFile(long lastModified, PropertiesLoadSupport pls,
				File file) {
			this.lastModified = lastModified;
			this.pls = pls;
			this.file = file;
		}
		
	}

}
