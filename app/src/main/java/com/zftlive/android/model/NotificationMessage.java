/*
 *  http://www.appcodes.cn APP精品源码下载站声明：
 * 1、本站源码为网上搜集或网友提供，如果涉及或侵害到您的版 权，请立即通知我们。
 * 2、 本站提供免费代码只可供研究学习使用，切勿用于商业用途 由此引起一切后果与本站无关。
 * 3、 商业源码请在源码授权范围内进行使用。
 * 4、更多APP精品源码下载请访问:http://www.appcodes.cn。
 * 5、如有疑问请发信息至appcodes@qq.com。
 */
package com.zftlive.android.model;

import android.os.Bundle;
import android.widget.RemoteViews;

/**
 * 发送Notification通知实体
 * 
 * @author 曾繁添
 * @version 1.0
 * 
 */
public class NotificationMessage extends Message {

	/**
	 * 
	 */
	private static final long serialVersionUID = 681166507845221063L;

	/**
	 * 状态栏提示信息图标
	 */
	private int iconResId;

	/**
	 * 状态栏提示信息图标
	 */
	private String statusBarText;

	/**
	 * 消息标题
	 */
	private String msgTitle;

	/**
	 * 消息内容
	 */
	private String msgContent;

	/**
	 * 点击消息跳转的界面
	 */
	private Class forwardComponent;
	
	/**
	 * 点击消息跳转界面需携带的数据
	 */
	private Bundle extras;

	/**
	 * 自定义消息通知布局View
	 */
	private RemoteViews mRemoteViews;

	public int getIconResId() {
		return iconResId;
	}

	public void setIconResId(int iconResId) {
		this.iconResId = iconResId;
	}

	public String getStatusBarText() {
		return statusBarText;
	}

	public void setStatusBarText(String statusBarText) {
		this.statusBarText = statusBarText;
	}

	public String getMsgTitle() {
		return msgTitle;
	}

	public void setMsgTitle(String msgTitle) {
		this.msgTitle = msgTitle;
	}

	public String getMsgContent() {
		return msgContent;
	}

	public void setMsgContent(String msgContent) {
		this.msgContent = msgContent;
	}

	public Class getForwardComponent() {
		return forwardComponent;
	}

	public void setForwardComponent(Class forwardComponent) {
		this.forwardComponent = forwardComponent;
	}

	public Bundle getExtras() {
		return extras;
	}

	public void setExtras(Bundle extras) {
		this.extras = extras;
	}

	public RemoteViews getmRemoteViews() {
		return mRemoteViews;
	}

	public void setmRemoteViews(RemoteViews mRemoteViews) {
		this.mRemoteViews = mRemoteViews;
	}
}
