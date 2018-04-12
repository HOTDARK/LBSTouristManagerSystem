/*
 * Copyright © 2014, Wangding (Chengdu) Tech Co.,Ltd. 
 * All right reserved.
 */
package com.hd.api.client.websvc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hd.api.client.service.WebServiceConfigureService;
import com.hd.sfw.webservice.WebServiceConfigureLoader;
import com.hd.sfw.webservice.model.WebServiceConfigure;

/**
 * 加载webservice配置信息
 */
@Component
public class SimpleWebServiceConfigureLoader implements WebServiceConfigureLoader {
	@Autowired
	private WebServiceConfigureService webServiceConfigureInfoService;
	
	@Override
	public List<WebServiceConfigure> getConfigs() {
		return webServiceConfigureInfoService.findAll();
	}

}
