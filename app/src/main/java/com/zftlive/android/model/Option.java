/*
 *  http://www.appcodes.cn APP精品源码下载站声明：
 * 1、本站源码为网上搜集或网友提供，如果涉及或侵害到您的版 权，请立即通知我们。
 * 2、 本站提供免费代码只可供研究学习使用，切勿用于商业用途 由此引起一切后果与本站无关。
 * 3、 商业源码请在源码授权范围内进行使用。
 * 4、更多APP精品源码下载请访问:http://www.appcodes.cn。
 * 5、如有疑问请发信息至appcodes@qq.com。
 */
package com.zftlive.android.model;

import java.io.Serializable;

/**
 * 下拉框、单选框、复选框选项Bean
 * @author 曾繁添
 * @version 1.0
 */
public class Option implements Serializable,Cloneable{

	private static final long serialVersionUID = -724868344947644938L;

	private String label = "请选择...";
	
	private String value = "";
	
	public Option() {
		
	}

	public Option(String value,String label) {
		this.label = label;
		this.value = value;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	@Override
	public String toString() {
		return label;
	}
	
	
}
