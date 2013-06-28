package com.tutorial.aaronpractice;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.app.Activity;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

/**
 * ExternalData uses the user interface external_data.xml. This is a very simple activity so all 
 * events are triggered by user input button presses defined in {@link #onClick}.
 * 
 * @author Aaron Klapheck
 * @version 1.0.0
 */
public class ExternalData extends Activity implements OnItemSelectedListener, OnClickListener{

	private static TextView tvCanRead, tvCanWrite;
	private static Spinner spin;
	private static EditText etSaveAs;
	private static Button bConfirmSave, bSaveFile;
	private final static int bConfirmSave_id = R.id.bConfirmSave, bSaveFile_id = R.id.bSaveFile;
	private static String state;
	private static boolean canW, canR;
	private static String[] paths = {"Music", "Pictures", "Downloads"};
	private static File path = null;
	private static File fileName = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.external_data);
		
		initializeViews();
		initializeListeners();
		checkState();
		
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
	private void initializeViews() {
		// TODO Auto-generated method stub
		tvCanRead = (TextView) findViewById(R.id.tvCanRead);
		tvCanWrite = (TextView) findViewById(R.id.tvCanWrite);
		spin = (Spinner) findViewById(R.id.spinner1);
		etSaveAs = (EditText) findViewById(R.id.etSaveAs);
		bConfirmSave = (Button) findViewById(bConfirmSave_id);
		bSaveFile = (Button) findViewById(bSaveFile_id);
		
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
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, 
				android.R.layout.simple_spinner_item, paths);
		spin.setAdapter(adapter);
		spin.setOnItemSelectedListener(this);
		bConfirmSave.setOnClickListener(this);
		bSaveFile.setOnClickListener(this);
	}
	
	private void checkState() {
		// TODO Auto-generated method stub
		state = Environment.getExternalStorageState();
		if(state.equals(Environment.MEDIA_MOUNTED)){
			// Can read and write.
			tvCanWrite.setText("Can Write: True");
			tvCanRead.setText("Can Read: True");
			canR = canW = true;
		} else if(state.equals(Environment.MEDIA_MOUNTED_READ_ONLY)){
			tvCanWrite.setText("Can Write: False");
			tvCanRead.setText("Can Read: True");
			canR = true;
			canW = false;
		} else {
			tvCanWrite.setText("Can Write: False");
			tvCanRead.setText("Can Read: False");
			canR = canW = false;
		}
	}


	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		int pos = spin.getSelectedItemPosition();
		switch(pos){
		case 0:
			//music
			path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC);
			break;
		case 1:
			//pictures
			path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
			break;
		case 2:
			//downloads
			path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
			break;
		}
	}


	public void onNothingSelected(AdapterView<?> parent) {
		// TODO Auto-generated method stub
		
	}



	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case bConfirmSave_id: 
			bSaveFile.setVisibility(View.VISIBLE);
			break;
		case bSaveFile_id:
			String f = etSaveAs.getText().toString() + ".png";
			fileName = new File(path, f);
			checkState();
			if(canW == canR == true){
				path.mkdir(); //mkdir will crate the path only if it does not already exist.
				
				try {
					InputStream is = getResources().openRawResource(R.drawable.aqua_ball);
					OutputStream os = new FileOutputStream(fileName);
					byte[] data = new byte[is.available()];
					is.read(data);
					os.write(data);
					is.close();
					os.close();
					
					Toast t = Toast.makeText(this, "File has been saved", Toast.LENGTH_SHORT);
					t.show();
					
					//Update files for the user to access.
					MediaScannerConnection.scanFile(this, new String[] {fileName.toString()}, 
							null, 
							new MediaScannerConnection.OnScanCompletedListener() {
								
								public void onScanCompleted(String path, Uri uri) {
									// TODO Auto-generated method stub
									Toast t = Toast.makeText(ExternalData.this, "scan complete", 
											Toast.LENGTH_SHORT);
									t.show();
								}
							});
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			break;
		}
	}
	
}
