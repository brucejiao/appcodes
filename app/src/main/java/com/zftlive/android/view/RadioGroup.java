/*
 *  http://www.appcodes.cn APP精品源码下载站声明：
 * 1、本站源码为网上搜集或网友提供，如果涉及或侵害到您的版 权，请立即通知我们。
 * 2、 本站提供免费代码只可供研究学习使用，切勿用于商业用途 由此引起一切后果与本站无关。
 * 3、 商业源码请在源码授权范围内进行使用。
 * 4、更多APP精品源码下载请访问:http://www.appcodes.cn。
 * 5、如有疑问请发信息至appcodes@qq.com。
 */
package com.zftlive.android.view;

import android.content.Context;
import android.util.AttributeSet;

public class RadioGroup extends android.widget.RadioGroup {

	public RadioGroup(Context context) {
		super(context);
	}

	public RadioGroup(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	// 设置子控件的值
	public void setChildValue() {
		int n = this.getChildCount();
		for (int i = 0; i < n; i++) {
			final RadioButton radio = (RadioButton) this.getChildAt(i);
			if (radio.getValue().equals(this.mValue)) {
				radio.setChecked(true);
			} else {
				radio.setChecked(false);
			}
		}
	}

	// 获取子类的值
	public void getChildValue() {
		int n = this.getChildCount();
		for (int i = 0; i < n; i++) {
			RadioButton radio = (RadioButton) this.getChildAt(i);
			if (radio.isChecked()) {
				this.mValue = radio.getValue();
			}
		}
	}

	private String mValue;
	
	public void setValue(String value) {
		this.mValue = value;
		setChildValue();
	}

	public String getValue() {
		getChildValue();
		return this.mValue;
	}

}
