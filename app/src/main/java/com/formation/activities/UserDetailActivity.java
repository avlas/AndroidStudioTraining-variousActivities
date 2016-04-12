package com.formation.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

/**
 * An activity representing a single User detail screen. This activity is only
 * used on handset devices. On tablet-size devices, item details are presented
 * side-by-side with a list of items in a {@link UserListActivity}.
 * <p>
 * This activity is mostly just a 'shell' activity containing nothing more than
 * a {@link UserDetailFragment}.
 */
public class UserDetailActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_detail);

		// Show the Up button in the action bar.
		getActionBar().setDisplayHomeAsUpEnabled(true);
		
		// savedInstanceState is non-null when there is fragment state
		// saved from previous configurations of this activity
		// (e.g. when rotating the screen from portrait to landscape).
		// In this case, the fragment will automatically be re-added
		// to its container so we don't need to manually add it.
		// For more information, see the Fragments API guide at:
		// http://developer.android.com/guide/components/fragments.html		
		if (savedInstanceState == null) {
			// Create the detail fragment and add it to the activity using a fragment transaction	
			String userId = getIntent().getStringExtra(UserDetailFragment.ARG_USER_ID);			
			Bundle arguments = new Bundle();
			arguments.putString(UserDetailFragment.ARG_USER_ID, userId);
			
			UserDetailFragment detailFragment = new UserDetailFragment();
			detailFragment.setArguments(arguments);
			
			getFragmentManager().beginTransaction().replace(R.id.userDetailFragment, detailFragment).commit();
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.user_detail, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
