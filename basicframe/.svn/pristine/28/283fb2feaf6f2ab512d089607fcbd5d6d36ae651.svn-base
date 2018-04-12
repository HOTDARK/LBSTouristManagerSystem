package com.hd.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hd.sfw.dao.mybatis.BaseMapper;
import com.hd.sys.entity.SysDict;

/**
 * <p>类名：SysDictMapper </p>
 * <p>描述：业务类型数据访问层接口类 </p>
 * <p>作者：somnuscy </p>
 * <p>时间：2014年12月8日 星期一 </p>
 */
public interface SysDictMapper extends BaseMapper<SysDict, String> {
	
	/**
	 * 根据父级编码查找系统字典信息
	 * @param parentCode
	 * @return
	 */
	public List<SysDict> findSysDictByParent(String parentCode);
	/**
	 * 根据父级编码、应用标识查找系统字典信息
	 * @param parentCode
	 * @param applicationFlag
	 * @return
	 */
	public List<SysDict> findByAppFlag(@Param("parentCode")String parentCode, @Param("appFlag")String applicationFlag);
}
