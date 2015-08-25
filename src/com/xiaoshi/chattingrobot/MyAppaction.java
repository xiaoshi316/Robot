package com.xiaoshi.chattingrobot;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.app.Application;
import android.content.Context;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.xiaoshi.chattingrobot.cache.ACache;
import com.xiaoshi.chattingrobot.utils.MyPreferenceManager;

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
	/** 是否为顶级视图 */
	public boolean mIsTop = false;
	public static Context mContext;
	public static List<Map<String, String>> lists_news;
	public static MyAppaction myApplication;
	/**是否是仿真环境*/
	public static boolean SIMULATION =false;
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		myApplication = this;
		mContext = getApplicationContext();
		cache = ACache.get(getApplicationContext());
		sharePreferences = MyPreferenceManager.getInstance(getApplicationContext());
		lists_news = new ArrayList<Map<String, String>>();
		ImageLoaderConfiguration configuration = new ImageLoaderConfiguration.Builder(getApplicationContext()).threadPoolSize(3)// 线程池内加载的数量
				.discCacheFileNameGenerator(new Md5FileNameGenerator()) // 将保存的时候的URI名称用MD5
				.discCacheFileCount(100).build();
		
		// Initialize ImageLoader with configuration.
		ImageLoader.getInstance().init(configuration);
		// 处理异常
		// CrashHandler crashHandler = CrashHandler.getInstance();
		// crashHandler.init(this);
	}

	public static MyAppaction getMyApplication() {
		return myApplication;
	}
}
