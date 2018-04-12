package com.hd.sys.core.tags;


import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * 类描述：自定义列属性
 * @author xf
 * 日期：2014-12-5
 */
public class ColumnTag extends TagSupport {
	private static final long serialVersionUID = -8051157825844566400L;
	private String title;//表头
	private String field;//对应字段
	private String aDataSort;//允许一个列的排序采取多个列考虑当做一个排序
	private String asSorting;//设置默认的排序方向
	private boolean bSearchable = false;//启用或禁用过滤数据在本专栏中
	private boolean bSortable = false;//启用或禁用在这列排序
	private boolean bVisible = true;//启用或禁用本专栏的显示
	private String fnCreatedCell;//自定义DOM元素(例如添加背景颜色)函数
	private String mRender;//自定义显示函数
	private String sCellType;//改变 Cell类型创建的列,td或th
	private String sClass;//class属性
	private String sContentPadding;//文本后缀
	private String sDefaultContent;//默认文本
	private String sType;//显示类型
	private String sWidth;//定义列的宽度
	private String sSortDataType;//定义一个数据源类型的排序
	
	public int doStartTag() throws JspTagException {
		return EVAL_PAGE;
	}
	
	public int doEndTag() throws JspTagException {
		Tag t = findAncestorWithClass(this, TableTag.class);
		TableTag table = (TableTag) t;
		table.setColumn(title,field,aDataSort,asSorting,bSearchable,bSortable,bVisible,fnCreatedCell,mRender,sCellType,sClass,sContentPadding,sDefaultContent,sType,sWidth,sSortDataType);
		return EVAL_PAGE;
	}
	
	public String getaDataSort() {
		return aDataSort;
	}
	public void setaDataSort(String aDataSort) {
		this.aDataSort = aDataSort;
	}
	public String getAsSorting() {
		return asSorting;
	}
	public void setAsSorting(String asSorting) {
		this.asSorting = asSorting;
	}
	public boolean getbSearchable() {
		return bSearchable;
	}
	public void setbSearchable(boolean bSearchable) {
		this.bSearchable = bSearchable;
	}
	public boolean getbSortable() {
		return bSortable;
	}
	public void setbSortable(boolean bSortable) {
		this.bSortable = bSortable;
	}
	public boolean getbVisible() {
		return bVisible;
	}
	public void setbVisible(boolean bVisible) {
		this.bVisible = bVisible;
	}
	public String getFnCreatedCell() {
		return fnCreatedCell;
	}
	public void setFnCreatedCell(String fnCreatedCell) {
		this.fnCreatedCell = fnCreatedCell;
	}
	public String getmRender() {
		return mRender;
	}
	public void setmRender(String mRender) {
		this.mRender = mRender;
	}
	public String getsCellType() {
		return sCellType;
	}
	public void setsCellType(String sCellType) {
		this.sCellType = sCellType;
	}
	public String getsClass() {
		return sClass;
	}
	public void setsClass(String sClass) {
		this.sClass = sClass;
	}
	public String getsContentPadding() {
		return sContentPadding;
	}
	public void setsContentPadding(String sContentPadding) {
		this.sContentPadding = sContentPadding;
	}
	public String getsDefaultContent() {
		return sDefaultContent;
	}
	public void setsDefaultContent(String sDefaultContent) {
		this.sDefaultContent = sDefaultContent;
	}
	public String getsType() {
		return sType;
	}
	public void setsType(String sType) {
		this.sType = sType;
	}
	public String getsWidth() {
		return sWidth;
	}
	public void setsWidth(String sWidth) {
		this.sWidth = sWidth;
	}
	public String getsSortDataType() {
		return sSortDataType;
	}
	public void setsSortDataType(String sSortDataType) {
		this.sSortDataType = sSortDataType;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	
}
