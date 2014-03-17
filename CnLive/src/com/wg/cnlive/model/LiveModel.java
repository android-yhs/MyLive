package com.wg.cnlive.model;

import java.util.List;

public class LiveModel {

	public String nowTime ;
	public String livingsUrl ;
	public String isFirstPage ;
	public String isLastPage ;
	public String preUrl ;
	public String nextUrl ;
	public List<LiveNodes> nodes ;
	
	public class LiveNodes {
		public String nodeId ;
		public String name ;
		public String rating ;
		public String reqPlayUrl ;
		public String image ;
		public String livingContId ;
		public String livingName ;
		public List<LiveContents> contents ;
	}
	
	public class LiveContents {
		public String contId ;
		public String name ;
		public String monthDay ;
		public String time ;
		public String timeDiffSec ;
		public String reqPlayUrl ;
		public String livingContId ;
	}
}
