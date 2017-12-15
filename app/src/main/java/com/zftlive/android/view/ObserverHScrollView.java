/*
 *  http://www.appcodes.cn APP精品源码下载站声明：
 * 1、本站源码为网上搜集或网友提供，如果涉及或侵害到您的版 权，请立即通知我们。
 * 2、 本站提供免费代码只可供研究学习使用，切勿用于商业用途 由此引起一切后果与本站无关。
 * 3、 商业源码请在源码授权范围内进行使用。
 * 4、更多APP精品源码下载请访问:http://www.appcodes.cn。
 * 5、如有疑问请发信息至appcodes@qq.com。
 */
package com.zftlive.android.view;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

/*
 * 自定义的 滚动控件
 * 重载了 onScrollChanged（滚动条变化）,监听每次的变化通知给 观察(此变化的)观察者
 * 可使用 AddOnScrollChangedListener 来订阅本控件的 滚动条变化
 * */
public class ObserverHScrollView extends HorizontalScrollView {
	ScrollViewObserver mScrollViewObserver = new ScrollViewObserver();

	public ObserverHScrollView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public ObserverHScrollView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public ObserverHScrollView(Context context) {
		super(context);
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		Log.i("ObserverHScrollView","MyHScrollView onTouchEvent");
		return super.onTouchEvent(ev);
	}

	@Override
	protected void onScrollChanged(int l, int t, int oldl, int oldt) {
		/*
		 * 当滚动条移动后，引发 滚动事件。通知给观察者，观察者会传达给其他的。
		 */
		if (mScrollViewObserver != null /*&& (l != oldl || t != oldt)*/) {
			mScrollViewObserver.NotifyOnScrollChanged(l, t, oldl, oldt);
		}
		super.onScrollChanged(l, t, oldl, oldt);
	}

	/*
	 * 订阅 本控件 的 滚动条变化事件
	 * */
	public void AddOnScrollChangedListener(OnScrollChangedListener listener) {
		mScrollViewObserver.AddOnScrollChangedListener(listener);
	}

	/*
	 * 取消 订阅 本控件 的 滚动条变化事件
	 * */
	public void RemoveOnScrollChangedListener(OnScrollChangedListener listener) {
		mScrollViewObserver.RemoveOnScrollChangedListener(listener);
	}

	/*
	 * 当发生了滚动事件时
	 */
	public static interface OnScrollChangedListener {
		public void onScrollChanged(int l, int t, int oldl, int oldt);
	}

	/*
	 * 观察者
	 */
	public static class ScrollViewObserver {
		List<OnScrollChangedListener> mList;

		public ScrollViewObserver() {
			super();
			mList = new ArrayList<OnScrollChangedListener>();
		}

		public void AddOnScrollChangedListener(OnScrollChangedListener listener) {
			mList.add(listener);
		}

		public void RemoveOnScrollChangedListener(
				OnScrollChangedListener listener) {
			mList.remove(listener);
		}

		public void NotifyOnScrollChanged(int l, int t, int oldl, int oldt) {
			if (mList == null || mList.size() == 0) {
				return;
			}
			for (int i = 0; i < mList.size(); i++) {
				if (mList.get(i) != null) {
					mList.get(i).onScrollChanged(l, t, oldl, oldt);
				}
			}
		}
	}
}
