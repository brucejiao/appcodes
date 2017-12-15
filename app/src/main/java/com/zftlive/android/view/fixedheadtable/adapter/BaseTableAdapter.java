/*
 *  http://www.appcodes.cn APP精品源码下载站声明：
 * 1、本站源码为网上搜集或网友提供，如果涉及或侵害到您的版 权，请立即通知我们。
 * 2、 本站提供免费代码只可供研究学习使用，切勿用于商业用途 由此引起一切后果与本站无关。
 * 3、 商业源码请在源码授权范围内进行使用。
 * 4、更多APP精品源码下载请访问:http://www.appcodes.cn。
 * 5、如有疑问请发信息至appcodes@qq.com。
 */
package com.zftlive.android.view.fixedheadtable.adapter;

import android.database.DataSetObservable;
import android.database.DataSetObserver;

/**
 * Common base class of common implementation for an {@link TableAdapter} that
 * can be used in {@link TableFixHeaders}.
 * 
 * @author Brais Gab韓 (InQBarna)
 */
public abstract class BaseTableAdapter implements TableAdapter {
	private final DataSetObservable mDataSetObservable = new DataSetObservable();

	@Override
	public void registerDataSetObserver(DataSetObserver observer) {
		mDataSetObservable.registerObserver(observer);
	}

	@Override
	public void unregisterDataSetObserver(DataSetObserver observer) {
		mDataSetObservable.unregisterObserver(observer);
	}

	/**
	 * Notifies the attached observers that the underlying data has been changed
	 * and any View reflecting the data set should refresh itself.
	 */
	public void notifyDataSetChanged() {
		mDataSetObservable.notifyChanged();
	}

	/**
	 * Notifies the attached observers that the underlying data is no longer
	 * valid or available. Once invoked this adapter is no longer valid and
	 * should not report further data set changes.
	 */
	public void notifyDataSetInvalidated() {
		mDataSetObservable.notifyInvalidated();
	}
}
