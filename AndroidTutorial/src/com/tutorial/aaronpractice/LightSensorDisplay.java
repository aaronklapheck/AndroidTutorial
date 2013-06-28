package com.tutorial.aaronpractice;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Got starter code from "Blogspot: Using Android device build-in light sensor".
 * @author Aaron Klapheck
 *
 */
public class LightSensorDisplay extends Activity implements SensorEventListener, OnClickListener{

	private float currentReading = 0;
	private SensorManager sm;
	private TextView tvMax, tvCurrent, tvLocalMax;
	private ProgressBar pbLight;
	private Button bSetMax;
	
	/**
	 * onCreate sets the screen orientation to Portrait, sets up the view, and 
	 * ensures the android device has a light sensor.
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		// Sets the screen orientation to portrait.
		super.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		setContentView(R.layout.light_sensor);
		
		InitializeViews();
		
		// Make sure the device has an accelerometer.
		sm = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		Sensor lightS = sm.getDefaultSensor(Sensor.TYPE_LIGHT);
		if(lightS != null){
			// If it has one then use it
			sm.registerListener(this, lightS, SensorManager.SENSOR_DELAY_NORMAL);
			// set text view
			float max = lightS.getMaximumRange();
			tvMax.setText("Maximum Possible Reading: " + String.valueOf(max));
			initializeListeners();
		} else{
			Toast.makeText(this, "No light sensor detected", Toast.LENGTH_LONG).show();
		}
		
		    	    
	}


	/**
	 * Initializes all views. For example:
	 * <p></p>
	 * {@code
	 * bNewTab = (Button) findViewById(R.id.bAddTab);
	 * }
	 * <p></p>
	 * Notice that the variable {@code bNewTab} is not defined by a class. This should be done
	 * in the class which is why it does not appear in this method.
	 */
	private void InitializeViews() {
		// TODO Auto-generated method stub
		tvMax = (TextView) findViewById(R.id.tvMaxLight);
		tvCurrent = (TextView) findViewById(R.id.tvCurrentLight);
		tvLocalMax = (TextView) findViewById(R.id.tvLocalLightMax);
		pbLight = (ProgressBar) findViewById(R.id.pbLightMeter);
		bSetMax = (Button) findViewById(R.id.bSetLightMax);
	}


	public void onSensorChanged(SensorEvent event) {
		// TODO Auto-generated method stub
		try {
			// will give us about 60 frames / second.
			Thread.sleep(16);
			currentReading = event.values[0];
			pbLight.setProgress((int) currentReading);
			tvCurrent.setText("Current Reading: " + String.valueOf(currentReading));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	public void onAccuracyChanged(Sensor arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}
	
	
	/**
	 * Initializes all listeners. For example:
	 * <p></p>
	 * {@code
	 * bNewButton.setOnClickListener(this);
	 * }
	 * <p></p>
	 * For this code to work the following must be done:
	 * <ul>
	 * 		<li>{@code bNewButton} (a button in this case) must be assigned in {@link #initializeViews}
	 * 			and declared in the class container
	 * 		</li>
	 * 		<li>For {@code this} to be used as the onClickListener, the 
	 * 			{@link android.view.View.OnClickListener} must be implemented by this class.
	 * 		</li>
	 * </ul>
	 */
	private void initializeListeners() {
		// TODO Auto-generated method stub
		bSetMax.setOnClickListener(this);
	}

	public void onClick(View v) {
		// TODO Auto-generated method stub
		pbLight.setMax((int) currentReading);
		tvLocalMax.setText("Maximum Local Reading: " + String.valueOf(currentReading));
	}
	
}
