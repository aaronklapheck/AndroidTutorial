package com.tutorial.aaronpractice;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.ads.AdRequest;
import com.google.ads.AdSize;
import com.google.ads.AdView;

public class Data extends Activity implements View.OnClickListener{

	private EditText send;
	private Button safr, sa;
	private TextView got;
	private final static String BREAD_KEY = "hungry";
	private RelativeLayout rel;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.get);
		initializeViews();
		initializeListeners();
		RelativeLayout.LayoutParams relet = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		relet.addRule(RelativeLayout.BELOW, got.getId());
		AdView ad = new AdView(Data.this, AdSize.BANNER, "a14fd7cfc9eee57");
		rel.addView(ad, relet);
		ad.loadAd(new AdRequest());
	}

	/**
	 * Initializes all views. For example:
	 * <p></p>
	 * {@code
	 * bNewTab = (Button) findViewById(R.id.bAddTab);
	 * }
	 * <p></p>
	 * Notice that the variable {@code bNewTab} is not defined by a class. This should be done
	 * in the class not the method.
	 */
	private void initializeViews() {
		// TODO Auto-generated method stub
		send = (EditText) findViewById(R.id.etSend);
		safr = (Button) findViewById(R.id.bStartAFR);
		sa = (Button) findViewById(R.id.bStartA);
		got = (TextView) findViewById(R.id.tvGot);
		rel = (RelativeLayout) findViewById(R.id.relLayout);
		
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
		safr.setOnClickListener(this);
		sa.setOnClickListener(this);
	}

	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		String bread = send.getText().toString();
		Bundle basket = new Bundle();
		basket.putString(BREAD_KEY, bread);
		
		switch(v.getId()){
		case R.id.bStartA:
			Intent breadBasket = new Intent(Data.this, OpenedClass.class);
			breadBasket.putExtras(basket);
			startActivity(breadBasket);
			break;
		case R.id.bStartAFR:
			Intent i = new Intent(Data.this, OpenedClass.class);
			i.putExtras(basket);
			startActivityForResult(i, 0);
			break;
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if(resultCode == RESULT_OK){
			Bundle extras = data.getExtras();
			String answer = (String) extras.get("answer");
			got.setText(answer);
		}
	}

}
