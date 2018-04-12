package com.hd.sfw.core.model;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>类名：Pagination </p>
 * <p>描述：DateTables分页工具类 </p>
 * <p>时间：2014-12-4 下午2:30:22 </p>
 *
 * @param <T> 业务实体
 */
@SuppressWarnings("serial")
public class Pagination<T> extends BaseModel {

	/** 总记录数 */
	private Integer iTotalRecords; 
	
	/** 过滤后的总记录数 */
    private Integer iTotalDisplayRecords; 
    
	/** DataTable请求服务器端次数 */
	private Integer sEcho;
	
	/** 分页时每页跨度数量 */ 
	private Integer iDisplayStart = 0;
	
	/** 每页显示条数 */
	private Integer iDisplayLength = 10;
	
	/** 是否是查询总条数【true：是，false：否】 */
	private Boolean isQueryCount = true;

	/** 显示数据 */
	private List<T> data = new ArrayList<T>();
	
	/**  */
	public Pagination() {
		
	}
	
	/**
	 * 初始化分页对象
	 * @param iTotalRecords
	 * @param iDisplayStart
	 * @param iDisplayLength
	 * @param data
	 */
	public Pagination(Integer iTotalRecords, List<T> data) {
		this.iTotalRecords = iTotalRecords;
		this.iTotalDisplayRecords = iTotalRecords;
		this.data = data;
	}
	
	/**
	 * 初始化分页对象
	 * @param iTotalRecords
	 * @param iDisplayStart
	 * @param iDisplayLength
	 * @param data
	 */
	public Pagination(Integer iTotalRecords, Integer iDisplayStart, Integer iDisplayLength, List<T> data) {
		this.iTotalRecords = iTotalRecords;
		this.iTotalDisplayRecords = iTotalRecords;
		this.iDisplayStart = iDisplayStart;
		this.iDisplayLength = iDisplayLength;
		this.data = data;
	}

	/**
	 * @return 获取iTotalRecords
	 */
	public Integer getiTotalRecords() {
		return iTotalRecords;
	}

	/**
	 * @param iTotalRecords 要设置的 iTotalRecords
	 */
	public void setiTotalRecords(Integer iTotalRecords) {
		this.iTotalRecords = iTotalRecords;
	}

	/**
	 * @return 获取iTotalDisplayRecords
	 */
	public Integer getiTotalDisplayRecords() {
		return iTotalDisplayRecords;
	}

	/**
	 * @param iTotalDisplayRecords 要设置的 iTotalDisplayRecords
	 */
	public void setiTotalDisplayRecords(Integer iTotalDisplayRecords) {
		this.iTotalDisplayRecords = iTotalDisplayRecords;
	}

	/**
	 * @return 获取sEcho
	 */
	public Integer getsEcho() {
		return sEcho;
	}

	/**
	 * @param sEcho 要设置的 sEcho
	 */
	public void setsEcho(Integer sEcho) {
		this.sEcho = sEcho;
	}

	/**
	 * @return 获取iDisplayStart
	 */
	public Integer getiDisplayStart() {
		return iDisplayStart;
	}

	/**
	 * @param iDisplayStart 要设置的 iDisplayStart
	 */
	public void setiDisplayStart(Integer iDisplayStart) {
		this.iDisplayStart = iDisplayStart;
	}

	/**
	 * @return 获取iDisplayLength
	 */
	public Integer getiDisplayLength() {
		return iDisplayLength;
	}

	/**
	 * @param iDisplayLength 要设置的 iDisplayLength
	 */
	public void setiDisplayLength(Integer iDisplayLength) {
		this.iDisplayLength = iDisplayLength;
	}

	/**
	 * @return 获取isPaginaton
	 */
	public Boolean getIsQueryCount() {
		return isQueryCount;
	}

	/**
	 * @param isQueryCount 要设置的 isQueryCount
	 */
	public void setIsQueryCount(Boolean isQueryCount) {
		this.isQueryCount = isQueryCount;
	}

	/**
	 * @return 获取data
	 */
	public List<T> getData() {
		return data;
	}

	/**
	 * @param data 要设置的 data
	 */
	public void setData(List<T> data) {
		this.data = data;
	}

}
