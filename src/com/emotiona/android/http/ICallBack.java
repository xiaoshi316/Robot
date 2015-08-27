package com.emotiona.android.http;

public interface ICallBack {
	/** 获取数据成功 */
	public void onSuccess(String response);

	/** 获取数据失败调接口 */
	public void onFailed(String error);
}
