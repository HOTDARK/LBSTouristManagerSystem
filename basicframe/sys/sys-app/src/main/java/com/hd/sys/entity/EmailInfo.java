package com.hd.sys.entity;

import java.io.Serializable;

public class EmailInfo implements Serializable {
	private static final long serialVersionUID = 1L;

	private String sendAccount;// 发件人
	private String password;// 发件人密码
	private String receviAccount;// 收件人
	private String title;// 邮件标题
	private String content;// 邮件内容

	public String getSendAccount() {
		return sendAccount;
	}

	public void setSendAccount(String sendAccount) {
		this.sendAccount = sendAccount;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getReceviAccount() {
		return receviAccount;
	}

	public void setReceviAccount(String receviAccount) {
		this.receviAccount = receviAccount;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
