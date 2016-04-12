package com.formation.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Button mirrorTextBtn = (Button) findViewById(R.id.button_mirrorText);
		mirrorTextBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent mirrorTextActivity = new Intent(MainActivity.this, MirrorTextActivity.class);
				startActivity(mirrorTextActivity);
			}
		});

		Button formBtn = (Button) findViewById(R.id.button_form);
		formBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent formActivity = new Intent(MainActivity.this, FormActivity.class);
				startActivity(formActivity);
			}
		});

		Button usersBtn = (Button) findViewById(R.id.button_users);
		usersBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent usersActivity = new Intent(MainActivity.this, UsersActivity.class);
				startActivity(usersActivity);
			}
		});

		Button contactsBtn = (Button) findViewById(R.id.button_contacts);
		contactsBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent contactsActivity = new Intent(MainActivity.this, ContactsActivity.class);
				startActivity(contactsActivity);
			}
		});

		Button animalsBtn = (Button) findViewById(R.id.button_animals);
		animalsBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent animalsActivity = new Intent(MainActivity.this, AnimalsActivity.class);
				startActivity(animalsActivity);
			}
		});
	}

	protected void onPause() {
		super.onPause();
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
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
