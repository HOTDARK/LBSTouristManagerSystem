package com.hd.api.server.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hd.api.server.dao.WebServiceNorthMapper;
import com.hd.api.server.entity.WebServiceNorth;
import com.hd.api.server.service.WebServiceNorthService;

@Component
public class WebServiceNorthServiceImpl implements WebServiceNorthService{

	private static final Logger log = Logger.getLogger(WebServiceNorthServiceImpl.class);
	
	@Autowired
	private WebServiceNorthMapper mapper;
	
	@Override
	public void addWebServiceLog(WebServiceNorth northboundInterfaceInfo) {
		if (northboundInterfaceInfo == null) {
			log.debug("没有要插入的日志");
			return;
		}
		mapper.addWebServiceLog(northboundInterfaceInfo);
	}

}
