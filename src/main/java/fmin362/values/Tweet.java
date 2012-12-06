package fmin362.values;

import java.util.ArrayList;
import java.util.List;

import fmin362.model.User;

public class Tweet {
	private List<User> users;

	public Tweet() {
		this.users = new ArrayList<User>();
	}

	public Tweet(List<User> users) {

		this.users = users;
	}

	public List<User> getUsers() {
		return users;
	}

	public int countUsers() {
		return users.size();
	}

	public int countMessages() {
		int count = 0;
		for (User user : users) {
			count += user.getMessages().size();
		}
		return count;
	}
}