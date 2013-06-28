package com.tutorial.aaronpractice;

import java.net.URL;

import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Put "http://www.google.com/ig/api?weather=sacramento,california" into the browser
 * to get an idea of the xml data this program is looking at.
 * 
 * @author Aaron
 *
 */
public class WeatherXMLParsing extends Activity implements OnClickListener {

	private static Button bDisplayWeather;
	private static EditText etCity, etState;
	private static TextView Show;
	private final static int B_WEATHER = R.id.bDisplayWeather;
	private final static String URL = "http://www.google.com/ig/api?weather=";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.xml_parse);

		InitializeViews();
		InitializeListeners();

		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
				.detectAll().penaltyLog().build();
		StrictMode.setThreadPolicy(policy);

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
		bDisplayWeather = (Button) findViewById(B_WEATHER);
		etState = (EditText) findViewById(R.id.etState);
		etCity = (EditText) findViewById(R.id.etCity);
		Show = (TextView) findViewById(R.id.tvDisplayWeather);
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
		bDisplayWeather.setOnClickListener(this);
	}

	/**
	 * The onClickListeners have been setup in {@link #InitializeListeners} and
	 * one button is set up to listen to onClicks:
	 * <ul>
	 * <li>bDisplayWeather - Displays the weather for the city and/or state
	 * specified by the user in the text view.</li>
	 * </ul>
	 */
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case B_WEATHER:
			String c = etCity.getText().toString();
			String s = etState.getText().toString();
			StringBuilder url = new StringBuilder(URL);
			String fullUrl = url.append(c).append(",").append(s).toString();
			try {
				URL website = new URL(fullUrl);
				// getting xml reader to parse data.
				XMLReader xr = SAXParserFactory.newInstance().newSAXParser()
						.getXMLReader();
				HandlingXMLStuff stuff = new HandlingXMLStuff();
				xr.setContentHandler(stuff);
				xr.parse(new InputSource(website.openStream()));
				String info = stuff.getInformation();
				Show.setText(info);
			} catch (Exception e) {
				Show.setText("error");
				e.printStackTrace();
			}
			break;
		}
	}

}
