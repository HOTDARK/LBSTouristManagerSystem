package com.hd.tsa.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hd.tsa.dao.WxAdInfoMapper;
import com.hd.tsa.entity.WxAdInfo;
import com.hd.tsa.service.WxAdInfoService;

@Service
public class WxAdInfoServiceImpl implements WxAdInfoService {

	@Autowired
	private WxAdInfoMapper wxAdInfoMapper;
	@Override
	public List<WxAdInfo> findByCondition(WxAdInfo entity) {
		return wxAdInfoMapper.findByCondition(entity);
	}

}
