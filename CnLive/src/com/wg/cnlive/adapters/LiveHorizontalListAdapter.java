package com.wg.cnlive.adapters;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.androidquery.AQuery;
import com.wg.cnlive.R;
import com.wg.cnlive.model.LiveModel.LiveContents;
import com.wg.cnlive.tools.ListTool;

public class LiveHorizontalListAdapter extends BaseAdapter {
	public List<LiveContents> contents ;
	private Context context ;
	private LayoutInflater mInflater ;
	public int scale = 5 ;
	
	public LiveHorizontalListAdapter(Context context) {
		this.context = context ;
		mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	
	@Override
	public int getCount() {
		if (!ListTool.isEmpty(contents)) {
			return contents.size() ;
		}
		return 0;
	}

	@Override
	public LiveContents getItem(int position) {
		if (!ListTool.isEmpty(contents)) {
			return contents.get(position) ;
		}
		return null;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View view, ViewGroup parent) {
		if (view == null) {
			view = mInflater.inflate(R.layout.horizontal_list_live_item, null) ;
		}
		AQuery aQuery = new AQuery(view) ;
		LiveContents nodeContentModel = getItem(position) ;
		FrameLayout.LayoutParams params1 = new FrameLayout.LayoutParams(Integer.parseInt(nodeContentModel.timeDiffSec)/scale, FrameLayout.LayoutParams.MATCH_PARENT) ;
		LinearLayout.LayoutParams params2 = new LinearLayout.LayoutParams(Integer.parseInt(nodeContentModel.timeDiffSec)/scale, LinearLayout.LayoutParams.WRAP_CONTENT) ;
		params2.leftMargin = 5 ;
		view.setLayoutParams(params1) ;
		aQuery.id(R.id.date_text).text(nodeContentModel.monthDay).getTextView().setLayoutParams(params2) ;
		aQuery.id(R.id.time_text).text(nodeContentModel.time).getTextView().setLayoutParams(params2) ;
		aQuery.id(R.id.title).text(nodeContentModel.name).getTextView().setLayoutParams(params2) ;
		return view;
	}
}
