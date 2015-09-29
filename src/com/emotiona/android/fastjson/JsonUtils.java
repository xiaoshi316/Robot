package com.emotiona.android.fastjson;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

/***
 * 
 * ClassName: JsonUtils 
 * Function: TODO ADD FUNCTION
 * Reason: TODO ADD REASON(可选)
 * date: 2015年9月22日 下午5:49:09
 * @author emotiona
 * @version 
 * @since JDK 1.7
 */
public class JsonUtils {
	/***
	 * 
	 * getObject
	 * TODO(解析json)
	 * @author emotiona<br/>
	 * @Email emtiona_xiaoshi@126.com 	
	 * @param jsonString
	 * @param cls
	 * @return T
	 * @since JDK 1.7
	 */
	public static <T> T getObject(String jsonString, Class<T> cls) {
		return JSON.parseObject(jsonString, cls);
	}
	/***
	 * 
	 * getObjects
	 * TODO(解析json)
	 * @author emotiona<br/>
	 * @Email emtiona_xiaoshi@126.com 	
	 * @param jsonString
	 * @param cls
	 * @return List<T>
	 * @since JDK 1.7
	 */
	public static <T> List<T> getObjects(String jsonString, Class<T> cls) {
		return JSON.parseArray(jsonString, cls);
	}

	/***
	 * 
	 * getKeyMapsList
	 * TODO(function)
	 * @author emotiona<br/>
	 * @Email emtiona_xiaoshi@126.com 	
	 * @param jsonString
	 * @return List<Map<object,object>>
	 * @since JDK 1.7
	 */
	public static List<Map<String, String>> getKeyMapsList(String jsonString) {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		list = JSON.parseObject(jsonString, new TypeReference<List<Map<String, String>>>() {
		});
		return list;
	}
}
