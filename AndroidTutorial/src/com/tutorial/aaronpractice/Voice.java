package com.tutorial.aaronpractice;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class Voice extends Activity implements OnClickListener{

	private Button b;
	private ListView lv;
	private static final int check = 11;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.voice);
		
		InitializeViews();
		InitializeListeners();
		    	    
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
		b = (Button) findViewById(R.id.bVoice);
		lv = (ListView) findViewById(R.id.lvVoice);
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
	private void InitializeListeners() {
		// TODO Auto-generated method stub
		b.setOnClickListener(this);
	}

	
	/**
	 * The onClickListeners have been setup in {@link #InitializeListeners} and two buttons
	 * are set up to listen to onClicks:
	 * <ul>
	 * 		<li>bResults - The action associated with this button click is defined in {@link #sixStrings}</li>
	 * 		<li>tbPassword - This toggle button with make the text field have a password protected look
	 * 			or make the string visible to the user depending on the state of the button.</li>
	 * </ul>
	 */
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent i = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
		//language model required for speech recognition.
		i.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
		i.putExtra(RecognizerIntent.EXTRA_PROMPT, "Speak and be recognized");
		startActivityForResult(i, check);
	}


	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		if(requestCode == check && resultCode == RESULT_OK){
			ArrayList<String> results = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
			// ArrayAdapter(context, type of list look and feel, list items)
			lv.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, results));
		}
		
		super.onActivityResult(requestCode, resultCode, data);
	}
	
}
