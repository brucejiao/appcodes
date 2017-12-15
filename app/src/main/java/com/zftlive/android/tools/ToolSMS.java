/*
 *  http://www.appcodes.cn APP精品源码下载站声明：
 * 1、本站源码为网上搜集或网友提供，如果涉及或侵害到您的版 权，请立即通知我们。
 * 2、 本站提供免费代码只可供研究学习使用，切勿用于商业用途 由此引起一切后果与本站无关。
 * 3、 商业源码请在源码授权范围内进行使用。
 * 4、更多APP精品源码下载请访问:http://www.appcodes.cn。
 * 5、如有疑问请发信息至appcodes@qq.com。
 */
package com.zftlive.android.tools;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

import com.zftlive.android.MApplication;


/**
 * 发送短信验证码工具类
 * @author 曾繁添
 * @version 1.0
 *
 */
public class ToolSMS {
	
	public static String APPKEY = "25c13dc2e1c4";
	public static String APPSECRET = "14340f710d155024867d4870786d4c10";
	public static String CHINA = "86";
	private static IValidateSMSCode mIValidateSMSCode;
	private static Handler mSMSHandle = new MySMSHandler();
	private static Context context = MApplication.gainContext();
	
	/**
	 * 初始化ShareSDK发送短信验证码实例
	 * @param appkey
	 * @param appSecrect
	 */
	public static void initSDK(String appkey, String appSecrect){
		// 初始化短信SDK
		SMSSDK.initSDK(context, appkey, appSecrect);
		//注册回调监听接口
		SMSSDK.registerEventHandler(new EventHandler() {
			public void afterEvent(int event, int result, Object data) {
				Message msg = new Message();
				msg.arg1 = event;
				msg.arg2 = result;
				msg.obj = data;
				mSMSHandle.sendMessage(msg);
			}
		});
	}
	
	/**
	 * 请求获取短信验证码
	 * @param phone 手机号
	 */
	public static void getVerificationCode(String phone){
		SMSSDK.getVerificationCode(CHINA, phone);
	}
	
	/**
	 * 提交短信验证码，校验是否正确
	 * @param phone 手机号
	 * @param validateCode 手机短信验证码
	 */
	public static void submitVerificationCode(String phone, String validateCode,IValidateSMSCode callback){
		mIValidateSMSCode = callback;
		SMSSDK.submitVerificationCode(CHINA, phone, validateCode);
	}
	
	/**
	 * 释放资源
	 */
	public static void release(){
		// 销毁回调监听接口
		SMSSDK.unregisterAllEventHandler();
	}
	
    /**
     * 消息处理Handle
     */
	private static class MySMSHandler extends Handler{
    	@Override
		public void handleMessage(Message msg) {
    		super.handleMessage(msg);
    		
    		int event = msg.arg1;
    		int result = msg.arg2;
    		Object data = msg.obj;
			
			if (result == SMSSDK.RESULT_COMPLETE) {
				//提交验证码成功
				if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
					//验证成功回调
					if(null != mIValidateSMSCode){
						mIValidateSMSCode.onSucced();
					}
				} 
			} else {
				Throwable exption = ((Throwable) data);
				//验证成功回调
				if(null != mIValidateSMSCode){
					mIValidateSMSCode.onFailed(exption);
				}
			}
		}
    }
	
    /**
     * 提交短信验证码回调接口
     */
    public interface IValidateSMSCode{
    	void onSucced();
    	void onFailed(Throwable e);
    }
}
