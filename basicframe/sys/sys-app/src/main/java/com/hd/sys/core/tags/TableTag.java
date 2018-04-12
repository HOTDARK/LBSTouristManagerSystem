package com.hd.sys.core.tags;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.hd.sys.core.tags.modal.Column;
/**
 * 
 * 类描述：自定义列表标签
 * 
 * @author:  xf
 * @date： 日期：2014-12-3
 * @version 1.0
 */
public class TableTag extends TagSupport {
	private static final long serialVersionUID = 2092434286862922687L;
	
	protected String id;// 表格ID
	protected String width;//表格宽度
	protected String height;//表格高度
	protected String cls;//表格class
	protected boolean bAutoWidth = true;//是否自动计算表格各列宽度
	protected boolean bDeferRender = false;//是否启用延迟加载
	protected boolean bFilter = true;//是否启用内置搜索功能：可以跨列搜索
	protected boolean bInfo = true;//是否显示表格相关信息：例如翻页信息等
	protected boolean bJQueryUI = false;//是否启用jQueryUI皮肤插件支持
	protected boolean bLengthChange = true;//是否允许用户，在下拉列表自定义选择分页大小，需要分页支持
	protected boolean bPaginate = true;//是否开启分页
	protected boolean bProcessing = false;//是否启用进度显示，进度条等等，对处理大量数据很有用处
	protected boolean bScrollInfinite = false;//是否开启内置滚动条，并且显示所有数据
	protected boolean bServerSide = false;//是否启用服务器处理数据源，必须sAjaxSource指明数据源位置
	protected boolean bSort = true;//是否开启列排序功能，如果想禁用某一列排序，可以在每列设置使用bSortable参数
	protected boolean bSortClasses = false;//开关，指定当当前列在排序时，是否增加classes 'sorting_1', 'sorting_2' and 'sorting_3'
	protected boolean bStateSave = false;//是否开启cookies保存当前状态信息
	protected boolean fnServerData = false;//是否加载数据
	protected String sScrollX;//是否开启水平滚动，以及指定滚动区域大小
	protected String sScrollY;//是否开启垂直滚动，以及指定滚动区域大小
	protected String url;//数据地址
	protected String sPaginationType;//分页样式
	protected String sAjaxDataProp;//返回json数据的key
	
	protected List<Column> columnList = new ArrayList<Column>();// 列集合
	
	public int doStartTag() throws JspTagException {
		columnList.clear();
		return EVAL_PAGE;
	}
	
	public int doEndTag() throws JspTagException {
		try {
			JspWriter out = this.pageContext.getOut();
			out.print(end().toString());
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return EVAL_PAGE;
	}
	
	public void setColumn(String title,String field,String aDataSort,String asSorting,boolean bSearchable,boolean bSortable,
			boolean bVisible,String fnCreatedCell,String mRender,String sCellType,String sClass,String sContentPadding,
			String sDefaultContent,String sType,String sWidth,String sSortDataType) {
		Column column = new Column();
		column.setTitle(title);
		column.setField(field);
		column.setaDataSort(aDataSort);
		column.setAsSorting(asSorting);
		column.setbSearchable(bSearchable);
		column.setbSortable(bSortable);
		column.setbVisible(bVisible);
		column.setFnCreatedCell(fnCreatedCell);
		column.setmRender(mRender);
		column.setsCellType(sCellType);
		column.setsClass(sClass);
		column.setsContentPadding(sContentPadding);
		column.setsDefaultContent(sDefaultContent);
		column.setsType(sType);
		column.setsWidth(sWidth);
		column.setsSortDataType(sSortDataType);
		columnList.add(column);
	}
	
	public boolean isNotNull(String str) {
		if (str != null && !"".equals(str)) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public StringBuffer end() {
		StringBuffer sb = new StringBuffer();
		sb.append("<table "+(isNotNull(width)?"width=\""+width+"\" ":"")+(isNotNull(height)?"height=\""+height+"\" ":"")+" class=\""+cls+"\" id=\""+id+"\" ></table>");
		sb.append("<script type=\"text/javascript\">");
		sb.append("var db_"+id+" = {};");
		sb.append("$(document).ready(function() {");
		sb.append("db_"+id+" = $('#" + id + "').dataTable({");
		sb.append("\"bAutoWidth\" : " + bAutoWidth + ",");
		sb.append("\"bDeferRender\" : " + bDeferRender + ",");
		sb.append("\"bFilter\" : " + bFilter + ",");
		sb.append("\"bInfo\" : " + bInfo + ",");
		sb.append("\"bJQueryUI\" : " + bJQueryUI + ",");
		sb.append("\"bLengthChange\" : " + bLengthChange + ",");
		sb.append("\"bPaginate\" : " + bPaginate + ",");
		sb.append("\"bProcessing\" : " + bProcessing + ",");
		sb.append("\"bScrollInfinite\" : " + bScrollInfinite + ",");
		sb.append("\"sServerMethod\" : \"POST\",");
		sb.append("\"bServerSide\" : " + bServerSide + ",");
		sb.append("\"bSort\" : " + bSort + ",");
		sb.append("\"bSortClasses\" : " + bSortClasses + ",");
		sb.append("\"bStateSave\" : " + bStateSave + ",");
		sb.append("\"sDom\" : 'rtlipf',");
		sb.append(isNotNull(sScrollX)?"\"sScrollX\" : \"" + sScrollX + "\",":"");
		sb.append(isNotNull(sScrollY)?"\"sScrollY\" : \"" + sScrollY + "\",":"");
		sb.append("\"sAjaxDataProp\" : " + (isNotNull(sAjaxDataProp)?"\""+sAjaxDataProp+"\",":"\"\","));
		sb.append(isNotNull(url)?"\"sAjaxSource\" : \"" + url + "\",":"");
		sb.append(isNotNull(sPaginationType)?"\"sPaginationType\" : \"" + sPaginationType + "\",":"");
		sb.append("\"oLanguage\" : {" + "\"sLengthMenu\" : \" <b>_MENU_</b> 条记录\"," + "\"sZeroRecords\" : \"没有检索到数据\"," + "\"sInfo\" : \"第<b> _START_</b> 至 <b>_END_</b> 条数据 共<b> _TOTAL_</b> 条\"," + "\"sInfoEmpty\" : \"第<b> 0</b> 至 <b>0</b> 条数据 共<b> 0</b> 条\"," + "\"sProcessing\" : \"正在加载数据...\"," + "\"sSearch\" : \"搜索\"," + "\"oPaginate\" : {" + "\"sFirst\" : \"首页\"," + "\"sPrevious\" : \"前页\", " + "\"sNext\" : \"后页\"," + "\"sLast\" : \"尾页\"" + "}" + "},"); // 汉化
		sb.append(fnServerData?"\"fnServerData\":function(sSource, aoData, fnCallback, oSettings){"
				 +"if(sSource != '' && sSource != null){"
				 +"sSource = encodeURI(sSource);"
				 +"oSettings.jqXHR = $.ajax( {"
				 +"\"dataType\": 'json',"
				 +"\"type\": \"POST\","
				 +"\"url\": sSource,"
				 +"\"data\": aoData,"
				 +"\"success\": fnCallback"
				 +"});"
				 +"}"
				 +"else{"
				 +"return \"[]\";}},":"");
		sb.append("\"aoColumns\" : [ ");
		int i = 0;
		for (Column column : columnList) {
			i++;
			sb.append("{");
			sb.append("\"sTitle\":\"" + column.getTitle() + "\"");
			sb.append(",\"sName\":\"" + column.getField() + "\"");
			sb.append(",\"mData\":\"" + column.getField() + "\"");
			sb.append(isNotNull(column.getsWidth())?",\"sWidth\":\"" + column.getsWidth() + "\"":"");
			sb.append(isNotNull(column.getaDataSort())?",\"aDataSort\":\"" + column.getaDataSort() + "\"":"");
			sb.append(isNotNull(column.getAsSorting())?",\"asSorting\":\"" + column.getAsSorting() + "\"":"");
			sb.append(",\"bSearchable\":" + column.getbSearchable());
			sb.append(",\"bSortable\":" + column.getbSortable());
			sb.append(",\"bVisible\":" + column.getbVisible());
			sb.append(isNotNull(column.getFnCreatedCell())?",\"fnCreatedCell\":" + column.getFnCreatedCell():"");
			sb.append(isNotNull(column.getsCellType())?",\"sCellType\":\"" + column.getsCellType() + "\"":"");
			sb.append(isNotNull(column.getsClass())?",\"sClass\":\"" + column.getsClass() + "\"":"");
			sb.append(isNotNull(column.getsContentPadding())?",\"sContentPadding\":\"" + column.getsContentPadding() + "\"":"");
			sb.append(isNotNull(column.getsDefaultContent())?",\"sDefaultContent\":\"" + column.getsDefaultContent() + "\"":"");
			sb.append(isNotNull(column.getsType())?",\"sType\":\"" + column.getsType() + "\"":"");
			sb.append(isNotNull(column.getsSortDataType())?",\"sSortDataType\":\"" + column.getsSortDataType() + "\"":"");
			sb.append(isNotNull(column.getmRender())?",\"mRender\":function (data, type, obj) { return " + column.getmRender()+"(data, type, obj);}":"");
			sb.append("}");
			if (i < columnList.size())
				sb.append(",");
		}
		sb.append("]");
		sb.append("});" + "});");
		sb.append("function changeUrl(id,url){");
		sb.append("var os = db_"+id+".fnSettings();");
		sb.append("os.sAjaxSource = url;");
		sb.append("db_"+id+".fnClearTable(0);");
		sb.append("db_"+id+".fnDraw();");
		sb.append("}");
		sb.append("</script>");
		return sb;
	}
	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getCls() {
		return cls;
	}

	public void setCls(String cls) {
		this.cls = cls;
	}

	public boolean isbAutoWidth() {
		return bAutoWidth;
	}

	public void setbAutoWidth(boolean bAutoWidth) {
		this.bAutoWidth = bAutoWidth;
	}

	public boolean isbDeferRender() {
		return bDeferRender;
	}

	public void setbDeferRender(boolean bDeferRender) {
		this.bDeferRender = bDeferRender;
	}

	public boolean isbFilter() {
		return bFilter;
	}

	public void setbFilter(boolean bFilter) {
		this.bFilter = bFilter;
	}

	public boolean isbInfo() {
		return bInfo;
	}

	public void setbInfo(boolean bInfo) {
		this.bInfo = bInfo;
	}

	public boolean isbJQueryUI() {
		return bJQueryUI;
	}

	public void setbJQueryUI(boolean bJQueryUI) {
		this.bJQueryUI = bJQueryUI;
	}

	public boolean isbLengthChange() {
		return bLengthChange;
	}

	public void setbLengthChange(boolean bLengthChange) {
		this.bLengthChange = bLengthChange;
	}

	public boolean isbPaginate() {
		return bPaginate;
	}

	public void setbPaginate(boolean bPaginate) {
		this.bPaginate = bPaginate;
	}

	public boolean isbProcessing() {
		return bProcessing;
	}

	public void setbProcessing(boolean bProcessing) {
		this.bProcessing = bProcessing;
	}

	public boolean isbScrollInfinite() {
		return bScrollInfinite;
	}

	public void setbScrollInfinite(boolean bScrollInfinite) {
		this.bScrollInfinite = bScrollInfinite;
	}

	public boolean isbServerSide() {
		return bServerSide;
	}

	public void setbServerSide(boolean bServerSide) {
		this.bServerSide = bServerSide;
	}

	public boolean isbSort() {
		return bSort;
	}

	public void setbSort(boolean bSort) {
		this.bSort = bSort;
	}

	public boolean isbSortClasses() {
		return bSortClasses;
	}

	public void setbSortClasses(boolean bSortClasses) {
		this.bSortClasses = bSortClasses;
	}

	public boolean isbStateSave() {
		return bStateSave;
	}

	public void setbStateSave(boolean bStateSave) {
		this.bStateSave = bStateSave;
	}

	public String getsScrollX() {
		return sScrollX;
	}

	public void setsScrollX(String sScrollX) {
		this.sScrollX = sScrollX;
	}

	public String getsScrollY() {
		return sScrollY;
	}

	public void setsScrollY(String sScrollY) {
		this.sScrollY = sScrollY;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getsPaginationType() {
		return sPaginationType;
	}

	public void setsPaginationType(String sPaginationType) {
		this.sPaginationType = sPaginationType;
	}

	public List<Column> getColumnList() {
		return columnList;
	}

	public void setColumnList(List<Column> columnList) {
		this.columnList = columnList;
	}

	public String getsAjaxDataProp() {
		return sAjaxDataProp;
	}

	public void setsAjaxDataProp(String sAjaxDataProp) {
		this.sAjaxDataProp = sAjaxDataProp;
	}

	public boolean isFnServerData() {
		return fnServerData;
	}

	public void setFnServerData(boolean fnServerData) {
		this.fnServerData = fnServerData;
	}

}
