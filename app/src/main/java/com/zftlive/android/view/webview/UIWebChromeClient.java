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
import com.zftlive.android.view.webview.UIWebView.IOnReceivedTitle;

import android.view.View;
import android.webkit.WebView;
import android.widget.ProgressBar;

public class UIWebChromeClient extends android.webkit.WebChromeClient {
	
	private IOnReceivedTitle mIOnReceivedTitle;

	private ProgressBar progressbar;
	
	public UIWebChromeClient() {
		this(null,null);
	}

	public UIWebChromeClient(IOnReceivedTitle mIOnReceivedTitle) {
		this(mIOnReceivedTitle,null);
	}
	
	public UIWebChromeClient(ProgressBar progressbar) {
		this(null,progressbar);
	}
	
	public UIWebChromeClient(IOnReceivedTitle mIOnReceivedTitle,ProgressBar progressbar) {
		this.mIOnReceivedTitle = mIOnReceivedTitle;
		this.progressbar = progressbar;
	}

	/***页面加载进度**/
    public void onProgressChanged(WebView view, int newProgress) {
        if (newProgress == 100) {
        	if(null != progressbar){
        		progressbar.setVisibility(View.GONE);
        	}
        	ToolAlert.closeLoading();
        } else {
        	if(null != progressbar)
        	{
        		if (progressbar.getVisibility() == View.GONE){
                	progressbar.setVisibility(View.VISIBLE);
                }
                progressbar.setProgress(newProgress);
        	}
        }
        super.onProgressChanged(view, newProgress);
    }
    
    /**
     * 获取到网页标题回调函数
     */
    public void onReceivedTitle(WebView view, String title) {
    	if(null != mIOnReceivedTitle){
    		mIOnReceivedTitle.onReceivedTitle(title);
    	}
        super.onReceivedTitle(view, title);
    }

}
