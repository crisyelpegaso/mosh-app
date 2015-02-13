package org.mosh.activity;

import org.mosh.R;

import android.content.Intent;
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
	
	static final String[] FRUITS = new String[] { "Apple", "Avocado", "Banana",
		"Blueberry", "Coconut", "Durian", "Guava", "Kiwifruit",
		"Jackfruit", "Mango", "Olive", "Pear", "Sugar-apple" };

	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.featured);
	    
	    featuredConcerts = (ListView) findViewById(R.id.featuredConcerts);
	     
	    listAdapter = new ArrayAdapter<String>(this, R.layout.list_featured, FRUITS);  
	    featuredConcerts.setAdapter(listAdapter);

	    featuredConcerts.setTextFilterEnabled(true);
		
	    featuredConcerts.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
			    // When clicked, show a toast with the TextView text
			    Toast.makeText(getApplicationContext(),
				((TextView) view).getText(), Toast.LENGTH_SHORT).show();
			    Intent intent = new Intent(view.getContext(), ConcertActivity.class);
			    startActivity(intent);
			}

		});
	}

}
