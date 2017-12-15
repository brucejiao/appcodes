/*
 *  http://www.appcodes.cn APP精品源码下载站声明：
 * 1、本站源码为网上搜集或网友提供，如果涉及或侵害到您的版 权，请立即通知我们。
 * 2、 本站提供免费代码只可供研究学习使用，切勿用于商业用途 由此引起一切后果与本站无关。
 * 3、 商业源码请在源码授权范围内进行使用。
 * 4、更多APP精品源码下载请访问:http://www.appcodes.cn。
 * 5、如有疑问请发信息至appcodes@qq.com。
 */
package com.zftlive.android.view.pulltorefresh.internal;

import android.annotation.TargetApi;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;
import android.view.View;

@SuppressWarnings("deprecation")
public class ViewCompat {

	public static void postOnAnimation(View view, Runnable runnable) {
		if (VERSION.SDK_INT >= VERSION_CODES.JELLY_BEAN) {
			SDK16.postOnAnimation(view, runnable);
		} else {
			view.postDelayed(runnable, 16);
		}
	}

	public static void setBackground(View view, Drawable background) {
		if (VERSION.SDK_INT >= VERSION_CODES.JELLY_BEAN) {
			SDK16.setBackground(view, background);
		} else {
			view.setBackgroundDrawable(background);
		}
	}

	public static void setLayerType(View view, int layerType) {
		if (VERSION.SDK_INT >= VERSION_CODES.HONEYCOMB) {
			SDK11.setLayerType(view, layerType);
		}
	}

	@TargetApi(11)
	static class SDK11 {

		public static void setLayerType(View view, int layerType) {
			view.setLayerType(layerType, null);
		}
	}

	@TargetApi(16)
	static class SDK16 {

		public static void postOnAnimation(View view, Runnable runnable) {
			view.postOnAnimation(runnable);
		}

		public static void setBackground(View view, Drawable background) {
			view.setBackground(background);
		}

	}

}
