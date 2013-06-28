package com.tutorial.aaronpractice;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;

/**
 * Accelerate uses the user interface MyBringBackSurface.java. This is a very simple activity so all 
 * events are triggered by user motions in {@link #onSensorChanged}.
 * <br /><br />
 * <b>Notable classes/methods used:</b> <br />
 * setRequestedOrientation, SensorManager, and Sensor.
 * 
 * <br /><br />
 * @author Aaron Klapheck
 * @version 1.0.0
 */
public class Accelerate extends Activity implements SensorEventListener{

	private static float sensorX, sensorY;
	MyBringBackSurface ourSurfaceView;
	SensorManager sm;
	
	/**
	 * onCreate sets the screen orientation to Portrait, sets up the view, and 
	 * ensures the android device has an accelerometer.
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		// Sets the screen orientation to portrait.
		super.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		
		ourSurfaceView = new MyBringBackSurface(this, 2);
		setContentView(ourSurfaceView);
		
		// Make sure the device has an accelerometer.
		sm = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		if(sm.getSensorList(Sensor.TYPE_ACCELEROMETER).size() != 0){
			// If it has one then use it
			Sensor s = sm.getSensorList(Sensor.TYPE_ACCELEROMETER).get(0);
			sm.registerListener(this, s, SensorManager.SENSOR_DELAY_NORMAL);
		}
		sensorX = sensorY = 0;
	}


	/**
	 * Every time the android accelerometer sensor detect an acceleration this method will be
	 * called. Acceleration in the x- and y-axis is saved and passed to 
	 * {@link com.tutorial.aaronpractice.MyBringBackSurface}
	 */
	public void onSensorChanged(SensorEvent event) {
		// TODO Auto-generated method stub
		try {
			// will give us about 60 frames / second.
			Thread.sleep(16);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sensorX = event.values[0];
		sensorY = event.values[1];
		ourSurfaceView.setPosition(sensorX, sensorY);
	}
	

	public void onAccuracyChanged(Sensor arg0, int arg1) {
		// TODO Auto-generated method stub
		
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
		sm.unregisterListener(this);
		super.onPause();
		ourSurfaceView.pause();
	}

}
