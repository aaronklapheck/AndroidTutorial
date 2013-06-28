package com.tutorial.aaronpractice;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * SharedPrefs uses the user interface shared_preferences.xml. This is a very simple activity so all 
 * events are triggered by user input button presses defined in {@link #onClick}.
 * 
 * @author Aaron Klapheck
 * @version 1.0.0
 */
public class SharedPrefs extends Activity implements OnClickListener{

	
	private static Button bLoad, bSave;
	private static TextView dataResults;
	private static EditText sharedData;
	private final static int bLoad_id = R.id.bLoad, bSave_id = R.id.bSave;
	private static String stringData = "";
	private final static String fileName = "MySharedString", stringKey = "SharedString", 
			getStringDefault = "couldn't load data";
	private static SharedPreferences someData;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.shared_preferences);
		
		InitializeViews();
		InitializeListeners();
		
		someData = getSharedPreferences(fileName, 0);
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
		bSave = (Button) findViewById(R.id.bSave);
		bLoad = (Button) findViewById(bLoad_id);
		sharedData = (EditText) findViewById(R.id.etInput);
		dataResults = (TextView) findViewById(R.id.tvLoadData);
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
		bSave.setOnClickListener(this);
	    bLoad.setOnClickListener(this);
	}
	
	
	/**
	 * The onClickListeners have been setup in {@link #initializeListeners} and two buttons
	 * are set up to listen to onClicks:
	 * <ul>
	 * 		<li>bSave - This button will save a string key pair through the 
	 *			{@link android.content.SharedPreferences} class.</li>
	 * 		<li>bLoad - This button will take the string stored in the 
	 *			{@link android.content.SharedPreferences} class and display it in a text view. </li>
	 * </ul>
	 */
	public void onClick(View v) {
		// TODO Auto-generated method stub		
		switch(v.getId()){
		case bSave_id: 
			stringData = sharedData.getText().toString();
			SharedPreferences.Editor editor = someData.edit();
			editor.putString(stringKey, stringData);
			editor.commit();
			break;
		case bLoad_id:
			String dataReturned = someData.getString(stringKey, getStringDefault);
			dataResults.setText(dataReturned);
			break;
		}
	}

}
