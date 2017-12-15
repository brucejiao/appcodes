/*
 *  http://www.appcodes.cn APP精品源码下载站声明：
 * 1、本站源码为网上搜集或网友提供，如果涉及或侵害到您的版 权，请立即通知我们。
 * 2、 本站提供免费代码只可供研究学习使用，切勿用于商业用途 由此引起一切后果与本站无关。
 * 3、 商业源码请在源码授权范围内进行使用。
 * 4、更多APP精品源码下载请访问:http://www.appcodes.cn。
 * 5、如有疑问请发信息至appcodes@qq.com。
 */
package com.zftlive.android.view.pulltorefresh.internal;

import com.zftlive.android.R;
import com.zftlive.android.view.pulltorefresh.PullToRefreshBase;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

@SuppressLint("ViewConstructor")
public class IndicatorLayout extends FrameLayout implements AnimationListener {

	static final int DEFAULT_ROTATION_ANIMATION_DURATION = 150;

	private Animation mInAnim, mOutAnim;
	private ImageView mArrowImageView;

	private final Animation mRotateAnimation, mResetRotateAnimation;

	public IndicatorLayout(Context context, PullToRefreshBase.Mode mode) {
		super(context);
		mArrowImageView = new ImageView(context);

		Drawable arrowD = getResources().getDrawable(R.drawable.view_pull_refresh_indicator_arrow);
		mArrowImageView.setImageDrawable(arrowD);

		final int padding = getResources().getDimensionPixelSize(R.dimen.indicator_internal_padding);
		mArrowImageView.setPadding(padding, padding, padding, padding);
		addView(mArrowImageView);

		int inAnimResId, outAnimResId;
		switch (mode) {
			case PULL_FROM_END:
				inAnimResId = R.anim.view_pull_refresh_slide_in_from_bottom;
				outAnimResId = R.anim.view_pull_refresh_slide_out_to_bottom;
				setBackgroundResource(R.drawable.view_pull_refresh_indicator_bg_bottom);

				// Rotate Arrow so it's pointing the correct way
				mArrowImageView.setScaleType(ScaleType.MATRIX);
				Matrix matrix = new Matrix();
				matrix.setRotate(180f, arrowD.getIntrinsicWidth() / 2f, arrowD.getIntrinsicHeight() / 2f);
				mArrowImageView.setImageMatrix(matrix);
				break;
			default:
			case PULL_FROM_START:
				inAnimResId = R.anim.view_pull_refresh_slide_in_from_top;
				outAnimResId = R.anim.view_pull_refresh_slide_out_to_top;
				setBackgroundResource(R.drawable.view_pull_refresh_indicator_bg_top);
				break;
		}

		mInAnim = AnimationUtils.loadAnimation(context, inAnimResId);
		mInAnim.setAnimationListener(this);

		mOutAnim = AnimationUtils.loadAnimation(context, outAnimResId);
		mOutAnim.setAnimationListener(this);

		final Interpolator interpolator = new LinearInterpolator();
		mRotateAnimation = new RotateAnimation(0, -180, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
				0.5f);
		mRotateAnimation.setInterpolator(interpolator);
		mRotateAnimation.setDuration(DEFAULT_ROTATION_ANIMATION_DURATION);
		mRotateAnimation.setFillAfter(true);

		mResetRotateAnimation = new RotateAnimation(-180, 0, Animation.RELATIVE_TO_SELF, 0.5f,
				Animation.RELATIVE_TO_SELF, 0.5f);
		mResetRotateAnimation.setInterpolator(interpolator);
		mResetRotateAnimation.setDuration(DEFAULT_ROTATION_ANIMATION_DURATION);
		mResetRotateAnimation.setFillAfter(true);

	}

	public final boolean isVisible() {
		Animation currentAnim = getAnimation();
		if (null != currentAnim) {
			return mInAnim == currentAnim;
		}

		return getVisibility() == View.VISIBLE;
	}

	public void hide() {
		startAnimation(mOutAnim);
	}

	public void show() {
		mArrowImageView.clearAnimation();
		startAnimation(mInAnim);
	}

	@Override
	public void onAnimationEnd(Animation animation) {
		if (animation == mOutAnim) {
			mArrowImageView.clearAnimation();
			setVisibility(View.GONE);
		} else if (animation == mInAnim) {
			setVisibility(View.VISIBLE);
		}

		clearAnimation();
	}

	@Override
	public void onAnimationRepeat(Animation animation) {
		// NO-OP
	}

	@Override
	public void onAnimationStart(Animation animation) {
		setVisibility(View.VISIBLE);
	}

	public void releaseToRefresh() {
		mArrowImageView.startAnimation(mRotateAnimation);
	}

	public void pullToRefresh() {
		mArrowImageView.startAnimation(mResetRotateAnimation);
	}

}
