package com.xiaoshi.chattingrobot.fastjson;

/***
 * 
 * @author xiaoshi email:emotiona_xiaoshi@126.com
 * @TODO
 * @2015年7月17日
 *
 */
public interface Helper {
	String getCode();
	String getText();
	String getUrl();
	String getList();
	String getContentByKey(String key);
	void put(String key,String value);
}
