package com.emotiona.android.fastjson;

import com.alibaba.fastjson.JSONObject;

public class JsonResultUtils {
	
	/**
	 * 生成helper
	 * @param json
	 * @return
	 */
	public final static JsonResultHelper helper(String json){
		if(null == json){
			new JsonResultHelper("");
		}
		return new JsonResultHelper(json);
	}
	
	public final static JsonResultHelper helper(JSONObject json){
		if(null == json){
			new JsonResultHelper("");
		}
		return new JsonResultHelper(json);
	}
	
}
