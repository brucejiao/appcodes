/*
 *  http://www.appcodes.cn APP精品源码下载站声明：
 * 1、本站源码为网上搜集或网友提供，如果涉及或侵害到您的版 权，请立即通知我们。
 * 2、 本站提供免费代码只可供研究学习使用，切勿用于商业用途 由此引起一切后果与本站无关。
 * 3、 商业源码请在源码授权范围内进行使用。
 * 4、更多APP精品源码下载请访问:http://www.appcodes.cn。
 * 5、如有疑问请发信息至appcodes@qq.com。
 */
package com.zftlive.android.sample.basic;

import android.content.Context;
import android.view.View;
import android.widget.Button;

import com.zftlive.android.R;
import com.zftlive.android.base.BaseActivity;
import com.zftlive.android.common.ActionBarManager;
import com.zftlive.android.tools.ToolLocation;
import com.zftlive.android.tools.ToolPhone;

/**
 * 基本常用操作测试样例
 * @author 曾繁添
 * @version 1.0
 *
 */
public class BasicTestActivity extends BaseActivity implements View.OnClickListener{

	private Button btn_opengps, btn_call,btn_contact,btn_setting,btn_carema,btn_photo;
	
	@Override
	public int bindLayout() {
		return R.layout.activity_basic_test;
	}

	@Override
	public void initView(View view) {
		btn_opengps = (Button) findViewById(R.id.btn_opengps);
		btn_opengps.setOnClickListener(this);
		btn_call = (Button) findViewById(R.id.btn_call);
		btn_call.setOnClickListener(this);
		btn_contact = (Button) findViewById(R.id.btn_contact);
		btn_contact.setOnClickListener(this);
		btn_setting = (Button) findViewById(R.id.btn_setting);
		btn_setting.setOnClickListener(this);
		btn_carema = (Button) findViewById(R.id.btn_carema);
		btn_carema.setOnClickListener(this);
		btn_photo = (Button) findViewById(R.id.btn_photo);
		btn_photo.setOnClickListener(this);
		
		//初始化带返回按钮的标题栏
		String strCenterTitle = getResources().getString(R.string.BasicTestActivity);
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

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_opengps:
			ToolLocation.forceOpenGPS(this);
			break;
		case R.id.btn_call:
			ToolPhone.callPhone(this, "10086");
			break;
		case R.id.btn_contact:
			ToolPhone.toChooseContactsList(getContext(), 99);
			break;
		case R.id.btn_setting:
			ToolPhone.toSettingActivity(getContext());
			break;
		case R.id.btn_carema:
			ToolPhone.toCameraActivity(getContext(), 88);
			break;
		case R.id.btn_photo:
			ToolPhone.toImagePickerActivity(getContext(), 77);
			break;
		default:
			break;
		}
	}

}
