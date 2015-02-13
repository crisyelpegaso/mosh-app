package org.mosh.activity;

import org.mosh.R;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class BaseActivity extends ActionBarActivity  {

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    // Inflate the menu items for use in the action bar
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.main, menu);
	    return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle presses on the action bar items
	    switch (item.getItemId()) {
	        case R.id.action_search:
	            onSearch();
	            return true;
	        case R.id.action_settings:
	        	onSettings();
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}

	private void onSearch(){
		Intent intent = new Intent(this, SearchActivity.class);
		startActivity(intent);
	}
	
	private void onSettings(){
		
	}

}
