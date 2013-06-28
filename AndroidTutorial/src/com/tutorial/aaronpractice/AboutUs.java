package com.tutorial.aaronpractice;

import android.app.Activity;
import android.os.Bundle;

/**
 * AboutUs uses the user interface about.xml. This activity is called from a menu item.
 * When this activity get called by an intent the UI will appear as a dialog in the middle
 * of the screen. This is done my changing the theme for this activity in the Manifest file to:
 * Theme.DeviceDefault.Dialog.NoActionBar
 * 
 * <br /><br />
 * @author Aaron Klapheck
 * @version 1.0.0
 */
public class AboutUs extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.about);
	}

}
