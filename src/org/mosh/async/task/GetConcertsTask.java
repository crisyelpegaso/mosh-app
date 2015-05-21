package org.mosh.async.task;

import android.content.Context;
import android.os.AsyncTask;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.mosh.adapter.ConcertAdapter;
import org.mosh.domain.Concert;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class GetConcertsTask extends AsyncTask<String, Void, List<Concert>> {

    private Context context;
    private ConcertAdapter concertsListAdapter;

    public GetConcertsTask(Context context, ConcertAdapter concertsListAdapter){
        this.context = context;
        this.concertsListAdapter = concertsListAdapter;
    }

    protected void onPostExecute(ArrayList<Concert> concertsFeatured){
        concertsListAdapter = new ConcertAdapter(this.context, concertsFeatured);
    }

    @Override
    protected List<Concert> doInBackground(String... urls) {
        List<Concert> results = new ArrayList<Concert>();
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
                results = ParseServerResponseUtil.getConcertsFromInputStream(inputStream);
            }
        }catch(Exception e){

        }

        if (!results.isEmpty()){
            concertsListAdapter.addAll(results);
        }

        return results;
    }


    public ConcertAdapter getConcertsAdapter(){
        return this.concertsListAdapter;
    }
}
