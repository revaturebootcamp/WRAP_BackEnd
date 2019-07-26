package com.revature.models;

import java.util.Arrays;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class UserRecipe {

	@Id
	private Integer ownerId;
	private Recipe[] recipes;
	
	public UserRecipe() {
	}

	

	public UserRecipe(Integer ownerId, Recipe[] recipes) {
		super();
		this.ownerId = ownerId;
		this.recipes = recipes;
	}



	public Integer getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(Integer ownerId) {
		this.ownerId = ownerId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ownerId == null) ? 0 : ownerId.hashCode());
		result = prime * result + Arrays.hashCode(recipes);
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
		UserRecipe other = (UserRecipe) obj;
		if (ownerId == null) {
			if (other.ownerId != null)
				return false;
		} else if (!ownerId.equals(other.ownerId))
			return false;
		if (!Arrays.equals(recipes, other.recipes))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "UserRecipe [ownerId=" + ownerId + ", recipes=" + Arrays.toString(recipes) + "]";
	}

	public Recipe[] getRecipes() {
		return recipes;
	}

	public void setRecipes(Recipe[] recipes) {
		this.recipes = recipes;
	}

	
	
}
