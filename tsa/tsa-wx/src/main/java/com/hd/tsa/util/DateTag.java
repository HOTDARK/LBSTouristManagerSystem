package com.hd.tsa.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.lang.StringUtils;

public class DateTag extends TagSupport {

    private static final long serialVersionUID = 6464168398214506236L;

    private String value;
    
    private String pattern;

    @Override
    public int doStartTag() throws JspException {
        String vv = "" + value;
        if("".equals(pattern) || pattern == null){
        	pattern="yyyy-MM-dd HH:mm";
        }
        try {
        	if (StringUtils.isNotBlank(vv)) {
        		long time = Long.valueOf(vv.trim());
        		Calendar c = Calendar.getInstance();
        		c.setTimeInMillis(time);
        		SimpleDateFormat dateformat = new SimpleDateFormat(pattern);
        		String s = dateformat.format(c.getTime());
        		pageContext.getOut().write(s);
			} else {
				pageContext.getOut().write(vv);
			}
        } catch (Exception e) {
            e.printStackTrace();
        }
        return super.doStartTag();
    }

    public void setValue(String value) {
        this.value = value;
    }

	public void setPattern(String pattern) {
		this.pattern = pattern;
	}

}