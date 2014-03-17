package com.wg.cnlive.tools;

import com.wg.cnlive.activitys.PlayerActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.webkit.MimeTypeMap;

public class MediaTool {
//	public static void playVideo(Context context,String url) {
//		String extension = MimeTypeMap.getFileExtensionFromUrl(url);  
//		String mimeType = MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension);  
//		Intent mediaIntent = new Intent(Intent.ACTION_VIEW);  
//		mediaIntent.setDataAndType(Uri.parse(url), mimeType);  
//		context.startActivity(mediaIntent); 
//	}
	
	public static void playVideo(Context context,String url) {
		Intent intent = new Intent() ;
		intent.putExtra("path", url) ;
		intent.setClass(context, PlayerActivity.class) ;
		context.startActivity(intent) ;
	}
}
