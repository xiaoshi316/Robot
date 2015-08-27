package com.emotiona.android.exception;

import com.emotiona.android.logutils.LogUtils;

/***
 * 
 * @author xiaoshi email:emotiona_xiaoshi@126.com
 * @TODO 处理服务器返回JSON数据没有key的情况
 * @2015年8月26日
 *
 */
public class NotKeyExpection extends Exception {

	/**
	 * NotKyeExpection.java TODO 自定义异常类 emotiona
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 错误提示信息
	 */
	private String message;
	public NotKeyExpection() {
		// TODO Auto-generated constructor stub
		super();
	}

	public NotKeyExpection(String msg) {
		// TODO Auto-generated constructor stub
		this.message=msg;
	}
	@Override
	public void printStackTrace() {
		// TODO Auto-generated method stub
		LogUtils.e("错误提示",message);
		super.printStackTrace();
	}
}
