/*
 *  http://www.appcodes.cn APP精品源码下载站声明：
 * 1、本站源码为网上搜集或网友提供，如果涉及或侵害到您的版 权，请立即通知我们。
 * 2、 本站提供免费代码只可供研究学习使用，切勿用于商业用途 由此引起一切后果与本站无关。
 * 3、 商业源码请在源码授权范围内进行使用。
 * 4、更多APP精品源码下载请访问:http://www.appcodes.cn。
 * 5、如有疑问请发信息至appcodes@qq.com。
 */
package com.zftlive.android.view.progressbar;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.ProgressBar;

/**
 * 垂直进度条
 * @author 曾繁添
 *
 */
public class VerticalProgressBar extends ProgressBar
{

	public VerticalProgressBar(Context context, AttributeSet attrs, int defStyle)
	{
		super(context, attrs, defStyle);
	}

	public VerticalProgressBar(Context context, AttributeSet attrs)
	{
		super(context, attrs);
	}

	public VerticalProgressBar(Context context)
	{
		super(context);
	}

	@Override
	protected synchronized void onDraw(Canvas canvas)
	{
		//反转90度，将水平ProgressBar竖起来
		canvas.rotate(-90);
		
		//将经过旋转后得到的VerticalProgressBar移到正确的位置
		canvas.translate(-getHeight(), 0);
		
		super.onDraw(canvas);
	}

	@Override
	protected synchronized void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(heightMeasureSpec, widthMeasureSpec);
		//互换宽高值
		setMeasuredDimension(getMeasuredHeight(), getMeasuredWidth());
	}
	
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		//互换宽高值
		super.onSizeChanged(h, w, oldw, oldh);
	}
}
