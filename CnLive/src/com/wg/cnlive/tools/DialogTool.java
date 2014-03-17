package com.wg.cnlive.tools;

import android.app.Dialog;
import android.content.Context;
import android.view.View;

import com.wg.cnlive.R;

public class DialogTool {
	public static Dialog waitDialog = null;
	public static void showWaitDialog(Context context) {
		if (DialogTool.waitDialog == null) {
			DialogTool.waitDialog = new Dialog(context, R.style.dialog);
            View view = View.inflate(context, R.layout.wait_layout, null);
            DialogTool.waitDialog.setContentView(view);
            DialogTool.waitDialog.setCancelable(false);
            DialogTool.waitDialog.setCanceledOnTouchOutside(true) ;
        }
		DialogTool.waitDialog.show() ;
	}
	
	public static void hideWaitDialog() {
		if (DialogTool.waitDialog != null) {
			DialogTool.waitDialog.cancel();
        }
	}
}
