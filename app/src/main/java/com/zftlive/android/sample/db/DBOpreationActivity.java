/*
 *  http://www.appcodes.cn APP精品源码下载站声明：
 * 1、本站源码为网上搜集或网友提供，如果涉及或侵害到您的版 权，请立即通知我们。
 * 2、 本站提供免费代码只可供研究学习使用，切勿用于商业用途 由此引起一切后果与本站无关。
 * 3、 商业源码请在源码授权范围内进行使用。
 * 4、更多APP精品源码下载请访问:http://www.appcodes.cn。
 * 5、如有疑问请发信息至appcodes@qq.com。
 */
package com.zftlive.android.sample.db;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;

import com.zftlive.android.R;
import com.zftlive.android.base.BaseActivity;
import com.zftlive.android.view.SwipeListView;

public class DBOpreationActivity extends BaseActivity {

	private SwipeListView mListView;
	private MyFavDataAdapter mAdapter;
	
	@Override
	public int bindLayout() {
		return R.layout.activity_db_fav_list;
	}

	@Override
	public void initView(View view) {
		mListView = (SwipeListView) findViewById(R.id.fav_listview);
		mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
//				//没有显示删除时进行跳转
//				if(!mListView.isShowRight()){
//					Intent intent = new Intent(getActivity(),EssayDetailActivity.class);
//					intent.putExtra("url", ((Favorite)mAdapter.getItem(position)).getLinkURL());
//					getActivity().startActivity(intent);
//				}
			}
		});
	}

	@Override
	public void doBusiness(Context mContext) {

	}

	@Override
	public void resume() {

	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}
