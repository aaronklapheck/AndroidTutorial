package com.tutorial.aaronpractice;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

public class OpenedClass extends Activity implements OnClickListener,
		OnCheckedChangeListener {

	TextView question, test;
	RadioGroup selection;
	Button returns;
	String gotBread, sendData;
	final static String BREAD_KEY = "hungry";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.send);
		initializeViews();
		initializeListeners();		
		
		SharedPreferences getData = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
		String name = getData.getString("name", "Aaron");
		String values = getData.getString("list", "4");
		if(values.contentEquals("1")){
			question.setText(name);
		}
		
		//Bundle gotBasket = getIntent().getExtras();
		//gotBread = gotBasket.getString(BREAD_KEY);
		//question.setText(gotBread);
	}


	private void initializeViews() {
		// TODO Auto-generated method stub
		question = (TextView) findViewById(R.id.tvQuestion);
		test = (TextView) findViewById(R.id.tvTest);
		returns = (Button) findViewById(R.id.bReturn);
		selection = (RadioGroup) findViewById(R.id.rgAnswers);
	}
	
	private void initializeListeners() {
		// TODO Auto-generated method stub
		selection.setOnCheckedChangeListener(this);
		returns.setOnClickListener(this);
	}

	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent person = new Intent();
		Bundle backpack = new Bundle();
		backpack.putString("answer", sendData);
		person.putExtras(backpack);
		setResult(RESULT_OK, person);
		finish();
	}

	public void onCheckedChanged(RadioGroup group, int checkedId) {
		// TODO Auto-generated method stub
		switch (checkedId) {
		case R.id.rbCrazy:
			sendData = "Probably right!";
			break;
		case R.id.rbHandsome:
			sendData = "Definitly right!";
			break;
		case R.id.rbSmart:
			sendData = "Definitive!";
			break;
		}
		
		test.setText(sendData);
	}

}
