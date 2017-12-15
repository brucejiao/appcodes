/*
 *  http://www.appcodes.cn APP精品源码下载站声明：
 * 1、本站源码为网上搜集或网友提供，如果涉及或侵害到您的版 权，请立即通知我们。
 * 2、 本站提供免费代码只可供研究学习使用，切勿用于商业用途 由此引起一切后果与本站无关。
 * 3、 商业源码请在源码授权范围内进行使用。
 * 4、更多APP精品源码下载请访问:http://www.appcodes.cn。
 * 5、如有疑问请发信息至appcodes@qq.com。
 */
package com.zftlive.android.view;

import com.zftlive.android.MApplication;

/**
 * 自定义View需要用到的常量、方法
 * @author 曾繁添
 *
 */
public interface IView {
	
	/**应用的包名称**/
	String PACKAGE_NAME = MApplication.gainContext().getPackageName();
	
	/***资源类型-array**/
	String ARRAY = "array";
	
	/***资源类型-attr**/
	String ATTR = "attr";
	
	/***资源类型-bool**/
	String BOOL = "bool";
	
	/***资源类型-color**/
	String COLOR = "color";
	
	/***资源类型-dimen**/
	String DIMEN = "dimen";
	
	/***资源类型-drawable**/
	String DRAWABLE = "drawable";
	
	/***资源类型-id**/
	String ID = "id";
	
	/***资源类型-id**/
	String INTEGER = "integer";
	
	/***资源类型-layout**/
	String LAYOUT = "layout";
	
	/***资源类型-drawable**/
	String STRING = "string";
	
	/***资源类型-style**/
	String STYLE = "style";
	
	/***资源类型-styleable**/
	String STYLEABLE = "styleable";

}
