package com.hd.tsa.service.wx.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hd.tsa.dao.WxActivityRelMapper;
import com.hd.tsa.entity.WxActivityRel;
import com.hd.tsa.service.wx.WxActivityRelService;
import com.hd.sfw.core.model.Pagination;

@Service
public class WxActivityRelServiceImpl implements WxActivityRelService{

	@Autowired
	private WxActivityRelMapper wxActivityRelMapper;
	
	@Override
	public void save(WxActivityRel entity) throws Exception {
		try {
			wxActivityRelMapper.insert(entity);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public void deleteById(Long id) throws Exception {
		try {
			wxActivityRelMapper.deleteByPK(id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public void updateById(WxActivityRel entity) throws Exception {
		try {
			wxActivityRelMapper.updateByPK(entity);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public WxActivityRel findById(Long id) throws Exception {
		return wxActivityRelMapper.findByPK(id);
	}

	@Override
	public List<WxActivityRel> findByCondition(WxActivityRel entity) throws Exception {
		return wxActivityRelMapper.findByCondition(entity);
	}

	@Override
	public Pagination<WxActivityRel> findPageByCondition(WxActivityRel entity) throws Exception {
		int num = wxActivityRelMapper.findNumByCondition(entity);
		if(num > 0){
			return new Pagination<>(num , wxActivityRelMapper.findByPage(entity, entity.getiDisplayStart(), entity.getiDisplayLength()));
		}else{
			return new Pagination<>(0, new ArrayList<>());
		}
	}

}
