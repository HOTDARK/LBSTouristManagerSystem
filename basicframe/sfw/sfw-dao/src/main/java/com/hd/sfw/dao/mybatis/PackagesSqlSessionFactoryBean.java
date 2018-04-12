package com.hd.sfw.dao.mybatis;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.log4j.Logger;
import org.mybatis.spring.SqlSessionFactoryBean;

import com.hd.sfw.core.utils.PackageUtils;

/**
 * Spring Mybatis整合
 * 通过通配符方式配置typeAliasesPackage
 * @author sunjian  
 * @version 1.0 2013-2-25
 */
public class PackagesSqlSessionFactoryBean extends SqlSessionFactoryBean{
	
	private final static Logger log = Logger.getLogger(PackagesSqlSessionFactoryBean.class);
	
	@Override
	public void setTypeAliasesPackage(String typeAliasesPackage) {
		List<String> list = PackageUtils.getPackages(typeAliasesPackage);
		if(list!=null&&list.size()>0){
			super.setTypeAliasesPackage(StringUtils.join(list.toArray(), ","));
		}else{
			log.warn("参数typeAliasesPackage:"+typeAliasesPackage+"，未找到任何包");
		}
	}

	@Override
	public void setPlugins(Interceptor[] plugins) {
		super.setPlugins(plugins);
	}
	
	
	
}
