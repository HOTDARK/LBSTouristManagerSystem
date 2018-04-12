package com.hd.tsa.service.wx;

import java.util.List;
import java.util.Map;

import com.hd.tsa.entity.WxActivityInfo;
import com.hd.tsa.entity.WxActivityRel;
import com.hd.sys.service.base.BaseService;

/**
 * 微信活动信息
 * @author somnuscy
 *
 */
public interface WxActivityInfoService extends BaseService<WxActivityInfo, Long> {

	/**
	 * 验证活动信息及素材不为空
	 * @param id
	 * @throws Exception
	 */
	public boolean checkActivityInfo(Long id)throws Exception;
	
	/**
	 * 查询活动信息
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Map<Integer, List<WxActivityRel>> queryActivityDetail(Long id)throws Exception;
}
