/*
 * Copyright © 2014, Wangding (Chengdu) Tech Co.,Ltd. 
 * All right reserved.
 */
package com.hd.api.client.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hd.api.client.dao.WebServiceConfigureMapper;
import com.hd.api.client.service.WebServiceConfigureService;
import com.hd.sfw.webservice.model.WebServiceConfigure;

/**
 * webservice配置信息service实现
 * @version	0.0.1
 * @author	<a href="mailto:sunjian@iwangding.com">孙剑</a>
 * @date	2014-9-11 上午9:35:50
 */
@Service
public class WebServiceConfigureServiceImpl implements WebServiceConfigureService {
	
	@Autowired
	private WebServiceConfigureMapper webServiceConfigureMapper;

	@Override
	public List<WebServiceConfigure> findAll() {
		return webServiceConfigureMapper.findByCondition(new WebServiceConfigure());
	}

	@Override
	public List<WebServiceConfigure> findByCondition(
			WebServiceConfigure condition, int page, int limit) {
		if(page<1){
			page=1;
		}
		int start = (page-1)*limit;
		return webServiceConfigureMapper.findByPage(condition, start, limit);
	}

	@Override
	public int getTotalCount(WebServiceConfigure condition) {
		return webServiceConfigureMapper.findNumByCondition(condition);
	}

	@Override
	public void add(WebServiceConfigure configure) {
		webServiceConfigureMapper.insert(configure);
	}

	@Override
	public void update(WebServiceConfigure configure) {
		webServiceConfigureMapper.updateByPK(configure);
	}

	@Override
	public void delete(String id) {
		webServiceConfigureMapper.deleteByPK(id);
	}

	@Override
	public WebServiceConfigure findById(String id) {
		return webServiceConfigureMapper.findByPK(id);
	}

}
