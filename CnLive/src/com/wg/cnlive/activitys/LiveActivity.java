package com.wg.cnlive.activitys;

import java.util.HashMap;
import java.util.Map;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxStatus;
import com.google.gson.Gson;
import com.wg.cnlive.R;
import com.wg.cnlive.activitys.HomeActivity.MyViewFlowItemListener;
import com.wg.cnlive.activitys.HomeActivity.ViewFlowSwitchListener;
import com.wg.cnlive.adapters.HomeGalleryAdapter;
import com.wg.cnlive.adapters.HomeListAdapter;
import com.wg.cnlive.adapters.LiveListAdapter;
import com.wg.cnlive.constant.MyUrlConstant;
import com.wg.cnlive.model.HomeModel;
import com.wg.cnlive.model.LiveModel;
import com.wg.cnlive.myviews.MyListView;
import com.wg.cnlive.tools.HttpTool;
import com.wg.cnlive.tools.LogTool;

import android.os.Bundle;
import android.view.View;

public class LiveActivity extends BaseActivity {

	private LiveModel liveModel ;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState) ;
	}

	@Override
	protected void initViews() {
		super.setContentView(R.layout.activity_live) ;
		aQuery = new AQuery(this) ;
		aQuery.id(R.id.top_title).text(R.string.live_title) ;
	}

	@Override
	protected void getDataFromInternet() {
		showWaitDialog() ;
		Map<String, Object> params = new HashMap<String, Object>();
	    params.put("pf", "livings");
		HttpTool.ajax(aQuery, this,params, MyUrlConstant.LIVE_URL, String.class, "liveCallBack") ;
	}
	
	public void liveCallBack(String url, String content, AjaxStatus status){
		LogTool.info(content) ;
		hideWaitDialog() ;
		aQuery.id(R.id.container).visibility(View.VISIBLE) ;
		Gson gson = new Gson() ;
		liveModel = gson.fromJson(content, LiveModel.class) ;
		aQuery.id(R.id.time_text).text(liveModel.nowTime) ;
		initListView() ;
	}
	
	private void initListView() {
		LiveListAdapter adapter = new LiveListAdapter(this) ;
		adapter.nodes = liveModel.nodes ;
		aQuery.id(R.id.listview).adapter(adapter) ;
	}
}
