package com.emotiona.android.fastjson;

import com.emotiona.android.exception.NotKeyExpection;

/***
 * 
 * @author xiaoshi email:emotiona_xiaoshi@126.com
 * @TODO
 * @2015年7月17日
 *
 */
public interface Helper {
	String getCode()throws NotKeyExpection;
	String getText()throws NotKeyExpection;
	String getUrl()throws NotKeyExpection;
	String getList()throws NotKeyExpection;
	String getContentByKey(String key) throws NotKeyExpection;
	void put(String key,String value);
}
