package com.hd.tsa.service.wx.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hd.tsa.dao.WxModulInfoMapper;
import com.hd.tsa.entity.WxModulInfo;
import com.hd.tsa.service.wx.WxModulInfoService;
import com.hd.sfw.core.model.Pagination;

@Service
public class WxModulInfoServiceImpl implements WxModulInfoService {

	@Autowired
	private WxModulInfoMapper wxModulInfoMapper;
	
	@Override
	public void save(WxModulInfo entity) throws Exception {
		try {
			wxModulInfoMapper.insert(entity);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public void deleteById(Long id) throws Exception {
		try {
			wxModulInfoMapper.deleteByPK(id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public void updateById(WxModulInfo entity) throws Exception {
		try {
			wxModulInfoMapper.updateByPK(entity);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public WxModulInfo findById(Long id) throws Exception {
		return wxModulInfoMapper.findByPK(id);
	}

	@Override
	public List<WxModulInfo> findByCondition(WxModulInfo entity) throws Exception {
		return wxModulInfoMapper.findByCondition(entity);
	}

	@Override
	public Pagination<WxModulInfo> findPageByCondition(WxModulInfo entity) throws Exception {
		int num = wxModulInfoMapper.findNumByCondition(entity);
		if (num == 0) {
			return new Pagination<WxModulInfo>(0, new ArrayList<WxModulInfo>());
		} else {
			List<WxModulInfo> webAdInfos = wxModulInfoMapper.findByPage(entity, entity.getiDisplayStart(), entity.getiDisplayLength());
			return new Pagination<WxModulInfo>(num, webAdInfos);
		}
	}

}
