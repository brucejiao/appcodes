/*
 *  http://www.appcodes.cn APP精品源码下载站声明：
 * 1、本站源码为网上搜集或网友提供，如果涉及或侵害到您的版 权，请立即通知我们。
 * 2、 本站提供免费代码只可供研究学习使用，切勿用于商业用途 由此引起一切后果与本站无关。
 * 3、 商业源码请在源码授权范围内进行使用。
 * 4、更多APP精品源码下载请访问:http://www.appcodes.cn。
 * 5、如有疑问请发信息至appcodes@qq.com。
 */
package com.zftlive.android.tools;

import java.io.InputStream;
import java.util.Properties;

import android.content.Context;

import com.zftlive.android.MApplication;

/**
 * 配置文件工具类
 * 
 * @author 曾繁添
 * @version 1.0
 * 
 */
public final class ToolProperties extends Properties {

	private static Properties property = new Properties();

	public static String readAssetsProp(String fileName, String key) {
		String value = "";
		try {
			InputStream in = MApplication.gainContext().getAssets().open(fileName);
			property.load(in);
			value = property.getProperty(key);
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		return value;
	}
	
	public static String readAssetsProp(Context context,String fileName, String key) {
		String value = "";
		try {
			InputStream in = context.getAssets().open(fileName);
			property.load(in);
			value = property.getProperty(key);
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		return value;
	}

	public static String readAssetsProp(String fileName, String key,String defaultValue) {
		String value = "";
		try {
			InputStream in = MApplication.gainContext().getAssets().open(fileName);
			property.load(in);
			value = property.getProperty(key, defaultValue);
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		return value;
	}
	
	public static String readAssetsProp(Context context,String fileName, String key,String defaultValue) {
		String value = "";
		try {
			InputStream in = context.getAssets().open(fileName);
			property.load(in);
			value = property.getProperty(key, defaultValue);
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		return value;
	}
}
