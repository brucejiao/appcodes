/*
 *  http://www.appcodes.cn APP精品源码下载站声明：
 * 1、本站源码为网上搜集或网友提供，如果涉及或侵害到您的版 权，请立即通知我们。
 * 2、 本站提供免费代码只可供研究学习使用，切勿用于商业用途 由此引起一切后果与本站无关。
 * 3、 商业源码请在源码授权范围内进行使用。
 * 4、更多APP精品源码下载请访问:http://www.appcodes.cn。
 * 5、如有疑问请发信息至appcodes@qq.com。
 */
package com.zftlive.android.view;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.widget.TextView;

import com.zftlive.android.tools.ToolResource;

/**
 * 自定义对话框
 * @author 曾繁添
 * @version 1.0
 */
public class ProgressDialog extends Dialog {
	
	/**消息TextView*/
	private TextView tvMsg ; 
	
	public ProgressDialog(Context context, String strMessage) {
		this(context, ToolResource.getStyleId("CustomProgressDialog"),strMessage);
	}

	public ProgressDialog(Context context, int theme, String strMessage) {
		super(context, theme);
		this.setContentView(ToolResource.getLayoutId("view_progress_dialog"));
		this.getWindow().getAttributes().gravity = Gravity.CENTER;
	    tvMsg = (TextView) this.findViewById(ToolResource.getIdId("tv_msg"));
	    setMessage(strMessage);
	}

	/**
	 * 设置进度条消息
	 * @param strMessage 消息文本
	 */
	public void setMessage(String strMessage){
		if (tvMsg != null) {
			tvMsg.setText(strMessage);
		}
	}
}