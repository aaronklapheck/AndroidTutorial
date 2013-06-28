package com.tutorial.aaronpractice;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

/**
 * Tabs uses the interface tabs.xml. Tabs uniquely defines tab layout using java instead of 
 * simply relying on the tabs.xml. {@link #setupTabs} sets up the tab layout. This is a very
 * simple activity so all events are triggered by user input button presses defined in 
 * {@link #onClick}.
 * 
 * @author Aaron Klapheck
 * @version 1.0.0
 */
public class Tabs extends Activity implements OnClickListener{

	
	TabHost th;
	Button bStart;
	Button bStop;
	Button bNewTab;
	TextView tvShowResults;
	long start = 0, stop = 0;
	long result = 0;
	int millis = 0, minutes = 0, seconds = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tabs);
		initializeViews();
		initializeListeners();
		
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
		setupTabs();
		bNewTab = (Button) findViewById(R.id.bAddTab);
		bStart = (Button) findViewById(R.id.bStartWatch);
		bStop = (Button) findViewById(R.id.bStopWatch);
		tvShowResults = (TextView) findViewById(R.id.tvShowResults);
	}

	
	/**
	 * setupTabs uses the {@link android.widget.TabHost} to set up the host for all the tabs.
	 * Next {@link android.widget.TabHost.TabSpec} is used to setup the content for the tab
	 * and the tab name i.e. Indicator. This is done for all three tabs.
	 */
	private void setupTabs() {
		// TODO Auto-generated method stub
		th = (TabHost) findViewById(R.id.tabhost);
		th.setup();
		TabSpec specs = th.newTabSpec("tag1");
		specs.setContent(R.id.tab1);
		specs.setIndicator("StopWatch");
		th.addTab(specs);
		
		specs = th.newTabSpec("tag2");
		specs.setContent(R.id.tab2);
		specs.setIndicator("Tab 2");
		th.addTab(specs);
		
		specs = th.newTabSpec("tag3");
		specs.setContent(R.id.tab3);
		specs.setIndicator("Add a Tab");
		th.addTab(specs);
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
		bNewTab.setOnClickListener(this);
		bStart.setOnClickListener(this);
		bStop.setOnClickListener(this);
	}
	
	
	/**
	 * The onClickListeners have been setup in {@link #initializeListeners} and three buttons
	 * are set up to listen to onClicks.
	 */
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.bAddTab:
			createNewTab();			
			break;
		case R.id.bStartWatch:
			
			start = System.currentTimeMillis();
			
			break;
		case R.id.bStopWatch:
			
			stop = System.currentTimeMillis();
			if (start != 0){
				result = (stop - start);
				millis = (int) result;
				seconds = (int) result/1000;
				minutes = seconds/60;
				
				
				millis = millis % 100;
				seconds = seconds % 100;
				tvShowResults.setText(String.format("%d:%02d:%02d", minutes, seconds, millis));
			}
			break;
		}
	}


	private void createNewTab() {
		// TODO Auto-generated method stub
		TabSpec tSpec = th.newTabSpec("NewTab");
		tSpec.setContent(new TabHost.TabContentFactory() {
			
			public View createTabContent(String tag) {
				// TODO Auto-generated method stub
				TextView newText = new TextView(Tabs.this);
				newText.setText("You've created a new tab");
				
				return (newText);
			}
		});
		
		tSpec.setIndicator("New");
		th.addTab(tSpec);
	}

}
