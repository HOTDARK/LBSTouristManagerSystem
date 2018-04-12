package com.hd.sys.service;

import java.util.List;

import com.hd.sys.entity.SysDict;
import com.hd.sys.service.base.BaseService;

/**
 * <p>类名：SysDictService </p>
 * <p>描述：业务类型业务层接口类 </p>
 * <p>作者：somnuscy </p>
 * <p>时间：2014年12月8日 星期一 </p>
 */
public interface SysDictService extends BaseService<SysDict, String> {
	
	/**
	 * <p>描述： 激活(规则：激活自己及父级状态)</p>
	 * <p>日期：2014-12-17 下午03:58:03 </p>
	 * @param typeDict
	 * @throws Exception
	 */
	public void updateDictActivate(SysDict typeDict) throws Exception;
	/**
	 * <p>描述：冻结(规则：冻结自己及下级状态) </p>
	 * <p>日期：2014-12-17 上午10:49:37 </p>
	 * @param typeDict
	 * @throws Exception
	 */
	public void updateDictFreeze(SysDict typeDict) throws Exception;
	/**
	 * 根据父级编码查找系统字典信息
	 * @param typeCode
	 * @return
	 * @throws Exception
	 */
	public List<SysDict> findSysDictByParent(String parentCode) throws Exception;
	/**
	 * 根据父级编码、应用标识查找系统字典信息
	 * @param parentCode
	 * @param applicationFlag
	 * @return
	 * @throws Exception
	 */
	public List<SysDict> findByAppFlag(String parentCode, String applicationFlag) throws Exception;
}
