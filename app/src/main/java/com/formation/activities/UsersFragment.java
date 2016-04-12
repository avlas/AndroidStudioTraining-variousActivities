package com.formation.activities;

import com.formation.model.UserContent;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

/**
 * A list fragment representing a list of Users. This fragment also supports
 * tablet devices by allowing list items to be given an 'activated' state upon
 * selection. This helps indicate which item is currently being viewed in a
 * {@link UserDetailFragment}.
 * <p>
 * Activities containing this fragment MUST implement the {@link Callbacks}
 * interface.
 */
public class UsersFragment extends Fragment {
	private Callbacks mCallbacks;
	
	private ListView usersView;
	private UserArrayAdapter adapter;
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);

		// Activities containing this fragment must implement its callbacks.
		if (!(activity instanceof Callbacks)) {
			throw new IllegalStateException("Activity must implement fragment's callbacks.");
		}
		mCallbacks = (Callbacks) activity;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		View view = inflater.inflate(R.layout.activity_users_fragment, container, false);

		usersView = (ListView) view.findViewById(R.id.list_users);
		adapter = new UserArrayAdapter(view.getContext(), R.layout.activity_users_fragment_row, R.id.row_fullname, UserContent.USERS);
		usersView.setAdapter(adapter);

		addListenerOnList();

		return view;
	}

	private void addListenerOnList() {
		usersView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				//mCallbacks.onItemClick((User) adapter.getItem(position).id);*/
				mCallbacks.onItemClick(UserContent.USERS.get(position).id);
			}
		});
	}

	public interface Callbacks {		
		public void onItemClick(String id);		
	}

	public void setActivateOnItemClick(boolean activateOnItemClick) {
		usersView.setChoiceMode(activateOnItemClick ? ListView.CHOICE_MODE_SINGLE : ListView.CHOICE_MODE_NONE);
	}
}
