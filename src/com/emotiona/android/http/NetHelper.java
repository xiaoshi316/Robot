package com.emotiona.android.http;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import com.emotiona.android.utils.InputStreamToString;

import android.content.Context;
import android.os.AsyncTask;
/**
 * 
 * ClassName: NetHelper 
 * Function: TODO 网络请求工具类，有测试环境或者真实环境
 * Reason: TODO ADD REASON(可选)
 * date: 2015年8月27日 下午3:29:22
 * @author emotiona
 * @version 
 * @since JDK 1.7
 */
public  class NetHelper implements INetHelper {
	@Override
	public void getResult(final String url, final ICallBack callback, boolean simulation, String simulation_name, Context context) {
		// TODO Auto-generated method stub
		if (simulation) {
			oeanAssetsFile(callback, simulation_name, context);
		} else {
			getResult(url,callback);
		}
	}
	
	@Override
	public void postResult(final String url, final Map<String, String> paramMap, final ICallBack callback, boolean simulation, String simulation_name,
			Context context) {
		// TODO Auto-generated method stub
		if (simulation) {
			oeanAssetsFile(callback, simulation_name, context);
		} else {
			postResult(url, paramMap, callback);
		}
	}
	@Override
	public void getResult(final String url,final ICallBack callback) {
		// TODO Auto-generated method stub
		new AsyncTask<Void, Void, String>() {

			@Override
			protected String doInBackground(Void... params) {
				String response = "";
				try {
					response = HttpConnectUtils.getClient(url);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return response;
			}

			@Override
			protected void onPostExecute(String response) {
				if (response != null) {
					callback.onSuccess(response);
				} else {
					callback.onFailed("网络连接失败，请检查网络");
				}
			}
		}.execute();
	}
	@Override
	public void postResult(final String url, final Map<String, String> paramMap, final ICallBack callback) {
		// TODO Auto-generated method stub
		new AsyncTask<Void, Void, String>() {
			@Override
			protected String doInBackground(Void... params) {
				String response = "";
				try {
					response = HttpConnectUtils.postClient(url, paramMap);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return response;
			}

			@Override
			protected void onPostExecute(String response) {
				if (response != null) {
					callback.onSuccess(response);
				} else {
					callback.onFailed("链接服务器失败");
				}
			}
		}.execute();
	}
	private void oeanAssetsFile(final ICallBack callback, String simulation_name, Context context) {
		try {
			InputStream open = context.getAssets().open(simulation_name);
			String response = InputStreamToString.isToString(open);
			callback.onSuccess(response);
		} catch (IOException e) {
			e.printStackTrace();
			callback.onFailed(e.getMessage());
		}
	}
}
