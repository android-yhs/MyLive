package com.wg.cnlive.activitys;

import java.util.Date;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxStatus;
import com.google.gson.Gson;
import com.wg.cnlive.R;
import com.wg.cnlive.adapters.VedioListAdapter;
import com.wg.cnlive.constant.MyUrlConstant;
import com.wg.cnlive.model.VedioItemModel;
import com.wg.cnlive.model.VedioModel;
import com.wg.cnlive.model.VedioModel.VedioContents;
import com.wg.cnlive.tools.HttpTool;
import com.wg.cnlive.tools.LogTool;
import com.wg.cnlive.tools.MediaTool;
import com.wg.mylib.views.lisview.XListView;
import com.wg.mylib.views.lisview.XListView.IXListViewListener;

public class VedioListActivity extends BaseActivity implements IXListViewListener {

	private String[] vedioArray ;
	private String[] vedioUrlArray ;
	private VedioModel[] vedioModelArray ;
	private String url ;
	private XListView listview ;
	private int typeBig = 0 ;
	private VedioListAdapter listAdapter ;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	@Override
	protected void initViews() {
		super.setContentView(R.layout.activity_vedio_list) ;
		aQuery = new AQuery(this) ;
		aQuery.id(R.id.left_top_btn).background(R.drawable.logo).text("").visibility(AQuery.INVISIBLE) ;
		listview = (XListView) aQuery.id(R.id.listview).getListView() ;
		listview.setPullRefreshEnable(true);
		listview.setPullLoadEnable(false);
		listview.setXListViewListener(this);
		listview.setRefreshTime(new Date().toLocaleString());
		listview.setOnItemClickListener(itemListener) ;
		
		listAdapter = new VedioListAdapter(this) ;
		listview.setAdapter(listAdapter) ;
		vedioArray = getResources().getStringArray(R.array.vedioarray) ;
		vedioUrlArray = getResources().getStringArray(R.array.vediourlarray) ;
		vedioModelArray = new VedioModel[vedioArray.length] ;
		aQuery.id(R.id.top_title).text(vedioArray[typeBig]) ;
		url = vedioUrlArray[typeBig] ;
	}

	@Override
	protected void getDataFromInternet() {
		showWaitDialog() ;
		HttpTool.ajax(aQuery, this, HttpTool.getParamsFormUrl(url), MyUrlConstant.VEDIO_URL, String.class, "vedioCallBack") ;
	}
	
	public void vedioCallBack(String url, String content, AjaxStatus status){
		LogTool.info(content) ;
		onStopLoad() ;
		hideWaitDialog() ;
		aQuery.id(R.id.listview).visibility(View.VISIBLE) ;
		Gson gson = new Gson() ;
		if (vedioModelArray[typeBig] == null) {
			vedioModelArray[typeBig] = new VedioModel() ;
		}
		vedioModelArray[typeBig].addModel(gson.fromJson(content, VedioModel.class)) ;
		listAdapter.vedioModelArray = this.vedioModelArray ;
		listAdapter.index = typeBig ;
		listAdapter.notifyDataSetChanged() ;
	}

	@Override
	public void onLoadMore() {
		
	}

	@Override
	public void onRefresh() {
		url = vedioUrlArray[typeBig] ;
		vedioModelArray[typeBig].vedioContentsClear() ;
		getDataFromInternet() ;
	}
	
	private void onStopLoad() {
		listview.stopRefresh();
        listview.stopLoadMore();
        listview.setRefreshTime(new Date().toLocaleString());
    }
	
	private OnItemClickListener itemListener = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> list, View view, int position,
				long id) {
			showWaitDialog() ;
			VedioContents vedioContents = listAdapter.getItem(position) ;
			HttpTool.ajax(aQuery, VedioListActivity.this, HttpTool.getParamsFormUrl(vedioContents.contUrl), MyUrlConstant.VEDIO_CONTENT_URL, String.class, "vedioItemCallBack") ;
		}
	};
	
	public void vedioItemCallBack(String url, String content, AjaxStatus status){
		LogTool.info(content) ;
		hideWaitDialog() ;
		Gson gson = new Gson() ;
		VedioItemModel vedioItemModel = gson.fromJson(content, VedioItemModel.class) ;
		MediaTool.playVideo(this, vedioItemModel.reqPlayUrl) ;
	}

}
