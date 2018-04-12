package com.hd.sfw.dao.mybatis;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.annotations.Param;

/**
 * mybatis mapper基础接口
 * @author somnuscy
 *
 * @param <T> 实体
 * @param <PK> 主键
 */
public interface BaseMapper<T, PK extends Serializable>{
	
	/**
	 * 单条添加
	 * @param entity
	 */
	public void insert(T entity);
	/**
	 * 按主键ID删除
	 * @param id
	 */
	public void deleteByPK(PK id);
	/**
	 * 按过主键ID更新
	 * @param entity
	 */
	public void updateByPK(T entity);
	/**
	 * 按主键ID查询
	 * @param id
	 * @return
	 */
	public T findByPK(PK id);
	/**
	 * 按对象条件查询实体
	 * @param entity
	 * @return
	 */
	public List<T> findByCondition(@Param("model")T entity);
	/**
	 * 按条件查询实体并分页
	 * @param entity
	 * @param start
	 * @param limit
	 * @return
	 */
	public List<T> findByPage(@Param("model")T entity, @Param("start")int start, @Param("limit")int limit);
	/**
	 * 按条件查询总记录数
	 * @param entity
	 * @return
	 */
	public int findNumByCondition(@Param("model")T entity);
}
