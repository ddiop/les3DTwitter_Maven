package fmin362.values;

import java.util.ArrayList;
import java.util.List;

import fmin362.model.UserAccount;

public class Tweet {

	private final List<UserAccount> userAccounts;

	public Tweet() {
		this.userAccounts = new ArrayList<UserAccount>();
	}

	public Tweet(List<UserAccount> userAccounts) {

		this.userAccounts = userAccounts;
	}

	public List<UserAccount> getUserAccounts() {
		return userAccounts;
	}

	public int countUserAccounts() {
		return userAccounts.size();
	}

	public int countMessages() {
		int count = 0;
		for (UserAccount userAccount : userAccounts) {
			count += userAccount.getMessages().size();
		}
		return count;
	}

}