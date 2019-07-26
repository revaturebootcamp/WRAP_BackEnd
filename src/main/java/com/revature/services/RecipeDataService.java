package com.revature.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.revature.models.Recipe;
import com.revature.models.RecipeData;
import com.revature.models.UserRecipe;
import com.revature.repositories.CurrentRecipeRepository;
import com.revature.repositories.FavoriteRecipeRepository;
import com.revature.repositories.HistoryRecipeRepository;
import com.revature.repositories.RecipeDataRepository;

@Component ("recipeDataService")
public class RecipeDataService {

	private RecipeDataRepository recipeDataRepository;
	
	private CurrentRecipeRepository currentRecipeRepository;
	private FavoriteRecipeRepository favoriteRecipeRepository;
	private HistoryRecipeRepository historyRecipeRepository;
	
	public RecipeDataService() {
	}

	
	
	public RecipeDataService(RecipeDataRepository recipeDataRepository, CurrentRecipeRepository currentRecipeRepository,
			FavoriteRecipeRepository favoriteRecipeRepository, HistoryRecipeRepository historyRecipeRepository) {
		super();
		this.recipeDataRepository = recipeDataRepository;
		this.currentRecipeRepository = currentRecipeRepository;
		this.favoriteRecipeRepository = favoriteRecipeRepository;
		this.historyRecipeRepository = historyRecipeRepository;
	}


	public UserRecipe getCurrentRecipes (Integer id) {
		UserRecipe recipes = null;
		
		try {
			recipes = this.currentRecipeRepository.findByOwnerId (id);
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return recipes;
	}
	
	public void saveCurrentRecipes (UserRecipe userRecipe) {
		this.currentRecipeRepository.save(userRecipe);
	}

	public UserRecipe getFavoriteRecipes (Integer id) {
		UserRecipe recipes = null;
		
		try {
			recipes = this.favoriteRecipeRepository.findByOwnerId (id);
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return recipes;
	}
	
	public void saveFavoriteRecipes (UserRecipe userRecipe) {
		this.favoriteRecipeRepository.save(userRecipe);
	}

	public UserRecipe getHistoryRecipes (Integer id) {
		UserRecipe recipes = null;
		
		try {
			recipes = this.historyRecipeRepository.findByOwnerId (id);
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return recipes;
	}
	
	public void saveHistoryRecipes (UserRecipe userRecipe) {
		this.favoriteRecipeRepository.save(userRecipe);
	}

	
	
	public RecipeData getRecipeDataForUser (Integer id) {
		return this.recipeDataRepository.findByOwnerId (id);
	}
	
	public Boolean setRecipeDataForUser (RecipeData recipeData) {
		if (null != this.recipeDataRepository.findByOwnerId(recipeData.getOwnerId())) {
			this.recipeDataRepository.deleteById(recipeData.getOwnerId());
		}
		this.recipeDataRepository.save(recipeData);
		
		return true;
	}
	

	public RecipeDataRepository getRecipeDataRepository() {
		return recipeDataRepository;
	}

	
	@Autowired
	public void setRecipeDataRepository(RecipeDataRepository recipeDataRepository) {
		this.recipeDataRepository = recipeDataRepository;
	}



	public CurrentRecipeRepository getCurrentRecipeRepository() {
		return currentRecipeRepository;
	}


	@Autowired
	public void setCurrentRecipeRepository(CurrentRecipeRepository currentRecipeRepository) {
		this.currentRecipeRepository = currentRecipeRepository;
	}



	public FavoriteRecipeRepository getFavoriteRecipeRepository() {
		return favoriteRecipeRepository;
	}


	@Autowired
	public void setFavoriteRecipeRepository(FavoriteRecipeRepository favoriteRecipeRepository) {
		this.favoriteRecipeRepository = favoriteRecipeRepository;
	}



	public HistoryRecipeRepository getHistoryRecipeRepository() {
		return historyRecipeRepository;
	}


	@Autowired
	public void setHistoryRecipeRepository(HistoryRecipeRepository historyRecipeRepository) {
		this.historyRecipeRepository = historyRecipeRepository;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((currentRecipeRepository == null) ? 0 : currentRecipeRepository.hashCode());
		result = prime * result + ((favoriteRecipeRepository == null) ? 0 : favoriteRecipeRepository.hashCode());
		result = prime * result + ((historyRecipeRepository == null) ? 0 : historyRecipeRepository.hashCode());
		result = prime * result + ((recipeDataRepository == null) ? 0 : recipeDataRepository.hashCode());
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
		RecipeDataService other = (RecipeDataService) obj;
		if (currentRecipeRepository == null) {
			if (other.currentRecipeRepository != null)
				return false;
		} else if (!currentRecipeRepository.equals(other.currentRecipeRepository))
			return false;
		if (favoriteRecipeRepository == null) {
			if (other.favoriteRecipeRepository != null)
				return false;
		} else if (!favoriteRecipeRepository.equals(other.favoriteRecipeRepository))
			return false;
		if (historyRecipeRepository == null) {
			if (other.historyRecipeRepository != null)
				return false;
		} else if (!historyRecipeRepository.equals(other.historyRecipeRepository))
			return false;
		if (recipeDataRepository == null) {
			if (other.recipeDataRepository != null)
				return false;
		} else if (!recipeDataRepository.equals(other.recipeDataRepository))
			return false;
		return true;
	}



	@Override
	public String toString() {
		return "RecipeDataService [recipeDataRepository=" + recipeDataRepository + ", currentRecipeRepository="
				+ currentRecipeRepository + ", favoriteRecipeRepository=" + favoriteRecipeRepository
				+ ", historyRecipeRepository=" + historyRecipeRepository + "]";
	}

	
	
}
