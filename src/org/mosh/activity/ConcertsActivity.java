package org.mosh.activity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.petiapp.base.R;

public class ConcertsActivity extends Activity {

	TextView content;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.concerts_main);

		content   =  (TextView)findViewById(R.id.content);
		Button saveme = (Button) findViewById(R.id.get_concerts);

		saveme.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				// ALERT MESSAGE
				Toast.makeText(getBaseContext(),
						"Please wait, connecting to server.", Toast.LENGTH_LONG)
						.show();

				HttpClient Client = new DefaultHttpClient();

				// Create URL string

				String URL = "http://10.32.100.88:8080/mosh-services/concerts";

				// Log.i("httpget", URL);

				try {
					String responseContent = "";

					// Create Request to server and get response
//					HttpGet httpget = new HttpGet(URL);
//					ResponseHandler<String> responseHandler = new BasicResponseHandler();
//					SetServerString = Client.execute(httpget,
//							responseHandler);

					
					// Show response on activity
					content.setText(GET(URL));
				} catch (Exception ex) {
					content.setText("Failed : " + ex.toString());
				}
			}
		});
	}
	
	public static String GET(String url) throws ClientProtocolException, IOException{

		        InputStream inputStream = null;
		        String result = "";
		 
		            // create HttpClient
		            HttpClient httpclient = new DefaultHttpClient();
		 
		            // make GET request to the given URL
		            HttpResponse httpResponse = httpclient.execute(new HttpGet(url));
		 
		            // receive response as inputStream
		            inputStream = httpResponse.getEntity().getContent();
		 
		            // convert inputstream to string
		            if(inputStream != null)
		                result = convertInputStreamToString(inputStream);
		            else
		                result = "Did not work!";
		 
		        return result;
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
