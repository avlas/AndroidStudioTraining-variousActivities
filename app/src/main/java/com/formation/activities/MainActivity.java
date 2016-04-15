package com.formation.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {
    private List<String> drawerTitles;
    private DrawerLayout drawerLayout;
    private ListView drawerList;
    private CharSequence title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer_list);

        initDrawerList();

        title = getTitle();
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerList = (ListView) findViewById(R.id.left_drawer);

        drawerList.setAdapter(new ArrayAdapter<>(this, R.layout.drawer_item, drawerTitles));
        drawerList.setOnItemClickListener(new ListView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View view, int position, long id) {
                selectItem(position);
            }
        });
    }

    private void initDrawerList() {
        drawerTitles = new ArrayList<>();
        drawerTitles.add(getString(R.string.mirror_text));
        drawerTitles.add(getString(R.string.form));
        drawerTitles.add(getString(R.string.users));
        drawerTitles.add(getString(R.string.contacts));
        drawerTitles.add(getString(R.string.animals));
        drawerTitles.add(getString(R.string.google_maps));
        drawerTitles.add(getString(R.string.preferences));
    }

    @Override
    public void setTitle(CharSequence title) {
        this.title = title;
        getActionBar().setTitle(this.title);
    }

    private void selectItem(int position) {
        Intent intent = null;
        switch (position) {
            case 0:
                intent = new Intent(MainActivity.this, MirrorTextActivity.class);
                break;
            case 1:
                intent = new Intent(MainActivity.this, FormActivity.class);
                break;
            case 2:
                intent = new Intent(MainActivity.this, UsersActivity.class);
                break;
            case 3:
                intent = new Intent(MainActivity.this, ContactsActivity.class);
                break;
            case 4:
                Intent animalIntentService = new Intent(MainActivity.this, AnimalsIntentService.class);
                startService(animalIntentService);

                intent = new Intent(MainActivity.this, AnimalsActivity.class);
                break;
            case 5:
                intent = new Intent(MainActivity.this, GoogleMapsActivity.class);
                break;
            case 6:
                intent = new Intent(MainActivity.this, PreferencesActivity.class);
                break;
        }
        startActivity(intent);

        // Highlight the selected item, update the title, and close the drawer
        drawerList.setItemChecked(position, true);
        setTitle(drawerTitles.get(position));
        drawerLayout.closeDrawer(drawerList);
    }

    protected void onPause() {
        super.onPause();
        setContentView(R.layout.activity_drawer_list);
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
