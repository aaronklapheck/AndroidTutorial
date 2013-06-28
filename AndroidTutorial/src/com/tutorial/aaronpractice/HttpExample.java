package com.tutorial.aaronpractice;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.widget.TextView;
import android.widget.Toast;

/**
 * HttpExample uses the user interface http_ex.xml. This activity gets data from
 * a website. There are two cases:
 * <ul>
 * 		<li>Case 1: gets the data from my website using an external class: GetHttpEx.</li>
 * 		<li>Case 2: gets the text from my latest twitter post using a JSONObject.</li>
 * </ul>
 * <b>Notable classes/methods used:</b> <br />
 * Toast, StringBuilder, HttpResponse, HttpGet, HttpClient, JSONObject, and AsyncTask.
 * 
 * <br /><br />
 * @author Aaron Klapheck
 * @version 1.0.0
 */
public class HttpExample extends Activity {

	private TextView tvHttp;
	private HttpClient client;
	private final static String URL = "http://api.twitter.com/1/statuses/user_timeline.json?screen_name=";
	private final static int AARONS_SITE = 0;
	private final static int TWITTER = 1;
	private static int program = TWITTER;
	private JSONObject json;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.http_ex);
		tvHttp = (TextView) findViewById(R.id.tvHttp);
		
		switch(program){
		case TWITTER:
			client = new DefaultHttpClient();
			new Read().execute("text");
			break;
		case AARONS_SITE:
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
					.detectAll().penaltyLog().build();
			StrictMode.setThreadPolicy(policy);
			getWebsiteData(); // Use this to pull data from my website.
			break;
		}


	}

	public void getWebsiteData() {

		GetHttpEx test = new GetHttpEx();
		String returned;

		try {
			returned = test.getInternetData();
			tvHttp.setText(returned);
		} catch (Exception e) { // TODO Auto-generated catch block
			e.printStackTrace();
			tvHttp.setText("error reading method");
		}

	}

	/**
	 * 
	 * 
	 * @param username - The user name entered into twitter.
	 * @return (JSONObject) - Object containing the person's last twitter posting.
	 * @throws ClientProtocolException
	 * @throws IOException
	 * @throws JSONException
	 */
	public JSONObject lastTweet(String username)
			throws ClientProtocolException, IOException, JSONException {
		
		
		
		StringBuilder url = new StringBuilder(URL);
		url.append(username);

		HttpGet get = new HttpGet(url.toString());
		HttpResponse response = client.execute(get);
		int status = response.getStatusLine().getStatusCode();

		switch (status) {
		case 200: // success
			HttpEntity e = response.getEntity();
			String data = EntityUtils.toString(e);
			JSONArray timeline = new JSONArray(data);
			JSONObject last = timeline.getJSONObject(0);
			return last;
		case 400: // client error
			Toast.makeText(getApplicationContext(), "client error",
					Toast.LENGTH_SHORT).show();
			return null;
		case 500: // server error
			Toast.makeText(getApplicationContext(), "server error",
					Toast.LENGTH_SHORT).show();
			return null;
		case 100 | 300: // information and forwarding.
			Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_SHORT)
					.show();
			return null;
		}

		Toast.makeText(getApplicationContext(),
				"error: status code outside of range", Toast.LENGTH_SHORT)
				.show();
		return null;

	}

	public class Read extends AsyncTask<String, Integer, String> {

		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			try {
				json = lastTweet("aaronklapheck");
				return json.getString(params[0]);
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			tvHttp.setText(result);
		}

	}
}
