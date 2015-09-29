package com.emotiona.android.fastjson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.emotiona.android.exception.NotKeyExpection;

public class JsonResultHelper implements Helper {

	private JSONObject objects;

	public JsonResultHelper(String jsonResult) {
		objects = JSON.parseObject(jsonResult);
	}

	public JsonResultHelper(JSONObject jsonResult) {
		if (null == jsonResult) {
			objects = JSON.parseObject("{}");
		} else {
			objects = jsonResult;
		}
	}


	public String getContentByKey(String key) throws NotKeyExpection {
		if (objects.containsKey(key)) {
			String result = String.valueOf(objects.get(key));
			return result;
		} else {
			throw new NotKeyExpection("server not"+key);
		}
	}

	@Override
	public void put(String key, String value) {
		objects.put(key, value);
	}

	@Override
	public String getCode() throws NotKeyExpection{
		// TODO Auto-generated method stub
		if (objects.containsKey("code")) {
			String result = String.valueOf(objects.get("code"));
			return result;
		} else {
			throw new NotKeyExpection("server's code is null");
		}
	}

	@Override
	public String getText() throws NotKeyExpection{
		if (objects.containsKey("text")) {
			String result = String.valueOf(objects.get("text"));
			return result;
		} else {
			throw new NotKeyExpection("server's text is null");
		}
	}

	@Override
	public String getUrl() throws NotKeyExpection{
		if (objects.containsKey("url")) {
			String result = String.valueOf(objects.get("url"));
			return result;
		} else {
			throw new NotKeyExpection("server's url is null");
		}
	}

	@Override
	public String getList() throws NotKeyExpection{
		if (objects.containsKey("list")) {
			String result = String.valueOf(objects.get("list"));
			return result;
		} else {
			throw new NotKeyExpection("server's list is null");
		}
	}
}
