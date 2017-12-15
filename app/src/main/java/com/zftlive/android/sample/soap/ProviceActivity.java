/*
 *  http://www.appcodes.cn APP精品源码下载站声明：
 * 1、本站源码为网上搜集或网友提供，如果涉及或侵害到您的版 权，请立即通知我们。
 * 2、 本站提供免费代码只可供研究学习使用，切勿用于商业用途 由此引起一切后果与本站无关。
 * 3、 商业源码请在源码授权范围内进行使用。
 * 4、更多APP精品源码下载请访问:http://www.appcodes.cn。
 * 5、如有疑问请发信息至appcodes@qq.com。
 */
package com.zftlive.android.sample.soap;

import java.util.ArrayList;
import java.util.List;

import org.ksoap2.serialization.SoapObject;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.zftlive.android.R;
import com.zftlive.android.base.BaseActivity;
import com.zftlive.android.common.ActionBarManager;
import com.zftlive.android.tools.ToolAlert;
import com.zftlive.android.tools.ToolSOAP;


/**
 * 调用WebService接口获取省份Activity
 * @author 曾繁添
 * @version 1.0
 *
 */
public class ProviceActivity extends BaseActivity {
	
	/**显示省份Listview**/
	private ListView mProvinceList;
	/***省份数据集合***/
	private List<String> provinceList = new ArrayList<String>();
	/***获取天气WebService**/
	public static final String WEB_SERVER_URL = "http://www.webxml.com.cn/WebServices/WeatherWebService.asmx";
	/***获取天气WebService命名空间**/
	public static final String NAME_SPACE = "http://WebXml.com.cn/";

	@Override
	public int bindLayout() {
		return R.layout.activity_soap_provice_city;
	}

	@Override
	public void initView(View view) {
		mProvinceList = (ListView) findViewById(R.id.province_list);
		mProvinceList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
				getOperation().addParameter("province", provinceList.get(position));
				getOperation().forward(CityActivity.class);
			}
		});
		
		//初始化带返回按钮的标题栏
		ActionBarManager.initBackTitle(getContext(), getActionBar(), "省份列表");
	}

	@Override
	public void doBusiness(final Context mContext) {
		//等待对话框
		ToolAlert.loading(this, "数据加载中...");
		
		//呼叫WebService接口
		ToolSOAP.callService(ProviceActivity.WEB_SERVER_URL,ProviceActivity.NAME_SPACE,"getSupportProvince", null, new ToolSOAP.WebServiceCallBack() {
			
			@Override
			public void onSucced(SoapObject result) {
				
				//关闭等待对话框
				ToolAlert.closeLoading();
				if(result != null){
					provinceList = parseSoapObject(result);
					mProvinceList.setAdapter(new ArrayAdapter<String>(ProviceActivity.this, android.R.layout.simple_list_item_1, provinceList));
				}else{
					ToolAlert.toastShort(mContext, "呼叫WebService-->getSupportProvince失败");
				}
			}

			@Override
			public void onFailure(String result) {
				//关闭等待对话框
				ToolAlert.closeLoading();
				
				ToolAlert.toastShort(mContext, "呼叫WebService-->getSupportProvince失败，原因："+result);
			}
		});
	}

	@Override
	public void resume() {
		
	}

	@Override
	public void destroy() {
		
	}
	
	/**
	 * 解析SoapObject对象
	 * @param result SoapObject查询结果对象
	 * @return
	 */
	private List<String> parseSoapObject(SoapObject result){
		List<String> list = new ArrayList<String>();
		SoapObject provinceSoapObject = (SoapObject) result.getProperty("getSupportProvinceResult");
		if(provinceSoapObject == null) {
			return null;
		}
		for(int i=0; i<provinceSoapObject.getPropertyCount(); i++){
			list.add(provinceSoapObject.getProperty(i).toString());
		}
		
		return list;
	}

}
