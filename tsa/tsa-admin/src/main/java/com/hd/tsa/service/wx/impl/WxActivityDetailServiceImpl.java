package com.hd.tsa.service.wx.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hd.tsa.dao.WxActivityDetailMapper;
import com.hd.tsa.entity.WxActivityDetail;
import com.hd.tsa.service.wx.WxActivityDetailService;
import com.hd.sfw.core.model.Pagination;

@Service
public class WxActivityDetailServiceImpl implements WxActivityDetailService {
	
	@Autowired
	private WxActivityDetailMapper wxActivityDetailMapper;

	@Override
	public void save(WxActivityDetail entity) throws Exception {
		try {
			wxActivityDetailMapper.insert(entity);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public void deleteById(Long id) throws Exception {
		try {
			wxActivityDetailMapper.deleteByPK(id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public void updateById(WxActivityDetail entity) throws Exception {
		try {
			wxActivityDetailMapper.updateByPK(entity);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public WxActivityDetail findById(Long id) throws Exception {
		return wxActivityDetailMapper.findByPK(id);
	}

	@Override
	public List<WxActivityDetail> findByCondition(WxActivityDetail entity) throws Exception {
		return wxActivityDetailMapper.findByCondition(entity);
	}

	@Override
	public Pagination<WxActivityDetail> findPageByCondition(WxActivityDetail entity) throws Exception {
		int num = wxActivityDetailMapper.findNumByCondition(entity);
		if (num == 0) {
			return new Pagination<WxActivityDetail>(0, new ArrayList<WxActivityDetail>());
		} else {
			List<WxActivityDetail> webAdInfos = wxActivityDetailMapper.findByPage(entity, entity.getiDisplayStart(), entity.getiDisplayLength());
			return new Pagination<WxActivityDetail>(num, webAdInfos);
		}
	}

}
