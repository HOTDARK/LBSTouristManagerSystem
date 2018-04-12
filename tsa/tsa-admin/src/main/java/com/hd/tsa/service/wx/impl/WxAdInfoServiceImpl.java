package com.hd.tsa.service.wx.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hd.tsa.dao.WxAdInfoMapper;
import com.hd.tsa.entity.WxAdInfo;
import com.hd.tsa.service.wx.WxAdInfoService;
import com.hd.sfw.core.model.Pagination;

@Service
public class WxAdInfoServiceImpl implements WxAdInfoService {

	@Autowired
	private WxAdInfoMapper wxAdInfoMapper;
	
	@Override
	public void save(WxAdInfo entity) throws Exception {
		try {
			if(entity.getAdPosition() != null && "004002".equals(entity.getAdPosition())){
				WxAdInfo checkEntity = new WxAdInfo();
				checkEntity.setAdPosition("004002"); //微信广告位置 004002--底部固定 只投放一条
				checkEntity.setDeleteFlag(0); //删除标记 0--未删除 1--已删除
				checkEntity.setDeliveryFlag(1); //投放标记 0--未投放 1--已投放
				List<WxAdInfo> list = wxAdInfoMapper.findByCondition(checkEntity);
				if(list != null && list.size() > 0){
					for (WxAdInfo wxAdInfo : list) {
						wxAdInfo.setDeliveryFlag(0);
						wxAdInfoMapper.updateByPK(wxAdInfo);
					}
				}
			}
			wxAdInfoMapper.insert(entity);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public void deleteById(Long id) throws Exception {
		try {
			wxAdInfoMapper.deleteByPK(id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public void updateById(WxAdInfo entity) throws Exception {
		try {
			if(entity.getAdPosition() != null && "004002".equals(entity.getAdPosition())){
				if(entity.getDeliveryFlag() != null && entity.getDeliveryFlag() == 1){
					WxAdInfo checkEntity = new WxAdInfo();
					checkEntity.setAdPosition("004002"); //微信广告位置 004002--底部固定 只投放一条
					checkEntity.setDeleteFlag(0); //删除标记 0--未删除 1--已删除
					checkEntity.setDeliveryFlag(1); //投放标记 0--未投放 1--已投放
					List<WxAdInfo> list = wxAdInfoMapper.findByCondition(checkEntity);
					if(list != null && list.size() > 0){
						for (WxAdInfo wxAdInfo : list) {
							wxAdInfo.setDeliveryFlag(0);
							wxAdInfoMapper.updateByPK(wxAdInfo);
						}
					}
				}
			}
			wxAdInfoMapper.updateByPK(entity);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public WxAdInfo findById(Long id) throws Exception {
		return wxAdInfoMapper.findByPK(id);
	}

	@Override
	public List<WxAdInfo> findByCondition(WxAdInfo entity) throws Exception {
		return wxAdInfoMapper.findByCondition(entity);
	}

	@Override
	public Pagination<WxAdInfo> findPageByCondition(WxAdInfo entity) throws Exception {
		int num = wxAdInfoMapper.findNumByCondition(entity);
		if (num == 0) {
			return new Pagination<WxAdInfo>(0, new ArrayList<WxAdInfo>());
		} else {
			List<WxAdInfo> webAdInfos = wxAdInfoMapper.findByPage(entity, entity.getiDisplayStart(), entity.getiDisplayLength());
			return new Pagination<WxAdInfo>(num, webAdInfos);
		}
	}

}
