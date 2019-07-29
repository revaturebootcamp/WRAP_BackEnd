package com.revature.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.models.Recipe;
import com.revature.repositories.RecipeRepository;

@Service("recipeService")
public class RecipeService {

	private RecipeRepository recipeRepository;
	
	public RecipeService() {
	}

	public RecipeService(RecipeRepository recipeRepository) {
		super();
		this.recipeRepository = recipeRepository;
	}

	
	
	public Recipe insertRecipe (Recipe r) {
		try {
			return this.recipeRepository.save(r);
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Recipe> getAllRecipes (Integer userId) {
		return this.recipeRepository.findAllByOwnerId (userId);
	}
	
	
	
	
	public RecipeRepository getRecipeRepository() {
		return recipeRepository;
	}

	@Autowired
	public void setRecipeRepository(RecipeRepository recipeRepository) {
		this.recipeRepository = recipeRepository;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((recipeRepository == null) ? 0 : recipeRepository.hashCode());
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
		RecipeService other = (RecipeService) obj;
		if (recipeRepository == null) {
			if (other.recipeRepository != null)
				return false;
		} else if (!recipeRepository.equals(other.recipeRepository))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "RecipeService [recipeRepository=" + recipeRepository + "]";
	}
	
	

}
