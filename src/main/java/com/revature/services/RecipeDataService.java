package com.revature.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.revature.models.RecipeData;
import com.revature.repositories.RecipeDataRepository;

@Component ("recipeDataService")
public class RecipeDataService {

	private RecipeDataRepository recipeDataRepository;
	
	public RecipeDataService() {
	}

	
	
	public RecipeDataService(RecipeDataRepository recipeDataRepository) {
		super();
		this.recipeDataRepository = recipeDataRepository;
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



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
		if (recipeDataRepository == null) {
			if (other.recipeDataRepository != null)
				return false;
		} else if (!recipeDataRepository.equals(other.recipeDataRepository))
			return false;
		return true;
	}



	@Override
	public String toString() {
		return "RecipeDataService [recipeDataRepository=" + recipeDataRepository + "]";
	}

	
	
}
