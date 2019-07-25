package com.revature.models;

public class IngredientStatus {

	private Integer id;
	private Boolean isDone;
	private float quantity;
	
	public IngredientStatus() {
	}

	public IngredientStatus(Integer id, Boolean isDone, float quantity) {
		super();
		this.id = id;
		this.isDone = isDone;
		this.quantity = quantity;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Boolean getIsDone() {
		return isDone;
	}

	public void setIsDone(Boolean isDone) {
		this.isDone = isDone;
	}

	public float getQuantity() {
		return quantity;
	}

	public void setQuantity(float quantity) {
		this.quantity = quantity;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((isDone == null) ? 0 : isDone.hashCode());
		result = prime * result + Float.floatToIntBits(quantity);
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
		IngredientStatus other = (IngredientStatus) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (isDone == null) {
			if (other.isDone != null)
				return false;
		} else if (!isDone.equals(other.isDone))
			return false;
		if (Float.floatToIntBits(quantity) != Float.floatToIntBits(other.quantity))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "IngredientStatus [id=" + id + ", isDone=" + isDone + ", quantity=" + quantity + "]";
	}

	
}
