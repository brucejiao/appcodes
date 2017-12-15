/*
 *  http://www.appcodes.cn APP精品源码下载站声明：
 * 1、本站源码为网上搜集或网友提供，如果涉及或侵害到您的版 权，请立即通知我们。
 * 2、 本站提供免费代码只可供研究学习使用，切勿用于商业用途 由此引起一切后果与本站无关。
 * 3、 商业源码请在源码授权范围内进行使用。
 * 4、更多APP精品源码下载请访问:http://www.appcodes.cn。
 * 5、如有疑问请发信息至appcodes@qq.com。
 */
package com.zftlive.android.tools;

import android.graphics.ColorMatrixColorFilter;  
import android.view.MotionEvent;  
import android.view.View;  
import android.view.View.OnTouchListener;  
import android.widget.ImageView;  
  
public class ToolAnimation {  
      
    /** 
     * 给试图添加点击效果,让背景变深 
     * */  
    public static void addTouchDrak(View view , boolean isClick){  
        view.setOnTouchListener( VIEW_TOUCH_DARK ) ;   
          
        if(!isClick){  
            view.setOnClickListener(new View.OnClickListener() {  
                  
                @Override  
                public void onClick(View v) {  
                }  
            });  
        }  
    }  
      
    /** 
     * 给试图添加点击效果,让背景变暗 
     * */  
    public static void addTouchLight(View view , boolean isClick){  
        view.setOnTouchListener( VIEW_TOUCH_LIGHT ) ;   
          
        if(!isClick){  
            view.setOnClickListener(new View.OnClickListener() {  
                  
                @Override  
                public void onClick(View v) {  
                }  
            });  
        }  
    }  
      
      
    /** 
     * 让控件点击时，颜色变深 
     * */  
    public static final OnTouchListener VIEW_TOUCH_DARK = new OnTouchListener() {  
  
        public final float[] BT_SELECTED = new float[] { 1, 0, 0, 0, -50, 0, 1,  
                0, 0, -50, 0, 0, 1, 0, -50, 0, 0, 0, 1, 0 };  
        public final float[] BT_NOT_SELECTED = new float[] { 1, 0, 0, 0, 0, 0,  
                1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0 };  
  
        @Override  
        public boolean onTouch(View v, MotionEvent event) {  
            if (event.getAction() == MotionEvent.ACTION_DOWN) {  
                if(v instanceof ImageView){  
                    ImageView iv = (ImageView) v;  
                    iv.setColorFilter( new ColorMatrixColorFilter(BT_SELECTED) ) ;   
                }else{  
                    v.getBackground().setColorFilter( new ColorMatrixColorFilter(BT_SELECTED) );  
                    v.setBackgroundDrawable(v.getBackground());  
                }  
            } else if (event.getAction() == MotionEvent.ACTION_UP) {  
                if(v instanceof ImageView){  
                    ImageView iv = (ImageView) v;  
                    iv.setColorFilter( new ColorMatrixColorFilter(BT_NOT_SELECTED) ) ;   
                }else{  
                    v.getBackground().setColorFilter(  
                            new ColorMatrixColorFilter(BT_NOT_SELECTED));  
                    v.setBackgroundDrawable(v.getBackground());  
                }  
            }  
            return false;  
        }  
    };  
      
    /** 
     * 让控件点击时，颜色变暗 
     * */  
    public static final OnTouchListener VIEW_TOUCH_LIGHT = new OnTouchListener(){  
  
        public final float[] BT_SELECTED = new float[] { 1, 0, 0, 0, 50, 0, 1,  
                0, 0, 50, 0, 0, 1, 0, 50, 0, 0, 0, 1, 0 };  
        public final float[] BT_NOT_SELECTED = new float[] { 1, 0, 0, 0, 0, 0,  
                1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0 };  
          
        @Override  
        public boolean onTouch(View v, MotionEvent event) {  
            if (event.getAction() == MotionEvent.ACTION_DOWN) {  
                if(v instanceof ImageView){  
                    ImageView iv = (ImageView) v;  
                    iv.setDrawingCacheEnabled(true);   
                      
                    iv.setColorFilter( new ColorMatrixColorFilter(BT_SELECTED) ) ;   
                }else{  
                    v.getBackground().setColorFilter( new ColorMatrixColorFilter(BT_SELECTED) );  
                    v.setBackgroundDrawable(v.getBackground());  
                }  
            } else if (event.getAction() == MotionEvent.ACTION_UP) {  
                if(v instanceof ImageView){  
                    ImageView iv = (ImageView) v;   
                    iv.setColorFilter( new ColorMatrixColorFilter(BT_NOT_SELECTED) ) ;   
                    System.out.println( "变回来" );   
                }else{  
                    v.getBackground().setColorFilter(  
                            new ColorMatrixColorFilter(BT_NOT_SELECTED));  
                    v.setBackgroundDrawable(v.getBackground());  
                }  
            }  
            return false;  
        }  
    };  
}  