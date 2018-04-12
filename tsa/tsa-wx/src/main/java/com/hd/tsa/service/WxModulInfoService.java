package com.hd.tsa.service;

import java.util.List;

import com.hd.tsa.entity.WxModulInfo;

public interface WxModulInfoService {

	public List<WxModulInfo> findByCondition(WxModulInfo entity);
}
