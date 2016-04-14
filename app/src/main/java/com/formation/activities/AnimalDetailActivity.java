package com.formation.activities;

import android.app.Activity;
import android.app.LoaderManager;
import android.content.ContentUris;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.formation.data.AnimalContract.Animals;

public class AnimalDetailActivity extends Activity implements LoaderManager.LoaderCallbacks<Cursor> {
    String animalDiet;
    String animalFamily;
    String animalName;
    String animalSex;
    int animalAge;
    private long uiAnimalId;
    private long animalId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animal_detail);

        uiAnimalId = getIntent().getExtras().getLong("animalId");

        getLoaderManager().initLoader(0, null, this);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {

        Uri singleRowUri = ContentUris.withAppendedId(Uri.parse(Animals.SCHEME + Animals.AUTHORITY + Animals.SEPARATOR + Animals.TABLE_NAME), uiAnimalId);
        return new CursorLoader(this, singleRowUri, null, null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        data.moveToFirst();
        while (!data.isAfterLast()) {
            animalId = data.getLong(data.getColumnIndexOrThrow(Animals._ID));
            animalDiet = data.getString(data.getColumnIndexOrThrow(Animals.COLUMN_NAME_DIET));
            animalFamily = data.getString(data.getColumnIndexOrThrow(Animals.COLUMN_NAME_FAMILY));
            animalName = data.getString(data.getColumnIndexOrThrow(Animals.COLUMN_NAME_NAME));
            animalSex = data.getString(data.getColumnIndexOrThrow(Animals.COLUMN_NAME_SEX));
            animalAge = data.getInt(data.getColumnIndexOrThrow(Animals.COLUMN_NAME_AGE));

            data.moveToNext();
        }
        initView();
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
    }

    private void initView() {
        TextView id = (TextView) findViewById(R.id.detail_id);
        id.setText(Long.toString(animalId));

        TextView diet = (TextView) findViewById(R.id.detail_diet);
        diet.setText(animalDiet);

        TextView family = (TextView) findViewById(R.id.detail_family);
        family.setText(animalFamily);

        TextView name = (TextView) findViewById(R.id.detail_name);
        name.setText(animalName);

        TextView sex = (TextView) findViewById(R.id.detail_sex);
        sex.setText(animalSex);

        TextView age = (TextView) findViewById(R.id.detail_age);
        age.setText(Integer.toString(animalAge));
    }

    protected void onPause() {
        super.onPause();
        setContentView(R.layout.activity_animal_detail);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.animal_detail, menu);
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
