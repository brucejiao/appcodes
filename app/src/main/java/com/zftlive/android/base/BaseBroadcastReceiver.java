/*
 *  http://www.appcodes.cn APP精品源码下载站声明：
 * 1、本站源码为网上搜集或网友提供，如果涉及或侵害到您的版 权，请立即通知我们。
 * 2、 本站提供免费代码只可供研究学习使用，切勿用于商业用途 由此引起一切后果与本站无关。
 * 3、 商业源码请在源码授权范围内进行使用。
 * 4、更多APP精品源码下载请访问:http://www.appcodes.cn。
 * 5、如有疑问请发信息至appcodes@qq.com。
 */
package com.zftlive.android.base;

import android.content.BroadcastReceiver;

/**
 *  android 系统中的四大组件之一BroadcastReceiver基类<生命周期只有十秒左右，耗时操作需开service来做><br>
 * @author 曾繁添
 * @version 1.0
 *
 */
public abstract class BaseBroadcastReceiver extends BroadcastReceiver {

	/**日志输出标志**/
	protected final String TAG = this.getClass().getSimpleName();
}
