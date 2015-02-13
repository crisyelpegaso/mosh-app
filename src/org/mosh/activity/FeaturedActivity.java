package org.mosh.activity;

import org.mosh.R;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;

public class FeaturedActivity extends BaseActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.featured);

	    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
	}

}
