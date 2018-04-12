package com.hd.sys.entity.vo;

/**
 * 
 * 用于报表工具中的series
 * 
 * @version 0.0.1
 * @author <a href="mailto:humeng@iwangding.com">胡猛</a>
 * @date 2014-11-21 下午2:52:14
 */
public class ReportSeriesVo {
	/**
	 * 报表X轴显示的字符
	 */
	private String name;
	/**
	 * 报表类型 exp：柱状图(bar),线图(line)........
	 */
	private String type;
	/**
	 * Y轴数据
	 */
	private int[] data;
	/**
	 * 如果使用堆积图表,stack表示以设置的字段为基础来统计
	 */
	private String stack;
	/**
	 * 柱子最大宽度
	 */
	private int barMaxWidth = 70;
	/**
	 * 柱子最小高度
	 */
	private int barMinHeight = 3;

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the data
	 */
	public int[] getData() {
		return data;
	}

	/**
	 * @param data
	 *            the data to set
	 */
	public void setData(int[] data) {
		this.data = data;
	}

	/**
	 * @return the stack
	 */
	public String getStack() {
		return stack;
	}

	/**
	 * @param stack
	 *            the stack to set
	 */
	public void setStack(String stack) {
		this.stack = stack;
	}

	/**
	 * @return the barMaxWidth
	 */
	public int getBarMaxWidth() {
		return barMaxWidth;
	}

	/**
	 * @param barMaxWidth
	 *            the barMaxWidth to set
	 */
	public void setBarMaxWidth(int barMaxWidth) {
		this.barMaxWidth = barMaxWidth;
	}

	/**
	 * @return the barMinHeight
	 */
	public int getBarMinHeight() {
		return barMinHeight;
	}

	/**
	 * @param barMinHeight
	 *            the barMinHeight to set
	 */
	public void setBarMinHeight(int barMinHeight) {
		this.barMinHeight = barMinHeight;
	}

}
