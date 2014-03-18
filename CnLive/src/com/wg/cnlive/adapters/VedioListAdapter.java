package com.wg.cnlive.adapters;

import java.util.List;

import com.androidquery.AQuery;
import com.wg.cnlive.R;
import com.wg.cnlive.model.VedioModel;
import com.wg.cnlive.model.LiveModel.LiveNodes;
import com.wg.cnlive.model.VedioModel.VedioContents;
import com.wg.cnlive.tools.ListTool;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class VedioListAdapter extends BaseAdapter {
	public int index ;
	private Context context ;
	private LayoutInflater mInflater ;
	public VedioModel[] vedioModelArray ;
	
	public VedioListAdapter(Context context) {
		this.context = context ;
		mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	
	private List<VedioContents> getContents() {
		if (vedioModelArray != null && vedioModelArray[index] != null
				&&vedioModelArray[index].urls != null 
				&&vedioModelArray[index].urls.get(vedioModelArray[index].index) != null
				&&vedioModelArray[index].urls.get(vedioModelArray[index].index).contents != null) {
			return vedioModelArray[index].urls.get(vedioModelArray[index].index).contents ;
		}
		return null ;
	}
	
	@Override
	public int getCount() {
		if (getContents() != null) {
			return getContents().size() ;
		}
		return 0;
	}

	@Override
	public VedioContents getItem(int position) {
		if (getContents() != null) {
			return getContents().get(position) ;
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
			view = mInflater.inflate(R.layout.list_vedio_item, null) ;
		}
		AQuery aQuery = new AQuery(view) ;
		VedioContents vedioContents = getItem(position) ;
		aQuery.id(R.id.ratingbar).rating(vedioContents.score) ;
		aQuery.id(R.id.title).text(vedioContents.name) ;
		aQuery.id(R.id.imageview).image(vedioContents.image, true, true, 0, 0, null, AQuery.FADE_IN_NETWORK, 1.0f) ;
		return view;
	}
}
