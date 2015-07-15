package com.xiaoshi.chattingrobot.http;

import java.util.Map;


public interface INetHelper {
	/***
	 * 功能：网络请求接口
	 */
	/**
	 * get方法
	 * @return 返回请求成功后的流
	 */
	public void getResult(String url,ICallBack callback);
	/**
	 * post方法
	 * @return 返回调用接口成功后的流
	 */
	public void postResult(String url,Map<String,String> paramMap,ICallBack callback);
}
