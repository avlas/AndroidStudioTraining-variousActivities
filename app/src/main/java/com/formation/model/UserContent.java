package com.formation.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.formation.activities.R;

public class UserContent {

	public static List<User> USERS = new ArrayList<User>();

	public static Map<String, User> USER_MAP = new HashMap<String, User>();

	static {
		addUser(new User("1","Tete", "Titi", R.drawable.nature));
		addUser(new User("2","Titi", "Toto", R.drawable.nature));
		addUser(new User("3","Toto", "Tutu", R.drawable.nature));
		addUser(new User("4","Tutu", "Tata", R.drawable.nature));
		addUser(new User("5","Tete", "Titi", R.drawable.nature));
		addUser(new User("6","Titi", "Toto", R.drawable.nature));
		addUser(new User("7","Toto", "Tutu", R.drawable.nature));
		addUser(new User("8","Tutu", "Tata", R.drawable.nature));
		addUser(new User("9","Tete", "Titi", R.drawable.nature));
		addUser(new User("10","Titi", "Toto", R.drawable.nature));
		addUser(new User("11","Toto", "Tutu", R.drawable.nature));
		addUser(new User("12","Tutu", "Tata", R.drawable.nature));
	}

	private static void addUser(User user) {
		USERS.add(user);
		USER_MAP.put(user.id, user);
	}
}
