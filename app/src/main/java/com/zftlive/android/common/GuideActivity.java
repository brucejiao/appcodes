/*
 *  http://www.appcodes.cn APP精品源码下载站声明：
 * 1、本站源码为网上搜集或网友提供，如果涉及或侵害到您的版 权，请立即通知我们。
 * 2、 本站提供免费代码只可供研究学习使用，切勿用于商业用途 由此引起一切后果与本站无关。
 * 3、 商业源码请在源码授权范围内进行使用。
 * 4、更多APP精品源码下载请访问:http://www.appcodes.cn。
 * 5、如有疑问请发信息至appcodes@qq.com。
 */
package com.zftlive.android.common;

import android.content.Context;
import android.view.View;

import com.zftlive.android.R;
import com.zftlive.android.base.BaseActivity;
import com.zftlive.android.view.imageindicator.ImageIndicatorView;

/**
 * 引导界面
 * @author 曾繁添
 * @version 1.0
 *
 */
public class GuideActivity extends BaseActivity {

	private ImageIndicatorView imageIndicatorView;
	@Override
	public int bindLayout() {
		return R.layout.activity_guide;
	}

	@Override
	public void initView(View view) {
		imageIndicatorView = (ImageIndicatorView) findViewById(R.id.guide_indicate_view);
		//滑动监听器
		imageIndicatorView.setOnItemChangeListener(new ImageIndicatorView.OnItemChangeListener() {
			@Override
			public void onPosition(int position, int totalCount) {
				if(position==totalCount){
					finish();
				}
			}
		});
	}

	@Override
	public void doBusiness(Context mContext) {
		
		final Integer[] resArray = new Integer[] { R.drawable.guide01, R.drawable.guide02, R.drawable.guide03 };
		imageIndicatorView.setupLayoutByDrawable(resArray);
		imageIndicatorView.setIndicateStyle(ImageIndicatorView.INDICATE_USERGUIDE_STYLE);
		imageIndicatorView.show();
	}

	@Override
	public void resume() {
		
	}

	@Override
	public void destroy() {
		
	}

}
