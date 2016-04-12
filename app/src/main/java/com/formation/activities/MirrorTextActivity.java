package com.formation.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MirrorTextActivity extends Activity {
	
	SharedPreferences preferences;
	EditText toReverseView;
	TextView reversedView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);					
		setContentView(R.layout.activity_mirror_text);
		
		preferences = this.getSharedPreferences ("saveMirrorInputs", Context.MODE_WORLD_WRITEABLE);
		
		toReverseView = (EditText) findViewById(R.id.textarea_toReverse);
		reversedView = (TextView) findViewById(R.id.label_reversed);

		toReverseView.addTextChangedListener(new TextWatcher() {
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				StringBuffer reversedSb = new StringBuffer(s);
				reversedView.setText(reversedSb.reverse().toString());
			}

			@Override
			public void afterTextChanged(Editable s) {
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
			}
		});

		toReverseView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				toReverseView.setText("");
				reversedView.setText("");
			}
		});

		Button saveButton = (Button) findViewById(R.id.button_save);
		saveButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent mainActivity = new Intent(MirrorTextActivity.this, MainActivity.class);
				startActivity(mainActivity);
			}
		});
	}

	protected void onPause() {
		super.onPause();
		setContentView(R.layout.activity_mirror_text);
		
		SharedPreferences.Editor editor = preferences.edit();
		editor.putString("toReverse", toReverseView.getText().toString());
		editor.putString("reversed", reversedView.getText().toString());
		editor.commit();
	}

	@Override
	protected void onResume() {
		super.onResume();
		
		String toReverse = preferences.getString("toReverse", "");
		toReverseView.setText(toReverse);
		
		String reversed = preferences.getString("reversed", "");
		reversedView.setText(reversed);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.mirror_text, menu);
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
