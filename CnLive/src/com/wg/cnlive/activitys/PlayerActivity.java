package com.wg.cnlive.activitys;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxStatus;
import com.google.gson.Gson;
import com.wg.cnlive.R;
import com.wg.cnlive.constant.MyUrlConstant;
import com.wg.cnlive.model.PlayDateModel;
import com.wg.cnlive.model.VedioItemModel;
import com.wg.cnlive.tools.HttpTool;
import com.wg.cnlive.tools.LogTool;
import com.wg.cnlive.tools.MediaTool;
import com.wg.mylib.util.LogUtil;
import com.wg.mylib.util.StringUtil;
import com.wg.mylib.util.TipsUtil;

import io.vov.vitamio.LibsChecker;
import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.MediaPlayer.OnBufferingUpdateListener;
import io.vov.vitamio.MediaPlayer.OnInfoListener;
import io.vov.vitamio.widget.MediaController;
import io.vov.vitamio.widget.VideoView;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class PlayerActivity extends BaseActivity implements OnInfoListener, OnBufferingUpdateListener {

	private String path = "";
	private Uri uri;
	private VideoView mVideoView;
	private boolean isStart;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	@Override
	protected void initViews() {
		if (!LibsChecker.checkVitamioLibs(this))
			return;
		setContentView(R.layout.activity_player);
		aQuery = new AQuery(this) ;
		mVideoView = (VideoView) findViewById(R.id.buffer);
		
	}
	
	@Override
	protected void getDataFromInternet() {
		Intent intent = getIntent() ;
		String reqPlayUrl = intent.getStringExtra("reqPlayUrl") ;
		
		if (StringUtil.isEmpty(reqPlayUrl)) {
			TipsUtil.show(this, "未发现视频地址，请重试！") ;
			return;
		} else {
			showWaitDialog() ;
			HttpTool.ajax(aQuery, this, HttpTool.getParamsFormUrl(reqPlayUrl), MyUrlConstant.PLAY_URL, String.class, "playCallBack") ;
		}
	}
	
	public void playCallBack(String url, String content, AjaxStatus status){
		LogTool.info(content) ;
		hideWaitDialog() ;
		Gson gson = new Gson() ;
		PlayDateModel playDataModel = gson.fromJson(content, PlayDateModel.class) ;
		path = playDataModel.url ;
//		path = "http://devimages.apple.com/iphone/samples/bipbop/gear1/prog_index.m3u8" ;
		MediaTool.play(this, path) ;
//		initPlayer() ;
	}
	
	private void initPlayer() {
		uri = Uri.parse(path);
		mVideoView.setVideoURI(uri);
		mVideoView.setMediaController(new MediaController(this));
		mVideoView.requestFocus();
		mVideoView.setOnInfoListener(this);
		mVideoView.setOnBufferingUpdateListener(this);
		mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
			@Override
			public void onPrepared(MediaPlayer mediaPlayer) {
				mediaPlayer.setPlaybackSpeed(1.0f);
			}
		});
	}

	@Override
	public boolean onInfo(MediaPlayer mp, int what, int extra) {
		switch (what) {
		case MediaPlayer.MEDIA_INFO_BUFFERING_START:
			LogUtil.info("MEDIA_INFO_BUFFERING_START") ;
			if (mVideoView.isPlaying()) {
				mVideoView.pause();
				isStart = true;
			}
			break;
		case MediaPlayer.MEDIA_INFO_BUFFERING_END:
			LogUtil.info("MEDIA_INFO_BUFFERING_END") ;
			if (isStart) {
				mVideoView.start();
			}
			break;
		case MediaPlayer.MEDIA_INFO_DOWNLOAD_RATE_CHANGED:
			LogUtil.info("MEDIA_INFO_DOWNLOAD_RATE_CHANGED") ;
			break;
		}
		return true;
	}

	@Override
	public void onBufferingUpdate(MediaPlayer mp, int percent) {
		LogUtil.info("percent=="+percent) ;
	}
	
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		LogTool.info("横竖屏切换") ;
	}

}
