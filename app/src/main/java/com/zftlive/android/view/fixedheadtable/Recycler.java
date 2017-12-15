/*
 *  http://www.appcodes.cn APP精品源码下载站声明：
 * 1、本站源码为网上搜集或网友提供，如果涉及或侵害到您的版 权，请立即通知我们。
 * 2、 本站提供免费代码只可供研究学习使用，切勿用于商业用途 由此引起一切后果与本站无关。
 * 3、 商业源码请在源码授权范围内进行使用。
 * 4、更多APP精品源码下载请访问:http://www.appcodes.cn。
 * 5、如有疑问请发信息至appcodes@qq.com。
 */
package com.zftlive.android.view.fixedheadtable;

import java.util.Stack;

import android.view.View;

/**
 * The Recycler facilitates reuse of views across layouts.
 * 
 * @author Brais Gab�n (InQBarna)
 */
public class Recycler {

	private Stack<View>[] views;

	/**
	 * Constructor
	 * 
	 * @param size
	 *            The number of types of view to recycle.
	 */
	@SuppressWarnings("unchecked")
	public Recycler(int size) {
		views = new Stack[size];
		for (int i = 0; i < size; i++) {
			views[i] = new Stack<View>();
		}
	}

	/**
	 * Add a view to the Recycler. This view may be reused in the function
	 * {@link #getRecycledView(int)}
	 * 
	 * @param view
	 *            A view to add to the Recycler. It can no longer be used.
	 * @param type
	 *            the type of the view.
	 */
	public void addRecycledView(View view, int type) {
		views[type].push(view);
	}

	/**
	 * Returns, if exists, a view of the type <code>typeView</code>.
	 * 
	 * @param typeView
	 *            the type of view that you want.
	 * @return a view of the type <code>typeView</code>. <code>null</code> if
	 *         not found.
	 */
	public View getRecycledView(int typeView) {
		try {
			return views[typeView].pop();
		} catch (java.util.EmptyStackException e) {
			return null;

		}
	}
}
