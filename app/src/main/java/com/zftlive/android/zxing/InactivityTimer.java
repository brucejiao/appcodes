/*
 *  http://www.appcodes.cn APP精品源码下载站声明：
 * 1、本站源码为网上搜集或网友提供，如果涉及或侵害到您的版 权，请立即通知我们。
 * 2、 本站提供免费代码只可供研究学习使用，切勿用于商业用途 由此引起一切后果与本站无关。
 * 3、 商业源码请在源码授权范围内进行使用。
 * 4、更多APP精品源码下载请访问:http://www.appcodes.cn。
 * 5、如有疑问请发信息至appcodes@qq.com。
 */

package com.zftlive.android.zxing;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.os.BatteryManager;
import android.util.Log;
import com.zftlive.android.zxing.common.Runnable;

/**
 * Finishes an activity after a period of inactivity if the device is on battery
 * power. <br/>
 * <br/>
 * 
 * 该活动监控器全程监控扫描活跃状态，与CaptureActivity生命周期相同
 */
final class InactivityTimer {

	private static final String TAG = InactivityTimer.class.getSimpleName();

	/**
	 * 如果在5min内扫描器没有被使用过，则自动finish掉activity
	 */
	private static final long INACTIVITY_DELAY_MS = 5 * 60 * 1000L;

	/**
	 * 在本app中，此activity即为CaptureActivity
	 */
	private final Activity activity;
	/**
	 * 接受系统广播：手机是否连通电源
	 */
	private final BroadcastReceiver powerStatusReceiver;
	private boolean registered;
	private AsyncTask<?, ?, ?> inactivityTask;

	InactivityTimer(Activity activity) {
		this.activity = activity;
		powerStatusReceiver = new PowerStatusReceiver();
		registered = false;
		onActivity();
	}

	/**
	 * 首先终止之前的监控任务，然后新起一个监控任务
	 */
	synchronized void onActivity() {
		cancel();
		inactivityTask = new InactivityAsyncTask();
		Runnable.execAsync(inactivityTask);
	}

	public synchronized void onPause() {
		cancel();
		if (registered) {
			activity.unregisterReceiver(powerStatusReceiver);
			registered = false;
		}
		else {
			Log.w(TAG, "PowerStatusReceiver was never registered?");
		}
	}

	public synchronized void onResume() {
		if (registered) {
			Log.w(TAG, "PowerStatusReceiver was already registered?");
		}
		else {
			activity.registerReceiver(powerStatusReceiver, new IntentFilter(
					Intent.ACTION_BATTERY_CHANGED));
			registered = true;
		}
		onActivity();
	}

	/**
	 * 取消监控任务
	 */
	private synchronized void cancel() {
		AsyncTask<?, ?, ?> task = inactivityTask;
		if (task != null) {
			task.cancel(true);
			inactivityTask = null;
		}
	}

	void shutdown() {
		cancel();
	}

	/**
	 * 监听是否连通电源的系统广播。如果连通电源，则停止监控任务，否则重启监控任务
	 */
	private final class PowerStatusReceiver extends BroadcastReceiver {
		@Override
		public void onReceive(Context context, Intent intent) {
			if (Intent.ACTION_BATTERY_CHANGED.equals(intent.getAction())) {
				// 0 indicates that we're on battery
				boolean onBatteryNow = intent.getIntExtra(
						BatteryManager.EXTRA_PLUGGED, -1) <= 0;
				if (onBatteryNow) {
					InactivityTimer.this.onActivity();
				}
				else {
					InactivityTimer.this.cancel();
				}
			}
		}
	}

	/**
	 * 该任务很简单，就是在INACTIVITY_DELAY_MS时间后终结activity
	 */
	private final class InactivityAsyncTask extends
			AsyncTask<Object, Object, Object> {
		@Override
		protected Object doInBackground(Object... objects) {
			try {
				Thread.sleep(INACTIVITY_DELAY_MS);
				Log.i(TAG, "Finishing activity due to inactivity");
				activity.finish();
			}
			catch (InterruptedException e) {
				// continue without killing
			}
			return null;
		}
	}

}
