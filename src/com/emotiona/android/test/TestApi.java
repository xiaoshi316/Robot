package com.emotiona.android.test;

import com.emotiona.android.http.ICallBack;
import com.emotiona.android.http.NetHelper;

import android.content.ComponentName;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ServiceInfo;
import android.test.AndroidTestCase;
import android.util.Log;

public class TestApi extends AndroidTestCase {
	private String result;

	public TestApi() {
		// TODO Auto-generated constructor stub
		// Log.e("Tag", getJson("http://192.168.1.129:8181/TempPro/getJson"));
		getMetaData();
	}

	public String getJson(String url) {

		new NetHelper().getResult(url, new ICallBack() {

			@Override
			public void onSuccess(String response) {
				// TODO Auto-generated method stub
				result = response;
			}

			@Override
			public void onFailed(String error) {
				// TODO Auto-generated method stub
				result = error;
			}
		}, false, null, getContext());

		return result;
	}

	public void getMetaData() {
		// 在Activity应用<meta-data>元素。
		// ActivityInfo info =
		// getContext().getPackageManager().getActivityInfo(getComponentName(),PackageManager.GET_META_DATA);
		// info.metaData.getString("meta_name");

		// 在application应用<meta-data>元素。
		ApplicationInfo appInfo;
		try {
			appInfo = getContext().getPackageManager().getApplicationInfo(getContext().getPackageName(),
					PackageManager.GET_META_DATA);
			String meta_name = appInfo.metaData.getString("meta_name");
			Log.e("TAG", meta_name);
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// 在service应用<meta-data>元素。
		// ComponentName cn = new ComponentName(getContext(),
		// MetaDataService.class);
		// ServiceInfo info =
		// getContext().getPackageManager().getServiceInfo(cn,
		// PackageManager.GET_META_DATA);
		// info.metaData.getString("meta_name");

		// 在receiver应用<meta-data>元素。
		// ComponentName cn = new ComponentName(getContext(),
		// MetaDataReceiver.class);
		// ActivityInfo info =
		// getContext().getPackageManager().getReceiverInfo(cn,
		// PackageManager.GET_META_DATA);
		// info.metaData.getString("meta_name");

	}
}
