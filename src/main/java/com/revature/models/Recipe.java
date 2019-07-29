package com.revature.models;

import java.util.Arrays;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Recipe {
	
	@Id
	@GeneratedValue
	private Integer tableId; //unique id for repository

	private Integer id; //id of recipe
	private Integer ownerId; //id of owner account
	private Ingredient[] ingredients;
	private Float quantity;
	private Boolean isCurrent = false,
					isFavorite = false,
					isHistory = false;
	
	public Recipe() {
	}



	public Recipe(Integer tableId, Integer id, Integer ownerId, Ingredient[] ingredients, Float quantity,
			Boolean isCurrent, Boolean isFavorite, Boolean isHistory) {
		super();
		this.tableId = tableId;
		this.id = id;
		this.ownerId = ownerId;
		this.ingredients = ingredients;
		this.quantity = quantity;
		this.isCurrent = isCurrent;
		this.isFavorite = isFavorite;
		this.isHistory = isHistory;
	}



	public Integer getTableId() {
		return tableId;
	}



	public void setTableId(Integer tableId) {
		this.tableId = tableId;
	}



	public Integer getOwnerId() {
		return ownerId;
	}



	public void setOwnerId(Integer ownerId) {
		this.ownerId = ownerId;
	}



	public Boolean getIsCurrent() {
		return isCurrent;
	}



	public void setIsCurrent(Boolean isCurrent) {
		this.isCurrent = isCurrent;
	}



	public Boolean getIsFavorite() {
		return isFavorite;
	}



	public void setIsFavorite(Boolean isFavorite) {
		this.isFavorite = isFavorite;
	}



	public Boolean getIsHistory() {
		return isHistory;
	}



	public void setIsHistory(Boolean isHistory) {
		this.isHistory = isHistory;
	}



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Ingredient[] getIngredients() {
		return ingredients;
	}

	public void setIngredients(Ingredient[] ingredients) {
		this.ingredients = ingredients;
	}

	public Float getQuantity() {
		return quantity;
	}

	public void setQuantity(Float quantity) {
		this.quantity = quantity;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + Arrays.hashCode(ingredients);
		result = prime * result + ((isCurrent == null) ? 0 : isCurrent.hashCode());
		result = prime * result + ((isFavorite == null) ? 0 : isFavorite.hashCode());
		result = prime * result + ((isHistory == null) ? 0 : isHistory.hashCode());
		result = prime * result + ((ownerId == null) ? 0 : ownerId.hashCode());
		result = prime * result + ((quantity == null) ? 0 : quantity.hashCode());
		result = prime * result + ((tableId == null) ? 0 : tableId.hashCode());
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
		Recipe other = (Recipe) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (!Arrays.equals(ingredients, other.ingredients))
			return false;
		if (isCurrent == null) {
			if (other.isCurrent != null)
				return false;
		} else if (!isCurrent.equals(other.isCurrent))
			return false;
		if (isFavorite == null) {
			if (other.isFavorite != null)
				return false;
		} else if (!isFavorite.equals(other.isFavorite))
			return false;
		if (isHistory == null) {
			if (other.isHistory != null)
				return false;
		} else if (!isHistory.equals(other.isHistory))
			return false;
		if (ownerId == null) {
			if (other.ownerId != null)
				return false;
		} else if (!ownerId.equals(other.ownerId))
			return false;
		if (quantity == null) {
			if (other.quantity != null)
				return false;
		} else if (!quantity.equals(other.quantity))
			return false;
		if (tableId == null) {
			if (other.tableId != null)
				return false;
		} else if (!tableId.equals(other.tableId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Recipe [tableId=" + tableId + ", id=" + id + ", ownerId=" + ownerId + ", ingredients="
				+ Arrays.toString(ingredients) + ", quantity=" + quantity + ", isCurrent=" + isCurrent + ", isFavorite="
				+ isFavorite + ", isHistory=" + isHistory + "]";
	}

}
