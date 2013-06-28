package com.tutorial.aaronpractice;

import java.util.Random;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;


/**
 * TextPlay uses the interface text.xml. This is a very simple activity so all events are triggered 
 * by user input button presses defined in {@link #onClick}.
 * 
 * @author Aaron Klapheck
 * @version 1.0.0
 */
public class TextPlay extends Activity implements View.OnClickListener{
	
	private static Button chkCmd;
	private static ToggleButton passTog;
	private static EditText txtCmd;
	private static TextView Show;
	private final static int chkCmd_id = R.id.bResults, passTog_id = R.id.tbPassword;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.text);
		
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
		chkCmd = (Button) findViewById(chkCmd_id);
		passTog = (ToggleButton) findViewById(passTog_id);
		txtCmd = (EditText) findViewById(R.id.etCommands);
		Show = (TextView) findViewById(R.id.tvResults);
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
		chkCmd.setOnClickListener(this);
	    passTog.setOnClickListener(this);
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
		switch(v.getId()){
		case chkCmd_id: 
			sixStrings();
			
			// Start - hide the keyboard after the results button is clicked.
			InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
			imm.hideSoftInputFromWindow(txtCmd.getWindowToken(), 0);
			// End - hide the keyboard after the results button is clicked.
			
			break;
		case passTog_id:
			if(passTog.isChecked()){
				txtCmd.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD); //TYPE_TEXT_VARIATION_PASSWORD);
			} else{
				txtCmd.setInputType(InputType.TYPE_CLASS_TEXT); //TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
			} 
			break;
		}
	}

	/**
	 * sixStrings takes the string entered into the text view {@code txtCmd} and will make the text view 
	 * {@code Show} do various things based on what the user has entered. There are six types of strings
	 * the user can enter defined as follows.
	 * <ul>
	 * 		<li>left - The {@code show} text view will be positioned to the left and will display
	 * 			the word {@code left}.</li>
	 * 		<li>center - The {@code show} text view will be positioned to the center and will display
	 * 			the word {@code center}.</li>
	 * 		<li>right - The {@code show} text view will be positioned to the right and will display
	 * 			the word {@code right}.</li>
	 * 		<li>blue - The text in the {@code show} text view will be turned blue.</li>
	 * 		<li>WTF - The {@code show} text view will have a random position, text size, and color using 
	 * 			{@link java.util.Random}. In addition the word shown will be {@code WTF!!}.</li>
	 * 		<li>everything else - The {@code show} text view will be positioned to the center, will display
	 * 			the word {@code invalid}, the text displayed will be white, and the text size will be
	 * 			the system default.</li>
	 * </ul>
	 */
	private void sixStrings() {
		// TODO Auto-generated method stub
		String check = txtCmd.getText().toString().toLowerCase();
    	Show.setText(check);
    	if(check.contentEquals("left")){
    		Show.setGravity(Gravity.LEFT);
    	} else if(check.contentEquals("center")){
    		Show.setGravity(Gravity.CENTER);
    	} else if(check.contentEquals("right")){
    		Show.setGravity(Gravity.RIGHT);
    	} else if(check.contentEquals("blue")){
    		Show.setTextColor(Color.BLUE);
    	} else if(check.contentEquals("wtf")){
    		Random crazy = new Random();
    		Show.setText("WTF!!!");
    		Show.setTextSize(crazy.nextInt(75));
    		Show.setTextColor(Color.rgb(crazy.nextInt(265), crazy.nextInt(265), crazy.nextInt(265)));
    		
    		switch(crazy.nextInt(3)){	        		
    		case 0:
    			Show.setGravity(Gravity.LEFT);	        			
    			break;
    		case 1:
    			Show.setGravity(Gravity.CENTER);	        			
    			break;
    		case 2:
    			Show.setGravity(Gravity.RIGHT);
    			break;
    		}
    		
    	} else{
    		Show.setText("invalid");
    		Show.setGravity(Gravity.CENTER);
    		Show.setTextColor(Color.WHITE);
    		Show.setTextSize((float) 14);
    	}
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(android.view.Menu menu) {
		// TODO Auto-generated method stub
		super.onCreateOptionsMenu(menu);
		MenuInflater blowUp = getMenuInflater();
		blowUp.inflate(R.menu.text_play, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch(item.getItemId()){
		case R.id.instructions:
			Intent i = new Intent("com.tutorial.aaronpractice.INSTRUCTIONS");
			startActivity(i);
			break;
		}
		return false;
	}

}
