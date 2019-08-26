package com.revature.controllers;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@CrossOrigin (allowCredentials = "true")
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

	
	
	
	
	
	/*
	 * These are for use
	 */
	
	@GetMapping (value="/logout")
	public Boolean logout (@CookieValue (UserAccountService.COOKIE) String cookie,
			HttpServletResponse response) {
		//set cookie to be removed by browser
		Cookie del = new Cookie (UserAccountService.COOKIE , "");
		del.setMaxAge(0);
		response.addCookie(del);
		
		//remove account information on the backend
		return this.userAccountService.logout (cookie);
	}
	

	@GetMapping (value="/verifyAccount")
	public Boolean verifyAccount (@CookieValue (UserAccountService.COOKIE) String cookie) {
		return this.userAccountService.verifyLogin(cookie);
	}
	
	@PostMapping (value="/insert")
	public Boolean insertAccount (@RequestBody UserAccount userAccount) {
		return this.userAccountService.insertUserAccount(userAccount);
	}

	@PostMapping (value="/login") 
	public Boolean login (HttpServletResponse response, @RequestBody UserAccount userAccount) {
		Cookie cookie = this.userAccountService.login(userAccount);
		
		if (null == cookie) {
			System.out.println("null on login");
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
	

	@PostMapping (value="recipe/insertmany")
	public List<Recipe> insertRecipes (@CookieValue (UserAccountService.COOKIE) String cookie,
										@RequestBody Recipe[] recipes) {
		UserAccount user = this.userAccountService.getAccount(cookie);
		if (null == user) {
			return null;
		}
		
		for (Recipe r : recipes) {
			r.setOwnerId(user.getId());
		}
		
		return this.recipeService.insertRecipes(recipes);		
	}
	
	
	@GetMapping (value="/recipe/find/all")
	public List<Recipe> getAllRecipes (@CookieValue (UserAccountService.COOKIE) String cookie) {
		UserAccount user = this.userAccountService.getAccount(cookie);
		
		if (null == user) {
			return null;
		}

		
		return this.recipeService.getAllRecipes (user.getId());
	}

	@GetMapping (value="/recipe/find/current")
	public List<Recipe> getCurrentRecipes (@CookieValue (UserAccountService.COOKIE) String cookie) {
		UserAccount user = this.userAccountService.getAccount(cookie);
		
		if (null == user) {
			return null;
		}
		
		return this.recipeService.getCurrentRecipes (user.getId());
	}

	@GetMapping (value="/recipe/find/favorite")
	public List<Recipe> getFavoriteRecipes (@CookieValue (UserAccountService.COOKIE) String cookie) {
		UserAccount user = this.userAccountService.getAccount(cookie);
		
		if (null == user) {
			return null;
		}
		
		return this.recipeService.getFavoriteRecipes (user.getId());
	}

	@GetMapping (value="/recipe/find/history")
	public List<Recipe> getHistoryRecipes (@CookieValue (UserAccountService.COOKIE) String cookie) {
		UserAccount user = this.userAccountService.getAccount(cookie);
		if (null == user) {
			return null;
		}
		
		return this.recipeService.getHistoryRecipes (user.getId());
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
