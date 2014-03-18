package com.wg.cnlive.activitys;


import java.util.Date;

import io.vov.vitamio.LibsChecker;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxStatus;
import com.google.gson.Gson;
import com.wg.cnlive.R;
import com.wg.cnlive.adapters.HomeGalleryAdapter;
import com.wg.cnlive.adapters.HomeListAdapter;
import com.wg.cnlive.constant.MyUrlConstant;
import com.wg.cnlive.model.HomeModel;
import com.wg.cnlive.model.HomeModel.RecommendModel;
import com.wg.cnlive.myviews.CircleFlowIndicator;
import com.wg.cnlive.myviews.ViewFlow;
import com.wg.cnlive.myviews.ViewFlow.ViewSwitchListener;
import com.wg.cnlive.tools.HttpTool;
import com.wg.cnlive.tools.LogTool;
import com.wg.mylib.views.dialog.MyDialog;
import com.wg.mylib.views.dialog.MyDialog.BgDrawable;
import com.wg.mylib.views.dialog.MyDialog.DialogOnClick;
import com.wg.mylib.views.lisview.XListView;
import com.wg.mylib.views.lisview.XListView.IXListViewListener;

public class HomeActivity extends BaseActivity implements IXListViewListener,DialogOnClick {

	private HomeModel homeModel ;
	private ViewFlow mainViewFlow;
	private CircleFlowIndicator mainFlowIndicator;
	private View headView ;
	private XListView listview ;
	private boolean isFirst = true ;
	private HomeListAdapter adapter ;
	private BgDrawable bgDrawable ;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	protected void initViews() {
		if (!LibsChecker.checkVitamioLibs(this))
			return;
		super.setContentView(R.layout.activity_home) ;
		aQuery = new AQuery(this) ;
		aQuery.id(R.id.left_top_btn).background(R.drawable.logo).text("") ;
		aQuery.id(R.id.top_title).visibility(AQuery.INVISIBLE) ;
		listview = (XListView) aQuery.id(R.id.listview).getListView() ;
		listview.setPullRefreshEnable(true);
		listview.setPullLoadEnable(false);
		listview.setXListViewListener(this);
		listview.setRefreshTime(new Date().toLocaleString());
		headView = getLayoutInflater().inflate(R.layout.home_viewflow, null) ;
		mainViewFlow = (ViewFlow) headView.findViewById(R.id.mainViewFlow) ;
		mainFlowIndicator = (CircleFlowIndicator) headView.findViewById(R.id.mainFlowIndic) ;
		adapter = new HomeListAdapter(this) ;
		listview.setAdapter(adapter) ;
		initBgDrawable() ;
	}
	
	private void initBgDrawable() {
    	bgDrawable = new BgDrawable() ;
		bgDrawable.bg = R.drawable.bg_dialog ;
		bgDrawable.botton_bg = R.drawable.bg_dialog_bottom ;
		bgDrawable.botton_bg_f = R.drawable.bg_dialog_bottom_f ;
		bgDrawable.left_bg = R.drawable.bg_dialog_left ;
		bgDrawable.left_bg_f = R.drawable.bg_dialog_left_f ;
		bgDrawable.right_bg = R.drawable.bg_dialog_right ;
		bgDrawable.right_bg_f = R.drawable.bg_dialog_right_f ;
    }
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		MyDialog.dialog = null ;
		System.gc() ;
	}
	
	@Override
	protected void getDataFromInternet() {
		showWaitDialog() ;
		HttpTool.ajax(aQuery, this, MyUrlConstant.HOME_URL, String.class, "homeCallBack") ;
	}
	
	public void homeCallBack(String url, String content, AjaxStatus status){
		LogTool.info(content) ;
		hideWaitDialog() ;
		aQuery.id(R.id.listview).visibility(View.VISIBLE) ;
		Gson gson = new Gson() ;
		homeModel = gson.fromJson(content, HomeModel.class) ;
		HomeGalleryAdapter galleryAdapter = new HomeGalleryAdapter(this) ;
		galleryAdapter.recommendList = homeModel.recommendList ;
		mainViewFlow.setFlowIndicator(mainFlowIndicator);
		mainViewFlow.setmSideBuffer(3);
		mainViewFlow.setOnViewSwitchListener(new ViewFlowSwitchListener());
		mainViewFlow.stopAutoFlowTimer();
		mainViewFlow.setAdapter(galleryAdapter);
		mainViewFlow.setmSideBuffer(homeModel.recommendList.size());
		mainViewFlow.setSelection(homeModel.recommendList.size()*1000);
		mainViewFlow.startAutoFlowTimer();
		mainViewFlow.setOnItemClickListener(new MyViewFlowItemListener()) ;
		
		initListView() ;
	}
	
	private void initListView() {
		onStopLoad() ;
		if (isFirst) {
			isFirst = false ;
			listview.addHeaderView(headView) ;
		}
		adapter.nodes = homeModel.nodes ;
		adapter.notifyDataSetChanged() ;
	}

	/**
	 * 
	 * @author tsh
	 * viewflow 切换事件
	 */
	class ViewFlowSwitchListener implements ViewSwitchListener {
		
		@Override
		public void onSwitched(View view, int position) {
			if (homeModel.recommendList!=null&&homeModel.recommendList.size()>0) {
				int index = position % homeModel.recommendList.size();
				RecommendModel recommendModel = homeModel.recommendList.get(index);
				aQuery.id(R.id.recommendTitle).text(recommendModel.programName) ;
			}
		}
	}
	
	class MyViewFlowItemListener implements OnItemClickListener {

		@Override
		public void onItemClick(AdapterView<?> adapter, View view, int position,
				long id) {
			int index = position % homeModel.recommendList.size();
			RecommendModel recommendModel = homeModel.recommendList.get(index);
			LogTool.info(recommendModel.programName) ;
		}
		
	}

	@Override
	public void onLoadMore() {
		
	}

	@Override
	public void onRefresh() {
		getDataFromInternet() ;
	}
	
	private void onStopLoad() {
		listview.stopRefresh();
        listview.stopLoadMore();
        listview.setRefreshTime(new Date().toLocaleString());
    }

	public boolean onKeyUp(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			MyDialog.dialogshow(this,"温馨提示","您确定退出吗？",true, bgDrawable, this) ;
			return true ;
		} else {
			return super.onKeyUp(keyCode, event) ;
		}
	}
	
	@Override
	public void onClick(View view, int witch) {
		MyDialog.hideDialog() ;
		switch (witch) {
		case 1: 
			onBackPressed() ;
			break;

		default:
			break;
		}
	};
}