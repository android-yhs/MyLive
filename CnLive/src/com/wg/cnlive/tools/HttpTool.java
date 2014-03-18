package com.wg.cnlive.tools;

import java.util.HashMap;
import java.util.Map;

import com.androidquery.AQuery;
import com.wg.cnlive.constant.MyUrlConstant;
import com.wg.mylib.util.StringUtil;

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
	
	public static Map<String, Object> getParamsFormUrl(String url) {
		Map<String, Object> params = new HashMap<String, Object>();
		if (!StringUtil.isEmpty(url)) {
			String[] urls = url.split("\\?") ;
			String[] param = urls[1].split("&") ;
			if (param != null) {
				int size = param.length ;
				for (int i=0;i<size;++i) {
					String[] tempParam = param[i].split("=") ;
					params.put(tempParam[0], tempParam[1]) ;
				}
			}
		}
		return params ;
	}
}
