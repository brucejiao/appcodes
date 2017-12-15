/*
 *  http://www.appcodes.cn APP精品源码下载站声明：
 * 1、本站源码为网上搜集或网友提供，如果涉及或侵害到您的版 权，请立即通知我们。
 * 2、 本站提供免费代码只可供研究学习使用，切勿用于商业用途 由此引起一切后果与本站无关。
 * 3、 商业源码请在源码授权范围内进行使用。
 * 4、更多APP精品源码下载请访问:http://www.appcodes.cn。
 * 5、如有疑问请发信息至appcodes@qq.com。
 */
package com.zftlive.android.view;

import java.util.List;

import android.content.Context;
import android.widget.ArrayAdapter;

import com.zftlive.android.model.Option;

/***下拉框适配器***/
public class SpinnerAdapter extends ArrayAdapter<Option>{

	public SpinnerAdapter(Context context, int resource,List<Option> objects) {
		super(context, resource, objects);
	}

	public SpinnerAdapter(Context context, int resource,int textViewResourceId, List<Option> objects) {
		super(context, resource, textViewResourceId, objects);
	}

	public SpinnerAdapter(Context context, int resource, Option[] objects) {
		super(context, resource, objects);
	}
	
	public SpinnerAdapter(Context context, int resource,int textViewResourceId, Option[] objects) {
		super(context, resource, textViewResourceId, objects);
	}
}
