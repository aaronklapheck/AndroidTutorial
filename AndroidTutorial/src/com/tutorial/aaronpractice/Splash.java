package com.tutorial.aaronpractice;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Window;
import android.view.WindowManager;

/**
 * Splash uses the user interface splash.xml. This is the start-up splash
 * screen. This screen shows how to implement a preference setting (weather or
 * not to have music). Set the starting activity in the manifest file.
 * <b>Notable classes/methods used:</b> <br />
 * initializeThread, PreferenceManager, and MediaPlayer.
 * 
 * <br />
 * <br />
 * 
 * @author Aaron Klapheck
 * @version 1.0.0
 */
public class Splash extends Activity {

	public MediaPlayer sounds;
	Thread timer;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		// Start - Set full screen
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		// End - Set full screen

		setContentView(R.layout.splash);
		initializeThread();

		sounds = MediaPlayer.create(Splash.this, R.raw.city_park);

		SharedPreferences getPref = PreferenceManager
				.getDefaultSharedPreferences(getBaseContext());
		boolean music = getPref.getBoolean("checkbox", true);
		if (music) {
			sounds.start();
		}

		timer.start();
	}

	private void initializeThread() {
		// TODO Auto-generated method stub
		timer = new Thread() {
			public void run() {
				try {
					sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					Intent startingPoint = new Intent(
							"com.tutorial.aaronpractice.MENU");
					startActivity(startingPoint);
				}
			}
		};
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		sounds.release();
		finish();
	}

}
