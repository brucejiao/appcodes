/*
 *  http://www.appcodes.cn APP精品源码下载站声明：
 * 1、本站源码为网上搜集或网友提供，如果涉及或侵害到您的版 权，请立即通知我们。
 * 2、 本站提供免费代码只可供研究学习使用，切勿用于商业用途 由此引起一切后果与本站无关。
 * 3、 商业源码请在源码授权范围内进行使用。
 * 4、更多APP精品源码下载请访问:http://www.appcodes.cn。
 * 5、如有疑问请发信息至appcodes@qq.com。
 */
package com.zftlive.android.sample.popupwindow;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.zftlive.android.R;
import com.zftlive.android.base.BaseActivity;
import com.zftlive.android.common.ActionBarManager;

/**
 * 底部弹出泡泡选择菜单样例
 * 
 * @author 曾繁添
 * @version 1.0
 * 
 */
public class PopDialogMenuActivity extends BaseActivity {

	private View root ;
	private PopupWindow popupWindow;

	@Override
	public int bindLayout() {
		return R.layout.activity_pop_dialog;
	}

	@Override
	public void initView(View view) {

		// 父窗口view
		root = findViewById(R.id.ll_root);
		
		//触发弹窗的控件
		TextView showPop = (TextView) view.findViewById(R.id.showPop);
		showPop.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				popupWindow.showAtLocation(root, Gravity.BOTTOM, 0, 0);
			}
		});
		
		//初始化带返回按钮的标题栏
		String strCenterTitle = getResources().getString(R.string.PopDialogMenuActivity);
		ActionBarManager.initBackTitle(getContext(), getActionBar(), strCenterTitle);
	}

	@Override
	public void doBusiness(Context mContext) {
		initPopupWindow();
	}

	@Override
	public void resume() {

	}

	@Override
	public void destroy() {

	}

	// 初始化方法
	private void initPopupWindow() {
		View contentView = getLayoutInflater().inflate(R.layout.activity_pop_dialog_menu, null);// 动态加载
		TextView mServerLogin = (TextView) contentView.findViewById(R.id.mServerLogin);
		TextView mSmsLogin = (TextView) contentView.findViewById(R.id.mSmsLogin);
		mServerLogin.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (popupWindow.isShowing())
					popupWindow.dismiss();
			}
		});
		mSmsLogin.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (popupWindow.isShowing())
					popupWindow.dismiss();
			}
		});
		
		// 全屏显示，将内容设置在底部
		popupWindow = new PopupWindow(contentView,ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
		popupWindow.setOutsideTouchable(false);
		popupWindow.setFocusable(true);
		popupWindow.setBackgroundDrawable(new BitmapDrawable());
		popupWindow.setAnimationStyle(R.style.pop_animation);
	}
}
