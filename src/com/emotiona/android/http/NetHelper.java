package com.emotiona.android.http;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import com.emotiona.android.utils.InputStreamToString;

import android.content.Context;
import android.os.AsyncTask;

public  class NetHelper implements INetHelper {
	@Override
	public void getResult(final String url, final ICallBack callback, boolean simulation, String simulation_name, Context context) {
		// TODO Auto-generated method stub
		if (simulation) {
			try {
				InputStream open = context.getAssets().open(simulation_name);
				String response = InputStreamToString.isToString(open);
				callback.onSuccess(response);
			} catch (IOException e) {
				e.printStackTrace();
				callback.onFailed(e.getMessage());
			}
		} else {
			
		}
	}
	@Override
	public void postResult(final String url, final Map<String, String> paramMap, final ICallBack callback, boolean simulation, String simulation_name,
			Context context) {
		// TODO Auto-generated method stub
		if (simulation) {
			try {
				InputStream open = context.getAssets().open(simulation_name);
				String response = InputStreamToString.isToString(open);
				callback.onSuccess(response);
			} catch (IOException e) {
				e.printStackTrace();
				callback.onFailed(e.getMessage());
			}
		} else {
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
	}
	@Override
	public void getResult(String url, ICallBack callback) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void postResult(String url, Map<String, String> paramMap, ICallBack callback) {
		// TODO Auto-generated method stub
		
	}
	
}
