package com.hd.sys.core.tags;

import java.io.IOException;

import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;


/**
 * 
 * 类描述：下拉选择框标签
 * 
 * @author:  xf
 * @date： 日期：2014-12-3
 * @version 1.0
 */
public class SelectTag extends TagSupport {
	private static final long serialVersionUID = 2092434286862922687L;
	
	protected String id;// ID
	protected String url;//远程数据
	protected String name;//控件名称
	protected String cls;//class
	protected boolean multiple = false;//是否多选
	protected String dataMaxOption;//最多可选下拉数
	protected String dataStyle;//样式
	protected boolean dataLiveSearch = false;//是否加入筛选
	protected String title;//说明，如果未选中任何下拉，显示其信息
	protected String dataSelectedTextFormat;//指定如何显示选中项
	protected String dataWidth;//设置宽度
	protected boolean disabled = false;//是否无效
	protected String dataSize;//指定显示条数
	protected String dataHeader;//设置选项第一条（非可选）
	protected String dataContainer;//指定容器
	
	protected boolean showTick = false;//是否显示选中图标
	protected boolean showMenuArrow = false;//是否显示菜单图标
	protected boolean dropup = false;//是否自动判断选项列表显示在上方或下方
	protected String checkType; //表单验证
	protected String errMsg; //错误提示
	
	public int doStartTag() throws JspTagException {
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
		sb.append("<select name=\""+name+"\" id=\""+id+"\" class=\"selectpicker ");
		sb.append(isNotNull(cls)?cls+"\" ":"\"");
		sb.append(showTick?"show-tick ":"");
		sb.append(showMenuArrow?"show-menu-arrow ":"");
		sb.append(showMenuArrow?"dropup ":"");
		sb.append("\"");
		sb.append(multiple?"multiple ":"");
		sb.append(isNotNull(dataMaxOption)?"data-max-option=\""+dataMaxOption+"\" ":"");
		sb.append(isNotNull(dataStyle)?"data-style=\""+dataStyle+"\"":"");
		sb.append("data-live-search=\""+dataLiveSearch+"\" ");
		sb.append(isNotNull(title)?"title=\""+title+"\" ":"");
		sb.append(isNotNull(dataSelectedTextFormat)?"data-selected-text-format=\""+dataSelectedTextFormat+"\" ":"");
		sb.append(isNotNull(dataWidth)?"data-width=\""+dataWidth+"\" ":"");
		sb.append(disabled?"disabled ":"");
		sb.append(isNotNull(dataSize)?"data-size=\""+dataSize+"\" ":"");
		sb.append(isNotNull(dataHeader)?"data-header=\""+dataHeader+"\" ":"");
		sb.append(isNotNull(dataContainer)?"data-container=\""+dataContainer+"\" ":"");
		sb.append(isNotNull(checkType)?"check-type=\""+checkType+"\" ":"");
		sb.append(isNotNull(errMsg)?"required-message=\""+errMsg+"\" ":"");
		sb.append("></select>");
		sb.append("<script type=\"text/javascript\">"
				+"$(document).ready(function() {"
				+"$.ajax({"
				+"type : 'POST',"
				+"url : '"+url+"',"
				+"dataType : 'JSON',"
				+"success : function(data) {"
				+"$(\"#"+id+"\").empty();" 
				+"var option = \"\";" 
				+"for(var i = 0;i < data.length;i++){"
				+"option += \"<option value='\"+data[i].id+\"' \";"
				+"if(data[i].selected){"
				+"option += \"selected='selected' \";"
				+"}"
				+"if(data[i].disabled){"
				+"option += \"disabled \";"
				+"}"
				+"if(data[i].dataDivider){"
				+"option += \"data-divider='true' \";"
				+"}"
				+"if(data[i].dataSubtext){"
				+"option += \"data-subtext='\"+data[i].dataSubtext+\"' \";"
				+"}"
				+"if(data[i].dataIcon){"
				+"option += \"data-icon='\"+data[i].dataIcon+\"' \";"
				+"}"
				+"if(data[i].dataContent){"
				+"option += \"data-content='\"+data[i].dataContent+\"' \";"
				+"}"
				+"option += \">\"+data[i].text+\"</option>\""
				+"}"
				+"$(\"#"+id+"\").html(option);"
				+"$(\"#"+id+"\").selectpicker(\"refresh\");"
				+"}"
				+"});"
				+"})"
				+"</script>");
		return sb;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public boolean isMultiple() {
		return multiple;
	}

	public void setMultiple(boolean multiple) {
		this.multiple = multiple;
	}

	public String getDataMaxOption() {
		return dataMaxOption;
	}

	public void setDataMaxOption(String dataMaxOption) {
		this.dataMaxOption = dataMaxOption;
	}

	public String getDataStyle() {
		return dataStyle;
	}

	public void setDataStyle(String dataStyle) {
		this.dataStyle = dataStyle;
	}

	public boolean isDataLiveSearch() {
		return dataLiveSearch;
	}

	public void setDataLiveSearch(boolean dataLiveSearch) {
		this.dataLiveSearch = dataLiveSearch;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDataSelectedTextFormat() {
		return dataSelectedTextFormat;
	}

	public void setDataSelectedTextFormat(String dataSelectedTextFormat) {
		this.dataSelectedTextFormat = dataSelectedTextFormat;
	}

	public String getDataWidth() {
		return dataWidth;
	}

	public void setDataWidth(String dataWidth) {
		this.dataWidth = dataWidth;
	}

	public boolean isDisabled() {
		return disabled;
	}

	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}

	public String getDataSize() {
		return dataSize;
	}

	public void setDataSize(String dataSize) {
		this.dataSize = dataSize;
	}

	public String getDataHeader() {
		return dataHeader;
	}

	public void setDataHeader(String dataHeader) {
		this.dataHeader = dataHeader;
	}

	public String getDataContainer() {
		return dataContainer;
	}

	public void setDataContainer(String dataContainer) {
		this.dataContainer = dataContainer;
	}

	public boolean isShowTick() {
		return showTick;
	}

	public void setShowTick(boolean showTick) {
		this.showTick = showTick;
	}

	public boolean isShowMenuArrow() {
		return showMenuArrow;
	}

	public void setShowMenuArrow(boolean showMenuArrow) {
		this.showMenuArrow = showMenuArrow;
	}

	public boolean isDropup() {
		return dropup;
	}

	public void setDropup(boolean dropup) {
		this.dropup = dropup;
	}

	public String getCls() {
		return cls;
	}

	public void setCls(String cls) {
		this.cls = cls;
	}

	public String getCheckType() {
		return checkType;
	}

	public void setCheckType(String checkType) {
		this.checkType = checkType;
	}

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}
	
}
