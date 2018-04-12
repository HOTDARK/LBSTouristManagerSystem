/*
 * Copyright © 2014, Wangding (Chengdu) Tech Co.,Ltd. 
 * All right reserved.
 */
package com.hd.api.client.service;

import java.util.List;

import com.hd.sfw.webservice.model.WebServiceConfigure;

/**
 * webservice配置service
 */
public interface WebServiceConfigureService {
	/**
	 * 查询所有
	 * @return
	 */
	public List<WebServiceConfigure> findAll();
	
	/**
	 * 分页查询
	 * @param condition
	 * @param start
	 * @param limit
	 * @return
	 */
	public List<WebServiceConfigure> findByCondition(WebServiceConfigure condition,int page,int limit);
	
	/**
	 * 获取满足条件的记录数
	 * @param condition
	 * @return
	 */
	public int getTotalCount(WebServiceConfigure condition);
	
	/**
	 * 添加
	 * @param configure
	 */
	public void add(WebServiceConfigure configure);
	
	/**
	 * 更新
	 * @param configure
	 */
	public void update(WebServiceConfigure configure);
	
	/**
	 * 删除
	 * @param id
	 */
	public void delete(String id);
	
	/**
	 * 根据id查询
	 * @param id
	 * @return
	 */
	public WebServiceConfigure findById(String id);
	
}
