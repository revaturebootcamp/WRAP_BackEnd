package com.revature.controllers;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.revature.models.UserAccount;
import com.revature.services.UserAccountService;

@RestController
@RequestMapping ("/useraccount")
public class UserAccountController {

	private UserAccountService userAccountService;
	
	public UserAccountController() {
	}

	@GetMapping (value="/hello")
	public String sayHello () {
		System.out.println("hello!!");
		return "hello";
	}
	
	@PostMapping (value="/insert")
	public String insertAccount (@RequestBody UserAccount userAccount) {
		return this.userAccountService.insertUserAccount(userAccount);
	}

	@GetMapping (value="/getAll")
	public List<UserAccount> getAll () {
		return this.userAccountService.selectAllUserAccounts();
	}
	
	@PostMapping (value="/login") 
	public Boolean login (HttpServletResponse response, @RequestBody UserAccount userAccount) {
		Cookie cookie = this.userAccountService.verifyLogin(userAccount, null);
		
		if (null == cookie) {
			return false;
		}
		
		response.addCookie(cookie);
		return true;
	}
	
	public UserAccountService getUserAccountService() {
		return userAccountService;
	}

	@Autowired
	public void setUserAccountService(UserAccountService userAccountService) {
		this.userAccountService = userAccountService;
	}
	
	
}
