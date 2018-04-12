package com.hd.sys.core.tags;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class AccountTypeTag extends TagSupport{
	private static final long serialVersionUID = 1L;

	public AccountTypeTag(){};
	
	public AccountTypeTag(String id, String name){
		this.id=id;
		this.name=name;
	};
	
	/**
	 * 控件ID
	 */
	private String id;
	/**
	 * 控件name
	 */
	private String name;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int doEndTag() throws JspException {
		JspWriter out = this.pageContext.getOut();
		try {
			out.print("<select class='selectpicker form-control query' name="+name+" id="+id+">");
			out.print("<option value=AdNo>宽带账号</option>"); 
			out.print("<option value=IPTVBusinessNo>IPTV账号</option>"); 
			/*out.print("<option value=PhoneNo>电话号码</option>"); 
			out.print("<option value=IDCardNo>身份证号码</option>");*/
			out.print("</select>");
		} catch (IOException e) {
			e.printStackTrace();
		}  

		return super.doEndTag();
	}
	@Override
	public int doStartTag() throws JspException {
		return super.doStartTag();
	}

}
