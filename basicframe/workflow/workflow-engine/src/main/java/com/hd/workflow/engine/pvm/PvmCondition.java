/*
 * Copyright © 2015, Wangding (Chengdu) Tech Co.,Ltd. 
 * All right reserved.
 */
package com.hd.workflow.engine.pvm;

import java.util.List;

import com.hd.workflow.engine.pvm.delegate.Expression;


/**
 * 分支附属条件
 * @version	0.0.1
 * @author	<a href="mailto:sunjian@iwangding.com">孙剑</a>
 * @date	2015-2-12 上午10:58:08
 */
public class PvmCondition{
	
	private Expression expression;
	
	//结果编码
	private String code;
	
	//状态
	private String state;
	
	//结论
	private Expression result;
	
	//处理建议
	private Expression tips;
	
	private List<PvmConditionParam> params;
	
	private PvmTransition transition;
	
	/**
	 * @return the expression
	 */
	public Expression getExpression() {
		return expression;
	}

	/**
	 * @param expression the expression to set
	 */
	public void setExpression(Expression expression) {
		this.expression = expression;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * @return the result
	 */
	public Expression getResult() {
		return result;
	}

	/**
	 * @param result the result to set
	 */
	public void setResult(Expression result) {
		this.result = result;
	}

	/**
	 * @return the tips
	 */
	public Expression getTips() {
		return tips;
	}

	/**
	 * @param tips the tips to set
	 */
	public void setTips(Expression tips) {
		this.tips = tips;
	}

	/**
	 * @return the transition
	 */
	public PvmTransition getTransition() {
		return transition;
	}

	/**
	 * @param transition the transition to set
	 */
	public void setTransition(PvmTransition transition) {
		this.transition = transition;
	}

	/**
	 * @return the params
	 */
	public List<PvmConditionParam> getParams() {
		return params;
	}

	/**
	 * @param params the params to set
	 */
	public void setParams(List<PvmConditionParam> params) {
		this.params = params;
	}
	
}
