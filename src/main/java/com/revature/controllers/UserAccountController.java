package com.revature.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.models.UserAccount;
import com.revature.repositories.UserAccountRepository;

@RestController
@RequestMapping ("/useraccount")
public class UserAccountController {

	@Autowired
	private UserAccountRepository userAccountRepository;
	
	public UserAccountController() {
	}

	@GetMapping (value="/hello")
	public String sayHello () {
		System.out.println("hello!!");
		return "hello";
	}
	
	@PostMapping (value="/insert")
	public Boolean insertAccount (@RequestBody UserAccount u) {
		return true;
	}
}
