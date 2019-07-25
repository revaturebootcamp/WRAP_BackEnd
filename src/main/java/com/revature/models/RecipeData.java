package com.revature.models;

import java.util.Arrays;

public class RecipeData {

	private Integer ownerId;
	private Recipe[] favorites,
					 current,
					 history;
	private IngredientStatus[] ingredientStatus;
	
	
	public RecipeData() {
	}


	public RecipeData(Integer ownerId, Recipe[] favorites, Recipe[] current, Recipe[] history,
			IngredientStatus[] ingredientStatus) {
		super();
		this.ownerId = ownerId;
		this.favorites = favorites;
		this.current = current;
		this.history = history;
		this.ingredientStatus = ingredientStatus;
	}


	public Integer getOwnerId() {
		return ownerId;
	}


	public void setOwnerId(Integer ownerId) {
		this.ownerId = ownerId;
	}


	public Recipe[] getFavorites() {
		return favorites;
	}


	public void setFavorites(Recipe[] favorites) {
		this.favorites = favorites;
	}


	public Recipe[] getCurrent() {
		return current;
	}


	public void setCurrent(Recipe[] current) {
		this.current = current;
	}


	public Recipe[] getHistory() {
		return history;
	}


	public void setHistory(Recipe[] history) {
		this.history = history;
	}


	public IngredientStatus[] getIngredientStatus() {
		return ingredientStatus;
	}


	public void setIngredientStatus(IngredientStatus[] ingredientStatus) {
		this.ingredientStatus = ingredientStatus;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(current);
		result = prime * result + Arrays.hashCode(favorites);
		result = prime * result + Arrays.hashCode(history);
		result = prime * result + Arrays.hashCode(ingredientStatus);
		result = prime * result + ((ownerId == null) ? 0 : ownerId.hashCode());
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
		RecipeData other = (RecipeData) obj;
		if (!Arrays.equals(current, other.current))
			return false;
		if (!Arrays.equals(favorites, other.favorites))
			return false;
		if (!Arrays.equals(history, other.history))
			return false;
		if (!Arrays.equals(ingredientStatus, other.ingredientStatus))
			return false;
		if (ownerId == null) {
			if (other.ownerId != null)
				return false;
		} else if (!ownerId.equals(other.ownerId))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "RecipeData [ownerId=" + ownerId + ", favorites=" + Arrays.toString(favorites) + ", current="
				+ Arrays.toString(current) + ", history=" + Arrays.toString(history) + ", ingredientStatus="
				+ Arrays.toString(ingredientStatus) + "]";
	}

	
}
