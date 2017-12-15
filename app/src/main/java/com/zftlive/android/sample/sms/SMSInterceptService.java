/*
 *  http://www.appcodes.cn APP精品源码下载站声明：
 * 1、本站源码为网上搜集或网友提供，如果涉及或侵害到您的版 权，请立即通知我们。
 * 2、 本站提供免费代码只可供研究学习使用，切勿用于商业用途 由此引起一切后果与本站无关。
 * 3、 商业源码请在源码授权范围内进行使用。
 * 4、更多APP精品源码下载请访问:http://www.appcodes.cn。
 * 5、如有疑问请发信息至appcodes@qq.com。
 */
package com.zftlive.android.sample.sms;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.IBinder;
import android.telephony.SmsMessage;

import com.zftlive.android.base.BaseBroadcastReceiver;
import com.zftlive.android.base.BaseService;
import com.zftlive.android.tools.ToolAlert;

public class SMSInterceptService extends BaseService {

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onCreate() {
		super.onCreate();

		IntentFilter filter = new IntentFilter();
		filter.addAction("android.provider.Telephony.SMS_RECEIVED");
		filter.setPriority(Integer.MAX_VALUE-1);
		registerReceiver(new SmsReceiver(), filter);
	}

	/**
	 * 订阅短信广播
	 * 
	 */
	protected class SmsReceiver extends BaseBroadcastReceiver 
	{

		@Override
		public void onReceive(Context context, Intent intent) 
		{
			Bundle bundle = intent.getExtras();
			if (bundle != null) 
			{
				Object[] pdusObjects = (Object[]) bundle.get("pdus");
				SmsMessage[] messages = new SmsMessage[pdusObjects.length];
				for (int i = 0; i < pdusObjects.length; i++) {
					messages[i] = SmsMessage.createFromPdu((byte[]) pdusObjects[i]);
				}

				for (SmsMessage message : messages) {
					System.out.println("SMSInterceptService-->来信号码："+ message.getDisplayOriginatingAddress()+ "\n短信内容：" + message.getDisplayMessageBody());
					ToolAlert.toastLong(context,"SMSInterceptService.SmsReceiver-->拦截到来自【" + message.getDisplayOriginatingAddress()+ "】的短信-->"+ message.getDisplayMessageBody());
				}
			}
		}

	}

}
