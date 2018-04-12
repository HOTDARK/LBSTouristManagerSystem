package com.hd.tsa.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hd.tsa.dao.WxModulInfoMapper;
import com.hd.tsa.entity.WxModulInfo;
import com.hd.tsa.service.WxModulInfoService;

@Service
public class WxModulInfoServiceImpl implements WxModulInfoService{

	@Autowired
	private WxModulInfoMapper wxModulInfoMapper;
	@Override
	public List<WxModulInfo> findByCondition(WxModulInfo entity) {
		return wxModulInfoMapper.findByCondition(entity);
	}

}