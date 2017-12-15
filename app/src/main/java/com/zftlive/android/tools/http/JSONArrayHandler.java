/*
 *  http://www.appcodes.cn APP精品源码下载站声明：
 * 1、本站源码为网上搜集或网友提供，如果涉及或侵害到您的版 权，请立即通知我们。
 * 2、 本站提供免费代码只可供研究学习使用，切勿用于商业用途 由此引起一切后果与本站无关。
 * 3、 商业源码请在源码授权范围内进行使用。
 * 4、更多APP精品源码下载请访问:http://www.appcodes.cn。
 * 5、如有疑问请发信息至appcodes@qq.com。
 */
package com.zftlive.android.tools.http;

import org.apache.http.Header;
import org.json.JSONArray;

import com.loopj.android.http.JsonHttpResponseHandler;

/**
 * JSONArray回调Handler
 * @author 曾繁添
 * @version 1.0
 */
public abstract class JSONArrayHandler extends JsonHttpResponseHandler {

	@Override
	public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
		super.onSuccess(statusCode, headers, response);
		success(response);
	}

	public void onFailure(int statusCode, Header[] headers,String responseBody, Throwable e) {
		super.onFailure(statusCode, headers, responseBody, e);
		failure(statusCode, responseBody, e);
	}
	
	public abstract void success(JSONArray jsonArray);
	
	public abstract void failure(int statusCode, String responseBody,Throwable e);

}
