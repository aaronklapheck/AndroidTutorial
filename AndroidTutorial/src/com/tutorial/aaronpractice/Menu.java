package com.tutorial.aaronpractice;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ShareCompat;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Menu extends ListActivity {

	public String classes[] = { "Counter", "TextPlay", "Email", "Camera",
			"Data", "GFX", "GFXSurface", "SoundStuff", "Tabs", "SharedPrefs",
			"InternalData", "ExternalData", "SQLiteExample", "Accelerate", 
			"HttpExample", "WeatherXMLParsing", "GLExample", "Voice",
			"StatusBar", "LightSensorDisplay"};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setListAdapter(new ArrayAdapter<String>(Menu.this,
				android.R.layout.simple_list_item_1, classes));
	} // R.layout.menu_list_view - Could not get this view to look right.

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);

		String itemClicked = classes[position];
		@SuppressWarnings("rawtypes")
		Class ourClass;
		try {
			ourClass = Class.forName("com.tutorial.aaronpractice."
					+ itemClicked);
			Intent ourIntent = new Intent(Menu.this, ourClass);
			startActivity(ourIntent);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public boolean onCreateOptionsMenu(android.view.Menu menu) {
		// TODO Auto-generated method stub
		super.onCreateOptionsMenu(menu);
		MenuInflater blowUp = getMenuInflater();
		blowUp.inflate(R.menu.cool_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case R.id.aboutUs:
			Intent i = new Intent("com.tutorial.aaronpractice.ABOUT");
			startActivity(i);
			break;
		case R.id.share:
			share();
			break;
		case R.id.prefrences:
			Intent pref = new Intent("com.tutorial.aaronpractice.PREFS");
			startActivity(pref);
			break;
		case R.id.exit:
			finish();
			break;
		}
		return false;
	}

	private void share() {
		// TODO Auto-generated method stub
		Intent shareIntent = ShareCompat.IntentBuilder.from(Menu.this)
				.setText(
						"This site has lots of great information about the author! \n" +
						"http://sites.google.com/site/aaronklapheckswebsite/")
				.setType("text/plain").getIntent()
				.setPackage("com.google.android.apps.plus");

		startActivity(shareIntent);

	}
}
