/*
 *  http://www.appcodes.cn APP精品源码下载站声明：
 * 1、本站源码为网上搜集或网友提供，如果涉及或侵害到您的版 权，请立即通知我们。
 * 2、 本站提供免费代码只可供研究学习使用，切勿用于商业用途 由此引起一切后果与本站无关。
 * 3、 商业源码请在源码授权范围内进行使用。
 * 4、更多APP精品源码下载请访问:http://www.appcodes.cn。
 * 5、如有疑问请发信息至appcodes@qq.com。
 */
package com.zftlive.android.tools;

import com.thoughtworks.xstream.XStream;

/**
 * XML工具类
 * @author 曾繁添
 *
 */
public class ToolXml {

    /**
     * java 转换成xml
     * 
     * @Title: toXml
     * @Description: 将JavaBean转成XML
     * @param bean 对象实例
     * @return String xml字符串
     */
    public static String toXml(Object bean) {
        XStream xstream = new XStream();
        xstream.processAnnotations(bean.getClass()); 
        return xstream.toXML(bean);
    }

    /**
     * 将传入xml文本转换成Java对象
     * @param xml
     * @param bean xml对应的class类
     * @return T xml对应的class类的实例对象 调用的方法实例：
     * PersonBean person=XmlUtil.toBean(xmlStr, PersonBean.class);
     */
    public static <T> T toBean(String xml, Class<T> bean) {
        XStream xstream = new XStream();
        xstream.processAnnotations(bean);
        T obj = (T) xstream.fromXML(xml);
        return obj;
    }
}
