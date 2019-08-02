package com.revature.services;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.revature.models.Recipe;
import com.revature.models.UserAccount;
import com.revature.repositories.RecipeRepository;

@RunWith (SpringRunner.class)
@DataJpaTest
public class RecipeServiceTest {
	@TestConfiguration
	static class UserAccountServiceTestContextConfiguration {

	    @MockBean
	    private RecipeRepository recipeRepository;
		
		@Bean
		public RecipeService recipeService () {
			RecipeService serv = new RecipeService ();
			serv.setRecipeRepository(recipeRepository);
			return serv;
		}
	}
	
	
	@Autowired 
	private RecipeService recipeService;
	
	@Test
	public void givenUserId_thenGetAllRecipes () {
		RecipeRepository repo = this.recipeService.getRecipeRepository();
		List<Recipe> rets = new ArrayList<Recipe> ();
		Recipe recipe;
		recipe = new Recipe ();
		recipe.setId(1);
		recipe.setOwnerId(1);
		rets.add(recipe);
		recipe = new Recipe ();
		recipe.setId(2);
		recipe.setOwnerId(1);
		rets.add(recipe);
		when (repo.findAllByOwnerId(1)).thenReturn (rets);
		
		List<Recipe> rets2 = this.recipeService.getAllRecipes(1);
		
		for (int i = 0; i < rets2.size(); ++i) {
			assertEquals (rets.get(i).getId(), rets2.get(i).getId());
		}
		
	}
}





