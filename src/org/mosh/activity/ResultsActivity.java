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

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class ResultsActivity extends BaseActivity {

	private static String endpoint = "http://10.32.100.88:8080/mosh-services";
	
	 // This is the Adapter being used to display the list's data
    private TextView content;
    
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        content = (TextView) findViewById(R.id.content);
        setContentView(R.layout.results);
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
			            	result = convertInputStreamToString(inputStream);
			            } 
		            }catch(Exception e){
		            	
		            }
		            
		            if (result == ""){
		            	return "No results found.";
		            }
		     
		        return result;
		}

	}

//convert inputstream to String
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
