/*
 *  http://www.appcodes.cn APP精品源码下载站声明：
 * 1、本站源码为网上搜集或网友提供，如果涉及或侵害到您的版 权，请立即通知我们。
 * 2、 本站提供免费代码只可供研究学习使用，切勿用于商业用途 由此引起一切后果与本站无关。
 * 3、 商业源码请在源码授权范围内进行使用。
 * 4、更多APP精品源码下载请访问:http://www.appcodes.cn。
 * 5、如有疑问请发信息至appcodes@qq.com。
 */
package com.zftlive.android.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Fragment基类(V4包)
 * @author 曾繁添
 * @version 1.0
 *
 */
public abstract class BaseFragmentV4 extends Fragment implements IBaseFragment{

	/**当前Fragment渲染的视图View**/
	private View mContextView = null;
	/**共通操作**/
	private Operation mBaseOperation = null;
	/**日志输出标志**/
	protected final String TAG = this.getClass().getSimpleName();
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		Log.d(TAG, "BaseFragmentV4-->onAttach()");
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.d(TAG, "BaseFragmentV4-->onCreate()");
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		Log.d(TAG, "BaseFragmentV4-->onCreateView()");
		
		//渲染视图View(防止切换时重绘View)
        if (null != mContextView) {
            ViewGroup parent = (ViewGroup) mContextView.getParent();
            if (null != parent) {
                parent.removeView(mContextView);
            }
        } else {
        	mContextView = inflater.inflate(bindLayout(), container);
        	// 控件初始化
            initView(mContextView);
    		//实例化共通操作
    		mBaseOperation = new Operation(getActivity());
        }
        
		//业务处理
		doBusiness(getActivity());
		
		return mContextView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		Log.d(TAG, "BaseFragmentV4-->onActivityCreated()");
		super.onActivityCreated(savedInstanceState);
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		Log.d(TAG, "BaseFragmentV4-->onSaveInstanceState()");
		super.onSaveInstanceState(outState);
	}

	@Override
	public void onStart() {
		Log.d(TAG, "BaseFragmentV4-->onStart()");
		super.onStart();
	}

	@Override
	public void onResume() {
		Log.d(TAG, "BaseFragmentV4-->onResume()");
		super.onResume();
	}

	@Override
	public void onPause() {
		Log.d(TAG, "BaseFragmentV4-->onPause()");
		super.onPause();
	}

	@Override
	public void onStop() {
		Log.d(TAG, "BaseFragmentV4-->onStop()");
		super.onStop();
	}

	@Override
	public void onDestroy() {
		Log.d(TAG, "BaseFragmentV4-->onDestroy()");
		super.onDestroy();
	}

	@Override
	public void onDetach() {
		Log.d(TAG, "BaseFragmentV4-->onDetach()");
		super.onDetach();
	}
	
	/**
	 * 获取当前Fragment依附在的Activity
	 * @return
	 */
	protected Activity getContext(){
		return getActivity();
	}
	
	/**
	 * 获取共通操作机能
	 */
	public Operation getOperation(){
		return this.mBaseOperation;
	}
	
}
