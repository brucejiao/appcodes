/*
 *  http://www.appcodes.cn APP精品源码下载站声明：
 * 1、本站源码为网上搜集或网友提供，如果涉及或侵害到您的版 权，请立即通知我们。
 * 2、 本站提供免费代码只可供研究学习使用，切勿用于商业用途 由此引起一切后果与本站无关。
 * 3、 商业源码请在源码授权范围内进行使用。
 * 4、更多APP精品源码下载请访问:http://www.appcodes.cn。
 * 5、如有疑问请发信息至appcodes@qq.com。
 */
package com.zftlive.android.tools;

import java.lang.reflect.Field;

import android.content.Context;
import android.util.Log;

import com.zftlive.android.MApplication;

/**
 * 获取资源工具类
 * @author 曾繁添
 * @version 1.0
 */
public class ToolResource {
	private static final String TAG = ToolResource.class.getName();

	private static Context mContext = MApplication.gainContext();

	private static Class<?> CDrawable = null;

	private static Class<?> CLayout = null;
	
	private static Class<?> CId = null;

	private static Class<?> CAnim = null;

	private static Class<?> CStyle = null;

	private static Class<?> CString = null;

	private static Class<?> CArray = null;
	
	static{
		try{
			CDrawable = Class.forName(mContext.getPackageName() + ".R$drawable");
			CLayout = Class.forName(mContext.getPackageName() + ".R$layout");
			CId = Class.forName(mContext.getPackageName() + ".R$id");
			CAnim = Class.forName(mContext.getPackageName() + ".R$anim");
			CStyle = Class.forName(mContext.getPackageName() + ".R$style");
			CString = Class.forName(mContext.getPackageName() + ".R$string");
			CArray = Class.forName(mContext.getPackageName() + ".R$array");

		}catch(ClassNotFoundException e){
			Log.i(TAG,e.getMessage());
		}
	}

	public static int getDrawableId(String resName){
		return getResId(CDrawable,resName);
	}
	
	public static int getLayoutId(String resName){
		return getResId(CLayout,resName);
	}
	
	public static int getIdId(String resName){
		return getResId(CId,resName);
	}
	
	public static int getAnimId(String resName){
		return getResId(CAnim,resName);
	}
	
	public static int getStyleId(String resName){
		return getResId(CStyle,resName);
	}
	
	public static int getStringId(String resName){
		return getResId(CString,resName);
	}
	
	public static int getArrayId(String resName){
		return getResId(CArray,resName);
	}
	
	private static int getResId(Class<?> resClass,String resName){
		if(resClass == null){
			Log.i(TAG,"getRes(null," + resName + ")");
			throw new IllegalArgumentException("ResClass is not initialized. Please make sure you have added neccessary resources. Also make sure you have " + mContext.getPackageName() + ".R$* configured in obfuscation. field=" + resName);
		}

		try {
			Field field = resClass.getField(resName);
			return field.getInt(resName);
		} catch (Exception e) {
			Log.i(TAG, "getRes(" + resClass.getName() + ", " + resName + ")");
			Log.i(TAG, "Error getting resource. Make sure you have copied all resources (res/) from SDK to your project. ");
			Log.i(TAG, e.getMessage());
		} 

		return -1;
	}
}
