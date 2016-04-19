package com.swarmcn.user.util.json;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

public class JSONUtil {
	
//	public static String getJSONFromObject(Object o) {
//		StringBuffer sb = new StringBuffer();
//		sb.append("{");
//		if (o != null) {
//			sb.append("\"success\":true");
//			sb.append(",\"data\":");
//			sb.append(JSONObject.fromObject(o, cfg()).toString());
//		}
//		sb.append("}");
//		return sb.toString();
//	}
	
	public static String getJSONFromObject(Object o) {
		String data = JSONObject.fromObject(o, cfg()).toString();
		return data;
	}
	
	public static String getJSONFromArray(Object o){
		String data = JSONArray.fromObject(o, cfg()).toString();
		return data;
	}
	
	/**
	 * 将列表，记录总数转换成JSON数据
	 * 
	 * @param total
	 *            记录总数
	 * @param list
	 *            列表
	 * @return
	 */
	public static String getJSONArrayFromList(long total, Object o) {
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		if (o != null) {
			sb.append("\"total\":");
			sb.append(total);
			sb.append(",\"rows\":");
			sb.append(JSONArray.fromObject(o, cfg()).toString());
		}
		sb.append("}");
		return sb.toString();
	}
	
//	public static String getJSONArrayFromObject(Object o) {
//		try {
//			JSONArray treeJson;
//			treeJson = JSONArray.fromObject(o);
//			return treeJson.toString();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return "[]";
//	}
	
	public static String getSuccessJson(String text){
		return "{\"success\":true,\"msg\":\""+text+"\"}";
	}
	
	public static String getFailureJson(String text){
		return "{\"success\":false,\"msg\":\""+text+"\"}";
	}
	
	// 重新设置时间格式
	public static JsonConfig cfg() {
		JsonConfig cfg = new JsonConfig();
		cfg.registerJsonValueProcessor(java.util.Date.class,
				new JsonValueProcessorImpl("yyyy-MM-dd HH:mm:ss"));
		cfg.registerJsonValueProcessor(java.sql.Date.class,
				new JsonValueProcessorImpl("yyyy-MM-dd HH:mm:ss"));
		return cfg;
	}
}
