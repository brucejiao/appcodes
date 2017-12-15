/*
 *  http://www.appcodes.cn APP精品源码下载站声明：
 * 1、本站源码为网上搜集或网友提供，如果涉及或侵害到您的版 权，请立即通知我们。
 * 2、 本站提供免费代码只可供研究学习使用，切勿用于商业用途 由此引起一切后果与本站无关。
 * 3、 商业源码请在源码授权范围内进行使用。
 * 4、更多APP精品源码下载请访问:http://www.appcodes.cn。
 * 5、如有疑问请发信息至appcodes@qq.com。
 */
package com.zftlive.android.view.pulltorefresh.extras.listfragment;

import com.zftlive.android.view.pulltorefresh.PullToRefreshBase;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;

abstract class PullToRefreshBaseListFragment<T extends PullToRefreshBase<? extends AbsListView>> extends ListFragment {

	private T mPullToRefreshListView;

	@Override
	public final View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View layout = super.onCreateView(inflater, container, savedInstanceState);

		ListView lv = (ListView) layout.findViewById(android.R.id.list);
		ViewGroup parent = (ViewGroup) lv.getParent();

		// Remove ListView and add PullToRefreshListView in its place
		int lvIndex = parent.indexOfChild(lv);
		parent.removeViewAt(lvIndex);
		mPullToRefreshListView = onCreatePullToRefreshListView(inflater, savedInstanceState);
		parent.addView(mPullToRefreshListView, lvIndex, lv.getLayoutParams());

		return layout;
	}

	/**
	 * @return The {@link PullToRefreshBase} attached to this ListFragment.
	 */
	public final T getPullToRefreshListView() {
		return mPullToRefreshListView;
	}

	/**
	 * Returns the {@link PullToRefreshBase} which will replace the ListView
	 * created from ListFragment. You should override this method if you wish to
	 * customise the {@link PullToRefreshBase} from the default.
	 * 
	 * @param inflater - LayoutInflater which can be used to inflate from XML.
	 * @param savedInstanceState - Bundle passed through from
	 *            {@link ListFragment#onCreateView(LayoutInflater, ViewGroup, Bundle)
	 *            onCreateView(...)}
	 * @return The {@link PullToRefreshBase} which will replace the ListView.
	 */
	protected abstract T onCreatePullToRefreshListView(LayoutInflater inflater, Bundle savedInstanceState);

}