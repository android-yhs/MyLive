package com.wg.cnlive.adapters;

import java.util.List;

import com.androidquery.AQuery;
import com.wg.cnlive.R;
import com.wg.cnlive.model.HomeModel.NodeModel;
import com.wg.cnlive.model.LiveModel.LiveNodes;
import com.wg.cnlive.tools.ListTool;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

public class LiveListAdapter extends BaseAdapter {
	public List<LiveNodes> nodes ;
	private Context context ;
	private LayoutInflater mInflater ;
	
	public LiveListAdapter(Context context) {
		this.context = context ;
		mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	
	@Override
	public int getCount() {
		if (!ListTool.isEmpty(nodes)) {
			return nodes.size() ;
		}
		return 0;
	}

	@Override
	public LiveNodes getItem(int position) {
		if (!ListTool.isEmpty(nodes)) {
			return nodes.get(position) ;
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
			view = mInflater.inflate(R.layout.listview_live_item, null) ;
		}
		AQuery aQuery = new AQuery(view) ;
		LiveNodes nodeModel = getItem(position) ;
		LiveHorizontalListAdapter adapter = new LiveHorizontalListAdapter(context) ;
		adapter.contents = nodeModel.contents ;
		aQuery.id(R.id.horizontalList).adapter(adapter) ;
		aQuery.id(R.id.content).text(nodeModel.rating) ;
		aQuery.id(R.id.title).text(nodeModel.name) ;
		aQuery.id(R.id.imageview).image(nodeModel.image, true, true, 0, 0, null, AQuery.FADE_IN_NETWORK, 1.0f) ;
		return view;
	}
}
