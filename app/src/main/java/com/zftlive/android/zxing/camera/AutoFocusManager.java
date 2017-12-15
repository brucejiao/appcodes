/*
 *  http://www.appcodes.cn APP精品源码下载站声明：
 * 1、本站源码为网上搜集或网友提供，如果涉及或侵害到您的版 权，请立即通知我们。
 * 2、 本站提供免费代码只可供研究学习使用，切勿用于商业用途 由此引起一切后果与本站无关。
 * 3、 商业源码请在源码授权范围内进行使用。
 * 4、更多APP精品源码下载请访问:http://www.appcodes.cn。
 * 5、如有疑问请发信息至appcodes@qq.com。
 */

package com.zftlive.android.zxing.camera;

import java.util.ArrayList;
import java.util.Collection;

import android.content.Context;
import android.content.SharedPreferences;
import android.hardware.Camera;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.util.Log;
import com.zftlive.android.zxing.config.Config;

/**
 * 由于对焦不是一次性完成的任务（手抖），而系统提供的对焦仅有Camera.autoFocus()方法，
 * 因此需要一个线程来不断调用Camera.autoFocus()直到用户满意按下快门为止
 */
final class AutoFocusManager implements Camera.AutoFocusCallback {

	private static final String TAG = AutoFocusManager.class.getSimpleName();

	private static final long AUTO_FOCUS_INTERVAL_MS = 2000L;
	private static final Collection<String> FOCUS_MODES_CALLING_AF;
	static {
		FOCUS_MODES_CALLING_AF = new ArrayList<String>(2);
		FOCUS_MODES_CALLING_AF.add(Camera.Parameters.FOCUS_MODE_AUTO);
		FOCUS_MODES_CALLING_AF.add(Camera.Parameters.FOCUS_MODE_MACRO);
	}

	private boolean active;
	private final boolean useAutoFocus;
	private final Camera camera;
	private AsyncTask<?, ?, ?> outstandingTask;

	AutoFocusManager(Context context, Camera camera) {
		this.camera = camera;
		SharedPreferences sharedPrefs = PreferenceManager
				.getDefaultSharedPreferences(context);
		String currentFocusMode = camera.getParameters().getFocusMode();
		useAutoFocus = sharedPrefs.getBoolean(Config.KEY_AUTO_FOCUS, true)
				&& FOCUS_MODES_CALLING_AF.contains(currentFocusMode);
		Log.i(TAG, "Current focus mode '" + currentFocusMode
				+ "'; use auto focus? " + useAutoFocus);
		start();
	}

	@Override
	public synchronized void onAutoFocus(boolean success, Camera theCamera) {
		if (active) {
			outstandingTask = new AutoFocusTask();
			com.zftlive.android.zxing.common.Runnable.execAsync(outstandingTask);
		}
	}

	synchronized void start() {
		if (useAutoFocus) {
			active = true;
			try {
				camera.autoFocus(this);
			}
			catch (RuntimeException re) {
				// Have heard RuntimeException reported in Android 4.0.x+;
				// continue?
				Log.w(TAG, "Unexpected exception while focusing", re);
			}
		}
	}

	synchronized void stop() {
		if (useAutoFocus) {
			try {
				camera.cancelAutoFocus();
			}
			catch (RuntimeException re) {
				// Have heard RuntimeException reported in Android 4.0.x+;
				// continue?
				Log.w(TAG, "Unexpected exception while cancelling focusing", re);
			}
		}
		if (outstandingTask != null) {
			outstandingTask.cancel(true);
			outstandingTask = null;
		}
		active = false;
	}

	private final class AutoFocusTask extends AsyncTask<Object, Object, Object> {
		@Override
		protected Object doInBackground(Object... voids) {
			try {
				Thread.sleep(AUTO_FOCUS_INTERVAL_MS);
			}
			catch (InterruptedException e) {
				// continue
			}
			synchronized (AutoFocusManager.this) {
				if (active) {
					start();
				}
			}
			return null;
		}
	}

}
