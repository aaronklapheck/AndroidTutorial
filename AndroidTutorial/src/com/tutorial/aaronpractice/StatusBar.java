package com.tutorial.aaronpractice;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class StatusBar extends Activity implements OnClickListener {

	private Button b;
	private String ns = Context.NOTIFICATION_SERVICE;
	private NotificationManager mNM;
	private static final int STATUS_ID = 3491786;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.status_bar);

		InitializeViews();
		InitializeListeners();
		
		mNM = (NotificationManager) getSystemService(ns);

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
		b = (Button) findViewById(R.id.bNotification);
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
		b.setOnClickListener(this);
	}

	/**
	 * The onClickListeners have been setup in {@link #InitializeListeners} on a button.
	 */
	public void onClick(View v) {
		// TODO Auto-generated method stub

		// Initialize the notification
		int icon = android.R.drawable.stat_notify_more;
		CharSequence tickerText = "Hello";
		long when = System.currentTimeMillis();
		Notification notification = new Notification(icon, tickerText, when);

		// Define notification message
		//Intent notificationIntent = new Intent(this, StatusBar.class);
		//PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);
		PendingIntent contentIntent = PendingIntent.getActivities(this, 0,
	            makeMessageIntentStack(this), PendingIntent.FLAG_CANCEL_CURRENT);
		Context context = getApplicationContext();
		CharSequence contentTitle = "What's Up";
		CharSequence contentText = "what's up gangstas";
		notification.setLatestEventInfo(context, contentTitle, contentText, contentIntent);
		notification.defaults = Notification.DEFAULT_ALL;
		notification.flags |= Notification.FLAG_AUTO_CANCEL;

		mNM.notify(STATUS_ID, notification);

	}
	
	
	/**
	 * Allows user to use the back button in a meaningful way.
	 * This method creates an array of Intent objects representing the
	 * activity stack for the incoming activity details state that the
	 * application should be in when launching it from a notification.
	 */
	static Intent[] makeMessageIntentStack(Context context) {
	    // A typical convention for notifications is to launch the user deeply
	    // into an application representing the data in the notification; to
	    // accomplish this, we can build an array of intents to insert the back
	    // stack stack history above the item being displayed.
	    Intent[] intents = new Intent[2]; // was 4 but not sure what 2 and 3 do.

	    // First: root activity of notification's activity.
	    // This is a convenient way to make the proper Intent to launch and
	    // reset an application's task.
	    intents[0] = Intent.makeRestartActivityTask(new ComponentName(context,
	            com.tutorial.aaronpractice.Menu.class));

	    // "App" ??
	    //intents[1] = new Intent(context, com.tutorial.aaronpractice.Menu.class);
	    //intents[1].putExtra("com.tutorial.aaronpractice.Menu.Path", "App");
	    
	    // "App/Notification" ??
	    //intents[2] = new Intent(context, com.tutorial.aaronpractice.Menu.class);
	    //intents[2].putExtra("com.tutorial.aaronpractice.Menu.Path", "App/Notification");

	    // Now the activity to display to the user.  Also fill in the data it
	    // should display.
	    intents[1] = new Intent(context, StatusBar.class);

	    return intents;
	}
	
}
