package com.formation.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import butterknife.Bind;
import butterknife.ButterKnife;

public class FormActivity extends Activity {
    @Bind(R.id.imageView_logo) ImageView logo;
    @Bind(R.id.radioGroup_sex) RadioGroup sexRadioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        ButterKnife.bind(this);

        logo.setImageResource(R.drawable.nature);
    }

    public void onRadioButtonClicked(View view) {
        int selectedIdRadioBtn = sexRadioGroup.getCheckedRadioButtonId();
        RadioButton sexRadioBtn = (RadioButton) findViewById(selectedIdRadioBtn);
    }

    protected void onPause() {
        super.onPause();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.form, menu);
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
