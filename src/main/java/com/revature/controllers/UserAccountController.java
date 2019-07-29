package com.revature.controllers;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.models.Recipe;
import com.revature.models.UserAccount;
import com.revature.services.RecipeService;
import com.revature.services.UserAccountService;

@RestController
@RequestMapping ("/useraccount")
public class UserAccountController {

	private UserAccountService userAccountService;
	private RecipeService recipeService;
	
	
	public UserAccountController() {
	}

	/*
	 * These are for testing purposes
	 */
	@GetMapping (value="/hello")
	public String sayHello () {
		System.out.println("hello!!");
		return "hello";
	}
	
	@GetMapping (value="/getAll")
	public List<UserAccount> getAll () {
		return this.userAccountService.selectAllUserAccounts();
	}

	@GetMapping (value="/verifyAccount")
	public Boolean verifyAccount (@CookieValue (UserAccountService.COOKIE) String cookie) {
		return this.userAccountService.verifyLogin(cookie);
	}
	
	
	
	/*
	 * These are for use
	 */
	
	@PostMapping (value="/insert")
	public Boolean insertAccount (@RequestBody UserAccount userAccount) {
		return this.userAccountService.insertUserAccount(userAccount);
	}

	@PostMapping (value="/login") 
	public Boolean login (HttpServletResponse response, @RequestBody UserAccount userAccount) {
		Cookie cookie = this.userAccountService.login(userAccount);
		
		if (null == cookie) {
			return false;
		}
		
		response.addCookie(cookie);
		return true;
	}
	
	
	
	
	@PostMapping (value="/recipe/insert")
	public Recipe insertRecipe (@CookieValue (UserAccountService.COOKIE) String cookie, 
								@RequestBody Recipe recipe) {
		UserAccount user = this.userAccountService.getAccount(cookie);
		if (null == user) {
			return null;
		}
		recipe.setOwnerId(user.getId());
		
		return this.recipeService.insertRecipe (recipe);
	}
	
	@GetMapping (value="/recipe/find/all")
	public List<Recipe> getAllRecipes (@CookieValue (UserAccountService.COOKIE) String cookie) {
		UserAccount user = this.userAccountService.getAccount(cookie);
		if (null == user) {
			return null;
		}
		
		
		return this.recipeService.getAllRecipes (user.getId());
	}
	

	
	
	
	
	
	
	
	
	public RecipeService getRecipeService() {
		return recipeService;
	}

	@Autowired
	public void setRecipeService(RecipeService recipeService) {
		this.recipeService = recipeService;
	}


	public UserAccountService getUserAccountService() {
		return userAccountService;
	}

	@Autowired
	public void setUserAccountService(UserAccountService userAccountService) {
		this.userAccountService = userAccountService;
	}
	
	
}
