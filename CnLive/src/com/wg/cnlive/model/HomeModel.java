package com.wg.cnlive.model;

import java.util.List;

public class HomeModel {

	public String changeImageTime ;
	public List<RecommendModel> recommendList ;
	public List<NodeModel> nodes ;
	
	public class RecommendModel {
		public String programID ;
		public String programName ;
		public String programPic ;
		public String programUrl ;
		public String programType ;
		public String nodeId ;
		public String isLive ;
		public String nodeUrl ;
		public String desc ;
	}
	
	public class NodeModel {
		public String name ;
		public String moreUrl ;
		public List<NodeContentModel> contents ;
	}
	
	public class NodeContentModel {
		public String name ;
		public String nodeName ;
		public String image ;
		public String livingImage ;
		public String livingName ;
		public String reqPlayUrl ;
	}
}
