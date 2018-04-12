package com.hd.sfw.core.support;

import java.util.Map;

/**
 * 对poperties文件自动加载进行支持
 * @author sunjian
 * @version V1.0, 2013-12-4 下午04:11:55
 */
public interface PropertiesLoadSupport {

	/**
	 * 返回properties文件路径
	 * @return
	 */
	public String getPropertiesFilePath();
	
	/**
	 * 在成功加载或者文件修改时会自动调用本方法
	 * @param map
	 */
	public void loadProperties(Map<String, String> map);
	
	/**
	 * 返回 true表示支持对getPropertiesFilePath()返回的文件进行检测，
	 * 如果发生修改则调用loadProperties()方法
	 * @return
	 */
	public boolean reloadablePropertiesFile();
}
