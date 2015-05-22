package org.mosh.activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.mosh.R;

import java.io.InputStream;
import org.mosh.util.parse.ParseServerResponseUtil;

public class ResultsActivity extends BaseActivity {

	// This is the Adapter being used to display the list's data
	private TextView content;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.results);
		content = (TextView) findViewById(R.id.content);
		// ALERT MESSAGE
		Toast.makeText(getBaseContext(),
				"Searching ....", Toast.LENGTH_LONG)
				.show();

		// Create URL string
//     		String URL = endpoint + "/artists?name=" + searchInput.getText().toString();
		String URL = endpoint + "/concerts";

		try {
			new GetConcertsTask().execute(URL);
		} catch (Exception ex) {
			content.setText("Failed : " + ex.toString());
		}

	}


	public class GetConcertsTask extends AsyncTask<String, Void, String> {

		@Override
		protected void onPostExecute(String result){
			content.setText(result);
		}

		@Override
		protected String doInBackground(String... urls) {
			String url= (String)urls[0];
			InputStream inputStream = null;
			String result = "";

			// create HttpClient
			HttpClient httpclient = new DefaultHttpClient();

			try{
				// make GET request to the given URL
				HttpResponse httpResponse = httpclient.execute(new HttpGet(url));

				// receive response as inputStream
				inputStream = httpResponse.getEntity().getContent();

				// convert inputstream to string
				if(inputStream != null){
					result = ParseServerResponseUtil.convertResultsInputStreamToString(inputStream);
				}
			}catch(Exception e){

			}

			if (result == ""){
				return "No results found.";
			}

			return result;
		}

	}

}
