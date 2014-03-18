package com.wg.cnlive.model;

import java.util.List;

public class VedioItemModel {
	public String name ;
	public String reqPlayUrl ;
	public String duration ;
	public List<VedioItemContents> contents ;
	
	public class VedioItemContents {
		public String name ;
		public String image ;
		public String desc ;
		public String reqPlayUrl ;
	}
}
