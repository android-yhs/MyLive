package com.wg.cnlive.adapters;

import java.util.List;

import com.androidquery.AQuery;
import com.wg.cnlive.R;
import com.wg.cnlive.model.HomeModel.RecommendModel;
import com.wg.cnlive.tools.ListTool;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class HomeGalleryAdapter extends BaseAdapter {

	public List<RecommendModel> recommendList ;
	private Context context ;
	private LayoutInflater mInflater ;
	
	public HomeGalleryAdapter(Context context) {
		this.context = context ;
		mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	
	@Override
	public int getCount() {
		if (!ListTool.isEmpty(recommendList)) {
//			return recommendList.size() ;
			return Integer.MAX_VALUE ;
		}
		return 0;
	}

	@Override
	public RecommendModel getItem(int position) {
		if (!ListTool.isEmpty(recommendList)) {
			return recommendList.get(position) ;
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
			view = mInflater.inflate(R.layout.gallery_home_item, null);
		}
		AQuery aQuery = new AQuery(view) ;
		aQuery.id(R.id.imageview).image(getItem(position % recommendList.size()).programPic, true, true, 0, 0, null, AQuery.FADE_IN_NETWORK, 1.0f) ;
		return view;
	}

}
