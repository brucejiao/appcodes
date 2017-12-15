/*
 *  http://www.appcodes.cn APP精品源码下载站声明：
 * 1、本站源码为网上搜集或网友提供，如果涉及或侵害到您的版 权，请立即通知我们。
 * 2、 本站提供免费代码只可供研究学习使用，切勿用于商业用途 由此引起一切后果与本站无关。
 * 3、 商业源码请在源码授权范围内进行使用。
 * 4、更多APP精品源码下载请访问:http://www.appcodes.cn。
 * 5、如有疑问请发信息至appcodes@qq.com。
 */
package com.zftlive.android.sample.menu;

import android.app.ActionBar;
import android.content.Context;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.zftlive.android.R;
import com.zftlive.android.base.BaseActivity;
import com.zftlive.android.common.ActionBarManager;

/**
 * 抽屉菜单
 * 
 * @author 曾繁添
 * @version 1.0
 * 
 */
public class DrawerLayoutActivity extends BaseActivity {
	private String[] mPlanetTitles;
	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;

	@Override
	public int bindLayout() {
		return R.layout.activity_drawerlayout;
	}

	@Override
	public void initView(View view) {
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

		// 绑定Listview
		mPlanetTitles = getResources().getStringArray(R.array.anim_type);
		mDrawerList = (ListView) findViewById(R.id.left_drawer);
		mDrawerList.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, mPlanetTitles));
		mDrawerList.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
				ActionBarManager.updateActionCenterTitle(getContext(),getActionBar(), mPlanetTitles[position]);
				mDrawerList.setItemChecked(position, true);
				mDrawerLayout.closeDrawer(mDrawerList);
			}
		});
	}

	@Override
	public void doBusiness(Context mContext) {
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(false);
		actionBar.setDisplayShowHomeEnabled(true);
		actionBar.setDisplayShowTitleEnabled(false);
		actionBar.setHomeButtonEnabled(true);
		actionBar.setLogo(R.drawable.ic_list_white_48dp);
		actionBar.setDisplayUseLogoEnabled(true);
		String strCenterTitle = getResources().getString(R.string.DrawerLayoutActivity);
		ActionBarManager.initTitleCenterActionBar(mContext,actionBar,strCenterTitle);
	}
	

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// 按钮按下，将抽屉打开
			if(mDrawerLayout.isDrawerOpen(Gravity.LEFT)){
				mDrawerLayout.closeDrawer(Gravity.LEFT);
			}else{
				mDrawerLayout.openDrawer(Gravity.LEFT);
			}
			break;
		default:
			break;
		}
		
	    return true;
	}

	@Override
	public void resume() {

	}

	@Override
	public void destroy() {

	}

}