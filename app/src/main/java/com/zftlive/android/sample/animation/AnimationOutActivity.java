/*
 *  http://www.appcodes.cn APP精品源码下载站声明：
 * 1、本站源码为网上搜集或网友提供，如果涉及或侵害到您的版 权，请立即通知我们。
 * 2、 本站提供免费代码只可供研究学习使用，切勿用于商业用途 由此引起一切后果与本站无关。
 * 3、 商业源码请在源码授权范围内进行使用。
 * 4、更多APP精品源码下载请访问:http://www.appcodes.cn。
 * 5、如有疑问请发信息至appcodes@qq.com。
 */
package com.zftlive.android.sample.animation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.zftlive.android.R;
import com.zftlive.android.base.BaseActivity;
import com.zftlive.android.common.ActionBarManager;
import com.zftlive.android.view.SwipeBackLayout;

/**
 * 动画启动退出界面
 * @author 曾繁添
 * @version 1.0
 *
 */
public class AnimationOutActivity extends BaseActivity {

	/**右滑关闭当前Activity顶层容器**/
	protected SwipeBackLayout rootView;
	
	@Override
	public int bindLayout() {
		return R.layout.activity_launcher;
	}

	@Override
	public void initView(View view) {
		//初始化带返回按钮的标题栏
		String strCenterTitle = getResources().getString(R.string.AnimationInActivity);
		ActionBarManager.initBackTitle(getContext(), getActionBar(), strCenterTitle);
		
		//追加右滑关闭activity顶层View
		rootView = (SwipeBackLayout) LayoutInflater.from(this).inflate(R.layout.view_swipeback_root, null);
		rootView.attachToActivity(this);
	}

	@Override
	public void doBusiness(Context mContext) {

	}

	@Override
	public void resume() {

	}

	@Override
	public void destroy() {

	}
}
