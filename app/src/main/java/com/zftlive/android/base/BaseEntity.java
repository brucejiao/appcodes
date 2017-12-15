/*
 *  http://www.appcodes.cn APP精品源码下载站声明：
 * 1、本站源码为网上搜集或网友提供，如果涉及或侵害到您的版 权，请立即通知我们。
 * 2、 本站提供免费代码只可供研究学习使用，切勿用于商业用途 由此引起一切后果与本站无关。
 * 3、 商业源码请在源码授权范围内进行使用。
 * 4、更多APP精品源码下载请访问:http://www.appcodes.cn。
 * 5、如有疑问请发信息至appcodes@qq.com。
 */
package com.zftlive.android.base;

import java.io.Serializable;

import com.j256.ormlite.field.DatabaseField;

/**
 * 实体基类
 * @author 曾繁添
 * @version 1.0
 *
 */
public abstract class BaseEntity implements Serializable {

	/**
	 */
	private static final long serialVersionUID = 6337104618534280060L;
	
	/**
	 * 主键ID
	 */
	@DatabaseField(id=true)
	protected String id;
	
	/**
	 * 备注
	 */
	@DatabaseField
	protected String remark;
	
	/**
	 * 版本号
	 */
	@DatabaseField(defaultValue="1")
	protected Integer version;
	
	/**
	 * 是否有效
	 */
	@DatabaseField(defaultValue="true")
	protected Boolean valid;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Boolean getValid() {
		return valid;
	}

	public void setValid(Boolean valid) {
		this.valid = valid;
	}

}
