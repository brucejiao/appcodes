/*
 *  http://www.appcodes.cn APP精品源码下载站声明：
 * 1、本站源码为网上搜集或网友提供，如果涉及或侵害到您的版 权，请立即通知我们。
 * 2、 本站提供免费代码只可供研究学习使用，切勿用于商业用途 由此引起一切后果与本站无关。
 * 3、 商业源码请在源码授权范围内进行使用。
 * 4、更多APP精品源码下载请访问:http://www.appcodes.cn。
 * 5、如有疑问请发信息至appcodes@qq.com。
 */
package com.zftlive.android.view.pulltorefresh.extras.listfragment;

import com.zftlive.android.view.pulltorefresh.PullToRefreshExpandableListView;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;


/**
 * A sample implementation of how to use {@link PullToRefreshExpandableListView}
 * with {@link ListFragment}. This implementation simply replaces the ListView
 * that {@code ListFragment} creates with a new
 * {@code PullToRefreshExpandableListView}. This means that ListFragment still
 * works 100% (e.g. <code>setListShown(...)</code> ).
 * <p/>
 * The new PullToRefreshListView is created in the method
 * {@link #onCreatePullToRefreshListView(LayoutInflater, Bundle)}. If you wish
 * to customise the {@code PullToRefreshExpandableListView} then override this
 * method and return your customised instance.
 * 
 * @author Chris Banes
 * 
 */
public class PullToRefreshExpandableListFragment extends PullToRefreshBaseListFragment<PullToRefreshExpandableListView> {

	protected PullToRefreshExpandableListView onCreatePullToRefreshListView(LayoutInflater inflater,
			Bundle savedInstanceState) {
		return new PullToRefreshExpandableListView(getActivity());
	}

}