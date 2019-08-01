package com.revature.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.Cookie;

import org.apache.log4j.Logger;
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
	
	private Logger logger = Logger.getLogger(UserAccountService.class);
	
	public UserAccountService() {
	}


	public UserAccountService(UserAccountRepository userAccountRepository) {
		super();
		this.userAccountRepository = userAccountRepository;
	}

	
	public Boolean verifyLogin (String cookie) {
		return this.cookieCache.containsKey(cookie);
	}
	
	public UserAccount getAccount (String cookie) {
		return this.cookieCache.get(cookie);
	}
	
	public Boolean logout (String cookie) {
		if (null == cookie) {
			return false;
		}
		
		UserAccount user = this.cookieCache.get(cookie);
		logger.info("Logout invoked by user: " + user);
		
		if (null == user) {
			return false;
		}
		
		this.cookieCache.remove (cookie);
		this.userCache.remove(user);
		
		return true;
	}
	

	
	public Cookie login (UserAccount u) {
		
		if (null == u) {
			logger.warn("Failed login with null credentials");
			return null;
		}
		
		logger.info("Attempted login for username: " + u.getUsername());
		
		UserAccount user = this.userAccountRepository.findByUsernameIgnoreCaseAndPassword (
							u.getUsername(), u.getPassword());

		if (null == user) {
			logger.warn("Failed login for username: " + u.getUsername());
			
			return null;
		}
		
		logger.info("Successful login for username: " + u.getUsername());
		
		//ensure if there is an old cookie value, it is removed
		user.setPassword(null); //set null since not storing password locally
		String badCookie = this.userCache.get(user);
		//remove cached data 
		this.logout(badCookie);
		
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
		
		this.cookieCache.put(cookie.getValue(), user);
		this.userCache.put(user, cookie.getValue());
		
		return cookie;

	}
	
	public Boolean insertUserAccount (UserAccount u) {
		if (null == u) {
			logger.warn("Failed to insert null user account");
			return false;
		}
		
		try {
			this.userAccountRepository.save(u);
		}
		catch (Exception e) {
			logger.warn("Failed to insert user account for username: " + u.getUsername());
			e.printStackTrace();
			return false;
		}
		
		logger.info("Successfully inserted new user account for username: " + u.getUsername());
		return true;
	}
	
	public List<UserAccount> selectAllUserAccounts () {
//		return this.userAccountRepository.findAll();
		return new ArrayList<UserAccount> ();
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
