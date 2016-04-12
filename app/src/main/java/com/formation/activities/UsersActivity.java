package com.formation.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

/**
 * An activity representing a list of Users. This activity has different
 * presentations for handset and tablet-size devices. On handsets, the activity
 * presents a list of items, which when touched, lead to a
 * {@link UserDetailActivity} representing item details. On tablets, the
 * activity presents the list of items and item details side-by-side using two
 * vertical panes.
 * <p>
 * The activity makes heavy use of fragments. The list of items is a
 * {@link UserListFragment} and the item details (if present) is a
 * {@link UserDetailFragment}.
 * <p>
 * This activity also implements the required {@link UserListFragment.Callbacks}
 * interface to listen for item selections.
 */
public class UsersActivity extends Activity implements UsersFragment.Callbacks {

	/**
	 * Whether or not the activity is in two-pane mode, i.e. running on a tablet device.
	 */
	private boolean mTwoPane;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_users);

		if (findViewById(R.id.userDetailFragment) != null) {
			mTwoPane = true;

			// In two-pane mode, list items should be given the 'activated'
			// state when touched.
			UsersFragment usersFragment = (UsersFragment) getFragmentManager().findFragmentById(R.id.usersFragment);
			usersFragment.setActivateOnItemClick(true);			
		}
	}

	@Override
	public void onItemClick(String id) {
		if (!mTwoPane) {
			Intent detailIntent = new Intent(UsersActivity.this, UserDetailActivity.class);
			detailIntent.putExtra(UserDetailFragment.ARG_USER_ID, id);
			startActivity(detailIntent);
		} else {
			Bundle arguments = new Bundle();
			arguments.putString(UserDetailFragment.ARG_USER_ID, id);

			UserDetailFragment detailFragment = new UserDetailFragment();
			detailFragment.setArguments(arguments);

			getFragmentManager().beginTransaction().replace(R.id.userDetailFragment, detailFragment).commit();
		}		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.users, menu);
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
