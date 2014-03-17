package com.wg.cnlive.activitys;

import com.wg.cnlive.R;
import com.wg.cnlive.tools.LogTool;

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
		mVideoView = (VideoView) findViewById(R.id.buffer);
		Intent intent = getIntent() ;
		path = intent.getStringExtra("path") ;
		
		if (path == "") {
			// Tell the user to provide a media file URL/path.
			Toast.makeText(PlayerActivity.this, "Please edit VideoBuffer Activity, and set path" + " variable to your media file URL/path", Toast.LENGTH_LONG).show();
			return;
		} else {
			/*
			 * Alternatively,for streaming media you can use
			 * mVideoView.setVideoURI(Uri.parse(URLstring));
			 */
			uri = Uri.parse(path);
			mVideoView.setVideoURI(uri);
			mVideoView.setMediaController(new MediaController(this));
			mVideoView.requestFocus();
			mVideoView.setOnInfoListener(this);
			mVideoView.setOnBufferingUpdateListener(this);
			mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
				@Override
				public void onPrepared(MediaPlayer mediaPlayer) {
					// optional need Vitamio 4.0
//					mediaPlayer.setPlaybackSpeed(1.0f);
				}
			});
			isStart = true;
			mVideoView.start();
		}
	}

	@Override
	protected void getDataFromInternet() {
		
	}

	@Override
	public boolean onInfo(MediaPlayer mp, int what, int extra) {
		switch (what) {
		case MediaPlayer.MEDIA_INFO_BUFFERING_START:
			if (mVideoView.isPlaying()) {
				mVideoView.pause();
				isStart = true;

			}
			break;
		case MediaPlayer.MEDIA_INFO_BUFFERING_END:
			if (isStart) {
				mVideoView.start();
			}
			break;
		case MediaPlayer.MEDIA_INFO_DOWNLOAD_RATE_CHANGED:
			break;
		}
		return true;
	}

	@Override
	public void onBufferingUpdate(MediaPlayer mp, int percent) {
	}
	
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		LogTool.info("横竖屏切换") ;
	}

}
