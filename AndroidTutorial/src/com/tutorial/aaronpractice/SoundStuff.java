package com.tutorial.aaronpractice;

import android.app.Activity;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;

public class SoundStuff extends Activity implements OnClickListener, OnLongClickListener{

	View v;
	SoundPool sp;
	int explosion = 0;
	MediaPlayer mP;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setVolumeControlStream(AudioManager.STREAM_MUSIC);
		v = new View(this);
		v.setOnClickListener(this);
		v.setOnLongClickListener(this);
		setContentView(v);
		
	}
 
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(explosion != 0){
			sp.play(explosion, 1, 1, 0, 0, 1);
		}
	}
	
	public boolean onLongClick(View v) {
		// TODO Auto-generated method stub
		/* Call stop before start to avoid repeatedly executing the start command and 
		 * thus having the music play over each-other. This causes no music to play at all
		 * so the mP.stop() command is not used. It appears that the mP.stop() command is not 
		 * even needed because multiple instances of the start command do not cause multiple
		 * instances of music to start.
		 */
		//mP.stop(); 
		mP.start();
		return false;
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		
		// SoundPool is used to play short (5 second) clips.
		sp = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
		explosion = sp.load(this, R.raw.gun_fire, 1);
		// MediaPlayer is used to play longer sounds. Like background music.
		mP = MediaPlayer.create(this, R.raw.city_park);
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		sp.release();
		mP.release();
	}

	
}
