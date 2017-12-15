/*
 *  http://www.appcodes.cn APP精品源码下载站声明：
 * 1、本站源码为网上搜集或网友提供，如果涉及或侵害到您的版 权，请立即通知我们。
 * 2、 本站提供免费代码只可供研究学习使用，切勿用于商业用途 由此引起一切后果与本站无关。
 * 3、 商业源码请在源码授权范围内进行使用。
 * 4、更多APP精品源码下载请访问:http://www.appcodes.cn。
 * 5、如有疑问请发信息至appcodes@qq.com。
 */
package com.zftlive.android.data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;

/**
 * 数据传输对象
 * 
 * @author 曾繁添
 * @version 1.0
 */
public class DTO<K, V> extends HashMap<K, V>  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6377960213315892547L;

	/**
	 * 赋值
	 * 
	 * @param objKey
	 *            键值
	 * @param objValue
	 *            对应值
	 */
	public V put(K objKey, V objValue) {
		if (readonly) {
			throw new RuntimeException("属性只读");
		} else {
			return super.put(objKey, objValue);
		}

	}

	/**
	 * 只读开关
	 */
	private boolean readonly = false;

	public void setReadonly(boolean readonly) {
		this.readonly = readonly;
	}
	
	/**
	 * 移除空值的Item
	 */
	public void removeEmptyValueItem(){
		for (Iterator<K> iterator = keySet().iterator(); iterator.hasNext();) {
			Object key = iterator.next();
			if(null == get(key) || "".equals(String.valueOf(get(key)))){
				remove(key);
			}
		}
	}

}
