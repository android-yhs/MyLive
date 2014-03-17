package com.wg.cnlive.activitys;

import io.vov.vitamio.LibsChecker;

import java.util.Timer;
import java.util.TimerTask;

import com.wg.cnlive.R;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

public class IndexActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	protected void initViews() {
		if (!LibsChecker.checkVitamioLibs(this))
			return;
		super.setContentView(R.layout.activity_index) ;
	}

	@Override
	protected void getDataFromInternet() {
//		Timer timer = new Timer() ;
//		TimerTask task = new TimerTask() {
//			
//			@Override
//			public void run() {
//				toNextActivity(IndexActivity.this,GroupActivity.class) ;
//				IndexActivity.this.finish() ;
//			}
//		};
//		timer.schedule(task, 100) ;
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		toNextActivity(IndexActivity.this,GroupActivity.class) ;
		IndexActivity.this.finish() ;
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		toNextActivity(IndexActivity.this,GroupActivity.class) ;
		IndexActivity.this.finish() ;
		return super.onTouchEvent(event);
	}
}
