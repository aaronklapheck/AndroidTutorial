package com.tutorial.aaronpractice;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class Counter extends Activity implements View.OnClickListener{
    /** Called when the activity is first created. */
	
	private static int counterItem = 0;
	private Button add, sub;
	private TextView display;
	//private final AdView ad = (AdView) findViewById(R.id.adView);
	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
		// Start - Set full screen
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		// End -  Set full screen
		
		setContentView(R.layout.counter);
        
		add = (Button) findViewById(R.id.bAdd);
		sub = (Button) findViewById(R.id.bSub);
		display = (TextView) findViewById(R.id.tvDisplay);
		
        
        display.setText("Your total is " + counterItem);
        
        //ad.loadAd(new AdRequest());
        
        add.setOnClickListener(this);
        sub.setOnClickListener(this);
        
    }


	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.bAdd:
			counterItem++;
			display.setText("Your total is " + counterItem);
			break;
		case R.id.bSub:
			counterItem--;
			display.setText("Your total is " + counterItem);
			break;
		}
	}
}