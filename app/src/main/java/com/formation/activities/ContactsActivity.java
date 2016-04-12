package com.formation.activities;

import android.Manifest;
import android.app.ListActivity;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.CursorLoader;
import android.content.Loader;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SimpleCursorAdapter;

public class ContactsActivity extends ListActivity implements LoaderCallbacks<Cursor> {

	SimpleCursorAdapter mAdapter;

	// Contacts Permissions
	private static final int REQUEST_CONTACTS = 1;
	private static String[] PERMISSIONS_CONTACTS = { Manifest.permission.READ_CONTACTS };

	// These are the Contacts rows that we will retrieve
	static final String[] PROJECTION = new String[] { ContactsContract.Data._ID, ContactsContract.Data.DISPLAY_NAME };

	// This is the select criteria
	static final String SELECTION = "((" + ContactsContract.Data.DISPLAY_NAME + " NOTNULL) AND ("
			+ ContactsContract.Data.DISPLAY_NAME + " != '' ))";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// setContentView(R.layout.activity_contacts);

		verifyContactsPermissions();

		String[] fromColumns = { ContactsContract.Data.DISPLAY_NAME };
		int[] toViews = { android.R.id.text1 }; 

		mAdapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_1, null, fromColumns, toViews, 0);
		setListAdapter(mAdapter);

		getLoaderManager().initLoader(0, null, this);
	}

	public void verifyContactsPermissions() {
		if (ContextCompat.checkSelfPermission(ContactsActivity.this,
				Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {

			if (ActivityCompat.shouldShowRequestPermissionRationale(ContactsActivity.this,
					Manifest.permission.READ_CONTACTS)) {

			} else {
				ActivityCompat.requestPermissions(ContactsActivity.this,
						new String[] { Manifest.permission.READ_CONTACTS }, REQUEST_CONTACTS);
			}
		} else {
			getLoaderManager().initLoader(0, null, this);
		}
	}

	@Override
	public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
		switch (requestCode) {
			case REQUEST_CONTACTS: {
				if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
					getLoaderManager().initLoader(0, null, this);
				} else {
				}
				return;
			}
		}
	}

	@Override
	public Loader<Cursor> onCreateLoader(int id, Bundle args) {
		return new CursorLoader(this, ContactsContract.Data.CONTENT_URI, PROJECTION, SELECTION, null, null);
	}

	@Override
	public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
		data.moveToFirst();
		while (!data.isAfterLast()) {
			long itemId = data.getLong(data.getColumnIndexOrThrow(ContactsContract.Data._ID));
			String displayName = data.getString(data.getColumnIndexOrThrow(ContactsContract.Data.DISPLAY_NAME));
			//Log.e("contact", itemId + " " + displayName);
			data.moveToNext();
		}
		mAdapter.swapCursor(data);
	}

	@Override
	public void onLoaderReset(Loader<Cursor> loader) {
		mAdapter.swapCursor(null);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.contacts, menu);
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
