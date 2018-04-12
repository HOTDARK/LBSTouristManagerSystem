package com.hd.tsa.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hd.tsa.dao.WxActivityDetailMapper;
import com.hd.tsa.entity.WxActivityDetail;
import com.hd.tsa.service.WxActivityDetailService;

@Service
public class WxActivityDetailServiceImpl implements WxActivityDetailService {

	@Autowired
	private WxActivityDetailMapper wxActivityDetailMapper;
	@Override
	public List<WxActivityDetail> findLayout() {
		return wxActivityDetailMapper.findLayout();
	}

}
