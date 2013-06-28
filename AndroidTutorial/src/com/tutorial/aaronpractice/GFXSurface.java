package com.tutorial.aaronpractice;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

/**
 * GFXSurface uses the user interface MyBringBackSurface. This activity gets data from
 * the user through touch interactions. A touch places a ball-cross, the user then drags
 * their finger across the screen, when the user releases another ball-cross is placed
 * and a blue ball is sent flying from start to end point. All activity occurs in the 
 * {@link #onTouch} method which calls this class: 
 * {@link com.tutorial.aaronpractice.MyBringBackSurface}
 * <br /><br />
 * <b>Notable classes/methods used:</b> <br />
 * OnTouchListener.
 * 
 * <br /><br />
 * @author Aaron Klapheck
 * @version 1.0.0
 */
public class GFXSurface extends Activity implements OnTouchListener{
	
	MyBringBackSurface ourSurfaceView;
	float x = 0, y = 0, sX = 0, sY = 0, fX = 0, fY = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		ourSurfaceView = new MyBringBackSurface(this, 1);
		ourSurfaceView.setOnTouchListener(this);
		setContentView(ourSurfaceView);
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		ourSurfaceView.resume();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		ourSurfaceView.pause();
	}

	/**
	 * Thread will spend its time listening for onTouch events. When the user
	 * touches the screen and releases the screen x and y coordinates will be 
	 * sent to {@link com.tutorial.aaronpractice.MyBringBackSurface}
	 */
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		x = event.getX();
		y = event.getY();
		ourSurfaceView.setPosition(x, y);
		
		switch(event.getAction()){
		case MotionEvent.ACTION_DOWN:
			sX = event.getX();
			sY = event.getY();
			ourSurfaceView.setStartPosition(sX, sY);
			break;
		case MotionEvent.ACTION_UP:
			fX = event.getX();
			fY = event.getY();
			ourSurfaceView.setEndPosition(fX, fY);
			break;
		}
		return true;
	}
	

}
