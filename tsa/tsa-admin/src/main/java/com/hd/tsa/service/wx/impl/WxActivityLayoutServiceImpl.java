package com.hd.tsa.service.wx.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hd.tsa.dao.WxActivityLayoutMapper;
import com.hd.tsa.dao.WxActivityRelMapper;
import com.hd.tsa.entity.WxActivityLayout;
import com.hd.tsa.entity.WxActivityRel;
import com.hd.tsa.service.wx.WxActivityLayoutService;
import com.hd.sfw.core.model.Pagination;

@Service
public class WxActivityLayoutServiceImpl implements WxActivityLayoutService{

	@Autowired
	private WxActivityLayoutMapper wxActivityLayoutMapper;
	
	@Autowired
	private WxActivityRelMapper wxActivityRelMapper;
	
	@Override
	public void save(WxActivityLayout entity) throws Exception {
		try {
			wxActivityLayoutMapper.insert(entity);
			if(entity.getColumnsNum() != null && entity.getColumnsNum() > 0){
				for (int i = 0; i < entity.getColumnsNum(); i++) {
					WxActivityRel rel = new WxActivityRel();
					rel.setLayoutId(entity.getId());
					rel.setSeqNum(i+1);
					wxActivityRelMapper.insert(rel);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public void deleteById(Long id) throws Exception {
		try {
			wxActivityLayoutMapper.deleteByPK(id);
			wxActivityRelMapper.deleteByLayoutId(id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public void updateById(WxActivityLayout entity) throws Exception {
		try {
			wxActivityLayoutMapper.updateByPK(entity);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public WxActivityLayout findById(Long id) throws Exception {
		return wxActivityLayoutMapper.findByPK(id);
	}

	@Override
	public List<WxActivityLayout> findByCondition(WxActivityLayout entity) throws Exception {
		return wxActivityLayoutMapper.findByCondition(entity);
	}

	@Override
	public Pagination<WxActivityLayout> findPageByCondition(WxActivityLayout entity) throws Exception {
		int num = wxActivityLayoutMapper.findNumByCondition(entity);
		if(num > 0){
			return new Pagination<>(num, wxActivityLayoutMapper.findByPage(entity, entity.getiDisplayStart(), entity.getiDisplayLength()));
		}else{
			return new Pagination<>(0, new ArrayList<>());
		}
	}

}
