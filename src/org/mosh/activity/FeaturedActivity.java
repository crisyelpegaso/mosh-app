package org.mosh.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import org.mosh.R;
import org.mosh.adapter.ConcertAdapter;
import org.mosh.async.task.GetConcertsTask;

public class FeaturedActivity extends BaseActivity {

	private ListView featuredConcerts;
	private ConcertAdapter concertsListAdapter;


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.featured);

		// ALERT MESSAGE
		Toast.makeText(getBaseContext(), R.string.searching_toast, Toast.LENGTH_LONG).show();

		// Create URL string
		String URL = endpoint + "/concerts";

		// Initialize ConcerAdapter to retrieve results
		featuredConcerts = (ListView) findViewById(R.id.featuredConcerts);
		featuredConcerts.setTextFilterEnabled(true);

		try {
			new GetConcertsTask(this, concertsListAdapter).execute(URL);
			featuredConcerts.setAdapter(concertsListAdapter);
		} catch (Exception ex) {

		}

		featuredConcerts.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
									int position, long id) {
				Intent intent = new Intent(view.getContext(), ConcertActivity.class);
				startActivity(intent);
			}

		});
	}
}