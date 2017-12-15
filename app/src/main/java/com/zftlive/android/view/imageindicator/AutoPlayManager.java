/*
 *  http://www.appcodes.cn APP精品源码下载站声明：
 * 1、本站源码为网上搜集或网友提供，如果涉及或侵害到您的版 权，请立即通知我们。
 * 2、 本站提供免费代码只可供研究学习使用，切勿用于商业用途 由此引起一切后果与本站无关。
 * 3、 商业源码请在源码授权范围内进行使用。
 * 4、更多APP精品源码下载请访问:http://www.appcodes.cn。
 * 5、如有疑问请发信息至appcodes@qq.com。
 */
package com.zftlive.android.view.imageindicator;

import android.os.Handler;
import android.os.Message;

/**
 * Auto BrocastManager for ImageIndicatorView
 * 
 * @author steven-pan
 * 
 */
public class AutoPlayManager {

	/**
	 * 自动播放标志位，默认播放
	 */
	private boolean broadcastEnable = false;
	/**
	 * 自动播放启动默认时间
	 */
	private static final long DEFAULT_STARTMILS = 2 * 1000;
	/**
	 * 自动播放间隔默认时间
	 */
	private static final long DEFAULT_INTEVALMILS = 3 * 1000;
	/**
	 * 启动时间ms
	 */
	private long startMils = DEFAULT_STARTMILS;
	/**
	 * 间隔ms
	 */
	private long intevalMils = DEFAULT_INTEVALMILS;
	/**
	 * 向右
	 */
	private final static int RIGHT = 0;
	/**
	 * 向左
	 */
	private final static int LEFT = 1;

	/**
	 * 当前方向
	 */
	private int direction = RIGHT;

	/**
	 * 自动播放默认次数（无限）
	 */
	private static final int DEFAULT_TIMES = -1;

	/**
	 * 自动播放次数
	 */
	private int broadcastTimes = DEFAULT_TIMES;

	/**
	 * 自动播放次数记数
	 */
	private int timesCount = 0;

	/**
	 * 循环播放
	 */
	private Handler broadcastHandler = null;

	/**
	 * target ImageIndicatorView
	 */
	private ImageIndicatorView mImageIndicatorView = null;

	public AutoPlayManager(ImageIndicatorView imageIndicatorView) {
		this.mImageIndicatorView = imageIndicatorView;
		this.broadcastHandler = new BroadcastHandler(AutoPlayManager.this);
	}

	/**
	 * 设置自动播放启动时间和间隔
	 * 
	 * @param startMils
	 *            启动时间ms(>2, 默认为8s)
	 * @param intevelMils
	 *            间隔ms(默认为3s)
	 */
	public void setBroadcastTimeIntevel(long startMils, long intevelMils) {
		this.startMils = startMils;
		this.intevalMils = intevelMils;
	}

	/**
	 * 设置自动播放开关
	 * 
	 * @param flag
	 *            打开或关闭
	 */
	public void setBroadcastEnable(boolean flag) {
		this.broadcastEnable = flag;
	}

	/**
	 * 设置循环播放次数
	 * 
	 * @param times
	 *            循环播放次数
	 */
	public void setBroadCastTimes(int times) {
		this.broadcastTimes = times;
	}

	/**
	 * 启动循环播放
	 */
	public void loop() {
		if (broadcastEnable) {
			broadcastHandler.sendEmptyMessageDelayed(0, this.startMils);
		}
	}

	protected void handleMessage(android.os.Message msg) {
		if (broadcastEnable) {
			if (System.currentTimeMillis()
					- mImageIndicatorView.getRefreshTime() < 2 * 1000) {// 最近一次划动间隔小于2s
				return;
			}
			if ((broadcastTimes != DEFAULT_TIMES)
					&& (timesCount > broadcastTimes)) {// 循环次数用完
				return;
			}

			if (direction == RIGHT) {// roll right
				if (mImageIndicatorView.getCurrentIndex() < mImageIndicatorView
						.getTotalCount()) {
					if (mImageIndicatorView.getCurrentIndex() == mImageIndicatorView
							.getTotalCount() - 1) {
						timesCount++;// 循环次数次数加1
						direction = LEFT;
					} else {
						mImageIndicatorView
								.getViewPager()
								.setCurrentItem(
										mImageIndicatorView.getCurrentIndex() + 1,
										true);
					}
				}
			} else {// roll left
				if (mImageIndicatorView.getCurrentIndex() >= 0) {
					if (mImageIndicatorView.getCurrentIndex() == 0) {
						direction = RIGHT;
					} else {
						mImageIndicatorView
								.getViewPager()
								.setCurrentItem(
										mImageIndicatorView.getCurrentIndex() - 1,
										true);
					}
				}
			}

			broadcastHandler.sendEmptyMessageDelayed(1, this.intevalMils);
		}
	}

	static class BroadcastHandler extends Handler {
		private AutoPlayManager autoBrocastManager;

		public BroadcastHandler(AutoPlayManager autoBrocastManager) {
			this.autoBrocastManager = autoBrocastManager;
		}

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			if (this.autoBrocastManager != null) {
				autoBrocastManager.handleMessage(msg);
			}
		}
	}

}
