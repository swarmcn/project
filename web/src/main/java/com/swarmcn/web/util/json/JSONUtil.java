package com.swarmcn.web.util.json;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

public class JSONUtil {
	
	public static String getJSONFromObject(Object o) {
		String data = JSONObject.fromObject(o, cfg()).toString();
		return data;
	}
	
	public static String getJSONFromArray(Object o){
		String data = JSONArray.fromObject(o, cfg()).toString();
		return data;
	}

	public static JsonConfig cfg() {
		JsonConfig cfg = new JsonConfig();
		cfg.registerJsonValueProcessor(java.util.Date.class,
				new JsonValueProcessorImpl("yyyy-MM-dd HH:mm:ss"));
		cfg.registerJsonValueProcessor(java.sql.Date.class,
				new JsonValueProcessorImpl("yyyy-MM-dd HH:mm:ss"));
		return cfg;
	}
}
