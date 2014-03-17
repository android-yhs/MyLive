package com.wg.cnlive.activitys;

import com.wg.cnlive.R;
import com.wg.cnlive.myviews.AbstractMyActivityGroup;
import com.wg.cnlive.tools.MoveBg;
import com.wg.mylib.views.dialog.MyDialog;
import com.wg.mylib.views.dialog.MyDialog.BgDrawable;
import com.wg.mylib.views.dialog.MyDialog.DialogOnClick;

import android.os.Bundle;
import android.view.Display;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class GroupActivity extends AbstractMyActivityGroup {
	//加载的Activity的名字，LocalActivityManager就是通过这些名字来查找对应的Activity的。
	private static final String HOME = "home";
    private static final String LIVE = "live";
    private static final String CHANNEL = "channel";
    private static final String RATTING = "ratting";
    private static final String SETTING = "setting";
    private RadioGroup radioBtn ;
    private RelativeLayout bottom_layout ;
    private int startLeft ;
    private ImageView img ;
    private int imageWidth ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	super.setContentView(R.layout.activity_group) ;
    	super.onCreate(savedInstanceState) ;
    	radioBtn = (RadioGroup) findViewById(R.id.radiogroup) ;
    	bottom_layout = (RelativeLayout) findViewById(R.id.layout_bottom);
    	radioBtn.setOnCheckedChangeListener(checkedChangeListener) ;
    	img = (ImageView) findViewById(R.id.radioBg) ;
    	Display mDisplay = getWindowManager().getDefaultDisplay();
    	imageWidth = mDisplay.getWidth()/5;
    	RelativeLayout.LayoutParams rlp = new RelativeLayout.LayoutParams(imageWidth, 70) ;
    	img.setLayoutParams(rlp) ;
    	img.setImageResource(R.drawable.bottom_focus) ;
    	setContainerView(HOME, HomeActivity.class);
    }
    
	@Override
	protected ViewGroup getContainer() {
		return (ViewGroup) findViewById(R.id.container) ;
	}
	
	private OnCheckedChangeListener checkedChangeListener = new OnCheckedChangeListener() {

		public void onCheckedChanged(RadioGroup group, int checkedId) {
			switch (checkedId) {
			case R.id.radio_home:
				setContainerView(HOME, HomeActivity.class);
				MoveBg.moveFrontBg(img, startLeft, 0, 0, 0);
				startLeft = 0;
				break;
			case R.id.radio_live:
				setContainerView(LIVE, LiveActivity.class);
				MoveBg.moveFrontBg(img, startLeft, imageWidth, 0, 0);
				startLeft = imageWidth;
				break;
			case R.id.radio_channel:
				setContainerView(CHANNEL, HomeActivity.class);
				MoveBg.moveFrontBg(img, startLeft, imageWidth * 2, 0, 0);
				startLeft = imageWidth * 2;
				break;
			case R.id.radio_ratting:
				setContainerView(RATTING, HomeActivity.class);
				MoveBg.moveFrontBg(img, startLeft, imageWidth * 3, 0, 0);
				startLeft = imageWidth * 3;
				break;
			case R.id.radio_setting:
				setContainerView(SETTING, HomeActivity.class);
				MoveBg.moveFrontBg(img, startLeft, imageWidth * 4, 0, 0);
				startLeft = imageWidth * 4;
				break;

			default:
				break;
			}
		}
	};
	
}