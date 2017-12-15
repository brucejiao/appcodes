/*
 *  http://www.appcodes.cn APP精品源码下载站声明：
 * 1、本站源码为网上搜集或网友提供，如果涉及或侵害到您的版 权，请立即通知我们。
 * 2、 本站提供免费代码只可供研究学习使用，切勿用于商业用途 由此引起一切后果与本站无关。
 * 3、 商业源码请在源码授权范围内进行使用。
 * 4、更多APP精品源码下载请访问:http://www.appcodes.cn。
 * 5、如有疑问请发信息至appcodes@qq.com。
 */
package com.zftlive.android.view.pulltorefresh.extras;

import java.util.concurrent.atomic.AtomicBoolean;

import com.zftlive.android.view.pulltorefresh.PullToRefreshBase.Mode;
import com.zftlive.android.view.pulltorefresh.PullToRefreshWebView;

import android.content.Context;
import android.util.AttributeSet;
import android.webkit.WebView;

/**
 * An advanced version of {@link PullToRefreshWebView} which delegates the
 * triggering of the PullToRefresh gesture to the Javascript running within the
 * WebView. This means that you should only use this class if:
 * <p/>
 * <ul>
 * <li>{@link PullToRefreshWebView} doesn't work correctly because you're using
 * <code>overflow:scroll</code> or something else which means
 * {@link WebView#getScrollY()} doesn't return correct values.</li>
 * <li>You control the web content being displayed, as you need to write some
 * Javascript callbacks.</li>
 * </ul>
 * <p/>
 * <p/>
 * The way this call works is that when a PullToRefresh gesture is in action,
 * the following Javascript methods will be called:
 * <code>isReadyForPullDown()</code> and <code>isReadyForPullUp()</code>, it is
 * your job to calculate whether the view is in a state where a PullToRefresh
 * can happen, and return the result via the callback mechanism. An example can
 * be seen below:
 * <p/>
 * 
 * <pre>
 * function isReadyForPullDown() {
 *   var result = ...  // Probably using the .scrollTop DOM attribute
 *   ptr.isReadyForPullDownResponse(result);
 * }
 * 
 * function isReadyForPullUp() {
 *   var result = ...  // Probably using the .scrollBottom DOM attribute
 *   ptr.isReadyForPullUpResponse(result);
 * }
 * </pre>
 * 
 * @author Chris Banes
 */
public class PullToRefreshWebView2 extends PullToRefreshWebView {

	static final String JS_INTERFACE_PKG = "ptr";
	static final String DEF_JS_READY_PULL_DOWN_CALL = "javascript:isReadyForPullDown();";
	static final String DEF_JS_READY_PULL_UP_CALL = "javascript:isReadyForPullUp();";

	public PullToRefreshWebView2(Context context) {
		super(context);
	}

	public PullToRefreshWebView2(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public PullToRefreshWebView2(Context context, Mode mode) {
		super(context, mode);
	}

	private JsValueCallback mJsCallback;
	private final AtomicBoolean mIsReadyForPullDown = new AtomicBoolean(false);
	private final AtomicBoolean mIsReadyForPullUp = new AtomicBoolean(false);

	@Override
	protected WebView createRefreshableView(Context context, AttributeSet attrs) {
		WebView webView = super.createRefreshableView(context, attrs);

		// Need to add JS Interface so we can get the response back
		mJsCallback = new JsValueCallback();
		webView.addJavascriptInterface(mJsCallback, JS_INTERFACE_PKG);

		return webView;
	}

	@Override
	protected boolean isReadyForPullStart() {
		// Call Javascript...
		getRefreshableView().loadUrl(DEF_JS_READY_PULL_DOWN_CALL);

		// Response will be given to JsValueCallback, which will update
		// mIsReadyForPullDown

		return mIsReadyForPullDown.get();
	}

	@Override
	protected boolean isReadyForPullEnd() {
		// Call Javascript...
		getRefreshableView().loadUrl(DEF_JS_READY_PULL_UP_CALL);

		// Response will be given to JsValueCallback, which will update
		// mIsReadyForPullUp

		return mIsReadyForPullUp.get();
	}

	/**
	 * Used for response from Javascript
	 * 
	 * @author Chris Banes
	 */
	final class JsValueCallback {

		public void isReadyForPullUpResponse(boolean response) {
			mIsReadyForPullUp.set(response);
		}

		public void isReadyForPullDownResponse(boolean response) {
			mIsReadyForPullDown.set(response);
		}
	}
}
