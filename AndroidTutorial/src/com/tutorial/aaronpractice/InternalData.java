package com.tutorial.aaronpractice;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class InternalData extends Activity implements OnClickListener{

	private final static int bLoad_id = R.id.bLoad, bSave_id = R.id.bSave;
	private final static String FILE_NAME = "InternalString";
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.shared_preferences);
		
		InitializeViews();
		InitializeListeners();
		InitializeStreams();
	}

	private static Button bLoad, bSave;
	private static TextView dataResults;
	private static EditText sharedData;
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
	
	
	private static FileOutputStream fos;
	/**
	 * Initializes output stream by opening it and closing it.
	 */
	private void InitializeStreams() {
		// TODO Auto-generated method stub
		try {
			fos = openFileOutput(FILE_NAME, Context.MODE_PRIVATE);
			fos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	/**
	 * The onClickListeners have been setup in {@link #initializeListeners} and two buttons
	 * are set up to listen to onClicks:
	 * <ul>
	 * 		<li>bResults - The action associated with this button click is defined in {@link #sixStrings}</li>
	 * 		<li>tbPassword - This toggle button with make the text field have a password protected look
	 * 			or make the string visible to the user depending on the state of the button.</li>
	 * </ul>
	 * @param <bSave_id>
	 */
	public void onClick(View v) {
		// TODO Auto-generated method stub		
		switch(v.getId()){
		case bSave_id: 
			String data = sharedData.getText().toString();
			// Saving data via File.
			/*File f = new File(FILE_NAME);
			try {
				fos = new FileOutputStream(f);
				//write some data.
				fos.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
			try {
				fos = openFileOutput(FILE_NAME, Context.MODE_PRIVATE);
				fos.write(data.getBytes());
				fos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case bLoad_id:
			new LoadSomeStuff().execute(FILE_NAME);
			break;
		}
	}
	
	// <String, Integer, String> explained: 
	// The first string is what we are passing to the class
	// Integer is measuring the progress of the task
	// The second string is what the class is returning
	public class LoadSomeStuff extends AsyncTask<String, Integer, String>{

		ProgressDialog dialog;
		
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			dialog = new ProgressDialog(InternalData.this);
			dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
			dialog.setMax(100);
			dialog.show();
		}
		
		
		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			String collected = null;
			
			for(int i = 0; i < 20; i++){
				publishProgress(5);
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			dialog.dismiss();
			
			try {
				FileInputStream fis = openFileInput(FILE_NAME);
				// fis.abailable() gives the length of the input stream in bytes.
				byte[] dataArray = new byte[fis.available()];
				// once .read has read through everything in fis it will return -1.
				while(fis.read(dataArray) != -1){
					collected = new String(dataArray);
				}
				fis.close();
				return collected;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
	
		
		@Override
		protected void onProgressUpdate(Integer... values) {
			// TODO Auto-generated method stub
			dialog.incrementProgressBy(values[0]);
		}

		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			dataResults.setText(result);
		}
		
		
	}
	
}
