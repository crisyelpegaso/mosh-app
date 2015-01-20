package org.mosh.activity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.mosh.R;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ConcertsActivity extends Activity {

	private static String endpoint = "http://10.32.100.88:8080/mosh-services";
	private TextView content;
	EditText searchInput;

	@Override
	public boolean onCreateOptionsMenu(Menu menu){
		MenuInflater menuInflater = getMenuInflater();
		menuInflater.inflate(R.menu.main, menu);
		return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle presses on the action bar items
	    switch (item.getItemId()) {
	        case R.id.action_search:
	        	content.setText("Search pressed");
	            return true;
	        case R.id.action_settings:
	        	content.setText("Settings pressed");
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.concerts_main);

		searchInput = ((EditText) findViewById(R.id.search_input));
		
		content   =  (TextView)findViewById(R.id.content);
		Button saveme = (Button) findViewById(R.id.get_concerts);
		
//		TextView search_results = (TextView) findViewById(R.id.search_results);
//		search_results.setText(searchInput);

		saveme.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				// ALERT MESSAGE
				Toast.makeText(getBaseContext(),
						"Please wait, connecting to server.", Toast.LENGTH_LONG)
						.show();

				// Create URL string
				String URL = endpoint + "/artists?name=" + searchInput.getText().toString();

				try {
					new GetArtistsTask().execute(URL);
				} catch (Exception ex) {
					content.setText("Failed : " + ex.toString());
				}
			}
		});
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
			            	result = convertInputStreamToString(inputStream);
			            } 
		            }catch(Exception e){
		            	
		            }
		            
		            if (result == ""){
		            	return "FAILED IN TASK";
		            }
		     
		        return result;
		}

	}
	
	
	public class GetArtistsTask extends AsyncTask<String, Void, String> {
		
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
			            	result = convertInputStreamToString(inputStream);
			            } 
		            }catch(Exception e){
		            	result = "An error ocurred";
		            }
		            
		            if (result == ""){
		            	return "No results found";
		            }
		     
		        return result;
		}

	}
	
	// convert inputstream to String
	private static String convertInputStreamToString(InputStream inputStream) throws IOException{
	    BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
	    String line = "";
	    String result = "";
	    while((line = bufferedReader.readLine()) != null)
	        result += line;
	
	    inputStream.close();
	    return result;
	
	}
}
