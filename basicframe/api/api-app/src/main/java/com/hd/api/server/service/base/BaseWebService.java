package com.hd.api.server.service.base;

import java.io.Serializable;

import com.hd.sfw.core.model.BaseModel;

/**
 * WebService业务层公用接口
 * @author somnuscy
 *
 * @param <T> 实体
 * @param <TList> 实体列表
 * @param <PK> 主键
 */
public interface BaseWebService<T extends Serializable, TList extends Serializable, PK extends Serializable> {

	/**
	 * 保存单条实体
	 * @param entity 实体
	 * @throws Exception 保存实体可能发生的异常
	 */
	public BaseModel save(T entity) throws Exception;
	/**
	 * 按主键ID删除
	 * @param id 主键ID
	 * @throws Exception 按主键删除可能发生的异常
	 */
	public BaseModel deleteById(PK id) throws Exception;
	/**
	 * 按主键ID更新实体
	 * @param entity 实体
	 * @throws Exception 修改实体可能发生的异常
	 */
	public BaseModel updateById(T entity) throws Exception;
	/**
	 * 按主键ID查找
	 * @param id 主键ID
	 * @return 按ID查找的对象
	 * @throws Exception 查询数据可能发生的异常
	 */
	public T findById(PK id) throws Exception;
	/**
	 * 按条件查询(满足条件的所有数据)
	 * @param entity 条件实体
	 * @return 查询的到数据集合
	 * @throws Exception 查询数据可能发生的异常
	 */
	public TList findByCondition(T entity) throws Exception;
	/**
	 * 按条件分页查询(实体需要继承Pagination<T>)
	 * @param entity 实体条件
	 * @return 分页对象
	 * @throws Exception 查询数据可能发生的异常
	 */
	public TList findPageByCondition(T entity) throws Exception;

}
