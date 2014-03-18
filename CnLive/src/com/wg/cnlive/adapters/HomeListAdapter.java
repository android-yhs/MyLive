package com.wg.cnlive.adapters;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.BaseAdapter;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;
import com.google.gson.Gson;
import com.wg.cnlive.R;
import com.wg.cnlive.constant.MyUrlConstant;
import com.wg.cnlive.model.PlayDateModel;
import com.wg.cnlive.model.HomeModel.NodeContentModel;
import com.wg.cnlive.model.HomeModel.NodeModel;
import com.wg.cnlive.tools.DialogTool;
import com.wg.cnlive.tools.HttpTool;
import com.wg.cnlive.tools.ListTool;
import com.wg.cnlive.tools.LogTool;
import com.wg.cnlive.tools.MediaTool;

public class HomeListAdapter extends BaseAdapter {

	public List<NodeModel> nodes;
	private Context context;
	private LayoutInflater mInflater;

	public HomeListAdapter(Context context) {
		this.context = context;
		mInflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		if (!ListTool.isEmpty(nodes)) {
			return nodes.size();
		}
		return 0;
	}

	@Override
	public NodeModel getItem(int position) {
		if (!ListTool.isEmpty(nodes)) {
			return nodes.get(position);
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
			view = mInflater.inflate(R.layout.listview_home_item, null);
		}
		final AQuery aQuery = new AQuery(view);
		final NodeModel nodeModel = getItem(position);
		aQuery.id(R.id.subTitle).text(nodeModel.name);
		HomeHorizontalListAdapter adapter = new HomeHorizontalListAdapter(
				context);
		adapter.contents = nodeModel.contents;
		aQuery.id(R.id.horizontalList).adapter(adapter)
				.itemClicked(new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> adapter, View view,
							int position, long id) {
						NodeContentModel contentModel = nodeModel.contents
								.get(position);
						MediaTool.playVideo(context, contentModel.reqPlayUrl) ;
					}
				});
		return view;
	}
}
