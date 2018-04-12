package com.hd.tsa.service;

import java.util.List;

import com.hd.tsa.entity.WxAdInfo;

public interface WxAdInfoService {

	public List<WxAdInfo> findByCondition(WxAdInfo adInfo);
}
