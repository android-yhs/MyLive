package com.wg.cnlive.tools;

import java.util.HashMap;
import java.util.Map;

import com.androidquery.AQuery;
import com.wg.cnlive.constant.MyUrlConstant;

import android.content.Context;

public class HttpTool {

	public static void ajax(AQuery aQuery,Context context,String url,Class cls,String callback) {
		Map<String, Object> params = new HashMap<String, Object>();
	    params.put("WD_CLIENT_TYPE", "3");
	    aQuery.ajax(MyUrlConstant.BASE_URL + url,params,cls,context,callback) ;
	}
	
	public static void ajax(AQuery aQuery,Context context,Map<String, Object> params,String url,Class cls,String callback) {
	    params.put("WD_CLIENT_TYPE", "3");
	    aQuery.ajax(MyUrlConstant.BASE_URL + url,params,cls,context,callback) ;
	}
}
