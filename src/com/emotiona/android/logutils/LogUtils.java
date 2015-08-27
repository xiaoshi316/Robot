package com.emotiona.android.logutils;

import com.emotiona.android.MyAppaction;

import android.util.Log;
/***
 * 
 * @author xiaoshi email:emotiona_xiaoshi@126.com
 * @TODO 日志工具类
 * @2015年8月26日
 *
 */
public class LogUtils
{

	private LogUtils()
	{
		/* cannot be instantiated */
		throw new UnsupportedOperationException("cannot be instantiated(不能被实例化)");
	}
	private static boolean isDebug = MyAppaction.ISLOG;
	private static final String TAG = "TAG";

	// 下面四个是默认tag的函数
	public static void i(String msg)
	{
		if (isDebug)
			Log.i(TAG, msg);
	}

	public static void d(String msg)
	{
		if (isDebug)
			Log.d(TAG, msg);
	}

	public static void e(String msg)
	{
		if (isDebug)
			Log.e(TAG, msg);
	}

	public static void v(String msg)
	{
		if (isDebug)
			Log.v(TAG, msg);
	}

	// 下面是传入自定义tag的函数
	public static void i(String tag, String msg)
	{
		if (isDebug)
			Log.i(tag, msg);
	}

	public static void d(String tag, String msg)
	{
		if (isDebug)
			Log.d(tag, msg);
	}

	public static void e(String tag, String msg)
	{
		if (isDebug)
			Log.e(tag, msg);
	}

	public static void v(String tag, String msg)
	{
		if (isDebug)
			Log.v(tag, msg);
	}
}