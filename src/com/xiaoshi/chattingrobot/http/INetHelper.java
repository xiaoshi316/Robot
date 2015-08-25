package com.xiaoshi.chattingrobot.http;

import java.util.Map;

import android.content.Context;


public interface INetHelper {
	/***
	 * 
	 * @param url
	 * @param callback
	 * @param simulation
	 * @param simulation_name
	 * @param context
	 * @2015年8月25日下午6:01:00
	 * @TODO 用一句话来描述此功能的用途
	 * @author emotiona
	 * @Email emotiona_xiaoshi@126.com
	 */
	public void getResult(String url,ICallBack callback,boolean simulation,String simulation_name,Context context);
	/***
	 * 
	 * @param url
	 * @param paramMap
	 * @param callback
	 * @param simulation
	 * @param simulation_name
	 * @param context
	 * @2015年8月25日下午6:00:43
	 * @TODO 用一句话来描述此功能的用途
	 * @author emotiona
	 * @Email emotiona_xiaoshi@126.com
	 */
	public void postResult(String url,Map<String,String> paramMap,ICallBack callback,boolean simulation,String simulation_name,Context context);
}
