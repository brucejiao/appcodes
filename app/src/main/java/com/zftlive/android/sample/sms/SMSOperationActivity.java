/*
 *  http://www.appcodes.cn APP精品源码下载站声明：
 * 1、本站源码为网上搜集或网友提供，如果涉及或侵害到您的版 权，请立即通知我们。
 * 2、 本站提供免费代码只可供研究学习使用，切勿用于商业用途 由此引起一切后果与本站无关。
 * 3、 商业源码请在源码授权范围内进行使用。
 * 4、更多APP精品源码下载请访问:http://www.appcodes.cn。
 * 5、如有疑问请发信息至appcodes@qq.com。
 */
package com.zftlive.android.sample.sms;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.zftlive.android.R;
import com.zftlive.android.base.BaseActivity;
import com.zftlive.android.common.ActionBarManager;
import com.zftlive.android.tools.ToolAlert;
import com.zftlive.android.tools.ToolPhone;
import com.zftlive.android.tools.ToolString;

public class SMSOperationActivity extends BaseActivity implements
		View.OnClickListener {

	private EditText et_phonenumber, et_content;
	private Button btn_send, btn_bind, btn_choice;
	private SMSBroadcastReceiver mSMSBroadcastReceiver;
	private final static int CHOICE_PHONE = 1;

	@Override
	public int bindLayout() {
		return R.layout.activity_sms_operation;
	}

	@Override
	public void initView(View view) {
		et_phonenumber = (EditText) findViewById(R.id.et_phonenumber);
		et_content = (EditText) findViewById(R.id.et_content);
		btn_send = (Button) findViewById(R.id.btn_send);
		btn_send.setOnClickListener(this);

		btn_bind = (Button) findViewById(R.id.btn_bind);
		btn_bind.setOnClickListener(this);

		btn_choice = (Button) findViewById(R.id.btn_choice);
		btn_choice.setOnClickListener(this);
		
		//初始化带返回按钮的标题栏
		String strCenterTitle = getResources().getString(R.string.SMSOperationActivity);
		ActionBarManager.initBackTitle(getContext(), getActionBar(), strCenterTitle);

	}

	@Override
	public void doBusiness(Context mContext) {

	}

	@Override
	public void resume() {

	}

	@Override
	public void destroy() {

		if (mSMSBroadcastReceiver != null
				&& mSMSBroadcastReceiver.isOrderedBroadcast()) {
			// 取消订阅广播
			unregisterReceiver(mSMSBroadcastReceiver);
		}
	}

	@Override
	public void onClick(View v) {

		String phoneNumber = et_phonenumber.getText().toString();
		String strContent = et_content.getText().toString();

		switch (v.getId()) {
		case R.id.btn_choice:
			// 跳转至选择联系人界面
			ToolPhone.toChooseContactsList(this, CHOICE_PHONE);
			break;
		case R.id.btn_bind:
			if (ToolString.isNoBlankAndNoNull(phoneNumber)) {
				IntentFilter filter = new IntentFilter();
				filter.addAction("android.provider.Telephony.SMS_RECEIVED");
				filter.setPriority(Integer.MAX_VALUE);
				mSMSBroadcastReceiver = new SMSBroadcastReceiver();
				registerReceiver(mSMSBroadcastReceiver, filter);
				ToolAlert.toastShort(this, "绑定拦截成功");
			} else {
				ToolAlert.toastShort(this, "手机号不能为空");
			}
			break;
		case R.id.btn_send:

			if (ToolString.isNoBlankAndNoNull(phoneNumber)
					&& ToolString.isNoBlankAndNoNull(strContent)) {
				ToolPhone.sendMessage(this,phoneNumber, strContent);
				// ToolSMS.sendMessage(this, phoneNumber,
				// strContent);//跳转到发送短信界面
			} else {
				ToolAlert.toastShort(this, "手机号和短信内容两者都不能为空");
			}
			break;
		default:
			break;
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		switch (requestCode) {
		case CHOICE_PHONE:
			if (Activity.RESULT_OK == resultCode) {
				Uri uri = data.getData();
				Cursor c = managedQuery(uri, null, null, null, null);
				c.moveToFirst();  
		        et_phonenumber.setText(ToolPhone.getChoosedPhoneNumber(this,resultCode,data));  
			}
			break;
		default:
			break;
		}
	}

}
