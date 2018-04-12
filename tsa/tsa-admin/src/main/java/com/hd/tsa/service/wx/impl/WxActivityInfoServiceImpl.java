package com.hd.tsa.service.wx.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hd.tsa.dao.WxActivityInfoMapper;
import com.hd.tsa.dao.WxActivityLayoutMapper;
import com.hd.tsa.dao.WxActivityRelMapper;
import com.hd.tsa.entity.WxActivityInfo;
import com.hd.tsa.entity.WxActivityLayout;
import com.hd.tsa.entity.WxActivityRel;
import com.hd.tsa.service.wx.WxActivityInfoService;
import com.hd.sfw.core.model.Pagination;

@Service
public class WxActivityInfoServiceImpl implements WxActivityInfoService {

	@Autowired
	private WxActivityInfoMapper wxActivityInfoMapper;
	
	@Autowired
	private WxActivityLayoutMapper wxActivityLayoutMapper;
	
	@Autowired
	private WxActivityRelMapper wxActivityRelMapper;
	
	@Override
	public void save(WxActivityInfo entity) throws Exception {
		try {
			wxActivityInfoMapper.insert(entity);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public void deleteById(Long id) throws Exception {
		try {
			wxActivityInfoMapper.deleteByPK(id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public void updateById(WxActivityInfo entity) throws Exception {
		try {
			//如果为应用状态，则禁用其他已应用的活动
			if(entity.getUseFlag() != null && entity.getUseFlag() == 1){
				WxActivityInfo old = new WxActivityInfo();
				old.setDeleteFlag(0);
				old.setUseFlag(1);
				List<WxActivityInfo> list = wxActivityInfoMapper.findByCondition(old);
				if(list != null && list.size() > 0){
					for (WxActivityInfo wxActivityInfo : list) {
						wxActivityInfo.setUseFlag(0);
						wxActivityInfoMapper.updateByPK(wxActivityInfo);
					}
				}
			}
			wxActivityInfoMapper.updateByPK(entity);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public WxActivityInfo findById(Long id) throws Exception {
		return wxActivityInfoMapper.findByPK(id);
	}

	@Override
	public List<WxActivityInfo> findByCondition(WxActivityInfo entity) throws Exception {
		return wxActivityInfoMapper.findByCondition(entity);
	}

	@Override
	public Pagination<WxActivityInfo> findPageByCondition(WxActivityInfo entity) throws Exception {
		int num = wxActivityInfoMapper.findNumByCondition(entity);
		if (num == 0) {
			return new Pagination<WxActivityInfo>(0, new ArrayList<WxActivityInfo>());
		} else {
			List<WxActivityInfo> webAdInfos = wxActivityInfoMapper.findByPage(entity, entity.getiDisplayStart(), entity.getiDisplayLength());
			return new Pagination<WxActivityInfo>(num, webAdInfos);
		}
	}

	@Override
	public boolean checkActivityInfo(Long id) throws Exception {
		int num = wxActivityInfoMapper.queryInfoAndLayoutAndRel(id);
		if(num > 0){
			return false;
		}
		return true;
	}

	@Override
	public Map<Integer, List<WxActivityRel>> queryActivityDetail(Long id) throws Exception {
		Map<Integer, List<WxActivityRel>> map = new LinkedHashMap<>();
		WxActivityLayout layout = new WxActivityLayout();
		layout.setInfoId(id);
		layout.setSortColumns("lineNum asc");
		List<WxActivityLayout> layoutList = wxActivityLayoutMapper.findByCondition(layout);
		if(layout != null){
			int num = 1;
			for (WxActivityLayout wxActivityLayout : layoutList) {
				WxActivityRel rel = new WxActivityRel();
				rel.setLayoutId(wxActivityLayout.getId());
				rel.setSortColumns("seqNum asc");
				List<WxActivityRel> relList = wxActivityRelMapper.findByCondition(rel);
				if(relList != null){
					map.put(num, relList);
					num++;
				}
			}
		}
		return map;
	}

}
