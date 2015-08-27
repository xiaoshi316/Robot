package com.emotiona.android;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.app.Application;
import android.content.Context;

import com.emotiona.android.cache.ACache;
import com.emotiona.android.utils.MyPreferenceManager;

public class MyAppaction extends Application {
	public static boolean isLogin = true;
	public static MyPreferenceManager sharePreferences;
	public static ACache cache;
	public static int GENERAL = 0;
	public static int FAST = 1;
	public static int SUBSIC = 2;
	/** 是否使用自动堆栈管理 */
	public static boolean isUseActivityManager = true;
	/** 首页按返回键的次数 */
	public static int BackKeyCount = 0;
	public static Context mContext;
	public static MyAppaction myApplication;
	/**是否是仿真环境*/
	public static boolean SIMULATION =false;
	public static boolean ISLOG=true;
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		myApplication = this;
		mContext = getApplicationContext();
		cache = ACache.get(getApplicationContext());
		sharePreferences = MyPreferenceManager.getInstance(getApplicationContext());
		// 处理异常
		// CrashHandler crashHandler = CrashHandler.getInstance();
		// crashHandler.init(this);
	}

	public static MyAppaction getMyApplication() {
		return myApplication;
	}
}
