package com.wg.cnlive.adapters;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.androidquery.AQuery;
import com.wg.cnlive.R;
import com.wg.cnlive.model.HomeModel.NodeContentModel;
import com.wg.cnlive.model.HomeModel.NodeModel;
import com.wg.cnlive.tools.ListTool;

public class HomeHorizontalListAdapter extends BaseAdapter {

	public List<NodeContentModel> contents ;
	private Context context ;
	private LayoutInflater mInflater ;
	
	public HomeHorizontalListAdapter(Context context) {
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
	public NodeContentModel getItem(int position) {
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
			view = mInflater.inflate(R.layout.horizontal_list_home_item, null) ;
		}
		AQuery aQuery = new AQuery(view) ;
		NodeContentModel nodeContentModel = getItem(position) ;
		aQuery.id(R.id.imageview).image(nodeContentModel.image, true, true, 0, 0, null, AQuery.FADE_IN_NETWORK, 1.0f) ;
		aQuery.id(R.id.title).text(nodeContentModel.name) ;
		return view;
	}
}
