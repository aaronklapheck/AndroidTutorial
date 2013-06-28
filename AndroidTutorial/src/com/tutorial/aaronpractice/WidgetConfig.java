package com.tutorial.aaronpractice;

import android.app.Activity;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RemoteViews;

/**
 * WidgetConfig uses the interface simple_widget_config.xml. This is a very
 * simple activity so all events are triggered by user input button presses
 * defined in {@link #onClick}.
 * 
 * @author Aaron Klapheck
 * @version 1.0.0
 */
public class WidgetConfig extends Activity implements OnClickListener {

	private static Button bConfig;
	private static EditText etConfig;
	private final static int bConfig_id = R.id.bWidgetConfig;
	private AppWidgetManager awm;
	private Context c;
	private int awID;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.simple_widget_config);

		InitializeViews();
		InitializeListeners();
		
		// Geting widget ID that launched this activity.
		c = this.getApplicationContext();
		Intent i = getIntent();
		Bundle extra = i.getExtras();
		if(extra != null){
			awID = extra.getInt(AppWidgetManager.EXTRA_APPWIDGET_ID,
					AppWidgetManager.INVALID_APPWIDGET_ID);
		} 
		// If they gave us an intent without the widget id, just bail
		else{ 
			finish();
		}
		awm = AppWidgetManager.getInstance(c);
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
	 * should be done in the class which is why it does not appear in this
	 * method.
	 */
	private void InitializeViews() {
		// TODO Auto-generated method stub
		bConfig = (Button) findViewById(bConfig_id);
		etConfig = (EditText) findViewById(R.id.etWidgetConfig);
	}

	/**
	 * Initializes all listeners. For example:
	 * <p>
	 * </p>
	 * {@code
	 * bNewButton.setOnClickListener(this);
	 * }
	 * <p>
	 * </p>
	 * For this code to work the following must be done:
	 * <ul>
	 * <li>{@code bNewButton} (a button in this case) must be assigned in
	 * {@link #initializeViews} and declared in the class container</li>
	 * <li>For {@code this} to be used as the onClickListener, the
	 * {@link android.view.View.OnClickListener} must be implemented by this
	 * class.</li>
	 * </ul>
	 */
	private void InitializeListeners() {
		// TODO Auto-generated method stub
		bConfig.setOnClickListener(this);
	}

	/**
	 * The onClickListeners have been setup in {@link #InitializeListeners} and
	 * the button is set up to listen to onClicks:
	 * When the button is pressed ...
	 */
	public void onClick(View v) {
		// TODO Auto-generated method stub
		String e = etConfig.getText().toString();
		
		RemoteViews r = new RemoteViews(c.getPackageName(), R.layout.simple_widget);
		r.setTextViewText(R.id.tvWidgetConfigInput, e);
		
		Intent in = new Intent(c, Splash.class);
		PendingIntent pi = PendingIntent.getActivity(c, 0, in, 0);
		r.setOnClickPendingIntent(R.id.bWidgetOpen, pi);
		awm.updateAppWidget(awID, r);
		
		Intent result = new Intent();
		result.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, awID);
		setResult(RESULT_OK, result);
		
		finish();
	}

}
