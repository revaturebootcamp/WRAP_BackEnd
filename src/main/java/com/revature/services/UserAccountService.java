package com.revature.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.Cookie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.revature.models.UserAccount;
import com.revature.repositories.UserAccountRepository;

@Component ("userAccountService")
public class UserAccountService {

	private UserAccountRepository userAccountRepository;
	private Random random = new Random ();
	private Map<Cookie,UserAccount> userCache = new HashMap<Cookie,UserAccount> (); 
	
	private static final String URL = "WRAP_UserAccountCookie";
	
	public UserAccountService() {
	}


	public UserAccountService(UserAccountRepository userAccountRepository) {
		super();
		this.userAccountRepository = userAccountRepository;
	}

	public Cookie verifyLogin (UserAccount u, Cookie c) {
		if (this.userCache.containsKey(c) && this.userCache.get(c).equals(u)) {
			return c;
		}
		return this.login (u);
	}
	
	public Cookie login (UserAccount u) {
		if ((this.userAccountRepository.findByUsernameIgnoreCaseAndPassword (u.getUsername(), u.getPassword())).isEmpty()) {
			return new Cookie (URL, String.valueOf(u.getUsername().hashCode() + random.nextInt()));
		}
		return null;
	}
	
	public String insertUserAccount (UserAccount u) {
		this.userAccountRepository.save(u);
		return "Added user: " + u.getUsername();
	}
	
	public List<UserAccount> selectAllUserAccounts () {
		return this.userAccountRepository.findAll();
	}

	public UserAccountRepository getUserAccountRepository() {
		return userAccountRepository;
	}

	@Autowired
	public void setUserAccountRepository(UserAccountRepository userAccountRepository) {
		this.userAccountRepository = userAccountRepository;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((userAccountRepository == null) ? 0 : userAccountRepository.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserAccountService other = (UserAccountService) obj;
		if (userAccountRepository == null) {
			if (other.userAccountRepository != null)
				return false;
		} else if (!userAccountRepository.equals(other.userAccountRepository))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "UserAccountService [userAccountRepository=" + userAccountRepository + "]";
	}


}
