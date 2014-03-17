package com.wg.cnlive.activitys;

import com.androidquery.AQuery;
import com.wg.cnlive.R;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public abstract class BaseActivity extends Activity {

	protected AQuery aQuery ;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initViews() ;
		getDataFromInternet() ;
	}
	
	abstract protected void initViews() ;
	
	abstract protected void getDataFromInternet() ;
	
	private Dialog waitDialog = null;
	
	protected void toNextActivity(Context context,Class cls) {
		Intent intent = new Intent() ;
		intent.setClass(context, cls) ;
		startActivity(intent) ;
	}
	
	protected void toNextActivity(Context context,Class cls,int in,int out) {
		Intent intent = new Intent() ;
		intent.setClass(context, cls) ;
		startActivity(intent) ;
		overridePendingTransition(in, out);
	}
	
	public void showWaitDialog() {
		if (waitDialog == null) {
            waitDialog = new Dialog(this, R.style.dialog);
            View view = View.inflate(this, R.layout.wait_layout, null);
            waitDialog.setContentView(view);
            waitDialog.setCancelable(false);
            waitDialog.setCanceledOnTouchOutside(true) ;
        }
		waitDialog.show() ;
	}
	
	public void hideWaitDialog() {
		if (waitDialog != null) {
            waitDialog.cancel();
        }
	}
}
