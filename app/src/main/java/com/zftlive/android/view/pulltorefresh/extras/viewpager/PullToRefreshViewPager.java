/*
 *  http://www.appcodes.cn APP精品源码下载站声明：
 * 1、本站源码为网上搜集或网友提供，如果涉及或侵害到您的版 权，请立即通知我们。
 * 2、 本站提供免费代码只可供研究学习使用，切勿用于商业用途 由此引起一切后果与本站无关。
 * 3、 商业源码请在源码授权范围内进行使用。
 * 4、更多APP精品源码下载请访问:http://www.appcodes.cn。
 * 5、如有疑问请发信息至appcodes@qq.com。
 */
package com.zftlive.android.view.pulltorefresh.extras.viewpager;

import com.zftlive.android.R;
import com.zftlive.android.view.pulltorefresh.PullToRefreshBase;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;

public class PullToRefreshViewPager extends PullToRefreshBase<ViewPager> {

	public PullToRefreshViewPager(Context context) {
		super(context);
	}

	public PullToRefreshViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	public final Orientation getPullToRefreshScrollDirection() {
		return Orientation.HORIZONTAL;
	}

	@Override
	protected ViewPager createRefreshableView(Context context, AttributeSet attrs) {
		ViewPager viewPager = new ViewPager(context, attrs);
		viewPager.setId(R.id.viewpager);
		return viewPager;
	}

	@Override
	protected boolean isReadyForPullStart() {
		ViewPager refreshableView = getRefreshableView();

		PagerAdapter adapter = refreshableView.getAdapter();
		if (null != adapter) {
			return refreshableView.getCurrentItem() == 0;
		}

		return false;
	}

	@Override
	protected boolean isReadyForPullEnd() {
		ViewPager refreshableView = getRefreshableView();

		PagerAdapter adapter = refreshableView.getAdapter();
		if (null != adapter) {
			return refreshableView.getCurrentItem() == adapter.getCount() - 1;
		}

		return false;
	}
}
