package com.formation.activities;

import java.util.List;

import com.formation.model.User;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class UserArrayAdapter extends ArrayAdapter<User> {
	private Context context;
	private List<User> values;

	public UserArrayAdapter(Context context, int itemLayout, int itemId, List<User> values) {
		super(context, itemLayout,  itemId, values);
		this.context = context;
		this.values = values;
	}

	@Override
	// Appeller pour chaque item afin de remplir la list (du 0 au adapter.getCount())
	public View getView(int position, View convertView, ViewGroup listView) {
		View rowView;
		if (convertView == null) {
			// inflater = pour fabriquer une view a partir d'un fichier XML(le model d'une ligne)
//			LayoutInflater inflater = LayoutInflater.from(listView.getContext());
//			rowView = inflater.inflate(R.layout.activity_users_fragment_row, listView, false);

			rowView = super.getView(position, convertView, listView);
			
//			User user = values.get(position);
			User user = getItem(position);

			ImageView logoView = (ImageView) rowView.findViewById(R.id.row_logo);
			logoView.setImageResource(user.getPhoto());

			TextView fullnameView = (TextView) rowView.findViewById(R.id.row_fullname);
			fullnameView.setText(user.getFirstName() + " " + user.getLastName());

			if (user.isActive()) {
				rowView.setBackgroundColor(context.getResources().getColor(R.color.green));
			} else {
				rowView.setBackgroundColor(context.getResources().getColor(R.color.red));
			}
		} else {
			rowView = convertView;
		}
		return rowView;
	}

	public List<User> getValues() {
		return values;
	}

	/*
	 * public void updateData(List<User> list) { 
	 * super.clear();
	 * super.addAll(list.toArray(new User[0]));
	 * 
	 * this.values.clear(); this.values.addAll(list); }
	 */
}
