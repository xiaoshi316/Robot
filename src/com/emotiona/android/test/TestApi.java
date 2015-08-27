package com.emotiona.android.test;

import com.emotiona.android.http.ICallBack;
import com.emotiona.android.http.NetHelper;

import android.test.AndroidTestCase;
import android.util.Log;

public class TestApi extends AndroidTestCase {
	private String result;
	public TestApi() {
		// TODO Auto-generated constructor stub
		Log.e("Tag", getJson("http://192.168.1.129:8181/TempPro/getJson"));
	}
	
	public String getJson(String url){
		 
		new NetHelper().getResult(url, new ICallBack() {
			
			@Override
			public void onSuccess(String response) {
				// TODO Auto-generated method stub
				result=response;
			}
			
			@Override
			public void onFailed(String error) {
				// TODO Auto-generated method stub
				
			}
		},false,null,getContext());
		
		return result;
	}
}
