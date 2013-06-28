package com.tutorial.aaronpractice;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;

public class GFX extends Activity{
	
	
	MyBringBack ourView;
	WakeLock wL; // To implement wake lock you also need to add a permission.
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
		// Start: set up wake-lock
		PowerManager pM = (PowerManager) getSystemService(Context.POWER_SERVICE);
		wL = pM.newWakeLock(PowerManager.FULL_WAKE_LOCK, "wakeLock");
		// End: set up wake-lock
		
		super.onCreate(savedInstanceState);
		
		wL.acquire(); // Start wake lock
		
		ourView = new MyBringBack(this);
		setContentView(ourView);
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		wL.release();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		wL.acquire();
	}

	
	
}
