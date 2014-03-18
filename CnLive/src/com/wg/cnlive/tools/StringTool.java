package com.wg.cnlive.tools;

import com.wg.mylib.util.LogUtil;
import com.wg.mylib.util.StringUtil;

public class StringTool {
	public static int getIntFromUrl(String url,String reg) {
		if (!StringUtil.isEmpty(url)) {
			int index = url.lastIndexOf(reg) ;
			String type = url.substring(index+reg.length(), index+reg.length()+1) ;
			LogUtil.info("type="+type) ;
			return Integer.parseInt(type) ;
		}
		return 0 ;
	}
}
