package org.mosh.activity;

import org.mosh.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class SearchActivity extends BaseActivity {

	EditText searchInput;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.search);

		searchInput = ((EditText) findViewById(R.id.search_input));

	}

	public void searchConcerts(View view) {

		Intent intent = new Intent(this, ResultsActivity.class);
		startActivity(intent);

	}

}
