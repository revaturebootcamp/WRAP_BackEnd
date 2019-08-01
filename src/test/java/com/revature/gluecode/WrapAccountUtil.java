package com.revature.gluecode;

import com.revature.models.UserAccount;

import cucumber.api.java.Before;

public class WrapAccountUtil {
	
	private UserAccount user;

	public UserAccount getUser() {
		return user;
	}

	@Before
	public void setup () {
		this.user = new UserAccount ();
	}
	
	
	
}
