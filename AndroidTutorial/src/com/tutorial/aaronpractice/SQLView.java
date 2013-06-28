package com.tutorial.aaronpractice;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * SQLView uses the user interface sql_view.xml. This displays items from our database.
 * 
 * @author Aaron Klapheck
 * @version 1.0.0
 */
public class SQLView extends Activity{

	
	TextView tvDisplay;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sql_view);
		
		initializeViews();
		HotOrNot ourInfo = new HotOrNot(this);
		ourInfo.open();
		String data = ourInfo.fetchAllData();
		ourInfo.close();
		tvDisplay.setText(data);
	}
	
	
	/**
	 * Initializes all views. For example:
	 * <p>
	 * </p>
	 * {@code
	 * bNewTab = (Button) findViewById(R.id.bAddTab);
	 * }
	 * <p>
	 * </p>
	 * Notice that the variable {@code bNewTab} is not defined by a class. This
	 * should be done in the class not the method.
	 */
	private void initializeViews() {
		// TODO Auto-generated method stub
		tvDisplay = (TextView) findViewById(R.id.tvSQLinfo);
	}

}
