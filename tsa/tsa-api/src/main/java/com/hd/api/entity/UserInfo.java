/*
 * Copyright © 2017, hd (ChongQing) Tech Co.,Ltd. 
 * All right reserved.
 */
package com.hd.api.entity;
import com.hd.sfw.core.model.Pagination;

/**
 * TODO 类描述
 * @version	0.0.1
 * @author	generator
 * @date	2017-08-22
 */
public class UserInfo extends Pagination<UserInfo> {
	private static final long serialVersionUID = 5454155825314635342L;
	
	//columns START
	private java.lang.String xgh;
	private java.lang.Integer yhzt;
	private java.lang.String dlmm;
	private java.lang.Integer mmzt;
	private java.lang.String yhts;
	private java.lang.String dwdm;
	private java.lang.String xm;
	private java.lang.String xmpy;
	private java.lang.String xbm;
	private java.lang.String csrq;
	private java.lang.String csdm;
	private java.lang.String jg;
	private java.lang.String mzm;
	private java.lang.String sfzjh;
	private java.lang.String zzmmm;
	private java.lang.String dqztm;
	private java.lang.String xzjb;
	private java.lang.String hyzk;
	private java.lang.String rzsj;
	private java.lang.String sjhm;
	private java.lang.String jtdz;
	private java.lang.String xzdz;
	private java.lang.Integer zcfs;
	private java.util.Date zcsj;
	private java.lang.String lastDlip;
	private java.util.Date lastDlsj;
	private java.lang.String openId;
	private java.lang.String cardId;
	private java.lang.String field1;
	private java.lang.String field2;
	private java.lang.String field3;
	//columns END
	
	//用于查询的时间列
	private java.util.Date zcsjBegin;
	private java.util.Date zcsjEnd;
	private java.util.Date lastDlsjBegin;
	private java.util.Date lastDlsjEnd;

	public UserInfo(){
	}

	public UserInfo(
		java.lang.String xgh
	){
		this.xgh = xgh;
	}

	public void setXgh(java.lang.String value) {
		this.xgh = value;
	}
	
	public java.lang.String getXgh() {
		return this.xgh;
	}
	public void setYhzt(java.lang.Integer value) {
		this.yhzt = value;
	}
	
	public java.lang.Integer getYhzt() {
		return this.yhzt;
	}
	public void setDlmm(java.lang.String value) {
		this.dlmm = value;
	}
	
	public java.lang.String getDlmm() {
		return this.dlmm;
	}
	public void setMmzt(java.lang.Integer value) {
		this.mmzt = value;
	}
	
	public java.lang.Integer getMmzt() {
		return this.mmzt;
	}
	public void setYhts(java.lang.String value) {
		this.yhts = value;
	}
	
	public java.lang.String getYhts() {
		return this.yhts;
	}
	public void setDwdm(java.lang.String value) {
		this.dwdm = value;
	}
	
	public java.lang.String getDwdm() {
		return this.dwdm;
	}
	public void setXm(java.lang.String value) {
		this.xm = value;
	}
	
	public java.lang.String getXm() {
		return this.xm;
	}
	public void setXmpy(java.lang.String value) {
		this.xmpy = value;
	}
	
	public java.lang.String getXmpy() {
		return this.xmpy;
	}
	public void setXbm(java.lang.String value) {
		this.xbm = value;
	}
	
	public java.lang.String getXbm() {
		return this.xbm;
	}
	public void setCsrq(java.lang.String value) {
		this.csrq = value;
	}
	
	public java.lang.String getCsrq() {
		return this.csrq;
	}
	public void setCsdm(java.lang.String value) {
		this.csdm = value;
	}
	
	public java.lang.String getCsdm() {
		return this.csdm;
	}
	public void setJg(java.lang.String value) {
		this.jg = value;
	}
	
	public java.lang.String getJg() {
		return this.jg;
	}
	public void setMzm(java.lang.String value) {
		this.mzm = value;
	}
	
	public java.lang.String getMzm() {
		return this.mzm;
	}
	public void setSfzjh(java.lang.String value) {
		this.sfzjh = value;
	}
	
	public java.lang.String getSfzjh() {
		return this.sfzjh;
	}
	public void setZzmmm(java.lang.String value) {
		this.zzmmm = value;
	}
	
	public java.lang.String getZzmmm() {
		return this.zzmmm;
	}
	public void setDqztm(java.lang.String value) {
		this.dqztm = value;
	}
	
	public java.lang.String getDqztm() {
		return this.dqztm;
	}
	public void setXzjb(java.lang.String value) {
		this.xzjb = value;
	}
	
	public java.lang.String getXzjb() {
		return this.xzjb;
	}
	public void setHyzk(java.lang.String value) {
		this.hyzk = value;
	}
	
	public java.lang.String getHyzk() {
		return this.hyzk;
	}
	public void setRzsj(java.lang.String value) {
		this.rzsj = value;
	}
	
	public java.lang.String getRzsj() {
		return this.rzsj;
	}
	public void setSjhm(java.lang.String value) {
		this.sjhm = value;
	}
	
	public java.lang.String getSjhm() {
		return this.sjhm;
	}
	public void setJtdz(java.lang.String value) {
		this.jtdz = value;
	}
	
	public java.lang.String getJtdz() {
		return this.jtdz;
	}
	public void setXzdz(java.lang.String value) {
		this.xzdz = value;
	}
	
	public java.lang.String getXzdz() {
		return this.xzdz;
	}
	public void setZcfs(java.lang.Integer value) {
		this.zcfs = value;
	}
	
	public java.lang.Integer getZcfs() {
		return this.zcfs;
	}
	public void setZcsjBegin(java.util.Date value) {
		this.zcsjBegin = value;
	}
	
	public java.util.Date getZcsjBegin() {
		return this.zcsjBegin;
	}
	public void setZcsjEnd(java.util.Date value) {
		this.zcsjEnd = value;
	}
	
	public java.util.Date getZcsjEnd() {
		return this.zcsjEnd;
	}
	public void setZcsj(java.util.Date value) {
		this.zcsj = value;
	}
	
	public java.util.Date getZcsj() {
		return this.zcsj;
	}
	public void setLastDlip(java.lang.String value) {
		this.lastDlip = value;
	}
	
	public java.lang.String getLastDlip() {
		return this.lastDlip;
	}
	public void setLastDlsjBegin(java.util.Date value) {
		this.lastDlsjBegin = value;
	}
	
	public java.util.Date getLastDlsjBegin() {
		return this.lastDlsjBegin;
	}
	public void setLastDlsjEnd(java.util.Date value) {
		this.lastDlsjEnd = value;
	}
	
	public java.util.Date getLastDlsjEnd() {
		return this.lastDlsjEnd;
	}
	public void setLastDlsj(java.util.Date value) {
		this.lastDlsj = value;
	}
	
	public java.util.Date getLastDlsj() {
		return this.lastDlsj;
	}
	public void setOpenId(java.lang.String value) {
		this.openId = value;
	}
	
	public java.lang.String getOpenId() {
		return this.openId;
	}
	public void setCardId(java.lang.String value) {
		this.cardId = value;
	}
	
	public java.lang.String getCardId() {
		return this.cardId;
	}
	public void setField1(java.lang.String value) {
		this.field1 = value;
	}
	
	public java.lang.String getField1() {
		return this.field1;
	}
	public void setField2(java.lang.String value) {
		this.field2 = value;
	}
	
	public java.lang.String getField2() {
		return this.field2;
	}
	public void setField3(java.lang.String value) {
		this.field3 = value;
	}
	
	public java.lang.String getField3() {
		return this.field3;
	}

	@Override
	public String toString() {
		return "UserInfo [xgh=" + xgh + ", yhzt=" + yhzt + ", dlmm=" + dlmm + ", mmzt=" + mmzt + ", yhts=" + yhts
				+ ", dwdm=" + dwdm + ", xm=" + xm + ", xmpy=" + xmpy + ", xbm=" + xbm + ", csrq=" + csrq + ", csdm="
				+ csdm + ", jg=" + jg + ", mzm=" + mzm + ", sfzjh=" + sfzjh + ", zzmmm=" + zzmmm + ", dqztm=" + dqztm
				+ ", xzjb=" + xzjb + ", hyzk=" + hyzk + ", rzsj=" + rzsj + ", sjhm=" + sjhm + ", jtdz=" + jtdz
				+ ", xzdz=" + xzdz + ", zcfs=" + zcfs + ", zcsj=" + zcsj + ", lastDlip=" + lastDlip + ", lastDlsj="
				+ lastDlsj + ", openId=" + openId + ", cardId=" + cardId + ", field1=" + field1 + ", field2=" + field2
				+ ", field3=" + field3 + ", zcsjBegin=" + zcsjBegin + ", zcsjEnd=" + zcsjEnd + ", lastDlsjBegin="
				+ lastDlsjBegin + ", lastDlsjEnd=" + lastDlsjEnd + "]";
	}

}