package com.xiaoshi.chattingrobot.http;

import java.util.Map;

import android.os.AsyncTask;
public class NetHelper implements INetHelper{

	@Override
	public void getResult(final String url, final ICallBack callback) {
		// TODO Auto-generated method stub
		new AsyncTask<Void, Void, String>() {
			@Override
			protected String doInBackground(Void... params) {
				String response="";
				try {
					response = HttpConnectUtils.getClient(url);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return response;
			}
			@Override
			protected void onPostExecute(String response) {
				if(response!=null){
					callback.onSuccess(response);
				}else{
					callback.onFailed("链接服务器失败");
				}
			}
		}.execute();
	}

	@Override
	public void postResult(final String url,final Map<String,String> paramMap, final ICallBack callback) {
		// TODO Auto-generated method stub
				new AsyncTask<Void, Void, String>() {
					
					@Override
					protected String doInBackground(Void... params) {
						String response="";
						try {
							response = HttpConnectUtils.postClient(url, paramMap);
						} catch (Exception e) {
							e.printStackTrace();
						}
						return response;
					}
					@Override
					protected void onPostExecute(String response) {
						if(response!=null){
							callback.onSuccess(response);
						}else{
							callback.onFailed("链接服务器失败");
						}
					}
				}.execute();
	}
}
