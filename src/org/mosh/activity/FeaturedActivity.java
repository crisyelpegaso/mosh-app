package org.mosh.activity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.mosh.R;
import org.mosh.activity.ResultsActivity.GetConcertsTask;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class FeaturedActivity extends BaseActivity {

	private ListView featuredConcerts ;  
	private ArrayAdapter<String> listAdapter ;  
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.featured);
	    
	    // ALERT MESSAGE
 		Toast.makeText(getBaseContext(),"Searching ....", Toast.LENGTH_LONG).show();

 		// Create URL string
 		String URL = endpoint + "/concerts";

 		try {
 			new GetConcertsTask().execute(URL);
 		} catch (Exception ex) {
 			
 		}
 		featuredConcerts = (ListView) findViewById(R.id.featuredConcerts);
	    featuredConcerts.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
			    Intent intent = new Intent(view.getContext(), ConcertActivity.class);
			    startActivity(intent);
			}

		});
	}

public class GetConcertsTask extends AsyncTask<String, Void, ArrayList<String>> {
		
		@Override
		protected void onPostExecute(ArrayList<String> result){
		     
		    listAdapter = new ArrayAdapter<String>(FeaturedActivity.this, R.layout.list_featured, result);  
		    featuredConcerts.setAdapter(listAdapter);
		    featuredConcerts.setTextFilterEnabled(true);

		}
		
		@Override
		protected ArrayList<String> doInBackground(String... urls) {
			ArrayList<String> results = new ArrayList<String>();
			String url= (String)urls[0]; 
			InputStream inputStream = null;
		 
		            // create HttpClient
		            HttpClient httpclient = new DefaultHttpClient();
		 
		            try{
		            	// make GET request to the given URL
			            HttpResponse httpResponse = httpclient.execute(new HttpGet(url));
			 
			            // receive response as inputStream
			            inputStream = httpResponse.getEntity().getContent();
			 
			            // convert inputstream to string
			            if(inputStream != null){
			            	results = convertInputStreamToString(inputStream);
			            } 
		            }catch(Exception e){
		            	
		            }
		            
		            if (results.isEmpty()){
		            	results.add("No results found.");
		            }
		     
		        return results;
		}

	}

//convert inputstream to String
	private static  ArrayList<String> convertInputStreamToString(InputStream inputStream) throws IOException{
	    BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
	    ArrayList<String> results = new ArrayList<String>();
	    String line = "";
	    String result = "";
	    while((line = bufferedReader.readLine()) != null)
	        result += line;
	
	    inputStream.close();
	    
	    try {
	    	JSONArray concerts = new JSONArray(result);
			for(int i = 0; i < concerts.length(); i++){
				JSONObject concert = concerts.getJSONObject(i);
				results.add(concert.getString("name") + " : " + concert.getString("date"));
			}
		} catch (JSONException e) {
			result = "FAILED";
		}
	    
	    if(results.isEmpty()){
	    	result = "FAILED";
	    }
	    
	    return results;
	
	}

}
