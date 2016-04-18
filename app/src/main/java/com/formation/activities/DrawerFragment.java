package com.formation.activities;

import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class DrawerFragment extends ListFragment {
    private List<String> drawerTitles;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        initButtonList();

        setListAdapter(new ArrayAdapter<>(inflater.getContext(), R.layout.drawer_item, drawerTitles.toArray(new String[0])));

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    private void initButtonList() {
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
    public void onListItemClick(ListView l, View v, int position, long id) {
        selectItem(position);
    }

    private void selectItem(int position) {
        Intent intent = null;
        switch (position) {
            case 0:
                intent = new Intent(getActivity(), MirrorTextActivity.class);
                break;
            case 1:
                intent = new Intent(getActivity(), FormActivity.class);
                break;
            case 2:
                intent = new Intent(getActivity(), UsersActivity.class);
                break;
            case 3:
                intent = new Intent(getActivity(), ContactsActivity.class);
                break;
            case 4:
                Intent animalIntentService = new Intent(getActivity(), AnimalsIntentService.class);
                getActivity().startService(animalIntentService);

                intent = new Intent(getActivity(), AnimalsActivity.class);
                break;
            case 5:
                intent = new Intent(getActivity(), GoogleMapsActivity.class);
                break;
            case 6:
                intent = new Intent(getActivity(), PreferencesActivity.class);
                break;
        }
        startActivity(intent);
    }
}
