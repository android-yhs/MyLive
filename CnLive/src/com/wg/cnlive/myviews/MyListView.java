package com.wg.cnlive.myviews;

import com.wg.cnlive.tools.LogTool;

import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ListView;

public class MyListView extends ListView {
	private GestureDetector mGestureDetector;
	View.OnTouchListener mGestureListener;
	private final int TRIGER_LENTH = 50;  
    private final int HORIZOTAL_LENTH = 20;
    private float  FistXLocation ;
    private float  FistYlocation ;
    private boolean Istrigger ;
	public MyListView(Context context) {
		super(context) ;
	}
	public MyListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		mGestureDetector = new GestureDetector(new YScrollDetector());
		setFadingEdgeLength(0);
	}
	public MyListView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}
	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		
		int deltaX = 0;  
        int deltaY = 0;  
  
        final float x = ev.getX();  
        final float y = ev.getY();  
  
        switch (ev.getAction()) {  
        case MotionEvent.ACTION_MOVE:  
            deltaX = (int)(FistXLocation - x);  
            deltaY = (int)(FistYlocation - y);  
        if (Math.abs(deltaY) > TRIGER_LENTH  
                    && Math.abs(deltaX) < HORIZOTAL_LENTH) {  
              
                Istrigger = true;  
                return super.onInterceptTouchEvent(ev);  
            //拦截这个手势剩下的部分  ，使他不会响应viewpager的相关手势  
            }  
  
            return false;//没有触发拦截条件，不拦截事件，继续分发至viewpager  
  
        case MotionEvent.ACTION_DOWN:  
            FistXLocation = x;  
            FistYlocation = y;  
//            if(getScaleY()<-400){  
//                System.out.println(getScaleY());  
//            }  
            requestDisallowInterceptTouchEvent(false);   
            return  super.onInterceptTouchEvent(ev);  
  
        case MotionEvent.ACTION_CANCEL:  
        case MotionEvent.ACTION_UP:  
            if (Istrigger) {  
  
                Istrigger = false;  
                return  super.onInterceptTouchEvent(ev);  
            }  
  
            break;  
        }  
        return super.onInterceptTouchEvent(ev);
//        return super.onInterceptTouchEvent(ev) && mGestureDetector.onTouchEvent(ev);
	}
	
	class YScrollDetector extends SimpleOnGestureListener {
		@Override
		public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
			LogTool.info("listview:distanceY="+distanceY + ";distanceX=" + distanceX) ;
			if(Math.abs(distanceY) >= Math.abs(distanceX)) {
				return true;
			}
			return false;
		}
	}
}