package com.xiaoshi.chattingrobot.fastjson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

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


	public String getContentByKey(String key) {
		if (objects.containsKey(key)) {
			String result = String.valueOf(objects.get(key));
			return result;
		} else {
			return "没有" + key;
		}
	}

	@Override
	public void put(String key, String value) {
		objects.put(key, value);
	}

	@Override
	public String getCode() {
		// TODO Auto-generated method stub
		if (objects.containsKey("code")) {
			String result = String.valueOf(objects.get("code"));
			return result;
		} else {
			return "code is null";
		}
	}

	@Override
	public String getText() {
		if (objects.containsKey("text")) {
			String result = String.valueOf(objects.get("text"));
			return result;
		} else {
			return "text is null";
		}
	}

	@Override
	public String getUrl() {
		if (objects.containsKey("url")) {
			String result = String.valueOf(objects.get("url"));
			return result;
		} else {
			return "url is null";
		}
	}

	@Override
	public String getList() {
		if (objects.containsKey("list")) {
			String result = String.valueOf(objects.get("list"));
			return result;
		} else {
			return "list is null";
		}
	}



}
