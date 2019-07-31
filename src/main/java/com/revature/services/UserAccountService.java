package com.revature.services;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.Cookie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.models.UserAccount;
import com.revature.repositories.UserAccountRepository;

@Service ("userAccountService")
public class UserAccountService {

	private UserAccountRepository userAccountRepository;
	private Random random = new Random ();
	private Map<String,UserAccount> cookieCache = new HashMap<String,UserAccount> ();
	private Map<UserAccount,String> userCache = new HashMap<UserAccount,String> ();
	
	public static final String COOKIE = "WRAP_UserAccountCookie";
	private static final int LOGOUT_TIME = 120 * 60;
	
	public UserAccountService() {
	}


	public UserAccountService(UserAccountRepository userAccountRepository) {
		super();
		this.userAccountRepository = userAccountRepository;
	}

	public Integer getAccountId (UserAccount inputUser) {
		UserAccount user = inputUser;
		UserAccount user2 = this.userAccountRepository.findByUsernameIgnoreCase(user.getUsername());
		if (null == user2) {
			return null;
		}
		return user2.getId();
	}
	
	public Boolean verifyLogin (String cookie) {
		return this.cookieCache.containsKey(cookie);
	}
	
	public UserAccount getAccount (String cookie) {
		return this.cookieCache.get(cookie);
	}
	
	public Cookie login (UserAccount u) {
		UserAccount user = this.userAccountRepository.findByUsernameIgnoreCaseAndPassword (u.getUsername(), u.getPassword());

		if (null == user) {
			return null;
		}
		
		
		//ensure if there is an old cookie value, it is removed
		user.setPassword(null); //set null since not storing password locally
		String badCookie = this.userCache.get(user);
		if (null != badCookie) {
			cookieCache.remove(badCookie);
			userCache.remove(user);
		}
		
		//set the new cookie for the login
		Cookie cookie = null;
		do {
			cookie = new Cookie (UserAccountService.COOKIE, 
					String.valueOf(u.hashCode() * random.nextInt()) 
					+ (new Date ().getTime()));
		} while (this.cookieCache.containsKey(cookie.getValue()));
		
		//set security info
		cookie.setMaxAge(UserAccountService.LOGOUT_TIME);
		cookie.setHttpOnly(true);
//		cookie.setSecure(true);
		
		//remove user password
		u.setPassword(null);
		
		this.cookieCache.put(cookie.getValue(), u);
		this.userCache.put(user, cookie.getValue());
		
		return cookie;

	}
	
	public Boolean insertUserAccount (UserAccount u) {
		try {
			this.userAccountRepository.save(u);
		}
		catch (Exception e) {
			return false;
		}
		return true;
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
		result = prime * result + ((cookieCache == null) ? 0 : cookieCache.hashCode());
		result = prime * result + ((random == null) ? 0 : random.hashCode());
		result = prime * result + ((userAccountRepository == null) ? 0 : userAccountRepository.hashCode());
		result = prime * result + ((userCache == null) ? 0 : userCache.hashCode());
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
		if (cookieCache == null) {
			if (other.cookieCache != null)
				return false;
		} else if (!cookieCache.equals(other.cookieCache))
			return false;
		if (random == null) {
			if (other.random != null)
				return false;
		} else if (!random.equals(other.random))
			return false;
		if (userAccountRepository == null) {
			if (other.userAccountRepository != null)
				return false;
		} else if (!userAccountRepository.equals(other.userAccountRepository))
			return false;
		if (userCache == null) {
			if (other.userCache != null)
				return false;
		} else if (!userCache.equals(other.userCache))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "UserAccountService [userAccountRepository=" + userAccountRepository + ", random=" + random
				+ ", cookieCache=" + cookieCache + ", userCache=" + userCache + "]";
	}


}
