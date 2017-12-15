/*
 *  http://www.appcodes.cn APP精品源码下载站声明：
 * 1、本站源码为网上搜集或网友提供，如果涉及或侵害到您的版 权，请立即通知我们。
 * 2、 本站提供免费代码只可供研究学习使用，切勿用于商业用途 由此引起一切后果与本站无关。
 * 3、 商业源码请在源码授权范围内进行使用。
 * 4、更多APP精品源码下载请访问:http://www.appcodes.cn。
 * 5、如有疑问请发信息至appcodes@qq.com。
 */
package com.zftlive.android.view.webview;

import com.zftlive.android.tools.ToolAlert;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.util.Log;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * 自定义的浏览器
 * @author 曾繁添
 *
 */
public class UIWebViewClient extends WebViewClient {

	/***跳转上下文**/
	private Context context;
	
	/***跳转界面对象**/
	private Class target;
	
	/***是否程序内部打开：true-->程序内部打开网页，false-->调用别的窗体打开**/
	private Boolean isInnerOpen = true;

	/**过滤目标字符串**/
	private String filter = "";
	
	private String TAG = UIWebViewClient.class.getSimpleName();
	
	/**当前加载网页URL***/
	public static String currentURL = "";
	
	/**
	 * 默认内部打开链接
	 */
	public UIWebViewClient() {
		this(true);
	}
	
	/**
	 * @param isInnerOpen 是否内部打开
	 */
	public UIWebViewClient(Boolean isInnerOpen) {
		this(null,null,isInnerOpen);
	}
	
	/**
	 * 
	 * @param context 上下文
	 * @param target 处理新开网页URL界面
	 * @param isInnerOpen 是否内部打开
	 */
	public UIWebViewClient(Context context, Class target, Boolean isInnerOpen) {
		this(context,target,isInnerOpen,"");
	}
	
	/**
	 * 
	 * @param context 上下文
	 * @param target 处理新开网页URL界面
	 * @param isInnerOpen 是否内部打开
	 * @param filter 过滤字符串
	 */
	public UIWebViewClient(Context context, Class target, Boolean isInnerOpen,String filter) {
		this.context = context;
		this.target = target;
		this.isInnerOpen = isInnerOpen;
		this.filter = filter;
	}

	/***
	 * 让浏览器支持访问https请求
	 */
	@SuppressLint("NewApi")
	public void onReceivedSslError(WebView view, SslErrorHandler handler,
			SslError error) {
		 handler.proceed();  
		 super.onReceivedSslError(view, handler, error);
	}
	
	/**
	 * 控制网页的链接跳转打开方式（拦截URL）
	 */
	public boolean shouldOverrideUrlLoading(WebView view, String url) {
		currentURL = url;
		if (isInnerOpen) {
			view.loadUrl(url);
			return true;
		} else {
			String host = Uri.parse(url).getHost();
			if (!host.startsWith("http")){
				host = "http://" + host;
			}
			if (!host.endsWith("/")){
				host = host + "/";
			}

			if (null != context && null != target) {
				if (host.equals(filter)) {
					Intent intent = new Intent(context, target);
					intent.putExtra("url", url);
					context.startActivity(intent);
					return true;
				}
			}
			return false;
		}
	}
	
	 @Override  
     public void onPageStarted(WebView view, String url, Bitmap favicon) {  
         super.onPageStarted(view, url, favicon);
         Log.e(TAG, "onPageStarted--->url="+url);
     }  

     @Override  
     public void onPageFinished(WebView view, String url) {  
    	//加载完毕后，开始加载图片
         //view.getSettings().setBlockNetworkImage(false);
         
         Log.e(TAG, "onPageFinished--->url="+url);
         super.onPageFinished(view, url);  
     }  

     @Override  
     public void onReceivedError(WebView view, int errorCode,String description, String failingUrl) {  
    	 ToolAlert.closeLoading();
    	 ToolAlert.toastShort("加载数据失败，错误码："+errorCode+ "\n 原因描述："+description);
         super.onReceivedError(view, errorCode, description, failingUrl);  
     }  
}
