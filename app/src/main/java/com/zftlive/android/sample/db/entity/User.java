/*
 *  http://www.appcodes.cn APP精品源码下载站声明：
 * 1、本站源码为网上搜集或网友提供，如果涉及或侵害到您的版 权，请立即通知我们。
 * 2、 本站提供免费代码只可供研究学习使用，切勿用于商业用途 由此引起一切后果与本站无关。
 * 3、 商业源码请在源码授权范围内进行使用。
 * 4、更多APP精品源码下载请访问:http://www.appcodes.cn。
 * 5、如有疑问请发信息至appcodes@qq.com。
 */
package com.zftlive.android.sample.db.entity;


import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.zftlive.android.base.BaseEntity;

/**
 * 首页[菜单]数据
 * 
 * @author 曾繁添
 */
@DatabaseTable(tableName = "User")
public class User extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1270221627651225756L;

	/**
	 * 序号
	 */
	@DatabaseField
	private String orderNo;
	
	/**
	 * 用户名
	 */
	@DatabaseField
	private String username;

	/**
	 * 电子邮箱
	 */
	@DatabaseField
	private String email;

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
