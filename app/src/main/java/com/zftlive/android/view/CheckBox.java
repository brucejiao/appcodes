/*
 *  http://www.appcodes.cn APP精品源码下载站声明：
 * 1、本站源码为网上搜集或网友提供，如果涉及或侵害到您的版 权，请立即通知我们。
 * 2、 本站提供免费代码只可供研究学习使用，切勿用于商业用途 由此引起一切后果与本站无关。
 * 3、 商业源码请在源码授权范围内进行使用。
 * 4、更多APP精品源码下载请访问:http://www.appcodes.cn。
 * 5、如有疑问请发信息至appcodes@qq.com。
 */
package com.zftlive.android.view;

import com.zftlive.android.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;

/**
 * 自定义CheckBox,增加属性key/value
 * @author 曾繁添
 * @version 1.0
 *
 */
public class CheckBox extends android.widget.CheckBox {

	private String mKey;
	private String mValue;

	public CheckBox(Context context) {
		this(context,null);
	}
	
	public CheckBox(Context context, AttributeSet attrs) {
		this(context, attrs,android.R.attr.checkboxStyle);
	}
	
	public CheckBox(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		
		// 获取自定义属性和默认值
		TypedArray mTypedArray = context.obtainStyledAttributes(attrs,R.styleable.TextView);
		mKey = mTypedArray.getString(R.styleable.TextView_key);
		mValue = mTypedArray.getString(R.styleable.TextView_value);
		mTypedArray.recycle();
	}
	
	public String getKey(){
		return mKey;
	}
	
	public void setKey(String key){
		this.mKey = key;
	}
	
	public String getValue() {
		return mValue;
	}

	public void setValue(String value) {
		this.mValue = value;
	}

}
