/*
 *  http://www.appcodes.cn APP精品源码下载站声明：
 * 1、本站源码为网上搜集或网友提供，如果涉及或侵害到您的版 权，请立即通知我们。
 * 2、 本站提供免费代码只可供研究学习使用，切勿用于商业用途 由此引起一切后果与本站无关。
 * 3、 商业源码请在源码授权范围内进行使用。
 * 4、更多APP精品源码下载请访问:http://www.appcodes.cn。
 * 5、如有疑问请发信息至appcodes@qq.com。
 */
package com.zftlive.android.view.pulltorefresh.extras;

import java.util.HashMap;

import com.zftlive.android.view.pulltorefresh.PullToRefreshBase;
import com.zftlive.android.view.pulltorefresh.PullToRefreshBase.Mode;
import com.zftlive.android.view.pulltorefresh.PullToRefreshBase.State;

import android.content.Context;
import android.media.MediaPlayer;
import android.view.View;

public class SoundPullEventListener<V extends View> implements PullToRefreshBase.OnPullEventListener<V> {

	private final Context mContext;
	private final HashMap<State, Integer> mSoundMap;

	private MediaPlayer mCurrentMediaPlayer;

	/**
	 * Constructor
	 * 
	 * @param context - Context
	 */
	public SoundPullEventListener(Context context) {
		mContext = context;
		mSoundMap = new HashMap<State, Integer>();
	}

	@Override
	public final void onPullEvent(PullToRefreshBase<V> refreshView, State event, Mode direction) {
		Integer soundResIdObj = mSoundMap.get(event);
		if (null != soundResIdObj) {
			playSound(soundResIdObj.intValue());
		}
	}

	/**
	 * Set the Sounds to be played when a Pull Event happens. You specify which
	 * sound plays for which events by calling this method multiple times for
	 * each event.
	 * <p/>
	 * If you've already set a sound for a certain event, and add another sound
	 * for that event, only the new sound will be played.
	 * 
	 * @param event - The event for which the sound will be played.
	 * @param resId - Resource Id of the sound file to be played (e.g.
	 *            <var>R.raw.pull_sound</var>)
	 */
	public void addSoundEvent(State event, int resId) {
		mSoundMap.put(event, resId);
	}

	/**
	 * Clears all of the previously set sounds and events.
	 */
	public void clearSounds() {
		mSoundMap.clear();
	}

	/**
	 * Gets the current (or last) MediaPlayer instance.
	 */
	public MediaPlayer getCurrentMediaPlayer() {
		return mCurrentMediaPlayer;
	}

	private void playSound(int resId) {
		// Stop current player, if there's one playing
		if (null != mCurrentMediaPlayer) {
			mCurrentMediaPlayer.stop();
			mCurrentMediaPlayer.release();
		}

		mCurrentMediaPlayer = MediaPlayer.create(mContext, resId);
		if (null != mCurrentMediaPlayer) {
			mCurrentMediaPlayer.start();
		}
	}

}
