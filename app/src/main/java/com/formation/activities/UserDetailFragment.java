package com.formation.activities;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import com.formation.model.User;
import com.formation.model.UserContent;

import android.Manifest;
import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * A fragment representing a single User detail screen. This fragment is either
 * contained in a {@link UserListActivity} in two-pane mode (on tablets) or a
 * {@link UserDetailActivity} on handsets.
 */
public class UserDetailFragment extends Fragment {

	public static final String ARG_USER_ID = "user_id";
	private View view;
	private User user;
	private CheckBox isActiveView;
	private SharedPreferences prefs;

	// Storage permissions
	private static final int REQUEST_EXTERNAL_STORAGE = 1;
	private static String[] PERMISSIONS_STORAGE = { Manifest.permission.READ_EXTERNAL_STORAGE,
			Manifest.permission.WRITE_EXTERNAL_STORAGE };

	public UserDetailFragment() {
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		prefs = getActivity().getSharedPreferences(this.getClass().toString(), Context.MODE_WORLD_READABLE);

		if (getArguments().containsKey(ARG_USER_ID)) {
			user = UserContent.USER_MAP.get(getArguments().getString(ARG_USER_ID));
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		view = inflater.inflate(R.layout.activity_user_detail_fragment, container, false);

		if (user != null) {
			initViews();
		}
		return view;
	}

	private void initViews() {
		ImageView logo = (ImageView) view.findViewById(R.id.detail_logo);
		logo.setImageResource(user.getPhoto());

		TextView fullname = (TextView) view.findViewById(R.id.detail_fullname);
		fullname.setText(user.getFirstName() + " " + user.getLastName());

		isActiveView = (CheckBox) view.findViewById(R.id.detail_isActive);
		if (user.isActive() == Boolean.TRUE) {
			isActiveView.setText("Actif");
			isActiveView.setChecked(Boolean.TRUE);
		} else {
			isActiveView.setText("Inactif");
			isActiveView.setChecked(Boolean.FALSE);
		}

		addListenerOnIsActive();
		storeInputChanges();
		readExternLogoAfterStoragePermissionsVerification();
	}

	private void addListenerOnIsActive() {
		isActiveView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (((CheckBox) v).isChecked()) {
					isActiveView.setText("Actif");
					user.setActive(Boolean.TRUE);
				} else {
					isActiveView.setText("Inactif");
					user.setActive(Boolean.FALSE);
				}
				storeInputChanges();
				readExternLogoAfterStoragePermissionsVerification();
			}
		});
	}

	public void readExternLogoAfterStoragePermissionsVerification() {
		if (ContextCompat.checkSelfPermission(getActivity(),
				Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

			if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
					Manifest.permission.READ_EXTERNAL_STORAGE)) {

			} else {
				ActivityCompat.requestPermissions(getActivity(),
						new String[] { Manifest.permission.READ_EXTERNAL_STORAGE }, REQUEST_EXTERNAL_STORAGE);
			}
		} else {
			if (user.isActive() == Boolean.TRUE) {
				readExternLogo("active");
			} else {
				readExternLogo("inactive");
			}
		}
	}

	@Override
	public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
		switch (requestCode) {
		case REQUEST_EXTERNAL_STORAGE: {
			if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
				if (user.isActive() == Boolean.TRUE) {
					readExternLogo("active");
				} else {
					readExternLogo("inactive");
				}
			} else {
			}
			return;
		}
		}
	}

	public void readExternLogo(String photoName) {
		String dirName = "Download";
		String photo = photoName + ".jpg";

		File photoFile = new File(File.separator + "sdcard" + File.separator + dirName + File.separator + photo);
		InputStream iStream;
		try {
			iStream = new BufferedInputStream(new FileInputStream(photoFile));
			Bitmap bm = BitmapFactory.decodeStream(iStream);
			iStream.close();

			ImageView sdcardLogo = (ImageView) view.findViewById(R.id.detail_extern_logo);
			sdcardLogo.setImageBitmap(bm);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onPause() {
		super.onPause();
		storeInputChanges();
	}

	@Override
	public void onResume() {
		super.onResume();

		// Restore the values of the checkbox
		if (user.isActive() == Boolean.TRUE) {
			isActiveView.setChecked(prefs.getBoolean("isActive", Boolean.TRUE));
		} else {
			isActiveView.setChecked(prefs.getBoolean("isActive", Boolean.FALSE));
		}
	}

	private void storeInputChanges() {
		SharedPreferences.Editor editor = prefs.edit();
		editor.putBoolean("isActive", user.isActive());
		editor.commit();
	}
}
