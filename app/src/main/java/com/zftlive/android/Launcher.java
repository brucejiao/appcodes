/*
 *  http://www.appcodes.cn APP精品源码下载站声明：
 * 1、本站源码为网上搜集或网友提供，如果涉及或侵害到您的版 权，请立即通知我们。
 * 2、 本站提供免费代码只可供研究学习使用，切勿用于商业用途 由此引起一切后果与本站无关。
 * 3、 商业源码请在源码授权范围内进行使用。
 * 4、更多APP精品源码下载请访问:http://www.appcodes.cn。
 * 5、如有疑问请发信息至appcodes@qq.com。
 */
package com.zftlive.android;

import android.content.Context;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;

import com.zftlive.android.base.BaseActivity;
import com.zftlive.android.sample.MainActivity;

/**
 * 程序启动界面
 * @author 曾繁添
 * @version 1.0
 *
 */
public class Launcher extends BaseActivity {

	@Override
	public int bindLayout() {
		return R.layout.activity_launcher;
	}

	@Override
	public void initView(View view) {
		
		//添加动画效果
		AlphaAnimation animation = new AlphaAnimation(0.3f, 1.0f);
		animation.setDuration(2000);
		animation.setAnimationListener(new AnimationListener() {

			@Override
			public void onAnimationStart(Animation animation) {
				
			}

			@Override
			public void onAnimationRepeat(Animation animation) {
				
			}

			@Override
			public void onAnimationEnd(Animation animation) {
				//跳转界面
				getOperation().forward(MainActivity.class);
				finish();
				//右往左推出效果
//				overridePendingTransition(R.anim.push_left_in,R.anim.push_left_out);
				//转动淡出效果1
				overridePendingTransition(R.anim.scale_rotate_in,R.anim.alpha_out);
				//下往上推出效果
//				overridePendingTransition(R.anim.push_up_in,R.anim.push_up_out);
			}
		});
		view.setAnimation(animation);
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