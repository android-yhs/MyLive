package com.wg.cnlive.model;

import java.util.ArrayList;
import java.util.List;

import com.wg.cnlive.tools.StringTool;
import com.wg.mylib.util.LogUtil;
import com.wg.mylib.util.StringUtil;

public class VedioModel {
	
	public List<VedioStyles> urls ;
	public List<VedioContents> contents ;
	public String isLastPage ;
	public String nextUrl ;
	public int index ;
	
	public class VedioStyles {
		// 类别
		public String name ;
		public String isSelected ;
		public String url ;
		public List<VedioContents> contents ;
	}
	
	public void vedioContentsClear() {
		this.urls.get(this.index).contents.clear() ;
	}
	
	public class VedioContents {
		public String name ;
		public String image ;
		public String contentType ;
		public float score ;
		public String contUrl ;
	}
	
	public void addModel(VedioModel vedioModel) {
		if (this.urls == null) {
			this.urls = vedioModel.urls ;
		}
		this.index = StringTool.getIntFromUrl(vedioModel.nextUrl, "type=") - 1 ;
		if (this.urls.get(index).contents == null) {
			this.urls.get(index).contents = new ArrayList<VedioContents>() ;
		}
		LogUtil.info("index=="+index) ;
		this.urls.get(index).contents.addAll(vedioModel.contents) ;
		this.isLastPage = vedioModel.isLastPage ;
		this.nextUrl = vedioModel.nextUrl ; 
	}
	
}
