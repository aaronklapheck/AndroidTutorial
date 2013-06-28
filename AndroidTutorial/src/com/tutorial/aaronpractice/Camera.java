package com.tutorial.aaronpractice;

import java.io.IOException;
import java.io.InputStream;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

/**
 * Camera uses the user interface photo.xml. This is a very simple activity so all events are triggered 
 * by user input button presses defined in {@link #onClick}.
 * <br /><br />
 * <b>Notable classes/methods used:</b> <br />
 * InputStream, BitmapFactory, Bundle, android.provider.MediaStore.ACTION_IMAGE_CAPTURE, 
 * and startActivityForResult.
 * 
 * <br /><br />
 * @author Aaron Klapheck
 * @version 1.0.0
 */
public class Camera extends Activity implements View.OnClickListener{

	ImageView iv;
	ImageButton ib;
	Button b;
	
	final static int cameraData = 0;
	
	Intent takePic;
	Intent setWall;
	
	Bitmap bmp;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.photo);		
		
		initializeViews();
		InitializeListeners();
		
		
		InputStream is = getResources().openRawResource(R.drawable.ic_launcher);
		bmp = BitmapFactory.decodeStream(is);
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
		iv = (ImageView) findViewById(R.id.ivReturnPic);
		ib = (ImageButton) findViewById(R.id.ibTakePic);
		b = (Button) findViewById(R.id.bSetWallpaper);
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
		ib.setOnClickListener(this);
		b.setOnClickListener(this);
	}
	
	
	/**
	 * The onClickListeners have been setup in {@link #InitializeListeners} and two buttons
	 * are set up to listen to onClicks:
	 * <ul>
	 * 		<li>ibTakePic - Instructs the device to to take a picture with the default capture device.</li>
	 * 		<li>bSetWallpaper - Sets the wall-paper to the picture taken.</li>
	 * </ul>
	 */
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		switch(v.getId()){
		case R.id.ibTakePic:
			takePic = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
			startActivityForResult(takePic, cameraData);
			break;
		case R.id.bSetWallpaper:
			try {
				getApplicationContext().setWallpaper(bmp);
				//WallpaperManager wall = new WallpaperManager(); 
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			setWall = new Intent(android.content.Intent.ACTION_SET_WALLPAPER);
			startActivity(setWall);
			break;
		}
		
	}

	/**
	 * Returns the photo in the Intent (extras) and saves it to a Bitmap.
	 */
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if(resultCode == RESULT_OK){
			Bundle extras = data.getExtras();
			bmp = (Bitmap) extras.get("data");
			iv.setImageBitmap(bmp);
		}
	}
	
	

}
