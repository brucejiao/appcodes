/*
 *  http://www.appcodes.cn APP精品源码下载站声明：
 * 1、本站源码为网上搜集或网友提供，如果涉及或侵害到您的版 权，请立即通知我们。
 * 2、 本站提供免费代码只可供研究学习使用，切勿用于商业用途 由此引起一切后果与本站无关。
 * 3、 商业源码请在源码授权范围内进行使用。
 * 4、更多APP精品源码下载请访问:http://www.appcodes.cn。
 * 5、如有疑问请发信息至appcodes@qq.com。
 */
package com.zftlive.android.base;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import android.app.Activity;

/**
 * Adapter基类
 * 
 * @author 曾繁添
 * @version 1.0
 * 
 */
public abstract class BaseAdapter extends android.widget.BaseAdapter {

	/** 数据存储集合 **/
	private List<Object> mDataList = new ArrayList<Object>();
	/** Context上下文 **/
	private Activity mContext;
	/** 每一页显示条数 **/
	private int mPerPageSize = 10;
	/**日志输出标志**/
	protected final String TAG = this.getClass().getSimpleName();

	public BaseAdapter() {
		this(null);
	}
	
	public BaseAdapter(Activity mContext) {
		this(mContext,10);
	}
	
	public BaseAdapter(Activity mContext,int mPerPageSize){
		this.mContext = mContext;
		this.mPerPageSize = mPerPageSize;
	}
	
	@Override
	public int getCount() {
		return mDataList.size();
	}

	@Override
	public Object getItem(int position) {
		if (position < mDataList.size())
			return mDataList.get(position);
		else
			return null;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}
	
	/**
	 * 获取当前页
	 * @return 当前页
	 */
	public int getPageNo(){
		return (getCount() / mPerPageSize) + 1;
	}
	
	/**
	 * 添加数据
	 * @param item 数据项
	 */
	public boolean addItem(Object object){
		return mDataList.add(object);
	}
	
	/**
	 * 在指定索引位置添加数据
	 * @param location 索引
	 * @param object 数据
	 */
	public void addItem(int location,Object object){
	     mDataList.add(location, object);
	}
	
	/**
	 * 集合方式添加数据
	 * @param collection 集合
	 */
	public boolean addItem(Collection<? extends Object> collection){
		return mDataList.addAll(collection);
	}
	
	/**
	 * 在指定索引位置添加数据集合
	 * @param location 索引
	 * @param collection 数据集合
	 */
	public boolean addItem(int location,Collection<? extends Object> collection){
		return mDataList.addAll(location,collection);
	}
	
	/**
	 * 移除指定对象数据
	 * @param object 移除对象
	 * @return 是否移除成功
	 */
	public boolean removeItem(Object object){
		return mDataList.remove(object);
	}
	
	/**
	 * 移除指定索引位置对象
	 * @param location 删除对象索引位置
	 * @return 被删除的对象
	 */
	public Object removeItem(int location){
	    return mDataList.remove(location);
	}
	
	/**
	 * 移除指定集合对象
	 * @param collection 待移除的集合
	 * @return 是否移除成功
	 */
	public boolean removeAll(Collection<? extends Object> collection){
		return mDataList.removeAll(collection);
	}
	
	/**
	 * 清空数据
	 */
	public void clear() {
		mDataList.clear();
	}
	
	/**
	 * 获取Activity方法
	 * @return Activity的子类
	 */
	public Activity getActivity(){
		if(null == mContext ) return null;
		
		if(mContext instanceof BaseActivity)
			return (BaseActivity)mContext;
		
		return null;
	}
}
