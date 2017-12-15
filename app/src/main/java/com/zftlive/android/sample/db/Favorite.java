/*
 *  http://www.appcodes.cn APP精品源码下载站声明：
 * 1、本站源码为网上搜集或网友提供，如果涉及或侵害到您的版 权，请立即通知我们。
 * 2、 本站提供免费代码只可供研究学习使用，切勿用于商业用途 由此引起一切后果与本站无关。
 * 3、 商业源码请在源码授权范围内进行使用。
 * 4、更多APP精品源码下载请访问:http://www.appcodes.cn。
 * 5、如有疑问请发信息至appcodes@qq.com。
 */
package com.zftlive.android.sample.db;


import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.zftlive.android.base.BaseEntity;

/**
 * [我的收藏]实体
 * @author 曾繁添
 */
@DatabaseTable(tableName="Favorite")
public class Favorite extends BaseEntity {

	/**
	 * 主键ID
	 */
	@DatabaseField(id=true)
	private String id;
	
	/**
	 * 发布日期yyyy-MM-dd
	 */
	@DatabaseField
	private String publishDate;
	
	/**
	 * 频道类别
	 */
	@DatabaseField
	private String category;
	
	/**
	 * 文章标题
	 */
	@DatabaseField
	private String title;
	
	/**
	 * 链接URL
	 */
	@DatabaseField
	private String linkURL ;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(String publishDate) {
		this.publishDate = publishDate;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLinkURL() {
		return linkURL;
	}

	public void setLinkURL(String linkURL) {
		this.linkURL = linkURL;
	}
}
